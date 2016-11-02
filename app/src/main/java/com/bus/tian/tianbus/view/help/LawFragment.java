package com.bus.tian.tianbus.view.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hsg on 11/2/16.
 */

public class LawFragment extends BaseFragment {

    @BindView(R.id.list_view_tab_law)
    ListView listView;

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
    }

    @Override
    protected void onRelease() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }
}
