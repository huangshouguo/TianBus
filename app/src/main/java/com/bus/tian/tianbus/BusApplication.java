package com.bus.tian.tianbus;

import android.app.Application;
import android.content.Context;

/**
 * Created by hsg on 10/24/16.
 */

public class BusApplication extends Application {

    private static Context appContent;

    @Override
    public void onCreate() {
        super.onCreate();
        appContent = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContent;
    }
}
