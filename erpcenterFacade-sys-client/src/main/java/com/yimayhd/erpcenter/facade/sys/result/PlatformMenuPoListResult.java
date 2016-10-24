package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformMenuPo;
/**
 * 
 * 描述：菜单list对象封装
 * @author liyong
 * 2016年10月21日
 */
public class PlatformMenuPoListResult extends ResultSupport implements Serializable {
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private List<PlatformMenuPo> platformMenuPos = new ArrayList<PlatformMenuPo>();
	
	public void setPlatformMenuPos(List<PlatformMenuPo> platformMenuPos) {
		this.platformMenuPos = platformMenuPos;
	}
	
	public List<PlatformMenuPo> getPlatformMenuPos() {
		return platformMenuPos;
	}

}
