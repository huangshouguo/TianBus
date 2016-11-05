package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IForumSummaryContract;
import com.bus.tian.tianbus.presenter.ForumSummaryPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 11/5/16.
 */
@Module
public class ForumSummaryModule {
    private IForumSummaryContract.IView view;

    public ForumSummaryModule(IForumSummaryContract.IView view) {
        this.view = view;
    }

    @Provides
    public IForumSummaryContract.IPresenter provideForumSummaryContractPresenter() {
        return new ForumSummaryPresenter(this.view);
    }
}
