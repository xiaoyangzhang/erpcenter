package com.yimayhd.erpcenter.dal.product.query;


import java.io.Serializable;

import com.yimayhd.erpcenter.common.query.PageQuery;

public class ProductStatePageQueryDTO extends PageQuery implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1514932164244446916L;
	
	/**
	 * 产品id
	 */

	private Integer infoId;//产品id
	private Integer infoBizId;//商家id
	private String infoCode;//产品编码
	private Integer infoBrandId;//名称-品牌
	private String infoBrandName;//名称-品牌名称
	private String infoNameCity;//名称-城市
	private Byte infoState;//产品状态,1待上架2上架3下架-1删除
	private Integer infoOperatorId;//操作计调id
	private String infoOperatorIds;//操作计调id
	private String prOrgId;//pr表机构id
	private Integer atObjType;//'1产品2路线'
	private Integer atType;//'类型:1图片2附件'
	private String atName;//附件名称
	private String atPath;//附件路径
	private Integer infoTravelDays;//行程天数
	private String tagTagName;//标签name
	/**
	 * @return the infoOperatorIds
	 */
	public String getInfoOperatorIds() {
		return infoOperatorIds;
	}
	/**
	 * @param infoOperatorIds the infoOperatorIds to set
	 */
	public void setInfoOperatorIds(String infoOperatorIds) {
		this.infoOperatorIds = infoOperatorIds;
	}
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
	 * @return the infoState
	 */
	public Byte getInfoState() {
		return infoState;
	}
	/**
	 * @param infoState the infoState to set
	 */
	public void setInfoState(Byte infoState) {
		this.infoState = infoState;
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
	 * @return the atObjType
	 */
	public Integer getAtObjType() {
		return atObjType;
	}
	/**
	 * @param atObjType the atObjType to set
	 */
	public void setAtObjType(Integer atObjType) {
		this.atObjType = atObjType;
	}
	/**
	 * @return the atType
	 */
	public Integer getAtType() {
		return atType;
	}
	/**
	 * @param atType the atType to set
	 */
	public void setAtType(Integer atType) {
		this.atType = atType;
	}
	/**
	 * @return the atName
	 */
	public String getAtName() {
		return atName;
	}
	/**
	 * @param atName the atName to set
	 */
	public void setAtName(String atName) {
		this.atName = atName;
	}
	/**
	 * @return the atPath
	 */
	public String getAtPath() {
		return atPath;
	}
	/**
	 * @param atPath the atPath to set
	 */
	public void setAtPath(String atPath) {
		this.atPath = atPath;
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
	 * @return the tagTagName
	 */
	public String getTagTagName() {
		return tagTagName;
	}
	/**
	 * @param tagTagName the tagTagName to set
	 */
	public void setTagTagName(String tagTagName) {
		this.tagTagName = tagTagName;
	}
	

}
