package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.FinishRegisterModule;
import com.bus.tian.tianbus.view.login.FinishRegisterFragment;

import dagger.Component;

/**
 * Created by hsg on 10/26/16.
 */
@Component(dependencies = INetCompoent.class, modules = FinishRegisterModule.class)
public interface IFinishRegisterComponent {
    void inject(FinishRegisterFragment finishRegisterFragment);
}
