package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.UserBean;

/**
 * Created by hsg on 10/28/16.
 */

public interface IForgotPasswordContract {

    interface IView extends IBaseContract.IBaseView {
        void updateSmsCaptchaView(UserBean userBean);

        void updateResetView();
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void loadSmsCaptcha(final String phoneNumber);

        void doResetPassword(final String phoneNumber, final String pwd, final String confirmPwd, final String captchaValue, final String captchaId);
    }
}
