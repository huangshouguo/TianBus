package com.bus.tian.tianbus.view.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.ILoginContract;
import com.bus.tian.tianbus.di.component.DaggerILoginComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.LoginModule;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.view.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginContract.IView {

    @BindView(R.id.edit_text_phone_number)
    EditText editTextPhoneNumber;
    @BindView(R.id.edit_text_password)
    EditText editTextPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_login_register)
    Button btnRegister;
    @BindView(R.id.btn_login_forgot)
    Button btnForgot;
    @Inject
    ILoginContract.IPresenter loginPresenter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (this.loginPresenter != null){
            this.loginPresenter.cancelLogin();
        }
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        DaggerILoginComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onRelease() {
        if (this.loginPresenter != null) {
            this.loginPresenter.onRelease();
        }
    }

    @OnClick({R.id.btn_login, R.id.btn_login_register, R.id.btn_login_forgot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                doLogin();
                break;
            case R.id.btn_login_register:
                showRegister();
                break;
            case R.id.btn_login_forgot:
                showForgotPassword();
                break;
            default:
                break;
        }
    }

    @Override
    public void updateLoginView(UserBean userBean) {
        finish();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    private void doLogin() {
        if (this.loginPresenter != null) {
            this.loginPresenter.login(
                    this.editTextPhoneNumber.getText().toString(),
                    this.editTextPassword.getText().toString());
        }
    }

    private void showRegister() {
        RegisterActivity.actionStart(this, this.editTextPhoneNumber.getText().toString());
    }

    private void showForgotPassword() {
        ForgotPasswordActivity.actionStart(this, this.editTextPhoneNumber.getText().toString());
    }
}
