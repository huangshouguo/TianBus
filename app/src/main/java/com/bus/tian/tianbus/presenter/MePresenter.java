package com.bus.tian.tianbus.presenter;

import com.bus.tian.tianbus.contract.IMeContract;
import com.bus.tian.tianbus.model.api.ApiExtension;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.model.data.DataFactory;
import com.bus.tian.tianbus.util.ApiRspSubscriber;
import com.bus.tian.tianbus.util.UserManager;

import rx.Subscription;

/**
 * Created by hsg on 2016/11/6.
 */

public class MePresenter extends BasePresenter implements IMeContract.IPresenter {

    private IMeContract.IView view;

    public MePresenter(IMeContract.IView view) {
        super(view);
        this.view = view;
    }


    @Override
    public void logout() {
        if (this.view == null) {
            return;
        }

        Subscription subscription = ApiExtension.logout(getApi())
                .compose(preHandleApiResponse())
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<UserBean>(this.view) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        UserManager.getInstance().handleLogoutSuccess();
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        super.onNext(userBean);
                        UserManager.getInstance().handleLogoutSuccess();
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void loadData() {
        if (this.view != null) {
            this.view.updateView(DataFactory.getMeItemDataList());
        }
    }
}
