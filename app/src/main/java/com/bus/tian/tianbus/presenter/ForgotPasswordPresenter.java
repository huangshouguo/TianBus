package com.bus.tian.tianbus.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.bus.tian.tianbus.contract.IForgotPasswordContract;
import com.bus.tian.tianbus.model.api.ApiResponseCode;
import com.bus.tian.tianbus.model.bean.ApiResponseBean;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;
import com.bus.tian.tianbus.util.ErrorMsgUtil;
import com.bus.tian.tianbus.util.ValidateUtil;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by hsg on 10/28/16.
 */

public class ForgotPasswordPresenter extends BasePresenter implements IForgotPasswordContract.IPresenter {
    private IForgotPasswordContract.IView view;

    public ForgotPasswordPresenter(IForgotPasswordContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadSmsCaptcha(String phoneNumber) {
        if (this.view == null) {
            Log.e(TAG, "loadSmsCaptcha: view is null");
            return;
        }

        if (!ValidateUtil.validatePhoneNumber(this.view, phoneNumber)) {
            Log.e(TAG, "loadSmsCaptcha: input params error, phoneNumber = " + phoneNumber);
            return;
        }

        Subscription subscription = getApi().findUserByPhoneNumber(phoneNumber)
                .flatMap(new Func1<ApiResponseBean<UserBean>, Observable<ApiResponseBean<UserBean>>>() {
                    @Override
                    public Observable<ApiResponseBean<UserBean>> call(ApiResponseBean<UserBean> userBeanApiResponseBean) {
                        if (userBeanApiResponseBean.getCode() != ApiResponseCode.API_RSP_CODE_SUCCEED) {
                            return getApi().sendSmsCaptcha(phoneNumber);
                        } else {
                            return Observable.error(new Throwable(ErrorMsgUtil.ERR_MSG_USER_NOT_FOUND));
                        }
                    }
                })
                .compose(preHandleApiResponse())
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<UserBean>(this.view) {
                    @Override
                    public void onNext(UserBean userBean) {
                        super.onNext(userBean);
                        view.updateSmsCaptchaView(userBean);
                    }
                });

        addSubscription(subscription);
    }

    @Override
    public void doResetPassword(String phoneNumber, String pwd, String confirmPwd, String captchaValue, String captchaId) {
        if (this.view == null) {
            Log.e(TAG, "doResetPassword: view is null");
            return;
        }

        if (!ValidateUtil.validatePhoneNumber(this.view, phoneNumber, false)) {
            Log.e(TAG, "doResetPassword: input params error, phoneNumber = " + phoneNumber);
            return;
        }
        if (!ValidateUtil.validateConfirmPassword(this.view, pwd, confirmPwd)) {
            Log.e(TAG, "doResetPassword: input params error, pwd = " + pwd + ", confirmPwd = " + confirmPwd);
            return;
        }

        if (!ValidateUtil.validateSmsCaptchaValue(this.view, captchaValue)) {
            Log.e(TAG, "doResetPassword: input params error, captchaValue = " + captchaValue);
            return;
        }

        if (TextUtils.isEmpty(captchaId)) {
            Log.e(TAG, "doResetPassword: input params error, captchaId = " + captchaId);
            return;
        }

        Subscription subscription = getApi().resetPassword(phoneNumber, pwd, captchaValue, captchaId)
                .compose(preHandleApiResponse())
                .flatMap(new Func1<UserBean, Observable<ApiResponseBean<UserBean>>>() {
                    @Override
                    public Observable<ApiResponseBean<UserBean>> call(UserBean userBean) {
                        return getApi().login(phoneNumber, pwd);
                    }
                })
                .compose(preHandleApiResponse())
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<UserBean>(this.view) {
                    @Override
                    public void onNext(UserBean userBean) {
                        super.onNext(userBean);
                        view.updateResetView();
                        view.showRemainderMessage("您的密码设置成功，请重新登录");
                    }
                });

        addSubscription(subscription);
    }
}
