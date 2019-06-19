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
import com.example.lovestou.bean.EducationBean;
import com.example.lovestou.utils.NewsInterface;

import java.util.ArrayList;
import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.RecyclerHolder> {
    private Context mContext;
    private List<EducationBean.ItemsBean> educationList = new ArrayList<>();


    public EducationAdapter(Context mContext, List<EducationBean.ItemsBean> educationList) {
        this.mContext = mContext;
        this.educationList = educationList;
    }

    public void addData(List<EducationBean.ItemsBean> educationList) {
        if (null != educationList) {
            this.educationList.addAll(educationList);
            notifyDataSetChanged();
        }
    }

    public void setData(List<EducationBean.ItemsBean> educationList) {
        if (null != educationList) {
            this.educationList.clear();
            this.educationList.addAll(educationList);
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
        EducationBean.ItemsBean educationBean = educationList.get(position);
        holder.item_title.setText(educationBean.getTitle());
        holder.item_clicks.setText(educationBean.getClicks()+" 阅读");
        holder.item_time.setText(educationBean.getAddTime());
        Glide
                .with(mContext)
                .load(educationBean.getImg())
                .placeholder(R.drawable.error)
                .into(holder.iv_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsWebActivity.class);
                intent.putExtra("url", NewsInterface.WEB_SITE +educationBean.getHref());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return educationList.size();
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