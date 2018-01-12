package com.zhilian.hzrf_oa.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhilian.api.StrKit;

/**
 * Created by Administrator on 2017-12-29.
 */

public class CacheUtil_copy {
    private Context mContext;
    private SharedPreferences sp;
    private SharedPreferences.Editor mEditor;

    public CacheUtil_copy(Context context, String xmlName) {
        mContext = context;
        sp = mContext.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
    }

    public <T> T getObject(String key, T obj) {
        String str = sp.getString(key, "");
        return StrKit.isBlank(str) ? null : (T) new Gson().fromJson(str, obj.getClass());
    }
    public <T> void saveObject(String key,T obj){
        mEditor = sp.edit();
        mEditor.putString(key, new GsonBuilder().create().toJson(obj));
        mEditor.commit();
        mEditor.clear();
    }
}
