package com.bus.tian.tianbus.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.bus.tian.tianbus.view.BaseActivity;

import rx.Observer;

/**
 * Created by hsg on 10/26/16.
 */

public class ProgressObserver {
    private static final String TAG = "ProgressObserver";
    private Activity activity;
    private Observer<String> observer;
    private ProgressDialog progressDialog;

    public ProgressObserver(BaseActivity baseActivity) {
        this.activity = baseActivity;
        initObserver();
        initProgress();
    }

    public Observer<String> showLoading(@NonNull final String loadingMessage, @NonNull final boolean shouldHideLoading) {
        Log.d(TAG, "showLoading() called with: loadingMessage = [" + loadingMessage + "], shouldHideLoading = [" + shouldHideLoading + "]");

        showProgress(loadingMessage, shouldHideLoading);
        return getObserver();
    }

    public void onRelease() {
        Log.w(TAG, "onRelease");

        if (this.progressDialog != null) {
            hideProgress();
            this.progressDialog = null;
        }

        if (this.observer != null) {
            this.observer = null;
        }

        if (this.activity != null) {
            this.activity = null;
        }
    }

    private void initObserver() {
        Log.d(TAG, "initObserver() called");

        this.observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                hideProgress();
            }

            @Override
            public void onNext(String s) {
                setMessage(s);
            }
        };
    }

    private void initProgress() {
        Log.d(TAG, "initProgress() called");

        if (this.activity != null) {
            this.progressDialog = new ProgressDialog(this.activity);
            this.progressDialog.setIndeterminate(true);
            this.progressDialog.setCancelable(true);
            this.progressDialog.setCanceledOnTouchOutside(false);
            this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    hideProgress();
                }
            });
        }
    }

    private void showProgress(@NonNull final String showMessage, boolean shouldHideLoading) {
        Log.d(TAG, "showProgress() called with: showMessage = [" + showMessage + "], shouldHideLoading = [" + shouldHideLoading + "]");

        if ((this.progressDialog != null)) {
            if (!shouldHideLoading && !this.progressDialog.isShowing()) {
                this.progressDialog.show();
            }
        }

        getObserver().onNext(showMessage);
    }

    private void hideProgress() {
        Log.d(TAG, "hideProgress() called");

        if (this.progressDialog != null) {
            if (this.progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
        }
    }

    private void setMessage(final String msg) {
        Log.d(TAG, "setMessage() called with: msg = [" + msg + "]");

        if (!TextUtils.isEmpty(msg)) {
            if (this.progressDialog != null && this.progressDialog.isShowing()) {
                this.progressDialog.setMessage(msg);
            }
        }
    }

    private Observer<String> getObserver() {
        Log.d(TAG, "getObserver() called");

        if (this.observer == null) {
            initObserver();
        }
        return this.observer;
    }
}
