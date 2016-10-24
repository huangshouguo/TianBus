package com.bus.tian.tianbus.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bus.tian.tianbus.contract.IBaseContract;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

import javax.annotation.Nonnull;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by hsg on 10/24/16.
 */

public abstract class BaseFragment extends Fragment implements IBaseContract.IBaseView, LifecycleProvider<FragmentEvent> {
    protected final String TAG = this.getClass().getSimpleName();
    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();
    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.lifecycleSubject.onNext(FragmentEvent.CREATE);
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
        super.onDetach();
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

    public <T> LifecycleTransformer<T> bindToDestroyEvent() {
        return bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }

    protected abstract int getContentViewResId();

    protected abstract void initData();

    protected abstract void initView();
}
