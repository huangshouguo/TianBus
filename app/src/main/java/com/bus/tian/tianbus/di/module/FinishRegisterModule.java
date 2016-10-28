package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IFinishRegisterContract;
import com.bus.tian.tianbus.presenter.FinishRegisterPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 10/27/16.
 */
@Module
public class FinishRegisterModule {

    private IFinishRegisterContract.IView view;

    public FinishRegisterModule(IFinishRegisterContract.IView view) {
        this.view = view;
    }

    @Provides
    public IFinishRegisterContract.IPresenter provideFinishRegisterContractPresenter(){
        return new FinishRegisterPresenter(this.view);
    }
}
