package com.zhilian.hzrf_oa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.ui.widget.BaseViewHolder;


public class WorkAdapter extends BaseAdapter {
    private Context mContext;
    public String[] mDescriptions = null;
    public int[] mIcons =null;
  
    public WorkAdapter(Context context, int[] icons, String[] descriptions) {
        mContext = context;
        mDescriptions = descriptions;
        mIcons = icons;
    }

    @Override
    public int getCount() {
        return mIcons.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
        }
        TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
        ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
        iv.setBackgroundResource(mIcons[position]);

        tv.setText(mDescriptions[position]);
        return convertView;
    }
}
