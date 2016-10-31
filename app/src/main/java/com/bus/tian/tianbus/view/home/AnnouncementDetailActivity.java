package com.bus.tian.tianbus.view.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.CopAnnouncementBean;
import com.bus.tian.tianbus.view.BaseActivity;
import com.google.gson.Gson;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AnnouncementDetailActivity extends BaseActivity {
    private static final String ANNOUNCEMENT_TAG = "announcement_tag";

    @BindView(R.id.text_announcement_detail_title)
    TextView textTitle;
    @BindView(R.id.text_announcement_detail_time)
    TextView textTime;
    @BindView(R.id.text_announcement_detail_content)
    TextView textContent;
    @BindView(R.id.tool_bar_announcement_detail)
    Toolbar toolBar;
    @BindString(R.string.text_cop_announcement_detail)
    String actionBarTitle;

    private CopAnnouncementBean copAnnouncementBean;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_announcement_detail;
    }

    @Override
    protected void initData() {
        try {
            String strData = getIntent().getStringExtra(ANNOUNCEMENT_TAG);
            this.copAnnouncementBean = new Gson().fromJson(strData, CopAnnouncementBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolbar();
        if (this.copAnnouncementBean != null) {
            this.textTitle.setText(this.copAnnouncementBean.getTitle());
            this.textTime.setText(this.copAnnouncementBean.getCreateTimeImpl());
            this.textContent.setText(this.copAnnouncementBean.getContent());
        }
    }

    @Override
    protected void onRelease() {

    }

    public static void actionStart(Context context, CopAnnouncementBean copAnnouncementBean) {
        Intent intent = new Intent(context, AnnouncementDetailActivity.class);
        try {
            String strAnnouncementBean = new Gson().toJson(copAnnouncementBean);
            intent.putExtra(ANNOUNCEMENT_TAG, strAnnouncementBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        context.startActivity(intent);
    }

    private void initToolbar() {
        setSupportActionBar(this.toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(this.actionBarTitle);
    }
}
