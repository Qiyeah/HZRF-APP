package com.zhilian.hzrf_oa.ui.leave.view;


import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.ui.leave.base.BaseFragment;
import com.zhilian.hzrf_oa.ui.leave.bean.LeaveDetailBean;
import com.zhilian.hzrf_oa.ui.leave.bean.TodoItemBean;

import java.util.List;

/**
 * Created by Administrator on 2017-12-29.
 */

public class ApprovedFragment extends BaseFragment {


    @Override
    protected void initView() {

    }

    @Override
    protected int layoutRes() {
        return R.layout.layout_apply;
    }

    @Override
    public void notifyAppliesDataChange(List<TodoItemBean> list) {

    }

    @Override
    public void notifyApprovesDataChange(List<LeaveDetailBean> list) {

    }


}
