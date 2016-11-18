package com.bus.tian.tianbus.model.api;

import com.bus.tian.tianbus.model.bean.ApiResponseBean;
import com.bus.tian.tianbus.model.bean.ForumCommentBean;
import com.bus.tian.tianbus.model.bean.ForumSummaryBean;
import com.bus.tian.tianbus.model.bean.ImageVideoBean;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.util.MultiPartUtil;
import com.bus.tian.tianbus.util.UserManager;

import java.util.Date;
import java.util.List;

import rx.Observable;

/**
 * Created by hsg on 10/24/16.
 */

public class ApiExtension {

    public static Observable<ApiResponseBean<ForumCommentBean>> sendReplyContent(IApi api, String themeId, String bizId, String comment) {
        return api.sendReplyContent(getUid(), themeId, bizId, getNowTime(), comment);
    }

    public static Observable<ApiResponseBean<ForumSummaryBean>> createForumTheme(IApi api, String title) {
        return api.createForumTheme(getUid(), title, getNowTime());
    }

    public static Observable<ApiResponseBean<UserBean>> logout(IApi api) {
        return api.logout(getUid());
    }

    public static Observable<ApiResponseBean<ImageVideoBean>> uploadPhotoCaptured(IApi api, String location, String comment, String imageFile) {
        return api.uploadPhotoCaptured(
                MultiPartUtil.string2RequestBody(getUid()),
                MultiPartUtil.string2RequestBody(location),
                MultiPartUtil.string2RequestBody(String.valueOf(getNowTime())),
                MultiPartUtil.string2RequestBody(comment),
                MultiPartUtil.imageFileToPart(imageFile));
    }

    public static Observable<ApiResponseBean<ImageVideoBean>> uploadVideoCaptured(IApi api, String location, String comment, String videoFile) {
        return api.uploadVideoCaptured(
                MultiPartUtil.string2RequestBody(getUid()),
                MultiPartUtil.string2RequestBody(location),
                MultiPartUtil.string2RequestBody(String.valueOf(getNowTime())),
                MultiPartUtil.string2RequestBody(comment),
                MultiPartUtil.videoFileToPart(videoFile));
    }

    public static Observable<ApiResponseBean<List<ImageVideoBean>>> getPhotoRecordSummary(IApi api) {
        return api.getPhotoRecordSummary(getUid());
    }

    public static Observable<ApiResponseBean<List<ImageVideoBean>>> getVideoRecordSummary(IApi api) {
        return api.getVideoRecordSummary(getUid());
    }

    private static String getUid() {
        if (UserManager.getInstance().isLogined()) {
            return UserManager.getInstance().getUserOfLogined().getUid();
        }

        return null;
    }

    private static long getNowTime() {
        return new Date().getTime();
    }
}
