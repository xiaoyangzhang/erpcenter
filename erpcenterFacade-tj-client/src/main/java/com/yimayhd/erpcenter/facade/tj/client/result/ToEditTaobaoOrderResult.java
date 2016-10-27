package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;

public class ToEditTaobaoOrderResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer operType;
	private SpecialGroupOrderVO specialGroupOrderVO;
	private List<DicInfo> jdxjList;
	private List<DicInfo> zjlxList;
	private List<DicInfo> lysfxmList;
	private List<DicInfo> jtfsList;
	private List<DicInfo> typeList;
	private List<RegionInfo> allProvince;
	private int count;
	private List<RegionInfo> cityList;
	private String guideStr;
	private List<PlatTaobaoTrade> orders;
	private String tbOrderIds;
	
	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public SpecialGroupOrderVO getSpecialGroupOrderVO() {
		return specialGroupOrderVO;
	}

	public void setSpecialGroupOrderVO(SpecialGroupOrderVO specialGroupOrderVO) {
		this.specialGroupOrderVO = specialGroupOrderVO;
	}

	public List<DicInfo> getJdxjList() {
		return jdxjList;
	}

	public void setJdxjList(List<DicInfo> jdxjList) {
		this.jdxjList = jdxjList;
	}

	public List<DicInfo> getZjlxList() {
		return zjlxList;
	}

	public void setZjlxList(List<DicInfo> zjlxList) {
		this.zjlxList = zjlxList;
	}

	public List<DicInfo> getLysfxmList() {
		return lysfxmList;
	}

	public void setLysfxmList(List<DicInfo> lysfxmList) {
		this.lysfxmList = lysfxmList;
	}

	public List<DicInfo> getJtfsList() {
		return jtfsList;
	}

	public void setJtfsList(List<DicInfo> jtfsList) {
		this.jtfsList = jtfsList;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<RegionInfo> getCityList() {
		return cityList;
	}

	public void setCityList(List<RegionInfo> cityList) {
		this.cityList = cityList;
	}

	public String getGuideStr() {
		return guideStr;
	}

	public void setGuideStr(String guideStr) {
		this.guideStr = guideStr;
	}

	public List<PlatTaobaoTrade> getOrders() {
		return orders;
	}

	public void setOrders(List<PlatTaobaoTrade> orders) {
		this.orders = orders;
	}

	public String getTbOrderIds() {
		return tbOrderIds;
	}

	public void setTbOrderIds(String tbOrderIds) {
		this.tbOrderIds = tbOrderIds;
	}

}
