package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.UserBean;

/**
 * Created by hsg on 2016/10/26.
 */

public interface ILoginContract {

    interface IView extends IBaseContract.IBaseView{
        void updateLoginView(UserBean userBean);
    }

    interface IPresenter extends IBaseContract.IBasePresenter{
        void login(final String phoneNumber, final String password);
    }
}
