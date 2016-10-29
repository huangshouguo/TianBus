package com.bus.tian.tianbus.contract;

/**
 * Created by hsg on 2016/10/29.
 */

public interface IMainContract {
    interface IView extends IBaseContract.IBaseView {
        void startLoginActivity();
        void updateViewOnLogin();
        void updateViewOnLogout();
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void initRxEvent();
    }
}
