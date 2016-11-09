package com.bus.tian.tianbus.model.net;

import com.bus.tian.tianbus.util.UserManager;

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

        if (UserManager.getInstance().isLogined()) {
            originalRequestBuilder.addHeader("User-Token", UserManager.getInstance().getUserToken());
        }

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
