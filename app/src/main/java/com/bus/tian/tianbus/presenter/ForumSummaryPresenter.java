package com.bus.tian.tianbus.presenter;

import com.bus.tian.tianbus.contract.IForumSummaryContract;
import com.bus.tian.tianbus.model.bean.ForumSummaryBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;

import java.util.List;

import rx.Subscription;

/**
 * Created by hsg on 11/5/16.
 */

public class ForumSummaryPresenter extends BasePresenter implements IForumSummaryContract.IPresenter{

    private IForumSummaryContract.IView view;

    public ForumSummaryPresenter(IForumSummaryContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadSummaryList() {
        if (this.view == null){
            return;
        }

        Subscription subscription = getApi().getForumSummaryList()
                .compose(preHandleApiResponse())
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<List<ForumSummaryBean>>(this.view){
                    @Override
                    public void onNext(List<ForumSummaryBean> forumSummaryBeanList) {
                        super.onNext(forumSummaryBeanList);
                        if (view != null){
                            view.updateView(forumSummaryBeanList);
                        }
                    }
                });
        addSubscription(subscription);
    }
}
