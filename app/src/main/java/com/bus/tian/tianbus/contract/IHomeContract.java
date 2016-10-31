package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.CopAnnouncementBean;

import java.util.List;

/**
 * Created by hsg on 2016/10/30.
 */

public interface IHomeContract {

    interface IView extends IBaseContract.IBaseView {
        void updateCopAnnouncementListView(List<CopAnnouncementBean> copAnnouncementBeanList);
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void loadCopAnnouncementData();
    }
}
