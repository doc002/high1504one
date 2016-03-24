package com.example.administrator.mygift.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.mygift.R;
import com.example.administrator.mygift.adapter.StrategyListAdapter;
import com.example.administrator.mygift.adapter.StrategyRecyleAdapter;
import com.example.administrator.mygift.bean.StrategyExpandBean;
import com.example.administrator.mygift.bean.StrategyHeaderBean;
import com.example.administrator.mygift.http.IOkCallBack;
import com.example.administrator.mygift.http.OkHttpTool;
import com.example.administrator.mygift.http.UrlConfig;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-3-23.
 */
public class StrategyFragment extends BaseFragment {
    private View view;
    private View headerView;
    private ListView listView;
    private ArrayList<String> stringList = new ArrayList<>();
    private ArrayList<StrategyExpandBean.DataEntity.ChannelGroupsEntity> channelsEntities
            = new ArrayList<>();
    private StrategyListAdapter strategyListAdapter;

    private RecyclerView recyclerView;
    private ArrayList<String> stringHeader = new ArrayList<>();
    private StrategyRecyleAdapter recyleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_strategy, container, false);
        headerView = inflater.inflate(R.layout.view_strategy_header, null);
        initView();
        initData();
        bindAdapter();
        setListener();
        return view;
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.strategy_list_view);
        recyclerView = (RecyclerView) headerView.findViewById(R.id.strategy_header_recyleview);
    }

    private void initData() {
        OkHttpTool.okGet(UrlConfig.STRATEGY_EXPANDLIST_URL, StrategyExpandBean.class,
                new IOkCallBack<StrategyExpandBean>() {
                    @Override
                    public void onSuccess(StrategyExpandBean resultInfo) {
                        int size = resultInfo.getData().getChannel_groups().size();
                        for (int i = 0; i < size; i++) {
                            stringList.add(resultInfo.getData().getChannel_groups().get(i).getName());
                        }
                        channelsEntities.addAll(resultInfo.getData().getChannel_groups());
                        strategyListAdapter.notifyDataSetChanged();
                    }
                }, 5);

        OkHttpTool.okGet(UrlConfig.STRATEGY_HEADER_URL, StrategyHeaderBean.class,
                new IOkCallBack<StrategyHeaderBean>() {
                    @Override
                    public void onSuccess(StrategyHeaderBean resultInfo) {
                        int size = resultInfo.getData().getCollections().size();
                        for (int i = 0; i < size; i++) {
                            stringHeader.add(resultInfo.getData().getCollections().get(i).getBanner_image_url());
                        }
                        recyleAdapter.notifyDataSetChanged();
                    }
                }, 6);
    }

    private void bindAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyleAdapter = new StrategyRecyleAdapter(getActivity(), stringHeader);
        recyclerView.setAdapter(recyleAdapter);
        listView.addHeaderView(headerView);
        strategyListAdapter = new StrategyListAdapter(getActivity(), channelsEntities);
        listView.setAdapter(strategyListAdapter);

    }

    private void setListener() {

    }
}
