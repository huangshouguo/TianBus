package com.bus.tian.tianbus.view;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.view.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tool_bar_main)
    Toolbar toolBar;
    @BindView(R.id.layout_content_main)
    FrameLayout layoutContent;
    @BindView(R.id.navigation_view_main)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initToolBar();
        initDrawerNav();
    }

    @Override
    protected void onRelease() {

    }

    private void initToolBar() {
        setSupportActionBar(this.toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("test");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_nav);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initDrawerNav() {
        this.drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        setupDrawerContent(this.navigationView);
        this.navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: onclick header");
                startLogin();
            }
        });
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
//                drawerLayout.closeDrawers();
                return true;
            });
        }
    }

    private void startLogin(){
        LoginActivity.actionStart(context);
    }
}
