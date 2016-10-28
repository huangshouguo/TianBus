package com.bus.tian.tianbus.view.login;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.view.BaseActivity;
import com.bus.tian.tianbus.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {
    private static final String PHONE_NUMBER_TAG = "phone_number_tag";

    @BindView(R.id.tool_bar_register)
    Toolbar toolBar;

    private FinishRegisterFragment finishRegisterFragment;
    private PreRegisterFragment preRegisterFragment;
    private String strInputPhoneNumber;


    @Override
    protected int getContentViewResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        this.strInputPhoneNumber = intent.getStringExtra(PHONE_NUMBER_TAG);
        this.preRegisterFragment = new PreRegisterFragment();
        this.finishRegisterFragment = new FinishRegisterFragment();
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolBar();
        showPreFragment();
    }

    @Override
    protected void onRelease() {
    }

    public static void actionStart(Context context, final String phoneNumber) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra(PHONE_NUMBER_TAG, phoneNumber);
        context.startActivity(intent);
    }

    private void initToolBar() {
        setSupportActionBar(this.toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.text_quickly_register);
    }

    private void showPreFragment() {
        updateFragment(this.preRegisterFragment, null);
    }

    public void showFinishFragment() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.text_finish_register);
        updateFragment(this.finishRegisterFragment, this.preRegisterFragment);
    }

    public void setInputPhoneNumber(final String inputPhoneNumber){
        Log.d(TAG, "setInputPhoneNumber() called with: inputPhoneNumber = [" + inputPhoneNumber + "]");
        this.strInputPhoneNumber = inputPhoneNumber;
    }

    public String getInputPhoneNumber() {
        return this.strInputPhoneNumber;
    }

    private void updateFragment(final BaseFragment newFragment, BaseFragment oldFragment) {
        if (newFragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (oldFragment != null) {
                fragmentTransaction.remove(oldFragment);
            }
            fragmentTransaction.replace(R.id.layout_content_register, newFragment);
            fragmentTransaction.commit();
        }
    }
}
