package com.bus.tian.tianbus.view.login;


import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IFinishRegisterContract;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.view.BaseFragment;

public class FinishRegisterFragment extends BaseFragment implements IFinishRegisterContract.IView{

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_register_finish;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onRelease() {

    }

    @Override
    public void updateSmsCaptchaView(UserBean userBean) {

    }

    @Override
    public void updateRegisterView(UserBean userBean) {

    }
}
