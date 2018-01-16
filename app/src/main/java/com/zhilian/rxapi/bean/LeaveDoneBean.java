package com.zhilian.rxapi.bean;

import java.util.List;

/**
 * Created by zhilian on 2018/1/9.
 */

public class LeaveDoneBean {

	private List<DoneItemBean> list;

	public List<DoneItemBean> getList() {
		return list;
	}

	public void setList(List<DoneItemBean> list) {
		this.list = list;
	}

	public static class DoneItemBean {

		/**
		 * active : 主任审批
		 * dayt : 1.0
		 * docid : 131
		 * name : 林小峰
		 * pid : 1350
		 * starttime : 2018-01-10 08:51:10
		 * tempcolumn : 0
		 * temprownumber : 2
		 * type : null
		 * unit : 防办领导
		 */

		private String active;
		private String dayt;
		private String docid;
		private String name;
		private String pid;
		private String starttime;
		private int tempcolumn;
		private int temprownumber;
		private String type;
		private String unit;

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

		public String getPid() {
			return pid;
		}

		public void setPid(String pid) {
			this.pid = pid;
		}

		public String getStarttime() {
			return starttime;
		}

		public void setStarttime(String starttime) {
			this.starttime = starttime;
		}

		public int getTempcolumn() {
			return tempcolumn;
		}

		public void setTempcolumn(int tempcolumn) {
			this.tempcolumn = tempcolumn;
		}

		public int getTemprownumber() {
			return temprownumber;
		}

		public void setTemprownumber(int temprownumber) {
			this.temprownumber = temprownumber;
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
	}
}
