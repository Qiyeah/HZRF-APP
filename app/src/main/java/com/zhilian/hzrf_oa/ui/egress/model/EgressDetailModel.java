package com.zhilian.hzrf_oa.ui.egress.model;

import com.google.gson.Gson;
import com.zhilian.hzrf_oa.base.IBaseModel;
import com.zhilian.hzrf_oa.util.LogUtil;
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

public class EgressDetailModel implements IBaseModel {
    public void getEgressDetail(String docid, String isdone, final EgressDetailModel.CallBack callback) {

        HashMap<String, String> map = new HashMap<>();
        map.put("docid",docid);
        map.put("isdone",isdone);
      //  LogUtil.e(docid);
      //  LogUtil.e(isdone);
        RxHttpServiceManager.getInstance()
            .getRxApiService()
            .getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initQueryParams(Constants.TYPE_QUERY,Constants.QUERY_EGRESS_DETAIL,map))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>() {
                @Override
                public void accept(@NonNull ResponseBody responseBody) throws Exception {
                    String json = responseBody.string().trim();
                    LogUtil.e("detail: " + json);
                    if (StrKit.notBlank(json)) {
                        EgressDetailBean bean = new Gson().fromJson(json, EgressDetailBean.class);
                        callback.onGetEgressDetailSuccess(bean);
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    callback.onDisconnected(throwable.getMessage());
                }
            });
    }

    public void saveOpinion(String saveModel, EgressDetailBean json, final CallBack callBack1) {
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
        map.put("docid", json.getId());
        LogUtil.e("url params = "+RxHttpUtil.initSaveParams("save",saveModel,map));
        RxHttpServiceManager.getInstance()
            .getRxApiService()
            .getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initSaveParams("save",saveModel,map))
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




    public interface CallBack {
        void onGetEgressDetailSuccess(EgressDetailBean bean);

        void onDisconnected(String message);
        void onSaveOpinionSuccess(String result);

        void onSaveOpinionFailure(String result);
    }

}
