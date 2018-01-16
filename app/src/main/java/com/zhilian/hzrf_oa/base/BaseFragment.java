package com.zhilian.hzrf_oa.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhilian.hzrf_oa.ui.widget.CustomListFragment;
import com.zhilian.hzrf_oa.ui.widget.CustomListView;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-12-29.
 */

public abstract class BaseFragment<T> extends CustomListFragment implements CustomListView.OnPullListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LogUtil.e("onCreate()");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(layoutRes(),container,false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPullDownRefresh(View headView) {
        loadData();
    }

    @Override
    public void onPullUpLoadMore(CustomListView.LoadMode mode, ProgressBar footBar, TextView loadTextView) {
        refreshData();
    }

    protected abstract void initView();
    protected abstract int layoutRes();
    public void notifyTodoDataChange(List<T> list){}
    public void notifyDoneDataChange(List<T> list){}
    public void notifyMineDataChange(List<T> list){}
    protected void loadData(){}
    protected void refreshData(){}

}
