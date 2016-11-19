package com.bus.tian.tianbus.view.forum;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.ForumCommentBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by hsg on 11/5/16.
 */

public class ForumDetailAdapter extends BaseQuickAdapter<ForumCommentBean, BaseViewHolder> {

    public ForumDetailAdapter(int layoutResId, List<ForumCommentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ForumCommentBean forumCommentBean) {
        baseViewHolder.setText(R.id.text_forum_reply_item_id, forumCommentBean.getIdImpl());
        baseViewHolder.setText(R.id.text_forum_reply_item_creator, forumCommentBean.getCustomer());
        baseViewHolder.setText(R.id.text_forum_reply_item_create_time, forumCommentBean.getCreateTimeImpl());
        baseViewHolder.setText(R.id.text_forum_reply_item_bizId, forumCommentBean.getBizIdImpl());
        baseViewHolder.setText(R.id.text_forum_reply_item_content, forumCommentBean.getComment());
    }
}
