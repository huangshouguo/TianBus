package com.bus.tian.tianbus.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.bus.tian.tianbus.contract.IBaseContract;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by hsg on 10/26/16.
 */

public class ApiRspSubscriber<T> extends Subscriber<T> {
    private static final String TAG = "ApiRspSubscriber";

    private IBaseContract.IBaseView baseView;
    private String loadingMessage;
    private boolean shouldHideProgressDialog;
    private Observer<String> progressObserver;

    public ApiRspSubscriber(@NonNull final IBaseContract.IBaseView baseView) {
        this(baseView, "正在加载数据...");
    }

    public ApiRspSubscriber(@NonNull final IBaseContract.IBaseView baseView, @NonNull final String loadingMessage) {
        this(baseView, loadingMessage, false);
    }

    public ApiRspSubscriber(@NonNull final IBaseContract.IBaseView baseView, @NonNull final String loadingMessage, @NonNull final boolean shouldHideProgressDialog) {
        this.baseView = baseView;
        this.loadingMessage = loadingMessage;
        this.shouldHideProgressDialog = shouldHideProgressDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (TextUtils.isEmpty(this.loadingMessage)) {
            Log.e(TAG, "onStart: loadingMessage is empty!");
            return;
        }

        if (this.baseView == null) {
            Log.e(TAG, "onStart: baseView is null!");
            return;
        }

        this.progressObserver = this.baseView.showProgress(this.loadingMessage, this.shouldHideProgressDialog);
    }

    @Override
    public void onCompleted() {
        if (this.progressObserver == null) {
            Log.e(TAG, "onCompleted: progressObserver is null");
            return;
        }
        this.progressObserver.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError:", e);

        if (this.progressObserver == null) {
            Log.e(TAG, "onError: progressObserver is null");
            return;
        }

        if (this.baseView == null) {
            Log.e(TAG, "onError: baseView is null");
            return;
        }

        this.progressObserver.onError(new Throwable(getErrorMessage(e)));
        this.baseView.showErrorMessage(getErrorMessage(e));
    }

    @Override
    public void onNext(T t) {
        if (this.progressObserver == null) {
            Log.e(TAG, "onNext: progressObserver is null");
            return;
        }

        this.progressObserver.onNext("数据加载成功!");
    }

    private String getErrorMessage(Throwable e) {
        String errorMsg = e.getMessage();

        if (e instanceof HttpException) {
            errorMsg = ErrorMsgUtil.ERR_MSG_SERVER_ERROR;
        } else if (e instanceof UnknownHostException) {
            errorMsg = ErrorMsgUtil.ERR_MSG_UNKNOWN_HOST;
        } else if (e instanceof ConnectException) {
            errorMsg = ErrorMsgUtil.ERR_MSG_CONNECT_ERROR;
        } else if (e instanceof SocketTimeoutException) {
            errorMsg = ErrorMsgUtil.ERR_MSG_SOCKET_TIMEOUT;
        }

        Log.d(TAG, "getErrorMessage() returned: " + errorMsg);
        return errorMsg;
    }
}
