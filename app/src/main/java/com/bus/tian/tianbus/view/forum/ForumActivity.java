package com.bus.tian.tianbus.view.forum;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.view.BaseActivity;
import com.bus.tian.tianbus.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForumActivity extends BaseActivity {

    @BindView(R.id.tool_bar_forum)
    Toolbar toolBar;
    @BindView(R.id.layout_content_forum)
    FrameLayout layoutContent;
    @BindView(R.id.fab_forum)
    FloatingActionButton fabForum;

    private BaseFragment curSelectedFragment;
    private ForumSummaryFragment forumSummaryFragment;
    private ForumDetailFragment forumDetailFragment;
    private ForumCreateFragment forumCreateFragment;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ForumActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_forum;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(this.toolBar);
        showForumSummaryFragment();
    }

    @Override
    protected void onRelease() {

    }

    @OnClick(R.id.fab_forum)
    public void onClick() {
        if (this.curSelectedFragment == this.forumCreateFragment) {
            this.forumCreateFragment.sendCreatedTheme();
        } else {
            showForumCreateFragment();
        }
    }

    public void showForumSummaryFragment() {
        getSupportActionBar().setTitle(R.string.text_message_board);

        if (this.forumSummaryFragment == null) {
            this.forumSummaryFragment = ForumSummaryFragment.getInstance();
        }

        this.fabForum.setImageResource(R.drawable.ic_add);
        this.fabForum.setVisibility(View.VISIBLE);

        updateFragment(this.forumSummaryFragment);
    }

    public void showForumDetailFragment(String themeId) {
        getSupportActionBar().setTitle(R.string.text_message_board_detail);
        this.forumDetailFragment = ForumDetailFragment.getInstance(themeId);
        this.fabForum.setVisibility(View.GONE);
        updateFragment(this.forumDetailFragment);
    }

    public void showForumCreateFragment() {
        getSupportActionBar().setTitle(R.string.text_message_board_create);

        if (this.forumCreateFragment == null) {
            this.forumCreateFragment = ForumCreateFragment.getInstance();
        }

        this.fabForum.setImageResource(R.drawable.ic_ok);
        this.fabForum.setVisibility(View.VISIBLE);

        updateFragment(this.forumCreateFragment);
    }

    private void updateFragment(final BaseFragment newFragment) {
        //1. check null
        if (newFragment == null) {
            return;
        }

        //2. check the same fragment
        if (newFragment == this.curSelectedFragment) {
            return;
        }

        //3. update
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (this.curSelectedFragment != null) {
            fragmentTransaction.remove(this.curSelectedFragment);
        }
        fragmentTransaction.replace(R.id.layout_content_forum, newFragment);
        fragmentTransaction.commit();

        //4. record selection
        this.curSelectedFragment = newFragment;
    }

    @Override
    public void onBackPressed() {
        if (this.curSelectedFragment == this.forumSummaryFragment) {
            super.onBackPressed();
        } else {
            showForumSummaryFragment();
        }
    }
}
