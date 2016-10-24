package com.yimayhd.erpcenter.dal.sales.client.finance.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class FinanceCommission implements Serializable{
    
	private static final long serialVersionUID = 581494414373462502L;

	private Integer id;
	private Integer bizId;
	private Integer groupId;
	private String commissionType;
	private String commissionName;
	private String cashType;
	private Integer guideId;
	private String guideName;
	private BigDecimal total;
	private BigDecimal totalCash;
	private Integer stateFinance;
	private Integer stateSeal;
	private Integer auditUserId;
	private String auditUser;
	private Long auditTime;
	private Integer createUserId;
	private String createUser;
	private Date createTime;
	private Integer sealUserId;
	private String sealUser;
	private Long sealTime;
	private Integer stateCommission;
	private String operateLog;
	private String payUserName;
	private Date payTime;
	
	public Integer getSealUserId() {
		return sealUserId;
	}
	public void setSealUserId(Integer sealUserId) {
		this.sealUserId = sealUserId;
	}
	public String getSealUser() {
		return sealUser;
	}
	public void setSealUser(String sealUser) {
		this.sealUser = sealUser;
	}
	public Long getSealTime() {
		return sealTime;
	}
	public void setSealTime(Long sealTime) {
		this.sealTime = sealTime;
	}
	
	public String getPayUserName() {
		return payUserName;
	}
	public void setPayUserName(String payUserName) {
		this.payUserName = payUserName;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getOperateLog() {
		return operateLog;
	}
	public void setOperateLog(String operateLog) {
		this.operateLog = operateLog;
	}
	public Integer getStateCommission() {
		return stateCommission;
	}
	public void setStateCommission(Integer stateCommission) {
		this.stateCommission = stateCommission;
	}
	private TourGroup tourGroup;
	
	public Integer getStateSeal() {
		return stateSeal;
	}
	public void setStateSeal(Integer stateSeal) {
		this.stateSeal = stateSeal;
	}
	public String getCommissionName() {
		return commissionName;
	}
	public void setCommissionName(String commissionName) {
		this.commissionName = commissionName;
	}
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
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
	public String getCommissionType() {
		return commissionType;
	}
	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}
	public String getCashType() {
		return cashType;
	}
	public void setCashType(String cashType) {
		this.cashType = cashType;
	}
	public Integer getGuideId() {
		return guideId;
	}
	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getTotalCash() {
		return totalCash;
	}
	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}
	public Integer getStateFinance() {
		return stateFinance;
	}
	public void setStateFinance(Integer stateFinance) {
		this.stateFinance = stateFinance;
	}
	public Integer getAuditUserId() {
		return auditUserId;
	}
	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	public Long getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Long auditTime) {
		this.auditTime = auditTime;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}