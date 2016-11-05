package com.bus.tian.tianbus.view;

import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import com.bus.tian.tianbus.view.about.AboutFragment;
import com.bus.tian.tianbus.view.forum.ForumActivity;
import com.bus.tian.tianbus.view.help.HelpFragment;
import com.bus.tian.tianbus.view.home.HomeFragment;
import com.bus.tian.tianbus.view.login.LoginActivity;
import com.bus.tian.tianbus.view.me.MeFragment;
import com.bus.tian.tianbus.view.setting.SettingFragment;

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
    @BindView(R.id.fab_main)
    FloatingActionButton fabMain;
    @BindView(R.id.coordinator_main)
    CoordinatorLayout coordinatorMain;

    private HeaderHolder headerHolder;
    private BaseFragment preFragment;
    private HomeFragment homeFragment;
    private HelpFragment helpFragment;
    private MeFragment meFragment;
    private SettingFragment settingFragment;
    private AboutFragment aboutFragment;

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
        initFloatActionBar();
        showHomeFragment();
    }

    private void initFloatActionBar() {
        this.fabMain.setOnClickListener(v -> showForum());
    }

    private void showForum() {
        ForumActivity.actionStart(this);
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
            header.setOnClickListener(v -> {
                if (!UserManager.getInstance().isLogined()) {
                    startLoginActivity();
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.drawerLayout.closeDrawers();
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
                    case R.id.menu_home:
                        showHomeFragment();
                        break;
                    case R.id.menu_help:
                        showHelpFragment();
                        break;
                    case R.id.menu_me:
                        showMeFragment();
                        break;
                    case R.id.menu_setting:
                        showSettingFragment();
                        break;
                    case R.id.menu_about_us:
                        showAboutFragment();
                        break;
                    default:
                        break;
                }

                // Close the navigation drawer when an item is selected.
                drawerLayout.closeDrawers();
                return false;
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

        showMeFragment();
    }

    @Override
    public void updateViewOnCancelLogin() {
        showHomeFragment();
    }

    @Override
    public void updateViewOnLogout() {
        if (this.headerHolder != null) {
            this.headerHolder.updateHeaderViewOnLogout();
        }

        showHomeFragment();
    }

    private void showHomeFragment() {
        if (this.homeFragment == null) {
            this.homeFragment = new HomeFragment();
        }

        updateFragment(this.homeFragment, this.preFragment);
        this.navigationView.setCheckedItem(R.id.menu_home);
        this.updateActionBarTitle(getString(R.string.text_home));
    }

    private void showHelpFragment() {
        if (this.helpFragment == null) {
            this.helpFragment = new HelpFragment();
        }

        updateFragment(this.helpFragment, this.preFragment);
        this.navigationView.setCheckedItem(R.id.menu_help);
        this.updateActionBarTitle(getString(R.string.text_query));
    }

    private void showMeFragment() {
        if (!UserManager.getInstance().isLogined()) {
            startLoginActivity();
            return;
        }
        if (this.meFragment == null) {
            this.meFragment = new MeFragment();
        }

        updateFragment(this.meFragment, this.preFragment);
        this.navigationView.setCheckedItem(R.id.menu_me);
        updateActionBarTitle(getString(R.string.text_me));
    }

    private void showSettingFragment() {
        if (this.settingFragment == null){
            this.settingFragment = SettingFragment.getInstance();
        }

        updateFragment(this.settingFragment, this.preFragment);
        this.navigationView.setCheckedItem(R.id.menu_setting);
        updateActionBarTitle(getString(R.string.text_setting));
    }

    private void showAboutFragment() {
        if (this.aboutFragment == null){
            this.aboutFragment = AboutFragment.getInstance();
        }

        updateFragment(this.aboutFragment, this.preFragment);
        this.navigationView.setCheckedItem(R.id.menu_about_us);
        updateActionBarTitle(getString(R.string.text_about_us));
    }

    private void updateActionBarTitle(final String title) {
        if (!TextUtils.isEmpty(title)) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void updateFragment(final BaseFragment newFragment, BaseFragment oldFragment) {
        //1. check null
        if (newFragment == null) {
            return;
        }

        //2. check the same fragment
        if (newFragment == oldFragment) {
            return;
        }

        //3. update
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (oldFragment != null) {
            fragmentTransaction.remove(oldFragment);
        }
        fragmentTransaction.replace(R.id.layout_content_main, newFragment);
        fragmentTransaction.commitAllowingStateLoss();

        //4. record selection
        oldFragment = newFragment;
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
            if (UserManager.getInstance().isLogined()) {
                updateHeaderViewOnLogin();
            } else {
                updateHeaderViewOnLogout();
            }
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
