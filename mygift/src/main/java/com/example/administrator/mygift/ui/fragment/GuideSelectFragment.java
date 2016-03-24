package com.example.administrator.mygift.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.administrator.mygift.R;
import com.example.administrator.mygift.adapter.GuideExpandListAdapter;
import com.example.administrator.mygift.adapter.GuideRecyclerAdapter;
import com.example.administrator.mygift.bean.GuideBannerBean;
import com.example.administrator.mygift.bean.GuideListBean;
import com.example.administrator.mygift.bean.GuideRecyleBean;
import com.example.administrator.mygift.http.IOkCallBack;
import com.example.administrator.mygift.http.OkHttpTool;
import com.example.administrator.mygift.http.UrlConfig;
import com.example.administrator.mygift.tools.DateFormatTool;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GuideSelectFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Bind(R.id.guide_list_view)
    ExpandableListView expandableListView;

    private View view;
    private View headerView;
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private ArrayList<String> recyleStringList = new ArrayList<>();
    private HeaderViewHolder headerViewHolder;
    private RecyclerView recyleView;
    private GuideRecyclerAdapter recyclerAdapter;
    private Context mContext;
    private List<String> timeList = new ArrayList<>();
    private Map<String, List<GuideListBean.DataEntity.ItemsEntity>> mExpandDatas = new HashMap<>();
    private GuideExpandListAdapter expandListAdapter = null;

    public GuideSelectFragment() {
    }

    public static GuideSelectFragment newInstance(String param1, String param2) {
        GuideSelectFragment fragment = new GuideSelectFragment();
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
        view = inflater.inflate(R.layout.fragment_select, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, view);

        setHeaderView();
        setExpandableListView();
        return view;
    }

    private void setHeaderView() {
        headerView = LayoutInflater.from(mContext).inflate(R.layout.guide_list_header, null);
        OkHttpTool.okGet(UrlConfig.GUIDE_SELECT_BANNER_URL, GuideBannerBean.class,
                new IOkCallBack<GuideBannerBean>() {
                    @Override
                    public void onSuccess(GuideBannerBean resultInfo) {
                        int size = resultInfo.getData().getBanners().size();
                        for (int i = 0; i < size; i++) {
                            stringArrayList.add(resultInfo.getData().getBanners().get(i).getWebp_url());
                        }
                        headerViewHolder = new HeaderViewHolder(headerView);
                        headerViewHolder.convenientBanner.setPages(new CBViewHolderCreator() {
                            @Override
                            public Object createHolder() {
                                return new BannerViewHolder();
                            }
                        }, stringArrayList)
                                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

                    }
                }, 3);
        OkHttpTool.okGet(UrlConfig.GUIDE_SELECT_HORIZENTAl_RECYLEVIEW_URL, GuideRecyleBean.class,
                new IOkCallBack<GuideRecyleBean>() {
                    @Override
                    public void onSuccess(GuideRecyleBean resultInfo) {
                        int size = resultInfo.getData().getSecondary_banners().size();
                        for (int i = 0; i < size; i++) {
                            recyleStringList.add(resultInfo.getData().getSecondary_banners().get(i).getImage_url());
                        }
                        recyclerAdapter.notifyDataSetChanged();
                    }
                }, 4);
        recyleView = (RecyclerView) headerView.findViewById(R.id.guide_list_header_recyleview);
        recyleView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        recyclerAdapter = new GuideRecyclerAdapter(mContext, recyleStringList);
        recyleView.setAdapter(recyclerAdapter);
        expandableListView.addHeaderView(headerView);
    }

    class BannerViewHolder implements Holder<String> {
        private ImageView bannerImg;

        @Override
        public View createView(Context context) {
            bannerImg = new ImageView(context);
            bannerImg.setScaleType(ImageView.ScaleType.FIT_XY);
            return bannerImg;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Picasso.with(context).load(data).into(bannerImg);
        }
    }

    class HeaderViewHolder {
        @Bind(R.id.guide_list_header_banner)
        ConvenientBanner convenientBanner;

        public HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private void setExpandableListView() {
        bindListViewAdapter();
        initListViewData();
    }

    private void initListViewData() {
        OkHttpTool.okGet(UrlConfig.GUIDE_SELECT_EXPANDLIST_URL, GuideListBean.class,
                new IOkCallBack<GuideListBean>() {
                    @Override
                    public void onSuccess(GuideListBean resultInfo) {
                        Log.i("songkai", "onSuccess: " + resultInfo.toString());
                        List<GuideListBean.DataEntity.ItemsEntity> itemsEntityList =
                                resultInfo.getData().getItems();
                        int size = itemsEntityList.size();
                        for (int i = 0; i < size; i++) {
                            GuideListBean.DataEntity.ItemsEntity entity = itemsEntityList.get(i);
                            String key = DateFormatTool.formatDate(entity.getPublished_at() * 1000L);
                            List<GuideListBean.DataEntity.ItemsEntity> itemsEntities1 =
                                    mExpandDatas.get(key);
                            if (itemsEntities1 != null) {
                                itemsEntities1.add(entity);
                            } else {
                                timeList.add(key);
                                itemsEntities1 = new ArrayList<>();
                                itemsEntities1.add(entity);
                                mExpandDatas.put(key, itemsEntities1);
                            }
                        }
                        expandListAdapter.notifyDataSetChanged();
                        for (int i = 0; i < 6; i++) {
                            expandableListView.expandGroup(i);
                        }
                    }

                }, 2);
    }

    private void bindListViewAdapter() {
        expandListAdapter = new GuideExpandListAdapter(getActivity(), timeList, mExpandDatas);
        expandableListView.setAdapter(expandListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
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
