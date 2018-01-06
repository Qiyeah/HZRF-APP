package com.zhilian.hzrf_oa.ui.leave.model;


import com.google.gson.Gson;
import com.zhilian.hzrf_oa.ui.leave.bean.LeaveRoot;
import com.zhilian.hzrf_oa.ui.leave.bean.TodoBean;
import com.zhilian.hzrf_oa.ui.leave.util.LogUtil;
import com.zhilian.rxapi.RxHttpServiceConstants;
import com.zhilian.rxapi.RxHttpServiceManager;
import com.zhilian.rxapi.RxHttpUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017-12-28.
 */

public class LeaveModel implements ILeaveModel<LeaveRoot> {

    @Override
    public void loadServerApplies(String queryName, final CallBack1 callBack) {
        RxHttpServiceManager.getInstance()
            .getRxApiService()
            .getServerData(RxHttpServiceConstants.URL, RxHttpUtil.initQueryParams("query",queryName,new HashMap<String, String>()))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ResponseBody>(){
                @Override
                public void accept(@NonNull ResponseBody responseBody) throws Exception {
                    String json = responseBody.string().trim();
                    LogUtil.e("receive: "+json);
                   /* if(StrKit.notBlank(json)){
                        TodoBean bean = new Gson().fromJson(json,TodoBean.class);
                        callBack.loadApplies(bean.getList());
                    }*/
                   if (json.equals(RxHttpServiceConstants.RESPONSE_ERROR)){
                        callBack.onDisconnected();
                   }else {
                       callBack.loadApplies(new Gson().fromJson(json,TodoBean.class).getList());
                   }
                }
            });
       }

    @Override
    public void loadServerApproves(String queryName, CallBack1 callBack) {

    }
}
