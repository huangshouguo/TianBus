package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.ForumSummaryBean;

import java.util.List;

/**
 * Created by hsg on 11/5/16.
 */

public interface IForumSummaryContract {
    interface IView extends IBaseContract.IBaseView {
        void updateView(List<ForumSummaryBean> forumSummaryBeanList);
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void loadSummaryList();
    }
}
