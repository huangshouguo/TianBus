package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.model.api.IApi;
import com.bus.tian.tianbus.model.net.NetInterceptor;
import com.bus.tian.tianbus.model.net.RetrofitHelper;
import com.bus.tian.tianbus.model.net.url.BaseUrl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 10/24/16.
 */
@Module
public class NetModule {

    @Provides
    public IApi provideApiService(RetrofitHelper retrofitHelper) {
        return retrofitHelper.getRetrofit().create(IApi.class);
    }

    @Provides
    public BaseUrl provideBaseUrl() {
        return new BaseUrl();
    }

    @Provides
    public NetInterceptor provideNetInterceptor() {
        return new NetInterceptor();
    }
}
