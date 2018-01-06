package com.zhilian.hzrf_oa.ui.leave.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public class LeaveDetailBean implements Serializable {
    private static final long serialVersionUID = 4987190517935993160L;

    /**
     * reason : sadfsssssssssssss
     * begindate : 2018-01-19
     * type : 年休假
     * wf : {"isreceive":"0","iscanreceive":"0","itemid":34,"id":1228}
     * itemid : 34
     * approvedate : 2018-01-12
     * atype : 1
     * id : 11
     * uname : 林小峰
     * opinion1 :
     * opinion :
     * activename : 申请
     * backlaststep : 0
     * fjlist : [{"size":"10.60K","name":"ic_dinner.png","id":1776,"url":"1514970540815.png"}]
     * positionId : 4
     * d_id : 1
     * opinion2 :
     * opinions : ["--请选择常用意见--","同意。","已核。","已阅。"]
     */

    private String reason;
    private String begindate;
    private String enddate;
    private String backdate;
    private String type;
    private WfBean wf;
    private int itemid;
    private String approvedate;
    private String dayt;
    private String days;
    private String atype;
    private String id;
    private String uname;
    private String dname;
    private List<String> opinions;
    private String opinion;
    private String opinion1;
    private String opinion2;
    private String activename;
    private String backlaststep;
    private int positionId;
    private String d_id;
    private List<FjlistBean> fjlist;
    private String married;
    private String opinionfield;

    public String getOpinionfield() {
        return opinionfield;
    }

    public void setOpinionfield(String opinionfield) {
        this.opinionfield = opinionfield;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getBackdate() {
        return backdate;
    }

    public void setBackdate(String backdate) {
        this.backdate = backdate;
    }

    public String getDayt() {
        return dayt;
    }

    public void setDayt(String dayt) {
        this.dayt = dayt;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WfBean getWf() {
        return wf;
    }

    public void setWf(WfBean wf) {
        this.wf = wf;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getApprovedate() {
        return approvedate;
    }

    public void setApprovedate(String approvedate) {
        this.approvedate = approvedate;
    }

    public String getAtype() {
        return atype;
    }

    public void setAtype(String atype) {
        this.atype = atype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getOpinion1() {
        return opinion1;
    }

    public void setOpinion1(String opinion1) {
        this.opinion1 = opinion1;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getActivename() {
        return activename;
    }

    public void setActivename(String activename) {
        this.activename = activename;
    }

    public String getBacklaststep() {
        return backlaststep;
    }

    public void setBacklaststep(String backlaststep) {
        this.backlaststep = backlaststep;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getOpinion2() {
        return opinion2;
    }

    public void setOpinion2(String opinion2) {
        this.opinion2 = opinion2;
    }

    public List<FjlistBean> getFjlist() {
        return fjlist;
    }

    public void setFjlist(List<FjlistBean> fjlist) {
        this.fjlist = fjlist;
    }

    public List<String> getOpinions() {
        return opinions;
    }

    public void setOpinions(List<String> opinions) {
        this.opinions = opinions;
    }

    public static class WfBean {
        /**
         * isreceive : 0
         * iscanreceive : 0
         * itemid : 34
         * id : 1228
         */

        private String isreceive;
        private String iscanreceive;
        private int itemid;
        private int id;

        public String getIsreceive() {
            return isreceive;
        }

        public void setIsreceive(String isreceive) {
            this.isreceive = isreceive;
        }

        public String getIscanreceive() {
            return iscanreceive;
        }

        public void setIscanreceive(String iscanreceive) {
            this.iscanreceive = iscanreceive;
        }

        public int getItemid() {
            return itemid;
        }

        public void setItemid(int itemid) {
            this.itemid = itemid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class FjlistBean {
        /**
         * size : 10.60K
         * name : ic_dinner.png
         * id : 1776
         * url : 1514970540815.png
         */

        private String size;
        private String name;
        private int id;
        private String url;

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
