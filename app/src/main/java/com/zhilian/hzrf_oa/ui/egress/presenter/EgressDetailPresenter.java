package com.zhilian.hzrf_oa.ui.egress.presenter;

import com.google.gson.Gson;
import com.zhilian.hzrf_oa.base.IBasePresenter;
import com.zhilian.hzrf_oa.ui.egress.model.EgressDetailModel;
import com.zhilian.hzrf_oa.ui.egress.view.IEgressDetailView;
import com.zhilian.hzrf_oa.ui.leave.model.LeaveDetailModel;
import com.zhilian.hzrf_oa.ui.leave.view.ILeaveDetailView;
import com.zhilian.hzrf_oa.util.StrKit;
import com.zhilian.rxapi.RxHttpServiceManager;
import com.zhilian.rxapi.RxHttpUtil;
import com.zhilian.rxapi.bean.EgressDetailBean;
import com.zhilian.rxapi.bean.LeaveDetailBean;
import com.zhilian.rxapi.constant.Constants;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018-1-4.
 */

public class EgressDetailPresenter implements IBasePresenter,EgressDetailModel.CallBack{
    private IEgressDetailView mView;
    private EgressDetailModel mModel;

    public EgressDetailPresenter(IEgressDetailView view) {
        mView = view;
        mModel = new EgressDetailModel();
    }

    public void getEgressDetail(String docid, String isdone) {
        mModel.getEgressDetail(docid,isdone,this);
    }

    @Override
    public void onGetEgressDetailSuccess(EgressDetailBean bean) {
        mView.getEgressDetail(bean);
    }

    @Override
    public void onDisconnected(String message) {

    }

    @Override
    public void onSaveOpinionSuccess(String result) {

    }

    @Override
    public void onSaveOpinionFailure(String result) {

    }

    public void saveOpinion(String saveModel, EgressDetailBean egress) {
        mModel.saveOpinion(saveModel,egress,this);
    }
}
