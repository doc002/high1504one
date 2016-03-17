package com.example.administrator.mygift.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mygift.R;
import com.example.administrator.mygift.bean.GuideListBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 16-3-15.
 */
public class GuideExpandListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> timeList;
    private Map<String, List<GuideListBean.DataEntity.ItemsEntity>> mExpandDatas;

    public GuideExpandListAdapter(Context context, List<String> timeList, Map<String,
            List<GuideListBean.DataEntity.ItemsEntity>> mExpandDatas) {
        this.context = context;
        this.timeList = timeList;
        this.mExpandDatas = mExpandDatas;
    }

    @Override
    public int getGroupCount() {
        if (timeList == null) {
            return 0;
        } else {
            return timeList.size();
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mExpandDatas != null && timeList != null && timeList.size() > groupPosition
                && mExpandDatas.get(timeList.get(groupPosition)) != null) {
            return mExpandDatas.get(timeList.get(groupPosition)).size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    /**
     * 创建Group的布局样式
     *
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView != null) {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.guide_list_item_group, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }
        groupViewHolder.groupTv.setText(timeList.get(groupPosition));
        return convertView;
    }

    class GroupViewHolder {
        @Bind(R.id.guide_list_item_group_tv)
        TextView groupTv;

        public GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView != null) {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.guide_list_item, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        }
        GuideListBean.DataEntity.ItemsEntity itemsEntity = mExpandDatas
                .get(timeList.get(groupPosition)).get(childPosition);
        childViewHolder.countTxt.setText(""+itemsEntity.getLikes_count());
        childViewHolder.titleTxt.setText(itemsEntity.getTitle());
        Picasso.with(context).load(itemsEntity.getCover_image_url()).into(childViewHolder.iconImg);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class ChildViewHolder {
        @Bind(R.id.guide_list_item_img)
        ImageView iconImg;
        @Bind(R.id.guide_list_item_title_tv)
        TextView titleTxt;
        @Bind(R.id.guide_list_item_countbg_tv)
        TextView countTxt;

        public ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
