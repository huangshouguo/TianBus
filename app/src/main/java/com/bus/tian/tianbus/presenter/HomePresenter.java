package com.bus.tian.tianbus.presenter;

import android.util.Log;

import com.bus.tian.tianbus.contract.IHomeContract;
import com.bus.tian.tianbus.model.bean.CopAnnouncementBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscription;

/**
 * Created by hsg on 2016/10/30.
 */

public class HomePresenter extends BasePresenter implements IHomeContract.IPresenter {

    private IHomeContract.IView view;

    public HomePresenter(IHomeContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadCopAnnouncementData() {
        if (this.view == null) {
            Log.e(TAG, "loadCopAnnouncementData: input view is null");
            return;
        }

        Subscription subscription = getApi().getCopAnnouncementList()
                .compose(preHandleApiResponse())
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<List<CopAnnouncementBean>>(this.view){
                    @Override
                    public void onNext(List<CopAnnouncementBean> copAnnouncementBeanList) {
                        super.onNext(copAnnouncementBeanList);
                        if (view != null){
                            view.updateCopAnnouncementListView(copAnnouncementBeanList);
                        }
                    }
                });

        addSubscription(subscription);

//        this.view.updateCopAnnouncementListView(generator());
    }

    private List<CopAnnouncementBean> generator(){
        List<CopAnnouncementBean> copAnnouncementBeanList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            CopAnnouncementBean copAnnouncementBean = new CopAnnouncementBean();
            copAnnouncementBean.setTitle("边防检查");
            copAnnouncementBean.setCreateTime(new Date());
            copAnnouncementBean.setContent("边防检查的内容边防检查的内容边防检查的内容边防检查的内容边防检查的内容边防检查的内容边防检查的内容边防检查的内容边防检查的内容边防检查的内容");
            copAnnouncementBeanList.add(copAnnouncementBean);
        }

        return copAnnouncementBeanList;
    }

}
