package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.HomeModule;
import com.bus.tian.tianbus.view.home.HomeFragment;

import dagger.Component;

/**
 * Created by hsg on 2016/10/30.
 */
@Component(dependencies = INetCompoent.class, modules = HomeModule.class)
public interface IHomeComponent {
    void inject(HomeFragment homeFragment);
}
