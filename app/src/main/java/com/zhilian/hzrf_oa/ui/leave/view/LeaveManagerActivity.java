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
import com.zhilian.hzrf_oa.ui.leave.base.BaseActivity;
import com.zhilian.hzrf_oa.ui.leave.base.BaseFragment;
import com.zhilian.hzrf_oa.ui.leave.bean.ApplyBean;
import com.zhilian.hzrf_oa.ui.leave.bean.LeaveDetailBean;
import com.zhilian.hzrf_oa.ui.leave.bean.LeaveRoot;
import com.zhilian.hzrf_oa.ui.leave.bean.TodoItemBean;
import com.zhilian.hzrf_oa.ui.leave.constant.Constants;
import com.zhilian.hzrf_oa.ui.leave.constant.LocalConstants;
import com.zhilian.hzrf_oa.ui.leave.presenter.LeavePresenter;
import com.zhilian.hzrf_oa.ui.leave.util.CacheUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017-12-28.
 */

/**
 * 工作台---请休假管理
 */
public class LeaveManagerActivity extends BaseActivity implements ILeaveView {

    @BindView(R.id.vp_container)
    ViewPager mVpContainer;
    @BindView(R.id.tv_draft)
    TextView mTvDraft;
    @BindView(R.id.tv_approved)
    TextView mTvApproved;
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

    private List<TodoItemBean> mTodoList = new ArrayList<>();// 待批列表数据

    private List<LeaveDetailBean> mApproveList = new ArrayList<>();//已批列表数据

    private LeaveDetailBean mCacheApply;//用户缓存的请休假申请
    private TodoItemBean mCacheTodo;//
    private FragmentPagerAdapter mAdapter;
    private List<BaseFragment> mTabs = new ArrayList<BaseFragment>();
    private ViewPager.OnPageChangeListener mPageChangeListener;
    private int index = 0;
    private String fileName = "apply.xml";

    /**
     * 初始化页面数据
     */
    @Override
    public void initData() {
        mPresenter = new LeavePresenter(this);
        mPresenter.initViewData();
        mCacheApply = new CacheUtil(getApplicationContext(),fileName).
            getObject("leave", LeaveDetailBean.class);
        if (null != mCacheApply) {
            mTodoList.add(cache2TodoItem(mCacheApply));
        }
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

        mTabs.add(new AppliesFragment(mTodoList));
        mTabs.add(new ApprovedFragment());

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

        mTodoList = list;
        if (null != mCacheApply) {
            mTodoList.add(cache2TodoItem(mCacheApply));
        }
        mTabs.get(0).notifyAppliesDataChange(mTodoList);

    }

    /**
     * 成功获取已批列表数据
     *
     * @param root
     */
    @Override
    public void onInitApprovesSuccess(LeaveRoot root) {
        mApproveList = root.getRoot();
    }

    @Override
    public void onDisConnected() {
        new TimeOutException().reLogin(getApplicationContext(), new ITimeOutException.CallBack() {
            @Override
            public void onReloginSuccess() {
                mPresenter.initViewData();
            }
        });
    }

    @Override
    public void onCreateNewApply(ApplyBean applyBean) {

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
        try {
            mPresenter.newAsk4Leave();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, LeaveDetailActivity.class);
        intent.putExtra("task", Constants.TASK_NEW);
        intent.putExtra("doc_id","0");
        intent.putExtra("isdone","0");
        startActivity(intent);
    }

    /**
     * 监听搜索按键
     */
    @OnClick(R.id.iv_search)
    public void onMIvSearchClicked() {
    }

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

    // 清除掉所有的选中状态
    private void clearSelection() {
        mTvDraft.setBackgroundColor(0xffffffff);
        mTvApproved.setBackgroundColor(0xffffffff);
    }

    private void changeTabs() {
        clearSelection();

        mVpContainer.setCurrentItem(index, false);
        if (0 == index)
            mTvDraft.setBackgroundColor(Color.parseColor("#99CCFF"));
        else
            mTvApproved.setBackgroundColor(Color.parseColor("#99CCFF"));
    }

    private TodoItemBean cache2TodoItem(LeaveDetailBean leave) {
        TodoItemBean bean = new TodoItemBean();
        bean.setType(leave.getType());
        bean.setActive("申请");
        bean.setDayt(leave.getDayt());
        bean.setName(leave.getUname());
        bean.setUnit(leave.getDname());
        return bean;
    }
}
