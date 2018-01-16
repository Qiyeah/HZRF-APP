package com.zhilian.hzrf_oa.ui.egress.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.base.BaseActivity;
import com.zhilian.hzrf_oa.base.BaseFragment;
import com.zhilian.hzrf_oa.ui.egress.presenter.EgressPresenter;
import com.zhilian.rxapi.bean.EgressBean;
import com.zhilian.rxapi.bean.EgressMineBean;
import com.zhilian.rxapi.constant.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhilian on 2018/1/15.
 */

public class EgressManagerActivity extends BaseActivity implements IEgressView {
	@BindView(R.id.iv_back)
	ImageView mIvBack;
	@BindView(R.id.tv_title)
	TextView mTvTitle;
	@BindView(R.id.iv_search)
	ImageView mIvSearch;
	@BindView(R.id.layout_bar)
	RelativeLayout mLayoutBar;
	@BindViews({R.id.tv_todo, R.id.tv_done, R.id.tv_mine})
	List<TextView> mTabTitles;
	@BindView(R.id.layout_tab)
	LinearLayout mLayoutTab;
	@BindView(R.id.vp_container)
	ViewPager mVpContainer;
	@BindView(R.id.bt_apply)
	Button mBtApply;

	private List<BaseFragment> mTabs;

	private EgressPresenter mPresenter;



	private HashMap<String, String> params;
	private int pageNum = 1;
	private String condition = "";
	private int index = 0;

	@Override
	public void initData() {
		params = new HashMap<>();
		params.put("pageNumber", "" + pageNum);
		params.put("condition", condition);
		mPresenter = new EgressPresenter(this);
		mPresenter.loadServerTodoEgresses(params);
	}

	@Override
	protected int layoutRes() {
		return R.layout.activity_egress;
	}

	@Override
	protected void initView() {
		mTabs = new ArrayList<>();
		mTabs.add(new TodoEgressFragment());
		mTabs.add(new DoneEgressFragment());
		mTabs.add(new MineEgressFragment());

		mVpContainer.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public Fragment getItem(int position) {
				return mTabs.get(position);
			}

			@Override
			public int getCount() {
				return mTabs.size();
			}
		});
		mIvBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		mVpContainer.setCurrentItem(index);
		mVpContainer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				index = position;
				switch (position) {
					case 0:
						mPresenter.loadServerTodoEgresses(params);
						break;
					case 1:
						mPresenter.loadServerDoneEgresses(params);
						break;
					case 2:
						mPresenter.loadServerMineEgresses(params);
						break;
				}
				switchViewState();
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});


		switchViewState();
	}

	@OnClick(R.id.bt_apply)
	public void onClickApply() {
		Intent intent = new Intent(this, EgressDetailActivity.class);
		intent.putExtra("task", Constants.TASK_NEW);
		intent.putExtra("index", 0);
		intent.putExtra("docid", "0");
		intent.putExtra("isdone", "0");
		startActivityForResult(intent, Constants.TASK_NEW);
	}

	@OnClick(R.id.tv_todo)
	public void onClickTodo() {
		index = 0;
		//mPresenter.loadServerTodoEgresses(params);
		switchViewState();
	}

	@OnClick(R.id.tv_done)
	public void onClickDone() {
		index = 1;
		//mPresenter.loadServerDoneEgresses(params);
		switchViewState();
	}

	@OnClick(R.id.tv_mine)
	public void onClickMine() {
		index = 2;
		//mPresenter.loadServerMineEgresses(params);
		switchViewState();
	}

	@Override
	protected void clearRes() {

	}

	@Override
	public void onLoadServerTodoEgresses(@NotNull EgressBean root) {
		mTabs.get(0).notifyTodoDataChange(root.getList());
	}

	@Override
	public void onLoadServerDoneEgresses(@NotNull EgressBean root) {
		mTabs.get(1).notifyDoneDataChange(root.getList());
	}

	@Override
	public void onLoadServerMineEgresses(@NotNull EgressMineBean root) {
		mTabs.get(2).notifyMineDataChange(root.getList());
	}

	private void switchViewState() {
		for (TextView tab : mTabTitles) {
			tab.setBackgroundColor(getResources().getColor(R.color.tab_unselected));
		}
		mTabTitles.get(index).setBackgroundColor(getResources().getColor(R.color.tab_selected));
		mVpContainer.setCurrentItem(index);
	}


}
