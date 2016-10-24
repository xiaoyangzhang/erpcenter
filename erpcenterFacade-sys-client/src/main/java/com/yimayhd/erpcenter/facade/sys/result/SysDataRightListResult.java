package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.SysDataRight;
/**
 * 
 * 描述：组织机构下的用户list返回结果的封装
 * @author liyong
 * 2016年10月21日
 */
public class SysDataRightListResult extends ResultSupport implements Serializable {
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private List<SysDataRight> sysDataRights = new ArrayList<SysDataRight>();
	
	public void setSysDataRights(List<SysDataRight> sysDataRights) {
		this.sysDataRights = sysDataRights;
	}
	
	public List<SysDataRight> getSysDataRights() {
		return sysDataRights;
	}

}
