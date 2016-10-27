package com.bus.tian.tianbus.util;

import android.text.TextUtils;
import android.util.Log;

import com.bus.tian.tianbus.contract.IBaseContract;

/**
 * Created by hsg on 2016/10/26.
 */

public class ValidateUtil {
    private static final String TAG = "ValidateUtil";

    public static boolean validatePhoneNumber(final IBaseContract.IBaseView view, final String phoneNumber, final boolean showMsg) {
        if (view == null) {
            Log.e(TAG, "validatePhoneNumber: input params error, view = " + view);
            return false;
        }

        if (TextUtils.isEmpty(phoneNumber)) {
            if (showMsg) {
                view.showErrorMessage(ErrorMsgUtil.ERR_MSG_PHONE_EMPTY);
            }
            return false;
        }

        if (!TextUtils.isDigitsOnly(phoneNumber) || (phoneNumber.length() != 11)) {
            if (showMsg) {
                view.showErrorMessage(ErrorMsgUtil.ERR_MSG_PHONE_INVALID);
            }
            return false;
        }

        return true;
    }

    public static boolean validatePhoneNumber(final IBaseContract.IBaseView view, String phoneNumber) {
        return validatePhoneNumber(view, phoneNumber, true);
    }

    public static boolean validatePassword(IBaseContract.IBaseView view, final String password) {
        if (view == null) {
            Log.e(TAG, "validatePhoneNumber: input params error, view = " + view);
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            view.showErrorMessage(ErrorMsgUtil.ERR_MSG_PWD_EMPTY);
            return false;
        }

        if ((password.length() < 6) || (password.length() > 10)) {
            view.showErrorMessage(ErrorMsgUtil.ERR_MSG_PWD_INVALID);
            return false;
        }

        return true;
    }

    public static boolean validateConfirmPassword(IBaseContract.IBaseView view, final String password, final String confirmPassword) {
        if (!validatePassword(view, password)) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            view.showErrorMessage(ErrorMsgUtil.ERR_MSG_PASSWORD_DIFF);
            return false;
        }

        return true;
    }

    public static boolean validateSmsCaptchaValue(final IBaseContract.IBaseView view, final String captchaValue) {
        if (view == null) {
            Log.e(TAG, "validatePhoneNumber: input params error, view = " + view);
            return false;
        }

        if (TextUtils.isEmpty(captchaValue)) {
            view.showErrorMessage(ErrorMsgUtil.ERR_MSG_SMS_CAPTCHA_EMPTY);
            return false;
        }

        return true;
    }
}
