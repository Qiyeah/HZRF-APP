package com.zhilian.hzrf_oa.ui.leave.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.net_exception.ITimeOutException;
import com.zhilian.hzrf_oa.net_exception.TimeOutException;
import com.zhilian.hzrf_oa.base.BaseActivity;
import com.zhilian.hzrf_oa.base.BaseFragment;
import com.zhilian.rxapi.bean.LeaveDoneBean;
import com.zhilian.rxapi.bean.LeaveMineBean;
import com.zhilian.rxapi.bean.TodoItemBean;
import com.zhilian.rxapi.constant.Constants;
import com.zhilian.hzrf_oa.ui.leave.presenter.LeavePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017-12-28.
 */

/**
 * 工作台---请休假管理
 */
public class LeaveManagerActivity extends BaseActivity  implements ILeaveView {

    @BindView(R.id.vp_container)
    ViewPager mVpContainer;
    @BindView(R.id.tv_draft)
    TextView mTvDraft;
    @BindView(R.id.tv_approved)
    TextView mTvApproved;
    @BindView(R.id.tv_my_applies)
    TextView mTvMyApplies;
    @BindViews({R.id.tv_draft,R.id.tv_approved,R.id.tv_my_applies})
    List<TextView> mTvTitles;
    @BindView(R.id.bt_apply)
    Button mBtApply;
    @BindView(R.id.linearLayout2)
    RelativeLayout mLinearLayout2;
    @BindView(R.id.lt_container)
    LinearLayout mLtContainer;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;



    private LeavePresenter mPresenter = null;
    private FragmentPagerAdapter mAdapter;
    private List<BaseFragment> mTabs = new ArrayList<BaseFragment>();
    private ViewPager.OnPageChangeListener mPageChangeListener;
    private int index = 0;
    private String fileName = "apply.xml";
    HashMap<String,String> map = new HashMap<>();
    /**
     * 初始化页面数据
     */
    @Override
    public void initData() {

        map.put("pageNumber","1");
        map.put("condition","");
        mPresenter = new LeavePresenter(this);
        mPresenter.initApplies(map);
        mPresenter.initApproves(map);
        mPresenter.initMyApplies(map);
    }

    /**
     * 加载布局文件
     *
     * @return
     */
    @Override
    protected int layoutRes() {
        return R.layout.activity_leave;
    }

    /**
     *
     */
    @Override
    protected void initView() {

        mTabs.add(new AppliesFragment());
        mTabs.add(new ApprovedFragment());
        mTabs.add(new MyAppliesFragment());

        if (mTabs != null && mTabs.size() > 0) {
            mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return mTabs.get(position);
                }

                @Override
                public int getCount() {
                    return mTabs.size();
                }
            };
            mVpContainer.setAdapter(mAdapter);
            mPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    index = position;
                    changeTabs();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            };
            mVpContainer.setOnPageChangeListener(mPageChangeListener);
            changeTabs();
        }
    }

    /**
     * +
     * 清理资源引用
     */
    @Override
    protected void clearRes() {

    }

    /**
     * 成功获取待批列表数据
     */
    @Override
    public void onInitAppliesSuccess(List<TodoItemBean> list) {
        mTabs.get(0).notifyTodoDataChange(list);
    }

    /**
     * 成功获取已批列表数据
     *
     * @param root
     */
    @Override
    public void onInitApprovesSuccess(List<LeaveDoneBean.DoneItemBean> root) {
        mTabs.get(1).notifyDoneDataChange(root);
    }

    @Override
    public void onDisConnected() {
        new TimeOutException().reLogin(getApplicationContext(), new ITimeOutException.CallBack() {
            @Override
            public void onReloginSuccess() {
                mPresenter.initApplies(map);
                mPresenter.initApproves(map);
                mPresenter.initMyApplies(map);
            }
        });
    }

    @Override
    public void onInitMyAppliesSuccess(List<LeaveMineBean.ItemBean> root) {
        mTabs.get(2).notifyMineDataChange(root);
    }

    /**
     * 监听返回按键
     */
    @OnClick(R.id.iv_back)
    public void onMIvBackClicked() {
        finish();
    }

    @OnClick(R.id.bt_apply)
    public void onMBtApplyClicked() {
        //TODO 新申请请休假
        Intent intent = new Intent(this, LeaveDetailActivity.class);
        intent.putExtra("task", Constants.TASK_NEW);
        intent.putExtra("docid","0");
        intent.putExtra("isdone","0");
        startActivityForResult(intent,Constants.TASK_NEW);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.initApplies(map);
    }

    /**
     * 监听搜索按键
     */
    /*@OnClick(R.id.iv_search)
    public void onMIvSearchClicked() {
    }*/

    /**
     * 监听待批列表按键
     */
    @OnClick(R.id.tv_draft)
    public void onMTvDraftClicked() {
        index = 0;
        changeTabs();
    }

    /**
     * 监听已批列表按键
     */
    @OnClick(R.id.tv_approved)
    public void onMTvApprovedClicked() {
        index = 1;
        changeTabs();
    }

    /**
     * 监听已批列表按键
     */
    @OnClick(R.id.tv_my_applies)
    public void onMTvMineClicked() {
        index = 2;
        changeTabs();
    }




    /**
     * 切换选中状态
     */
    private void changeTabs() {
//        mTvDraft.setBackgroundColor(0xffffffff);
//        mTvApproved.setBackgroundColor(0xffffffff);
//        mTvMyApplies.setBackgroundColor(0xffffffff);
        for (TextView tvTitle : mTvTitles) {
            tvTitle.setBackgroundColor(0xffffffff);
        }
        mTvTitles.get(index).setBackgroundColor(Color.parseColor("#99CCFF"));
        mVpContainer.setCurrentItem(index, false);
       /* if (0 == index)
            mTvDraft.setBackgroundColor(Color.parseColor("#99CCFF"));
        else
            mTvApproved.setBackgroundColor(Color.parseColor("#99CCFF"));*/
    }
}
