package com.bus.tian.tianbus.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bus.tian.tianbus.contract.IBaseContract;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

import javax.annotation.Nonnull;

import rx.Observable;
import rx.Observer;
import rx.subjects.BehaviorSubject;

/**
 * Created by hsg on 10/24/16.
 */

public abstract class BaseFragment extends Fragment implements IBaseContract.IBaseView, LifecycleProvider<FragmentEvent> {
    protected final String TAG = this.getClass().getSimpleName();
    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();
    protected BaseActivity baseActivity;
    protected View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.baseActivity = (BaseActivity) context;
        this.lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.lifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(getContentViewResId(), container, false);
        initData();
        initView();
        return this.rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        this.lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        this.lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        this.lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        onRelease();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        this.lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        this.lifecycleSubject.onNext(FragmentEvent.DETACH);
        onRelease();
        super.onDetach();
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        if (this.baseActivity != null){
            this.baseActivity.showErrorMessage(errorMsg);
        }
    }

    @Override
    public Observer<String> showProgress(String loadingMessage, boolean shouldHideProgress) {
        if (this.baseActivity != null){
            this.baseActivity.showProgress(loadingMessage, shouldHideProgress);
        }
        return null;
    }

    @Nonnull
    @Override
    public Observable<FragmentEvent> lifecycle() {
        return this.lifecycleSubject.asObservable();
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@Nonnull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(this.lifecycleSubject, event);
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(this.lifecycleSubject);
    }

    @Override
    public <T> LifecycleTransformer<T> doBindToLifecycle() {
        return bindToDestroyEvent();
    }

    public <T> LifecycleTransformer<T> bindToDestroyEvent() {
        return bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    protected abstract int getContentViewResId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void onRelease();
}
