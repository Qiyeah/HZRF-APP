package com.zhilian.hzrf_oa.ui.leave.view;


import com.zhilian.hzrf_oa.ui.leave.base.IBaseView;
import com.zhilian.hzrf_oa.ui.leave.bean.ApplyBean;
import com.zhilian.hzrf_oa.ui.leave.bean.LeaveRoot;
import com.zhilian.hzrf_oa.ui.leave.bean.TodoItemBean;

import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public interface ILeaveView extends IBaseView {
    void onInitAppliesSuccess(List<TodoItemBean> list);
    void onInitApprovesSuccess(LeaveRoot root);

    void onDisConnected();

    void onCreateNewApply(ApplyBean applyBean);
}
