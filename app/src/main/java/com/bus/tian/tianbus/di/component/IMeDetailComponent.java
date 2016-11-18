package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.MeDetailModule;
import com.bus.tian.tianbus.view.me.MeDetailActivity;

import dagger.Component;

/**
 * Created by hsg on 11/18/16.
 */

@Component(dependencies = INetCompoent.class, modules = MeDetailModule.class)
public interface IMeDetailComponent {
    void inject(MeDetailActivity activity);
}
