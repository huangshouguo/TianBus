package com.bus.tian.tianbus.presenter;

import com.bus.tian.tianbus.contract.IBaseContract;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.model.api.IApi;

import javax.inject.Inject;

/**
 * Created by hsg on 10/24/16.
 */

public class BasePresenter implements IBaseContract.IBasePresenter {
    protected final String TAG = this.getClass().getSimpleName();

    @Inject
    IApi api;

    private IBaseContract.IBaseView baseView;

    public BasePresenter(IBaseContract.IBaseView baseView) {
        this.baseView = baseView;
        DaggerINetCompoent.builder().build().inject(this);
    }

    protected IApi getApi() {
        return this.api;
    }
}
