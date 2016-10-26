package com.bus.tian.tianbus.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.bus.tian.tianbus.contract.IRegisterContract;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;
import com.bus.tian.tianbus.util.ErrorMsgUtil;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by hsg on 10/26/16.
 */

public class RegisterPresenter extends BasePresenter implements IRegisterContract.IPresenter {

    private IRegisterContract.IPreView preView;
    private IRegisterContract.IFinishView finishView;

    public RegisterPresenter(IRegisterContract.IPreView view) {
        super(view);
        this.preView = view;
    }

    public RegisterPresenter(IRegisterContract.IFinishView finishView) {
        super(finishView);
        this.finishView = finishView;
    }

    @Override
    public void findUserByPhoneNumber(final String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            this.preView.showErrorMessage(ErrorMsgUtil.ERR_MSG_PHONE_EMPTY);
            Log.e(TAG, "findUserByPhoneNumber: input params error, phoneNumber = " + phoneNumber);
            return;
        }

        getApi().findUserByPhoneNumber(phoneNumber)
                .compose(preHandleApiResponse())
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<UserBean>(this.preView) {
                    @Override
                    public void onNext(UserBean userBean) {
                        super.onNext(userBean);

                    }
                });
    }

    @Override
    public void loadSmsCaptcha(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            Log.e(TAG, "loadSmsCaptcha: input params error, phoneNumber = " + phoneNumber);
            return;
        }

        getApi().sendSmsCaptcha(phoneNumber)
                .compose(preHandleApiResponse())
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<UserBean>(this.finishView) {
                    @Override
                    public void onNext(UserBean userBean) {
                        super.onNext(userBean);
                        finishView.updateSmsCaptchaView(userBean);
                    }
                });
    }

    @Override
    public void doRegister(String phoneNumber, String pwd, String captchaValue, String captchaId) {
        if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(captchaValue) || TextUtils.isEmpty(captchaId)) {
            Log.e(TAG, "doRegister: input params error, phoneNumber = " + phoneNumber + ", pwd = " + pwd + ", captchaValue = " + captchaValue + ", captchaId = " + captchaId);
        }

        getApi().register(phoneNumber, pwd, captchaValue, captchaId)
                .compose(preHandleApiResponse())
                .flatMap(new Func1<UserBean, Observable<UserBean>>() {
                    @Override
                    public Observable<UserBean> call(UserBean userBean) {
                        return getApi().login(phoneNumber, pwd)
                                .compose(preHandleApiResponse());
                    }
                })
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<UserBean>(finishView) {
                    @Override
                    public void onNext(UserBean userBean) {
                        super.onNext(userBean);
                        finishView.updateRegisterView(userBean);
                    }
                });
    }
}
