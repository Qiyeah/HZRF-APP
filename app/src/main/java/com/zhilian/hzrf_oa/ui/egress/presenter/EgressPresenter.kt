package com.zhilian.hzrf_oa.ui.egress.presenter

import com.zhilian.hzrf_oa.ui.egress.model.EgressModel
import com.zhilian.hzrf_oa.ui.egress.model.IEgressModel
import com.zhilian.hzrf_oa.ui.egress.view.IEgressView
import com.zhilian.hzrf_oa.util.LogUtil
import com.zhilian.rxapi.bean.EgressBean
import com.zhilian.rxapi.bean.EgressMineBean

/**
 * Created by zhilian on 2018/1/12.
 */
class EgressPresenter : IEgressModel.CallBack{



    lateinit var mView:IEgressView
    lateinit var mModel: IEgressModel

    constructor(mView: IEgressView) {
        this.mView = mView
        mModel = EgressModel()
    }

    fun loadServerTodoEgresses(map:Map<String,String>) {
        mModel.loadServerTodoEgresses(map,this)
    }
    fun loadServerDoneEgresses(map:Map<String,String>) {
        mModel.loadServerDoneEgresses(map,this)
    }
    fun loadServerMineEgresses(map:Map<String,String>) {
        mModel.loadServerMineEgresses(map,this)
    }


    override fun onLoadServerTodoEgresses(root: EgressBean) {
        mView.onLoadServerTodoEgresses(root)
    }

    override fun onLoadServerDoneEgresses(root: EgressBean) {
        mView.onLoadServerDoneEgresses(root)
    }

    override fun onLoadServerMineEgresses(root: EgressMineBean) {
        mView.onLoadServerMineEgresses(root)
    }
    override fun onDisconnected() {
        //LogUtil.e("与服务器失去连接")
    }

}
