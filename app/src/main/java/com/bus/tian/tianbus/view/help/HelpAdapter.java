package com.bus.tian.tianbus.view.help;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hsg on 11/2/16.
 */

public class HelpAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String[] titles;

    public HelpAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragmentList != null ? this.fragmentList.get(position) : null;
    }

    @Override
    public int getCount() {
        return this.fragmentList != null ? this.fragmentList.size() : 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeViewAt(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (((this.titles != null) && (position < this.titles.length)) ? this.titles[position] : null);
    }
}
