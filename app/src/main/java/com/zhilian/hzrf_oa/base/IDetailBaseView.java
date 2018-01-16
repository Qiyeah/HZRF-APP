package com.zhilian.hzrf_oa.base;

/**
 * Created by zhilian on 2018/1/16.
 */

public interface IDetailBaseView {
	void setApplyDate(String date);
	void setBeginDate(String date);
	void setEndDate(String date);
	void onReasonChanged(String reason);
	void onOpinion1Changed(String opinion);
	void onOpinion2Changed(String opinion);
	void setBackDate(String date);
}
