package com.bus.tian.tianbus.contract;

import com.bus.tian.tianbus.model.bean.ImageVideoBean;

/**
 * Created by hsg on 11/12/16.
 */

public interface IUploadFileContract {
    interface IView extends IBaseContract.IBaseView {
        void uploadFileCompleted();
    }

    interface IPresenter extends IBaseContract.IBasePresenter {
        void uploadImageFile(ImageVideoBean imageVideoBean);

        void uploadVideoFile(ImageVideoBean imageVideoBean);
    }
}
