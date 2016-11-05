package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IForumDetailContract;
import com.bus.tian.tianbus.presenter.ForumDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 11/5/16.
 */
@Module
public class ForumDetailModule {
    private IForumDetailContract.IView view;

    public ForumDetailModule(IForumDetailContract.IView view) {
        this.view = view;
    }

    @Provides
    public IForumDetailContract.IPresenter provideForumDetailContractPresenter() {
        return new ForumDetailPresenter(this.view);
    }
}
