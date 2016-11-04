package com.bus.tian.tianbus.view.common;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.WebView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.TextLinkBean;
import com.bus.tian.tianbus.view.BaseActivity;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {
    private static final String TEXT_LINK_BEAN_TAG = "text_link_bean_tag";

    @BindView(R.id.web_view)
    WebView webView;

    private TextLinkBean textLinkBean;

    public static void actionStart(Context context, TextLinkBean textLinkBean) {
        if (textLinkBean == null) {
            return;
        }

        Intent intent = new Intent(context, WebActivity.class);
        String strBean = new Gson().toJson(textLinkBean);
        intent.putExtra(TEXT_LINK_BEAN_TAG, strBean);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData() {
        this.textLinkBean = new Gson().fromJson(getIntent().getStringExtra(TEXT_LINK_BEAN_TAG), TextLinkBean.class);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        this.webView.getSettings().setDatabaseEnabled(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setJavaScriptEnabled(true);

        if (this.textLinkBean != null && !TextUtils.isEmpty(this.textLinkBean.getLink())) {
            this.webView.loadUrl(this.textLinkBean.getLink());
        }
    }

    @Override
    protected void onRelease() {

    }
}
