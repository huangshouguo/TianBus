package com.bus.tian.tianbus.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.view.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

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
    @BindView(R.id.btn_sms_captcha_timer)
    Button btnSmsCaptchTimmer;
    @BindView(R.id.btn_forgot_password)
    Button btnForgotPassword;

    @Inject
    IForgotPasswordContract.IPresenter presenter;

    private String strSmsCaptchaId;

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
        this.btnForgotPassword.setEnabled(false);
    }

    @Override
    protected void onRelease() {
        if (presenter != null) {
            presenter.onRelease();
            presenter = null;
        }

    }

    @OnClick({R.id.btn_sms_captcha_timer, R.id.btn_forgot_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sms_captcha_timer:
                sendSmsCaptcha();
                break;
            case R.id.btn_forgot_password:
                doResetPassword();
                break;
            default:
                break;
        }
    }

    @Override
    public void updateSmsCaptchaView(UserBean userBean) {
        if (userBean != null) {
            this.strSmsCaptchaId = userBean.getCaptchaId();
            this.btnForgotPassword.setEnabled(true);
            this.textRemainder.setText(getRemainderMessage());
            startTimer();
        }
    }

    @Override
    public void updateResetView() {
        finish();
    }

    public static void actionStart(Context context, final String phoneNumber) {
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        intent.putExtra(PHONE_NUMBER_TAG, phoneNumber);
        context.startActivity(intent);
    }

    private void startTimer() {
        this.btnSmsCaptchTimmer.setEnabled(false);

        Timer timer = new Timer();
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int time = msg.arg1;
                btnSmsCaptchTimmer.setText(String.valueOf(time) + "s");
                if (time <= 0) {
                    timer.cancel();
                    btnSmsCaptchTimmer.setText(R.string.text_resent_onclick);
                    btnSmsCaptchTimmer.setEnabled(true);
                }
            }
        };

        TimerTask task = new TimerTask() {
            int time = 60;

            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = time--;
                handler.sendMessage(message);
            }
        };

        timer.schedule(task, 1000, 1000);
    }

    private void sendSmsCaptcha() {
        if (this.presenter != null) {
            this.presenter.loadSmsCaptcha(this.editTextPhoneNumber.getText().toString());
        }
    }

    private String getRemainderMessage() {
        return "短信验证码已经发送至您的手机：" + this.editTextPhoneNumber.getText().toString() + "，请注意查收并完成密码的设置。";
    }

    private void doResetPassword() {
        if (this.presenter != null) {
            this.presenter.doResetPassword(this.editTextPhoneNumber.getText().toString(),
                    this.editTextPassword.getText().toString(),
                    this.editTextPasswordConfirm.getText().toString(),
                    this.editTextSmsCaptcha.getText().toString(),
                    this.strSmsCaptchaId);
        }
    }
}
