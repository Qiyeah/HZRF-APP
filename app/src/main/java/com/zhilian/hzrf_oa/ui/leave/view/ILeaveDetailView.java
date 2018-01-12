package com.zhilian.hzrf_oa.ui.leave.view;

import com.zhilian.hzrf_oa.base.IBaseView;
import com.zhilian.rxapi.bean.LeaveDetailBean;

/**
 * Created by Administrator on 2018-1-2.
 */

public interface ILeaveDetailView extends IBaseView {
    void setApplyDate(String date);
    void setBeginDate(String date);
    void setEndDate(String date);
    void setBackDate(String date);
    void onReasonChanged(String reason);
    void onOpinion1Changed(String opinion);
    void onOpinion2Changed(String opinion);

    void onGetLeaveDetailSuccess(LeaveDetailBean bean);

    void onSaveOpinionSuccess(String result);

    void onSaveOpinionFailure(String result);

    void updateDayt(String dayt);

	void onDisconnected(String message);
}
