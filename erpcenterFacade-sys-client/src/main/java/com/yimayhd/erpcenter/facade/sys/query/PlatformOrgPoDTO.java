package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
/**
 * 
 * 描述：单位信息参数分装
 * @author liyong
 * 2016年10月21日
 */
public class PlatformOrgPoDTO implements Serializable{

	/**
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 5974424082152546331L;
	private PlatformOrgPo platformOrgPo = new PlatformOrgPo();
	
	public void setPlatformOrgPo(PlatformOrgPo platformOrgPo) {
		this.platformOrgPo = platformOrgPo;
	}
	
	public PlatformOrgPo getPlatformOrgPo() {
		return platformOrgPo;
	}
}
