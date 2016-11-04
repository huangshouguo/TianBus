package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.TextLinkBean;

import java.util.List;

/**
 * Created by hsg on 11/4/16.
 */

public interface IHelpLawContract {
    interface IView extends IBaseContract.IBaseView{
        void updateView(List<TextLinkBean> textLinkBeanList);
    }

    interface IPresenter extends IBaseContract.IBasePresenter{
        void loadData();
    }
}
