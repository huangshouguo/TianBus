package com.bus.tian.tianbus.presenter;

import com.bus.tian.tianbus.contract.IMeDetailContract;
import com.bus.tian.tianbus.model.api.ApiExtension;
import com.bus.tian.tianbus.model.bean.ImageVideoBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;

import java.util.List;

import rx.Subscription;

/**
 * Created by hsg on 11/18/16.
 */

public class MeDetailPresenter extends BasePresenter implements IMeDetailContract.IPresenter {

    private IMeDetailContract.IView view;

    public MeDetailPresenter(IMeDetailContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadImageRecordData() {
        if (this.view != null) {
            Subscription subscription = ApiExtension.getPhotoRecordSummary(getApi())
                    .compose(preHandleApiResponse())
                    .compose(doSchedulersAndBindLifecycle())
                    .subscribe(new ApiRspSubscriber<List<ImageVideoBean>>(this.view) {
                        @Override
                        public void onNext(List<ImageVideoBean> imageVideoBeen) {
                            super.onNext(imageVideoBeen);
                            if (view != null) {
                                view.updateView(imageVideoBeen);
                            }
                        }
                    });
            addSubscription(subscription);
        }
    }

    @Override
    public void loadVideoRecordData() {
        if (this.view != null) {
            Subscription subscription = ApiExtension.getVideoRecordSummary(getApi())
                    .compose(preHandleApiResponse())
                    .compose(doSchedulersAndBindLifecycle())
                    .subscribe(new ApiRspSubscriber<List<ImageVideoBean>>(this.view) {
                        @Override
                        public void onNext(List<ImageVideoBean> imageVideoBeen) {
                            super.onNext(imageVideoBeen);
                            if (view != null) {
                                view.updateView(imageVideoBeen);
                            }
                        }
                    });
            addSubscription(subscription);
        }
    }
}
