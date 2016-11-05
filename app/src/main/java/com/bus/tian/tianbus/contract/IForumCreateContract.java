package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.ForumSummaryBean;

/**
 * Created by hsg on 11/5/16.
 */

public interface IForumCreateContract {
    interface IView extends IBaseContract.IBaseView{
        void updateView(ForumSummaryBean forumSummaryBean);
    }

    interface IPresenter extends IBaseContract.IBasePresenter{
        void createForumTheme(String title);
    }
}
