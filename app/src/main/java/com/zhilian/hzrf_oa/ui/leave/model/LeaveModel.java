package com.zhilian.hzrf_oa.ui.leave.model;


import com.google.gson.Gson;
import com.zhilian.hzrf_oa.json.HttpUtil;
import com.zhilian.hzrf_oa.util.LogUtil;
import com.zhilian.rxapi.bean.DoneBean;
import com.zhilian.rxapi.bean.MyLeaveBean;
import com.zhilian.rxapi.bean.TodoBean;
import com.zhilian.rxapi.constant.Constants;
import com.zhilian.rxapi.RxHttpServiceConstants;
import com.zhilian.rxapi.RxHttpServiceManager;
import com.zhilian.rxapi.RxHttpUtil;

import java.util.HashMap;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


/**
 * Created by Administrator on 2017-12-28.
 */

public class LeaveModel implements ILeaveModel {


	@Override
	public void loadServerApplies(HashMap map, final CallBack1 callBack) {
		RxHttpServiceManager.getInstance()
			.getRxApiService()
			.getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initQueryParams(Constants.TYPE_QUERY, Constants.QUERY_LEAVE_TODO, map))
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Consumer<ResponseBody>() {
				@Override
				public void accept(@NonNull ResponseBody responseBody) throws Exception {
					String json = new String(responseBody.bytes());
					LogUtil.e("applies:"+json);
					if (json.equals(RxHttpServiceConstants.RESPONSE_ERROR)) {
						callBack.onDisconnected();
					} else {
						callBack.loadApplies(new Gson().fromJson(json, TodoBean.class).getList());
					}
				}
			});
	}

	@Override
	public void loadServerApproves(HashMap map, final CallBack2 callBack) {
		RxHttpServiceManager.getInstance()
			.getRxApiService()
			.getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initQueryParams(Constants.TYPE_QUERY, Constants.QUERY_LEAVE_DONE, map))
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Consumer<ResponseBody>() {
				@Override
				public void accept(@NonNull ResponseBody responseBody) throws Exception {
					String json = new String(responseBody.bytes());
					LogUtil.e("approves:"+json);
					if (json.equals(RxHttpServiceConstants.RESPONSE_ERROR)) {
						callBack.onDisconnected();
					} else {
						callBack.loadApproves(new Gson().fromJson(json, DoneBean.class).getList());
					}
				}
			});
	}

	@Override
	public void loadServerMyApplies(HashMap map, final CallBack2 callBack) {
		RxHttpServiceManager.getInstance()
			.getRxApiService()
			.getServerData(RxHttpUtil.initUrl(), RxHttpUtil.initQueryParams(Constants.TYPE_QUERY, Constants.QUERY_MY_LEAVE,map ))
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Consumer<ResponseBody>() {
				@Override
				public void accept(@NonNull ResponseBody responseBody) throws Exception {
					String json = new String(responseBody.bytes());
					LogUtil.e("myApplies:"+json);
					if (json.equals(RxHttpServiceConstants.RESPONSE_ERROR)) {
						callBack.onDisconnected();
					} else {
						callBack.loadMyApplies(new Gson().fromJson(json, MyLeaveBean.class).getList());
					}
				}
			});
	}


}
