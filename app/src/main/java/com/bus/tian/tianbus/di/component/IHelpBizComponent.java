package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.HelpBizModule;
import com.bus.tian.tianbus.view.help.BusinessFragment;

import dagger.Component;

/**
 * Created by hsg on 11/4/16.
 */
@Component(dependencies = INetCompoent.class, modules = HelpBizModule.class)
public interface IHelpBizComponent {
    void inject(BusinessFragment businessFragment);
}
