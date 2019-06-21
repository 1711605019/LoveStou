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
import com.example.lovestou.bean.TodayBean;

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
        viewHolder.textView.setText(todayBean.getTitle());
        Glide
                .with(context)
                .load(todayBean.getImg())
                .placeholder(R.drawable.error)
                .into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, VideoActivity.class);
//                intent.putExtra("url",stNewsBean.getHref());
//                intent.putExtra("title",stNewsBean.getTitle());
//                context.startActivity(intent);
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