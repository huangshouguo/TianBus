package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IPreRegisterContract;
import com.bus.tian.tianbus.presenter.PreRegisterPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 10/27/16.
 */
@Module
public class PreRegisterModule {

    private IPreRegisterContract.IView view;

    public PreRegisterModule(IPreRegisterContract.IView view) {
        this.view = view;
    }

    @Provides
    public IPreRegisterContract.IPresenter provideFinishRegisterContract(){
        return new PreRegisterPresenter(this.view);
    }
}
