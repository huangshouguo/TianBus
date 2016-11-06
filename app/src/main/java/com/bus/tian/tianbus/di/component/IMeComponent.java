package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.MeModule;
import com.bus.tian.tianbus.view.me.MeFragment;

import dagger.Component;

/**
 * Created by hsg on 2016/11/6.
 */
@Component(dependencies = INetCompoent.class, modules = MeModule.class)
public interface IMeComponent {
    void inject(MeFragment meFragment);
}
