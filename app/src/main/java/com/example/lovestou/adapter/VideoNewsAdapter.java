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
    private List<VideoBean> videoList;

    public VideoNewsAdapter(Context context, List<VideoBean> videoList) {
        this.videoList = videoList;
        this.context = context;
    }
    public void addData(List<VideoBean> videoList) {
        if (null != videoList) {
            this.videoList.addAll(videoList);
            notifyDataSetChanged();
        }
    }

    public void setData(List<VideoBean> videoList) {
        if (null != videoList) {
            this.videoList.clear();
            this.videoList.addAll(videoList);
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
        VideoBean videoBean = videoList.get(i);
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
                VideoBean videoBean1 = videoList.get(i);
                VideoActivity.videoBean = videoBean1;
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
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