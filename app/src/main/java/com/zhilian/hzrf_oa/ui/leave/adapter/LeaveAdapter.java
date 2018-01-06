package com.zhilian.hzrf_oa.ui.leave.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.ui.leave.bean.LeaveDetailBean;
import com.zhilian.hzrf_oa.ui.leave.bean.TodoItemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-1-2.
 */

public class LeaveAdapter extends BaseAdapter {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_person)
    TextView mTvPerson;

    @BindView(R.id.tv_unit)
    TextView mTvUnit;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    private List<TodoItemBean> data;
    private Context mContext;

    public LeaveAdapter(List<TodoItemBean> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TodoItemBean leave = data.get(i);
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.leave_item, viewGroup, false);
            ButterKnife.bind(this, view);
        }
        mTvPerson.setText(leave.getName()+"\t\t\t"+leave.getType()+"\t\t\t"+leave.getDayt()+" 天");
        mTvDate.setText(leave.getApprovedate());
        mTvUnit.setText(leave.getActive());
        return view;
    }
}
