package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.MainModule;
import com.bus.tian.tianbus.view.MainActivity;

import dagger.Component;

/**
 * Created by hsg on 2016/10/29.
 */
@Component(dependencies = INetCompoent.class, modules = MainModule.class)
public interface IMainComponent {
    void inject(MainActivity mainActivity);
}
