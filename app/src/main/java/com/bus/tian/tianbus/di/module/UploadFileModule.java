package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IUploadFileContract;
import com.bus.tian.tianbus.presenter.UpLoadFilePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 11/12/16.
 */
@Module
public class UploadFileModule {

    private IUploadFileContract.IView view;

    public UploadFileModule(IUploadFileContract.IView view) {
        this.view = view;
    }

    @Provides
    public IUploadFileContract.IPresenter provideUploadFileContractPresenter() {
        return new UpLoadFilePresenter(this.view);
    }
}
