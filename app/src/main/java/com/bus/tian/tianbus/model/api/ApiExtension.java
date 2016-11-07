package com.bus.tian.tianbus.model.api;

import com.bus.tian.tianbus.model.bean.ApiResponseBean;
import com.bus.tian.tianbus.model.bean.ForumCommentBean;
import com.bus.tian.tianbus.model.bean.ForumSummaryBean;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.util.UserManager;

import java.util.Date;

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

    public static Observable<ApiResponseBean<UserBean>> logout(IApi api){
        return api.logout(getUid());
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
