package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.ImageVideoBean;

import java.util.List;

/**
 * Created by hsg on 11/18/16.
 */

public interface IMeDetailContract {
    interface IView extends IBaseContract.IBaseView {
        void updateView(List<ImageVideoBean> imageVideoBeanList);
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void loadImageRecordData();

        void loadVideoRecordData();
    }
}
