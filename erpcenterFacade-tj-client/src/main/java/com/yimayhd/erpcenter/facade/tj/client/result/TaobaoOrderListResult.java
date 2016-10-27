package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;

public class TaobaoOrderListResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<DicInfo> pp;
	
	private List<RegionInfo> allProvince;
	
	private List<DicInfo> typeList;
	
	private List<DicInfo> sourceTypeList;
	
	private String orgTreeJsonStr;
	
	private String orgUserTreeJsonStr;

	public List<DicInfo> getPp() {
		return pp;
	}

	public void setPp(List<DicInfo> pp) {
		this.pp = pp;
	}

	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}

	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}

	public List<DicInfo> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<DicInfo> typeList) {
		this.typeList = typeList;
	}

	public List<DicInfo> getSourceTypeList() {
		return sourceTypeList;
	}

	public void setSourceTypeList(List<DicInfo> sourceTypeList) {
		this.sourceTypeList = sourceTypeList;
	}

	public String getOrgTreeJsonStr() {
		return orgTreeJsonStr;
	}

	public void setOrgTreeJsonStr(String orgTreeJsonStr) {
		this.orgTreeJsonStr = orgTreeJsonStr;
	}

	public String getOrgUserTreeJsonStr() {
		return orgUserTreeJsonStr;
	}

	public void setOrgUserTreeJsonStr(String orgUserTreeJsonStr) {
		this.orgUserTreeJsonStr = orgUserTreeJsonStr;
	}
	
}
