package com.bus.tian.tianbus.presenter;

import com.bus.tian.tianbus.contract.IBaseContract;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.model.api.ApiResponseCode;
import com.bus.tian.tianbus.model.api.IApi;
import com.bus.tian.tianbus.model.bean.ApiResponseBean;
import com.bus.tian.tianbus.util.ErrorMsgUtil;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hsg on 10/24/16.
 */

public class BasePresenter implements IBaseContract.IBasePresenter {
    protected final String TAG = this.getClass().getSimpleName();

    @Inject
    IApi api;

    private CompositeSubscription compositeSubscription;
    private IBaseContract.IBaseView baseView;


    public BasePresenter(IBaseContract.IBaseView baseView) {
        this.baseView = baseView;
        DaggerINetCompoent.builder().build().inject(this);
    }

    @Override
    public void onRelease() {
        if (this.compositeSubscription != null) {
            this.compositeSubscription.unsubscribe();
            this.compositeSubscription = null;
        }
    }

    protected void addSubscription(Subscription subscription) {
        if (subscription == null) {
            return;
        }

        if (this.compositeSubscription == null) {
            this.compositeSubscription = new CompositeSubscription();
        }

        this.compositeSubscription.add(subscription);
    }

    protected IApi getApi() {
        return this.api;
    }

    protected <T> Observable.Transformer<T, T> applySchedulers() {
        return tObservable -> tObservable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    protected <T> Observable.Transformer<T, T> bindToLifecycle() {
        return (this.baseView != null) ? this.baseView.doBindToLifecycle() : null;
    }

    protected <T> Observable.Transformer<T, T> doSchedulersAndBindLifecycle() {
        return tObservable -> tObservable
                .compose(bindToLifecycle())
                .compose(applySchedulers());
    }

    protected <T> Observable.Transformer<ApiResponseBean<T>, T> preHandleApiResponse() {
        return apiResponseBeanObservable -> apiResponseBeanObservable
                .flatMap(new Func1<ApiResponseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(ApiResponseBean<T> tApiResponseBean) {
                        if (tApiResponseBean != null) {
                            if (tApiResponseBean.getCode() == ApiResponseCode.API_RSP_CODE_SUCCEED) {
                                return createData(tApiResponseBean.getData());
                            }
                            return Observable.error(new Throwable(tApiResponseBean.getMsg()));
                        }
                        return Observable.error(new Throwable(ErrorMsgUtil.ERR_MSG_SERVER_ERROR));
                    }
                });
    }

    private <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
}
