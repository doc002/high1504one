package com.example.administrator.mygift.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.administrator.mygift.R;
import com.example.administrator.mygift.adapter.HotGridAdapter;
import com.example.administrator.mygift.bean.HotBean;
import com.example.administrator.mygift.http.IOkCallBack;
import com.example.administrator.mygift.http.OkHttpTool;
import com.example.administrator.mygift.http.UrlConfig;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HotFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public HotFragment() {

    }

    public static HotFragment newInstance(String param1, String param2) {
        HotFragment fragment = new HotFragment();
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

    private View view;
    private Context mContext;

    @Bind(R.id.hot_gridview)
    GridView hotGv;
    private ArrayList<HotBean.HotEntity.ItemsEntity> itemsEntities = new ArrayList<>();
    private HotGridAdapter hotGridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.bind(this,view);
        mContext = getActivity();

        initData();
        bindAdapter();
        setListener();

        return view;
    }

    private void initData() {
        OkHttpTool.okGet(UrlConfig.HOT_GRIDVIEW_URL, HotBean.class, new IOkCallBack<HotBean>() {
            @Override
            public void onSuccess(HotBean resultInfo) {
                itemsEntities.addAll(resultInfo.getData().getItems());
            }
        },4);
    }

    private void bindAdapter() {
        hotGridAdapter = new HotGridAdapter(mContext,itemsEntities);
        hotGv.setAdapter(hotGridAdapter);
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
