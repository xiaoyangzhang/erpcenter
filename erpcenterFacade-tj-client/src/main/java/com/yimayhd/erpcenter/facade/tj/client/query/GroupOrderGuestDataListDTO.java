package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class GroupOrderGuestDataListDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private GroupOrder groupOrder;
    private Integer rows;
    private Integer pageSize; 
    private Integer page;
    private Integer userRightType;
    private String sidx;
    private String sord;
    private Integer bizId;
    private List<DicInfo> typeList;
    private PageBean pageBean;
    private Set<Integer> dataUserIdSets;
	/**
	 * @return the groupOrder
	 */
	public GroupOrder getGroupOrder() {
		return groupOrder;
	}
	/**
	 * @param groupOrder the groupOrder to set
	 */
	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}
	/**
	 * @return the rows
	 */
	public Integer getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
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
	 * @return the sidx
	 */
	public String getSidx() {
		return sidx;
	}
	/**
	 * @param sidx the sidx to set
	 */
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	/**
	 * @return the sord
	 */
	public String getSord() {
		return sord;
	}
	/**
	 * @param sord the sord to set
	 */
	public void setSord(String sord) {
		this.sord = sord;
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
    
    
}
