package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.ImageTextBean;

import java.util.List;

/**
 * Created by hsg on 2016/11/6.
 */

public interface IMeContract {
    interface IView extends IBaseContract.IBaseView {
        void updateView(List<ImageTextBean> data);
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void logout();

        void loadData();
    }
}
