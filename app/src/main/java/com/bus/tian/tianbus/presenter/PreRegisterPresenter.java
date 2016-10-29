package com.bus.tian.tianbus.presenter;

import android.util.Log;

import com.bus.tian.tianbus.contract.IPreRegisterContract;
import com.bus.tian.tianbus.model.api.ApiResponseCode;
import com.bus.tian.tianbus.model.bean.ApiResponseBean;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;
import com.bus.tian.tianbus.util.ErrorMsgUtil;
import com.bus.tian.tianbus.util.ValidateUtil;

import rx.Subscription;

/**
 * Created by hsg on 10/27/16.
 */

public class PreRegisterPresenter extends BasePresenter implements IPreRegisterContract.IPresenter {

    private IPreRegisterContract.IView view;

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

        if (!ValidateUtil.validatePhoneNumber(this.view, phoneNumber)) {
            Log.e(TAG, "findUserByPhoneNumber: input params error, phoneNumber = " + phoneNumber);
            return;
        }

        Subscription subscription = getApi().findUserByPhoneNumber(phoneNumber)
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<ApiResponseBean<UserBean>>(this.view){
                    @Override
                    public void onNext(ApiResponseBean<UserBean> userBeanApiResponseBean) {
                        if (userBeanApiResponseBean.getCode() == ApiResponseCode.API_RSP_CODE_SUCCEED){
                            view.updateView(userBeanApiResponseBean.getData());
                        } else {
                            view.showErrorMessage(ErrorMsgUtil.ERR_MSG_USER_REGISTERED);
                        }
                    }
                });

        addSubscription(subscription);
    }
}
