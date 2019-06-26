package com.example.lovestou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lovestou.R;
import com.example.lovestou.bean.MsgItem;

import java.util.ArrayList;
import java.util.List;

public class LayoutMsgViewAdapter extends BaseAdapter {

    private List<MsgItem> objects = new ArrayList<MsgItem>();

    private Context context;
    private LayoutInflater layoutInflater;


    public LayoutMsgViewAdapter(List<MsgItem> objects, Context context) {
        this.objects = objects;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public MsgItem getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_msg_view, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MsgItem) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(MsgItem object, ViewHolder holder) {
        holder.leftMsg.setText(object.getResult().getContent());
        holder.rightMsg.setText(object.getMyself());
    }

    protected class ViewHolder {
        private LinearLayout leftLayout;
        private TextView leftMsg;
        private LinearLayout rightLayout;
        private TextView rightMsg;

        public ViewHolder(View view) {
            leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            leftMsg = (TextView) view.findViewById(R.id.left_msg);
            rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            rightMsg = (TextView) view.findViewById(R.id.right_msg);
        }
    }
}
