package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.SysBizInfo;
/**
 * 
 * 描述：SysBizInfo对象封装
 * @author liyong
 * 2016年10月21日
 */
public class SysBizInfoFacadeResult extends ResultSupport implements Serializable {
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private SysBizInfo sysBizInfo = new SysBizInfo();
	public void setSysBizInfo(SysBizInfo sysBizInfo) {
		this.sysBizInfo = sysBizInfo;
	}
	
	public SysBizInfo getSysBizInfo() {
		return sysBizInfo;
	}

}
