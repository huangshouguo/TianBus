package com.bus.tian.tianbus.model.net;

import android.support.annotation.NonNull;

import com.bus.tian.tianbus.model.net.url.BaseUrl;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hsg on 10/24/16.
 */

public class RetrofitHelper {
    private Retrofit retrofit;

    @Inject
    public RetrofitHelper(@NonNull BaseUrl baseUrl, @NonNull NetClientHelper netClientHelper) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl.getRestBaseUrl())
                .client(netClientHelper.getOkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }
}
