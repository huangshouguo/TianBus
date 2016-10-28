package com.bus.tian.tianbus.model.api;

import com.bus.tian.tianbus.model.bean.ApiResponseBean;
import com.bus.tian.tianbus.model.bean.UserBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hsg on 10/24/16.
 */

public interface IApi {

    @FormUrlEncoded
    @POST("checkphone")
    Observable<ApiResponseBean<UserBean>> findUserByPhoneNumber(
            @Field("phone") String phoneNumber);

    @FormUrlEncoded
    @POST("getcaptcha")
    Observable<ApiResponseBean<UserBean>> sendSmsCaptcha(
            @Field("phone") String phoneNumber);

    @FormUrlEncoded
    @POST("register")
    Observable<ApiResponseBean<UserBean>> register(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("captcha") String captchaValue,
            @Field("captchaId") String captchaId);

    @FormUrlEncoded
    @POST("login")
    Observable<ApiResponseBean<UserBean>> login(
            @Field("phone") String phone,
            @Field("password") String password);
}
