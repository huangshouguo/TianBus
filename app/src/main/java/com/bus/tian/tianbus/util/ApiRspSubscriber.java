package com.bus.tian.tianbus.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.bus.tian.tianbus.contract.IBaseContract;

import java.net.ConnectException;
import java.net.SocketException;
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
    private boolean shouldHideProgressDialog;
    private Observer<String> progressObserver;

    public ApiRspSubscriber(@NonNull IBaseContract.IBaseView baseView) {
        this(baseView, false);
    }

    public ApiRspSubscriber(@NonNull IBaseContract.IBaseView baseView, @NonNull boolean shouldHideProgressDialog) {
        this.baseView = baseView;
        this.shouldHideProgressDialog = shouldHideProgressDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (this.baseView != null) {
            this.progressObserver = this.baseView.showProgress(this.shouldHideProgressDialog);
        }
    }

    @Override
    public void onCompleted() {
        if (this.progressObserver != null)
            this.progressObserver.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError:", e);

        if (this.progressObserver != null) {
            this.progressObserver.onError(new Throwable(getErrorMessage(e)));
        }

        Log.e(TAG, "onError: errorMsg = " + getErrorMessage(e));
    }

    @Override
    public void onNext(T t) {
        if (this.progressObserver != null) {
            this.progressObserver.onNext("数据加载成功!");
        }
    }

    private String getErrorMessage(Throwable e) {
        String errorMsg = e.getMessage();

        if (e instanceof HttpException) {
            errorMsg = ErrorMsgUtil.ERR_MSG_SERVER_ERROR;
        } else if (e instanceof UnknownHostException) {
            errorMsg = ErrorMsgUtil.ERR_MSG_UNKNOWN_HOST;
        } else if (e instanceof ConnectException) {
            errorMsg = ErrorMsgUtil.ERR_MSG_CONNECT_ERROR;
        } else if (e instanceof SocketException) {
            errorMsg = ErrorMsgUtil.ERR_MSG_SOCKET_TIMEOUT;
        }

        return errorMsg;
    }
}
