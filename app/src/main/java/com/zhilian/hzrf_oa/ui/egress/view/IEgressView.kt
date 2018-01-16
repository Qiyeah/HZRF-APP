package com.zhilian.hzrf_oa.ui.egress.view

import com.zhilian.rxapi.bean.EgressBean
import com.zhilian.rxapi.bean.EgressMineBean

/**
 * Created by zhilian on 2018/1/15.
 */
interface IEgressView{
    fun onLoadServerTodoEgresses(root: EgressBean)
    fun onLoadServerDoneEgresses(root: EgressBean)
    fun onLoadServerMineEgresses(root: EgressMineBean)
}