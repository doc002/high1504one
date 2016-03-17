package com.example.administrator.mygift.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.mygift.bean.GuideTabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-3-14.
 */
public class GuideViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private List<GuideTabBean.DataEntity.ChannelsEntity> channelsEntities;
    public GuideViewPagerAdapter(FragmentManager fm,
                                 List<GuideTabBean.DataEntity.ChannelsEntity> channelsEntities,
                                 ArrayList<Fragment> fragments) {
        super(fm);
        this.channelsEntities = channelsEntities;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return channelsEntities.get(position).getName();
    }
}
