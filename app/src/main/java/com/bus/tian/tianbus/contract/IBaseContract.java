package com.bus.tian.tianbus.contract;

import com.trello.rxlifecycle.LifecycleTransformer;

import rx.Observer;

/**
 * Created by hsg on 10/24/16.
 */

public interface IBaseContract {
    interface IBaseView {
        //rx subscription can be unsubscribed automatically
        <T> LifecycleTransformer<T> doBindToLifecycle();

        void showErrorMessage(final String errorMsg);

        Observer<String> showProgress(final String loadingMessage, final boolean shouldHideProgressDialog);
    }

    interface IBasePresenter {
        void onRelease();
    }
}
