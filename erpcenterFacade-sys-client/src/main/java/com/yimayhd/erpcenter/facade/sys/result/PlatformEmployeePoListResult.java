package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
/**
 * 
 * 描述：List<PlatformEmployeePo> 的结果的封装
 * @author liyong
 * 2016年10月21日
 */
public class PlatformEmployeePoListResult extends ResultSupport implements Serializable {
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private List<PlatformEmployeePo> platformEmployeePos = new ArrayList<PlatformEmployeePo>();
	
	public void setPlatformEmployeePos(List<PlatformEmployeePo> platformEmployeePos) {
		this.platformEmployeePos = platformEmployeePos;
	}
	
	public List<PlatformEmployeePo> getPlatformEmployeePos() {
		return platformEmployeePos;
	}

}
