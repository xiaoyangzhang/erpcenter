package com.yimayhd.erpcenter.biz.sys.service.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class SearchEmployeeListResult extends ResultSupport{

	private static final long serialVersionUID = -5636950350216985901L;
	
	private List<PlatformEmployeePo> employeeList;
	public List<PlatformEmployeePo> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<PlatformEmployeePo> employeeList) {
		this.employeeList = employeeList;
	}
}
