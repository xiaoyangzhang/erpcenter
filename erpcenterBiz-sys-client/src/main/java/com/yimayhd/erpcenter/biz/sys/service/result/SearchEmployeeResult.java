package com.yimayhd.erpcenter.biz.sys.service.result;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class SearchEmployeeResult extends ResultSupport{

	private static final long serialVersionUID = -5636950350216985901L;
	
	private PlatformEmployeePo employee;

	public PlatformEmployeePo getEmployee() {
		return employee;
	}

	public void setEmployee(PlatformEmployeePo employee) {
		this.employee = employee;
	}
}
