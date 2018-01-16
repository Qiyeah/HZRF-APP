package com.zhilian.hzrf_oa.ui.leave.view;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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
import com.zhilian.api.JsonStringRequest;
import com.zhilian.api.ParaMap;
import com.zhilian.api.RequestUtil;
import com.zhilian.api.Sign;
import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.adapter.ApprovesAdapter;
import com.zhilian.hzrf_oa.common.BusinessContant;
import com.zhilian.hzrf_oa.base.BaseFragment;
import com.zhilian.rxapi.bean.LeaveDoneBean;
import com.zhilian.rxapi.constant.Constants;
import com.zhilian.hzrf_oa.ui.widget.CustomListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-12-29.
 */

public class ApprovedFragment extends BaseFragment<LeaveDoneBean.DoneItemBean> {
    @BindView(R.id.view)
    View mView;
    @BindView(android.R.id.list)
    CustomListView mList;
    @BindView(android.R.id.empty)
    TextView mEmpty;
    @BindView(R.id.imageview)
    ImageView mImageview;
    @BindView(R.id.noinfo)
    RelativeLayout mNoinfo;
    private ImageView search;
    private ApprovesAdapter mAdapter;
    private List<LeaveDoneBean.DoneItemBean> mApproves;
    private List<LeaveDoneBean.DoneItemBean> list;

    @Override
    protected void initView() {
        if (null != getActivity()){
            search = (ImageView) getActivity().findViewById(R.id.iv_search);
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mView.setVisibility(View.VISIBLE);
                    showPopupWindow();
                }
            });
            menu=(LinearLayout)getActivity().findViewById(R.id.lt_container);
            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent intent = new Intent(getActivity(), LeaveDetailActivity.class);
                 //   LogUtil.e("approve: docid = "+mApproves.get(position-1).getDocid());
                    intent.putExtra("docid", mApproves.get(position-1).getDocid());
                    intent.putExtra("task", Constants.TASK_DONE);
                    intent.putExtra("isdone", "1");
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    protected int layoutRes() {
        return R.layout.layout_leave_list;
    }
    @Override
    public void onResume() {
        super.onResume();
       if (null != mApproves){
           mAdapter = new ApprovesAdapter(mApproves,getActivity());
           mList.setAdapter(mAdapter);
           mList.onLoadComplete();
       }
    }
    @Override
    public void notifyDoneDataChange(List<LeaveDoneBean.DoneItemBean> list) {
        mApproves = list;
        if (null != getActivity()) {
            if (null == mAdapter) {
                mAdapter = new ApprovesAdapter(list, getActivity());
                mList.setAdapter(mAdapter);
            }
            initView();
            mList.onLoadComplete();
        }

    }
    Integer pageNumber=1;//添加数据页码
    Integer pageNumbers=1;//搜索数据页码
    Handler myhandler= new Handler();
    Runnable eChanged = new Runnable() {
        @Override
        public void run() {
            mAdapter.notifyDataSetChanged();
        }
    };

    public void search(String condition){
        BusinessContant bc = new BusinessContant();
        String key = bc.getCONFIRM_ID();
        String url=bc.URL;
        String token = "1lj4hbato30kl1ppytwa1ueqdn";
        final String encodingAesKey = "InVjlo7czsOWrCSmTPgEUXBzlFnmqpNMQU3ZfilULHyHZiRjVUhxxWpexhYH6f4i";
        Map<String,String> ret = Sign.sign(url, token, encodingAesKey);
        String signature = ret.get("signature");
        String nonceStr = ret.get("nonceStr");
        String timestamp = ret.get("timestamp");
        Map<String, String> queryParas = ParaMap.create("accessToken", token)
            .put("nonce", nonceStr)
            .put("timestamp", timestamp)
            .put("signature", signature)
            .getData();
        url = RequestUtil.buildUrlWithQueryString(url,queryParas);
        InQueryMsg inQueryMsg = new InQueryMsg(1348831860,"query",key);
        inQueryMsg.setQueryName(Constants.QUERY_LEAVE_TODO);
        HashMap<String,String> map = new HashMap<>();
        map.put("projectName","");
        map.put("condition",condition);
        map.put("pageNumber",String.valueOf(pageNumbers));
        inQueryMsg.setQueryPara(map);
        String postData = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            postData = mapper.writeValueAsString(inQueryMsg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue = RequestUtil.getRequestQueue();

        JsonRequest jsonRequest = new JsonStringRequest(Request.Method.POST,url, postData,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (pageNumbers==1){
                        mApproves.clear();
                    }
                    mNoinfo.setVisibility(View.GONE);
                    try {
                        LeaveDoneBean page = JSON.parseObject(response.toString(), LeaveDoneBean.class);
                        list = page.getList();
                        if (list.size()>0){
                            pageNumbers++;
                            for (int i = 0; i < list.size(); i++) {
                                mApproves.add(list.get(i));
                            }
                            if (pageNumbers==2) {
                                mAdapter = new ApprovesAdapter(mApproves, getActivity());
                                mList.setAdapter(mAdapter);
                            }else if (pageNumbers>2){
                                if (mList!=null) {
                                    mList.onLoadComplete();
                                }
                                myhandler.post(eChanged);
                            }
                        }else {
                            if (pageNumbers == 1) {
                                mAdapter = new ApprovesAdapter(mApproves, getActivity());
                                mList.setAdapter(mAdapter);
                            } else if (pageNumbers > 1) {
                                if (mList!=null) {
                                    mList.onLoadComplete();
                                }
                                Toast.makeText(getActivity(), "没有更多数据！", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),"出错了!",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonRequest);
    }
    private EditText editText;
    private ImageView ivDeleteText;
    private TextView request;
    private PopupWindow popupWindow;
    private LinearLayout menu;
    private void showPopupWindow() {
        View contentView = getActivity().getLayoutInflater().inflate(R.layout.search_in_service_layout, null);
        editText = (EditText) contentView.findViewById(R.id.condition);
        ivDeleteText = (ImageView) contentView.findViewById(R.id.ivDeleteText);
        request=(TextView)contentView.findViewById(R.id.request);
        popupWindow = new PopupWindow(contentView);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(menu);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                             @Override
                                             public void onDismiss() {
                                                 mView.setVisibility(View.GONE);
                                             }
                                         }

        );
        editText.setFocusable(true);//使弹框获取焦点
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                //这个应该是在改变的时候会做的动作吧，具体还没用到过。
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
                //这是文本框改变之前会执行的动作
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                /**这是文本框改变之后 会执行的动作
                 * 因为我们要做的就是，在文本框改变的同时，我们的listview的数据也进行相应的变动，并且如一的显示在界面上。
                 * 所以这里我们就需要加上数据的修改的动作了。
                 */
                if (s.length() == 0) {
                    request.setText("取消");
                    request.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });
                    ivDeleteText.setVisibility(View.GONE);//当文本框为空时，则叉叉消失
                } else {
                    request.setText("搜索");
                    request.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pageNumbers=1;
                            search(editText.getText().toString());
                        }
                    });
                    ivDeleteText.setVisibility(View.VISIBLE);//当文本框不为空时，出现叉叉
                }

            }
        });
        ivDeleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

    }


}
