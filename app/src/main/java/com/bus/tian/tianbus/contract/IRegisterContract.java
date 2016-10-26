package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.UserBean;

/**
 * Created by hsg on 10/26/16.
 */

public interface IRegisterContract {
    interface IPreView extends IBaseContract.IBaseView {
        void updateView(UserBean userBean);
    }

    interface IFinishView extends IBaseContract.IBaseView {
        void updateSmsCaptchaView(UserBean userBean);

        void updateRegisterView(UserBean userBean);
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void findUserByPhoneNumber(final String phoneNumber);

        void loadSmsCaptcha(final String phoneNumber);

        void doRegister(final String phoneNumber, final String pwd, final String captchaValue, final String captchaId);
    }
}
