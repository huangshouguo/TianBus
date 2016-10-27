package com.bus.tian.tianbus.presenter;

import android.util.Log;

import com.bus.tian.tianbus.contract.ILoginContract;
import com.bus.tian.tianbus.model.api.ApiResponseCode;
import com.bus.tian.tianbus.model.bean.ApiResponseBean;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;
import com.bus.tian.tianbus.util.ErrorMsgUtil;
import com.bus.tian.tianbus.util.ValidateUtil;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by hsg on 2016/10/26.
 */

public class LoginPresenter extends BasePresenter implements ILoginContract.IPresenter {

    private ILoginContract.IView view;

    @Inject
    public LoginPresenter(ILoginContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void login(String phoneNumber, String password) {
        if (!ValidateUtil.validatePhoneNumber(this.view, phoneNumber)) {
            Log.e(TAG, "login: input params error, phoneNumber = " + phoneNumber);
            return;

        }

        if (!ValidateUtil.validatePassword(this.view, password)) {
            Log.e(TAG, "login: input params error, password = " + password);
            return;
        }

        Subscription subscription = getApi().findUserByPhoneNumber(phoneNumber)
                .flatMap(new Func1<ApiResponseBean<UserBean>, Observable<ApiResponseBean<UserBean>>>() {
                    @Override
                    public Observable<ApiResponseBean<UserBean>> call(ApiResponseBean<UserBean> userBeanApiResponseBean) {
                        if (userBeanApiResponseBean.getCode() != ApiResponseCode.API_RSP_CODE_SUCCEED) {
                            return getApi().login(phoneNumber, password);
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
                    }
                });

        addSubscription(subscription);
    }
}
