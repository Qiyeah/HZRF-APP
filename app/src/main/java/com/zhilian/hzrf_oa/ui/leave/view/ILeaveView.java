package com.zhilian.hzrf_oa.ui.leave.view;


import com.zhilian.hzrf_oa.base.IBaseView;
import com.zhilian.rxapi.bean.DoneBean;
import com.zhilian.rxapi.bean.MyLeaveBean;
import com.zhilian.rxapi.bean.TodoItemBean;

import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public interface ILeaveView extends IBaseView {
    void onInitAppliesSuccess(List<TodoItemBean> list);
    void onInitApprovesSuccess(List<DoneBean.DoneItemBean> root);
    void onDisConnected();

	void onInitMyAppliesSuccess(List<MyLeaveBean.ItemBean> list);
}
