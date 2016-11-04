package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.HelpBizBean;

import java.util.List;

/**
 * Created by hsg on 11/4/16.
 */

public interface IHelpBizContract {
    interface IView extends IBaseContract.IBaseView {
        void updateView(List<HelpBizBean> helpBizBeanList);
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void loadData();
    }
}
