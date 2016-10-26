package com.yimayhd.erpcenter.dal.sales.client.quality.vo;

import java.io.Serializable;
import java.util.Date;

public class QualityTourGroupVo implements Serializable {
	private Integer groupId;
	/**
	 * 团队信息页面需要orderid才能查看
	 */
	private Integer orderId;
	private String groupCode;
	private Date dateStart;
	private Date dateEnd;
	private Integer groupMode;
	private String productBrandName;
	private String productName;
	private Integer supplierId;
	private String supplierName;
	private String operatorName;
	private Integer operatorId;
	private Integer totalAdult;
	private Integer totalChild;
	private Integer totalGuide;
	private Integer groupState;
	private Integer score;
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public Integer getTotalAdult() {
		return totalAdult;
	}
	public void setTotalAdult(Integer totalAdult) {
		this.totalAdult = totalAdult;
	}
	public Integer getTotalChild() {
		return totalChild;
	}
	public void setTotalChild(Integer totalChild) {
		this.totalChild = totalChild;
	}
	public Integer getTotalGuide() {
		return totalGuide;
	}
	public void setTotalGuide(Integer totalGuide) {
		this.totalGuide = totalGuide;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getGroupState() {
		return groupState;
	}
	public void setGroupState(Integer groupState) {
		this.groupState = groupState;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
