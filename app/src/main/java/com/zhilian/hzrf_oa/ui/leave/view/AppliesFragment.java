package com.zhilian.hzrf_oa.ui.leave.view;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.ui.leave.adapter.LeaveAdapter;
import com.zhilian.hzrf_oa.ui.leave.base.BaseFragment;
import com.zhilian.hzrf_oa.ui.leave.bean.LeaveDetailBean;
import com.zhilian.hzrf_oa.ui.leave.bean.TodoItemBean;
import com.zhilian.hzrf_oa.ui.leave.constant.LocalConstants;
import com.zhilian.hzrf_oa.ui.leave.util.LogUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-12-29.
 */

public class AppliesFragment extends BaseFragment {
    @BindView(R.id.lv_draft)
    ListView mLvDraft;
    private LeaveAdapter mAdapter;
    private List<TodoItemBean> mDraftList;

    @SuppressLint("ValidFragment")
    public AppliesFragment(List<TodoItemBean> draftList) {
        mDraftList = draftList;

    }

    public AppliesFragment() {
    }

    @Override
    protected void initView() {
        mLvDraft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), LeaveDetailActivity.class);
                intent.putExtra(LocalConstants.TASK_KEY, LocalConstants.TASK_TODO);
                intent.putExtra(LocalConstants.DOC_ID, mDraftList.get(i).getDocid());
                intent.putExtra(LocalConstants.IS_DONE, "0");
                startActivity(intent);
            }
        });

    }

    @Override
    protected int layoutRes() {
        return R.layout.layout_draft;
    }




    public void notifyAppliesDataChange(List<TodoItemBean> data) {
        mDraftList = data;
        for (TodoItemBean datum : data) {
            LogUtil.e(datum.toString());
        }
        if (null !=getActivity()){
            if (null == mAdapter){
                mAdapter = new LeaveAdapter(mDraftList,getActivity());
                mLvDraft.setAdapter(mAdapter);
            }else {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void notifyApprovesDataChange(List<LeaveDetailBean> list) {

    }
}
