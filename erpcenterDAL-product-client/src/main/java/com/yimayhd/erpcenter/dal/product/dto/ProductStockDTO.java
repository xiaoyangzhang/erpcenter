package com.yimayhd.erpcenter.dal.product.dto;

import java.io.Serializable;

public class ProductStockDTO implements Serializable{
	
	private static final long serialVersionUID = 85760395896024430L;
	
	private Integer infoId;//产品id
	private Integer infoBizId;//商家id
	private Integer infoBrandId;//名称-品牌
	private String infoBrandName;//名称-品牌名称
	private String infoNameCity;//名称-城市
	private Byte infoState;//产品状态,1待上架2上架3下架-1删除
	private Integer psId;//pr表id
	private Long psItemDate;//pr表库存日期时间
	private Integer psStockCount;//总量
	private Integer psReceiveCount;//预定数量
	private Integer psState;//ps表状态1正常 -1删除
	private String prOrgId;//pr表机构id
	
	
	
	/**
	 * @return the psId
	 */
	public Integer getPsId() {
		return psId;
	}
	/**
	 * @param psId the psId to set
	 */
	public void setPsId(Integer psId) {
		this.psId = psId;
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
	 * @return the psItemDate
	 */
	public Long getPsItemDate() {
		return psItemDate;
	}
	/**
	 * @param psItemDate the psItemDate to set
	 */
	public void setPsItemDate(Long psItemDate) {
		this.psItemDate = psItemDate;
	}
	/**
	 * @return the psStockCount
	 */
	public Integer getPsStockCount() {
		return psStockCount;
	}
	/**
	 * @param psStockCount the psStockCount to set
	 */
	public void setPsStockCount(Integer psStockCount) {
		this.psStockCount = psStockCount;
	}
	/**
	 * @return the psReceiveCount
	 */
	public Integer getPsReceiveCount() {
		return psReceiveCount;
	}
	/**
	 * @param psReceiveCount the psReceiveCount to set
	 */
	public void setPsReceiveCount(Integer psReceiveCount) {
		this.psReceiveCount = psReceiveCount;
	}
	/**
	 * @return the psState
	 */
	public Integer getPsState() {
		return psState;
	}
	/**
	 * @param psState the psState to set
	 */
	public void setPsState(Integer psState) {
		this.psState = psState;
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
	
	
	
	
}
