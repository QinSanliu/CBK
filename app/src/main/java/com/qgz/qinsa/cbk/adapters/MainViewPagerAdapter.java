package com.qgz.qinsa.cbk.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by qinsa on 2016/11/14.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String[] tabTitles;

    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] tabTitles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tabTitles = tabTitles;
    }

    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }
}
