package com.zhilian.hzrf_oa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.hzrf_oa.R;
import com.zhilian.rxapi.bean.DoneBean;
import com.zhilian.rxapi.constant.Constants;
import com.zhilian.hzrf_oa.util.StrKit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-1-2.
 */

public class ApprovesAdapter extends BaseAdapter {
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_describe)
    TextView mTvDescribe;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    private List<DoneBean.DoneItemBean> data;
    private Context mContext;

    public ApprovesAdapter(List<DoneBean.DoneItemBean> data, Context context) {
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
        DoneBean.DoneItemBean leave = data.get(i);
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.leave_item, viewGroup, false);
            ButterKnife.bind(this, view);
        }
        mTvDescribe.setText(generateItemTitle(leave));
        mTvDate.setText(leave.getStarttime());
        mTvState.setText(leave.getActive());
        return view;
    }

    private String generateItemTitle(DoneBean.DoneItemBean leave) {
        StringBuilder sb = new StringBuilder();
        sb.append(leave.getName())
                .append("申请了");
        if (StrKit.notBlank(leave.getType())){
            sb.append(Constants.BRACKET1).append(leave.getType()).append(Constants.BRACKET2);
        }

        if (StrKit.notBlank(leave.getDayt())){
            if (leave.getDayt().contains(".")){
                String[] dayts = leave.getDayt().split("\\.");
                if (Integer.valueOf(dayts[0]) > 0) {
                    sb.append(dayts[0]).append("天");
                    if (Integer.valueOf(dayts[1]) > 0) {
                        sb.append("半.");
                    }
                } else {
                    if (Integer.valueOf(dayts[1]) == 5) {
                        sb.append("半天.");
                    } else {
                        sb.append(" O 天.");
                    }
                }
            }else {
                sb.append(" O 天.");
            }
        }
        return sb.toString();
    }
}
