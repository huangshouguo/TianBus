package com.bus.tian.tianbus.view.forum;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IForumSummaryContract;
import com.bus.tian.tianbus.di.component.DaggerIForumSummaryComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.ForumSummaryModule;
import com.bus.tian.tianbus.model.bean.ForumSummaryBean;
import com.bus.tian.tianbus.view.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hsg on 11/5/16.
 */

public class ForumSummaryFragment extends BaseFragment implements IForumSummaryContract.IView {
    @BindView(R.id.recycler_view_forum_summary)
    RecyclerView recyclerView;
    @Inject
    IForumSummaryContract.IPresenter presenter;

    private ForumSummaryAdapter adapter;
    private List<ForumSummaryBean> forumSummaryBeanList;
    private ForumActivity forumActivity;

    public ForumSummaryFragment() {
    }

    public static ForumSummaryFragment getInstance() {
        return new ForumSummaryFragment();
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_forum_summary;
    }

    @Override
    protected void initData() {
        DaggerIForumSummaryComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .forumSummaryModule(new ForumSummaryModule(this))
                .build()
                .inject(this);

        this.forumActivity = (ForumActivity) baseActivity;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);
        this.forumSummaryBeanList = new ArrayList<>();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(baseActivity));
        this.adapter = new ForumSummaryAdapter(R.layout.list_item_forum_summary, this.forumSummaryBeanList);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if ((forumActivity != null) && (i >= 0) && (forumSummaryBeanList != null) && (i < forumSummaryBeanList.size())) {
                    forumActivity.showForumDetailFragment(forumSummaryBeanList.get(i).getThemeId());
                }
            }
        });

        if (this.presenter != null) {
            this.presenter.loadSummaryList();
        }
    }

    @Override
    protected void onRelease() {
        if (presenter != null) {
            presenter.onRelease();
            presenter = null;
        }
    }

    @Override
    public void updateView(List<ForumSummaryBean> forumSummaryBeanList) {
        if (this.forumSummaryBeanList != null) {
            this.forumSummaryBeanList.clear();
            this.forumSummaryBeanList.addAll(forumSummaryBeanList);
            if (this.adapter != null) {
                this.adapter.notifyDataSetChanged();
            }
        }
    }
}
