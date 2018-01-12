package com.zhilian.hzrf_oa.ui.fragment;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.adapter.MessageAdapter;
import com.zhilian.hzrf_oa.common.BusinessContant;
import com.zhilian.hzrf_oa.entity.MessageBean;
import com.zhilian.hzrf_oa.ui.activity.InnerSendManagerActivity;
import com.zhilian.hzrf_oa.ui.activity.MainActivity;
import com.zhilian.hzrf_oa.ui.activity.ReceiveManageActivity;
import com.zhilian.hzrf_oa.ui.leave.view.LeaveManagerActivity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhilian.api.InQueryMsg;
import com.zhilian.api.JsonStringRequest;
import com.zhilian.api.RequestUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 消息
 */
public class ChatsFragment extends Fragment implements View.OnClickListener {
	private MainActivity activity;
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private String mParam1;
	private String mParam2;

	private ListView listView;
	private MessageAdapter adapter;
	private List<MessageBean> list = new ArrayList<MessageBean>();

	RelativeLayout remind1;// 收文督办
	RelativeLayout remind2;// 待阅文件
	RelativeLayout remind3;// 待办收文
	RelativeLayout remind4;// 待办发文
	RelativeLayout remind5;// 待办请休假
	RelativeLayout remind6;// 待办会议
	TextView news_count1;// 收文督办（数）
	TextView news_count2;// 待阅文件（数）
	TextView news_count3;// 待办收文（数）
	TextView news_count4;// 待办发文（数）
	TextView news_count5;// 待办请休假（数）
	TextView news_count6;// 待办会议（数）

	NotificationManager mNotificationManager;// 状态栏通知的管理类

	private OnFragmentInteractionListener mListener;

	public static ChatsFragment newInstance(String param1, String param2) {
		ChatsFragment fragment = new ChatsFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public ChatsFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.message_layout, container, false);
		activity = (MainActivity) getActivity();

		listView = (ListView) v.findViewById(R.id.message_listView);
		adapter = new MessageAdapter(list, activity);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast.makeText(MessageActivity.this, "正在开发中...",
//						Toast.LENGTH_SHORT).show();
			}
		});

		//MyNotification();
		initView(v);// 初始化控件

		return v;
	}

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
		//addData();// 添加测试数据
		getRemind();// 获取消息数
	}

	private void initView(View v) {
		remind1 = (RelativeLayout) v.findViewById(R.id.remind1);
		remind2 = (RelativeLayout) v.findViewById(R.id.remind2);
		remind3 = (RelativeLayout) v.findViewById(R.id.remind3);
		remind4 = (RelativeLayout) v.findViewById(R.id.remind4);
		remind5 = (RelativeLayout) v.findViewById(R.id.remind5);
		remind6 = (RelativeLayout) v.findViewById(R.id.remind6);
		news_count1 = (TextView) v.findViewById(R.id.news_count1);
		news_count2 = (TextView) v.findViewById(R.id.news_count2);
		news_count3 = (TextView) v.findViewById(R.id.news_count3);
		news_count4 = (TextView) v.findViewById(R.id.news_count4);
		news_count5 = (TextView) v.findViewById(R.id.news_count5);
		news_count6 = (TextView) v.findViewById(R.id.news_count6);

		remind1.setOnClickListener(this);
		remind2.setOnClickListener(this);
		remind3.setOnClickListener(this);
		remind4.setOnClickListener(this);
		remind5.setOnClickListener(this);
		remind6.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.remind1:// 收文督办
				Toast.makeText(activity, "提示：暂未开通", Toast.LENGTH_SHORT).show();
				break;
			case R.id.remind2:// 待阅文件
				Toast.makeText(activity, "提示：暂未开通", Toast.LENGTH_SHORT).show();
				break;
			case R.id.remind3:// 待办收文
				Intent intent3 = new Intent(activity, ReceiveManageActivity.class);
				startActivity(intent3);
				//finish();
				break;
			case R.id.remind4:// 内部发文
				Intent intent4 = new Intent(activity, InnerSendManagerActivity.class);
				startActivity(intent4);
				//finish();
				break;
			case R.id.remind5:// 待办请休假
				Intent intent5 = new Intent(activity, LeaveManagerActivity.class);
				startActivity(intent5);
				break;
			case R.id.remind6:// 待办会议
				Toast.makeText(activity, "提示：暂未开通", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	private void getRemind() {
		BusinessContant bc = new BusinessContant();
		String key = bc.getCONFIRM_ID();
		String url = bc.URL;
		InQueryMsg inQueryMsg = new InQueryMsg(1348831860, "query", key);
		inQueryMsg.setQueryName("getRemindInfo");
		HashMap<String, String> map = new HashMap<>();
		map.put("getRemindInfo", "工作提醒");
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
						JSONObject dataJson = null;
						try {
							dataJson = new JSONObject(response.toString());

							String docreceivenum = dataJson.getString("docreceivenum");
							String docsendnum = dataJson.getString("docsendnum");
							String innersendnum = dataJson.getString("innersendnum");
							String leavenum = dataJson.getString("leavenum");
							news_count3.setText(docreceivenum);
							news_count4.setText(innersendnum);
							news_count5.setText(leavenum);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

				Toast.makeText(activity, "出错了!", Toast.LENGTH_LONG).show();
			}
		});
		requestQueue.add(jsonRequest);
	}

	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		getRemind();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

}
