package com.bus.tian.tianbus.util;

import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.model.event.EventType;
import com.bus.tian.tianbus.model.event.UserLoginEvent;
import com.google.gson.Gson;

/**
 * Created by hsg on 2016/10/29.
 */

public class UserManager {
    private static final String USER_PROFILE = "user.profile";
    private static UserManager instance;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    public void login() {
        sendShouldLoginEvent();
    }

    public boolean isLogined() {
        return (getUserProfile() != null);
    }

    public UserBean getUserOfLogined() {
        return getUserProfile();
    }

    public void handleLoginSuccess(UserBean userBean) {
        if (userBean != null) {
            //1. save user profile
            saveUserProfileData(userBean);

            //2. send event
            sendLoginSuccessEvent();
        }
    }

    public void handleLogoutSuccess() {
        //1. clear user profile
        clearUserProfileData();

        //2. send event
        sendLogoutSuccessEvent();
    }

    public void handleTokenInvalid() {
        sendTokenInvalidEvent();
    }

    public String getUserToken() {
        String result = null;
        if (isLogined()) {
            result = getUserOfLogined().getToken();
        }
        return result;
    }

    private void sendShouldLoginEvent() {
        if (RxBusUtil.getDefaultInstance().hasObservers()) {
            RxBusUtil.getDefaultInstance()
                    .send(new UserLoginEvent(EventType.EVENT_TYPE_SHOULD_LOGIN));
        }
    }

    private void sendLoginSuccessEvent() {
        if (RxBusUtil.getDefaultInstance().hasObservers()) {
            RxBusUtil.getDefaultInstance()
                    .send(new UserLoginEvent(EventType.EVENT_TYPE_LOGIN_SUCCESS));
        }
    }

    private void sendTokenInvalidEvent() {
        if (RxBusUtil.getDefaultInstance().hasObservers()) {
            RxBusUtil.getDefaultInstance()
                    .send(new UserLoginEvent(EventType.EVENT_TYPE_TOKEN_INVALID));
        }
    }

    private void sendLogoutSuccessEvent() {
        if (RxBusUtil.getDefaultInstance().hasObservers()) {
            RxBusUtil.getDefaultInstance()
                    .send(new UserLoginEvent(EventType.EVENT_TYPE_LOGOUT_SUCCESS));
        }
    }

    private void saveUserProfileData(UserBean userBean) {
        if (userBean != null) {
            try {
                String strUserBean = new Gson().toJson(userBean);
                SharedPreferencesUtil.getInstance().saveStringData(USER_PROFILE, strUserBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void clearUserProfileData() {
        SharedPreferencesUtil.getInstance().saveStringData(USER_PROFILE, null);
    }

    private UserBean getUserProfile() {
        UserBean userBean = null;
        try {
            String strUserBean = SharedPreferencesUtil.getInstance().getStringData(USER_PROFILE);
            userBean = new Gson().fromJson(strUserBean, UserBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userBean;
    }
}
