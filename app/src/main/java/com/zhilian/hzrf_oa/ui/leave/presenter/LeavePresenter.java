package com.zhilian.hzrf_oa.ui.leave.presenter;


import com.zhilian.hzrf_oa.ui.leave.bean.LeaveRoot;
import com.zhilian.hzrf_oa.ui.leave.bean.TodoItemBean;
import com.zhilian.hzrf_oa.ui.leave.model.ILeaveModel;
import com.zhilian.hzrf_oa.ui.leave.model.LeaveModel;
import com.zhilian.hzrf_oa.ui.leave.util.LogUtil;
import com.zhilian.hzrf_oa.ui.leave.view.ILeaveView;

import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public class LeavePresenter implements ILeavePresenter,ILeaveModel.CallBack1<List<TodoItemBean>>,ILeaveModel.CallBack2<LeaveRoot>{
    private ILeaveModel mModel;
    private ILeaveView mView;

    public LeavePresenter(ILeaveView view) {
        mView = view;
        mModel = new LeaveModel();
    }

    @Override
    public void initViewData(String queryName1, String queryName2) {
        mModel.loadServerApplies(queryName1,this);
        mModel.loadServerApproves(queryName2,this);
    }

    @Override
    public void loadApplies(List<TodoItemBean> list) {

        mView.onInitAppliesSuccess(list);
    }

    @Override
    public void onDisconnected() {
        mView.onDisConnected();
    }

    @Override
    public void loadApproves(LeaveRoot root) {
        mView.onInitApprovesSuccess(root);
    }
}
