package com.bus.tian.tianbus.view.login;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IRegisterContract;
import com.bus.tian.tianbus.di.component.DaggerIRegisterPresenterComponent;
import com.bus.tian.tianbus.di.module.RegisterPresenterModule;
import com.bus.tian.tianbus.di.name.RegisterNamed;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.presenter.RegisterPresenter;
import com.bus.tian.tianbus.view.BaseActivity;

import javax.inject.Inject;

public class RegisterPreActivity extends BaseActivity implements IRegisterContract.IPreView {

    @RegisterNamed("pre")
    @Inject
    RegisterPresenter registerPresenter;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_register_pre;
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

    }

    @Override
    public void updateView(UserBean userBean) {

    }
}
