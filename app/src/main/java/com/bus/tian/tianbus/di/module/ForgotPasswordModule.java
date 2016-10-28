package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IForgotPasswordContract;
import com.bus.tian.tianbus.presenter.ForgotPasswordPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 10/28/16.
 */
@Module
public class ForgotPasswordModule {
    IForgotPasswordContract.IView view;

    public ForgotPasswordModule(IForgotPasswordContract.IView view) {
        this.view = view;
    }

    @Provides
    public IForgotPasswordContract.IPresenter provideForgotPasswordContractPresenter() {
        return new ForgotPasswordPresenter(this.view);
    }
}
