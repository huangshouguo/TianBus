package com.bus.tian.tianbus.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.bus.tian.tianbus.contract.IPreRegisterContract;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;
import com.bus.tian.tianbus.util.ErrorMsgUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by hsg on 10/27/16.
 */

public class PreRegisterPresenter extends BasePresenter implements IPreRegisterContract.IPresenter {

    private IPreRegisterContract.IView view;

    @Inject
    public PreRegisterPresenter(IPreRegisterContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void findUserByPhoneNumber(String phoneNumber) {
        if (this.view == null) {
            Log.e(TAG, "findUserByPhoneNumber: view is null");
            return;
        }

        if (TextUtils.isEmpty(phoneNumber)) {
            this.view.showErrorMessage(ErrorMsgUtil.ERR_MSG_PHONE_EMPTY);
            Log.e(TAG, "findUserByPhoneNumber: input params error, phoneNumber = " + phoneNumber);
            return;
        }

        Subscription subscription = getApi().findUserByPhoneNumber(phoneNumber)
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
