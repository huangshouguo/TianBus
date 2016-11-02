package com.bus.tian.tianbus.view.help;

import android.widget.ListView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.TextLinkBean;
import com.bus.tian.tianbus.view.BaseFragment;
import com.bus.tian.tianbus.view.common.SingleTextAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hsg on 11/2/16.
 */

public class BusinessFragment extends BaseFragment {

    @BindView(R.id.list_view_tab_business)
    ListView listView;

    private SingleTextAdapter adapter;
    private List<TextLinkBean> dataList;

    public BusinessFragment() {
    }

    public static BusinessFragment getInstance() {
        return new BusinessFragment();
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_tab_business;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);

        this.dataList = new ArrayList<>();
        dataFactory();
        this.adapter = new SingleTextAdapter(baseActivity, R.layout.text_next_item, this.dataList);
        this.listView.setAdapter(this.adapter);
    }

    @Override
    protected void onRelease() {
    }

    private void dataFactory(){
        TextLinkBean lawMaster = new TextLinkBean();
        lawMaster.setText("中华人民共和国宪法");
        TextLinkBean lawCriminal = new TextLinkBean();
        lawCriminal.setText("中华人民共和国刑法");
        TextLinkBean lawTraffic = new TextLinkBean();
        lawTraffic.setText("中华人民共和国道路交通安全法");
        TextLinkBean lawSecurity = new TextLinkBean();
        lawSecurity.setText("中华人民共和国治安管理处罚法");

        if (this.dataList != null){
            this.dataList.clear();
            this.dataList.add(lawMaster);
            this.dataList.add(lawCriminal);
            this.dataList.add(lawTraffic);
            this.dataList.add(lawSecurity);
        }
    }
}
