package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupExtraItem;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupSupplierVo;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class ToAddGroupOrderResult extends BaseStateResult {
	private static final long serialVersionUID = -5975932058423223401L;
	private ProductGroupPrice productGroupPrice;
	private ProductGroup group;
	private ProductInfo productInfo;
	private ProductRemark productRemark;
	private List<ProductGroupExtraItem> grouplist;
	private ProductGroupPrice groupPrice;
	private List<DicInfo> jdxjList;
	private List<DicInfo> jtfsList;
	private List<DicInfo> zjlxList;
	private List<DicInfo> sourceTypeList;
	private List<RegionInfo> allProvince;
	private List<DicInfo> lysfxmList;
	private List<ProductGroupSupplierVo> groupSuppliersList;

	public ProductGroupPrice getProductGroupPrice() {
		return productGroupPrice;
	}

	public void setProductGroupPrice(ProductGroupPrice productGroupPrice) {
		this.productGroupPrice = productGroupPrice;
	}

	public ProductGroup getGroup() {
		return group;
	}

	public void setGroup(ProductGroup group) {
		this.group = group;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public ProductRemark getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(ProductRemark productRemark) {
		this.productRemark = productRemark;
	}

	public List<ProductGroupExtraItem> getGrouplist() {
		return grouplist;
	}

	public void setGrouplist(List<ProductGroupExtraItem> grouplist) {
		this.grouplist = grouplist;
	}

	public ProductGroupPrice getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(ProductGroupPrice groupPrice) {
		this.groupPrice = groupPrice;
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

	public List<DicInfo> getLysfxmList() {
		return lysfxmList;
	}

	public void setLysfxmList(List<DicInfo> lysfxmList) {
		this.lysfxmList = lysfxmList;
	}

	public List<ProductGroupSupplierVo> getGroupSuppliersList() {
		return groupSuppliersList;
	}

	public void setGroupSuppliersList(List<ProductGroupSupplierVo> groupSuppliersList) {
		this.groupSuppliersList = groupSuppliersList;
	}
}
