package com.bus.tian.tianbus.view.help;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.TextLinkBean;
import com.bus.tian.tianbus.view.BaseFragment;
import com.bus.tian.tianbus.view.common.SingleTextAdapter;
import com.bus.tian.tianbus.view.common.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by hsg on 11/2/16.
 */

public class LawFragment extends BaseFragment {

    @BindView(R.id.list_view_tab_law)
    ListView listView;

    private SingleTextAdapter adapter;
    private List<TextLinkBean> dataList;

    public LawFragment() {
    }

    public static LawFragment getInstance() {
        return new LawFragment();
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_tab_law;
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


    @OnItemClick(R.id.list_view_tab_law)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (this.dataList != null && id >= 0 && id < this.dataList.size()) {
            WebActivity.actionStart(baseActivity, this.dataList.get((int) id));
        }
    }

    private void dataFactory() {
        TextLinkBean lawMaster = new TextLinkBean();
        lawMaster.setText("中华人民共和国宪法");
        lawMaster.setLink("http://qianxun.baidu.com/guizhang/index.html?query=%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E5%AE%AA%E6%B3%95&norm_id=1158746347&content=null");

        TextLinkBean lawCriminal = new TextLinkBean();
        lawCriminal.setText("中华人民共和国刑法");
        lawCriminal.setLink("http://qianxun.baidu.com/guizhang/index.html?query=%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E5%88%91%E6%B3%95&norm_id=3034657581&content=null");

        TextLinkBean lawTraffic = new TextLinkBean();
        lawTraffic.setText("中华人民共和国道路交通安全法");
        lawTraffic.setLink("http://qianxun.baidu.com/guizhang/index.html?query=%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E9%81%93%E8%B7%AF%E4%BA%A4%E9%80%9A%E5%AE%89%E5%85%A8%E6%B3%95&norm_id=2408220583&content=null");

        TextLinkBean lawSecurity = new TextLinkBean();
        lawSecurity.setText("中华人民共和国治安管理处罚法");
        lawSecurity.setLink("http://qianxun.baidu.com/guizhang/index.html?query=%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E6%B2%BB%E5%AE%89%E7%AE%A1%E7%90%86%E5%A4%84%E7%BD%9A%E6%B3%95&norm_id=2294665965&content=null");

        if (this.dataList != null) {
            this.dataList.clear();
            this.dataList.add(lawMaster);
            this.dataList.add(lawCriminal);
            this.dataList.add(lawTraffic);
            this.dataList.add(lawSecurity);
        }
    }
}
