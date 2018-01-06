package com.zhilian.hzrf_oa.ui.leave.model;


import com.zhilian.hzrf_oa.ui.leave.base.IBaseModel;

/**
 * Created by Administrator on 2017-12-28.
 */

public interface ILeaveModel<T> extends IBaseModel<T> {

    /**
     * 加载待审批的请休假申请到申请列表
     * @param queryName
     * @param callBack
     */
    void loadServerApplies(String queryName, CallBack1<T> callBack);

    /**
     * 加载已经办理完结的请休假申请到办理完结列表
     * @param queryName
     * @param callBack
     */
    void loadServerApproves(String queryName, CallBack1<T> callBack);

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
}

