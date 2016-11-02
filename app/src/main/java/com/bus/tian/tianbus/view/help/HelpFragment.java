package com.bus.tian.tianbus.view.help;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HelpFragment extends BaseFragment {

    @BindView(R.id.tab_layout_help)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_help)
    ViewPager viewPager;
    @BindString(R.string.text_tab_law)
    String strTab1Title;
    @BindString(R.string.text_tab_business)
    String strTab2Title;

    private List<Fragment> fragmentList = new ArrayList<>();
    private HelpAdapter adapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_help;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, rootView);
        String[] titles = new String[]{this.strTab1Title, this.strTab2Title};
        this.fragmentList.clear();
        this.fragmentList.add(LawFragment.getInstance());
        this.fragmentList.add(BusinessFragment.getInstance());
        this.adapter = new HelpAdapter(getChildFragmentManager(), this.fragmentList, titles);
        this.viewPager.setAdapter(this.adapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    @Override
    protected void onRelease() {

    }
}
