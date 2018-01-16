package com.zhilian.hzrf_oa.ui.egress.model

import com.zhilian.rxapi.bean.EgressBean
import com.zhilian.rxapi.bean.EgressMineBean

/**
 * Created by zhilian on 2018/1/12.
 */
interface IEgressModel {
    fun  loadServerTodoEgresses( map :Map<String, String>,callBack:CallBack)
    fun loadServerDoneEgresses( map :Map<String, String>,callBack:CallBack)
    fun loadServerMineEgresses( map :Map<String, String>,callBack:CallBack)

    interface CallBack{
        fun onLoadServerTodoEgresses(root: EgressBean)
        fun onLoadServerDoneEgresses(root:EgressBean)
        fun onLoadServerMineEgresses(root: EgressMineBean)
        fun onDisconnected()
    }
}