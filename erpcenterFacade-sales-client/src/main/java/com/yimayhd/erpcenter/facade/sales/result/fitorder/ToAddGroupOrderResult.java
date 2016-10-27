package com.yimayhd.erpcenter.facade.sales.result.fitorder;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToAddGroupOrderResult extends BaseStateResult {
	private List<DicInfo> sourceTypeList;
	private List<RegionInfo> allProvince;
	private List<DicInfo> lysfxmList;
	private List<DicInfo> dicInfoETList;
	private List<DicInfo> dicInfoCRList;
	private List<DicInfo> jdxjList;
	private List<DicInfo> jtfsList;
	private List<DicInfo> zjlxList;
	private int count;
	private List<ProductGroupSupplier> supplierList;
	private FitOrderVO vo;

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

	public List<DicInfo> getLysfxmList() {
		return lysfxmList;
	}

	public void setLysfxmList(List<DicInfo> lysfxmList) {
		this.lysfxmList = lysfxmList;
	}

	public List<DicInfo> getDicInfoETList() {
		return dicInfoETList;
	}

	public void setDicInfoETList(List<DicInfo> dicInfoETList) {
		this.dicInfoETList = dicInfoETList;
	}

	public List<DicInfo> getDicInfoCRList() {
		return dicInfoCRList;
	}

	public void setDicInfoCRList(List<DicInfo> dicInfoCRList) {
		this.dicInfoCRList = dicInfoCRList;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ProductGroupSupplier> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<ProductGroupSupplier> supplierList) {
		this.supplierList = supplierList;
	}

	public FitOrderVO getVo() {
		return vo;
	}

	public void setVo(FitOrderVO vo) {
		this.vo = vo;
	}
}
