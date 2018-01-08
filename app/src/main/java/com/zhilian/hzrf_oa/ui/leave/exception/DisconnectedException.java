package com.zhilian.hzrf_oa.ui.leave.exception;

import com.zhilian.hzrf_oa.ui.leave.constant.Constants;
import com.zhilian.hzrf_oa.ui.leave.util.LogUtil;
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
