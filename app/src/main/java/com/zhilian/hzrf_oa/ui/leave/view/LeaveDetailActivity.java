package com.zhilian.hzrf_oa.ui.leave.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhilian.api.InQueryMsg;
import com.zhilian.api.InSaveMsg;
import com.zhilian.api.JsonStringRequest;
import com.zhilian.api.JsonUtil;
import com.zhilian.api.ParaMap;
import com.zhilian.api.RequestUtil;
import com.zhilian.api.Sign;
import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.adapter.SelectmanAdapter;
import com.zhilian.hzrf_oa.common.BusinessContant;
import com.zhilian.hzrf_oa.common.Common;
import com.zhilian.hzrf_oa.json.T_Selectman;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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
    Spinner mType;
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

    /*  @BindView(R.id.fj_list)
      NoScrollListView mFjList;*/
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
   /* @BindView(R.id.bt_update)
    Button mBtUpdate;*/

    @BindViews({R.id.bt_reason, R.id.bt_opinion1, R.id.bt_opinion2})
    List<Button> mWriteBtns;

    @BindViews({R.id.bt_save, R.id.bt_submit, R.id.bt_return})
    List<Button> mSubmitBtns;

    @BindViews({R.id.reason, R.id.opinion1, R.id.opinion2})
    List<TextView> mWriteEts;

    private int task = 0;
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
    private String fileName = "apply.xml";


    @Override
    public void initData() {
        task = getIntent().getIntExtra("task", 0);
        mPresenter = new LeaveDetailPresenter(this);
        docid = getIntent().getStringExtra("doc_id");
        isdone = getIntent().getStringExtra("isdone");
        mPresenter.getLeaveDetail(docid, isdone);
        //addData();
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
        try {
            updateDayt();
        } catch (Exception e) {
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
        try {
            updateDayt();
        } catch (Exception e) {
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

    @OnClick(R.id.dayt)
    public void onFoucsDayt() {
        mDayt.setFocusable(true);
    }

    @OnClick(R.id.iv_back)
    public void onMIvBackClicked() {
        finish();
    }

    @OnClick(R.id.bt_submit)
    public void onMBtSubmitClicked() {
        submit();
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
        new CacheUtil(this, fileName).saveObject("leave", leave);
    }


    @OnClick({R.id.approvedate, R.id.begindate, R.id.enddate})
    public void onSelectDateClicked(View view) {
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
        mWriteEts.get(1).setText(OpinionUtil.getOpinion("", opinion, LocalConstants.USER_NAME));
        leave.setOpinion1(opinion);
        if (StrKit.notBlank(opinion)) {
            mSubmitBtns.get(1).setVisibility(View.VISIBLE);
        }
        mPresenter.saveOpinion(Constants.SAVE_OPINION, leave);
    }

    @Override
    public void onOpinion2Changed(String opinion) {
        mWriteEts.get(2).setText(opinion);
        if (StrKit.notBlank(opinion)) {
            mSubmitBtns.get(1).setVisibility(View.VISIBLE);
        }
        mPresenter.saveOpinion("editopinion", leave);
    }


    @Override
    public void onGetLeaveDetailSuccess(LeaveDetailBean bean) {
        leave = bean;
        updateView();
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
        String uname = leave.getUname();
        String dname = leave.getDname();
        String married = leave.getMarried();
        String type = leave.getType();
        String approvedate = leave.getApprovedate();
        String dayt = leave.getDayt();
        String begindate = leave.getBegindate();
        String enddate = leave.getEnddate();
        String reason = leave.getReason();
        String opinion1 = leave.getOpinion1();
        String opinion2 = leave.getOpinion2();
        String backdate = leave.getBackdate();
        String days = leave.getDays();
        if (null != type) {
            for (int i = 0; i < typeRes.length; i++) {
                if (type.equals(typeRes[i]))
                    mType.setSelection(i);
            }
        }
        if (StrKit.notBlank(dname)) {
            mDname.setText(dname);
        }
        if (StrKit.notBlank(married)) {
            mMarried.setText(married);
        }
        if (StrKit.notBlank(uname)) {
            mUname.setText(uname);
        }
        if (StrKit.notBlank(approvedate)) {
            mApprovedate.setText(approvedate);
        }
        if (StrKit.notBlank(dayt)) {
            mDayt.setText(dayt);
        }
        if (StrKit.notBlank(begindate)) {
            mBegindate.setText(begindate);
        }
        if (StrKit.notBlank(enddate)) {
            mEnddate.setText(enddate);
        }
        if (StrKit.notBlank(backdate)) {
            mBackdate.setText(backdate);
        }
        if (StrKit.notBlank(days)) {
            mDays.setText(days);
        }
        if (StrKit.notBlank(reason)) {
            mWriteEts.get(0).setText(reason);
        }
        if (StrKit.notBlank(opinion1)) {
            mWriteEts.get(1).setText(OpinionUtil.getOpinion(opinion1, "", LocalConstants.USER_NAME));
        }
        if (StrKit.notBlank(opinion2)) {
            mWriteEts.get(2).setText(OpinionUtil.getOpinion(opinion2, "", LocalConstants.USER_NAME));
        }


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
                //  mBtUpdate.setVisibility(View.VISIBLE);
                mDayt.setFocusable(true);
                mApprovedate.setClickable(true);
                mBegindate.setClickable(true);
                mEnddate.setClickable(true);
                mSubmitBtns.get(0).setVisibility(View.VISIBLE);
                mSubmitBtns.get(1).setVisibility(View.VISIBLE);
                break;
            case LocalConstants.TASK_TODO://如果是待办请休假

                if (opinionField.equals("opinion1")) {
                    mWriteBtns.get(1).setVisibility(View.VISIBLE);
                    if (StrKit.notBlank(leave.getOpinion1())) {
                        mSubmitBtns.get(1).setVisibility(View.VISIBLE);
                    }
                } else if (opinionField.equals("opinion2")) {
                    mWriteBtns.get(2).setVisibility(View.VISIBLE);
                    if (StrKit.notBlank(leave.getOpinion2())) {
                        mSubmitBtns.get(1).setVisibility(View.VISIBLE);
                    }
                } else {
                    mWriteBtns.get(0).setVisibility(View.VISIBLE);
                }
                if (backlaststep.equals("1")) {
                    mSubmitBtns.get(2).setVisibility(View.VISIBLE);
                }
                break;
            case LocalConstants.TASK_DONE:
                break;

        }
    }

    private TextView tv_title;
    private RadioGroup radioGroup;
    private ListView mansListview;
    private RadioButton tempButton;

    private String atype;
    private AlertDialog alertDialog;
    private String checked_id;
    //TODO 提交
    public void submit() {
        bc.setItem_id(""+leave.getItemid());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        atype = leave.getAtype();
        LogUtil.e("atype = " + atype);
        if (atype.equals("3")) {//环节类型 3为结束 实现完成操作
            builder.setTitle("确认窗口");
            builder.setMessage("此操作将结束流程，是否继续？");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    selectman("WanCheng");
                }
            });
            alertDialog = builder.create();
            alertDialog.show();
        } else {//查找下一处理人或下一环节
            builder.setTitle("下一环节");
            View view = getLayoutInflater().inflate(R.layout.button3_layout, null);
            //stepListView=(ListView)view.findViewById(R.id.steps);
            tv_title = (TextView) view.findViewById(R.id.title);
            radioGroup = (RadioGroup) view.findViewById(R.id.selectman);
            mansListview = (ListView) view.findViewById(R.id.selectmans);

            if (bc.getItem_id() != null) {
                if (Common.judgeNet(this)) {
                    Fasong();
                    if (StrKit.isBlank(bc.getNexttype()) ? false : bc.getNexttype().equals("toast")) {
                        builder.setTitle("下一环节处理人");
                    }
                }
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
                        tempButton = (RadioButton) findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        // 以下就可以对这个RadioButton进行处理了
                        checked_id = String.valueOf(checkedId);
                        if (bc.getNexttype().equals("toast")) {
                            bc.setUserid(checked_id);//当前选中人
                        } else {
                            bc.setCheckid(checked_id);//选中环节
                        }

                    }
                });

                builder.setView(view);// 使用自定义布局作为对话框内容
                builder.setPositiveButton("确定", null);

                // 负面语义按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        bc.setCheckid("");
                        bc.setUserid("");
                    }
                });

                alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (com.zhilian.api.StrKit.notBlank(bc.getCheckid())) {
                            checked_id = bc.getCheckid();
                        }
                        if (checked_id != null) {
                            selectman("FaSongdoc");
                        } else {
                            Toast.makeText(LeaveDetailActivity.this, "请选择！", Toast.LENGTH_SHORT).show();
                        }

                        if (bc.getNexttype().equals("toast")) {
                            alertDialog.dismiss();

                        } else {
                            alertDialog.setTitle("下一环节处理人");
                            alertDialog.show();
                        }
                    }

                });
            }
        }
    }

    private List<T_Selectman> selectmenlist = new ArrayList<T_Selectman>();
    SelectmanAdapter selectmanAdapter;

    public void selectDMan() {
        String key = bc.getCONFIRM_ID();
        String url = bc.URL;
        Map<String, String> queryParas = Common.getQueryParas();
        url = RequestUtil.buildUrlWithQueryString(url, queryParas);
        InQueryMsg inQueryMsg = new InQueryMsg(1348831860, "query", key);
        inQueryMsg.setQueryName("getManInDept");
        HashMap<String, String> map = new HashMap<>();
        inQueryMsg.setQueryPara(map);
        String postData = Common.getPostData(inQueryMsg);
        RequestQueue requestQueue = RequestUtil.getRequestQueue();

        JsonRequest jsonRequest = new JsonStringRequest(Request.Method.POST, url, postData,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       LogUtil.e("response:"+response.toString());
                        selectmenlist.clear();
                        if (!response.equals(bc.ERROR)) {
                            List<T_Selectman> list = JSON.parseArray(response, T_Selectman.class);
                            for (int i = 0; i < list.size(); i++) {
                                selectmenlist.add(list.get(i));
                            }
                            selectmanAdapter = new SelectmanAdapter(selectmenlist, LeaveDetailActivity.this);
                            mansListview.setAdapter(selectmanAdapter);
                        } else {
                            new TimeOutException().reLogin(getApplicationContext(), new ITimeOutException.CallBack() {
                                @Override
                                public void onReloginSuccess() {
                                    selectDMan();
                                }
                            });
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                com.zhilian.hzrf_oa.util.LogUtil.e("e", error.getMessage());
                Toast.makeText(getApplicationContext(), "出错了!", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonRequest);
    }

    String tempopinion = "";

    public void selectman(String operation1) {
        final String operation = operation1;


        if (operation.equals("opinion1")) {
            tempopinion = mWriteEts.get(1).getText().toString();
        } else if (operation.equals("opinion2")) {
            tempopinion = mWriteEts.get(2).getText().toString();
        } else if (operation.equals("")) {
            tempopinion = mWriteEts.get(0).getText().toString();
        }
        LogUtil.e("opinionfield = "+StrKit.notBlank(leave.getOpinionfield()));
        LogUtil.e("tempopinion = "+StrKit.isBlank(tempopinion));
        if (StrKit.isBlank(leave.getOpinionfield()) && StrKit.isBlank(tempopinion)) {
            Toast.makeText(LeaveDetailActivity.this, "请填写意见!", Toast.LENGTH_SHORT).show();
        } else {
            new Thread() {
                public void run() {
                    try {
                        String key = bc.getCONFIRM_ID();
                        String url = bc.URL;
                        String token = "1lj4hbato30kl1ppytwa1ueqdn";
                        final String encodingAesKey = "InVjlo7czsOWrCSmTPgEUXBzlFnmqpNMQU3ZfilULHyHZiRjVUhxxWpexhYH6f4i";
                        Map<String, String> ret = Sign.sign(url, token, encodingAesKey);
                        String signature = ret.get("signature");
                        String nonceStr = ret.get("nonceStr");
                        String timestamp = ret.get("timestamp");
                        Map<String, String> queryParas = ParaMap.create("accessToken", token)
                                .put("nonce", nonceStr)
                                .put("timestamp", timestamp)
                                .put("signature", signature)
                                .getData();
                        url = RequestUtil.buildUrlWithQueryString(url, queryParas);
                        InSaveMsg insaveMsg = new InSaveMsg(1348831860, "save", key);
                        insaveMsg.setModelName("receivesave");
                        HashMap<String, String> map = new HashMap<>();
                        map.put("nexttype", bc.getNexttype());//要改的
                        if (atype.equals("3") || operation.equals("TuiHuiShangBu")) {//如果为完成操作或是退货操作，传回当前环节
                            map.put("nextitemid", bc.getItem_id());
                        } else {
                            map.put("nextitemid", bc.getCheckid());
                        }
                        map.put("nexttodoman", bc.getUserid());//选中人的id
                        map.put("pid", bc.getPid());
                        map.put("doc", "receive");
                        map.put("operation", operation);
                        map.put("opinionfield", leave.getOpinionfield());
                        map.put("opinion", tempopinion);
                        map.put("flow", "receive");
                        insaveMsg.setModelProperty(map);
                        String postData = null;
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            postData = mapper.writeValueAsString(insaveMsg);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        System.out.println("发送前的明文：" + postData);
                        RequestQueue requestQueue = RequestUtil.getRequestQueue();

                        JsonRequest jsonRequest = new JsonStringRequest(Request.Method.POST, url, postData,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("TAG", "response -> " + response.toString());
                                        try {
                                            JSONObject dataJson = new JSONObject(response);
                                            String nexttype = dataJson.getString("nextType");
                                            String amount = dataJson.getString("type");
                                            if (nexttype.equals("toast")) {
                                                Toast.makeText(getApplicationContext(), amount, Toast.LENGTH_LONG).show();
                                                finish();
                                            } else {
                                                JSONArray users = dataJson.getJSONArray("user");
                                                bc.setNexttype("toast");
                                                String name, sname, pname;
                                                int id;
                                                JSONArray user = dataJson.getJSONArray("user");
                                                List<T_Selectman> list = JsonUtil.getselectmanList(users.toString());
                                                radioGroup.removeAllViews();
                                                for (int i = 0; i < user.length(); i++) {
                                                    id = list.get(i).getId();
                                                    name = list.get(i).getName();
                                                    sname = list.get(i).getD_id();
                                                    pname = list.get(i).getPid();

                                                    if (amount.equals("1")) {
                                                        tempButton = new RadioButton(LeaveDetailActivity.this);

                                                        tempButton.setTextSize(16f);
                                                        tempButton.setText(name + " ( " + pname + " : " + sname + " )");
                                                        tempButton.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离
                                                        tempButton.setId(id);
                                                        //radioGroup.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                                        radioGroup.addView(tempButton);
                                                        if (i == 0) {
                                                            radioGroup.check(tempButton.getId());
                                                            System.out.println("=====");
                                                        }
                                                    } else {
                                                        selectmenlist.add(new T_Selectman(id, name, pname, sname));
                                                    }
                                                }
                                                if (!amount.equals("1")) {
                                                    selectmanAdapter = new SelectmanAdapter(selectmenlist, getApplicationContext());
                                                    mansListview.setAdapter(selectmanAdapter);
                                                }
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                , new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                com.zhilian.hzrf_oa.util.LogUtil.e("e", error.getMessage());
                                Toast.makeText(getApplicationContext(), "出错了!", Toast.LENGTH_LONG).show();
                            }
                        });
                        requestQueue.add(jsonRequest);

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                }
            }.start();
        }
    }

    private void Fasong() {
        new Thread() {
            public void run() {
                try {
                    String key = bc.getCONFIRM_ID();
                    String url = bc.URL;
                    String token = "1lj4hbato30kl1ppytwa1ueqdn";
                    final String encodingAesKey = "InVjlo7czsOWrCSmTPgEUXBzlFnmqpNMQU3ZfilULHyHZiRjVUhxxWpexhYH6f4i";
                    Map<String, String> ret = Sign.sign(url, token, encodingAesKey);
                    String signature = ret.get("signature");
                    String nonceStr = ret.get("nonceStr");
                    String timestamp = ret.get("timestamp");
                    Map<String, String> queryParas = ParaMap.create("accessToken", token)
                            .put("nonce", nonceStr)
                            .put("timestamp", timestamp)
                            .put("signature", signature)
                            .getData();
                    url = RequestUtil.buildUrlWithQueryString(url, queryParas);
                    InQueryMsg inQueryMsg = new InQueryMsg(1348831860, "query", key);
                    inQueryMsg.setQueryName("fasong");
                    HashMap<String, String> map = new HashMap<>();
                    map.put("curitemid", bc.getItem_id());//环节ID
                    bc.setPid(""+leave.getWf().getId());
                    map.put("pid", bc.getPid());
                    map.put("doc", "receive");
                    inQueryMsg.setQueryPara(map);
                    String postData = null;
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        postData = mapper.writeValueAsString(inQueryMsg);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    System.out.println("发送前的明文：" + postData);
                    RequestQueue requestQueue = RequestUtil.getRequestQueue();

                    JsonRequest jsonRequest = new JsonStringRequest(Request.Method.POST, url, postData,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("TAG", "response -> " + response.toString());
                                    System.out.println("解密后：" + response.toString());
                                    try {

                                        JSONObject dataJson = new JSONObject(response.toString());
                                        String type = dataJson.getString("type");
                                        String ato, name;
                                        int id;
                                        selectmenlist.clear();
                                        if (type.equals("9")) {
                                            Toast.makeText(getApplicationContext(), "没有下一处理人，请联系管理员！", Toast.LENGTH_LONG).show();
                                        } else if (type.equals("step")) {
                                            bc.setNexttype("windows");
                                            JSONArray was = dataJson.getJSONArray("trans");
                                            List<T_Selectman> list = JsonUtil.getselectmanList(was.toString());
                                            for (int i = 0; i < list.size(); i++) {

                                                id = list.get(i).getId();
                                                ato = list.get(i).getAto();

                                                tempButton = new RadioButton(LeaveDetailActivity.this);
                                                tempButton.setText(ato);
                                                tempButton.setTextColor(0xFF505050);
                                                tempButton.setTextSize(16f);
                                                tempButton.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离
                                                tempButton.setId(id);
                                                tempButton.setText(ato);
                                                //radioGroup.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

                                                radioGroup.addView(tempButton);
                                                if (i == 0) {
                                                    radioGroup.check(tempButton.getId());
                                                }

                                            }
                                            //selectmanAdapter=new SelectmanAdapter(selectmenlist,getApplicationContext());
                                            //stepListView.setAdapter(selectmanAdapter);
                                            tv_title.setText("下一环节");
                                        } else {
                                            bc.setCheckid(dataJson.getString("nextStep"));
                                            bc.setNexttype("toast");
                                            JSONArray user = dataJson.getJSONArray("user");
                                            String amount = dataJson.getString("type");//你跟踪看一下这个值有没有不一样
                                            String sname, pname;
                                            List<T_Selectman> list = JsonUtil.getselectmanList(user.toString());
                                            for (int i = 0; i < user.length(); i++) {
                                                id = list.get(i).getId();
                                                name = list.get(i).getName();
                                                sname = list.get(i).getD_id();
                                                pname = list.get(i).getPid();

                                                if (amount.equals("1")) {
                                                    tempButton = new RadioButton(LeaveDetailActivity.this);

                                                    tempButton.setTextSize(16f);
                                                    tempButton.setText(name + " ( " + pname + " : " + sname + " )");
                                                    tempButton.setPadding(80, 0, 0, 0);  // 设置文字距离按钮四周的距离
                                                    tempButton.setTextSize(16f);
                                                    tempButton.setId(id);
                                                    //radioGroup.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                                    radioGroup.addView(tempButton);
                                                    if (i == 0) {
                                                        radioGroup.check(tempButton.getId());
                                                    }
                                                } else {
                                                    selectmenlist.add(new T_Selectman(id, name, pname, sname));
                                                }
                                            }
                                            if (!amount.equals("1")) {
                                                selectmanAdapter = new SelectmanAdapter(selectmenlist, getApplicationContext());
                                                mansListview.setAdapter(selectmanAdapter);
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("TAG", error.getMessage(), error);
                            Toast.makeText(getApplicationContext(), "出错了!", Toast.LENGTH_LONG).show();
                        }
                    });
                    requestQueue.add(jsonRequest);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
