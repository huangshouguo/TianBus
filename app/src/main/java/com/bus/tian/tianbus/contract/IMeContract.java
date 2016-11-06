package com.bus.tian.tianbus.contract;

/**
 * Created by hsg on 2016/11/6.
 */

public interface IMeContract {
    interface IView extends IBaseContract.IBaseView{
        void updateView();
    }

    interface IPresenter extends IBaseContract.IBasePresenter{
        void logout();
    }
}
