package com.yimayhd.erpcenter.facade.operation.result;

import java.io.Serializable;

import com.yihg.mybatis.utility.PageBean;

public class GuestShopResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PageBean pageBean;
	
	private int shoppingDataState;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getShoppingDataState() {
		return shoppingDataState;
	}

	public void setShoppingDataState(int shoppingDataState) {
		this.shoppingDataState = shoppingDataState;
	}
	
	
	
}
