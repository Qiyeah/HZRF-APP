package com.zhilian.hzrf_oa.ui.leave.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public class LeaveRoot implements Serializable {
    private static final long serialVersionUID = -4118834997889241940L;
    private List<LeaveDetailBean> root;

    public List<LeaveDetailBean> getRoot() {
        return root;
    }
    public void setRoot(List<LeaveDetailBean> root) {
        this.root = root;
    }
    public void add(List<LeaveDetailBean> root){
        root.addAll(root);
    }
    public boolean isBlank(){
        return this == null || root.size() ==0;
    }
}
