package com.zhilian.hzrf_oa.ui.leave.model;

import com.google.gson.Gson;
import com.zhilian.hzrf_oa.ui.leave.base.IBaseModel;
import com.zhilian.hzrf_oa.ui.leave.bean.LeaveDetailBean;
import com.zhilian.hzrf_oa.ui.leave.bean.TodoBean;
import com.zhilian.hzrf_oa.ui.leave.constant.Constants;
import com.zhilian.hzrf_oa.ui.leave.util.LogUtil;
import com.zhilian.hzrf_oa.ui.leave.util.StrKit;
import com.zhilian.rxapi.RxHttpServiceManager;
import com.zhilian.rxapi.RxHttpUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018-1-4.
 */

public class LeaveDetailModel implements IBaseModel {
    public void getLeaveDetail(String docid, String isdone, final LeaveDetailModel.CallBack callback) {

        HashMap<String, String> map = new HashMap<>();
        map.put("docid",docid);
        map.put("isdone",isdone);
        LogUtil.e(docid);
        LogUtil.e(isdone);
        RxHttpServiceManager.getInstance()
            .getRxApiService()
            .getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initQueryParams(Constants.TYPE_QUERY,Constants.QUERY_LEAVE_DETAIL,map))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>(){
                @Override
                public void accept(@NonNull ResponseBody responseBody) throws Exception {
                    String json = responseBody.string().trim();
                    LogUtil.e("receive: "+json);
                    if(StrKit.notBlank(json)){
                        LeaveDetailBean bean = new Gson().fromJson(json,LeaveDetailBean.class);
                        callback.onGetLeaveDetailSuccess(bean);
                    }
                }
            });
    }

    public void saveOpinion(String saveModel, LeaveDetailBean json, final CallBack1 callBack1) {
        HashMap<String, String> map = new HashMap<>();
        map.put("itemid", String.valueOf(json.getWf().getItemid()));
        String opinionfield = json.getOpinionfield();
        if (opinionfield.equals("opinion1")){
            map.put("opinion", json.getOpinion1());
        }else  if (opinionfield.equals("opinion2")){
            map.put("opinion", json.getOpinion2());
        }
//        LogUtil.e("json.getWf().getId() = "+json.getWf().getId());
        map.put("pid", String.valueOf(json.getWf().getId()));
        map.put("isreceiveopinion", "0");
        map.put("docid", "0");
        RxHttpServiceManager.getInstance()
            .getRxApiService()
            .getServerData(Constants.URL, RxHttpUtil.initSaveParams("save",saveModel,map))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(ResponseBody responseBody) throws Exception {
                    String result = new String(responseBody.bytes());
                    callBack1.onSaveOpinionSuccess(result);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    callBack1.onSaveOpinionFailure(throwable.getCause().getMessage());
                }
            });
}


    public interface CallBack1{
        void onSaveOpinionSuccess(String result);

        void onSaveOpinionFailure(String result);
    }

    public interface CallBack {
        void onGetLeaveDetailSuccess(LeaveDetailBean bean);
    }
}
