package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.RegisterPresenterModule;
import com.bus.tian.tianbus.view.login.RegisterActivity;

import dagger.Component;

/**
 * Created by hsg on 10/26/16.
 */
@Component(dependencies = INetCompoent.class, modules = RegisterPresenterModule.class)
public interface IRegisterPresenterComponent {
    void inject(RegisterActivity registerPreActivity);
}
