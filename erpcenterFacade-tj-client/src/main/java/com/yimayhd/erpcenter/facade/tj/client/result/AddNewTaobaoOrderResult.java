package com.yimayhd.erpcenter.facade.tj.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

public class AddNewTaobaoOrderResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<DicInfo> jdxjList;
	private List<DicInfo> jtfsList;
	private List<DicInfo> zjlxList;
	private List<DicInfo> typeList;
	private List<RegionInfo> allProvince;
	private List<DicInfo> lysfxmList;
	public List<DicInfo> getJdxjList() {
		return jdxjList;
	}
	public void setJdxjList(List<DicInfo> jdxjList) {
		this.jdxjList = jdxjList;
	}
	public List<DicInfo> getJtfsList() {
		return jtfsList;
	}
	public void setJtfsList(List<DicInfo> jtfsList) {
		this.jtfsList = jtfsList;
	}
	public List<DicInfo> getZjlxList() {
		return zjlxList;
	}
	public void setZjlxList(List<DicInfo> zjlxList) {
		this.zjlxList = zjlxList;
	}
	public List<DicInfo> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<DicInfo> typeList) {
		this.typeList = typeList;
	}
	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}
	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}
	public List<DicInfo> getLysfxmList() {
		return lysfxmList;
	}
	public void setLysfxmList(List<DicInfo> lysfxmList) {
		this.lysfxmList = lysfxmList;
	}

}
