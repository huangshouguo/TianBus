package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.ForgotPasswordModule;
import com.bus.tian.tianbus.view.login.ForgotPasswordActivity;

import dagger.Component;

/**
 * Created by hsg on 10/28/16.
 */

@Component(dependencies = INetCompoent.class, modules = ForgotPasswordModule.class)
public interface IForgotPasswordComponent {
    void inject(ForgotPasswordActivity forgotPasswordActivity);
}
