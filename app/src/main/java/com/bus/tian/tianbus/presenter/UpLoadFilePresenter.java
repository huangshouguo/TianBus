package com.bus.tian.tianbus.presenter;

import com.bus.tian.tianbus.contract.IUploadFileContract;
import com.bus.tian.tianbus.model.api.ApiExtension;
import com.bus.tian.tianbus.model.bean.ImageVideoBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;

import rx.Subscription;

/**
 * Created by hsg on 11/12/16.
 */

public class UpLoadFilePresenter extends BasePresenter implements IUploadFileContract.IPresenter {
    private IUploadFileContract.IView view;

    public UpLoadFilePresenter(IUploadFileContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void uploadImageFile(ImageVideoBean imageVideoBean) {
        if (this.view == null) {
            return;
        }

        if (imageVideoBean != null) {
            Subscription subscription = ApiExtension.uploadPhotoCaptured(getApi(), imageVideoBean.getLocation(), imageVideoBean.getComment(), imageVideoBean.getFilePath())
                    .compose(preHandleApiResponse())
                    .compose(doSchedulersAndBindLifecycle())
                    .subscribe(new ApiRspSubscriber<ImageVideoBean>(this.view) {
                        @Override
                        public void onNext(ImageVideoBean imageVideoBean) {
                            super.onNext(imageVideoBean);
                            if (view != null) {
                                view.uploadFileCompleted();
                            }
                        }
                    });

            addSubscription(subscription);
        }
    }

    @Override
    public void uploadVideoFile(ImageVideoBean imageVideoBean) {
        if (this.view == null) {
            return;
        }

        if (imageVideoBean != null) {
            Subscription subscription = ApiExtension.uploadVideoCaptured(getApi(), imageVideoBean.getLocation(), imageVideoBean.getComment(), imageVideoBean.getFilePath())
                    .compose(preHandleApiResponse())
                    .compose(doSchedulersAndBindLifecycle())
                    .subscribe(new ApiRspSubscriber<ImageVideoBean>(this.view) {
                        @Override
                        public void onNext(ImageVideoBean imageVideoBean) {
                            super.onNext(imageVideoBean);
                            if (view != null) {
                                view.uploadFileCompleted();
                            }
                        }
                    });

            addSubscription(subscription);
        }
    }
}
