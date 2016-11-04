package com.bus.tian.tianbus.view.help;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.contract.IHelpLawContract;
import com.bus.tian.tianbus.di.component.DaggerIHelpLawComponent;
import com.bus.tian.tianbus.di.component.DaggerINetCompoent;
import com.bus.tian.tianbus.di.module.HelpLawModule;
import com.bus.tian.tianbus.model.bean.TextLinkBean;
import com.bus.tian.tianbus.view.BaseFragment;
import com.bus.tian.tianbus.view.common.SingleTextAdapter;
import com.bus.tian.tianbus.view.common.WebActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by hsg on 11/2/16.
 */

public class LawFragment extends BaseFragment implements IHelpLawContract.IView {

    @BindView(R.id.list_view_tab_law)
    ListView listView;
    @Inject
    IHelpLawContract.IPresenter presenter;

    private SingleTextAdapter adapter;
    private List<TextLinkBean> dataList;

    public LawFragment() {
    }

    public static LawFragment getInstance() {
        return new LawFragment();
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
        return R.layout.fragment_tab_law;
    }

    @Override
    protected void initData() {
        DaggerIHelpLawComponent.builder()
                .iNetCompoent(DaggerINetCompoent.create())
                .helpLawModule(new HelpLawModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void onRelease() {
        if (this.presenter != null) {
            this.presenter.onRelease();
            this.presenter = null;
        }
    }

    @OnItemClick(R.id.list_view_tab_law)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if ((this.dataList != null) && (id >= 0) && (id < this.dataList.size())) {
            WebActivity.actionStart(baseActivity, this.dataList.get((int) id));
        }
    }

    @Override
    public void updateView(List<TextLinkBean> textLinkBeanList) {
        if (this.dataList != null) {
            this.dataList.clear();
        }
        this.dataList = textLinkBeanList;
        this.adapter = new SingleTextAdapter(baseActivity, R.layout.text_next_item, this.dataList);
        this.listView.setAdapter(this.adapter);
    }
}
