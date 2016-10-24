package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class PlatformEmployeePoListDTO  implements Serializable{
	
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
