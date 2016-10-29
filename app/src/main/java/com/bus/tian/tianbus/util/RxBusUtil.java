package com.bus.tian.tianbus.util;

import com.bus.tian.tianbus.model.event.UserLoginEvent;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by hsg on 2016/10/29.
 */

public class RxBusUtil {
    private static volatile RxBusUtil defaultInstance;
    private final Subject<Object, Object> rxBus;

    public RxBusUtil() {
        //PublishSubject只会把订阅发生的时间点之后来自原始Observable的数据发送给观察者
        rxBus = new SerializedSubject<>(PublishSubject.create());
    }

    // singleton
    public static RxBusUtil getDefaultInstance() {
        RxBusUtil tmpRxBus = defaultInstance;

        if (defaultInstance == null) {
            synchronized (RxBusUtil.class) {
                tmpRxBus = defaultInstance;
                if (defaultInstance == null) {
                    tmpRxBus = new RxBusUtil();
                    defaultInstance = tmpRxBus;
                }
            }
        }
        return tmpRxBus;
    }

    public void send(Object o) {
        rxBus.onNext(o);
    }

    public <T> Observable<T> toObserverable(Class<T> evenType) {
        return rxBus.ofType(evenType);
    }

    public boolean hasObservers() {
        return rxBus.hasObservers();
    }

    public static Observable<UserLoginEvent> loginEventObservable() {
        return getDefaultInstance()
                .toObserverable(UserLoginEvent.class)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
