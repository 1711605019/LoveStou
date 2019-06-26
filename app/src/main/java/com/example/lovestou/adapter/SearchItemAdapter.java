package com.example.lovestou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovestou.R;
import com.example.lovestou.bean.HistoryBean;

import java.util.ArrayList;
import java.util.List;

public class SearchItemAdapter extends BaseAdapter {

    private List<HistoryBean> historyList = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public SearchItemAdapter(List<HistoryBean> historyList, Context context) {
        this.historyList = historyList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public HistoryBean getItem(int position) {
        return historyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.search_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((HistoryBean) getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(HistoryBean historyBean, ViewHolder holder, int position) {
        //TODO implement
        holder.tvRecord.setText(historyBean.getInput());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    protected class ViewHolder {
        private TextView tvRecord;
    private ImageView ivDelete;

        public ViewHolder(View view) {
            tvRecord = (TextView) view.findViewById(R.id.tv_record);
            ivDelete = (ImageView) view.findViewById(R.id.iv_delete);
        }
    }
}
