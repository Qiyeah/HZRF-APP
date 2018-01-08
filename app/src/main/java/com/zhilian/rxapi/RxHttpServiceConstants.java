package com.zhilian.rxapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhilian.api.ContextUtil;
import com.zhilian.api.InQueryMsg;
import com.zhilian.api.ParaMap;
import com.zhilian.api.RequestUtil;
import com.zhilian.api.Sign;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sl on 2017-10-17.
 */

public interface RxHttpServiceConstants {
    /**
     * ************************************* 请求参数 ***************************************
     */
    String BASE_URL = "http://192.168.9.31:8083/";
    //String BASE_URL = "http://www.zhiliantech.com:8083/ZhiLian-OA/";// 云服务器
    String URL = null;
    String TYPE_QUERY = "query";
    String TYPE_SAVE = "save";

    /**
     * ************************************* 方法名 ***************************************
     */
    String QUERY_LEAVE_TODO = "getLeaveTodoList";//查询待办理请休假
    String QUERY_LEAVE_DONE = "getLeaveDoneList";//查询已办理请休假
    String QUERY_LEAVE_NEW = "";//查询新申请请休假
    String QUERY_LEAVE_DETAIL= "LeaveDetail";//查询新申请请休假
    String QUERY_LEAVE_DAYT = "";//查询可请假天数
    String QUERY_FSONG = "fasong";//查询可请假天数
    String QUERY_MY_LEAVE_LIST = "getMyLeaveList";// 获取我的请休假列表
    String SAVE_OPINION = "editopinion";//意见保存
    String SAVE_SELECT_MEN = "receivesave";//选人

    /**
     * ************************************* 错误反馈 ***************************************
     */
    String RESPONSE_ERROR = "用户登录超时！";

}
