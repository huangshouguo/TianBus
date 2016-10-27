package com.bus.tian.tianbus.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.bus.tian.tianbus.contract.IFinishRegisterContract;
import com.bus.tian.tianbus.model.bean.ApiResponseBean;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;
import com.bus.tian.tianbus.util.ValidateUtil;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by hsg on 10/27/16.
 */

public class FinishRegisterPresenter extends BasePresenter implements IFinishRegisterContract.IPresenter {

    private IFinishRegisterContract.IView view;

    @Inject
    public FinishRegisterPresenter(IFinishRegisterContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadSmsCaptcha(String phoneNumber) {
        if (this.view == null) {
            Log.e(TAG, "loadSmsCaptcha: view is null ");
            return;
        }

        if (!ValidateUtil.validatePhoneNumber(this.view, phoneNumber, false)) {
            Log.e(TAG, "loadSmsCaptcha: input params error, phoneNumber = " + phoneNumber);
            return;
        }

        Subscription subscription = getApi().sendSmsCaptcha(phoneNumber)
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
    public void doRegister(String phoneNumber, String pwd, String captchaValue, String captchaId) {
        if (this.view == null) {
            Log.e(TAG, "doRegister: view is null");
            return;
        }

        if (!ValidateUtil.validatePhoneNumber(this.view, phoneNumber, false)) {
            Log.e(TAG, "loadSmsCaptcha: input params error, phoneNumber = " + phoneNumber);
            return;
        }
        if (!ValidateUtil.validatePassword(this.view, pwd)) {
            Log.e(TAG, "loadSmsCaptcha: input params error, pwd = " + pwd);
            return;
        }

        if (!ValidateUtil.validatePassword(this.view, captchaValue)) {
            Log.e(TAG, "loadSmsCaptcha: input params error, captchaValue = " + captchaValue);
            return;
        }

        if (TextUtils.isEmpty(captchaId)) {
            Log.e(TAG, "doRegister: input params error, captchaId = " + captchaId);
            return;
        }

        Subscription subscription = getApi().register(phoneNumber, pwd, captchaValue, captchaId)
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
                        view.updateRegisterView(userBean);
                    }
                });

        addSubscription(subscription);
    }
}
