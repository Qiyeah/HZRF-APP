package com.zhilian.hzrf_oa.ui.leave.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Administrator on 2017-12-29.
 */

public class CacheUtil {
    private Context mContext;
    private SharedPreferences sp;
    private SharedPreferences.Editor mEditor;

    public CacheUtil(Context context, String xmlName) {
        mContext = context;
        sp = mContext.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
    }

    public <T> T getObject(String key, Class clazz) {
        String str = sp.getString(key, "");
        return StrKit.isBlank(str) ? null : (T) new Gson().fromJson(str, clazz);
    }
    public <T> void saveObject(String key, T obj){
        mEditor = sp.edit();
        mEditor.putString(key, new GsonBuilder().create().toJson(obj));
        mEditor.commit();
        mEditor.clear();
        Toast.makeText(mContext, "保存成功！", Toast.LENGTH_SHORT).show();
    }
}
