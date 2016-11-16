package com.bus.tian.tianbus.di.component;

import com.bus.tian.tianbus.di.module.UploadFileModule;
import com.bus.tian.tianbus.view.home.CameraActivity;

import dagger.Component;

/**
 * Created by hsg on 11/12/16.
 */
@Component(dependencies = INetCompoent.class, modules = UploadFileModule.class)
public interface IUploadFileComponent {
    void inject(CameraActivity activity);
}
