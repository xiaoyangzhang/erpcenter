package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;

public class SaveSpecialGroupDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int bizId;
	private SpecialGroupOrderVO vo;
	private String orderNo;
	private int userId;
	private String userName;
	private String taobaoOrderId;
	private String taobaoOrderIds;
	private String myBizCode;
	private Integer groupMode;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public SpecialGroupOrderVO getVo() {
		return vo;
	}
	public void setVo(SpecialGroupOrderVO vo) {
		this.vo = vo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTaobaoOrderId() {
		return taobaoOrderId;
	}
	public void setTaobaoOrderId(String taobaoOrderId) {
		this.taobaoOrderId = taobaoOrderId;
	}
	public String getTaobaoOrderIds() {
		return taobaoOrderIds;
	}
	public void setTaobaoOrderIds(String taobaoOrderIds) {
		this.taobaoOrderIds = taobaoOrderIds;
	}
	public String getMyBizCode() {
		return myBizCode;
	}
	public void setMyBizCode(String myBizCode) {
		this.myBizCode = myBizCode;
	}
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}

}
