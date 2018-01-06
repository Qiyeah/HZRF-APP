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

public class RxHttpServiceConstants {
    public static String USER_ID = null;
    public static String CONFIRM_ID = null;
    public static String APP_KEY = null;
    public static String BASE_URL = "http://192.168.9.37:8083/";
    public static String URL = null;
    public static final String RESPONSE_ERROR = "用户登录超时！";
    static {
        String token = "1lj4hbato30kl1ppytwa1ueqdn";
        String encodingAesKey = "InVjlo7czsOWrCSmTPgEUXBzlFnmqpNMQU3ZfilULHyHZiRjVUhxxWpexhYH6f4i";
        Map<String, String> ret = Sign.sign(BASE_URL, token, encodingAesKey);
        String signature = ret.get("signature");
        String nonceStr = ret.get("nonceStr");
        String timestamp = ret.get("timestamp");
        Map<String, String> queryParas = ParaMap.create("accessToken", token)
            .put("nonce", nonceStr)
            .put("timestamp", timestamp)
            .put("signature", signature)
            .getData();
        URL = RequestUtil.buildUrlWithQueryString(BASE_URL+"Api", queryParas);
    }

//    public final static String BASE_URL = "http://www.zhiliantech.com:8083/ZhiLian-OA/Api/";// 云服务器






}
