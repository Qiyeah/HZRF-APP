package com.zhilian.hzrf_oa.ui.leave.view;


import com.zhilian.rxapi.bean.LeaveDoneBean;
import com.zhilian.rxapi.bean.LeaveMineBean;
import com.zhilian.rxapi.bean.TodoItemBean;

import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public interface ILeaveView {
    void onInitAppliesSuccess(List<TodoItemBean> list);
    void onInitApprovesSuccess(List<LeaveDoneBean.DoneItemBean> root);
    void onDisConnected();

	void onInitMyAppliesSuccess(List<LeaveMineBean.ItemBean> list);
}
