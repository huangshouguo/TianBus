package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.ForumCreateModule;
import com.bus.tian.tianbus.view.forum.ForumCreateFragment;

import dagger.Component;

/**
 * Created by hsg on 11/5/16.
 */
@Component(dependencies = INetCompoent.class, modules = ForumCreateModule.class)
public interface IForumCreateComponent {
    void inject(ForumCreateFragment forumCreateFragment);
}
