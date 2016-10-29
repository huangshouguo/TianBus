package com.bus.tian.tianbus.presenter;

import android.util.Log;

import com.bus.tian.tianbus.contract.IMainContract;
import com.bus.tian.tianbus.util.RxBusUtil;

import rx.Subscription;

/**
 * Created by hsg on 2016/10/29.
 */

public class MainPresenter extends BasePresenter implements IMainContract.IPresenter {

    private IMainContract.IView view;

    public MainPresenter(IMainContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void initRxEvent() {
        Subscription subscription = RxBusUtil.loginEventObservable()
                .compose(bindToLifecycle())
                .subscribe(userLoginEvent -> {
                    if (userLoginEvent != null) {
                        switch (userLoginEvent.getEventType()) {
                            case EVENT_TYPE_LOGIN_SUCCESS:
                                onLogin();
                                break;
                            case EVENT_TYPE_LOGIN_CANCEL:
                                onLoginCancel();
                                break;
                            case EVENT_TYPE_TOKEN_INVALID:
                                onTokenInvalid();
                                break;
                            case EVENT_TYPE_LOGOUT_SUCCESS:
                                onLogout();
                                break;
                            default:
                                break;
                        }
                    }
                });
        addSubscription(subscription);
    }


    private void onLogin() {
        if (this.view == null){
            Log.e(TAG, "onLogin: view is null");
            return;
        }

        this.view.updateViewOnLogin();
    }

    private void onLoginCancel() {
        if (this.view == null){
            Log.e(TAG, "onLogin: view is null");
            return;
        }

        this.view.updateViewOnLogin();
    }

    private void onTokenInvalid() {
        if (this.view == null){
            Log.e(TAG, "onLogin: view is null");
            return;
        }

        this.view.startLoginActivity();
    }

    private void onLogout() {
        if (this.view == null){
            Log.e(TAG, "onLogin: view is null");
            return;
        }

        this.view.updateViewOnLogout();
    }
}
