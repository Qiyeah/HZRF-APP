package com.zhilian.hzrf_oa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.util.LogUtil;
import com.zhilian.rxapi.bean.EgressBean;
import com.zhilian.rxapi.bean.EgressMineBean;

import java.util.List;

/**
 * Created by zhilian on 2018/1/15.
 */

public class EgressMineAdapter extends BaseAdapter {
	Context mContext;
	List<EgressMineBean.ListBean> mSource;
	View mView;
	ImageView mIvIcon;
	TextView mTvDescribe;
	TextView mTvState;
	TextView mTvDate;

	public EgressMineAdapter(Context context, List<EgressMineBean.ListBean> source) {
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
		EgressMineBean.ListBean mine = mSource.get(position);
		if (null == view){
			view =  LayoutInflater.from(mContext).inflate(R.layout.leave_item, viewGroup, false);
		}

		mTvDescribe = (TextView) view.findViewById(R.id.tv_describe);
		mTvDate = (TextView) view.findViewById(R.id.tv_date);
		mTvState = (TextView) view.findViewById(R.id.tv_state);
//        LogUtil.e(mSource.get(position).toString())
		if (null != mine){
			mTvDescribe.setText(" "+mine.getBegindate()+" 到 "+mine.getEnddate() + " "+mine.getType()+" 共 "+mine.getDays()+ " 天");
			mTvDate.setText(mSource.get(position).getApprovedate());
			String str = "";
			if ("0".equals(mine.getPstatus())){
				str = "未办结";
			}else {
				str = "已办结";
			}
			mTvState.setText(str);
		}
		return view;
	}

	private void initView(View view, int position) {


	}
}
