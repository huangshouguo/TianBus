package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.LoginPresenterModule;
import com.bus.tian.tianbus.view.login.LoginActivity;

import dagger.Component;

/**
 * Created by hsg on 2016/10/26.
 */
@Component(dependencies = INetCompoent.class, modules = LoginPresenterModule.class)
public interface ILoginComponent {
    void inject(LoginActivity loginActivity);
}
