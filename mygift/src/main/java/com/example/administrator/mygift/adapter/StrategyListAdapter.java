package com.example.administrator.mygift.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mygift.R;
import com.example.administrator.mygift.bean.StrategyExpandBean;
import com.example.administrator.mygift.ui.myview.MyGridView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-3-23.
 */
public class StrategyListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<StrategyExpandBean.DataEntity.ChannelGroupsEntity> channelsEntities;

    public StrategyListAdapter(Context context, ArrayList<StrategyExpandBean.DataEntity.ChannelGroupsEntity> channelsEntities) {
        this.context = context;
        this.channelsEntities = channelsEntities;
    }

    @Override
    public int getCount() {
        if (channelsEntities == null) {
            return 0;
        } else {
            return channelsEntities.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return channelsEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StrategyExpandBean.DataEntity.ChannelGroupsEntity channelGroupsEntity = channelsEntities.get(position);
        ViewHolder viewHolder = null;
        if (viewHolder != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.strategy_list_item, null);
            viewHolder = new ViewHolder(convertView, channelGroupsEntity);
            convertView.setTag(viewHolder);
        }
        viewHolder.titleTv.setText(channelGroupsEntity.getName());

        return convertView;
    }

    class ViewHolder {
        TextView titleTv;
        MyGridView gridView;
        GridAdapter gridAdapter;
        ArrayList<StrategyExpandBean.DataEntity.ChannelGroupsEntity.ChannelsEntity> channelsEntities
                = new ArrayList<>();

        public ViewHolder(View view, StrategyExpandBean.DataEntity.ChannelGroupsEntity channelGroupsEntity) {
            titleTv = (TextView) view.findViewById(R.id.strategy_list_item_title_tv);
            gridView = (MyGridView) view.findViewById(R.id.strategy_list_item_gv);

            gridAdapter = new GridAdapter(channelGroupsEntity.getChannels());
            gridView.setAdapter(gridAdapter);
        }


        class GridAdapter extends BaseAdapter {
            List<StrategyExpandBean.DataEntity.ChannelGroupsEntity.ChannelsEntity> channelsEntities;

            public GridAdapter(List<StrategyExpandBean.DataEntity.ChannelGroupsEntity.ChannelsEntity>
                                       channelsEntities) {
                this.channelsEntities = channelsEntities;
            }

            @Override
            public int getCount() {
                if (channelsEntities == null) {
                    return 0;
                } else {
                    return channelsEntities.size();
                }
            }

            @Override
            public Object getItem(int position) {
                return channelsEntities.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                InViewHolder inViewHolder = null;
                if (convertView!=null){
                    inViewHolder = (InViewHolder) convertView.getTag();
                }else {
                    convertView = LayoutInflater.from(context).inflate(R.layout.strategy_list_girdview_item,null);
                    inViewHolder = new InViewHolder(convertView);
                    convertView.setTag(inViewHolder);
                }
                StrategyExpandBean.DataEntity.ChannelGroupsEntity.ChannelsEntity channelsEntity
                        = channelsEntities.get(position);
                inViewHolder.textView.setText(channelsEntity.getName());
                Picasso.with(context).load(channelsEntity.getIcon_url()).into(inViewHolder.imageView);
                return convertView;
            }

            class InViewHolder{
                ImageView imageView;
                TextView textView;

                public InViewHolder(View view){
                    imageView = (ImageView) view.findViewById(R.id.category_grid_item_img);
                    textView = (TextView) view.findViewById(R.id.category_grid_item_tv);
                }
            }
        }
    }
}
