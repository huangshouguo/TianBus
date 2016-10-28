package com.bus.tian.tianbus.view.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IForgotPasswordContract;
import com.bus.tian.tianbus.di.component.DaggerIForgotPasswordComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.ForgotPasswordModule;
import com.bus.tian.tianbus.view.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends BaseActivity implements IForgotPasswordContract.IView {
    private static final String PHONE_NUMBER_TAG = "phone_number_tag";

    @BindView(R.id.tool_bar_forgot_password)
    Toolbar toolBar;
    @BindView(R.id.text_forgot_password_remainder)
    TextView textRemainder;
    @BindView(R.id.edit_text_phone_number)
    EditText editTextPhoneNumber;
    @BindView(R.id.edit_text_password)
    EditText editTextPassword;
    @BindView(R.id.edit_text_password_confirm)
    EditText editTextPasswordConfirm;
    @BindView(R.id.edit_text_sms_captcha)
    EditText editTextSmsCaptcha;
    @BindView(R.id.btn_sms_captch_timmer)
    Button btnSmsCaptchTimmer;
    @BindView(R.id.btn_forgot_password)
    Button btnForgotPassword;

    @Inject
    IForgotPasswordContract.IPresenter presenter;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void initData() {
        DaggerIForgotPasswordComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .forgotPasswordModule(new ForgotPasswordModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onRelease() {

    }

    @OnClick({R.id.btn_sms_captch_timmer, R.id.btn_forgot_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sms_captch_timmer:
                break;
            case R.id.btn_forgot_password:
                break;
        }
    }

    @Override
    public void updateSmsCaptchaView() {

    }

    @Override
    public void updateResetView() {

    }

    public static void actionStart(Context context, final String phoneNumber) {
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        intent.putExtra(PHONE_NUMBER_TAG, phoneNumber);
        context.startActivity(intent);
    }
}
