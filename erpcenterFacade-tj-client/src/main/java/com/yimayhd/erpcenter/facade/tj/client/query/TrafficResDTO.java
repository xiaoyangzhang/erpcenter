package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.product.vo.TrafficResVo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class TrafficResDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int trafficProductInfoId;
	private TrafficResVo trafficResVo;
	private PlatformEmployeePo curUser;
	private int bizId;
	private int userId;
	private String orgSupplierMapping;
	private int supplierId;
	private String supplierName;
	
	private Integer operType;
	private int orderId;
	
	public TrafficResVo getTrafficResVo() {
		return trafficResVo;
	}
	public void setTrafficResVo(TrafficResVo trafficResVo) {
		this.trafficResVo = trafficResVo;
	}
	public PlatformEmployeePo getCurUser() {
		return curUser;
	}
	public void setCurUser(PlatformEmployeePo curUser) {
		this.curUser = curUser;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTrafficProductInfoId() {
		return trafficProductInfoId;
	}
	public void setTrafficProductInfoId(int trafficProductInfoId) {
		this.trafficProductInfoId = trafficProductInfoId;
	}
	public String getOrgSupplierMapping() {
		return orgSupplierMapping;
	}
	public void setOrgSupplierMapping(String orgSupplierMapping) {
		this.orgSupplierMapping = orgSupplierMapping;
	}

	public Integer getOperType() {
		return operType;
	}
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
}
