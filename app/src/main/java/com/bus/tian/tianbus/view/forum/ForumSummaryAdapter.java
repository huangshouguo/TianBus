package com.bus.tian.tianbus.view.forum;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.ForumSummaryBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by hsg on 11/5/16.
 */

public class ForumSummaryAdapter extends BaseQuickAdapter<ForumSummaryBean, BaseViewHolder> {

    public ForumSummaryAdapter(int layoutResId, List<ForumSummaryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ForumSummaryBean forumSummaryBean) {
        baseViewHolder.setText(R.id.text_forum_item_title, forumSummaryBean.getTitle());
        baseViewHolder.setText(R.id.text_forum_item_creator, forumSummaryBean.getCreatorImpl());
        baseViewHolder.setText(R.id.text_forum_item_create_time, forumSummaryBean.getCreateTimeImpl());
        baseViewHolder.setText(R.id.text_forum_item_comment_count, forumSummaryBean.getCommentCountImpl());
    }
}
