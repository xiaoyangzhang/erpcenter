package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;

public class ToSaleGuestListExcelDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String startTime;
	private String endTime;
	private String receiveMode;
	private String groupCode;
	private String supplierName;
	private String orgIds;
	private String orgNames;
	private String operType;
	private String saleOperatorIds;
	private String saleOperatorName;
	private String orderMode;
	private String remark;
	private Integer page;
	private Integer pageSize;
	private Integer userRightType;
	private String guestName;
	private Integer gender;
	private Integer ageFirst;
	private Integer ageSecond;
	private String nativePlace;
	
	private Set<Integer> dataUserIdSets;
	private Integer bizId;
	private String url;
	private String path;
	
	private PageBean pageBean;
	
	private Integer doType;
	
	
	/**
	 * @return the doType
	 */
	public Integer getDoType() {
		return doType;
	}
	/**
	 * @param doType the doType to set
	 */
	public void setDoType(Integer doType) {
		this.doType = doType;
	}
	/**
	 * @return the pageBean
	 */
	public PageBean getPageBean() {
		return pageBean;
	}
	/**
	 * @param pageBean the pageBean to set
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the receiveMode
	 */
	public String getReceiveMode() {
		return receiveMode;
	}
	/**
	 * @param receiveMode the receiveMode to set
	 */
	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}
	/**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}
	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}
	/**
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * @return the orgIds
	 */
	public String getOrgIds() {
		return orgIds;
	}
	/**
	 * @param orgIds the orgIds to set
	 */
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	/**
	 * @return the orgNames
	 */
	public String getOrgNames() {
		return orgNames;
	}
	/**
	 * @param orgNames the orgNames to set
	 */
	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}
	/**
	 * @return the operType
	 */
	public String getOperType() {
		return operType;
	}
	/**
	 * @param operType the operType to set
	 */
	public void setOperType(String operType) {
		this.operType = operType;
	}
	/**
	 * @return the saleOperatorIds
	 */
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	/**
	 * @param saleOperatorIds the saleOperatorIds to set
	 */
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	/**
	 * @return the saleOperatorName
	 */
	public String getSaleOperatorName() {
		return saleOperatorName;
	}
	/**
	 * @param saleOperatorName the saleOperatorName to set
	 */
	public void setSaleOperatorName(String saleOperatorName) {
		this.saleOperatorName = saleOperatorName;
	}
	/**
	 * @return the orderMode
	 */
	public String getOrderMode() {
		return orderMode;
	}
	/**
	 * @param orderMode the orderMode to set
	 */
	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the userRightType
	 */
	public Integer getUserRightType() {
		return userRightType;
	}
	/**
	 * @param userRightType the userRightType to set
	 */
	public void setUserRightType(Integer userRightType) {
		this.userRightType = userRightType;
	}
	/**
	 * @return the guestName
	 */
	public String getGuestName() {
		return guestName;
	}
	/**
	 * @param guestName the guestName to set
	 */
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	/**
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * @return the ageFirst
	 */
	public Integer getAgeFirst() {
		return ageFirst;
	}
	/**
	 * @param ageFirst the ageFirst to set
	 */
	public void setAgeFirst(Integer ageFirst) {
		this.ageFirst = ageFirst;
	}
	/**
	 * @return the ageSecond
	 */
	public Integer getAgeSecond() {
		return ageSecond;
	}
	/**
	 * @param ageSecond the ageSecond to set
	 */
	public void setAgeSecond(Integer ageSecond) {
		this.ageSecond = ageSecond;
	}
	/**
	 * @return the nativePlace
	 */
	public String getNativePlace() {
		return nativePlace;
	}
	/**
	 * @param nativePlace the nativePlace to set
	 */
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	/**
	 * @return the dataUserIdSets
	 */
	public Set<Integer> getDataUserIdSets() {
		return dataUserIdSets;
	}
	/**
	 * @param dataUserIdSets the dataUserIdSets to set
	 */
	public void setDataUserIdSets(Set<Integer> dataUserIdSets) {
		this.dataUserIdSets = dataUserIdSets;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
