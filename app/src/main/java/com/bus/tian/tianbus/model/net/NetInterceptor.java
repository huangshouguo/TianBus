package com.bus.tian.tianbus.model.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hsg on 10/24/16.
 */

public class NetInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        ////////////////////////////////////////////////////////////////////////////////////////////
        // request
        Request.Builder originalRequestBuilder = chain.request().newBuilder();

        ////////////////////////////////////////////////////////////////////////////////////////////
        // response
        Response response = null;
        try {
            response = chain.proceed(originalRequestBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return response;
    }
}
