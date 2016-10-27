package com.yimayhd.erpcenter.facade.sales.result.fitorder;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToFitOrderListResult extends BaseStateResult{
	private static final long serialVersionUID = -8074501494817998129L;
	private List<DicInfo> pp;
	private List<RegionInfo> allProvince;
	private List<DicInfo> sourceTypeList;
	private String orgJsonStr;
	private String orgUserJsonStr;
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
	public List<DicInfo> getSourceTypeList() {
		return sourceTypeList;
	}
	public void setSourceTypeList(List<DicInfo> sourceTypeList) {
		this.sourceTypeList = sourceTypeList;
	}
	public String getOrgJsonStr() {
		return orgJsonStr;
	}
	public void setOrgJsonStr(String orgJsonStr) {
		this.orgJsonStr = orgJsonStr;
	}
	public String getOrgUserJsonStr() {
		return orgUserJsonStr;
	}
	public void setOrgUserJsonStr(String orgUserJsonStr) {
		this.orgUserJsonStr = orgUserJsonStr;
	}
}
