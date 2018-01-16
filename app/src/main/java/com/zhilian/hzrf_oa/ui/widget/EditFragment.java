package com.zhilian.hzrf_oa.ui.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.base.IDetailBaseView;
import com.zhilian.hzrf_oa.entity.Opinion;
import com.zhilian.hzrf_oa.listener.OnTextChangedListener;
import com.zhilian.hzrf_oa.listener.OnItemSelectedListenerImpl;
import com.zhilian.hzrf_oa.util.OpinionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-1-3.
 */

@SuppressLint("ValidFragment")
public class EditFragment extends DialogFragment {

    @BindView(R.id.sp_opinions)
    Spinner mSpOpinions;
    @BindView(R.id.layout_container)
    LinearLayout mLayoutContainer;
    @BindView(R.id.tv_opinion)
    TextView mTvOpinion;
    @BindView(R.id.et_opinion)
    EditText mEtOpinion;

    private View mView;
    private IDetailBaseView mDetail;
    private int mResId;

    private Activity mContext;

    private String opinionStr;
    private ArrayAdapter<String> arrayAdapter;
    private String docId,itemId;
    private String[] opinions;

    @SuppressLint("ValidFragment")
    public EditFragment(int resId, String docId, String itemId, String[] opinions) {
        mResId = resId;
        this.docId = docId;
        this.itemId = itemId;
        this.opinions = opinions;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mContext =  getActivity();
        mDetail = (IDetailBaseView) mContext;
        final AlertDialog alert = new AlertDialog.Builder(mContext).create();
        mView = LayoutInflater.from(mContext).inflate(layoutRes1(), null);
        ButterKnife.bind(this, mView);
        switch (mResId) {
            case R.id.bt_reason:
                mLayoutContainer.setVisibility(View.INVISIBLE);
                mTvOpinion.setText("填写事由：");
                break;
            case R.id.bt_opinion1:
                mTvOpinion.setText("填写审核意见：");
                break;
            case R.id.bt_opinion2:
                mTvOpinion.setText("填写审批意见：");
                break;
        }
        /**
         * 每次填写意见时，显示已经缓存过的意见
         */
        opinionStr = OpinionUtil.getInstance(getActivity())
            .getOpinion(docId, itemId);
        mEtOpinion.setText(opinionStr);
        /**
         * 监听填写意见的文本框
         */
        mEtOpinion.addTextChangedListener(new OnTextChangedListener() {
            @Override
            public void afterTextChanged(Editable s) {
                opinionStr = mEtOpinion.getText().toString().trim();
                /**
                 * 填写意见的文本框内容改变时，缓存意见
                 */
                OpinionUtil.getInstance(getActivity())
                    .saveOpinion(
                        new Opinion(docId, itemId, opinionStr));
            }
        });
        mSpOpinions.setOnItemSelectedListener(new OnItemSelectedListenerImpl() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String edit = opinions[position];
                if (position > 0) {
                    mEtOpinion.setText(edit + "");
                }
            }
        });



        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, opinions);

        mSpOpinions.setAdapter(arrayAdapter);


        alert.setView(mView);
        // alert.setMessage("Sorry, your device doesn't support flash light!");
        alert.setButton(Dialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                /**
                 * 意见保存到后台时，清除缓存
                 */
                OpinionUtil.getInstance(getActivity())
                    .clearOpinion(docId, itemId);

                String temp = mEtOpinion.getText().toString().trim();
                temp = (temp != null) ? temp : "";
                switch (mResId) {
                    case R.id.bt_reason:
                        mDetail.onReasonChanged(temp);
                        break;
                    case R.id.bt_opinion1:
                        mDetail.onOpinion1Changed(temp);
                        break;
                    case R.id.bt_opinion2:
                        mDetail.onOpinion2Changed(temp);
                        break;
                }

                alert.dismiss();
                alert.cancel();
            }
        });
        return alert;
    }

    private int layoutRes1() {
        return R.layout.layout_reason;
    }


}
