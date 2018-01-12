package com.zhilian.rxapi.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018-1-4.
 */

public class TodoItemBean implements Serializable {
    private static final long serialVersionUID = 579187196161339338L;
    /**
     * active : 申请
     * dayt : 0.0
     * docid : 11
     * name : 林小峰
     * pid : 1228
     * starttime : 2018-01-03 17:09:07
     * tempcolumn : 0
     * temprownumber : 1
     * type : 年休假
     * unit : 防办领导
     */

    private String active;
    private String dayt;
    private String docid;
    private String name;
    private String type;
    private String unit;
    private String approvedate;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDayt() {
        return dayt;
    }

    public void setDayt(String dayt) {
        this.dayt = dayt;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getApprovedate() {
        return approvedate;
    }

    public void setApprovedate(String approvedate) {
        this.approvedate = approvedate;
    }

    @Override
    public String toString() {
        return "TodoItemBean{" +
            "active='" + active + '\'' +
            ", dayt=" + dayt +
            ", docid=" + docid +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", unit='" + unit + '\'' +
            ", approvedate='" + approvedate + '\'' +
            '}';
    }
}
