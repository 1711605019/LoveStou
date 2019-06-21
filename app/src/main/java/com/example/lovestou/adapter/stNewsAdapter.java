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
import com.example.lovestou.bean.stNewsBean;

import java.util.List;

public class stNewsAdapter extends RecyclerView.Adapter<stNewsAdapter.ViewHolder> {
    private Context context;
    private List<stNewsBean> stNewsList;

    public stNewsAdapter(Context context, List<stNewsBean> stNewsList) {
        this.stNewsList = stNewsList;
        this.context = context;
    }
    public void addData(List<stNewsBean> stNewsList) {
        if (null != stNewsList) {
            this.stNewsList.addAll(stNewsList);
            notifyDataSetChanged();
        }
    }

    public void setData(List<stNewsBean> stNewsList) {
        if (null != stNewsList) {
            this.stNewsList.clear();
            this.stNewsList.addAll(stNewsList);
            notifyDataSetChanged();
        }
    }

    @Override
    public stNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        stNewsBean stNewsBean = stNewsList.get(i);
        viewHolder.textView.setText(stNewsBean.getTitle());
        Glide
                .with(context)
                .load(stNewsBean.getImg())
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
        return stNewsList.size();
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