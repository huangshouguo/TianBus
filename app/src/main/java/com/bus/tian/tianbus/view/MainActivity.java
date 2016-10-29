package com.bus.tian.tianbus.view;

import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IMainContract;
import com.bus.tian.tianbus.di.component.DaggerIMainComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.MainModule;
import com.bus.tian.tianbus.model.bean.UserBean;
import com.bus.tian.tianbus.util.UserManager;
import com.bus.tian.tianbus.view.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainContract.IView {

    @BindView(R.id.tool_bar_main)
    Toolbar toolBar;
    @BindView(R.id.layout_content_main)
    FrameLayout layoutContent;
    @BindView(R.id.navigation_view_main)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindString(R.string.app_name)
    String strAppName;

    @Inject
    IMainContract.IPresenter presenter;

    private HeaderHolder headerHolder;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        DaggerIMainComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        if (this.presenter != null) {
            this.presenter.initRxEvent();
        }
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolBar();
        initDrawerNav();
    }

    @Override
    protected void onRelease() {
        if (this.presenter != null) {
            this.presenter.onRelease();
            this.presenter = null;
        }
    }

    private void initToolBar() {
        setSupportActionBar(this.toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(strAppName);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_nav);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initDrawerNav() {
        this.drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        setupDrawerContent(this.navigationView);
        View header = this.navigationView.getHeaderView(0);
        if (header != null) {
            this.headerHolder = new HeaderHolder(header);
            header.setOnClickListener(v -> startLoginActivity());
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(item -> {
                switch (item.getItemId()) {
                    case R.id.menu_bout_us:
                        break;
                    case R.id.menu_help:
                        break;
                    default:
                        break;
                }

                // Close the navigation drawer when an item is selected.
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                return true;
            });
        }
    }

    @Override
    public void startLoginActivity() {
        LoginActivity.actionStart(context);
    }

    @Override
    public void updateViewOnLogin() {
        if (this.headerHolder != null) {
            this.headerHolder.updateHeaderViewOnLogin();
        }
    }

    @Override
    public void updateViewOnLogout() {
        if (this.headerHolder != null) {
            this.headerHolder.updateHeaderViewOnLogout();
        }
    }

    static class HeaderHolder {
        @BindView(R.id.image_nav_header)
        ImageView imgHeader;
        @BindView(R.id.text_nav_header)
        TextView textTitle;
        @BindDrawable(R.drawable.logo)
        Drawable drawableLogo;
        @BindDrawable(R.drawable.user)
        Drawable drawableUser;
        @BindString(R.string.text_login_onclick)
        String textLoginOnClick;

        public HeaderHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void updateHeaderViewOnLogin() {
            UserBean userBean = UserManager.getInstance().getUserOfLogined();
            if (userBean != null) {
                this.textTitle.setText(userBean.getPhone());
                this.imgHeader.setImageDrawable(drawableUser);
            }
        }

        public void updateHeaderViewOnLogout() {
            this.textTitle.setText(textLoginOnClick);
            this.imgHeader.setImageDrawable(drawableLogo);
        }
    }
}
