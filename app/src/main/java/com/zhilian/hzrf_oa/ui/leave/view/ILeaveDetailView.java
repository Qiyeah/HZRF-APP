package com.zhilian.hzrf_oa.ui.leave.view;

import com.zhilian.hzrf_oa.base.IBaseView;
import com.zhilian.hzrf_oa.base.IDetailBaseView;
import com.zhilian.rxapi.bean.LeaveDetailBean;

/**
 * Created by Administrator on 2018-1-2.
 */

public interface ILeaveDetailView extends IDetailBaseView {

    void onGetLeaveDetailSuccess(LeaveDetailBean bean);

    void onSaveOpinionSuccess(String result);

    void onSaveOpinionFailure(String result);

    void updateDayt(String dayt);

	void onDisconnected(String message);
}
