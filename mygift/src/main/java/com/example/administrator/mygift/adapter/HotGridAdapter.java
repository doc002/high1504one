package com.example.administrator.mygift.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mygift.R;
import com.example.administrator.mygift.bean.HotBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 16-3-22.
 */
public class HotGridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HotBean.HotEntity.ItemsEntity> itemsEntities;

    public HotGridAdapter(Context context, ArrayList<HotBean.HotEntity.ItemsEntity> itemsEntities) {
        this.context = context;
        this.itemsEntities = itemsEntities;
    }

    @Override
    public int getCount() {
        if (itemsEntities == null) {
            return 0;
        } else {
            return itemsEntities.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return itemsEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        HotBean.HotEntity.ItemsEntity itemsEntity = itemsEntities.get(position);
        viewHolder.nameTv.setText(itemsEntity.getData().getName());
        viewHolder.priceTv.setText(""+itemsEntity.getData().getPrice());
        viewHolder.likeCountTv.setText(""+itemsEntity.getData().getFavorites_count());
        Picasso.with(context).load(itemsEntity.getData().getCover_image_url()).into(viewHolder.iconImg);
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.hot_item_img)
        ImageView iconImg;
        @Bind(R.id.hot_item_name_tv)
        TextView nameTv;
        @Bind(R.id.hot_item_price_tv)
        TextView priceTv;
        @Bind(R.id.hot_item_like_tv)
        TextView likeCountTv;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
