package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

public class TaobaoOrderListByOpDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int bizId;
	private List<DicInfo> pp;
	private List<RegionInfo> allProvince;
	private List<DicInfo> typeList;
	private List<DicInfo> sourceTypeList;
	private String orgJsonStr;
	private String orgUserJsonStr;
	
	private GroupOrder groupOrder;
	private Set<Integer> dataUserIdSets;
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
	 * @return the pageTotalAudit
	 */
	public Integer getPageTotalAudit() {
		return pageTotalAudit;
	}
	/**
	 * @param pageTotalAudit the pageTotalAudit to set
	 */
	public void setPageTotalAudit(Integer pageTotalAudit) {
		this.pageTotalAudit = pageTotalAudit;
	}
	/**
	 * @return the pageTotalChild
	 */
	public Integer getPageTotalChild() {
		return pageTotalChild;
	}
	/**
	 * @param pageTotalChild the pageTotalChild to set
	 */
	public void setPageTotalChild(Integer pageTotalChild) {
		this.pageTotalChild = pageTotalChild;
	}
	/**
	 * @return the pageTotalGuide
	 */
	public Integer getPageTotalGuide() {
		return pageTotalGuide;
	}
	/**
	 * @param pageTotalGuide the pageTotalGuide to set
	 */
	public void setPageTotalGuide(Integer pageTotalGuide) {
		this.pageTotalGuide = pageTotalGuide;
	}
	/**
	 * @return the pageTotal
	 */
	public BigDecimal getPageTotal() {
		return pageTotal;
	}
	/**
	 * @param pageTotal the pageTotal to set
	 */
	public void setPageTotal(BigDecimal pageTotal) {
		this.pageTotal = pageTotal;
	}
	/**
	 * @return the page
	 */
	public PageBean<GroupOrder> getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(PageBean<GroupOrder> page) {
		this.page = page;
	}
	private Integer pageTotalAudit;
	private Integer pageTotalChild;
	private Integer pageTotalGuide;
	private BigDecimal pageTotal;
	private PageBean<GroupOrder> page;
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
	
	
	
	
}
