package com.example.lovestou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lovestou.R;
import com.example.lovestou.activity.NewsWebActivity;
import com.example.lovestou.bean.DataBean;
import com.example.lovestou.utils.NewsInterface;

import java.util.ArrayList;
import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.RecyclerHolder> {
    private Context mContext;
    private List<DataBean.ItemsBean> dataList = new ArrayList<>();

    public CollectionAdapter(Context mContext, List<DataBean.ItemsBean> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    public void addData(List<DataBean.ItemsBean> dataList) {
        if (null != dataList) {
            this.dataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    public void setData(List<DataBean.ItemsBean> dataList) {
        if (null != dataList) {
            this.dataList.clear();
            this.dataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        DataBean.ItemsBean dataBean = dataList.get(position);
        holder.item_title.setText(dataBean.getTitle());
        holder.item_clicks.setText(dataBean.getClicks() + " 阅读");
        holder.item_time.setText(dataBean.getAddTime());
        Glide
                .with(mContext)
                .load(dataBean.getImg())
                .placeholder(R.drawable.error)
                .into(holder.iv_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsWebActivity.class);
                DataBean.ItemsBean itemsBean = dataList.get(position);
                NewsWebActivity.itemsBean = itemsBean;
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        public TextView item_title, item_clicks, item_time;
        public ImageView iv_img;

        private RecyclerHolder(View itemView) {
            super(itemView);
            item_title = itemView.findViewById(R.id.item_title);
            item_clicks = itemView.findViewById(R.id.item_clicks);
            item_time = itemView.findViewById(R.id.item_time);
            iv_img = itemView.findViewById(R.id.iv_img);
        }
    }

}
