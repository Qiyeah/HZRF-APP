package com.zhilian.rxapi.bean;

import java.util.List;

/**
 * Created by zhilian on 2018/1/16.
 */

public class EgressDetailBean {

	/**
	 * reason : xxxxxxxxxxxxxxxxxxx
	 * begindate : 2018-01-22
	 * type : 开会
	 * wf : {"isreceive":"0","starttime":null,"iscanreceive":"0","itemid":80,"id":1388}
	 * itemid : 80
	 * u_id : 88
	 * approvedate : 2018-01-22
	 * atype : 1
	 * id : 165
	 * dname : 办领导
	 * types : ["开会","学习","值班","下工地","其他"]
	 * uname : 林小峰
	 * dayg : null
	 * opinion1 : 
	 * opinion : 
	 * activename : 申请
	 * backlaststep : 0
	 * enddate : 2018-01-26
	 * tempopinion2 : null
	 * fjlist : [{"size":"391.00B","name":"021022123905890.png","id":1780,"url":"1515982991031.png"}]
	 * tempopinion1 : null
	 * positionId : 4
	 * opinionfield : null
	 * d_id : 1
	 * opinion2 : 
	 * opinions : ["--请选择常用意见--","同意。","已核。","已阅。"]
	 * days : 3.0
	 */
	private String days;
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
	private String opinion1;
	private String opinion;
	private String activename;
	private String backlaststep;
	private String enddate;
	private String tempopinion2;
	private String tempopinion1;
	private int positionId;
	private String opinionfield;
	private String d_id;
	private String opinion2;

	private List<String> types;
	private List<FjlistBean> fjlist;
	private List<String> opinions;

	public String getDayt() {
		return days;
	}

	public void setDayt(String days) {
		this.days = days;
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



	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
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
		 * starttime : null
		 * iscanreceive : 0
		 * itemid : 80
		 * id : 1388
		 */

		private String isreceive;
		private String starttime;
		private String iscanreceive;
		private int itemid;
		private int id;

		public String getIsreceive() {
			return isreceive;
		}

		public void setIsreceive(String isreceive) {
			this.isreceive = isreceive;
		}

		public String getStarttime() {
			return starttime;
		}

		public void setStarttime(String starttime) {
			this.starttime = starttime;
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
		 * size : 391.00B
		 * name : 021022123905890.png
		 * id : 1780
		 * url : 1515982991031.png
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

	@Override
	public String toString() {
		return "EgressDetailBean{" +
			"dayt='" + days + '\'' +
			", reason='" + reason + '\'' +
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
			", opinion1='" + opinion1 + '\'' +
			", opinion='" + opinion + '\'' +
			", activename='" + activename + '\'' +
			", backlaststep='" + backlaststep + '\'' +
			", enddate='" + enddate + '\'' +
			", tempopinion2='" + tempopinion2 + '\'' +
			", tempopinion1='" + tempopinion1 + '\'' +
			", positionId=" + positionId +
			", opinionfield='" + opinionfield + '\'' +
			", d_id='" + d_id + '\'' +
			", opinion2='" + opinion2 + '\'' +
			", types=" + types +
			", fjlist=" + fjlist +
			", opinions=" + opinions +
			'}';
	}
}
