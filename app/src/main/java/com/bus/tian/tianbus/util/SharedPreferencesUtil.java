package com.bus.tian.tianbus.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.bus.tian.tianbus.BusApplication;

/**
 * Created by hsg on 2016/10/29.
 */

public class SharedPreferencesUtil {
    private static final String TAG = "SharedPreferencesUtil";
    private static SharedPreferencesUtil instance;

    private SharedPreferencesUtil() {
    }

    public static SharedPreferencesUtil getInstance() {
        if (instance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (instance == null) {
                    instance = new SharedPreferencesUtil();
                }
            }
        }
        return instance;
    }

    public void saveStringData(final String name, final String data) {
        Log.d(TAG, "saveString() called with: name = [" + name + "], data = [" + data + "]");
        if (TextUtils.isEmpty(name)) {
            Log.e(TAG, "saveStringData: name is null");
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (editor != null) {
                editor.putString(name, data);
                editor.commit();
            }
        }
    }

    public String getStringData(final String name) {
        String result = null;

        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences != null) {
            result = sharedPreferences.getString(name, null);
        }

        Log.d(TAG, "getStringData() called with: name = [" + name + "]" + " returned: " + result);
        return result;
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(BusApplication.getAppContext());
    }
}
