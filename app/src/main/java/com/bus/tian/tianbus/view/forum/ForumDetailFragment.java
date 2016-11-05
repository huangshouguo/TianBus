package com.bus.tian.tianbus.view.forum;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IForumDetailContract;
import com.bus.tian.tianbus.di.component.DaggerIForumDetailComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.ForumDetailModule;
import com.bus.tian.tianbus.model.bean.ForumCommentBean;
import com.bus.tian.tianbus.model.bean.ForumDetailBean;
import com.bus.tian.tianbus.model.bean.ForumSummaryBean;
import com.bus.tian.tianbus.view.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.id;

/**
 * Created by hsg on 11/5/16.
 */

public class ForumDetailFragment extends BaseFragment implements IForumDetailContract.IView {
    private static final String THEME_ID_TAG = "theme_id_tag";

    @BindView(R.id.text_forum_detail_title)
    TextView textTitle;
    @BindView(R.id.text_forum_detail_creator)
    TextView textlCreator;
    @BindView(R.id.text_forum_detail_create_time)
    TextView textCreateTime;
    @BindView(R.id.text_forum_detail_comment_count)
    TextView textCommentCount;
    @BindView(R.id.recycler_view_forum_detail)
    RecyclerView recyclerView;
    @BindView(R.id.edit_text_forum_detail_reply)
    EditText editTextReply;
    @BindView(R.id.btn_forum_detail_send)
    Button btnSend;
    @Inject
    IForumDetailContract.IPresenter presenter;

    private String strThemeId;
    private ForumDetailAdapter adapter;
    private List<ForumCommentBean> forumCommentBeanList;
    private String strReplyId;

    public ForumDetailFragment() {
    }

    public static ForumDetailFragment getInstance(String themeId) {
        ForumDetailFragment ff = new ForumDetailFragment();
        Bundle b = new Bundle();
        b.putString(THEME_ID_TAG, themeId);
        ff.setArguments(b);
        return ff;
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_forum_detail;
    }

    @Override
    protected void initData() {
        this.strThemeId = getArguments().getString(THEME_ID_TAG);
        DaggerIForumDetailComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .forumDetailModule(new ForumDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(baseActivity));
        this.adapter = new ForumDetailAdapter(R.layout.list_item_forum_reply, this.forumCommentBeanList);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if ((i >= 0) && (forumCommentBeanList != null) && (i < forumCommentBeanList.size())) {
                    ForumCommentBean forumCommentBean = forumCommentBeanList.get(id);
                    strReplyId = forumCommentBean.getCustomer();
                    editTextReply.setHint("回复：" + strReplyId);
                }
            }
        });
    }

    @Override
    protected void onRelease() {
        if (this.presenter != null) {
            this.presenter.onRelease();
            this.presenter = null;
        }
    }

    @Override
    public void updateDetailView(ForumDetailBean forumDetailBean) {
        if (forumDetailBean != null) {
            updateTitleView(forumDetailBean.getTheme());
            if (this.adapter != null) {
                this.forumCommentBeanList = forumDetailBean.getComments();
                this.adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void updateSendReplyView(ForumCommentBean forumCommentBean) {
        if (this.presenter != null) {
            this.presenter.loadDetailData(this.strThemeId);
        }
    }

    @OnClick(R.id.btn_forum_detail_send)
    public void onClick() {
        String comment = this.editTextReply.getText().toString();
        if ((this.presenter != null) && (!TextUtils.isEmpty(this.strThemeId)) && !TextUtils.isEmpty(comment) && !TextUtils.isEmpty(this.strReplyId)) {
            this.presenter.sendReplyContent(this.strThemeId, this.strReplyId, comment);
        }
    }


    private void updateTitleView(ForumSummaryBean forumSummaryBean) {
        if (forumSummaryBean != null) {
            this.textTitle.setText(forumSummaryBean.getTitle());
            this.textlCreator.setText(forumSummaryBean.getCreator());
            this.textCreateTime.setText(forumSummaryBean.getCreateTimeImpl());
            this.textCommentCount.setText(forumSummaryBean.getCommentCount());
            this.editTextReply.setHint("回复：" + forumSummaryBean.getCreator());
            this.strReplyId = forumSummaryBean.getCreator();
        }
    }
}
