package com.zhilian.rxapi.exception;

import com.zhilian.rxapi.constant.Constants;
import com.zhilian.hzrf_oa.util.LogUtil;
/**
 * Created by zhilian on 2018/1/8.
 */

public class DisconnectedException extends Exception {
    @Override
    public String getMessage() {
        return Constants.RESPONSE_ERROR;
    }

    @Override
    public void printStackTrace() {
        LogUtil.e(getMessage());
    }
}
