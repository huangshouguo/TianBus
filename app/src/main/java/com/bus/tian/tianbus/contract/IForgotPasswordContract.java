package com.bus.tian.tianbus.contract;

/**
 * Created by hsg on 10/28/16.
 */

public interface IForgotPasswordContract {

    interface IView extends IBaseContract.IBaseView {
        void updateSmsCaptchaView();

        void updateResetView();
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void loadSmsCaptcha(final String phoneNumber);

        void doResetPassword(final String phoneNumber, final String pwd, final String confirmPwd, final String captchaValue, final String captchaId);
    }
}
