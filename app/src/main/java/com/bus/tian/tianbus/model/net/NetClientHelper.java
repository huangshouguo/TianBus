package com.bus.tian.tianbus.model.net;

import com.bus.tian.tianbus.util.TimeoutUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by hsg on 10/24/16.
 */

public class NetClientHelper {
    private NetInterceptor netInterceptor;
    private HttpLoggingInterceptor httpLoggingInterceptor;
    private OkHttpClient okHttpClient;

    @Inject
    public NetClientHelper(NetInterceptor baseParamsInterceptor) {
        this.netInterceptor = baseParamsInterceptor;
        initHttpLoggingInterceptor();
        initOkHttpClient();
    }

    public OkHttpClient getOkHttpClient() {
        return this.okHttpClient;
    }

    private void initHttpLoggingInterceptor() {
        this.httpLoggingInterceptor = new HttpLoggingInterceptor();
        this.httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private void initOkHttpClient() {
        this.okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(this.netInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .addNetworkInterceptor(this.httpLoggingInterceptor)
                .retryOnConnectionFailure(false)
                .connectTimeout(TimeoutUtil.NET_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TimeoutUtil.NET_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TimeoutUtil.NET_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
