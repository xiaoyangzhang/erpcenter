package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToNotGroupListResult extends BaseStateResult {

	private static final long serialVersionUID = -2253369421912970914L;

	private GroupOrder groupOrder;
	
	private Integer totalChild;
	private Integer totalAudit;
	private BigDecimal total;

	private Integer pageTotalAudit = 0;
	private Integer pageTotalChild = 0;
	private BigDecimal pageTotal;

	private List<RegionInfo> allProvince;
	private List<DicInfo> pp;
	private PageBean pageBean;
	
	private List<RegionInfo> cityList;
	private String orgJsonStr;
	private String orgUserJsonStr;

	HashMap<String, BigDecimal> map_sum;

	List<Map<String,Object>> list;

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

	public Integer getTotalChild() {
		return totalChild;
	}

	public void setTotalChild(Integer totalChild) {
		this.totalChild = totalChild;
	}

	public Integer getTotalAudit() {
		return totalAudit;
	}

	public void setTotalAudit(Integer totalAudit) {
		this.totalAudit = totalAudit;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getPageTotalAudit() {
		return pageTotalAudit;
	}

	public void setPageTotalAudit(Integer pageTotalAudit) {
		this.pageTotalAudit = pageTotalAudit;
	}

	public Integer getPageTotalChild() {
		return pageTotalChild;
	}

	public void setPageTotalChild(Integer pageTotalChild) {
		this.pageTotalChild = pageTotalChild;
	}

	public BigDecimal getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(BigDecimal pageTotal) {
		this.pageTotal = pageTotal;
	}

	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}

	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}

	public List<DicInfo> getPp() {
		return pp;
	}

	public void setPp(List<DicInfo> pp) {
		this.pp = pp;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<RegionInfo> getCityList() {
		return cityList;
	}

	public void setCityList(List<RegionInfo> cityList) {
		this.cityList = cityList;
	}

	public String getOrgJsonStr() {
		return orgJsonStr;
	}

	public void setOrgJsonStr(String orgJsonStr) {
		this.orgJsonStr = orgJsonStr;
	}

	public String getOrgUserJsonStr() {
		return orgUserJsonStr;
	}

	public void setOrgUserJsonStr(String orgUserJsonStr) {
		this.orgUserJsonStr = orgUserJsonStr;
	}

	public HashMap<String, BigDecimal> getMap_sum() {
		return map_sum;
	}

	public void setMap_sum(HashMap<String, BigDecimal> map_sum) {
		this.map_sum = map_sum;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
}
