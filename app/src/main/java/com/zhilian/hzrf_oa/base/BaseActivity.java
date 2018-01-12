package com.zhilian.hzrf_oa.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-12-28.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearRes();
        finish();
    }
    /**
     * 返回布局资源ID
     * @return
     */
    protected abstract int layoutRes();

    /**
     * 初始化视图
     */
    protected abstract void  initView();

    /**
     * 清理页面资源
     */
    protected abstract void clearRes();
}
