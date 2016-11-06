package com.bus.tian.tianbus.view.me;


import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IMeContract;
import com.bus.tian.tianbus.di.component.DaggerIMeComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.MeModule;
import com.bus.tian.tianbus.util.UserManager;
import com.bus.tian.tianbus.view.BaseFragment;
import com.bus.tian.tianbus.view.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MeFragment extends BaseFragment implements IMeContract.IView {

    @Inject
    IMeContract.IPresenter presenter;
    @BindView(R.id.text_me_user)
    TextView textUser;
    @BindView(R.id.recycler_view_me)
    RecyclerView recyclerView;

    private MainActivity mainActivity;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.me_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_logout:
                if (this.presenter != null) {
                    this.presenter.logout();
                }
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
        DaggerIMeComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .meModule(new MeModule(this))
                .build()
                .inject(this);

        this.mainActivity = (MainActivity) baseActivity;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);
        this.textUser.setText(UserManager.getInstance().getUserOfLogined().getPhone());
        setHasOptionsMenu(true);

    }

    @Override
    protected void onRelease() {
        if (this.presenter != null) {
            this.presenter.onRelease();
            this.presenter = null;
        }
    }

    @Override
    public void updateView() {
    }
}
