package com.zhilian.rxapi;

import android.content.Context;

import com.zhilian.hzrf_oa.adapter.SelectmanAdapter;
import com.zhilian.hzrf_oa.common.BusinessContant;
import com.zhilian.hzrf_oa.json.T_Selectman;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 处理工作流转环节
 *
 * Created by zhilian on 2018/1/8.
 */

public class WorkFlow {
    private Context mContext;

    private List<T_Selectman> selectmenlist = new ArrayList<T_Selectman>();
    private SelectmanAdapter selectmanAdapter;
    private  String tempopinion = "";
    private BusinessContant bc = new BusinessContant();

    public static WorkFlow instance = null;
    public WorkFlow getInstance(Context context){
        return new WorkFlow(context);
    }
    private WorkFlow(Context context){

    }

 /*   public void selectman(String operation1,String opinion) {
        final String operation = operation1;


        if (operation.equals("opinion1")) {
            tempopinion = mWriteEts.get(1).getText().toString();
        } else if (operation.equals("opinion2")) {
            tempopinion = mWriteEts.get(2).getText().toString();
        } else if (operation.equals("")) {
            tempopinion = mWriteEts.get(0).getText().toString();
        }
        LogUtil.e("opinionfield = "+ StrKit.notBlank(leave.getOpinionfield()));
        LogUtil.e("tempopinion = "+StrKit.isBlank(tempopinion));
        if (StrKit.isBlank(leave.getOpinionfield()) && StrKit.isBlank(tempopinion)) {
            Toast.makeText(mContext, "请填写意见!", Toast.LENGTH_SHORT).show();
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
                                com.zhilian.rxapi.util.e("e", error.getMessage());
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

                    RequestQueue requestQueue = RequestUtil.getRequestQueue();

                    JsonRequest jsonRequest = new JsonStringRequest(Request.Method.POST, url, postData,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("TAG", "response -> " + response.toString());

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
    }*/
}
