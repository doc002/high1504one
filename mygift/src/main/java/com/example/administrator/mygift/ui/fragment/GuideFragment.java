package com.example.administrator.mygift.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mygift.R;
import com.example.administrator.mygift.adapter.GuideViewPagerAdapter;
import com.example.administrator.mygift.bean.GuideTabBean;
import com.example.administrator.mygift.http.IOkCallBack;
import com.example.administrator.mygift.http.OkHttpTool;
import com.example.administrator.mygift.http.UrlConfig;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GuideFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GuideFragment() {
    }

    private View view;
    private Context mContext;
    @Bind(R.id.guide_tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.guide_view_pager)
    ViewPager mViewPager;
    private List<GuideTabBean.DataEntity.ChannelsEntity> channelsEntities = new ArrayList<>();
    private ArrayList<String> tabString = new ArrayList<>();
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private GuideSelectFragment selectFragment = GuideSelectFragment.newInstance(null, null);
    private GuideViewPagerAdapter guideViewPagerAdapter;

    private HotFragment hotFragment = HotFragment.newInstance(null, null);

    public static GuideFragment newInstance(String param1, String param2) {
        GuideFragment fragment = new GuideFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_guide, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, view);
        initView();
        initData();
        bindAdapter();
        setListener();
        return view;
    }


    private void initView() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initData() {
        OkHttpTool.okGet(UrlConfig.GUIDE_SELECT_TAB_URL,
                GuideTabBean.class, new IOkCallBack<GuideTabBean>() {

            @Override
            public void onSuccess(GuideTabBean resultInfo) {
                //接受到服务器返回的数据
                channelsEntities.addAll(resultInfo.getData().getChannels());
                guideViewPagerAdapter.notifyDataSetChanged();
                setTabView();

            }
        }, 1);
        fragmentArrayList.add(selectFragment);
        for (int i = 0; i < 16; i++) {
            fragmentArrayList.add(HotFragment.newInstance(null, null));
        }
        int size = channelsEntities.size();
        for (int i = 0; i < size; i++) {
            tabString.add(channelsEntities.get(i).getName());
        }
    }

    private void bindAdapter() {
        guideViewPagerAdapter = new GuideViewPagerAdapter(getChildFragmentManager(),
                channelsEntities, fragmentArrayList);
        mViewPager.setAdapter(guideViewPagerAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void setTabView() {
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setListener() {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
