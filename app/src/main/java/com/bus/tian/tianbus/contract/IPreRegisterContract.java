package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.UserBean;

/**
 * Created by hsg on 10/27/16.
 */

public interface IPreRegisterContract {
    interface IView extends IBaseContract.IBaseView {
        void updateView(UserBean userBean);
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void findUserByPhoneNumber(final String phoneNumber);
    }
}
