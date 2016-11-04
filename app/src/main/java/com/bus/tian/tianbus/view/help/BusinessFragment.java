package com.bus.tian.tianbus.view.help;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IHelpBizContract;
import com.bus.tian.tianbus.di.component.DaggerIHelpBizComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.HelpBizModule;
import com.bus.tian.tianbus.model.bean.HelpBizBean;
import com.bus.tian.tianbus.view.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hsg on 11/2/16.
 */

public class BusinessFragment extends BaseFragment implements IHelpBizContract.IView {

    @BindView(R.id.recycler_view_tab_business)
    RecyclerView recyclerView;

    @Inject
    IHelpBizContract.IPresenter presenter;

    private List<HelpBizBean> dataList;
    private BusinessAdapter adapter;

    public BusinessFragment() {
    }

    public static BusinessFragment getInstance() {
        return new BusinessFragment();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (this.presenter != null) {
            this.presenter.loadData();
        }
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_tab_business;
    }

    @Override
    protected void initData() {
        DaggerIHelpBizComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .helpBizModule(new HelpBizModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void onRelease() {
        if (presenter != null) {
            presenter.onRelease();
            presenter = null;
        }
    }

    @Override
    public void updateView(List<HelpBizBean> helpBizBeanList) {
        if (this.dataList != null) {
            this.dataList.clear();
        }
        this.dataList = helpBizBeanList;
        this.recyclerView.setLayoutManager(new LinearLayoutManager(baseActivity));
        this.adapter = new BusinessAdapter(R.layout.list_section_item, R.layout.list_section_header, this.dataList);
        this.recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Log.e(TAG, "SimpleOnItemClick: i = " + i);
            }
        });

        this.recyclerView.setAdapter(this.adapter);
    }
}
