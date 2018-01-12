package com.zhilian.hzrf_oa.ui.leave.presenter;

import java.util.HashMap;

/**
 * Created by Administrator on 2017-12-28.
 */

public interface ILeavePresenter {
    void initApproves(HashMap<String, String> map);
    void initApplies(HashMap<String, String> map);

	void initMyApplies(HashMap<String, String> map);
}
