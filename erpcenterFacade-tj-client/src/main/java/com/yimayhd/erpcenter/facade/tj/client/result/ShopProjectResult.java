package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;

public class ShopProjectResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PageBean pageBean;
	private Map<String,Object> map;
	private Map<String,Object> mapDetail;
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public Map<String, Object> getMapDetail() {
		return mapDetail;
	}
	public void setMapDetail(Map<String, Object> mapDetail) {
		this.mapDetail = mapDetail;
	}
	
	
	
}
