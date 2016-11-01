package com.yimayhd.erpcenter.dal.sales.client.solr.dto;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class GroupOrderDTO implements Serializable{

	   private static final long serialVersionUID = -7825603703944657447L;
	   @Field
	   private Integer goId ;//主键--------
	   @Field
	   private Integer goBizId ;//----------
	   @Field
	   private Integer goGroupId ;//'散客订单并团后才有',----------
	   @Field
	   private Integer goOrderType;//'0散客订单 1定制订单 -1一地散  -2初始订单',-------------
	   @Field
	   private String  goSupplierName ;// '组团社名称',-----------
	   @Field
	   private Integer goSupplierId ;//'组团社id',---------------
	   @Field
	   private Integer goOrderNoSort ;//------------------
	   @Field
	   private String  goOrderNo ;//--------------
	   @Field
	   private String goSupplierCode ;//
	   @Field
	   private String goContactName ;//'选择后直接带入姓名电话等，可以修改，不关联id',
	   @Field
	   private Integer goOperatorId ;//'当前登录人员',----------
	   @Field
	   private String goOperatorName ;// '当前登录人员',
	   @Field
	   private Integer goSaleOperatorId ;//'销售员id',---------------
	   @Field
	   private String goSaleOperatorName ;// '销售员名称',
	   @Field
	   private Integer goNumAdult ;//'人数：成人',
	   @Field
	   private Integer goNumChild ;// '人数：儿童',
	   @Field
	   private Integer goNumGuide ;// DEFAULT '0' COMMENT '人数：导演',
	   @Field
	   private Integer goNumChildBaby ;//DEFAULT '0' COMMENT '婴儿',
	   @Field
	   private Long goDepartureDate ;//'订单出发日期',
	   @Field
	   private Integer goProductId ;// '产品id',
	   @Field
	   private Integer goProductBrandId ;// '产品品牌id',
	   @Field
	   private String goProductBrandName ;//
	   @Field
	   private String goProductName ;//
	   @Field
	   private String goProductShortName ;// '产品简称',
	   @Field
	   private String goReceiveMode ;//'客人接站牌',--------
	   @Field
	   private Integer goCreatorId ;//-----------
	   @Field
	   private String goCreatorName ;//
	   @Field
	   private Long goCreateTime ;//----------
	   @Field
	   private Integer goState ;//'订单状态 1正常 -1删除',---------
	   @Field
	   private Double goTotal ;//decimal(18,4) DEFAULT '0.0000' '订单金额',-------------
	   @Field
	   private Double goTotalCash ;//decimal(18,4) DEFAULT '0.0000' '订单已收款金额',
	   @Field
	   private Integer goPriceId ;//----------
	   @Field
	   private Integer goStateSeal ;//DEFAULT '0'  '封存状态 0未封存 1已封存',------------
	   @Field
	   private String goSourceTypeName ;//'客源类别：同行，直客等；组团版使用agencyFit/toEditFirOrder.htm',
	   @Field
	   private Integer goProvinceId ;//-----------
	   @Field
	   private String goProvinceName ;//
	   @Field
	   private Integer goCityId ;//------------------
	   @Field
	   private String goCityName ;//
	   @Field
	   private Integer goOrderLockState ;//DEFAULT '0' '1是锁单状态，0是解锁状态，默认0',-----------------

	   //tour_group
	   @Field
	   private Integer tourGroupId;//------------
	   @Field
	   private Integer tourGroupMode ;//0时为散客团，>0时为定制团(字典为团类别) -1一地散 -2初始团，',----------
	   @Field
	   private String tourGroupCode ;//'自动生成',--------------
	   @Field
	   private Long tourDateStart ;//,----------
	   @Field
	   private Long tourDateEnd ;//,------------
	   @Field
	   private Long tourCreateTime ;//------------
	   @Field
	   private Integer tourGroupState ;//'0.未确认  1.已确认（待出团，行程中，已离开） 2.作废  3审核 4封存',-------------
	   @Field
	   private String tourProductBrandName ;//----------------
	   @Field
	   private String tourProductName ;//-----------------
	   @Field
	   private String tourOperatorName;//计调-----------
	   @Field
	   private Integer tourOperatorId ;//计调--------------
	   @Field
	   private Integer tourTotalAdult;//成人
	   @Field
	   private Integer tourTotalChild;//儿童
	   @Field
	   private Integer tourTotalGuide;//导游
	   @Field
	   private Integer tourDayNum;//导游
	   
	   //group_order_guest
	   @Field
	   private String guestGuestName ;//客人名字---------------
	   
	   
	   //group_order_price
	   @Field
	   private Double priceMode0 ;// '0收入1预算成本sum值',
	   @Field
	   private Double priceMode1 ;//'0收入1预算成本sum值',
	/**
	 * @return the goId
	 */
	public Integer getGoId() {
		return goId;
	}
	/**
	 * @param goId the goId to set
	 */
	public void setGoId(Integer goId) {
		this.goId = goId;
	}
	/**
	 * @return the goBizId
	 */
	public Integer getGoBizId() {
		return goBizId;
	}
	/**
	 * @param goBizId the goBizId to set
	 */
	public void setGoBizId(Integer goBizId) {
		this.goBizId = goBizId;
	}
	/**
	 * @return the goGroupId
	 */
	public Integer getGoGroupId() {
		return goGroupId;
	}
	/**
	 * @param goGroupId the goGroupId to set
	 */
	public void setGoGroupId(Integer goGroupId) {
		this.goGroupId = goGroupId;
	}
	/**
	 * @return the goOrderType
	 */
	public Integer getGoOrderType() {
		return goOrderType;
	}
	/**
	 * @param goOrderType the goOrderType to set
	 */
	public void setGoOrderType(Integer goOrderType) {
		this.goOrderType = goOrderType;
	}
	/**
	 * @return the goSupplierName
	 */
	public String getGoSupplierName() {
		return goSupplierName;
	}
	/**
	 * @param goSupplierName the goSupplierName to set
	 */
	public void setGoSupplierName(String goSupplierName) {
		this.goSupplierName = goSupplierName;
	}
	/**
	 * @return the goSupplierId
	 */
	public Integer getGoSupplierId() {
		return goSupplierId;
	}
	/**
	 * @param goSupplierId the goSupplierId to set
	 */
	public void setGoSupplierId(Integer goSupplierId) {
		this.goSupplierId = goSupplierId;
	}
	/**
	 * @return the goOrderNoSort
	 */
	public Integer getGoOrderNoSort() {
		return goOrderNoSort;
	}
	/**
	 * @param goOrderNoSort the goOrderNoSort to set
	 */
	public void setGoOrderNoSort(Integer goOrderNoSort) {
		this.goOrderNoSort = goOrderNoSort;
	}
	/**
	 * @return the goOrderNo
	 */
	public String getGoOrderNo() {
		return goOrderNo;
	}
	/**
	 * @param goOrderNo the goOrderNo to set
	 */
	public void setGoOrderNo(String goOrderNo) {
		this.goOrderNo = goOrderNo;
	}
	/**
	 * @return the goSupplierCode
	 */
	public String getGoSupplierCode() {
		return goSupplierCode;
	}
	/**
	 * @param goSupplierCode the goSupplierCode to set
	 */
	public void setGoSupplierCode(String goSupplierCode) {
		this.goSupplierCode = goSupplierCode;
	}
	/**
	 * @return the goContactName
	 */
	public String getGoContactName() {
		return goContactName;
	}
	/**
	 * @param goContactName the goContactName to set
	 */
	public void setGoContactName(String goContactName) {
		this.goContactName = goContactName;
	}
	/**
	 * @return the goOperatorId
	 */
	public Integer getGoOperatorId() {
		return goOperatorId;
	}
	/**
	 * @param goOperatorId the goOperatorId to set
	 */
	public void setGoOperatorId(Integer goOperatorId) {
		this.goOperatorId = goOperatorId;
	}
	/**
	 * @return the goOperatorName
	 */
	public String getGoOperatorName() {
		return goOperatorName;
	}
	/**
	 * @param goOperatorName the goOperatorName to set
	 */
	public void setGoOperatorName(String goOperatorName) {
		this.goOperatorName = goOperatorName;
	}
	/**
	 * @return the goSaleOperatorId
	 */
	public Integer getGoSaleOperatorId() {
		return goSaleOperatorId;
	}
	/**
	 * @param goSaleOperatorId the goSaleOperatorId to set
	 */
	public void setGoSaleOperatorId(Integer goSaleOperatorId) {
		this.goSaleOperatorId = goSaleOperatorId;
	}
	/**
	 * @return the goSaleOperatorName
	 */
	public String getGoSaleOperatorName() {
		return goSaleOperatorName;
	}
	/**
	 * @param goSaleOperatorName the goSaleOperatorName to set
	 */
	public void setGoSaleOperatorName(String goSaleOperatorName) {
		this.goSaleOperatorName = goSaleOperatorName;
	}
	/**
	 * @return the goNumAdult
	 */
	public Integer getGoNumAdult() {
		return goNumAdult;
	}
	/**
	 * @param goNumAdult the goNumAdult to set
	 */
	public void setGoNumAdult(Integer goNumAdult) {
		this.goNumAdult = goNumAdult;
	}
	/**
	 * @return the goNumChild
	 */
	public Integer getGoNumChild() {
		return goNumChild;
	}
	/**
	 * @param goNumChild the goNumChild to set
	 */
	public void setGoNumChild(Integer goNumChild) {
		this.goNumChild = goNumChild;
	}
	/**
	 * @return the goNumGuide
	 */
	public Integer getGoNumGuide() {
		return goNumGuide;
	}
	/**
	 * @param goNumGuide the goNumGuide to set
	 */
	public void setGoNumGuide(Integer goNumGuide) {
		this.goNumGuide = goNumGuide;
	}
	/**
	 * @return the goNumChildBaby
	 */
	public Integer getGoNumChildBaby() {
		return goNumChildBaby;
	}
	/**
	 * @param goNumChildBaby the goNumChildBaby to set
	 */
	public void setGoNumChildBaby(Integer goNumChildBaby) {
		this.goNumChildBaby = goNumChildBaby;
	}
	/**
	 * @return the goDepartureDate
	 */
	public Long getGoDepartureDate() {
		return goDepartureDate;
	}
	/**
	 * @param goDepartureDate the goDepartureDate to set
	 */
	public void setGoDepartureDate(Long goDepartureDate) {
		this.goDepartureDate = goDepartureDate;
	}
	/**
	 * @return the goProductId
	 */
	public Integer getGoProductId() {
		return goProductId;
	}
	/**
	 * @param goProductId the goProductId to set
	 */
	public void setGoProductId(Integer goProductId) {
		this.goProductId = goProductId;
	}
	/**
	 * @return the goProductBrandId
	 */
	public Integer getGoProductBrandId() {
		return goProductBrandId;
	}
	/**
	 * @param goProductBrandId the goProductBrandId to set
	 */
	public void setGoProductBrandId(Integer goProductBrandId) {
		this.goProductBrandId = goProductBrandId;
	}
	/**
	 * @return the goProductBrandName
	 */
	public String getGoProductBrandName() {
		return goProductBrandName;
	}
	/**
	 * @param goProductBrandName the goProductBrandName to set
	 */
	public void setGoProductBrandName(String goProductBrandName) {
		this.goProductBrandName = goProductBrandName;
	}
	/**
	 * @return the goProductName
	 */
	public String getGoProductName() {
		return goProductName;
	}
	/**
	 * @param goProductName the goProductName to set
	 */
	public void setGoProductName(String goProductName) {
		this.goProductName = goProductName;
	}
	/**
	 * @return the goProductShortName
	 */
	public String getGoProductShortName() {
		return goProductShortName;
	}
	/**
	 * @param goProductShortName the goProductShortName to set
	 */
	public void setGoProductShortName(String goProductShortName) {
		this.goProductShortName = goProductShortName;
	}
	/**
	 * @return the goReceiveMode
	 */
	public String getGoReceiveMode() {
		return goReceiveMode;
	}
	/**
	 * @param goReceiveMode the goReceiveMode to set
	 */
	public void setGoReceiveMode(String goReceiveMode) {
		this.goReceiveMode = goReceiveMode;
	}
	/**
	 * @return the goCreatorId
	 */
	public Integer getGoCreatorId() {
		return goCreatorId;
	}
	/**
	 * @param goCreatorId the goCreatorId to set
	 */
	public void setGoCreatorId(Integer goCreatorId) {
		this.goCreatorId = goCreatorId;
	}
	/**
	 * @return the goCreatorName
	 */
	public String getGoCreatorName() {
		return goCreatorName;
	}
	/**
	 * @param goCreatorName the goCreatorName to set
	 */
	public void setGoCreatorName(String goCreatorName) {
		this.goCreatorName = goCreatorName;
	}
	/**
	 * @return the goCreateTime
	 */
	public Long getGoCreateTime() {
		return goCreateTime;
	}
	/**
	 * @param goCreateTime the goCreateTime to set
	 */
	public void setGoCreateTime(Long goCreateTime) {
		this.goCreateTime = goCreateTime;
	}
	/**
	 * @return the goState
	 */
	public Integer getGoState() {
		return goState;
	}
	/**
	 * @param goState the goState to set
	 */
	public void setGoState(Integer goState) {
		this.goState = goState;
	}
	/**
	 * @return the goTotal
	 */
	public Double getGoTotal() {
		return goTotal;
	}
	/**
	 * @param goTotal the goTotal to set
	 */
	public void setGoTotal(Double goTotal) {
		this.goTotal = goTotal;
	}
	/**
	 * @return the goTotalCash
	 */
	public Double getGoTotalCash() {
		return goTotalCash;
	}
	/**
	 * @param goTotalCash the goTotalCash to set
	 */
	public void setGoTotalCash(Double goTotalCash) {
		this.goTotalCash = goTotalCash;
	}
	/**
	 * @return the goPriceId
	 */
	public Integer getGoPriceId() {
		return goPriceId;
	}
	/**
	 * @param goPriceId the goPriceId to set
	 */
	public void setGoPriceId(Integer goPriceId) {
		this.goPriceId = goPriceId;
	}
	/**
	 * @return the goStateSeal
	 */
	public Integer getGoStateSeal() {
		return goStateSeal;
	}
	/**
	 * @param goStateSeal the goStateSeal to set
	 */
	public void setGoStateSeal(Integer goStateSeal) {
		this.goStateSeal = goStateSeal;
	}
	/**
	 * @return the goSourceTypeName
	 */
	public String getGoSourceTypeName() {
		return goSourceTypeName;
	}
	/**
	 * @param goSourceTypeName the goSourceTypeName to set
	 */
	public void setGoSourceTypeName(String goSourceTypeName) {
		this.goSourceTypeName = goSourceTypeName;
	}
	/**
	 * @return the goProvinceId
	 */
	public Integer getGoProvinceId() {
		return goProvinceId;
	}
	/**
	 * @param goProvinceId the goProvinceId to set
	 */
	public void setGoProvinceId(Integer goProvinceId) {
		this.goProvinceId = goProvinceId;
	}
	/**
	 * @return the goProvinceName
	 */
	public String getGoProvinceName() {
		return goProvinceName;
	}
	/**
	 * @param goProvinceName the goProvinceName to set
	 */
	public void setGoProvinceName(String goProvinceName) {
		this.goProvinceName = goProvinceName;
	}
	/**
	 * @return the goCityId
	 */
	public Integer getGoCityId() {
		return goCityId;
	}
	/**
	 * @param goCityId the goCityId to set
	 */
	public void setGoCityId(Integer goCityId) {
		this.goCityId = goCityId;
	}
	/**
	 * @return the goCityName
	 */
	public String getGoCityName() {
		return goCityName;
	}
	/**
	 * @param goCityName the goCityName to set
	 */
	public void setGoCityName(String goCityName) {
		this.goCityName = goCityName;
	}
	/**
	 * @return the goOrderLockState
	 */
	public Integer getGoOrderLockState() {
		return goOrderLockState;
	}
	/**
	 * @param goOrderLockState the goOrderLockState to set
	 */
	public void setGoOrderLockState(Integer goOrderLockState) {
		this.goOrderLockState = goOrderLockState;
	}
	/**
	 * @return the tourGroupId
	 */
	public Integer getTourGroupId() {
		return tourGroupId;
	}
	/**
	 * @param tourGroupId the tourGroupId to set
	 */
	public void setTourGroupId(Integer tourGroupId) {
		this.tourGroupId = tourGroupId;
	}
	/**
	 * @return the tourGroupMode
	 */
	public Integer getTourGroupMode() {
		return tourGroupMode;
	}
	/**
	 * @param tourGroupMode the tourGroupMode to set
	 */
	public void setTourGroupMode(Integer tourGroupMode) {
		this.tourGroupMode = tourGroupMode;
	}
	/**
	 * @return the tourGroupCode
	 */
	public String getTourGroupCode() {
		return tourGroupCode;
	}
	/**
	 * @param tourGroupCode the tourGroupCode to set
	 */
	public void setTourGroupCode(String tourGroupCode) {
		this.tourGroupCode = tourGroupCode;
	}
	/**
	 * @return the tourDateStart
	 */
	public Long getTourDateStart() {
		return tourDateStart;
	}
	/**
	 * @param tourDateStart the tourDateStart to set
	 */
	public void setTourDateStart(Long tourDateStart) {
		this.tourDateStart = tourDateStart;
	}
	/**
	 * @return the tourDateEnd
	 */
	public Long getTourDateEnd() {
		return tourDateEnd;
	}
	/**
	 * @param tourDateEnd the tourDateEnd to set
	 */
	public void setTourDateEnd(Long tourDateEnd) {
		this.tourDateEnd = tourDateEnd;
	}
	/**
	 * @return the tourCreateTime
	 */
	public Long getTourCreateTime() {
		return tourCreateTime;
	}
	/**
	 * @param tourCreateTime the tourCreateTime to set
	 */
	public void setTourCreateTime(Long tourCreateTime) {
		this.tourCreateTime = tourCreateTime;
	}
	/**
	 * @return the tourGroupState
	 */
	public Integer getTourGroupState() {
		return tourGroupState;
	}
	/**
	 * @param tourGroupState the tourGroupState to set
	 */
	public void setTourGroupState(Integer tourGroupState) {
		this.tourGroupState = tourGroupState;
	}
	/**
	 * @return the tourProductBrandName
	 */
	public String getTourProductBrandName() {
		return tourProductBrandName;
	}
	/**
	 * @param tourProductBrandName the tourProductBrandName to set
	 */
	public void setTourProductBrandName(String tourProductBrandName) {
		this.tourProductBrandName = tourProductBrandName;
	}
	/**
	 * @return the tourProductName
	 */
	public String getTourProductName() {
		return tourProductName;
	}
	/**
	 * @param tourProductName the tourProductName to set
	 */
	public void setTourProductName(String tourProductName) {
		this.tourProductName = tourProductName;
	}
	/**
	 * @return the tourOperatorName
	 */
	public String getTourOperatorName() {
		return tourOperatorName;
	}
	/**
	 * @param tourOperatorName the tourOperatorName to set
	 */
	public void setTourOperatorName(String tourOperatorName) {
		this.tourOperatorName = tourOperatorName;
	}
	/**
	 * @return the tourOperatorId
	 */
	public Integer getTourOperatorId() {
		return tourOperatorId;
	}
	/**
	 * @param tourOperatorId the tourOperatorId to set
	 */
	public void setTourOperatorId(Integer tourOperatorId) {
		this.tourOperatorId = tourOperatorId;
	}
	/**
	 * @return the tourTotalAdult
	 */
	public Integer getTourTotalAdult() {
		return tourTotalAdult;
	}
	/**
	 * @param tourTotalAdult the tourTotalAdult to set
	 */
	public void setTourTotalAdult(Integer tourTotalAdult) {
		this.tourTotalAdult = tourTotalAdult;
	}
	/**
	 * @return the tourTotalChild
	 */
	public Integer getTourTotalChild() {
		return tourTotalChild;
	}
	/**
	 * @param tourTotalChild the tourTotalChild to set
	 */
	public void setTourTotalChild(Integer tourTotalChild) {
		this.tourTotalChild = tourTotalChild;
	}
	/**
	 * @return the tourTotalGuide
	 */
	public Integer getTourTotalGuide() {
		return tourTotalGuide;
	}
	/**
	 * @param tourTotalGuide the tourTotalGuide to set
	 */
	public void setTourTotalGuide(Integer tourTotalGuide) {
		this.tourTotalGuide = tourTotalGuide;
	}
	/**
	 * @return the guestGuestName
	 */
	public String getGuestGuestName() {
		return guestGuestName;
	}
	/**
	 * @param guestGuestName the guestGuestName to set
	 */
	public void setGuestGuestName(String guestGuestName) {
		this.guestGuestName = guestGuestName;
	}
	/**
	 * @return the priceMode0
	 */
	public Double getPriceMode0() {
		return priceMode0;
	}
	/**
	 * @param priceMode0 the priceMode0 to set
	 */
	public void setPriceMode0(Double priceMode0) {
		this.priceMode0 = priceMode0;
	}
	/**
	 * @return the priceMode1
	 */
	public Double getPriceMode1() {
		return priceMode1;
	}
	/**
	 * @param priceMode1 the priceMode1 to set
	 */
	public void setPriceMode1(Double priceMode1) {
		this.priceMode1 = priceMode1;
	}
	/**
	 * @return the tourDayNum
	 */
	public Integer getTourDayNum() {
		return tourDayNum;
	}
	/**
	 * @param tourDayNum the tourDayNum to set
	 */
	public void setTourDayNum(Integer tourDayNum) {
		this.tourDayNum = tourDayNum;
	}

	
	   
	   
}
