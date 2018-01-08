package com.zhilian.hzrf_oa.ui.leave.model;


import com.zhilian.hzrf_oa.ui.leave.base.IBaseModel;
import com.zhilian.hzrf_oa.ui.leave.bean.ApplyBean;

/**
 * Created by Administrator on 2017-12-28.
 */

public interface ILeaveModel<T> extends IBaseModel<T> {

    /**
     * 加载待审批的请休假申请到申请列表
     * @param callBack
     */
    void loadServerApplies(CallBack1<T> callBack);

    /**
     * 加载已经办理完结的请休假申请到办理完结列表
     * @param callBack
     */
    void loadServerApproves(CallBack1<T> callBack);

    void newAsk4Leave(CallBack3 callBack);

    interface CallBack1<T>{
        /**
         *
         * @param t
         */
        void loadApplies(T t);

        void onDisconnected();
    }
    interface CallBack2<T>{
        /**
         *
         * @param t
         */
        void loadApproves(T t);
    }
    interface CallBack3<T>{
        void onCreateNewApplySuccess(T t);
    }
}

