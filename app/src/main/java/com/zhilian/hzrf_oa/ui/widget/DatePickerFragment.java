package com.zhilian.hzrf_oa.ui.widget;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.zhilian.hzrf_oa.R;
import com.zhilian.hzrf_oa.util.DateUtil;
import com.zhilian.hzrf_oa.util.StrKit;
import com.zhilian.hzrf_oa.ui.leave.view.ILeaveDetailView;

import java.util.Calendar;

/**
 * Created by Administrator on 2018-1-2.
 */

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private ILeaveDetailView mView;
    private int mResId;
    private String mDate;
    @SuppressLint("ValidFragment")
    public DatePickerFragment(ILeaveDetailView view, int resId, String strDate) {
        mView = view;
        mResId = resId;
        mDate=strDate;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        switch (mResId){
            case R.id.approvedate:
                mView.setApplyDate(formatDate(i,i1,i2));
                break;
            case R.id.begindate:
                mView.setBeginDate(formatDate(i,i1,i2));
                break;
            case R.id.enddate:
                mView.setEndDate(formatDate(i,i1,i2));
                break;
            case R.id.backdate:
                mView.setBackDate(formatDate(i,i1,i2));
                break;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog dialog = null;
        if (StrKit.isBlank(mDate)){
            Calendar c= Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            dialog = new DatePickerDialog(getActivity(),this,year,month,day);
        }else {
            int[] arr = DateUtil.getInstance().splitDate(mDate);
            dialog = new DatePickerDialog(getActivity(),this,arr[0],arr[1]-1,arr[2]);
        }
        return dialog;
    }
    private String formatDate(int year, int month, int day){
        return year+"-"+((month+1)<10?"0"+(month+1):(month+1))+"-"+(day<10?"0"+day:day);
    }


}
