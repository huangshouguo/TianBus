package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.ForumCommentBean;
import com.bus.tian.tianbus.model.bean.ForumDetailBean;

/**
 * Created by hsg on 11/5/16.
 */

public interface IForumDetailContract {

    interface IView extends IBaseContract.IBaseView{
        void updateDetailView(ForumDetailBean forumDetailBean);
        void updateSendReplyView(ForumCommentBean forumCommentBean);
    }

    interface IPresenter extends IBaseContract.IBasePresenter{
        void loadDetailData(String id);
        void sendReplyContent(String themeId, String bizId, String comment);
    }
}
