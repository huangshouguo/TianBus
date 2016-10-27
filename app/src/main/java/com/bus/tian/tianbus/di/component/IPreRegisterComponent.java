package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.PreRegisterModule;
import com.bus.tian.tianbus.view.login.PreRegisterFragment;

import dagger.Component;

/**
 * Created by hsg on 10/26/16.
 */
@Component(dependencies = INetCompoent.class, modules = PreRegisterModule.class)
public interface IPreRegisterComponent {
    void inject(PreRegisterFragment preRegisterFragment);
}
