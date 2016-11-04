package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IHelpBizContract;
import com.bus.tian.tianbus.presenter.HelpBizPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 11/4/16.
 */
@Module
public class HelpBizModule {

    IHelpBizContract.IView view;

    public HelpBizModule(IHelpBizContract.IView view) {
        this.view = view;
    }

    @Provides
    public IHelpBizContract.IPresenter provideHelpBizContractPresenter() {
        return new HelpBizPresenter(this.view);
    }
}
