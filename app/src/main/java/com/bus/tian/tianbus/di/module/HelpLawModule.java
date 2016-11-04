package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IHelpLawContract;
import com.bus.tian.tianbus.presenter.HelpLawPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 11/4/16.
 */
@Module
public class HelpLawModule {
    private IHelpLawContract.IView view;

    public HelpLawModule(IHelpLawContract.IView view) {
        this.view = view;
    }

    @Provides
    public IHelpLawContract.IPresenter provideHelpLawContractPresenter() {
        return new HelpLawPresenter(this.view);
    }
}
