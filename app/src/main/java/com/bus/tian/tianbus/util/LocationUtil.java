package com.bus.tian.tianbus.util;

import android.text.TextUtils;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bus.tian.tianbus.BusApplication;

/**
 * Created by hsg on 11/12/16.
 */

public class LocationUtil implements BDLocationListener {
    private static final String TAG = "LocationUtil";
    private static LocationUtil instance;
    private LocationClient locationClient;
    private String strAddress;

    public static LocationUtil getInstance() {
        if (instance == null) {
            instance = new LocationUtil();
        }
        return instance;
    }

    private LocationUtil() {
        initLocation();
    }

    private void initLocation() {
        this.locationClient = new LocationClient(BusApplication.getAppContext());
        this.locationClient.registerLocationListener(this);

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        this.locationClient.setLocOption(option);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        Log.d(TAG, "onReceiveLocation() called with: bdLocation = [" + bdLocation + "]");
        if (bdLocation != null) {
            this.strAddress = String.format("%s", bdLocation.getAddress().address);
        }
        Log.d(TAG, "onReceiveLocation: strAddress = " + this.strAddress);
    }

    public void start() {
        if (this.locationClient != null) {
            this.locationClient.start();
        }
    }

    public void stop() {
        if (this.locationClient != null) {
            this.locationClient.stop();
        }
    }

    public String getLocationAddress() {
        if (TextUtils.isEmpty(this.strAddress)) {
            this.strAddress = "获取位置信息失败,请手动输入位置信息!";
        }
        return this.strAddress;
    }
}
