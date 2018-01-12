package com.zhilian.hzrf_oa.util;

import android.content.Context;

/**
 * Created by Administrator on 2017-12-29.
 */

public class WindowUtil {

    public static int getWindowWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    public static int getWindowHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
