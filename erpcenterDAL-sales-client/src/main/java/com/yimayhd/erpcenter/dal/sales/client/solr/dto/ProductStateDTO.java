package com.yimayhd.erpcenter.dal.sales.client.solr.dto;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class ProductStateDTO implements Serializable{


	
	private static final long serialVersionUID = -7825603703944657447L;
	@Field
	private Integer infoId;//产品id
	@Field
	private Integer infoBizId;//商家id
	@Field
	private String infoCode;//产品编码
	@Field
	private Integer infoType;//产品类型
	@Field
	private Integer infoBrandId;//名称-品牌
	@Field
	private String infoBrandName;//名称-品牌名称
	@Field
	private String infoNameCity;//名称-城市
	@Field
	private String infoNameBrief;//简要说明
	@Field
	private Integer infoTravelDays;//行程天数
	@Field
	private Integer infoOrderNum;//排序,填写数字
	@Field
	private Integer infoDestProvinceId;//目的地省id
	@Field
	private String infoDestProvinceName;//目的地省
	@Field
	private Integer infoDestCityId;//目的地市id
	@Field
	private String infoDestCityName;//目的地市
	@Field
	private Integer infoState;//产品状态,1待上架2上架3下架-1删除
	@Field
	private Integer infoCreatorId;//创建人id
	@Field
	private String infoCreatorName;//创建人name
	@Field
	private Long infoCreateTime;//创建时间
	@Field
	private Integer infoOperatorId;//操作计调id
	@Field
	private String infoOperatorName;//操作计调name
	@Field
	private String tagTagName;//操作计调name
	@Field
	private String prOrgId;//pr表机构id
	/**
	 * @return the infoId
	 */
	public Integer getInfoId() {
		return infoId;
	}
	/**
	 * @param infoId the infoId to set
	 */
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	/**
	 * @return the infoBizId
	 */
	public Integer getInfoBizId() {
		return infoBizId;
	}
	/**
	 * @param infoBizId the infoBizId to set
	 */
	public void setInfoBizId(Integer infoBizId) {
		this.infoBizId = infoBizId;
	}
	/**
	 * @return the infoCode
	 */
	public String getInfoCode() {
		return infoCode;
	}
	/**
	 * @param infoCode the infoCode to set
	 */
	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	/**
	 * @return the infoType
	 */
	public Integer getInfoType() {
		return infoType;
	}
	/**
	 * @param infoType the infoType to set
	 */
	public void setInfoType(Integer infoType) {
		this.infoType = infoType;
	}
	/**
	 * @return the infoBrandId
	 */
	public Integer getInfoBrandId() {
		return infoBrandId;
	}
	/**
	 * @param infoBrandId the infoBrandId to set
	 */
	public void setInfoBrandId(Integer infoBrandId) {
		this.infoBrandId = infoBrandId;
	}
	/**
	 * @return the infoBrandName
	 */
	public String getInfoBrandName() {
		return infoBrandName;
	}
	/**
	 * @param infoBrandName the infoBrandName to set
	 */
	public void setInfoBrandName(String infoBrandName) {
		this.infoBrandName = infoBrandName;
	}
	/**
	 * @return the infoNameCity
	 */
	public String getInfoNameCity() {
		return infoNameCity;
	}
	/**
	 * @param infoNameCity the infoNameCity to set
	 */
	public void setInfoNameCity(String infoNameCity) {
		this.infoNameCity = infoNameCity;
	}
	/**
	 * @return the infoNameBrief
	 */
	public String getInfoNameBrief() {
		return infoNameBrief;
	}
	/**
	 * @param infoNameBrief the infoNameBrief to set
	 */
	public void setInfoNameBrief(String infoNameBrief) {
		this.infoNameBrief = infoNameBrief;
	}
	/**
	 * @return the infoTravelDays
	 */
	public Integer getInfoTravelDays() {
		return infoTravelDays;
	}
	/**
	 * @param infoTravelDays the infoTravelDays to set
	 */
	public void setInfoTravelDays(Integer infoTravelDays) {
		this.infoTravelDays = infoTravelDays;
	}
	/**
	 * @return the infoOrderNum
	 */
	public Integer getInfoOrderNum() {
		return infoOrderNum;
	}
	/**
	 * @param infoOrderNum the infoOrderNum to set
	 */
	public void setInfoOrderNum(Integer infoOrderNum) {
		this.infoOrderNum = infoOrderNum;
	}
	/**
	 * @return the infoDestProvinceId
	 */
	public Integer getInfoDestProvinceId() {
		return infoDestProvinceId;
	}
	/**
	 * @param infoDestProvinceId the infoDestProvinceId to set
	 */
	public void setInfoDestProvinceId(Integer infoDestProvinceId) {
		this.infoDestProvinceId = infoDestProvinceId;
	}
	/**
	 * @return the infoDestProvinceName
	 */
	public String getInfoDestProvinceName() {
		return infoDestProvinceName;
	}
	/**
	 * @param infoDestProvinceName the infoDestProvinceName to set
	 */
	public void setInfoDestProvinceName(String infoDestProvinceName) {
		this.infoDestProvinceName = infoDestProvinceName;
	}
	/**
	 * @return the infoDestCityId
	 */
	public Integer getInfoDestCityId() {
		return infoDestCityId;
	}
	/**
	 * @param infoDestCityId the infoDestCityId to set
	 */
	public void setInfoDestCityId(Integer infoDestCityId) {
		this.infoDestCityId = infoDestCityId;
	}
	/**
	 * @return the infoDestCityName
	 */
	public String getInfoDestCityName() {
		return infoDestCityName;
	}
	/**
	 * @param infoDestCityName the infoDestCityName to set
	 */
	public void setInfoDestCityName(String infoDestCityName) {
		this.infoDestCityName = infoDestCityName;
	}
	/**
	 * @return the infoState
	 */
	public Integer getInfoState() {
		return infoState;
	}
	/**
	 * @param infoState the infoState to set
	 */
	public void setInfoState(Integer infoState) {
		this.infoState = infoState;
	}
	/**
	 * @return the infoCreatorId
	 */
	public Integer getInfoCreatorId() {
		return infoCreatorId;
	}
	/**
	 * @param infoCreatorId the infoCreatorId to set
	 */
	public void setInfoCreatorId(Integer infoCreatorId) {
		this.infoCreatorId = infoCreatorId;
	}
	/**
	 * @return the infoCreatorName
	 */
	public String getInfoCreatorName() {
		return infoCreatorName;
	}
	/**
	 * @param infoCreatorName the infoCreatorName to set
	 */
	public void setInfoCreatorName(String infoCreatorName) {
		this.infoCreatorName = infoCreatorName;
	}
	/**
	 * @return the infoCreateTime
	 */
	public Long getInfoCreateTime() {
		return infoCreateTime;
	}
	/**
	 * @param infoCreateTime the infoCreateTime to set
	 */
	public void setInfoCreateTime(Long infoCreateTime) {
		this.infoCreateTime = infoCreateTime;
	}
	/**
	 * @return the infoOperatorId
	 */
	public Integer getInfoOperatorId() {
		return infoOperatorId;
	}
	/**
	 * @param infoOperatorId the infoOperatorId to set
	 */
	public void setInfoOperatorId(Integer infoOperatorId) {
		this.infoOperatorId = infoOperatorId;
	}
	/**
	 * @return the infoOperatorName
	 */
	public String getInfoOperatorName() {
		return infoOperatorName;
	}
	/**
	 * @param infoOperatorName the infoOperatorName to set
	 */
	public void setInfoOperatorName(String infoOperatorName) {
		this.infoOperatorName = infoOperatorName;
	}
	/**
	 * @return the prOrgId
	 */
	public String getPrOrgId() {
		return prOrgId;
	}
	/**
	 * @param prOrgId the prOrgId to set
	 */
	public void setPrOrgId(String prOrgId) {
		this.prOrgId = prOrgId;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	

}
