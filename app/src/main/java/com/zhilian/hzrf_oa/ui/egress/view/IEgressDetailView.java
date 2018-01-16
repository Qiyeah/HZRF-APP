package com.zhilian.hzrf_oa.ui.egress.view;

import com.zhilian.hzrf_oa.base.IDetailBaseView;
import com.zhilian.rxapi.bean.EgressDetailBean;

/**
 * Created by zhilian on 2018/1/16.
 */

public interface IEgressDetailView extends IDetailBaseView {
	void getEgressDetail(EgressDetailBean detail);
}
