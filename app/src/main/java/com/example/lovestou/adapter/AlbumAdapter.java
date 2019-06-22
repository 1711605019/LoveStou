package com.example.lovestou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.lovestou.R;
import com.example.lovestou.bean.AlbumBean;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends BaseAdapter {

    private List<AlbumBean> dataList = new ArrayList<>();//数据集合对象
    private Context context;

    public AlbumAdapter(Context context){
        this.context = context;//上下文
    }
    public void setData(List<AlbumBean> dataList){//赋值方法
        this.dataList = dataList;
    }
    class ViewHolder{
        public ImageView imageView;
    }

    @Override
    public int getCount() {//反馈回数量
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {//反馈回对象
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {//反馈回位置
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null){//获取布局文件
            LayoutInflater linflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//定义布局文件加载器
            view = linflater.inflate(R.layout.item_album,null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.img_item_album_title);//布局界面上的图片控件
            view.setTag(viewHolder);
        }
        //ViewHolder holder = (ViewHolder)view.getTag();
        ViewHolder holder = new ViewHolder();
        holder.imageView = view.findViewById(R.id.img_item_album_title);
        holder.imageView.setImageResource(dataList.get(position).imgResId);//图片赋值
        return view;
    }
}
