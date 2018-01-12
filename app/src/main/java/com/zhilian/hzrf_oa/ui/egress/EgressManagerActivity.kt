package com.zhilian.hzrf_oa.ui.egress

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import com.zhilian.hzrf_oa.R
import com.zhilian.hzrf_oa.base.BaseActivity
import com.zhilian.hzrf_oa.base.BaseFragment
import com.zhilian.hzrf_oa.ui.leave.view.AppliesFragment
import com.zhilian.hzrf_oa.ui.leave.view.ApprovedFragment
import com.zhilian.hzrf_oa.ui.leave.view.MyAppliesFragment
import com.zhilian.hzrf_oa.util.LogUtil


/**
 * Created by zhilian on 2018/1/12.
 */
class EgressManagerActivity : BaseActivity(),View.OnClickListener {
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_todo -> index = 0
            R.id.tv_done -> index = 1
            R.id.tv_mine -> index = 2
        }

        switchViewState()
    }

    var index = 0

    lateinit var mTvTodo: TextView
    lateinit var mTvDone: TextView
    lateinit var mTvMine: TextView
    lateinit var mIvBack: ImageView
    lateinit var mIvSearch: ImageView
    lateinit var mVpContainer: ViewPager

    lateinit var mTabs:MutableList<BaseFragment>
    lateinit var mAdapter:FragmentPagerAdapter

    override fun initData() {
    }

    override fun layoutRes(): Int {
        return R.layout.activity_egress
    }


    override fun initView() {
        mTvTodo = findViewById(R.id.tv_todo) as TextView
        mTvTodo.setOnClickListener(this)
        mTvDone = findViewById(R.id.tv_done) as TextView
        mTvDone.setOnClickListener(this)
        mTvMine = findViewById(R.id.tv_mine) as TextView
        mTvMine.setOnClickListener(this)
        mVpContainer = findViewById(R.id.vp_container) as ViewPager
        mIvBack = findViewById(R.id.iv_back) as ImageView

        mVpContainer.setCurrentItem(index)

        mIvBack.setOnClickListener(View.OnClickListener { finish() })

        initFragments()

        switchViewState()
    }

    private fun initFragments() {
        mTabs = mutableListOf()

        mTabs.add(AppliesFragment())
        mTabs.add(ApprovedFragment())
        mTabs.add(MyAppliesFragment())

        mAdapter = object : FragmentPagerAdapter(supportFragmentManager) {

            override fun getCount(): Int {
                return mTabs.size
            }

            override fun getItem(arg0: Int): Fragment {
                return mTabs[arg0]
            }
        }

        mVpContainer.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                index = position
                switchViewState()
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun switchViewState() {
        mTvTodo.setBackgroundColor(resources.getColor(R.color.tab_unselected))
        mTvDone.setBackgroundColor(resources.getColor(R.color.tab_unselected))
        mTvMine.setBackgroundColor(resources.getColor(R.color.tab_unselected))
        when (index) {
            0 -> mTvTodo.setBackgroundColor(resources.getColor(R.color.tab_selected))
            1 -> mTvDone.setBackgroundColor(resources.getColor(R.color.tab_selected))
            2 -> mTvMine.setBackgroundColor(resources.getColor(R.color.tab_selected))
        }
        mVpContainer.setCurrentItem(index)
    }

    override fun clearRes() {

    }

}