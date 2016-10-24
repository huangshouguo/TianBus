package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.NetModule;
import com.bus.tian.tianbus.presenter.BasePresenter;

import dagger.Component;

/**
 * Created by hsg on 10/24/16.
 */
@Component(modules = NetModule.class)
public interface INetCompoent {
    void inject(BasePresenter basePresenter);
}
