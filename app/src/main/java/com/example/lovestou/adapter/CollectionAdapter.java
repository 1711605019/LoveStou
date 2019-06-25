package com.example.lovestou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lovestou.R;
import com.example.lovestou.bean.DataBean;

import java.util.ArrayList;
import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.RecyclerHolder> {
    private Context mContext;
    private List<DataBean.ItemsBean> foodList = new ArrayList<>();


    public CollectionAdapter(Context mContext, List<DataBean.ItemsBean> foodList) {
        this.mContext = mContext;
        this.foodList = foodList;
    }

    public void addData(List<DataBean.ItemsBean> foodList) {
        if (null != foodList) {
            this.foodList.addAll(foodList);
            notifyDataSetChanged();
        }
    }

    public void setData(List<DataBean.ItemsBean> foodList) {
        if (null != foodList) {
            this.foodList.clear();
            this.foodList.addAll(foodList);
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
        DataBean.ItemsBean FoodBean = foodList.get(position);
        holder.item_title.setText(FoodBean.getTitle());
        holder.item_clicks.setText(FoodBean.getClicks() + " 阅读");
        holder.item_time.setText(FoodBean.getAddTime());
        Glide
                .with(mContext)
                .load(FoodBean.getImg())
                .placeholder(R.drawable.error)
                .into(holder.iv_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, NewsWebActivity.class);
//                intent.putExtra("url", NewsInterface.WEB_SITE + FoodBean.getHref());
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
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
