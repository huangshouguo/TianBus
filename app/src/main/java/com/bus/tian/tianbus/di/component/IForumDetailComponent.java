package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.ForumDetailModule;
import com.bus.tian.tianbus.view.forum.ForumDetailFragment;

import dagger.Component;

/**
 * Created by hsg on 11/5/16.
 */
@Component(dependencies = INetCompoent.class, modules = ForumDetailModule.class)
public interface IForumDetailComponent {
    void inject(ForumDetailFragment forumDetailFragment);
}
