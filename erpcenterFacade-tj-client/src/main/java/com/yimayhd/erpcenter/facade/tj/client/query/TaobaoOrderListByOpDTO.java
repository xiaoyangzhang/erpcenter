package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

public class TaobaoOrderListByOpDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int bizId;
	private List<DicInfo> pp;
	private List<RegionInfo> allProvince;
	private List<DicInfo> typeList;
	private List<DicInfo> sourceTypeList;
	private String orgJsonStr;
	private String orgUserJsonStr;
	/**
	 * @return the bizId
	 */
	public int getBizId() {
		return bizId;
	}
	/**
	 * @param bizId the bizId to set
	 */
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	/**
	 * @return the pp
	 */
	public List<DicInfo> getPp() {
		return pp;
	}
	/**
	 * @param pp the pp to set
	 */
	public void setPp(List<DicInfo> pp) {
		this.pp = pp;
	}
	/**
	 * @return the allProvince
	 */
	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}
	/**
	 * @param allProvince the allProvince to set
	 */
	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}
	/**
	 * @return the typeList
	 */
	public List<DicInfo> getTypeList() {
		return typeList;
	}
	/**
	 * @param typeList the typeList to set
	 */
	public void setTypeList(List<DicInfo> typeList) {
		this.typeList = typeList;
	}
	/**
	 * @return the sourceTypeList
	 */
	public List<DicInfo> getSourceTypeList() {
		return sourceTypeList;
	}
	/**
	 * @param sourceTypeList the sourceTypeList to set
	 */
	public void setSourceTypeList(List<DicInfo> sourceTypeList) {
		this.sourceTypeList = sourceTypeList;
	}
	/**
	 * @return the orgJsonStr
	 */
	public String getOrgJsonStr() {
		return orgJsonStr;
	}
	/**
	 * @param orgJsonStr the orgJsonStr to set
	 */
	public void setOrgJsonStr(String orgJsonStr) {
		this.orgJsonStr = orgJsonStr;
	}
	/**
	 * @return the orgUserJsonStr
	 */
	public String getOrgUserJsonStr() {
		return orgUserJsonStr;
	}
	/**
	 * @param orgUserJsonStr the orgUserJsonStr to set
	 */
	public void setOrgUserJsonStr(String orgUserJsonStr) {
		this.orgUserJsonStr = orgUserJsonStr;
	}
	
	
	
	
}
