package com.zhilian.hzrf_oa.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.zhilian.api.StrKit;
import com.zhilian.hzrf_oa.entity.Opinion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.zhilian.hzrf_oa.ui.leave.util.LogUtil;

/**
 * Created by Administrator on 2017-12-18.
 */

public class OpinionUtil {
    private Context mContext;
    private SharedPreferences mSp;
    private SharedPreferences.Editor mEditor;
    public static OpinionUtil instance = null;
    private String key = null;


    public static synchronized OpinionUtil getInstance(Context context) {
        if (null == instance){
            synchronized (OpinionUtil.class){
                instance = new OpinionUtil(context);
            }
        }
        return instance;
    }

    private OpinionUtil(Context context) {
        mContext = context;
        mSp = mContext.getSharedPreferences("opinion",Context.MODE_PRIVATE);
    }

    /**
     * 保存意见到缓存
     * @param opinion
     */
    public void saveOpinion(Opinion opinion){
        mEditor = mSp.edit();
        mEditor.putString(opinion.getKey(),opinion.getContent());
        mEditor.commit();
    }

    /**
     * 清除缓存中的意见
     * @param pid
     * @param itemId 环节ID
     */
    public void clearOpinion(String pid,String itemId){
        mEditor = mSp.edit();
        mEditor.remove(pid+"-"+itemId);
        mEditor.commit();
    }

    /**
     * 获取缓存中的意见
     * @param pid
     * @param itemId 环节ID
     * @return
     */
    public String getOpinion(String pid,String itemId){
        return mSp.getString(pid+"-"+itemId,"");
    }

    /**
     *
     * @param tempOpinion 历史意见
     * @param displayOpinion 视图中的意见
     * @param uName 用户名称
     * @return 新意见
     */
    public static String getOpinion(String tempOpinion,String displayOpinion,String uName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        String opinions = "";
        LogUtil.e("tempOpinion = "+tempOpinion);
        if (StrKit.notBlank(tempOpinion)) {
            opinions = tempOpinion.replace("&nbsp;", " ").replace("<br>", "\n");
        } else {
            if (StrKit.notBlank(displayOpinion)){
                opinions = "  " + displayOpinion + "\n        " + uName + "  [" + sdf.format(new Date()) + "]\n\n";
            }
        }
        return opinions;
    }
}
