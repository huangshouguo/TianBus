package com.bus.tian.tianbus.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.bus.tian.tianbus.contract.IBaseContract;

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
        if (this.progressObserver != null) {
            this.progressObserver.onError(e);
            Log.e(TAG, "onError:" , e );
        }
    }

    @Override
    public void onNext(T t) {
        if (this.progressObserver != null){
            this.progressObserver.onNext("数据加载成功!");
        }
    }
}
