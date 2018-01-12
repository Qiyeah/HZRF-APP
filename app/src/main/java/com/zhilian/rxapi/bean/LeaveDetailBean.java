package com.zhilian.rxapi.bean;


import java.util.List;

/**
 * Created by Administrator on 2017-12-28.
 */

public class LeaveDetailBean{

    /**
     * reason : 111111111111
     * begindate : 2018-01-11
     * type : 年休假
     * wf : {"isopen":null,"isreceive":"0","reader":null,"formname":null,"doneuser":null,"starttime":null,"title":null,"iscanreceive":"0","isend":null,"flowform":null,"bodyiscreaded":null,"isnormalend":null,"todoManName":null,"itemid":39,"serialVersionUID":-1,"bodyauthor":null,"workpath":null,"templatename":null,"id":1377,"ishold":null,"editor":null,"islock":null,"hastemplate":null,"starter":null,"todoman":null,"todousers":null,"worddocname":null,"mainflowid":null,"bodyversion":null,"subflowid":null,"flowname":null,"startdept":null,"subflowname":null}
     * itemid : 39
     * u_id : 94
     * approvedate : 2018-01-10
     * atype : 2
     * id : 158
     * dname : 工程科
     * types : ["年休假","事假","病假"]
     * uname : 陈璐
     * dayg : null
     * backdate : null
     * daye : 15.0
     * opinion1 : 
     * dayc : 15.0
     * workyear : 118
     * opinion : 
     * dayz : 
     * activename : 科室审核
     * backlaststep : 1
     * enddate : null
     * tempopinion2 : null
     * fjlist : []
     * tempopinion1 : 
     * positionId : 9
     * dayt : 1.0
     * opinionfield : opinion1
     * d_id : 4
     * opinion2 : 
     * opinions : ["--请选择常用意见--","同意。","已核。","已阅。"]
     * days : null
     * dayn : 0.0
     * married : 已婚
     */

    private String reason;
    private String begindate;
    private String type;
    private WfBean wf;
    private String itemid;
    private String u_id;
    private String approvedate;
    private String atype;
    private String id;
    private String dname;
    private String uname;
    private String dayg;
    private String backdate;
    private String daye;
    private String opinion1;
    private String dayc;
    private String workyear;
    private String opinion;
    private String dayz;
    private String activename;
    private String backlaststep;
    private String enddate;
    private String tempopinion2;
    private String tempopinion1;
    private int positionId;
    private String dayt;
    private String opinionfield;
    private String d_id;
    private String opinion2;
    private String days;
    private String dayn;
    private String married;
    private List<String> types;
    private List<T_FJList> fjlist;
    private List<String> opinions;

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

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
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

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDayg() {
        return dayg;
    }

    public void setDayg(String dayg) {
        this.dayg = dayg;
    }

    public String getBackdate() {
        return backdate;
    }

    public void setBackdate(String backdate) {
        this.backdate = backdate;
    }

    public String getDaye() {
        return daye;
    }

    public void setDaye(String daye) {
        this.daye = daye;
    }

    public String getOpinion1() {
        return opinion1;
    }

    public void setOpinion1(String opinion1) {
        this.opinion1 = opinion1;
    }

    public String getDayc() {
        return dayc;
    }

    public void setDayc(String dayc) {
        this.dayc = dayc;
    }

    public String getWorkyear() {
        return workyear;
    }

    public void setWorkyear(String workyear) {
        this.workyear = workyear;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getDayz() {
        return dayz;
    }

    public void setDayz(String dayz) {
        this.dayz = dayz;
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

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getTempopinion2() {
        return tempopinion2;
    }

    public void setTempopinion2(String tempopinion2) {
        this.tempopinion2 = tempopinion2;
    }

    public String getTempopinion1() {
        return tempopinion1;
    }

    public void setTempopinion1(String tempopinion1) {
        this.tempopinion1 = tempopinion1;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getDayt() {
        return dayt;
    }

    public void setDayt(String dayt) {
        this.dayt = dayt;
    }

    public String getOpinionfield() {
        return opinionfield;
    }

    public void setOpinionfield(String opinionfield) {
        this.opinionfield = opinionfield;
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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDayn() {
        return dayn;
    }

    public void setDayn(String dayn) {
        this.dayn = dayn;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<T_FJList> getFjlist() {
        return fjlist;
    }

    public void setFjlist(List<T_FJList> fjlist) {
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
         * isopen : null
         * isreceive : 0
         * reader : null
         * formname : null
         * doneuser : null
         * starttime : null
         * title : null
         * iscanreceive : 0
         * isend : null
         * flowform : null
         * bodyiscreaded : null
         * isnormalend : null
         * todoManName : null
         * itemid : 39
         * serialVersionUID : -1
         * bodyauthor : null
         * workpath : null
         * templatename : null
         * id : 1377
         * ishold : null
         * editor : null
         * islock : null
         * hastemplate : null
         * starter : null
         * todoman : null
         * todousers : null
         * worddocname : null
         * mainflowid : null
         * bodyversion : null
         * subflowid : null
         * flowname : null
         * startdept : null
         * subflowname : null
         */

        private String isopen;
        private String isreceive;
        private String reader;
        private String formname;
        private String doneuser;
        private String starttime;
        private String title;
        private String iscanreceive;
        private String isend;
        private String flowform;
        private String bodyiscreaded;
        private String isnormalend;
        private String todoManName;
        private int itemid;
        private int serialVersionUID;
        private String bodyauthor;
        private String workpath;
        private String templatename;
        private int id;
        private String ishold;
        private String editor;
        private String islock;
        private String hastemplate;
        private String starter;
        private String todoman;
        private String todousers;
        private String worddocname;
        private String mainflowid;
        private String bodyversion;
        private String subflowid;
        private String flowname;
        private String startdept;
        private String subflowname;

        public String getIsopen() {
            return isopen;
        }

        public void setIsopen(String isopen) {
            this.isopen = isopen;
        }

        public String getIsreceive() {
            return isreceive;
        }

        public void setIsreceive(String isreceive) {
            this.isreceive = isreceive;
        }

        public String getReader() {
            return reader;
        }

        public void setReader(String reader) {
            this.reader = reader;
        }

        public String getFormname() {
            return formname;
        }

        public void setFormname(String formname) {
            this.formname = formname;
        }

        public String getDoneuser() {
            return doneuser;
        }

        public void setDoneuser(String doneuser) {
            this.doneuser = doneuser;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIscanreceive() {
            return iscanreceive;
        }

        public void setIscanreceive(String iscanreceive) {
            this.iscanreceive = iscanreceive;
        }

        public String getIsend() {
            return isend;
        }

        public void setIsend(String isend) {
            this.isend = isend;
        }

        public String getFlowform() {
            return flowform;
        }

        public void setFlowform(String flowform) {
            this.flowform = flowform;
        }

        public String getBodyiscreaded() {
            return bodyiscreaded;
        }

        public void setBodyiscreaded(String bodyiscreaded) {
            this.bodyiscreaded = bodyiscreaded;
        }

        public String getIsnormalend() {
            return isnormalend;
        }

        public void setIsnormalend(String isnormalend) {
            this.isnormalend = isnormalend;
        }

        public String getTodoManName() {
            return todoManName;
        }

        public void setTodoManName(String todoManName) {
            this.todoManName = todoManName;
        }

        public int getItemid() {
            return itemid;
        }

        public void setItemid(int itemid) {
            this.itemid = itemid;
        }

        public int getSerialVersionUID() {
            return serialVersionUID;
        }

        public void setSerialVersionUID(int serialVersionUID) {
            this.serialVersionUID = serialVersionUID;
        }

        public String getBodyauthor() {
            return bodyauthor;
        }

        public void setBodyauthor(String bodyauthor) {
            this.bodyauthor = bodyauthor;
        }

        public String getWorkpath() {
            return workpath;
        }

        public void setWorkpath(String workpath) {
            this.workpath = workpath;
        }

        public String getTemplatename() {
            return templatename;
        }

        public void setTemplatename(String templatename) {
            this.templatename = templatename;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIshold() {
            return ishold;
        }

        public void setIshold(String ishold) {
            this.ishold = ishold;
        }

        public String getEditor() {
            return editor;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public String getIslock() {
            return islock;
        }

        public void setIslock(String islock) {
            this.islock = islock;
        }

        public String getHastemplate() {
            return hastemplate;
        }

        public void setHastemplate(String hastemplate) {
            this.hastemplate = hastemplate;
        }

        public String getStarter() {
            return starter;
        }

        public void setStarter(String starter) {
            this.starter = starter;
        }

        public String getTodoman() {
            return todoman;
        }

        public void setTodoman(String todoman) {
            this.todoman = todoman;
        }

        public String getTodousers() {
            return todousers;
        }

        public void setTodousers(String todousers) {
            this.todousers = todousers;
        }

        public String getWorddocname() {
            return worddocname;
        }

        public void setWorddocname(String worddocname) {
            this.worddocname = worddocname;
        }

        public String getMainflowid() {
            return mainflowid;
        }

        public void setMainflowid(String mainflowid) {
            this.mainflowid = mainflowid;
        }

        public String getBodyversion() {
            return bodyversion;
        }

        public void setBodyversion(String bodyversion) {
            this.bodyversion = bodyversion;
        }

        public String getSubflowid() {
            return subflowid;
        }

        public void setSubflowid(String subflowid) {
            this.subflowid = subflowid;
        }

        public String getFlowname() {
            return flowname;
        }

        public void setFlowname(String flowname) {
            this.flowname = flowname;
        }

        public String getStartdept() {
            return startdept;
        }

        public void setStartdept(String startdept) {
            this.startdept = startdept;
        }

        public String getSubflowname() {
            return subflowname;
        }

        public void setSubflowname(String subflowname) {
            this.subflowname = subflowname;
        }

        @Override
        public String toString() {
            return "WfBean{" +
                "isopen='" + isopen + '\'' +
                ", isreceive='" + isreceive + '\'' +
                ", reader='" + reader + '\'' +
                ", formname='" + formname + '\'' +
                ", doneuser='" + doneuser + '\'' +
                ", starttime='" + starttime + '\'' +
                ", title='" + title + '\'' +
                ", iscanreceive='" + iscanreceive + '\'' +
                ", isend='" + isend + '\'' +
                ", flowform='" + flowform + '\'' +
                ", bodyiscreaded='" + bodyiscreaded + '\'' +
                ", isnormalend='" + isnormalend + '\'' +
                ", todoManName='" + todoManName + '\'' +
                ", itemid=" + itemid +
                ", serialVersionUID=" + serialVersionUID +
                ", bodyauthor='" + bodyauthor + '\'' +
                ", workpath='" + workpath + '\'' +
                ", templatename='" + templatename + '\'' +
                ", id=" + id +
                ", ishold='" + ishold + '\'' +
                ", editor='" + editor + '\'' +
                ", islock='" + islock + '\'' +
                ", hastemplate='" + hastemplate + '\'' +
                ", starter='" + starter + '\'' +
                ", todoman='" + todoman + '\'' +
                ", todousers='" + todousers + '\'' +
                ", worddocname='" + worddocname + '\'' +
                ", mainflowid='" + mainflowid + '\'' +
                ", bodyversion='" + bodyversion + '\'' +
                ", subflowid='" + subflowid + '\'' +
                ", flowname='" + flowname + '\'' +
                ", startdept='" + startdept + '\'' +
                ", subflowname='" + subflowname + '\'' +
                '}';
        }
    }

    @Override
    public String toString() {
        return "LeaveDetailBean{" +
            "reason='" + reason + '\'' +
            ", begindate='" + begindate + '\'' +
            ", type='" + type + '\'' +
            ", wf=" + wf +
            ", itemid='" + itemid + '\'' +
            ", u_id='" + u_id + '\'' +
            ", approvedate='" + approvedate + '\'' +
            ", atype='" + atype + '\'' +
            ", id='" + id + '\'' +
            ", dname='" + dname + '\'' +
            ", uname='" + uname + '\'' +
            ", dayg='" + dayg + '\'' +
            ", backdate='" + backdate + '\'' +
            ", daye='" + daye + '\'' +
            ", opinion1='" + opinion1 + '\'' +
            ", dayc='" + dayc + '\'' +
            ", workyear='" + workyear + '\'' +
            ", opinion='" + opinion + '\'' +
            ", dayz='" + dayz + '\'' +
            ", activename='" + activename + '\'' +
            ", backlaststep='" + backlaststep + '\'' +
            ", enddate='" + enddate + '\'' +
            ", tempopinion2='" + tempopinion2 + '\'' +
            ", tempopinion1='" + tempopinion1 + '\'' +
            ", positionId=" + positionId +
            ", dayt='" + dayt + '\'' +
            ", opinionfield='" + opinionfield + '\'' +
            ", d_id='" + d_id + '\'' +
            ", opinion2='" + opinion2 + '\'' +
            ", days='" + days + '\'' +
            ", dayn='" + dayn + '\'' +
            ", married='" + married + '\'' +
            ", types=" + types +
            ", fjlist=" + fjlist +
            ", opinions=" + opinions +
            '}';
    }
}
