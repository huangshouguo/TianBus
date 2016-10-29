package com.bus.tian.tianbus.view;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.bus.tian.tianbus.contract.IBaseContract;
import com.bus.tian.tianbus.util.ProgressObserver;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

import javax.annotation.Nonnull;

import rx.Observable;
import rx.Observer;
import rx.subjects.BehaviorSubject;

/**
 * Created by hsg on 10/24/16.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseContract.IBaseView, LifecycleProvider<ActivityEvent> {
    protected final String TAG = this.getClass().getSimpleName();
    protected Context context;

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
    private ProgressObserver progressObserver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
        this.context = this;

        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.lifecycleSubject.onNext(ActivityEvent.CREATE);

        initData();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    protected void onPause() {
        this.lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        this.lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        this.lifecycleSubject.onNext(ActivityEvent.DESTROY);
        this.onRelease();

        if (this.progressObserver != null) {
            this.progressObserver.onRelease();
            this.progressObserver = null;
        }

        super.onDestroy();
    }

    @Override
    public void showRemainderMessage(String remainderMsg) {
        Log.d(TAG, "showRemainderMessage() called with: remainderMsg = [" + remainderMsg + "]");

        // TODO: 2016/10/29 wrapper show message func
        Toast.makeText(this, remainderMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        Log.e(TAG, "showErrorMessage: errorMsg = " + errorMsg);

        // TODO: 10/27/16 wrapper show message func
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public Observer<String> showProgress(String loadingMessage, boolean shouldHideProgress) {
        return getProgressObserver().showLoading(loadingMessage, shouldHideProgress);
    }

    @Nonnull
    @Override
    public Observable<ActivityEvent> lifecycle() {
        return this.lifecycleSubject.asObservable();
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@Nonnull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(this.lifecycleSubject, event);
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(this.lifecycleSubject);
    }

    protected <T> LifecycleTransformer<T> bindToDestroyEvent() {
        //not unsubscribe until view destroyed,  instead of bindToliefeCycle that some bugs, such as subscribed in onResume and unsubscribed in onPause when show loading
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    @Override
    public <T> LifecycleTransformer<T> doBindToLifecycle() {
        return bindToDestroyEvent();
    }

    protected abstract int getContentViewResId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void onRelease();

    private ProgressObserver getProgressObserver() {
        if (this.progressObserver == null) {
            this.progressObserver = new ProgressObserver(this);
        }
        return this.progressObserver;
    }
}
