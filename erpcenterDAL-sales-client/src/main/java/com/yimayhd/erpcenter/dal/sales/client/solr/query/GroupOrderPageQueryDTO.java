package com.yimayhd.erpcenter.dal.sales.client.solr.query;

import java.io.Serializable;
import java.util.Set;

import com.yimayhd.erpcenter.common.query.PageQuery;

public class GroupOrderPageQueryDTO extends PageQuery implements Serializable {

	private static final long serialVersionUID = 1514932164589646916L;
	
	private Integer tourGroupMode;//> 0 必须
	private Integer goState;//!= -1 必须
	private Integer goOrderType;//=1必须
	private Integer goBizId;//原mapper没判断，必传
	private Integer listType;//判断状态只判数字不判空0goSaleOperatorId，1goOperatorId
	private String goSaleOperatorId ;//IN没有判断
	private String goOperatorId;//IN没有判断
	private Integer dateType;//只盘数字不判空1tg.date_start，2tg.create_time
	private Long startTime;//判空和判null
	private Long endTime;//判空和判null
	private Integer tourGroupState;//判空判null
	private Integer cashState;//只判数字不判null，0goTotal=goTotalCache,1goTotal!=goTotalCache
	private Integer goTotal;//无具体数值
	private Integer goTotalCache;//无具体数值
	private String  goReceiveMode;//判空判null，LIKE
	private Integer goOrderLockState;//判空判null
	private Integer goProvinceId;//判null不等于空不等于-1
	private Integer goCityId;//判null不等于空不等于-1
	private Integer operType;//1sale_operator_id,2operator_id,3creator_id
	private String goCreatorId;//IN
	private Integer goSourcetypeId;//!=-1&&!=NULL
	private String tourGroupCode;//!=-1&&!=NULL
	private String goSupplierName;//!=-1&&!=NULL
	private String tourProductName;//!=-1&&!=NULL
	private Set<Integer> listTypeIds;//什么都不判
	
	private Integer goProductBrandId;
	private String goProductBrandName;
	private String goProductName;
	
	private String goOrderBusiness;
	private Integer goStateFinance;
	
	//排序条件ORDER BY tg.date_start ,tg.`create_time`
	//tg.group_mode > 0
	//go.state != -1
	//go.order_type = 1
    //go.biz_id = #{bizId,jdbcType=INTEGER}
	//go.sale_operator_id (listType==0)
	//go.operator_id in    (listType==1)
//	tg.date_start  #{page.parameter.startTime} <tg.date_start< #{page.parameter.endTime} page.parameter.dateType == 1
//	tg.create_time  #{page.parameter.startTime} <tg.date_start< #{page.parameter.endTime} page.parameter.dateType == 2
//	tg.group_state 
//	go.total != total_cash  page.parameter.cashState == 0
//	go.total = total_cash   page.parameter.cashState ==1
//	go.receive_mode LIKE
//	go.order_lock_state
//	and go.province_id page.parameter.provinceId !=-1
//	go.city_id =#{page.parameter.cityId} page.parameter.cityId
//	go.sale_operator_id in    page.parameter.operType==1
//	go.operator_id in    page.parameter.operType==2
//	go.creator_id in    page.parameter.operType==3
//	go.source_type_id page.parameter.sourceTypeId !=-1
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
	 * @return the listType
	 */
	public Integer getListType() {
		return listType;
	}
	/**
	 * @param listType the listType to set
	 */
	public void setListType(Integer listType) {
		this.listType = listType;
	}
	/**
	 * @return the goSaleOperatorId
	 */
	public String getGoSaleOperatorId() {
		return goSaleOperatorId;
	}
	/**
	 * @param goSaleOperatorId the goSaleOperatorId to set
	 */
	public void setGoSaleOperatorId(String goSaleOperatorId) {
		this.goSaleOperatorId = goSaleOperatorId;
	}
	/**
	 * @return the goOperatorId
	 */
	public String getGoOperatorId() {
		return goOperatorId;
	}
	/**
	 * @param goOperatorId the goOperatorId to set
	 */
	public void setGoOperatorId(String goOperatorId) {
		this.goOperatorId = goOperatorId;
	}
	/**
	 * @return the dateType
	 */
	public Integer getDateType() {
		return dateType;
	}
	/**
	 * @param dateType the dateType to set
	 */
	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}
	/**
	 * @return the startTime
	 */
	public Long getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public Long getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
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
	 * @return the cashState
	 */
	public Integer getCashState() {
		return cashState;
	}
	/**
	 * @param cashState the cashState to set
	 */
	public void setCashState(Integer cashState) {
		this.cashState = cashState;
	}
	/**
	 * @return the goTotal
	 */
	public Integer getGoTotal() {
		return goTotal;
	}
	/**
	 * @param goTotal the goTotal to set
	 */
	public void setGoTotal(Integer goTotal) {
		this.goTotal = goTotal;
	}
	/**
	 * @return the goTotalCache
	 */
	public Integer getGoTotalCache() {
		return goTotalCache;
	}
	/**
	 * @param goTotalCache the goTotalCache to set
	 */
	public void setGoTotalCache(Integer goTotalCache) {
		this.goTotalCache = goTotalCache;
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
	 * @return the operType
	 */
	public Integer getOperType() {
		return operType;
	}
	/**
	 * @param operType the operType to set
	 */
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	/**
	 * @return the goCreatorId
	 */
	public String getGoCreatorId() {
		return goCreatorId;
	}
	/**
	 * @param goCreatorId the goCreatorId to set
	 */
	public void setGoCreatorId(String goCreatorId) {
		this.goCreatorId = goCreatorId;
	}
	/**
	 * @return the goSourcetypeId
	 */
	public Integer getGoSourcetypeId() {
		return goSourcetypeId;
	}
	/**
	 * @param goSourcetypeId the goSourcetypeId to set
	 */
	public void setGoSourcetypeId(Integer goSourcetypeId) {
		this.goSourcetypeId = goSourcetypeId;
	}
	/**
	 * @return the listTypeIds
	 */
	public Set<Integer> getListTypeIds() {
		return listTypeIds;
	}
	/**
	 * @param listTypeIds the listTypeIds to set
	 */
	public void setListTypeIds(Set<Integer> listTypeIds) {
		this.listTypeIds = listTypeIds;
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
	 * @return the goOrderBusiness
	 */
	public String getGoOrderBusiness() {
		return goOrderBusiness;
	}
	/**
	 * @param goOrderBusiness the goOrderBusiness to set
	 */
	public void setGoOrderBusiness(String goOrderBusiness) {
		this.goOrderBusiness = goOrderBusiness;
	}
	/**
	 * @return the goStateFinance
	 */
	public Integer getGoStateFinance() {
		return goStateFinance;
	}
	/**
	 * @param goStateFinance the goStateFinance to set
	 */
	public void setGoStateFinance(Integer goStateFinance) {
		this.goStateFinance = goStateFinance;
	}
	
	
}
