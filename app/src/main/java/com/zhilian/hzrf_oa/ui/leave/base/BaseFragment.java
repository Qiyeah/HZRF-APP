package com.zhilian.hzrf_oa.ui.leave.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhilian.hzrf_oa.ui.leave.bean.LeaveDetailBean;
import com.zhilian.hzrf_oa.ui.leave.bean.TodoItemBean;
import com.zhilian.hzrf_oa.ui.leave.util.LogUtil;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-12-29.
 */

public abstract class BaseFragment extends Fragment {
    private View mView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(layoutRes(),container,false);
        ButterKnife.bind(this, rootView);
       initView();
        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    protected abstract void initView();
    protected abstract int layoutRes();
    public abstract void notifyAppliesDataChange(List<TodoItemBean> list);
    public abstract void notifyApprovesDataChange(List<LeaveDetailBean> list);

}
