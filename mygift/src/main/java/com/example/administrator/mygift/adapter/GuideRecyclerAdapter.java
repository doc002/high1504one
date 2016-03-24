package com.example.administrator.mygift.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.mygift.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-3-17.
 */

public class GuideRecyclerAdapter extends RecyclerView.Adapter<GuideRecyclerAdapter.RecyleViewHolder> {
    private Context mContext;
    private ArrayList<String> stringArrayList = new ArrayList<>();

    public GuideRecyclerAdapter(Context mContext, ArrayList<String> stringArrayList) {
        this.mContext = mContext;
        this.stringArrayList = stringArrayList;
    }

    /**
     * 创建ViewHolder对象,并且对Item的布局进行初始化
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载Item布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.guide_recyle_item, null);
        return new RecyleViewHolder(view);
    }

    MyListener myListener = new MyListener();

    /**
     * 此方法是给Item布局中的控件赋值
     * 参数一：onCreateViewHolder方法返回的ViewHolder对象
     * 参数二：Item的索引。
     */
    @Override
    public void onBindViewHolder(RecyleViewHolder holder, int position) {
        Picasso.with(mContext).load(stringArrayList.get(position)).into(holder.itemImg);
        holder.itemImg.setOnClickListener(myListener);
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    class RecyleViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImg;

        public RecyleViewHolder(View itemView) {
            super(itemView);
            itemImg = (ImageView) itemView.findViewById(R.id.guide_recyle_img);
        }
    }

    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "------", Toast.LENGTH_SHORT).show();
        }
    }
}
