package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IMeContract;
import com.bus.tian.tianbus.presenter.MePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 2016/11/6.
 */
@Module
public class MeModule {
    private IMeContract.IView view;

    public MeModule(IMeContract.IView view) {
        this.view = view;
    }

    @Provides
    public IMeContract.IPresenter provideMeContractPresenter(){
        return new MePresenter(this.view);
    }
}
