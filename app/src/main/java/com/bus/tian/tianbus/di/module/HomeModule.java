package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IHomeContract;
import com.bus.tian.tianbus.presenter.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 2016/10/30.
 */
@Module
public class HomeModule {

    private IHomeContract.IView view;

    public HomeModule(IHomeContract.IView view) {
        this.view = view;
    }

    @Provides
    public IHomeContract.IPresenter provideHomeContractPresenter() {
        return new HomePresenter(this.view);
    }
}
