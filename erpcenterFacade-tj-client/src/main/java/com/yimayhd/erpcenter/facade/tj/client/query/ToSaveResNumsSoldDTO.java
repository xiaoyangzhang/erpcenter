package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class ToSaveResNumsSoldDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlatformEmployeePo curUser;
	private String productList;
	private String numStock;
	private String numDisable;
	private String id;
	private Integer poorNumStock;
	private Integer poorNumDisable;
	private Integer userId;
	private String userName;
	public PlatformEmployeePo getCurUser() {
		return curUser;
	}
	public void setCurUser(PlatformEmployeePo curUser) {
		this.curUser = curUser;
	}
	public String getProductList() {
		return productList;
	}
	public void setProductList(String productList) {
		this.productList = productList;
	}
	public String getNumStock() {
		return numStock;
	}
	public void setNumStock(String numStock) {
		this.numStock = numStock;
	}
	public String getNumDisable() {
		return numDisable;
	}
	public void setNumDisable(String numDisable) {
		this.numDisable = numDisable;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPoorNumStock() {
		return poorNumStock;
	}
	public void setPoorNumStock(Integer poorNumStock) {
		this.poorNumStock = poorNumStock;
	}
	public Integer getPoorNumDisable() {
		return poorNumDisable;
	}
	public void setPoorNumDisable(Integer poorNumDisable) {
		this.poorNumDisable = poorNumDisable;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
