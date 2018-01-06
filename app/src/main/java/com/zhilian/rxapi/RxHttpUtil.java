package com.zhilian.rxapi;

import com.google.gson.GsonBuilder;
import com.zhilian.api.ContextUtil;
import com.zhilian.api.InQueryMsg;
import com.zhilian.api.InSaveMsg;
import com.zhilian.api.StrKit;
import com.zhilian.hzrf_oa.ui.leave.constant.LocalConstants;
import com.zhilian.hzrf_oa.ui.leave.util.LogUtil;

import java.util.HashMap;

/**
 * Created by Administrator on 2017-12-23.
 */

public class RxHttpUtil {

    public static String initQueryParams(String type, String method, HashMap<String, String> params){
        InQueryMsg inQueryMsg = new InQueryMsg(1111111111, type, LocalConstants.APP_KEY);
        inQueryMsg.setQueryPara(params);
        inQueryMsg.setQueryName(method);
        String postData = new GsonBuilder().disableHtmlEscaping().create().toJson(inQueryMsg);
        return StrKit.notBlank(postData)?postData:"";
    }
    public static String initSaveParams(String type,String method,HashMap<String,String> params){
        InSaveMsg msg = new InSaveMsg(1111111111,type,LocalConstants.APP_KEY);
        msg.setModelName(method);
        msg.setModelProperty(params);
        String postData = new GsonBuilder().disableHtmlEscaping().create().toJson(msg);
        return StrKit.notBlank(postData)?postData:"";
    }
}
