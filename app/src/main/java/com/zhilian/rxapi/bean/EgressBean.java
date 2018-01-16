package com.zhilian.rxapi.bean;

import java.util.List;

/**
 * Created by zhilian on 2018/1/15.
 */

public class EgressBean {

	/**
	 * totalRow : 1
	 * pageNumber : 1
	 * lastPage : true
	 * firstPage : true
	 * totalPage : 1
	 * pageSize : 10
	 * list : [{"active":"主任审批","approvedate":"2018-01-15","days":3,"docid":164,"name":"林小峰","pid":1387,"starttime":"2018-01-15 10:22:49","tempcolumn":0,"temprownumber":1,"type":"开会","unit":"防办领导"}]
	 */

	private int totalRow;
	private int pageNumber;
	private boolean lastPage;
	private boolean firstPage;
	private int totalPage;
	private int pageSize;
	private List<ListBean> list;

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<ListBean> getList() {
		return list;
	}

	public void setList(List<ListBean> list) {
		this.list = list;
	}

	public static class ListBean {
		/**
		 * active : 主任审批
		 * approvedate : 2018-01-15
		 * days : 3.0
		 * docid : 164
		 * name : 林小峰
		 * pid : 1387
		 * starttime : 2018-01-15 10:22:49
		 * tempcolumn : 0
		 * temprownumber : 1
		 * type : 开会
		 * unit : 防办领导
		 */

		private String active;
		private String approvedate;
		private double days;
		private int docid;
		private String name;
		private int pid;
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

		public String getApprovedate() {
			return approvedate;
		}

		public void setApprovedate(String approvedate) {
			this.approvedate = approvedate;
		}

		public double getDays() {
			return days;
		}

		public void setDays(double days) {
			this.days = days;
		}

		public int getDocid() {
			return docid;
		}

		public void setDocid(int docid) {
			this.docid = docid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getPid() {
			return pid;
		}

		public void setPid(int pid) {
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

		@Override
		public String toString() {
			return "ListBean{" +
				"active='" + active + '\'' +
				", approvedate='" + approvedate + '\'' +
				", days=" + days +
				", docid=" + docid +
				", name='" + name + '\'' +
				", pid=" + pid +
				", starttime='" + starttime + '\'' +
				", tempcolumn=" + tempcolumn +
				", temprownumber=" + temprownumber +
				", type='" + type + '\'' +
				", unit='" + unit + '\'' +
				'}';
		}
	}
}
