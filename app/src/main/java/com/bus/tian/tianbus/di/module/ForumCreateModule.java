package com.bus.tian.tianbus.di.module;

import com.bus.tian.tianbus.contract.IForumCreateContract;
import com.bus.tian.tianbus.presenter.ForumCreatePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hsg on 11/5/16.
 */
@Module
public class ForumCreateModule {
    private IForumCreateContract.IView view;

    public ForumCreateModule(IForumCreateContract.IView view) {
        this.view = view;
    }

    @Provides
    public IForumCreateContract.IPresenter provideForumCreateContractPresenter() {
        return new ForumCreatePresenter(this.view);
    }
}
