package com.yimayhd.erpcenter.biz.product.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.service.ProductStockDal;

public class ProductStockBizImpl implements ProductStockBiz {

	@Resource
	private ProductStockDal productStockDal;
	
	@Override
	public List<ProductStock> getStocksByProductIdAndDateSpan(Integer productId,
			Date startDate, Date endDate) {
		return productStockDal.getStocksByProductIdAndDateSpan(productId, startDate,endDate);
	}
	
	@Override
	public void saveStock(Integer productId,List<ProductStock> stockList,Date startDate,Date endDate) {
		productStockDal.saveStock(productId, stockList, startDate, endDate);
	}

	@Override
	public ProductStock getStockByProductIdAndDate(Integer productId, Date date) {
		return productStockDal.getStockByProductIdAndDate(productId, date);
	}

	@Override
	public int updateStockCount(Integer productId, Date itemDate, int count) {		
		return productStockDal.updateStockCount(productId, itemDate, count);
	}

	@Override
	public int updateReserveCount(Integer productId, Date itemDate, int count) {
		return productStockDal.updateReserveCount(productId, itemDate, count);
	}

	@Override
	public int getRestCountByProductIdAndDate(Integer productId, Date date) {
		ProductStock stock = getStockByProductIdAndDate(productId,date);
		if(stock==null){
			return 0;
		}
		return stock.getStockCount()-stock.getReceiveCount();
	}

	@Override
	public int stockCntAddAndReserveCntReduce(Integer productId, Date itemDate,int count) {
		return productStockDal.stockCntAddAndReserveCntReduce(productId, itemDate, count);
	}
}
