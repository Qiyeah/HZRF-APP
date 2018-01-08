package com.zhilian.hzrf_oa.ui.leave.presenter;


import com.zhilian.hzrf_oa.ui.leave.bean.ApplyBean;
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

public class LeavePresenter implements ILeavePresenter,ILeaveModel.CallBack1<List<TodoItemBean>>,ILeaveModel.CallBack2<LeaveRoot>,ILeaveModel.CallBack3<ApplyBean> {
    private ILeaveModel mModel;
    private ILeaveView mView;

    public LeavePresenter(ILeaveView view) {
        mView = view;
        mModel = new LeaveModel();
    }

    @Override
    public void initViewData() {
        mModel.loadServerApplies(this);
        mModel.loadServerApproves(this);
    }

    @Override
    public void newAsk4Leave() {
        mModel.newAsk4Leave(this);
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

    @Override
    public void onCreateNewApplySuccess(ApplyBean applyBean) {
            mView.onCreateNewApply(applyBean);
    }
}
