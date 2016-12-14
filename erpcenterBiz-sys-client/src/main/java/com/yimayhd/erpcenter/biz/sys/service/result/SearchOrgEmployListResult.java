package com.yimayhd.erpcenter.biz.sys.service.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class SearchOrgEmployListResult extends ResultSupport{

	private static final long serialVersionUID = -5636950350216985901L;
	
	private List<PlatformEmployeePo> employeeList;
	private long totalCount;
	private long totalPage;

	public List<PlatformEmployeePo> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<PlatformEmployeePo> employeeList) {
		this.employeeList = employeeList;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
}
