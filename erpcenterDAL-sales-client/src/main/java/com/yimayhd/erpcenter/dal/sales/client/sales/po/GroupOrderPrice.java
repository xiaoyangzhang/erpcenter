package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yihg.basic.util.LogFieldAnno;

public class GroupOrderPrice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@LogFieldAnno(isKey = true)
	private Integer id;
	@LogFieldAnno(description="类别",needExtDescription = "[{\"key\":\"0\", \"value\":\"收入\"},{\"key\":\"1\", \"value\":\"预算成本\"}]")
	private Integer mode;

	private Integer orderId;
	private Integer itemId;
	@LogFieldAnno(description="项目", delOrIns = true)
	private String itemName;
	@LogFieldAnno(description="备注", delOrIns = true)
	private String remark;
	
	private Double primeCost;
	@LogFieldAnno(description="单价", delOrIns = true)
	private Double unitPrice;
	@LogFieldAnno(description="次数", delOrIns = true)
	private Double numTimes;
	@LogFieldAnno(description="人数", delOrIns = true)
	private Double numPerson;
	@LogFieldAnno(description="金额", delOrIns = true)
	private Double totalPrice;

	private Integer rowState;

	private Integer creatorId;

	private String creatorName;

	private Long createTime;

	private Integer priceLockState ;
	@LogFieldAnno(description="审核状态")
	private Integer stateFinance;
	
	public Integer getStateFinance() {
		return stateFinance;
	}

	public void setStateFinance(Integer stateFinance) {
		this.stateFinance = stateFinance;
	}

	public Integer getPriceLockState() {
		return priceLockState;
	}

	public void setPriceLockState(Integer priceLockState) {
		this.priceLockState = priceLockState;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName == null ? null : itemName.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	

	public Integer getRowState() {
		return rowState;
	}

	public void setRowState(Integer rowState) {
		this.rowState = rowState;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName == null ? null : creatorName.trim();
	}

	public Long getCreateTime() {
		return createTime;
	}


	public Double getNumTimes() {
		return numTimes;
	}

	public void setNumTimes(Double numTimes) {
		this.numTimes = numTimes;
	}

	public Double getNumPerson() {
		return numPerson;
	}

	public void setNumPerson(Double numPerson) {
		this.numPerson = numPerson;
	}

	public Double getPrimeCost() {
		return primeCost;
	}

	public void setPrimeCost(Double primeCost) {
		this.primeCost = primeCost;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
}