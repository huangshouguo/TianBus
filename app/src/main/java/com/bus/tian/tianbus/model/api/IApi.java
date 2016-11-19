package com.bus.tian.tianbus.model.api;

import com.bus.tian.tianbus.model.bean.ApiResponseBean;
import com.bus.tian.tianbus.model.bean.CopAnnouncementBean;
import com.bus.tian.tianbus.model.bean.ForumCommentBean;
import com.bus.tian.tianbus.model.bean.ForumDetailBean;
import com.bus.tian.tianbus.model.bean.ForumSummaryBean;
import com.bus.tian.tianbus.model.bean.ImageVideoBean;
import com.bus.tian.tianbus.model.bean.UserBean;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hsg on 10/24/16.
 */

public interface IApi {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 注册相关Api
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
    @POST("forgotpassword")
    Observable<ApiResponseBean<UserBean>> forgotPassword(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("captcha") String captchaValue,
            @Field("captchaId") String captchaId);

    @FormUrlEncoded
    @POST("login")
    Observable<ApiResponseBean<UserBean>> login(
            @Field("phone") String phone,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("resetpassword")
    Observable<ApiResponseBean<UserBean>> resetPassword(
            @Field("uid") String uid,
            @Field("oldPassword") String oldPassword,
            @Field("newPassword") String newPassword);

    @FormUrlEncoded
    @POST("logout")
    Observable<ApiResponseBean<UserBean>> logout(
            @Field("uid") String uid);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 警务公告相关Api
    @GET("announcement")
    Observable<ApiResponseBean<List<CopAnnouncementBean>>> getCopAnnouncementList();

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 警情相关Api
    @Multipart
    @POST("image")
    Observable<ApiResponseBean<ImageVideoBean>> uploadPhotoCaptured(
            @Part("uid") RequestBody uid,
            @Part("location") RequestBody location,
            @Part("createTime") RequestBody createTime,
            @Part("comment") RequestBody comment,
            @Part MultipartBody.Part image);

    @Multipart
    @POST("video")
    Observable<ApiResponseBean<ImageVideoBean>> uploadVideoCaptured(
            @Part("uid") RequestBody uid,
            @Part("location") RequestBody location,
            @Part("createTime") RequestBody createTime,
            @Part("comment") RequestBody comment,
            @Part MultipartBody.Part video);

    @GET("image/summary")
    Observable<ApiResponseBean<List<ImageVideoBean>>> getPhotoRecordSummary(
            @Query("uid") String uid);

    @GET("video/summary")
    Observable<ApiResponseBean<List<ImageVideoBean>>> getVideoRecordSummary(
            @Query("uid") String uid);


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 警务交流相关Api
    @GET("forum/theme/summary")
    Observable<ApiResponseBean<List<ForumSummaryBean>>> getForumSummaryList();

    @GET("forum/theme/detail")
    Observable<ApiResponseBean<ForumDetailBean>> getForumDetailById(
            @Query("id") String id);

    @FormUrlEncoded
    @POST("forum/theme/reply")
    Observable<ApiResponseBean<ForumCommentBean>> sendReplyContent(
            @Field("uid") String uid,
            @Field("themeId") String themeId,
            @Field("bizId") String bizId,
            @Field("createTime") long createTime,
            @Field("comment") String comment);

    @FormUrlEncoded
    @POST("forum/theme/create")
    Observable<ApiResponseBean<ForumSummaryBean>> createForumTheme(
            @Field("uid") String uid,
            @Field("title") String title,
            @Field("createTime") long createTime);

}
