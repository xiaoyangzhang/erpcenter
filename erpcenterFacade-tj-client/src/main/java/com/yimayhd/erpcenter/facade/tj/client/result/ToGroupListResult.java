package com.yimayhd.erpcenter.facade.tj.client.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupProfit;

public class ToGroupListResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PageBean pageBean;
	
	private int bizId;
	private String orgJsonStr;
	private String orgUserJsonStr;
	private TJGroupProfit totalTj;
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public String getOrgJsonStr() {
		return orgJsonStr;
	}
	public void setOrgJsonStr(String orgJsonStr) {
		this.orgJsonStr = orgJsonStr;
	}
	public String getOrgUserJsonStr() {
		return orgUserJsonStr;
	}
	public void setOrgUserJsonStr(String orgUserJsonStr) {
		this.orgUserJsonStr = orgUserJsonStr;
	}
	public TJGroupProfit getTotalTj() {
		return totalTj;
	}
	public void setTotalTj(TJGroupProfit totalTj) {
		this.totalTj = totalTj;
	}

}
