/**
 * 
 */
package com.yimayhd.erpcenter.facade.result;

import java.io.Serializable;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.PriceView;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSeller;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;

/**
 * @ClassName: ProductInfoResult
 * @Description: 产品列表/预览
 * @author zhangxiaoyang
 * @date 2016年10月19日
 */
public class ProductInfoResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 6636674666861206380L;
	
	private ProductInfoVo productInfoVo;
	
	private ProductRouteVo productRouteVo;
	
	private ProductRemark productRemark;
	private PageBean pageBean;
	private List<ProductGroupSeller> groupSellers;
	private List<ProductRoute> productRoutes;
	private List<ProductGroup> productGroups;
	private ProductInfo productInfo;
	private List<ProductStock> productStocks;
	private List<PriceView> priceViews;
	
	public List<ProductStock> getProductStocks() {
		return productStocks;
	}

	public void setProductStocks(List<ProductStock> productStocks) {
		this.productStocks = productStocks;
	}

	public List<PriceView> getPriceViews() {
		return priceViews;
	}

	public void setPriceViews(List<PriceView> priceViews) {
		this.priceViews = priceViews;
	}

	public List<ProductGroup> getProductGroups() {
		return productGroups;
	}

	public void setProductGroups(List<ProductGroup> productGroups) {
		this.productGroups = productGroups;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public List<ProductRoute> getProductRoutes() {
		return productRoutes;
	}

	public void setProductRoutes(List<ProductRoute> productRoutes) {
		this.productRoutes = productRoutes;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<ProductGroupSeller> getGroupSellers() {
		return groupSellers;
	}

	public void setGroupSellers(List<ProductGroupSeller> groupSellers) {
		this.groupSellers = groupSellers;
	}

	public ProductInfoVo getProductInfoVo() {
		return productInfoVo;
	}

	public void setProductInfoVo(ProductInfoVo productInfoVo) {
		this.productInfoVo = productInfoVo;
	}

	public ProductRouteVo getProductRouteVo() {
		return productRouteVo;
	}

	public void setProductRouteVo(ProductRouteVo productRouteVo) {
		this.productRouteVo = productRouteVo;
	}

	public ProductRemark getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(ProductRemark productRemark) {
		this.productRemark = productRemark;
	}
	
	
}
