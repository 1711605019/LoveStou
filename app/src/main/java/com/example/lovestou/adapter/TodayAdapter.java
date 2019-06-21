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
import com.example.lovestou.activity.VideoActivity;
import com.example.lovestou.bean.TodayBean;
import com.example.lovestou.bean.stNewsBean;

import java.util.List;

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.ViewHolder> {
    private Context context;
    private List<TodayBean> todayList;

    public TodayAdapter(Context context, List<TodayBean> todayList) {
        this.todayList = todayList;
        this.context = context;
    }
    public void addData(List<TodayBean> todayList) {
        if (null != todayList) {
            this.todayList.addAll(todayList);
            notifyDataSetChanged();
        }
    }

    public void setData(List<TodayBean> todayList) {
        if (null != todayList) {
            this.todayList.clear();
            this.todayList.addAll(todayList);
            notifyDataSetChanged();
        }
    }

    @Override
    public TodayAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        TodayBean todayBean = todayList.get(i);
        String t = todayBean.getTitle().substring(0,todayBean.getTitle().length()-10);
        String time = todayBean.getTitle().substring(todayBean.getTitle().length()-10);
        viewHolder.textView.setText(t);

        Glide
                .with(context)
                .load(todayBean.getImg())
                .placeholder(R.drawable.error)
                .into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("url",todayBean.getHref());
                intent.putExtra("title",t);
                intent.putExtra("time",time);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todayList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.homeNews_img);
            textView = view.findViewById(R.id.homeNews_title);
        }
    }
}