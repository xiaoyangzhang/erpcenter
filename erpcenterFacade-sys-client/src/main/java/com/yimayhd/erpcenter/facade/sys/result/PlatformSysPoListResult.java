package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformSysPo;
/**
 * 
 * 描述：List<PlatformSysPo> 结果封装
 * @author liyong
 * 2016年10月21日
 */
public class PlatformSysPoListResult extends ResultSupport implements Serializable {

	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private List<PlatformSysPo> platformSysPos = new ArrayList<PlatformSysPo>();

	public void setPlatformSysPos(List<PlatformSysPo> platformSysPos) {
		this.platformSysPos = platformSysPos;
	}
	
	public List<PlatformSysPo> getPlatformSysPos() {
		return platformSysPos;
	}
}
