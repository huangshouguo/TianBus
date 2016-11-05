package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.ForumSummaryModule;
import com.bus.tian.tianbus.view.forum.ForumSummaryFragment;

import dagger.Component;

/**
 * Created by hsg on 11/5/16.
 */
@Component(dependencies = INetCompoent.class, modules = ForumSummaryModule.class)
public interface IForumSummaryComponent {
    void inject(ForumSummaryFragment forumSummaryFragment);
}
