package com.bus.tian.tianbus.view.help;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.TitleContentBean;
import com.bus.tian.tianbus.view.BaseActivity;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BizDetailActivity extends BaseActivity {
    private static final String TITLE_CONTENT_TAG = "title_content_tag";

    @BindView(R.id.text_title_biz_detail)
    TextView textTitle;
    @BindView(R.id.text_content_biz_detail)
    TextView textContent;

    private TitleContentBean titleContentBean;

    public static void actionStart(Context context, TitleContentBean titleContentBean) {
        Intent intent = new Intent(context, BizDetailActivity.class);
        try {
            intent.putExtra(TITLE_CONTENT_TAG, new Gson().toJson(titleContentBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_biz_detail;
    }

    @Override
    protected void initData() {
        try {
            this.titleContentBean = new Gson().fromJson(getIntent().getStringExtra(TITLE_CONTENT_TAG), TitleContentBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        if (this.titleContentBean != null) {
            this.textTitle.setText(this.titleContentBean.getTitle());
            this.textContent.setText(this.titleContentBean.getContent());
        }
    }

    @Override
    protected void onRelease() {

    }
}
