package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;


public class ChangePriceDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<GroupOrderPrice> gop;
	private List<DicInfo> lysfxmList;
	
	private List<GroupOrderPrice> groupOrderPrices;
	private Integer orderId;
	private Integer userId;
	private String userName; 
	private Integer bizId;
	/**
	 * @return the gop
	 */
	public List<GroupOrderPrice> getGop() {
		return gop;
	}
	/**
	 * @param gop the gop to set
	 */
	public void setGop(List<GroupOrderPrice> gop) {
		this.gop = gop;
	}
	/**
	 * @return the lysfxmList
	 */
	public List<DicInfo> getLysfxmList() {
		return lysfxmList;
	}
	/**
	 * @param lysfxmList the lysfxmList to set
	 */
	public void setLysfxmList(List<DicInfo> lysfxmList) {
		this.lysfxmList = lysfxmList;
	}
	/**
	 * @return the groupOrderPrices
	 */
	public List<GroupOrderPrice> getGroupOrderPrices() {
		return groupOrderPrices;
	}
	/**
	 * @param groupOrderPrices the groupOrderPrices to set
	 */
	public void setGroupOrderPrices(List<GroupOrderPrice> groupOrderPrices) {
		this.groupOrderPrices = groupOrderPrices;
	}
	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the bizId
	 */
	public Integer getBizId() {
		return bizId;
	}
	/**
	 * @param bizId the bizId to set
	 */
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
	
}
