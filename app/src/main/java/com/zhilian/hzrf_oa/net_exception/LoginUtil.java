package com.zhilian.hzrf_oa.net_exception;

import android.content.Context;
import android.os.Looper;

import com.zhilian.hzrf_oa.common.BusinessContant;
import com.zhilian.rxapi.constant.LocalConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sl on 2017-12-6.
 */

public class LoginUtil{
    public static boolean confirmUser(Context context, String user_id, String pwd) {
        BusinessContant bc = new BusinessContant();
        boolean is_exist = false;
        String target = "";
        target = bc.URL + "/Mobile/login/" + user_id + "-" + pwd;
        try {       //要访问的URL地址
            URL url;
            url = new URL(target);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();  //创建一个HTTP连接
            urlConn.connect();

           //
            if (urlConn.getResponseCode() == 200) {
                InputStream in = urlConn.getInputStream(); // 获得读取的内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "/n");
                    }
                    JSONObject dataJson = new JSONObject(sb.toString());
                    String status = dataJson.getString("status");
                    String msg = dataJson.getString("msg");
                    if (status.equals("1")) {
                        is_exist = true;
                        bc.setCONFIRM_ID(msg);
                        LocalConstants.APP_KEY = msg;
                        LocalConstants.USER_NAME = dataJson.getString("uname");
                        LocalConstants.LIMIT =  dataJson.getString("startReceive");//服务器发出：0，无权限，1，有权限
                    } else {
                        Looper.prepare();
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    in.close(); //关闭字符输入流对象
                    urlConn.disconnect();   //断开连接
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is_exist;
    }
}
