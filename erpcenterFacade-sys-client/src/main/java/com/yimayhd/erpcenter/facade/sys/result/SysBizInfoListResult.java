package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.SysBizInfo;
/**
 * 
 * 描述：企业信息列表封装结果
 * @author liyong
 * 2016年10月21日
 */
public class SysBizInfoListResult extends ResultSupport implements Serializable {
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SysBizInfo> sysBizInfos = new ArrayList<SysBizInfo>();
	
	public void setSysBizInfos(List<SysBizInfo> sysBizInfos) {
		this.sysBizInfos = sysBizInfos;
	}
	
	public List<SysBizInfo> getSysBizInfos() {
		return sysBizInfos;
	}
}
