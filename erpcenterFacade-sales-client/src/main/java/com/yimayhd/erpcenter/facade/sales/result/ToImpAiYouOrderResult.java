package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;

public class ToImpAiYouOrderResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int count; // 库存
	private int bizId;
	private List<DicInfo> zjlxList;
	private FitOrderVO fitOrderVO;
	private List<DicInfo> jdxjList;
	private List<DicInfo> sourceTypeList;
	private List<DicInfo> jtfsList;
	private List<RegionInfo> allProvince;
	private List<DicInfo> lysfxmList;
	private List<ProductGroupSupplier> supplierList;
	
	
	public List<ProductGroupSupplier> getSupplierList() {
		return supplierList;
	}
	public void setSupplierList(List<ProductGroupSupplier> supplierList) {
		this.supplierList = supplierList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public List<DicInfo> getZjlxList() {
		return zjlxList;
	}
	public void setZjlxList(List<DicInfo> zjlxList) {
		this.zjlxList = zjlxList;
	}
	public FitOrderVO getFitOrderVO() {
		return fitOrderVO;
	}
	public void setFitOrderVO(FitOrderVO fitOrderVO) {
		this.fitOrderVO = fitOrderVO;
	}
	public List<DicInfo> getJdxjList() {
		return jdxjList;
	}
	public void setJdxjList(List<DicInfo> jdxjList) {
		this.jdxjList = jdxjList;
	}
	public List<DicInfo> getSourceTypeList() {
		return sourceTypeList;
	}
	public void setSourceTypeList(List<DicInfo> sourceTypeList) {
		this.sourceTypeList = sourceTypeList;
	}
	public List<DicInfo> getJtfsList() {
		return jtfsList;
	}
	public void setJtfsList(List<DicInfo> jtfsList) {
		this.jtfsList = jtfsList;
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
