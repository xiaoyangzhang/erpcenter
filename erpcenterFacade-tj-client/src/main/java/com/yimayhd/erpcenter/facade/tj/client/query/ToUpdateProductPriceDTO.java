package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class ToUpdateProductPriceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String suggestPriceId;
	private String adjustUprodownNum;
	private String price;
	private Integer resId;
	private PlatformEmployeePo curUser;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSuggestPriceId() {
		return suggestPriceId;
	}
	public void setSuggestPriceId(String suggestPriceId) {
		this.suggestPriceId = suggestPriceId;
	}
	public String getAdjustUprodownNum() {
		return adjustUprodownNum;
	}
	public void setAdjustUprodownNum(String adjustUprodownNum) {
		this.adjustUprodownNum = adjustUprodownNum;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
		this.resId = resId;
	}
	public PlatformEmployeePo getCurUser() {
		return curUser;
	}
	public void setCurUser(PlatformEmployeePo curUser) {
		this.curUser = curUser;
	}
	
	
}
