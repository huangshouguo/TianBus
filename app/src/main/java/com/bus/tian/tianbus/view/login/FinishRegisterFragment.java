package com.bus.tian.tianbus.view.login;


import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IFinishRegisterContract;
import com.bus.tian.tianbus.di.component.DaggerIFinishRegisterComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.FinishRegisterModule;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.view.BaseFragment;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinishRegisterFragment extends BaseFragment implements IFinishRegisterContract.IView {

    @BindView(R.id.text_register_finish_remainder)
    TextView texRemainder;
    @BindView(R.id.edit_text_sms_captcha)
    EditText editTextSmsCaptcha;
    @BindView(R.id.btn_sms_captcha_timer)
    Button btnSmsCaptchTimmer;
    @BindView(R.id.edit_text_password)
    EditText editTextPassword;
    @BindView(R.id.edit_text_password_confirm)
    EditText editTextPasswordConfirm;
    @BindView(R.id.btn_finish_register)
    Button btnFinishRegister;

    @Inject
    IFinishRegisterContract.IPresenter presenter;

    private RegisterActivity registerActivity;
    private String strMosaicPhoneNumber;
    private String strSmsCaptchaId;


    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_register_finish;
    }

    @Override
    protected void initData() {
        DaggerIFinishRegisterComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .finishRegisterModule(new FinishRegisterModule(this))
                .build()
                .inject(this);

        this.registerActivity = (RegisterActivity) getActivity();
        sendSmsCaptcha();
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);
        this.texRemainder.setText(getRemainderMessage());
    }

    @Override
    protected void onRelease() {
        if (this.presenter != null) {
            this.presenter.onRelease();
        }
    }

    @Override
    public void updateSmsCaptchaView(UserBean userBean) {
        if (userBean != null) {
            this.strMosaicPhoneNumber = userBean.getPhone();
            this.strSmsCaptchaId = userBean.getCaptchaId();
            this.texRemainder.setText(getRemainderMessage());
            startTimer();
        }

    }

    @Override
    public void updateRegisterView(UserBean userBean) {

    }

    @OnClick({R.id.btn_sms_captcha_timer, R.id.btn_finish_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sms_captcha_timer:
                sendSmsCaptcha();
                break;
            case R.id.btn_finish_register:
                doRegister();
                break;
        }
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
            this.presenter.loadSmsCaptcha(getInputPhoneNumber());
        }
    }

    private void doRegister() {
        if (shouldDoRegister()) {
            didRegister();
        } else {
            this.editTextPassword.getText().clear();
            this.editTextPasswordConfirm.getText().clear();
        }
    }

    private boolean shouldDoRegister() {
        return (this.presenter != null) && this.presenter.confirmPassword(
                this.editTextPassword.getText().toString(),
                this.editTextPasswordConfirm.getText().toString());
    }

    private void didRegister() {
        if (this.presenter != null) {
            this.presenter.doRegister(getInputPhoneNumber(),
                    this.editTextPassword.getText().toString(),
                    this.editTextSmsCaptcha.getText().toString(),
                    this.strSmsCaptchaId);
        }
    }

    private String getInputPhoneNumber() {
        return (this.registerActivity != null) ? this.registerActivity.getInputPhoneNumber() : null;
    }

    private String getMosaicPhoneNumber() {
        return this.strMosaicPhoneNumber;
    }

    private String getRemainderMessage() {
        return "短信验证码已经发送至您的手机：" + getInputPhoneNumber() + "，请注意查收并完成密码的设置。";
    }
}
