package com.zhilian.hzrf_oa.ui.egress.model

import java.util.HashMap

/**
 * Created by zhilian on 2018/1/12.
 */
interface IEgressModle{
    fun <T> loadServerTodoEgresses( map :Map<String, String>,callBack:CallBack<T>)
    fun <T> loadServerDoneEgresses( map :Map<String, String>,callBack:CallBack<T>)
    fun <T> loadServerMineEgresses( map :Map<String, String>,callBack:CallBack<T>)

    interface CallBack<T>{
        fun onLoadServerTodoEgresses(root:T)
        fun onLoadServerDoneEgresses(root:T)
        fun onLoadServerMineEgresses(root:T)
    }
}