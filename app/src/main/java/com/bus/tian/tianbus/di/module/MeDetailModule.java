package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IMeDetailContract;
import com.bus.tian.tianbus.presenter.MeDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 11/18/16.
 */
@Module
public class MeDetailModule {
    private IMeDetailContract.IView view;

    public MeDetailModule(IMeDetailContract.IView view) {
        this.view = view;
    }

    @Provides
    public IMeDetailContract.IPresenter provideMeDetailContractPresenter() {
        return new MeDetailPresenter(this.view);
    }
}
