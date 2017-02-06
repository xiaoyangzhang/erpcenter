package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransferOrder implements Serializable {

	private static final long serialVersionUID = 5569788136004930675L;

	private int id;
	private String appId;
	private String apiMethod;
	private int apiSupplierId;
	private String apiSupplierName;
	private int fromOrderId;
	private String sendUserName;
	private String sendUserMobile;
	private String sendUserTel;
	private String sendUserFax;
	private int bizId;
	private int orderId;
	private String orderCodeSend;
	private String orderCodeReceive;
	private String orderProductName;
	private int supplierId;
	private String supplierName;
	private String supplierUserName;
	private String supplierUserMobile;
	private String supplierUserTel;
	private String supplierUserFax;
	private int daynum;
	private Date dateStart;
	private Date dateEnd;
	private int personAdult;
	private int personChild;
	private int personGuide;
	private String remark;
	private String remarkService;
	private BigDecimal total;
	private byte stateReceive;
	private byte stateUpdate;
	private Date timeReceive;
	private Date timeCreate;
	private Date timeUpdate;
	private String fromAppKey;
	private String toAppKey;
	private String receiveMode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getApiMethod() {
		return apiMethod;
	}

	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}

	public int getApiSupplierId() {
		return apiSupplierId;
	}

	public void setApiSupplierId(int apiSupplierId) {
		this.apiSupplierId = apiSupplierId;
	}

	public String getApiSupplierName() {
		return apiSupplierName;
	}

	public void setApiSupplierName(String apiSupplierName) {
		this.apiSupplierName = apiSupplierName;
	}

	public int getFromOrderId() {
		return fromOrderId;
	}

	public void setFromOrderId(int fromOrderId) {
		this.fromOrderId = fromOrderId;
	}

	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public String getSendUserMobile() {
		return sendUserMobile;
	}

	public void setSendUserMobile(String sendUserMobile) {
		this.sendUserMobile = sendUserMobile;
	}

	public String getSendUserTel() {
		return sendUserTel;
	}

	public void setSendUserTel(String sendUserTel) {
		this.sendUserTel = sendUserTel;
	}

	public String getSendUserFax() {
		return sendUserFax;
	}

	public void setSendUserFax(String sendUserFax) {
		this.sendUserFax = sendUserFax;
	}

	public int getBizId() {
		return bizId;
	}

	public void setBizId(int bizId) {
		this.bizId = bizId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderCodeSend() {
		return orderCodeSend;
	}

	public void setOrderCodeSend(String orderCodeSend) {
		this.orderCodeSend = orderCodeSend;
	}

	public String getOrderCodeReceive() {
		return orderCodeReceive;
	}

	public void setOrderCodeReceive(String orderCodeReceive) {
		this.orderCodeReceive = orderCodeReceive;
	}

	public String getOrderProductName() {
		return orderProductName;
	}

	public void setOrderProductName(String orderProductName) {
		this.orderProductName = orderProductName;
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

	public String getSupplierUserName() {
		return supplierUserName;
	}

	public void setSupplierUserName(String supplierUserName) {
		this.supplierUserName = supplierUserName;
	}

	public String getSupplierUserMobile() {
		return supplierUserMobile;
	}

	public void setSupplierUserMobile(String supplierUserMobile) {
		this.supplierUserMobile = supplierUserMobile;
	}

	public String getSupplierUserTel() {
		return supplierUserTel;
	}

	public void setSupplierUserTel(String supplierUserTel) {
		this.supplierUserTel = supplierUserTel;
	}

	public String getSupplierUserFax() {
		return supplierUserFax;
	}

	public void setSupplierUserFax(String supplierUserFax) {
		this.supplierUserFax = supplierUserFax;
	}

	public int getDaynum() {
		return daynum;
	}

	public void setDaynum(int daynum) {
		this.daynum = daynum;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getPersonAdult() {
		return personAdult;
	}

	public void setPersonAdult(int personAdult) {
		this.personAdult = personAdult;
	}

	public int getPersonChild() {
		return personChild;
	}

	public void setPersonChild(int personChild) {
		this.personChild = personChild;
	}

	public int getPersonGuide() {
		return personGuide;
	}

	public void setPersonGuide(int personGuide) {
		this.personGuide = personGuide;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemarkService() {
		return remarkService;
	}

	public void setRemarkService(String remarkService) {
		this.remarkService = remarkService;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public byte getStateReceive() {
		return stateReceive;
	}

	public void setStateReceive(byte stateReceive) {
		this.stateReceive = stateReceive;
	}

	public byte getStateUpdate() {
		return stateUpdate;
	}

	public void setStateUpdate(byte stateUpdate) {
		this.stateUpdate = stateUpdate;
	}

	public Date getTimeReceive() {
		return timeReceive;
	}

	public void setTimeReceive(Date timeReceive) {
		this.timeReceive = timeReceive;
	}

	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Date getTimeUpdate() {
		return timeUpdate;
	}

	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	public String getFromAppKey() {
		return fromAppKey;
	}

	public void setFromAppKey(String fromAppKey) {
		this.fromAppKey = fromAppKey;
	}

	public String getToAppKey() {
		return toAppKey;
	}

	public void setToAppKey(String toAppKey) {
		this.toAppKey = toAppKey;
	}
	
	public String getReceiveMode() {
        return receiveMode;
    }

    public void setReceiveMode(String receiveMode) {
        this.receiveMode = receiveMode;
    }
}