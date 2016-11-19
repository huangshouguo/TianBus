package com.bus.tian.tianbus.view.me;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IMeContract;
import com.bus.tian.tianbus.di.component.DaggerIMeComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.MeModule;
import com.bus.tian.tianbus.model.bean.ImageTextBean;
import com.bus.tian.tianbus.util.UserManager;
import com.bus.tian.tianbus.view.BaseFragment;
import com.bus.tian.tianbus.view.MainActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

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
    private MeAdapter adapter;
    private List<ImageTextBean> dataList;

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
        this.initRecyclerView();

        if (this.presenter != null) {
            this.presenter.loadData();
        }
    }

    @Override
    protected void onRelease() {
        if (this.presenter != null) {
            this.presenter.onRelease();
            this.presenter = null;
        }
    }

    @Override
    public void updateView(List<ImageTextBean> data) {
        if (this.dataList != null) {
            this.dataList.clear();
            this.dataList.addAll(data);
            if (this.adapter != null) {
                this.adapter.notifyDataSetChanged();
            }
        }
    }

    private void initRecyclerView() {
        this.dataList = new ArrayList<>();
        this.adapter = new MeAdapter(R.layout.list_item_me, this.dataList);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this.mainActivity, 2));
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (i) {
                    case 0:
                        MeDetailActivity.actionStart(mainActivity, MeDetailActivity.SOURCE_IMAGE_TAG);
                        break;
                    case 1:
                        MeDetailActivity.actionStart(mainActivity, MeDetailActivity.SOURCE_VIDEO_TAG);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
