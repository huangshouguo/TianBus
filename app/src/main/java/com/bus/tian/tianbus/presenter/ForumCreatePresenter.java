package com.bus.tian.tianbus.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.bus.tian.tianbus.contract.IForumCreateContract;
import com.bus.tian.tianbus.model.api.ApiExtension;
import com.bus.tian.tianbus.model.bean.ForumSummaryBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;

import rx.Subscription;

/**
 * Created by hsg on 11/5/16.
 */

public class ForumCreatePresenter extends BasePresenter implements IForumCreateContract.IPresenter {
    private IForumCreateContract.IView view;

    public ForumCreatePresenter(IForumCreateContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void createForumTheme(String title) {
        if (this.view == null || TextUtils.isEmpty(title)) {
            Log.d(TAG, "createForumTheme() called with: title = [" + title + "]" + ", view = [" + view + "]");
            return;
        }

        Subscription subscription = ApiExtension.createForumTheme(getApi(), title)
                .compose(preHandleApiResponse())
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<ForumSummaryBean>(this.view) {
                    @Override
                    public void onNext(ForumSummaryBean forumSummaryBean) {
                        super.onNext(forumSummaryBean);
                        if (view != null) {
                            view.updateView(forumSummaryBean);
                        }
                    }
                });

        addSubscription(subscription);
    }
}
