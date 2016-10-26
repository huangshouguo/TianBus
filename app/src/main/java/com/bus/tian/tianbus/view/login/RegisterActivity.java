package com.bus.tian.tianbus.view.login;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IRegisterContract;
import com.bus.tian.tianbus.di.component.DaggerIRegisterPresenterComponent;
import com.bus.tian.tianbus.di.module.RegisterPresenterModule;
import com.bus.tian.tianbus.di.name.RegisterNamed;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.presenter.RegisterPresenter;
import com.bus.tian.tianbus.view.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity implements IRegisterContract.IPreView {


    @RegisterNamed("pre")
    @Inject
    RegisterPresenter registerPresenter;
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
        DaggerIRegisterPresenterComponent.builder()
                .registerPresenterModule(new RegisterPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    public void updateView(UserBean userBean) {

    }

    public static void actionStart(Context context){

    }
}
