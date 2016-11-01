package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.TrafficResLine;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;

public class TrafficAddResOrderResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TrafficResProduct trafficResProduct;
	private SpecialGroupOrderVO  vo;
	private List<DicInfo> jdxjList;
	private List<DicInfo> jtfsList;
	private List<DicInfo> zjlxList;
	private List<DicInfo> sourceTypeList;
	private List<RegionInfo> allProvince;
	private List<DicInfo> lysfxmList;
	private ProductInfo productInfo;
	private List<TrafficResLine> trafficResLine;
	private List<RegionInfo> cityList;
	
	private Integer operType;
	
	public TrafficResProduct getTrafficResProduct() {
		return trafficResProduct;
	}
	public void setTrafficResProduct(TrafficResProduct trafficResProduct) {
		this.trafficResProduct = trafficResProduct;
	}
	public SpecialGroupOrderVO getVo() {
		return vo;
	}
	public void setVo(SpecialGroupOrderVO vo) {
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
	public List<DicInfo> getLysfxmList() {
		return lysfxmList;
	}
	public void setLysfxmList(List<DicInfo> lysfxmList) {
		this.lysfxmList = lysfxmList;
	}
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	public List<TrafficResLine> getTrafficResLine() {
		return trafficResLine;
	}
	public void setTrafficResLine(List<TrafficResLine> trafficResLine) {
		this.trafficResLine = trafficResLine;
	}
	public List<RegionInfo> getCityList() {
		return cityList;
	}
	public void setCityList(List<RegionInfo> cityList) {
		this.cityList = cityList;
	}
	public Integer getOperType() {
		return operType;
	}
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	
}
