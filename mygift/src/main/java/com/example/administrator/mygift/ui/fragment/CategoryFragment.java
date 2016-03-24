package com.example.administrator.mygift.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.mygift.R;
import com.example.administrator.mygift.adapter.CategoryViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-3-22.
 */
public class CategoryFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private View view;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private StrategyFragment strategyFragment;
    private GiftFragment giftFragment;
    private RadioGroup radioGroup;
    private CategoryViewPagerAdapter viewPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category,null);
        initView();
        initData();
        bindAdapter();
        setListener();
        return view;
    }

    private void initView() {
        radioGroup = (RadioGroup) view.findViewById(R.id.category_top_rb);
        viewPager = (ViewPager) view.findViewById(R.id.category_viewpager);
    }

    private void initData() {
        strategyFragment = new StrategyFragment();
        giftFragment = new GiftFragment();
        fragmentArrayList.add(strategyFragment);
        fragmentArrayList.add(giftFragment);
    }

    private void bindAdapter() {
        viewPagerAdapter = new CategoryViewPagerAdapter(getChildFragmentManager(),fragmentArrayList);
        viewPager.setAdapter(viewPagerAdapter);
    }


    private void setListener() {
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.category_strategy_rb:
                viewPager.setCurrentItem(0);
                break;
            case R.id.category_gift_rb:
                viewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(position);
        radioButton.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
