package com.yimayhd.erpcenter.facade.product.service.impl;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;
import com.yimayhd.erpcenter.facade.query.ProductStockStaticDto;
import com.yimayhd.erpcenter.facade.service.ProductStockFacade;

public class ProductStockFacadeImpl implements ProductStockFacade{
	
	@Autowired
	private ProductInfoBiz productInfoBiz;

	@Override
	public PageBean getStockStaticsListNew(ProductStockStaticDto dto) throws ParseException {
		StockStaticCondition condition  = dto.getCondition();
		
		return productInfoBiz.getStockStaticsList2(condition);
	}

}
