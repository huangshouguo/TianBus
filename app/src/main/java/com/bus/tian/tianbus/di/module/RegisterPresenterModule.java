package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IRegisterContract;
import com.bus.tian.tianbus.di.name.RegisterNamed;
import com.bus.tian.tianbus.presenter.RegisterPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 10/26/16.
 */
@Module
public class RegisterPresenterModule {
    private IRegisterContract.IPreView preView;
    private IRegisterContract.IFinishView finishView;

    public RegisterPresenterModule(IRegisterContract.IPreView preView) {
        this.preView = preView;
    }

    public RegisterPresenterModule(IRegisterContract.IFinishView finishView) {
        this.finishView = finishView;
    }

    @RegisterNamed("pre")
    @Provides
    public RegisterPresenter providePrePresenter() {
        return new RegisterPresenter(this.preView);
    }

    @RegisterNamed("finish")
    @Provides
    public RegisterPresenter provideFinishPresenter() {
        return new RegisterPresenter(this.finishView);
    }
}
