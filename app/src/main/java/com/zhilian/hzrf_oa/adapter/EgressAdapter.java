package com.zhilian.hzrf_oa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.util.Constants;
import com.zhilian.hzrf_oa.util.LogUtil;
import com.zhilian.rxapi.bean.EgressBean;
import com.zhilian.rxapi.constant.LocalConstants;

import java.util.List;

/**
 * Created by zhilian on 2018/1/15.
 */

public class EgressAdapter extends BaseAdapter {
	Context mContext;
	List<EgressBean.ListBean> mSource;
	View mView;
	ImageView mIvIcon;
	TextView mTvDescribe;
	TextView mTvState;
	TextView mTvDate;

	public EgressAdapter(Context context, List<EgressBean.ListBean> source) {
		mContext = context;
		mSource = source;
	}

	@Override
	public int getCount() {
		return mSource.size();
	}

	@Override
	public Object getItem(int i) {
		return mSource.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		EgressBean.ListBean egress = mSource.get(position);
		//LogUtil.e("egress : \n"+egress);
		if (null == view){
			view =  LayoutInflater.from(mContext).inflate(R.layout.leave_item, viewGroup, false);
		}
		mTvDescribe = (TextView) view.findViewById(R.id.tv_describe);
		mTvDate = (TextView) view.findViewById(R.id.tv_date);
		mTvState = (TextView) view.findViewById(R.id.tv_state);
//        LogUtil.e(mSource.get(position).toString())
		if (null!= egress){
			if (egress.getName()!=null&&egress.getName().equals(LocalConstants.USER_NAME)) {
				mTvDescribe.setText("我申请了[ " + egress.getType() + " ]共 " + egress.getDays() + " 天");
			} else {
				mTvDescribe.setText(egress.getName() + "申请了[ " + egress.getType() + " ]共 " + egress.getDays() + " 天");
			}
			mTvDate.setText(egress.getApprovedate());
			mTvState.setText(egress.getActive());
		}
		return view;
	}

	private void initView(View view, int position) {

	}
}
