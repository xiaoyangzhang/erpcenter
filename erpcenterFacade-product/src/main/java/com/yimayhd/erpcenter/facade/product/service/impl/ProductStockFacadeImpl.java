package com.yimayhd.erpcenter.facade.product.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.dal.product.po.PriceView;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;
import com.yimayhd.erpcenter.facade.query.ProductStockStaticDto;
import com.yimayhd.erpcenter.facade.result.ProductInfoResult;
import com.yimayhd.erpcenter.facade.service.ProductStockFacade;

public class ProductStockFacadeImpl implements ProductStockFacade{
	
	@Autowired
	private ProductInfoBiz productInfoBiz;
	@Autowired
	private ProductStockBiz productStockBiz;
	@Autowired
	private ProductGroupBiz productGroupBiz;
	@Override
	public PageBean getStockStaticsListNew(ProductStockStaticDto dto) throws ParseException {
		StockStaticCondition condition  = dto.getCondition();
		
		return productInfoBiz.getStockStaticsList2(condition);
	}

	@Override
	public List<ProductStock> getStocksByProductIdAndDateSpan(
			Integer productId, Date startDate, Date endDate) {
		List<ProductStock> stocks = productStockBiz.getStocksByProductIdAndDateSpan(productId, startDate, endDate);
		return stocks;
	}

	@Override
	public PageBean findProductSalesPlus(PageBean pageBean, Integer bizId,
			Integer orgId) {
		pageBean = productInfoBiz.findProductSalesPlus(pageBean, bizId, orgId);
		return pageBean;
	}

	@Override
	public ProductInfoResult productGroupPriceDate(Integer productId,
			Integer userId) {
		ProductInfoResult result = new ProductInfoResult();
		List<ProductGroup> productGroups = productGroupBiz.selectProductGroupsBySellerId(productId,userId);
    	result.setProductGroups(productGroups);
		ProductInfo  info = productInfoBiz.findProductInfoById(productId);
    	result.setProductInfo(info);
		return result;
	}

	@Override
	public ProductInfoResult priceData(Integer productId, Integer groupId,
			Date startDate, Date endDate) {
		ProductInfoResult result = new ProductInfoResult();
		List<PriceView> priceViews = productInfoBiz.getPriceViewsByDate(groupId, startDate, endDate);        
		result.setPriceViews(priceViews);
		List<ProductStock> stockList = productStockBiz.getStocksByProductIdAndDateSpan(productId, startDate, endDate);
        result.setProductStocks(stockList);
		return result;
	}

}
