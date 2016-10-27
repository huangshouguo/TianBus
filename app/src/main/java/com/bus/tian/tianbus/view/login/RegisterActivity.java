package com.bus.tian.tianbus.view.login;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.tool_bar_register)
    Toolbar toolBar;
    @BindView(R.id.layout_content_register)
    FrameLayout layoutContent;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onRelease() {

    }

    public static void actionStart(Context context) {

    }
}
