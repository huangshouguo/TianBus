package com.bus.tian.tianbus.di.name;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by hsg on 10/26/16.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterNamed {
    String value() default "";
}
