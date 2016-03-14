package com.example.administrator.hightone;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.hightone.adapter.FragmentAdapter;
import com.example.administrator.hightone.fragment.BaseFragment;
import com.example.administrator.hightone.fragment.FoodFragment;
import com.example.administrator.hightone.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.main_tab_layout);

        mTabLayout.addTab(mTabLayout.newTab().setText("精选"));
        mTabLayout.addTab(mTabLayout.newTab().setText("海淘"));
        mTabLayout.addTab(mTabLayout.newTab().setText("涨姿势"));
        mTabLayout.addTab(mTabLayout.newTab().setText("创意生活"));
        mTabLayout.addTab(mTabLayout.newTab().setText("纪念日"));
        mTabLayout.addTab(mTabLayout.newTab().setText("美食"));
        mTabLayout.addTab(mTabLayout.newTab().setText("生日"));
        mTabLayout.addTab(mTabLayout.newTab().setText("数码"));
        mTabLayout.addTab(mTabLayout.newTab().setText("爱运动"));
        mTabLayout.addTab(mTabLayout.newTab().setText("科技范"));
        mTabLayout.addTab(mTabLayout.newTab().setText("家居"));
        mTabLayout.addTab(mTabLayout.newTab().setText("设计感"));
        mTabLayout.addTab(mTabLayout.newTab().setText("礼物"));
        mTabLayout.addTab(mTabLayout.newTab().setText("送基友"));
        mTabLayout.addTab(mTabLayout.newTab().setText("送爸妈"));
        mTabLayout.addTab(mTabLayout.newTab().setText("爱动漫"));
        mTabLayout.addTab(mTabLayout.newTab().setText("送女票"));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        setViewPager();
        tabView();
    }

    private void tabView(){
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void setViewPager(){
        fragmentList.add(HomeFragment.newInstance(null,null));
        fragmentList.add(FoodFragment.newInstance(null,null));
        adapter = new FragmentAdapter(getSupportFragmentManager(),fragmentList);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
