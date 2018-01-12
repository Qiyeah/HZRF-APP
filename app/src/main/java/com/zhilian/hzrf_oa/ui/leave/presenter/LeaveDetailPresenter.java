package com.zhilian.hzrf_oa.ui.leave.presenter;

import com.zhilian.hzrf_oa.base.IBasePresenter;
import com.zhilian.rxapi.bean.LeaveDetailBean;
import com.zhilian.hzrf_oa.ui.leave.model.LeaveDetailModel;
import com.zhilian.hzrf_oa.ui.leave.view.ILeaveDetailView;

/**
 * Created by Administrator on 2018-1-4.
 */

public class LeaveDetailPresenter implements IBasePresenter ,LeaveDetailModel.CallBack,LeaveDetailModel.CallBack1,LeaveDetailModel.CallBack2{
    private ILeaveDetailView mView;
    private LeaveDetailModel mModel;

    public LeaveDetailPresenter(ILeaveDetailView view) {
        mView = view;
        mModel = new LeaveDetailModel();
    }

    public void getLeaveDetail(String docid,String isdone) {
        mModel.getLeaveDetail(docid,isdone,this);
    }

    @Override
    public void onGetLeaveDetailSuccess(LeaveDetailBean bean) {
        mView.onGetLeaveDetailSuccess(bean);
    }

    @Override
    public void onDisconnected(String message) {
        mView.onDisconnected(message);
    }

    public void saveOpinion(String saveModel, LeaveDetailBean temp) {
        mModel.saveOpinion(saveModel,temp,this);
    }

    @Override
    public void onSaveOpinionSuccess(String result) {
        mView.onSaveOpinionSuccess(result);
    }

    @Override
    public void onSaveOpinionFailure(String result) {
        mView.onSaveOpinionFailure(result);
    }

    public void getLeaveDayt(String start,String end) {
        mModel.getLeaveDayt(start,end,this);
    }

    @Override
    public void onGetLeaveDaytSuccess(String dayt) {
        mView.updateDayt(dayt);
    }
}
