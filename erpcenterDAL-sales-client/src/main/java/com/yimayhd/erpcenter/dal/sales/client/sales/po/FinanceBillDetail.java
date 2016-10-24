package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;

public class FinanceBillDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer bizId;
	/**
	 * 团ID
	 */
	private Integer groupId;
	/**
	 * 单据类型，1：餐卷，2：房劵，3：门票，4：娱乐
	 */
	private String billType;
	/**
	 * 单据商家
	 */
	private String billBusiness;
	/**
	 * 数量
	 */
	private Integer num;
	
	private String remark;
	/**
	 * 领单数量
	 */
	private Integer receivedNum;
	/**
	 * 派单单据号
	 */
	private String billNumReceive;
	/**
	 * 派单备注
	 */
	private String remarkReceive;
	
	/**
	 * 销单数量
	 */
	private Integer returndNum;
	/**
	 * 销单单据号
	 */
	private String billNumReturn;
	/**
	 * 销单备注
	 */
	private String remarkReturn;
	/**
	 * 导游ID
	 */
	private Integer guideId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillBusiness() {
		return billBusiness;
	}

	public void setBillBusiness(String billBusiness) {
		this.billBusiness = billBusiness;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getBillNumReceive() {
		return billNumReceive;
	}

	public void setBillNumReceive(String billNumReceive) {
		this.billNumReceive = billNumReceive;
	}

	public String getRemarkReceive() {
		return remarkReceive;
	}

	public void setRemarkReceive(String remarkReceive) {
		this.remarkReceive = remarkReceive;
	}


	public String getBillNumReturn() {
		return billNumReturn;
	}

	public void setBillNumReturn(String billNumReturn) {
		this.billNumReturn = billNumReturn;
	}

	public String getRemarkReturn() {
		return remarkReturn;
	}

	public void setRemarkReturn(String remarkReturn) {
		this.remarkReturn = remarkReturn;
	}

	public Integer getReceivedNum() {
		return receivedNum;
	}

	public void setReceivedNum(Integer receivedNum) {
		this.receivedNum = receivedNum;
	}

	public Integer getReturndNum() {
		return returndNum;
	}

	public void setReturndNum(Integer returndNum) {
		this.returndNum = returndNum;
	}

	public Integer getGuideId() {
		return guideId;
	}

	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}


	
	
	
}
