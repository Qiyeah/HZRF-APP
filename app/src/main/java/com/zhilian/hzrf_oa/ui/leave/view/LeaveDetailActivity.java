package com.zhilian.hzrf_oa.ui.leave.view;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.zhilian.api.InQueryMsg;
import com.zhilian.api.JsonStringRequest;
import com.zhilian.api.RequestUtil;
import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.common.BusinessContant;
import com.zhilian.hzrf_oa.common.Common;
import com.zhilian.hzrf_oa.net_exception.ITimeOutException;
import com.zhilian.hzrf_oa.net_exception.TimeOutException;
import com.zhilian.hzrf_oa.ui.leave.base.BaseActivity;
import com.zhilian.hzrf_oa.ui.leave.bean.LeaveDetailBean;
import com.zhilian.hzrf_oa.ui.leave.bean.TodoItemBean;
import com.zhilian.hzrf_oa.ui.leave.constant.Constants;
import com.zhilian.hzrf_oa.ui.leave.constant.LocalConstants;
import com.zhilian.hzrf_oa.ui.leave.presenter.LeaveDetailPresenter;
import com.zhilian.hzrf_oa.ui.leave.util.CacheUtil;
import com.zhilian.hzrf_oa.ui.leave.util.DateUtil;
import com.zhilian.hzrf_oa.ui.leave.util.LogUtil;
import com.zhilian.hzrf_oa.ui.leave.util.StrKit;
import com.zhilian.hzrf_oa.ui.leave.weidget.DatePickerFragment;
import com.zhilian.hzrf_oa.ui.leave.weidget.EditFragment;
import com.zhilian.hzrf_oa.ui.widget.NoScrollListView;
import com.zhilian.hzrf_oa.util.OpinionUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017-12-29.
 */

public class LeaveDetailActivity extends BaseActivity implements ILeaveDetailView {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.uname)
    TextView mUname;
    @BindView(R.id.dname)
    TextView mDname;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.married)
    TextView mMarried;
    @BindView(R.id.approvedate)
    TextView mApprovedate;
    @BindView(R.id.dayt)
    EditText mDayt;
    @BindView(R.id.begindate)
    TextView mBegindate;
    @BindView(R.id.enddate)
    TextView mEnddate;

    @BindView(R.id.fj_list)
    NoScrollListView mFjList;
    @BindView(R.id.backdate)
    TextView mBackdate;
    @BindView(R.id.days)
    TextView mDays;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;
    @BindView(R.id.bt_save)
    Button mBtSave;

    @BindView(R.id.bt_return)
    Button mBtReturn;
    @BindView(R.id.bt_update)
    Button mBtUpdate;

    @BindViews({R.id.bt_reason, R.id.bt_opinion1, R.id.bt_opinion2})
    List<Button> mWriteBtns;

    @BindViews({R.id.bt_save, R.id.bt_submit, R.id.bt_return})
    List<Button> mSubmitBtns;

    @BindViews({R.id.reason, R.id.opinion1, R.id.opinion2})
    List<TextView> mWriteEts;

    private int task = 1111;
    private int step = 0;

    private LeaveDetailBean leave;
    private TodoItemBean itemBean;
    private List<String> types;
    private List<String> dnames;
    private List<String> marrieds;
    private String[] typeRes;
    private String[] dnameRes;
    private String[] marriedRes;
    private boolean isCache = false;
    private LeaveDetailPresenter mPresenter;

    @Override
    public void initData() {
       task = getIntent().getIntExtra(LocalConstants.TASK_KEY, 0);
       mPresenter = new LeaveDetailPresenter(this);

        docid = getIntent().getStringExtra("docid");
        isdone = getIntent().getStringExtra("isdone");

        addData();

        typeRes = getResources().getStringArray(R.array.apply_types);
        dnameRes = getResources().getStringArray(R.array.apply_dnames);
        marriedRes = getResources().getStringArray(R.array.apply_married);
        types = Arrays.asList(typeRes);
        dnames = Arrays.asList(typeRes);
        marrieds = Arrays.asList(typeRes);
    }


    @Override
    public void setApplyDate(String date) {
        mApprovedate.setText(date);
        leave.setApprovedate(date);
    }

    @Override
    public void setBeginDate(String date) {
        mBegindate.setText(date);
        leave.setBegindate(date);
       try{
           updateDayt();
       }catch (Exception e){
           Toast.makeText(this, "计算失败！", Toast.LENGTH_SHORT).show();
       }
    }

    private void updateDayt() {
        if (StrKit.notBlank(leave.getBegindate()) && StrKit.notBlank(leave.getEnddate())) {
            int dayt = DateUtil.getInstance().diffDays(leave.getEnddate(), leave.getBegindate());
            mDayt.setText("" + dayt);
        }
    }

    @Override
    public void setEndDate(String date) {
        mEnddate.setText(date);
        leave.setEnddate(date);
        try{
            updateDayt();
        }catch (Exception e){
            Toast.makeText(this, "计算失败！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setBackDate(String date) {
        mBackdate.setText(date);
        leave.setBackdate(date);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_leave_detail;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void clearRes() {

    }


    @OnClick(R.id.iv_back)
    public void onMIvBackClicked() {
        finish();
    }

    @OnClick(R.id.bt_submit)
    public void onMBtSubmitClicked() {

    }

    @OnClick(R.id.bt_save)
    public void onMBtSaveClicked() {
        String dayt = mDayt.getText().toString().trim();
        String reason = mWriteEts.get(0).getText().toString().trim();
        if (StrKit.notBlank(dayt)) {
            leave.setDayt(dayt);
        }
        if (StrKit.notBlank(reason)) {
            leave.setReason(reason);
        }
        new CacheUtil(this, Constants.CACHE_FILE_XML).saveObject(LocalConstants.CACHE_APPLY_KEY, leave);
    }


    @OnClick({R.id.approvedate, R.id.begindate, R.id.enddate})
    public void onSubmitClicked(View view) {
        String tempStr = "";
        switch (view.getId()) {
            case R.id.approvedate:
                tempStr = leave.getApprovedate();
                break;
            case R.id.begindate:
                tempStr = leave.getBegindate();
                break;
            case R.id.enddate:
                tempStr = leave.getEnddate();
                break;
        }
        DatePickerFragment dialog = new DatePickerFragment(this, view.getId(), tempStr);
        dialog.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }


    @Override
    public void onReasonChanged(String reason) {
        mWriteEts.get(0).setText(reason);
    }

    @Override
    public void onOpinion1Changed(String opinion) {
        mWriteEts.get(1).setText(OpinionUtil.getOpinion("",opinion,LocalConstants.USER_NAME));
        leave.setOpinion1(opinion);
        if (StrKit.notBlank(opinion)){
            mSubmitBtns.get(1).setVisibility(View.VISIBLE);
        }
        mPresenter.saveOpinion("editopinion",leave);
    }

    @Override
    public void onOpinion2Changed(String opinion) {
        mWriteEts.get(2).setText(opinion);
        if (StrKit.notBlank(opinion)){
            mSubmitBtns.get(1).setVisibility(View.VISIBLE);
        }
        mPresenter.saveOpinion("editopinion",leave);
    }


    @Override
    public void onGetLeaveDetailSuccess(LeaveDetailBean bean) {
        //updateView();
    }

    @Override
    public void onSaveOpinionSuccess(String result) {

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveOpinionFailure(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    private void updateView() {
        mUname.setText(leave.getUname());
        mType.setText(leave.getType());
        mDname.setText(leave.getDname());
        mMarried.setText(leave.getMarried());
        mApprovedate.setText(leave.getApprovedate());
        mDayt.setText("" + leave.getDayt());
        mBegindate.setText(leave.getBegindate());
        mEnddate.setText(leave.getEnddate());
        mWriteEts.get(0).setText(leave.getReason());
        //mFjList.setAdapter();
        mWriteEts.get(1).setText(OpinionUtil.getOpinion(leave.getOpinion1(),"",LocalConstants.USER_NAME));
        mWriteEts.get(2).setText(leave.getOpinion2());
        mBackdate.setText(leave.getBackdate());
        mDays.setText(leave.getDays());
    }


    @OnClick({R.id.bt_reason, R.id.bt_opinion1, R.id.bt_opinion2})
    public void onWriteClicked(View view) {
        new EditFragment(view.getId(),
            String.valueOf(leave.getWf().getId()),
            String.valueOf(leave.getItemid()),
            leave.getOpinions().toArray(new String[]{})).show(getFragmentManager(), "write");
    }

    BusinessContant bc = new BusinessContant();
    Common common = new Common();
    private String docid;
    private String isdone;

    private void addData() {
        new Thread() {
            public void run() {
                String key = bc.getCONFIRM_ID();
                String url = bc.URL;
                Map<String, String> queryParas = common.getQueryParas();
                url = RequestUtil.buildUrlWithQueryString(url, queryParas);
                final InQueryMsg inQueryMsg = new InQueryMsg(1348831860, "query", key);
                inQueryMsg.setQueryName("LeaveDetail");
                HashMap<String, String> map = new HashMap<>();
                map.put("docid", docid);
                map.put("isdone", isdone);
                inQueryMsg.setQueryPara(map);
                String postData = null;
                postData = common.getPostData(inQueryMsg);
                RequestQueue requestQueue = RequestUtil.getRequestQueue();
                LogUtil.e("url = " + url);
                LogUtil.e("postData = " + postData);

                JsonRequest jsonRequest = new JsonStringRequest(Request.Method.POST, url, postData,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            LogUtil.e("response = " + response);
                            if (response.toString().equals(bc.ERROR)) {
                                new TimeOutException().reLogin(getApplicationContext(), new ITimeOutException.CallBack() {
                                    @Override
                                    public void onReloginSuccess() {
                                        addData();
                                    }
                                });
                                // Toast.makeText(LeaveDetail.this, response.toString(), Toast.LENGTH_SHORT).show();
                            } else {
                                leave = JSON.parseObject(response, LeaveDetailBean.class);
                                updateView();
                                switchDisplayBtns();
                            }
                        }
                    }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        LogUtil.e(error.getMessage(), error);
                        Toast.makeText(getApplicationContext(), "出错了!", Toast.LENGTH_LONG).show();
                    }
                });
                requestQueue.add(jsonRequest);
            }
        }.start();
    }

    private void switchDisplayBtns() {
        String opinionField = leave.getOpinionfield();
        String backlaststep = leave.getBacklaststep();
        mApprovedate.setClickable(false);
        mBegindate.setClickable(false);
        mEnddate.setClickable(false);
        switch (task) {
            case LocalConstants.TASK_NEW://如果是新申请请休假，显示"填写事由"、"附件上传"、"保存"、"提交"的按键
                mWriteBtns.get(0).setVisibility(View.VISIBLE);
                mBtUpdate.setVisibility(View.VISIBLE);
                mDayt.setFocusable(true);
                mApprovedate.setClickable(true);
                mBegindate.setClickable(true);
                mEnddate.setClickable(true);
                mSubmitBtns.get(0).setVisibility(View.VISIBLE);
                mSubmitBtns.get(1).setVisibility(View.VISIBLE);
                break;
            case LocalConstants.TASK_TODO://如果是待办请休假

                if (opinionField.equals("opinion1")){
                    mWriteBtns.get(1).setVisibility(View.VISIBLE);
                    if (StrKit.notBlank(leave.getOpinion1())){
                        mSubmitBtns.get(1).setVisibility(View.VISIBLE);
                    }
                }else if (opinionField.equals("opinion2")){
                    mWriteBtns.get(2).setVisibility(View.VISIBLE);
                    if (StrKit.notBlank(leave.getOpinion2())){
                        mSubmitBtns.get(1).setVisibility(View.VISIBLE);
                    }
                }else {
                    mWriteBtns.get(0).setVisibility(View.VISIBLE);
                }
                if (backlaststep.equals("1")){
                    mSubmitBtns.get(2).setVisibility(View.VISIBLE);
                }
                break;
            case LocalConstants.TASK_DONE:
                break;

        }
    }
}
