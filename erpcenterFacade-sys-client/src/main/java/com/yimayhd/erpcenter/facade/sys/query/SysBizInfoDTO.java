package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.SysBizInfo;
/**
 * 
 * 描述：企业信心参数封装
 * @author liyong
 * 2016年10月21日
 */
public class SysBizInfoDTO implements Serializable{

	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysBizInfo sysBizInfo = new SysBizInfo();

	public SysBizInfo getSysBizInfo() {
		return sysBizInfo;
	}

	public void setSysBizInfo(SysBizInfo sysBizInfo) {
		this.sysBizInfo = sysBizInfo;
	}
	
	

}
