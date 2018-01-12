package com.zhilian.hzrf_oa.ui.leave.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.zhilian.hzrf_oa.adapter.MySpinnerAdapter;
import com.zhilian.hzrf_oa.adapter.SelectmanAdapter;
import com.zhilian.hzrf_oa.common.BusinessContant;
import com.zhilian.hzrf_oa.common.Common;
import com.zhilian.hzrf_oa.json.T_Selectman;
import com.zhilian.hzrf_oa.base.BaseActivity;
import com.zhilian.hzrf_oa.ui.widget.DatePickerFragment;
import com.zhilian.hzrf_oa.ui.widget.EditFragment;
import com.zhilian.rxapi.bean.LeaveDetailBean;
import com.zhilian.rxapi.constant.Constants;
import com.zhilian.rxapi.constant.ErrorConstants;
import com.zhilian.rxapi.constant.LocalConstants;
import com.zhilian.hzrf_oa.listener.OnItemSelectedListenerImpl;
import com.zhilian.hzrf_oa.ui.leave.presenter.LeaveDetailPresenter;
import com.zhilian.hzrf_oa.util.CacheUtil;
import com.zhilian.hzrf_oa.util.DateUtil;
import com.zhilian.hzrf_oa.util.LogUtil;
import com.zhilian.hzrf_oa.util.StrKit;
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
	@BindView(R.id.sp_dayt_type)
	Spinner mDayType;
	@BindView(R.id.dayt)
	EditText mDayt;
	@BindView(R.id.dayt_unit)
	TextView mDayUnit;
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
	@BindView(R.id.history)
	TextView mHistory;
	@BindView(R.id.njqk)
	TextView mNjqk;

	private int task = 0;
	private LeaveDetailBean leave;
	private LeaveDetailPresenter mPresenter;
	private String fileName = "leave";
	private int index;


	@Override
	public void initData() {
		task = getIntent().getIntExtra("task", 0);
		mPresenter = new LeaveDetailPresenter(this);
		docid = getIntent().getStringExtra("docid");
		index = getIntent().getIntExtra("index", 0);
		isdone = getIntent().getStringExtra("isdone");
		switch (task) {
			case Constants.TASK_NEW:
			case Constants.TASK_TODO:
				if (!"0".equals(docid)) {
					try {
						leave = new CacheUtil(getApplicationContext(), fileName).
							getObject(LocalConstants.USER_NAME + "-" + docid, LeaveDetailBean.class);
						updateView();
						switchDisplayBtns();
					} catch (Exception e) {
						LogUtil.e(ErrorConstants.ERROR_LOAD_CACHE_LEAVE);
					}
				}
				if (null == leave){
					mPresenter.getLeaveDetail(docid, isdone);
				}
				break;
			case Constants.TASK_DONE:
				mPresenter.getLeaveDetail(docid, isdone);
				break;
		}


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
	}

	private void updateDayt() {
		int dayt = 0;
		if (StrKit.notBlank(leave.getBegindate()) && StrKit.notBlank(leave.getEnddate())) {
			dayt = DateUtil.getInstance().diffDays(leave.getEnddate(), leave.getBegindate());
		}
		if (dayt > 0) {
			mPresenter.getLeaveDayt(leave.getBegindate(), leave.getEnddate());
		} else {

		}
	}

	@Override
	public void setEndDate(String date) {
		mEnddate.setText(date);
		leave.setEnddate(date);
		updateDayt();
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
		if (task == Constants.TASK_NEW && null != leave) {
			new CacheUtil(this, fileName).saveObject(LocalConstants.USER_NAME + "-" + leave.getId(), leave);
		}
		finish();
	}

	@OnClick(R.id.bt_submit)
	public void onMBtSubmitClicked() {
		submit();
		Intent intent = new Intent();
		intent.putExtra("index", index);
		setResult(Constants.TASK_TODO, intent);
		//finish();
	}

	@OnClick(R.id.bt_save)
	public void onMBtSaveClicked() {
		new CacheUtil(this, fileName).saveObject(LocalConstants.USER_NAME + "-" + leave.getId(), leave);
		setResult(Constants.TASK_NEW);
		finish();
	}


	@OnClick({R.id.approvedate, R.id.begindate, R.id.enddate})
	public void onSelectDateClicked(View view) {
		String tempStr = "";
		if (null != leave) {
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
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (task == Constants.TASK_NEW) {
				if (null != leave){
					new CacheUtil(this, fileName).saveObject(LocalConstants.USER_NAME + "-" + leave.getId(), leave);
				}
			}
			finish();
		}
		return super.onKeyDown(keyCode,event);
	}


	/**
	 * 显示填写的请假原因
	 *
	 * @param reason
	 */
	@Override
	public void onReasonChanged(String reason) {
		mWriteEts.get(0).setText(reason);
		leave.setReason(reason);
		if (StrKit.notBlank(reason)) {
			mSubmitBtns.get(0).setVisibility(View.VISIBLE);
			mSubmitBtns.get(1).setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 显示填写的审核意见
	 *
	 * @param opinion
	 */
	@Override
	public void onOpinion1Changed(String opinion) {
		mWriteEts.get(1).setText(OpinionUtil.getOpinion("", opinion, LocalConstants.USER_NAME));
		leave.setOpinion1(opinion);
		if (StrKit.notBlank(opinion)) {
			mSubmitBtns.get(1).setVisibility(View.VISIBLE);
		}
		mPresenter.saveOpinion(Constants.SAVE_OPINION, leave);
	}

	/**
	 * 显示填写的审批意见	 *
	 * @param opinion
	 */
	@Override
	public void onOpinion2Changed(String opinion) {
		mWriteEts.get(2).setText(opinion);
		if (StrKit.notBlank(opinion)) {
			mSubmitBtns.get(1).setVisibility(View.VISIBLE);
		}
		mPresenter.saveOpinion("editopinion", leave);
	}

	/**
	 * 显示加载成功的请假详情
	 *
	 * @param bean
	 */
	@Override
	public void onGetLeaveDetailSuccess(LeaveDetailBean bean) {
		leave = bean;
		updateView();
		switchDisplayBtns();
	}

	/**
	 * 显示保存意见成功时的返回结果
	 *
	 * @param result
	 */
	@Override
	public void onSaveOpinionSuccess(String result) {
		Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示保存意见失败时的返回结果
	 *
	 * @param result
	 */
	@Override
	public void onSaveOpinionFailure(String result) {
		Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 更新请假天数
	 *
	 * @param dayt
	 */
	@Override
	public void updateDayt(String dayt) {
		mDayt.setText(dayt);
		leave.setDayt(dayt);
	}

	@Override
	public void onDisconnected(String message) {
		if (message.equals(Constants.RESPONSE_ERROR)) {
			//TODO 重新获取key（重新登陆),并请求数据
		} else {
			Toast.makeText(this, Constants.LEAVE_DETAIL_ERROR, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 更新页面数据
	 */
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
		String daye = leave.getDaye().trim();
		String workyear = leave.getWorkyear().trim();
		String dayc = leave.getDayc().trim();
		String dayg = leave.getDayg().trim();
		String dayn = leave.getDayn().trim();
		final String[] types = leave.getTypes().toArray(new String[]{});
		mNjqk.setText("工龄 " + workyear + " 年，可休年假 " + daye + " 天，已休 " + dayn + " 天，剩余 " + dayc + " 天");
		if (null != type) {
			for (int i = 0; i < types.length; i++) {
				if (type.equals(types[i]))
					mType.setSelection(i);
			}
		}
		mType.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				leave.setType(types[i]);
			}
		});
		mType.setAdapter(new MySpinnerAdapter(this,types));
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
		mDayType.setAdapter(new MySpinnerAdapter(this,Constants.LEAVE_DAYT_TYPE));

		if (StrKit.notBlank(dayg)){
			int i = Arrays.asList(Constants.LEAVE_DAYT_TYPE).indexOf(dayg);
			mDayType.setSelection(i);
			if (i == 0){
				mDayt.setVisibility(View.VISIBLE);
				mDayUnit.setVisibility(View.VISIBLE);
			}
		}else {
			mDayType.setSelection(0);
			mDayt.setVisibility(View.VISIBLE);
			mDayUnit.setVisibility(View.VISIBLE);
		}
		mDayType.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				leave.setDayg(Constants.LEAVE_DAYT_TYPE[i]);
				switch (i) {
					case 0://多天
						mDayt.setVisibility(View.VISIBLE);
						mDayUnit.setVisibility(View.VISIBLE);
						break;
					case 1://一天
						leave.setDayt("1.0");
						mDayt.setVisibility(View.INVISIBLE);
						mDayUnit.setVisibility(View.INVISIBLE);
						break;
					case 3:
					case 2://半天
						mDayt.setVisibility(View.INVISIBLE);
						mDayUnit.setVisibility(View.INVISIBLE);
						leave.setDayt("0.5");
						break;
				}
				mDayt.setText(leave.getDayt());
			}
		});

	}

	/**
	 * 响应 填写请假原因、审核意见、审批意见 等Button点击事件
	 *
	 * @param view
	 */
	@OnClick({R.id.bt_reason, R.id.bt_opinion1, R.id.bt_opinion2})
	public void onWriteClicked(View view) {
		new EditFragment(view.getId(),
			String.valueOf(leave.getWf().getId()),
			String.valueOf(leave.getItemid()),
			leave.getOpinions().toArray(new String[]{})).show(getFragmentManager(), "write");
	}

	/**
	 * 切换视图中的按钮显示状态
	 */
	private void switchDisplayBtns() {
		String opinionField = leave.getOpinionfield();
		String backlaststep = leave.getBacklaststep();
		switch (task) {
			case LocalConstants.TASK_NEW://如果是新申请请休假，显示"填写事由"、"附件上传"、"保存"、"提交"的按键
				if (StrKit.notBlank(leave.getReason())) {
					mSubmitBtns.get(0).setVisibility(View.VISIBLE);
					mSubmitBtns.get(1).setVisibility(View.VISIBLE);
				}
				mWriteBtns.get(0).setVisibility(View.VISIBLE);
				break;
			case LocalConstants.TASK_TODO://如果是待办请休假

				mType.setEnabled(false);
				mDayType.setEnabled(false);
				mDayt.setKeyListener(null);
				mBegindate.setKeyListener(null);
				mEnddate.setKeyListener(null);
				mApprovedate.setKeyListener(null);
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
				mType.setEnabled(false);
				mDayType.setEnabled(false);
				mDayt.setKeyListener(null);
				mBegindate.setKeyListener(null);
				mEnddate.setKeyListener(null);
				mApprovedate.setKeyListener(null);
				break;

		}
	}


	/**
	 * 以下内容来自 copy
	 */
	BusinessContant bc = new BusinessContant();
	Common common = new Common();
	private String docid;
	private String isdone;
	private TextView tv_title;
	private RadioGroup radioGroup;
	private ListView mansListview;
	private RadioButton tempButton;

	private List<T_Selectman> selectmenlist = new ArrayList<T_Selectman>();
	SelectmanAdapter selectmanAdapter;
	String tempopinion = "";
	private String atype;
	private AlertDialog alertDialog;
	private String checked_id;

	//TODO 提交
	public void submit() {
		bc.setItem_id("" + leave.getItemid());
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		atype = leave.getAtype();
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

	public void selectman(String operation1) {
		final String operation = operation1;


		if (operation.equals("opinion1")) {
			tempopinion = mWriteEts.get(1).getText().toString();
		} else if (operation.equals("opinion2")) {
			tempopinion = mWriteEts.get(2).getText().toString();
		} else if (operation.equals("")) {
			tempopinion = mWriteEts.get(0).getText().toString();
		}
		if (StrKit.notBlank(leave.getOpinionfield()) && StrKit.isBlank(tempopinion)) {
			Toast.makeText(LeaveDetailActivity.this, "请填写意见!", Toast.LENGTH_SHORT).show();
		} else if (task == Constants.TASK_NEW && StrKit.isBlank(leave.getReason())) {
			Toast.makeText(LeaveDetailActivity.this, "请填写请假事由!", Toast.LENGTH_SHORT).show();
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
						insaveMsg.setModelName("leavesave");
						HashMap<String, String> map = new HashMap<>();
						map.put("nexttype", bc.getNexttype());//要改的
						if (atype.equals("3") || operation.equals("TuiHuiShangBu")) {//如果为完成操作或是退货操作，传回当前环节
							map.put("nextitemid", bc.getItem_id());
						} else {
							map.put("nextitemid", bc.getCheckid());
						}
						map.put("nexttodoman", bc.getUserid());//选中人的id
						map.put("pid", bc.getPid());
						map.put("approvedate", leave.getApprovedate());
						map.put("begindate", leave.getBegindate());
						map.put("enddate", leave.getEnddate());
						map.put("type", leave.getType());
						map.put("dayt", leave.getDayt());
						map.put("reason", leave.getReason());
						map.put("doc", "leave");
						map.put("operation", operation);
						map.put("opinionfield", leave.getOpinionfield());
						map.put("opinion", tempopinion);
						map.put("flow", "leave");
						if (operation.equals("WanCheng")) {
							map.put("backdate", leave.getBackdate());
							map.put("days", leave.getDays());
						}
						insaveMsg.setModelProperty(map);
						String postData = null;
						ObjectMapper mapper = new ObjectMapper();
						try {
							postData = mapper.writeValueAsString(insaveMsg);
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}

						RequestQueue requestQueue = RequestUtil.getRequestQueue();

						JsonRequest jsonRequest = new JsonStringRequest(Request.Method.POST, url, postData,
							new Response.Listener<String>() {
								@Override
								public void onResponse(String response) {

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
								Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
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
					bc.setPid("" + leave.getWf().getId());
					map.put("pid", bc.getPid());
					map.put("doc", "leave");
					map.put("condition", leave.getDayt());
					inQueryMsg.setQueryPara(map);
					String postData = null;
					ObjectMapper mapper = new ObjectMapper();
					try {
						postData = mapper.writeValueAsString(inQueryMsg);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}

					RequestQueue requestQueue = RequestUtil.getRequestQueue();

					JsonRequest jsonRequest = new JsonStringRequest(Request.Method.POST, url, postData,
						new Response.Listener<String>() {
							@Override
							public void onResponse(String response) {


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
