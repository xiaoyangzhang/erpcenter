package com.yimayhd.erpcenter.dal.sales.client.taobao.po;

import java.io.Serializable;
import java.util.Date;

public class TaobaoOrder implements Serializable {

	
	private Integer id;
	
	private Integer bizId;
	
	private Integer orderId;
	
	private String nameProduct; //产品名称
	
	private String nameSKU; //套餐
	
	private String codeMaster; // 主订单号
	
	private String codeSlave; // 子订单号
	
	private String codeSelf; //自编码
	
	private String buyerAccountWangwang; // 	买家旺旺号
	
	private String buyerAccountAlipay;    // 支付宝账号
	
	private String buyerAccountMail;   // 买家邮箱
	
	private String buyerRegion;  //买家下单地区
	
	private String orderSource; //购买来源
	
	private String orderCreateTime; // 下单时间
	
	private String orderBuyTime; // 购买时间
	
	private String orderBuyTimeStr;
	
	private Double  orderPrice; //商品单价
	
	private Double  orderNum; //购买数量
	
	private Double  orderTotal; //金额
	
	private String  contactName; //收货人姓名
	
	private String  contactTel; //收货人电话
	
	private String  contactPostcode; //收货人邮编
	
	private String  contactRegion; //收货人地区
	
	private String  contactAddress; //收货人详细地址
	
	private String  remarkBuyer; //买家备注
	
	private String  remarkSaler; //卖家备注
	
	private String  myCreateUsername; //订单创建人
	
	private Date  myCreateTime; //订单创建时间
	
	private String  myUpdateUsername; //订单最后修改人
	
	private Date  myUpdateName; //订单最后修改时间
	
	private String  myState; //订单状态：NEW（未处理）、CONFIRM（已组单）、CANCEL（废弃）
	
	private String  myStoreId; //店名：爱游店、怡美店等
	
	private Date startMin;
	
	private Date startMax;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the nameProduct
	 */
	public String getNameProduct() {
		return nameProduct;
	}

	/**
	 * @param nameProduct the nameProduct to set
	 */
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}



	/**
	 * @return the nameSKU
	 */
	public String getNameSKU() {
		return nameSKU;
	}

	/**
	 * @param nameSKU the nameSKU to set
	 */
	public void setNameSKU(String nameSKU) {
		this.nameSKU = nameSKU;
	}

	/**
	 * @return the codeMaster
	 */
	public String getCodeMaster() {
		return codeMaster;
	}

	/**
	 * @param codeMaster the codeMaster to set
	 */
	public void setCodeMaster(String codeMaster) {
		this.codeMaster = codeMaster;
	}

	/**
	 * @return the codeSlave
	 */
	public String getCodeSlave() {
		return codeSlave;
	}

	/**
	 * @param codeSlave the codeSlave to set
	 */
	public void setCodeSlave(String codeSlave) {
		this.codeSlave = codeSlave;
	}

	/**
	 * @return the codeSelf
	 */
	public String getCodeSelf() {
		return codeSelf;
	}

	/**
	 * @param codeSelf the codeSelf to set
	 */
	public void setCodeSelf(String codeSelf) {
		this.codeSelf = codeSelf;
	}

	/**
	 * @return the buyerAccountWangwang
	 */
	public String getBuyerAccountWangwang() {
		return buyerAccountWangwang;
	}

	/**
	 * @param buyerAccountWangwang the buyerAccountWangwang to set
	 */
	public void setBuyerAccountWangwang(String buyerAccountWangwang) {
		this.buyerAccountWangwang = buyerAccountWangwang;
	}

	/**
	 * @return the buyerAccountAlipay
	 */
	public String getBuyerAccountAlipay() {
		return buyerAccountAlipay;
	}

	/**
	 * @param buyerAccountAlipay the buyerAccountAlipay to set
	 */
	public void setBuyerAccountAlipay(String buyerAccountAlipay) {
		this.buyerAccountAlipay = buyerAccountAlipay;
	}

	/**
	 * @return the buyerAccountMail
	 */
	public String getBuyerAccountMail() {
		return buyerAccountMail;
	}

	/**
	 * @param buyerAccountMail the buyerAccountMail to set
	 */
	public void setBuyerAccountMail(String buyerAccountMail) {
		this.buyerAccountMail = buyerAccountMail;
	}

	/**
	 * @return the buyerRegion
	 */
	public String getBuyerRegion() {
		return buyerRegion;
	}

	/**
	 * @param buyerRegion the buyerRegion to set
	 */
	public void setBuyerRegion(String buyerRegion) {
		this.buyerRegion = buyerRegion;
	}

	/**
	 * @return the orderSource
	 */
	public String getOrderSource() {
		return orderSource;
	}

	/**
	 * @param orderSource the orderSource to set
	 */
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	/**
	 * @return the orderCreateTime
	 */
	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	/**
	 * @param orderCreateTime the orderCreateTime to set
	 */
	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	/**
	 * @return the orderBuyTime
	 */
	public String getOrderBuyTime() {
		return orderBuyTime;
	}

	/**
	 * @param orderBuyTime the orderBuyTime to set
	 */
	public void setOrderBuyTime(String orderBuyTime) {
		this.orderBuyTime = orderBuyTime;
	}

	/**
	 * @return the orderBuyTimeStr
	 */
	public String getOrderBuyTimeStr() {
		return orderBuyTimeStr;
	}

	/**
	 * @param orderBuyTimeStr the orderBuyTimeStr to set
	 */
	public void setOrderBuyTimeStr(String orderBuyTimeStr) {
		this.orderBuyTimeStr = orderBuyTimeStr;
	}


	/**
	 * @return the orderPrice
	 */
	public Double getOrderPrice() {
		return orderPrice;
	}

	/**
	 * @param orderPrice the orderPrice to set
	 */
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	/**
	 * @return the orderNum
	 */
	public Double getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(Double orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the orderTotal
	 */
	public Double getOrderTotal() {
		return orderTotal;
	}

	/**
	 * @param orderTotal the orderTotal to set
	 */
	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the contactTel
	 */
	public String getContactTel() {
		return contactTel;
	}

	/**
	 * @param contactTel the contactTel to set
	 */
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	/**
	 * @return the contactPostcode
	 */
	public String getContactPostcode() {
		return contactPostcode;
	}

	/**
	 * @param contactPostcode the contactPostcode to set
	 */
	public void setContactPostcode(String contactPostcode) {
		this.contactPostcode = contactPostcode;
	}

	/**
	 * @return the contactRegion
	 */
	public String getContactRegion() {
		return contactRegion;
	}

	/**
	 * @param contactRegion the contactRegion to set
	 */
	public void setContactRegion(String contactRegion) {
		this.contactRegion = contactRegion;
	}

	/**
	 * @return the contactAddress
	 */
	public String getContactAddress() {
		return contactAddress;
	}

	/**
	 * @param contactAddress the contactAddress to set
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * @return the remarkBuyer
	 */
	public String getRemarkBuyer() {
		return remarkBuyer;
	}

	/**
	 * @param remarkBuyer the remarkBuyer to set
	 */
	public void setRemarkBuyer(String remarkBuyer) {
		this.remarkBuyer = remarkBuyer;
	}

	/**
	 * @return the remarkSaler
	 */
	public String getRemarkSaler() {
		return remarkSaler;
	}

	/**
	 * @param remarkSaler the remarkSaler to set
	 */
	public void setRemarkSaler(String remarkSaler) {
		this.remarkSaler = remarkSaler;
	}

	/**
	 * @return the myCreateUsername
	 */
	public String getMyCreateUsername() {
		return myCreateUsername;
	}

	/**
	 * @param myCreateUsername the myCreateUsername to set
	 */
	public void setMyCreateUsername(String myCreateUsername) {
		this.myCreateUsername = myCreateUsername;
	}

	/**
	 * @return the myCreateTime
	 */
	public Date getMyCreateTime() {
		return myCreateTime;
	}

	/**
	 * @param myCreateTime the myCreateTime to set
	 */
	public void setMyCreateTime(Date myCreateTime) {
		this.myCreateTime = myCreateTime;
	}

	/**
	 * @return the myUpdateUsername
	 */
	public String getMyUpdateUsername() {
		return myUpdateUsername;
	}

	/**
	 * @param myUpdateUsername the myUpdateUsername to set
	 */
	public void setMyUpdateUsername(String myUpdateUsername) {
		this.myUpdateUsername = myUpdateUsername;
	}

	/**
	 * @return the myUpdateName
	 */
	public Date getMyUpdateName() {
		return myUpdateName;
	}

	/**
	 * @param myUpdateName the myUpdateName to set
	 */
	public void setMyUpdateName(Date myUpdateName) {
		this.myUpdateName = myUpdateName;
	}

	/**
	 * @return the myState
	 */
	public String getMyState() {
		return myState;
	}

	/**
	 * @param myState the myState to set
	 */
	public void setMyState(String myState) {
		this.myState = myState;
	}

	/**
	 * @return the myStoreId
	 */
	public String getMyStoreId() {
		return myStoreId;
	}

	/**
	 * @param myStoreId the myStoreId to set
	 */
	public void setMyStoreId(String myStoreId) {
		this.myStoreId = myStoreId;
	}

	/**
	 * @return the startMin
	 */
	public Date getStartMin() {
		return startMin;
	}

	/**
	 * @param startMin the startMin to set
	 */
	public void setStartMin(Date startMin) {
		this.startMin = startMin;
	}

	/**
	 * @return the startMax
	 */
	public Date getStartMax() {
		return startMax;
	}

	/**
	 * @param startMax the startMax to set
	 */
	public void setStartMax(Date startMax) {
		this.startMax = startMax;
	}

}
