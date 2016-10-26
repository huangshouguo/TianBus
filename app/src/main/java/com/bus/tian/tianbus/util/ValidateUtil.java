package com.bus.tian.tianbus.util;

import android.text.TextUtils;
import android.util.Log;

import com.bus.tian.tianbus.contract.IBaseContract;

/**
 * Created by hsg on 2016/10/26.
 */

public class ValidateUtil {
    private static final String TAG = "ValidateUtil";

    public static boolean validatePhoneNumber(final IBaseContract.IBaseView view, String phoneNumber) {
        if (view == null) {
            Log.e(TAG, "validatePhoneNumber: input params error, view = " + view);
            return false;
        }

        if (TextUtils.isEmpty(phoneNumber)) {
            view.showErrorMessage(ErrorMsgUtil.ERR_MSG_PHONE_EMPTY);
            return false;
        }

        if (!TextUtils.isDigitsOnly(phoneNumber) || phoneNumber.length() != 11) {
            view.showErrorMessage(ErrorMsgUtil.ERR_MSG_PHONE_INVALID);
            return false;
        }

        return true;
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

        if (password.length() < 6 || password.length() > 10) {
            view.showErrorMessage(ErrorMsgUtil.ERR_MSG_PWD_INVALID);
            return false;
        }

        return true;
    }
}
