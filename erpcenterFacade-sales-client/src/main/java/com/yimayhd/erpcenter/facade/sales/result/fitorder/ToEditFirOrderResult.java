package com.yimayhd.erpcenter.facade.sales.result.fitorder;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToEditFirOrderResult extends BaseStateResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4418324040618971250L;
	private FitOrderVO vo;
	private List<DicInfo> jdxjList;
	private List<DicInfo> jtfsList;
	private List<DicInfo> zjlxList;
	private List<DicInfo> sourceTypeList;
	private List<RegionInfo> allProvince;
	private List<RegionInfo> cityList;
	private List<DicInfo> lysfxmList;
	private Integer count;

	public FitOrderVO getVo() {
		return vo;
	}

	public void setVo(FitOrderVO vo) {
		this.vo = vo;
	}

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

	public List<DicInfo> getSourceTypeList() {
		return sourceTypeList;
	}

	public void setSourceTypeList(List<DicInfo> sourceTypeList) {
		this.sourceTypeList = sourceTypeList;
	}

	public List<RegionInfo> getAllProvince() {
		return allProvince;
	}

	public void setAllProvince(List<RegionInfo> allProvince) {
		this.allProvince = allProvince;
	}

	public List<RegionInfo> getCityList() {
		return cityList;
	}

	public void setCityList(List<RegionInfo> cityList) {
		this.cityList = cityList;
	}

	public List<DicInfo> getLysfxmList() {
		return lysfxmList;
	}

	public void setLysfxmList(List<DicInfo> lysfxmList) {
		this.lysfxmList = lysfxmList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
