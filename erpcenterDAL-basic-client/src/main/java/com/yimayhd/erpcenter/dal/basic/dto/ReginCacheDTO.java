package com.yimayhd.erpcenter.dal.basic.dto;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

public class ReginCacheDTO implements Serializable{

	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月20日 
	 */
	private static final long serialVersionUID = -3852824862959629368L;
	
	private List<RegionInfo> regionList;

	public List<RegionInfo> getRegionList() {
		return regionList;
	}

	public void setRegionList(List<RegionInfo> regionList) {
		this.regionList = regionList;
	}
	
	

}
