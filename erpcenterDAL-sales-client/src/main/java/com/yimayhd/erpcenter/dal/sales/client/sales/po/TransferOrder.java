package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransferOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5569788136004930675L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.app_id
	 * @mbggenerated
	 */
	private String appId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.api_method
	 * @mbggenerated
	 */
	private String apiMethod;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.api_supplier_id
	 * @mbggenerated
	 */
	private Integer apiSupplierId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.api_supplier_name
	 * @mbggenerated
	 */
	private String apiSupplierName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.from_order_id
	 * @mbggenerated
	 */
	private Integer fromOrderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.send_user_name
	 * @mbggenerated
	 */
	private String sendUserName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.send_user_mobile
	 * @mbggenerated
	 */
	private String sendUserMobile;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.send_user_tel
	 * @mbggenerated
	 */
	private String sendUserTel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.send_user_fax
	 * @mbggenerated
	 */
	private String sendUserFax;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.biz_id
	 * @mbggenerated
	 */
	private Integer bizId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.order_id
	 * @mbggenerated
	 */
	private Integer orderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.order_code_send
	 * @mbggenerated
	 */
	private String orderCodeSend;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.order_code_receive
	 * @mbggenerated
	 */
	private String orderCodeReceive;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.order_product_name
	 * @mbggenerated
	 */
	private String orderProductName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.supplier_id
	 * @mbggenerated
	 */
	private Integer supplierId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.supplier_name
	 * @mbggenerated
	 */
	private String supplierName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.supplier_user_name
	 * @mbggenerated
	 */
	private String supplierUserName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.supplier_user_mobile
	 * @mbggenerated
	 */
	private String supplierUserMobile;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.supplier_user_tel
	 * @mbggenerated
	 */
	private String supplierUserTel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.supplier_user_fax
	 * @mbggenerated
	 */
	private String supplierUserFax;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.daynum
	 * @mbggenerated
	 */
	private Integer daynum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.date_start
	 * @mbggenerated
	 */
	private Date dateStart;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.date_end
	 * @mbggenerated
	 */
	private Date dateEnd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.person_adult
	 * @mbggenerated
	 */
	private Integer personAdult;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.person_child
	 * @mbggenerated
	 */
	private Integer personChild;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.person_guide
	 * @mbggenerated
	 */
	private Integer personGuide;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.remark
	 * @mbggenerated
	 */
	private String remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.remark_service
	 * @mbggenerated
	 */
	private String remarkService;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.total
	 * @mbggenerated
	 */
	private BigDecimal total;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.state_receive
	 * @mbggenerated
	 */
	private Byte stateReceive;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.state_update
	 * @mbggenerated
	 */
	private Byte stateUpdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.time_receive
	 * @mbggenerated
	 */
	private Date timeReceive;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.time_create
	 * @mbggenerated
	 */
	private Date timeCreate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column transfer_order.time_update
	 * @mbggenerated
	 */
	private Date timeUpdate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.id
	 * @return  the value of transfer_order.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.id
	 * @param id  the value for transfer_order.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.app_id
	 * @return  the value of transfer_order.app_id
	 * @mbggenerated
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.app_id
	 * @param appId  the value for transfer_order.app_id
	 * @mbggenerated
	 */
	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.api_method
	 * @return  the value of transfer_order.api_method
	 * @mbggenerated
	 */
	public String getApiMethod() {
		return apiMethod;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.api_method
	 * @param apiMethod  the value for transfer_order.api_method
	 * @mbggenerated
	 */
	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod == null ? null : apiMethod.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.api_supplier_id
	 * @return  the value of transfer_order.api_supplier_id
	 * @mbggenerated
	 */
	public Integer getApiSupplierId() {
		return apiSupplierId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.api_supplier_id
	 * @param apiSupplierId  the value for transfer_order.api_supplier_id
	 * @mbggenerated
	 */
	public void setApiSupplierId(Integer apiSupplierId) {
		this.apiSupplierId = apiSupplierId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.api_supplier_name
	 * @return  the value of transfer_order.api_supplier_name
	 * @mbggenerated
	 */
	public String getApiSupplierName() {
		return apiSupplierName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.api_supplier_name
	 * @param apiSupplierName  the value for transfer_order.api_supplier_name
	 * @mbggenerated
	 */
	public void setApiSupplierName(String apiSupplierName) {
		this.apiSupplierName = apiSupplierName == null ? null : apiSupplierName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.from_order_id
	 * @return  the value of transfer_order.from_order_id
	 * @mbggenerated
	 */
	public Integer getFromOrderId() {
		return fromOrderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.from_order_id
	 * @param fromOrderId  the value for transfer_order.from_order_id
	 * @mbggenerated
	 */
	public void setFromOrderId(Integer fromOrderId) {
		this.fromOrderId = fromOrderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.send_user_name
	 * @return  the value of transfer_order.send_user_name
	 * @mbggenerated
	 */
	public String getSendUserName() {
		return sendUserName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.send_user_name
	 * @param sendUserName  the value for transfer_order.send_user_name
	 * @mbggenerated
	 */
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName == null ? null : sendUserName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.send_user_mobile
	 * @return  the value of transfer_order.send_user_mobile
	 * @mbggenerated
	 */
	public String getSendUserMobile() {
		return sendUserMobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.send_user_mobile
	 * @param sendUserMobile  the value for transfer_order.send_user_mobile
	 * @mbggenerated
	 */
	public void setSendUserMobile(String sendUserMobile) {
		this.sendUserMobile = sendUserMobile == null ? null : sendUserMobile.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.send_user_tel
	 * @return  the value of transfer_order.send_user_tel
	 * @mbggenerated
	 */
	public String getSendUserTel() {
		return sendUserTel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.send_user_tel
	 * @param sendUserTel  the value for transfer_order.send_user_tel
	 * @mbggenerated
	 */
	public void setSendUserTel(String sendUserTel) {
		this.sendUserTel = sendUserTel == null ? null : sendUserTel.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.send_user_fax
	 * @return  the value of transfer_order.send_user_fax
	 * @mbggenerated
	 */
	public String getSendUserFax() {
		return sendUserFax;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.send_user_fax
	 * @param sendUserFax  the value for transfer_order.send_user_fax
	 * @mbggenerated
	 */
	public void setSendUserFax(String sendUserFax) {
		this.sendUserFax = sendUserFax == null ? null : sendUserFax.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.biz_id
	 * @return  the value of transfer_order.biz_id
	 * @mbggenerated
	 */
	public Integer getBizId() {
		return bizId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.biz_id
	 * @param bizId  the value for transfer_order.biz_id
	 * @mbggenerated
	 */
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.order_id
	 * @return  the value of transfer_order.order_id
	 * @mbggenerated
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.order_id
	 * @param orderId  the value for transfer_order.order_id
	 * @mbggenerated
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.order_code_send
	 * @return  the value of transfer_order.order_code_send
	 * @mbggenerated
	 */
	public String getOrderCodeSend() {
		return orderCodeSend;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.order_code_send
	 * @param orderCodeSend  the value for transfer_order.order_code_send
	 * @mbggenerated
	 */
	public void setOrderCodeSend(String orderCodeSend) {
		this.orderCodeSend = orderCodeSend == null ? null : orderCodeSend.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.order_code_receive
	 * @return  the value of transfer_order.order_code_receive
	 * @mbggenerated
	 */
	public String getOrderCodeReceive() {
		return orderCodeReceive;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.order_code_receive
	 * @param orderCodeReceive  the value for transfer_order.order_code_receive
	 * @mbggenerated
	 */
	public void setOrderCodeReceive(String orderCodeReceive) {
		this.orderCodeReceive = orderCodeReceive == null ? null : orderCodeReceive.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.order_product_name
	 * @return  the value of transfer_order.order_product_name
	 * @mbggenerated
	 */
	public String getOrderProductName() {
		return orderProductName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.order_product_name
	 * @param orderProductName  the value for transfer_order.order_product_name
	 * @mbggenerated
	 */
	public void setOrderProductName(String orderProductName) {
		this.orderProductName = orderProductName == null ? null : orderProductName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.supplier_id
	 * @return  the value of transfer_order.supplier_id
	 * @mbggenerated
	 */
	public Integer getSupplierId() {
		return supplierId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.supplier_id
	 * @param supplierId  the value for transfer_order.supplier_id
	 * @mbggenerated
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.supplier_name
	 * @return  the value of transfer_order.supplier_name
	 * @mbggenerated
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.supplier_name
	 * @param supplierName  the value for transfer_order.supplier_name
	 * @mbggenerated
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName == null ? null : supplierName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.supplier_user_name
	 * @return  the value of transfer_order.supplier_user_name
	 * @mbggenerated
	 */
	public String getSupplierUserName() {
		return supplierUserName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.supplier_user_name
	 * @param supplierUserName  the value for transfer_order.supplier_user_name
	 * @mbggenerated
	 */
	public void setSupplierUserName(String supplierUserName) {
		this.supplierUserName = supplierUserName == null ? null : supplierUserName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.supplier_user_mobile
	 * @return  the value of transfer_order.supplier_user_mobile
	 * @mbggenerated
	 */
	public String getSupplierUserMobile() {
		return supplierUserMobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.supplier_user_mobile
	 * @param supplierUserMobile  the value for transfer_order.supplier_user_mobile
	 * @mbggenerated
	 */
	public void setSupplierUserMobile(String supplierUserMobile) {
		this.supplierUserMobile = supplierUserMobile == null ? null : supplierUserMobile.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.supplier_user_tel
	 * @return  the value of transfer_order.supplier_user_tel
	 * @mbggenerated
	 */
	public String getSupplierUserTel() {
		return supplierUserTel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.supplier_user_tel
	 * @param supplierUserTel  the value for transfer_order.supplier_user_tel
	 * @mbggenerated
	 */
	public void setSupplierUserTel(String supplierUserTel) {
		this.supplierUserTel = supplierUserTel == null ? null : supplierUserTel.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.supplier_user_fax
	 * @return  the value of transfer_order.supplier_user_fax
	 * @mbggenerated
	 */
	public String getSupplierUserFax() {
		return supplierUserFax;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.supplier_user_fax
	 * @param supplierUserFax  the value for transfer_order.supplier_user_fax
	 * @mbggenerated
	 */
	public void setSupplierUserFax(String supplierUserFax) {
		this.supplierUserFax = supplierUserFax == null ? null : supplierUserFax.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.daynum
	 * @return  the value of transfer_order.daynum
	 * @mbggenerated
	 */
	public Integer getDaynum() {
		return daynum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.daynum
	 * @param daynum  the value for transfer_order.daynum
	 * @mbggenerated
	 */
	public void setDaynum(Integer daynum) {
		this.daynum = daynum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.date_start
	 * @return  the value of transfer_order.date_start
	 * @mbggenerated
	 */
	public Date getDateStart() {
		return dateStart;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.date_start
	 * @param string  the value for transfer_order.date_start
	 * @mbggenerated
	 */
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.date_end
	 * @return  the value of transfer_order.date_end
	 * @mbggenerated
	 */
	public Date getDateEnd() {
		return dateEnd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.date_end
	 * @param dateEnd  the value for transfer_order.date_end
	 * @mbggenerated
	 */
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.person_adult
	 * @return  the value of transfer_order.person_adult
	 * @mbggenerated
	 */
	public Integer getPersonAdult() {
		return personAdult;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.person_adult
	 * @param personAdult  the value for transfer_order.person_adult
	 * @mbggenerated
	 */
	public void setPersonAdult(Integer personAdult) {
		this.personAdult = personAdult;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.person_child
	 * @return  the value of transfer_order.person_child
	 * @mbggenerated
	 */
	public Integer getPersonChild() {
		return personChild;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.person_child
	 * @param personChild  the value for transfer_order.person_child
	 * @mbggenerated
	 */
	public void setPersonChild(Integer personChild) {
		this.personChild = personChild;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.person_guide
	 * @return  the value of transfer_order.person_guide
	 * @mbggenerated
	 */
	public Integer getPersonGuide() {
		return personGuide;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.person_guide
	 * @param personGuide  the value for transfer_order.person_guide
	 * @mbggenerated
	 */
	public void setPersonGuide(Integer personGuide) {
		this.personGuide = personGuide;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.remark
	 * @return  the value of transfer_order.remark
	 * @mbggenerated
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.remark
	 * @param remark  the value for transfer_order.remark
	 * @mbggenerated
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.remark_service
	 * @return  the value of transfer_order.remark_service
	 * @mbggenerated
	 */
	public String getRemarkService() {
		return remarkService;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.remark_service
	 * @param remarkService  the value for transfer_order.remark_service
	 * @mbggenerated
	 */
	public void setRemarkService(String remarkService) {
		this.remarkService = remarkService == null ? null : remarkService.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.total
	 * @return  the value of transfer_order.total
	 * @mbggenerated
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.total
	 * @param total  the value for transfer_order.total
	 * @mbggenerated
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.state_receive
	 * @return  the value of transfer_order.state_receive
	 * @mbggenerated
	 */
	public Byte getStateReceive() {
		return stateReceive;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.state_receive
	 * @param stateReceive  the value for transfer_order.state_receive
	 * @mbggenerated
	 */
	public void setStateReceive(Byte stateReceive) {
		this.stateReceive = stateReceive;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.state_update
	 * @return  the value of transfer_order.state_update
	 * @mbggenerated
	 */
	public Byte getStateUpdate() {
		return stateUpdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.state_update
	 * @param stateUpdate  the value for transfer_order.state_update
	 * @mbggenerated
	 */
	public void setStateUpdate(Byte stateUpdate) {
		this.stateUpdate = stateUpdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.time_receive
	 * @return  the value of transfer_order.time_receive
	 * @mbggenerated
	 */
	public Date getTimeReceive() {
		return timeReceive;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.time_receive
	 * @param timeReceive  the value for transfer_order.time_receive
	 * @mbggenerated
	 */
	public void setTimeReceive(Date timeReceive) {
		this.timeReceive = timeReceive;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.time_create
	 * @return  the value of transfer_order.time_create
	 * @mbggenerated
	 */
	public Date getTimeCreate() {
		return timeCreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.time_create
	 * @param timeCreate  the value for transfer_order.time_create
	 * @mbggenerated
	 */
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column transfer_order.time_update
	 * @return  the value of transfer_order.time_update
	 * @mbggenerated
	 */
	public Date getTimeUpdate() {
		return timeUpdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column transfer_order.time_update
	 * @param timeUpdate  the value for transfer_order.time_update
	 * @mbggenerated
	 */
	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}
}