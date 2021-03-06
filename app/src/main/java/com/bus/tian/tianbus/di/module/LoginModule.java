package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.ILoginContract;
import com.bus.tian.tianbus.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 2016/10/26.
 */
@Module
public class LoginModule {
    private ILoginContract.IView view;

    public LoginModule(ILoginContract.IView view) {
        this.view = view;
    }

    @Provides
    public ILoginContract.IPresenter provideLoginPresenter() {
        return new LoginPresenter(this.view);
    }
}
