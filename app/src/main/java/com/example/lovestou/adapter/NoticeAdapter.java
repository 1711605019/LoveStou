package com.example.lovestou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lovestou.R;
import com.example.lovestou.activity.BannerWebActivity;
import com.example.lovestou.bean.NoticeBean;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.RecyclerHolder> {
    private Context mContext;
    private List<NoticeBean> noticeList = new ArrayList<>();


    public NoticeAdapter(Context mContext, List<NoticeBean> noticeList) {
        this.mContext = mContext;
        this.noticeList = noticeList;
    }

    public void addData(List<NoticeBean> noticeList) {
        if (null != noticeList) {
            this.noticeList.addAll(noticeList);
            notifyDataSetChanged();
        }
    }

    public void setData(List<NoticeBean> noticeList) {
        if (null != noticeList) {
            this.noticeList.clear();
            this.noticeList.addAll(noticeList);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notice_item, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        NoticeBean noticeBean = noticeList.get(position);
        holder.tv_title.setText(noticeBean.getTitle());
        holder.tv_time.setText(noticeBean.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BannerWebActivity.class);
                intent.putExtra("url", noticeBean.getHref());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        public TextView tv_title,tv_time;
        private RecyclerHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }

}
