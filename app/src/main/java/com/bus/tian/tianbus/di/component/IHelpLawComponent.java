package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.HelpLawModule;
import com.bus.tian.tianbus.view.help.LawFragment;

import dagger.Component;

/**
 * Created by hsg on 11/4/16.
 */
@Component(dependencies = INetCompoent.class, modules = HelpLawModule.class)
public interface IHelpLawComponent {
    void inject(LawFragment lawFragment);
}
