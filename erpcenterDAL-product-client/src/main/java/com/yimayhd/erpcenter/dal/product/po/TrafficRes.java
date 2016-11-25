package com.yimayhd.erpcenter.dal.product.po;


import com.yimayhd.erpcenter.common.util.LogFieldAnno;

import java.io.Serializable;
import java.math.BigDecimal;

public class TrafficRes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3414693074311675082L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.id
	 * @mbggenerated
	 */
	@LogFieldAnno(isKey = true)
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.biz_id
	 * @mbggenerated
	 */
	private Integer bizId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.res_name
	 * @mbggenerated
	 */
	@LogFieldAnno(description="资源名称", delOrIns = true)
	private String resName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.res_method
	 * @mbggenerated
	 */
	@LogFieldAnno(description="类型")
	private String resMethod;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.cost_price
	 * @mbggenerated
	 */
	@LogFieldAnno(description="成本价", delOrIns = true)
	private BigDecimal costPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.num_stock
	 * @mbggenerated
	 */
	@LogFieldAnno(description="总库存", delOrIns = true)
	private Integer numStock;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.num_disable
	 * @mbggenerated
	 */
	@LogFieldAnno(description="机动位", delOrIns = true)
	private Integer numDisable;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.num_reserved
	 * @mbggenerated
	 */
	@LogFieldAnno(description="预留", delOrIns = true)
	private Integer numReserved;
	@LogFieldAnno(description="清预留", delOrIns = true)
	private Integer numClean;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.num_sold
	 * @mbggenerated
	 */
	@LogFieldAnno(description="已售", delOrIns = true)
	private Integer numSold;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.num_balance
	 * @mbggenerated
	 */
	@LogFieldAnno(description="余位", delOrIns = true)
	private Integer numBalance;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.state
	 * @mbggenerated
	 */
	@LogFieldAnno(description="状态")
	private Byte state;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.date_start
	 * @mbggenerated
	 */
	@LogFieldAnno(description="日期", delOrIns = true)
	private String dateStart;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.date_latest
	 * @mbggenerated
	 */
	@LogFieldAnno(description="最晚预定时间")
	private String dateLatest;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.remark
	 * @mbggenerated
	 */

	@LogFieldAnno(description="备注")
	private String remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.user_id
	 * @mbggenerated
	 */
	private Integer userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.user_name
	 * @mbggenerated
	 */
	private String userName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.time_create
	 * @mbggenerated
	 */
	private String timeCreate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res.time_update
	 * @mbggenerated
	 */
	private String timeUpdate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.id
	 * @return  the value of traffic_res.id
	 * @mbggenerated
	 */
	private String classNo;
	private String departureCity;
	private String departureTime;
	private String arrivalCity;
	private String arrivalTime;
	private String lineInfo;
	
	private String adjustTime;
	private String STOCK;
	private String STOCKDISABLE;
	private String ORDERSOLD;
	private String ORDERRESERVE;
	private String ORDERCANCEL;
	private String ORDERCLEAN;  
	private String  ORDERCONFIRM;
	private String ORDERUNCONFIRM;
	private String newDateLatest;
	private String date;
	private Integer numCancel;
	private Integer sumTicket;

	private BigDecimal childPrice;
	private BigDecimal babyPrice;
	
	
	public BigDecimal getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(BigDecimal childPrice) {
		this.childPrice = childPrice;
	}

	public BigDecimal getBabyPrice() {
		return babyPrice;
	}

	public void setBabyPrice(BigDecimal babyPrice) {
		this.babyPrice = babyPrice;
	}

	public Integer getSumTicket() {
		return sumTicket;
	}

	public void setSumTicket(Integer sumTicket) {
		this.sumTicket = sumTicket;
	}

	public Integer getNumCancel() {
		return numCancel;
	}

	public void setNumCancel(Integer numCancel) {
		this.numCancel = numCancel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNewDateLatest() {
		return newDateLatest;
	}

	public void setNewDateLatest(String newDateLatest) {
		this.newDateLatest = newDateLatest;
	}

	public String getAdjustTime() {
		return adjustTime;
	}

	public void setAdjustTime(String adjustTime) {
		this.adjustTime = adjustTime;
	}

	public String getSTOCK() {
		return STOCK;
	}

	public void setSTOCK(String sTOCK) {
		STOCK = sTOCK;
	}

	public String getSTOCKDISABLE() {
		return STOCKDISABLE;
	}

	public void setSTOCKDISABLE(String sTOCKDISABLE) {
		STOCKDISABLE = sTOCKDISABLE;
	}

	public String getORDERSOLD() {
		return ORDERSOLD;
	}

	public void setORDERSOLD(String oRDERSOLD) {
		ORDERSOLD = oRDERSOLD;
	}

	public String getORDERRESERVE() {
		return ORDERRESERVE;
	}

	public void setORDERRESERVE(String oRDERRESERVE) {
		ORDERRESERVE = oRDERRESERVE;
	}

	public String getORDERCANCEL() {
		return ORDERCANCEL;
	}

	public void setORDERCANCEL(String oRDERCANCEL) {
		ORDERCANCEL = oRDERCANCEL;
	}

	public String getORDERCLEAN() {
		return ORDERCLEAN;
	}

	public void setORDERCLEAN(String oRDERCLEAN) {
		ORDERCLEAN = oRDERCLEAN;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getLineInfo() {
		return lineInfo;
	}

	public void setLineInfo(String lineInfo) {
		this.lineInfo = lineInfo;
	}

	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.id
	 * @param id  the value for traffic_res.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.biz_id
	 * @return  the value of traffic_res.biz_id
	 * @mbggenerated
	 */
	public Integer getBizId() {
		return bizId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.biz_id
	 * @param bizId  the value for traffic_res.biz_id
	 * @mbggenerated
	 */
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.res_name
	 * @return  the value of traffic_res.res_name
	 * @mbggenerated
	 */
	public String getResName() {
		return resName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.res_name
	 * @param resName  the value for traffic_res.res_name
	 * @mbggenerated
	 */
	public void setResName(String resName) {
		this.resName = resName == null ? null : resName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.res_method
	 * @return  the value of traffic_res.res_method
	 * @mbggenerated
	 */
	public String getResMethod() {
		return resMethod;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.res_method
	 * @param resMethod  the value for traffic_res.res_method
	 * @mbggenerated
	 */
	public void setResMethod(String resMethod) {
		this.resMethod = resMethod == null ? null : resMethod.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.cost_price
	 * @return  the value of traffic_res.cost_price
	 * @mbggenerated
	 */
	public BigDecimal getCostPrice() {
		return costPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.cost_price
	 * @param costPrice  the value for traffic_res.cost_price
	 * @mbggenerated
	 */
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.num_stock
	 * @return  the value of traffic_res.num_stock
	 * @mbggenerated
	 */
	public Integer getNumStock() {
		return numStock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.num_stock
	 * @param numStock  the value for traffic_res.num_stock
	 * @mbggenerated
	 */
	public void setNumStock(Integer numStock) {
		this.numStock = numStock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.num_disable
	 * @return  the value of traffic_res.num_disable
	 * @mbggenerated
	 */
	public Integer getNumDisable() {
		return numDisable;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.num_disable
	 * @param numDisable  the value for traffic_res.num_disable
	 * @mbggenerated
	 */
	public void setNumDisable(Integer numDisable) {
		this.numDisable = numDisable;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.num_reserved
	 * @return  the value of traffic_res.num_reserved
	 * @mbggenerated
	 */
	public Integer getNumReserved() {
		return numReserved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.num_reserved
	 * @param numReserved  the value for traffic_res.num_reserved
	 * @mbggenerated
	 */
	public void setNumReserved(Integer numReserved) {
		this.numReserved = numReserved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.num_sold
	 * @return  the value of traffic_res.num_sold
	 * @mbggenerated
	 */
	public Integer getNumSold() {
		return numSold;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.num_sold
	 * @param numSold  the value for traffic_res.num_sold
	 * @mbggenerated
	 */
	public void setNumSold(Integer numSold) {
		this.numSold = numSold;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.num_balance
	 * @return  the value of traffic_res.num_balance
	 * @mbggenerated
	 */
	public Integer getNumBalance() {
		return numBalance;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.num_balance
	 * @param numBalance  the value for traffic_res.num_balance
	 * @mbggenerated
	 */
	public void setNumBalance(Integer numBalance) {
		this.numBalance = numBalance;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.state
	 * @return  the value of traffic_res.state
	 * @mbggenerated
	 */
	public Byte getState() {
		return state;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.state
	 * @param state  the value for traffic_res.state
	 * @mbggenerated
	 */
	public void setState(Byte state) {
		this.state = state;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.date_start
	 * @return  the value of traffic_res.date_start
	 * @mbggenerated
	 */
	public String getDateStart() {
		return dateStart;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.date_start
	 * @param dateStart  the value for traffic_res.date_start
	 * @mbggenerated
	 */
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.date_latest
	 * @return  the value of traffic_res.date_latest
	 * @mbggenerated
	 */
	public String getDateLatest() {
		return dateLatest;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.date_latest
	 * @param dateLatest  the value for traffic_res.date_latest
	 * @mbggenerated
	 */
	public void setDateLatest(String dateLatest) {
		this.dateLatest = dateLatest;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.remark
	 * @return  the value of traffic_res.remark
	 * @mbggenerated
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.remark
	 * @param remark  the value for traffic_res.remark
	 * @mbggenerated
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.user_id
	 * @return  the value of traffic_res.user_id
	 * @mbggenerated
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.user_id
	 * @param userId  the value for traffic_res.user_id
	 * @mbggenerated
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.user_name
	 * @return  the value of traffic_res.user_name
	 * @mbggenerated
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.user_name
	 * @param userName  the value for traffic_res.user_name
	 * @mbggenerated
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.time_create
	 * @return  the value of traffic_res.time_create
	 * @mbggenerated
	 */
	public String getTimeCreate() {
		return timeCreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.time_create
	 * @param timeCreate  the value for traffic_res.time_create
	 * @mbggenerated
	 */
	public void setTimeCreate(String timeCreate) {
		this.timeCreate = timeCreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res.time_update
	 * @return  the value of traffic_res.time_update
	 * @mbggenerated
	 */
	public String getTimeUpdate() {
		return timeUpdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res.time_update
	 * @param timeUpdate  the value for traffic_res.time_update
	 * @mbggenerated
	 */
	public void setTimeUpdate(String timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	public Integer getNumClean() {
		return numClean;
	}

	public void setNumClean(Integer numClean) {
		this.numClean = numClean;
	}

	public String getORDERCONFIRM() {
		return ORDERCONFIRM;
	}

	public void setORDERCONFIRM(String oRDERCONFIRM) {
		ORDERCONFIRM = oRDERCONFIRM;
	}

	public String getORDERUNCONFIRM() {
		return ORDERUNCONFIRM;
	}

	public void setORDERUNCONFIRM(String oRDERUNCONFIRM) {
		ORDERUNCONFIRM = oRDERUNCONFIRM;
	}
}