package com.example.administrator.mygift.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.mygift.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-3-23.
 */
public class StrategyRecyleAdapter extends RecyclerView.Adapter<StrategyRecyleAdapter.RecyleViewHolder> {
    private Context context;
    private ArrayList<String> stringHeader;

    public StrategyRecyleAdapter(Context context, ArrayList<String> stringHeader) {
        this.context = context;
        this.stringHeader = stringHeader;
    }

    @Override
    public RecyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_strategy_header_item,null);
        return new RecyleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyleViewHolder holder, int position) {
        Picasso.with(context).load(stringHeader.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return stringHeader.size();
    }

    class RecyleViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;

        public RecyleViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.strategy_header_item_img);
        }
    }
}
