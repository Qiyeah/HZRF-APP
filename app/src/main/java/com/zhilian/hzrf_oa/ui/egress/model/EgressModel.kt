package com.zhilian.hzrf_oa.ui.egress.model

import com.google.gson.Gson
import com.zhilian.hzrf_oa.util.LogUtil
import com.zhilian.rxapi.RxHttpServiceConstants
import com.zhilian.rxapi.RxHttpServiceManager
import com.zhilian.rxapi.RxHttpUtil
import com.zhilian.rxapi.bean.EgressBean
import com.zhilian.rxapi.bean.EgressMineBean
import com.zhilian.rxapi.constant.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by zhilian on 2018/1/12.
 */
class EgressModel : IEgressModel {
    override fun loadServerDoneEgresses(map: Map<String, String>, callBack: IEgressModel.CallBack) {
        RxHttpServiceManager.getInstance()
                .rxApiService
                .getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initQueryParams(Constants.TYPE_QUERY, Constants.QUERY_EGRESS_DONE, map))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ responseBody ->
                    val json = String(responseBody.bytes())
//                    LogUtil.e(" done:" + json)
                    if (json == RxHttpServiceConstants.RESPONSE_ERROR) {
                        callBack.onDisconnected()
                    } else {
                        callBack.onLoadServerDoneEgresses(Gson().fromJson(json, EgressBean::class.java))
                    }
                }/*,{throwable->LogUtil.e(throwable.message)}*/)
    }

    override fun loadServerMineEgresses(map: Map<String, String>, callBack: IEgressModel.CallBack) {
        RxHttpServiceManager.getInstance()
                .rxApiService
                .getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initQueryParams(Constants.TYPE_QUERY, Constants.QUERY_MY_EGRESS, map))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { responseBody ->
                    val json = String(responseBody.bytes())
//                    LogUtil.e("mine :" + json)
                    if (json == RxHttpServiceConstants.RESPONSE_ERROR) {
                        callBack.onDisconnected()
                    } else {
                        callBack.onLoadServerMineEgresses(Gson().fromJson(json, EgressMineBean::class.java))
                    }
                }
    }

    override fun loadServerTodoEgresses(map: Map<String, String>, callBack: IEgressModel.CallBack) {

        RxHttpServiceManager.getInstance()
                .rxApiService
                .getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initQueryParams(Constants.TYPE_QUERY, Constants.QUERY_EGRESS_TODO, map))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ responseBody ->
                    val json = String(responseBody.bytes())
//                    LogUtil.e("todo :" + json)
                    if (json == RxHttpServiceConstants.RESPONSE_ERROR) {
                        callBack.onDisconnected()
                    } else {

                       var  root:EgressBean  = Gson().fromJson(json, EgressBean::class.java)
                        callBack.onLoadServerTodoEgresses(root)
                    }
                })
    }


}