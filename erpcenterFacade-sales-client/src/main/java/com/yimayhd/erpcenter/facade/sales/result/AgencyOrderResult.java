package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupSupplierVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupComment;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;

/**
* @ClassName: AiYouOrderResult
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zhangxiaoyang
* @date 2016年11月1日
*/
public class AgencyOrderResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private ProductInfo productInfo;
	private ProductRemark productRemark;
	private List<ProductGroupSupplierVo> groupSupplierVos;
	private ProductGroup productGroup;
	private int count;
	private FitOrderVO fitOrderVO;
	private List<Map<String, Object>> mapList;
	private GroupOrder groupOrder;
	private List<RegionInfo> regionList;
	private PageBean pageBean;
	private List<String> strList;
	private List<Integer> intList;
	private TourGroup tourGroup;
	private List<TourGroupComment> groupComments;
	private List<GroupRoute> groupRoutes;
	private TeamGroupVO teamGroupVO;
	private String guideStr;
	
	public TeamGroupVO getTeamGroupVO() {
		return teamGroupVO;
	}
	public void setTeamGroupVO(TeamGroupVO teamGroupVO) {
		this.teamGroupVO = teamGroupVO;
	}
	public String getGuideStr() {
		return guideStr;
	}
	public void setGuideStr(String guideStr) {
		this.guideStr = guideStr;
	}
	public List<GroupRoute> getGroupRoutes() {
		return groupRoutes;
	}
	public void setGroupRoutes(List<GroupRoute> groupRoutes) {
		this.groupRoutes = groupRoutes;
	}
	public List<TourGroupComment> getGroupComments() {
		return groupComments;
	}
	public void setGroupComments(List<TourGroupComment> groupComments) {
		this.groupComments = groupComments;
	}
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	public List<String> getStrList() {
		return strList;
	}
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
	public List<Integer> getIntList() {
		return intList;
	}
	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public FitOrderVO getFitOrderVO() {
		return fitOrderVO;
	}
	public void setFitOrderVO(FitOrderVO fitOrderVO) {
		this.fitOrderVO = fitOrderVO;
	}
	public List<Map<String, Object>> getMapList() {
		return mapList;
	}
	public void setMapList(List<Map<String, Object>> mapList) {
		this.mapList = mapList;
	}
	public GroupOrder getGroupOrder() {
		return groupOrder;
	}
	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}
	public List<RegionInfo> getRegionList() {
		return regionList;
	}
	public void setRegionList(List<RegionInfo> regionList) {
		this.regionList = regionList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public List<ProductGroupSupplierVo> getGroupSupplierVos() {
		return groupSupplierVos;
	}
	public void setGroupSupplierVos(List<ProductGroupSupplierVo> groupSupplierVos) {
		this.groupSupplierVos = groupSupplierVos;
	}
	public ProductGroup getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}
	
}
