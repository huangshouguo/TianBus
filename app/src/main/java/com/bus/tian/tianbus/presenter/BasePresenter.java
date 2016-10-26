package com.bus.tian.tianbus.presenter;

import com.bus.tian.tianbus.contract.IBaseContract;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.model.api.ApiResponseCode;
import com.bus.tian.tianbus.model.api.IApi;
import com.bus.tian.tianbus.model.bean.ApiResponseBean;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hsg on 10/24/16.
 */

public class BasePresenter implements IBaseContract.IBasePresenter {
    protected final String TAG = this.getClass().getSimpleName();

    @Inject
    IApi api;

    private IBaseContract.IBaseView baseView;


    public BasePresenter(IBaseContract.IBaseView baseView) {
        this.baseView = baseView;
        DaggerINetCompoent.builder().build().inject(this);
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
                        if (tApiResponseBean.getCode() == ApiResponseCode.API_RSP_CODE_SUCCEED) {
                            return createData(tApiResponseBean.getData());
                        } else {
                            switch (tApiResponseBean.getCode()) {
                                case ApiResponseCode.API_RSP_CODE_TOKEN_INVALID:
                                    // TODO: 10/26/16 add handler for token invalid
                                    break;
                                default:
                                    break;
                            }
                            return Observable.error(new Throwable(tApiResponseBean.getMessage()));
                        }
                    }
                });
    }

    private <T> Observable<T> createData(T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e){
                    subscriber.onError(e);
                }
            }
        });
    }
}
