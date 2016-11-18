package com.bus.tian.tianbus.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IMeDetailContract;
import com.bus.tian.tianbus.di.component.DaggerIMeDetailComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.MeDetailModule;
import com.bus.tian.tianbus.model.bean.ImageVideoBean;
import com.bus.tian.tianbus.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeDetailActivity extends BaseActivity implements IMeDetailContract.IView {
    public static final String SOURCE_IMAGE_TAG = "source_image_tag";
    public static final String SOURCE_VIDEO_TAG = "source_video_tag";
    private static final String SOURCE_TAG = "source_tag";

    @BindView(R.id.tool_bar_me_detail)
    Toolbar toolBarMeDetail;
    @BindView(R.id.recycler_view_me_detail)
    RecyclerView recyclerViewMeDetail;
    @Inject
    IMeDetailContract.IPresenter presenter;

    private String strSourceTag;
    private List<ImageVideoBean> dataList;
    private MeDetailAdapter adapter;

    public static void actionStart(Context context, String sourceTag) {
        Intent intent = new Intent(context, MeDetailActivity.class);
        intent.putExtra(SOURCE_TAG, sourceTag);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_me_detail;
    }

    @Override
    protected void initData() {
        this.strSourceTag = getIntent().getStringExtra(SOURCE_TAG);
        DaggerIMeDetailComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .meDetailModule(new MeDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolbar();

        this.dataList = new ArrayList<>();
        this.adapter = new MeDetailAdapter(R.layout.list_item_me_detail, this.dataList);
        this.recyclerViewMeDetail.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerViewMeDetail.setAdapter(this.adapter);

        loadData();
    }

    @Override
    protected void onRelease() {
        if (this.presenter != null) {
            this.presenter.onRelease();
        }
    }

    @Override
    public void updateView(List<ImageVideoBean> imageVideoBeanList) {
        if (this.dataList != null) {
            this.dataList.clear();
            this.dataList.addAll(imageVideoBeanList);
            if (this.adapter != null) {
                this.adapter.notifyDataSetChanged();
            }
        }
    }

    private void initToolbar() {
        setSupportActionBar(this.toolBarMeDetail);
        ActionBar actionBar = getSupportActionBar();
        String title = SOURCE_IMAGE_TAG.equals(this.strSourceTag) ? "上传警情图片记录" : "上传警情视频记录";
        actionBar.setTitle(title);
    }

    private void loadData() {
        if (!TextUtils.isEmpty(this.strSourceTag)) {
            switch (this.strSourceTag) {
                case SOURCE_IMAGE_TAG:
                    if (this.presenter != null) {
                        this.presenter.loadImageRecordData();
                    }
                    break;
                case SOURCE_VIDEO_TAG:
                    if (this.presenter != null) {
                        this.presenter.loadVideoRecordData();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
