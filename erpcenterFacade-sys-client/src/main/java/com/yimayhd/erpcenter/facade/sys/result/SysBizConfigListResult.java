package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.SysBizConfig;
/**
 * 
 * 描述：SysbizConfiglist返回结果封装
 * @author liyong
 * 2016年10月21日
 */
public class SysBizConfigListResult extends ResultSupport implements Serializable {
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private List<SysBizConfig> sysBizConfigs = new ArrayList<SysBizConfig>();

	public List<SysBizConfig> getSysBizConfigs() {
		return sysBizConfigs;
	}

	public void setSysBizConfigs(List<SysBizConfig> sysBizConfigs) {
		this.sysBizConfigs = sysBizConfigs;
	}
	
	
}
