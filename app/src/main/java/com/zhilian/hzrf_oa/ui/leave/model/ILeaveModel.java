package com.zhilian.hzrf_oa.ui.leave.model;


import com.zhilian.hzrf_oa.base.IBaseModel;
import com.zhilian.rxapi.bean.LeaveMineBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public interface ILeaveModel<T> extends IBaseModel<T> {

    /**
     * 加载待审批的请休假申请到申请列表
	 * @param map
	 * @param callBack
	 */
    void loadServerApplies(HashMap<String, String> map, CallBack1<T> callBack);

    /**
     * 加载已经办理完结的请休假申请到办理完结列表
     * @param callBack
     */
    void loadServerApproves(HashMap<String, String> map,CallBack2 callBack);
	void loadServerMyApplies(HashMap<String, String> map, CallBack2 callBack);

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
        void onDisconnected();

        void loadMyApplies(List<LeaveMineBean.ItemBean> list);
    }

}

