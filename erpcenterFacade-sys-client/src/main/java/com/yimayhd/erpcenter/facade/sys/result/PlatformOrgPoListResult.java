package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;

public class PlatformOrgPoListResult extends ResultSupport implements Serializable {

	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private List<PlatformOrgPo> platformOrgPos = new ArrayList<PlatformOrgPo>();
	public void setPlatformOrgPos(List<PlatformOrgPo> platformOrgPos) {
		this.platformOrgPos = platformOrgPos;
	}
	public List<PlatformOrgPo> getPlatformOrgPos() {
		return platformOrgPos;
	}
}
