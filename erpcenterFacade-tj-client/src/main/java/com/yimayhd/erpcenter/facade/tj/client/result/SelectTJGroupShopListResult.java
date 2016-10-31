package com.yimayhd.erpcenter.facade.tj.client.result;

import java.util.Map;

import com.yihg.mybatis.utility.PageBean;

public class SelectTJGroupShopListResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PageBean pageBean;
	private Map<String, Object> totalMap;
	private Map<String, Object> totalCommMap;
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public Map<String, Object> getTotalMap() {
		return totalMap;
	}
	public void setTotalMap(Map<String, Object> totalMap) {
		this.totalMap = totalMap;
	}
	public Map<String, Object> getTotalCommMap() {
		return totalCommMap;
	}
	public void setTotalCommMap(Map<String, Object> totalCommMap) {
		this.totalCommMap = totalCommMap;
	}
	
	

}
