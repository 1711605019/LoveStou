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
import com.example.lovestou.bean.TravelBean;
import com.example.lovestou.utils.NewsInterface;

import java.util.ArrayList;
import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.RecyclerHolder> {
    private Context mContext;
    private List<TravelBean.ItemsBean> travelList = new ArrayList<>();


    public TravelAdapter(Context mContext, List<TravelBean.ItemsBean> travelList) {
        this.mContext = mContext;
        this.travelList = travelList;
    }

    public void addData(List<TravelBean.ItemsBean> travelList) {
        if (null != travelList) {
            this.travelList.addAll(travelList);
            notifyDataSetChanged();
        }
    }

    public void setData(List<TravelBean.ItemsBean> travelList) {
        if (null != travelList) {
            this.travelList.clear();
            this.travelList.addAll(travelList);
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
        TravelBean.ItemsBean TravelBean = travelList.get(position);
        holder.item_title.setText(TravelBean.getTitle());
        holder.item_clicks.setText(TravelBean.getClicks()+" 阅读");
        holder.item_time.setText(TravelBean.getAddTime());
        Glide
                .with(mContext)
                .load(TravelBean.getImg())
                .placeholder(R.drawable.error)
                .into(holder.iv_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsWebActivity.class);
                intent.putExtra("url", NewsInterface.WEB_SITE +TravelBean.getHref());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return travelList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        public TextView item_title,item_clicks,item_time;
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