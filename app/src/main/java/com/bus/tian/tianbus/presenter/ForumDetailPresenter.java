package com.bus.tian.tianbus.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.bus.tian.tianbus.contract.IForumDetailContract;
import com.bus.tian.tianbus.model.api.ApiExtension;
import com.bus.tian.tianbus.model.bean.ForumCommentBean;
import com.bus.tian.tianbus.model.bean.ForumDetailBean;
import com.bus.tian.tianbus.util.ApiRspSubscriber;

import rx.Subscription;

/**
 * Created by hsg on 11/5/16.
 */

public class ForumDetailPresenter extends BasePresenter implements IForumDetailContract.IPresenter {
    private IForumDetailContract.IView view;

    public ForumDetailPresenter(IForumDetailContract.IView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadDetailData(String id) {
        if ((this.view == null) && TextUtils.isEmpty(id)) {
            Log.e(TAG, "loadDetailData() called with: id = [" + id + "]" + ", view = [" + view + "]");
            return;
        }

        Subscription subscription = getApi().getForumDetailById(id)
                .compose(preHandleApiResponse())
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<ForumDetailBean>(this.view) {
                    @Override
                    public void onNext(ForumDetailBean forumDetailBean) {
                        super.onNext(forumDetailBean);
                        if (view != null) {
                            view.updateDetailView(forumDetailBean);
                        }
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void sendReplyContent(String themeId, String bizId, String comment) {
        if ((this.view == null) || TextUtils.isEmpty(themeId) || TextUtils.isEmpty(bizId) || TextUtils.isEmpty(comment)) {
            Log.e(TAG, "sendReplyContent() called with: themeId = [" + themeId + "], bizId = [" + bizId + "], comment = [" + comment + "]" + ", view = [" + view + "]");
        }

        Subscription subscription = ApiExtension.sendReplyContent(getApi(), themeId, bizId, comment)
                .compose(preHandleApiResponse())
                .compose(doSchedulersAndBindLifecycle())
                .subscribe(new ApiRspSubscriber<ForumCommentBean>(this.view) {
                    @Override
                    public void onNext(ForumCommentBean forumCommentBean) {
                        super.onNext(forumCommentBean);
                        if (view != null) {
                            view.updateSendReplyView(forumCommentBean);
                        }
                    }
                });
        addSubscription(subscription);
    }
}
