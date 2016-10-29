package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IMainContract;
import com.bus.tian.tianbus.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 2016/10/29.
 */
@Module
public class MainModule {

    private IMainContract.IView view;

    public MainModule(IMainContract.IView view) {
        this.view = view;
    }

    @Provides
    public IMainContract.IPresenter provideMainContractPresenter() {
        return new MainPresenter(this.view);
    }
}
