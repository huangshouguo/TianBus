package com.bus.tian.tianbus.view.login;


import android.widget.Button;
import android.widget.EditText;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IPreRegisterContract;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreRegisterFragment extends BaseFragment implements IPreRegisterContract.IView {

    @BindView(R.id.edit_text_phone_number)
    EditText editTextPhoneNumber;
    @BindView(R.id.btn_next_register)
    Button btnNextRegister;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_register_pre;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void onRelease() {

    }

    @Override
    public void updateView(UserBean userBean) {

    }


    @OnClick(R.id.btn_next_register)
    public void onClick() {
    }
}
