package com.yimayhd.erpcenter.facade.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.facade.query.ProductStockStaticDto;
import com.yimayhd.erpcenter.facade.result.ProductInfoResult;

/**
 * 描述：产品库存对应的facade
 * @author liyong
 * 2016年10月18日
 */
public interface ProductStockFacade {
	/**
	 * 描述：新版产品库存统计
	 * @author liyong
	 * 2016年10月18日 
	 * @param condition
	 * @return PageBean
	 */
	PageBean getStockStaticsListNew(ProductStockStaticDto condition) throws ParseException;
	List<ProductStock> getStocksByProductIdAndDateSpan(Integer productId,Date startDate,Date endDate);
	PageBean findProductSalesPlus(PageBean pageBean,Integer bizId,Integer orgId);
	ProductInfoResult productGroupPriceDate(Integer productId,Integer userId);
	ProductInfoResult priceData(Integer productId,Integer groupId,Date startDate,Date endDate);
}
