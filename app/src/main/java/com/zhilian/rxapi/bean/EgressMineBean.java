package com.zhilian.rxapi.bean;

import java.util.List;

/**
 * Created by zhilian on 2018/1/16.
 */

public class EgressMineBean {

	/**
	 * totalRow : 2
	 * pageNumber : 1
	 * lastPage : true
	 * firstPage : true
	 * totalPage : 1
	 * pageSize : 10
	 * list : [{"approvedate":"2018-01-22","begindate":"2018-01-22","dayg":null,"days":3,"enddate":"2018-01-26","id":165,"pstatus":"0","tempcolumn":0,"temprownumber":1,"type":"开会"}]
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
		 * approvedate : 2018-01-22
		 * begindate : 2018-01-22
		 * dayg : null
		 * days : 3.0
		 * enddate : 2018-01-26
		 * id : 165
		 * pstatus : 0
		 * tempcolumn : 0
		 * temprownumber : 1
		 * type : 开会
		 */

		private String approvedate;
		private String begindate;
		private String dayg;
		private double days;
		private String enddate;
		private int id;
		private String pstatus;
		private int tempcolumn;
		private int temprownumber;
		private String type;

		public String getApprovedate() {
			return approvedate;
		}

		public void setApprovedate(String approvedate) {
			this.approvedate = approvedate;
		}

		public String getBegindate() {
			return begindate;
		}

		public void setBegindate(String begindate) {
			this.begindate = begindate;
		}

		public String getDayg() {
			return dayg;
		}

		public void setDayg(String dayg) {
			this.dayg = dayg;
		}

		public double getDays() {
			return days;
		}

		public void setDays(double days) {
			this.days = days;
		}

		public String getEnddate() {
			return enddate;
		}

		public void setEnddate(String enddate) {
			this.enddate = enddate;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getPstatus() {
			return pstatus;
		}

		public void setPstatus(String pstatus) {
			this.pstatus = pstatus;
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

		@Override
		public String toString() {
			return "ListBean{" +
				"approvedate='" + approvedate + '\'' +
				", begindate='" + begindate + '\'' +
				", dayg='" + dayg + '\'' +
				", days=" + days +
				", enddate='" + enddate + '\'' +
				", id=" + id +
				", pstatus='" + pstatus + '\'' +
				", tempcolumn=" + tempcolumn +
				", temprownumber=" + temprownumber +
				", type='" + type + '\'' +
				'}';
		}
	}
}
