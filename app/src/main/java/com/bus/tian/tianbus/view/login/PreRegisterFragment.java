package com.bus.tian.tianbus.view.login;


import android.widget.Button;
import android.widget.EditText;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IPreRegisterContract;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.component.DaggerIPreRegisterComponent;
import com.bus.tian.tianbus.di.module.PreRegisterModule;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.view.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreRegisterFragment extends BaseFragment implements IPreRegisterContract.IView {

    @BindView(R.id.edit_text_phone_number)
    EditText editTextPhoneNumber;
    @BindView(R.id.btn_next_register)
    Button btnNextRegister;

    @Inject
    IPreRegisterContract.IPresenter presenter;

    private RegisterActivity registerActivity;
    private String strInputPhoneNumber;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_register_pre;
    }

    @Override
    protected void initData() {
        DaggerIPreRegisterComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .preRegisterModule(new PreRegisterModule(this))
                .build()
                .inject(this);

        this.registerActivity = (RegisterActivity) getActivity();
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);
        if (this.registerActivity != null) {
            this.editTextPhoneNumber.setText(this.registerActivity.getInputPhoneNumber());
        }
    }

    @Override
    protected void onRelease() {
        if (this.presenter != null) {
            this.presenter.onRelease();
        }
    }

    @Override
    public void updateView(UserBean userBean) {
        showNext();
    }


    @OnClick(R.id.btn_next_register)
    public void onClick() {
        findUserByPhoneNumber();
    }

    private void findUserByPhoneNumber() {
        if (this.presenter != null) {
            this.presenter.findUserByPhoneNumber(this.editTextPhoneNumber.getText().toString());
            this.strInputPhoneNumber = this.editTextPhoneNumber.getText().toString();
        }
    }

    private void showNext() {
        if (this.registerActivity != null) {
            this.registerActivity.setInputPhoneNumber(this.strInputPhoneNumber);
            this.registerActivity.showFinishFragment();
        }
    }
}
