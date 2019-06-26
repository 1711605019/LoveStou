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
import com.example.lovestou.bean.VideoBean;

import java.util.List;

public class VideoNewsAdapter extends RecyclerView.Adapter<VideoNewsAdapter.ViewHolder> {
    private Context context;
    private List<VideoBean> todayList;

    public VideoNewsAdapter(Context context, List<VideoBean> todayList) {
        this.todayList = todayList;
        this.context = context;
    }
    public void addData(List<VideoBean> todayList) {
        if (null != todayList) {
            this.todayList.addAll(todayList);
            notifyDataSetChanged();
        }
    }

    public void setData(List<VideoBean> todayList) {
        if (null != todayList) {
            this.todayList.clear();
            this.todayList.addAll(todayList);
            notifyDataSetChanged();
        }
    }

    @Override
    public VideoNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        VideoBean videoBean = todayList.get(i);
        String t = videoBean.getTitle().substring(0, videoBean.getTitle().length()-10);
        String time = videoBean.getTitle().substring(videoBean.getTitle().length()-10);
        viewHolder.textView.setText(t);

        Glide
                .with(context)
                .load(videoBean.getImg())
                .placeholder(R.drawable.error)
                .into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("url", videoBean.getHref());
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