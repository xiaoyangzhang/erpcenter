package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
/**
 * 
 * 描述：单位结果对象的封装
 * @author liyong
 * 2016年10月21日
 */
public class PlatformOrgPoResult extends ResultSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	private PlatformOrgPo platformOrgPo = new PlatformOrgPo();
	
	public void setPlatformOrgPo(PlatformOrgPo platformOrgPo) {
		this.platformOrgPo = platformOrgPo;
	}
	
	public PlatformOrgPo getPlatformOrgPo() {
		return platformOrgPo;
	}
}
