package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.dal.product.dao.ProductStockMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.service.ProductStockDal;


public class ProductStockDalImpl implements ProductStockDal {


	@Resource
	private ProductStockMapper stockMapper;
	
	@Override
	public List<ProductStock> getStocksByProductIdAndDateSpan(Integer productId,
			Date startDate, Date endDate) {
		return stockMapper.getStocksByProductIdAndDateSpan(productId, startDate,endDate);
	}

	
	@Transactional
	@Override
	public void saveStock(Integer productId,List<ProductStock> stockList,Date startDate,Date endDate) {
		stockMapper.setDeleteByProductIdAndDateSpan(productId,startDate,endDate);
		for(ProductStock stock : stockList){
			if(stock.getId() == null){
				//ProductStock 	 = getStockByProductIdAndDate(productId,stock.getItemDate());
				stockMapper.deleteByProductIdAndDate(productId, stock.getItemDate());
				stock.setState(1);
				stock.setCreateTime(new Date().getTime());
				stock.setReceiveCount(0);
				stockMapper.insert(stock);
			}else{
				stock.setState(1);
				stockMapper.updateByPrimaryKeySelective(stock);
			}
		}
	}



	@Override
	public ProductStock getStockByProductIdAndDate(Integer productId, Date date) {
		if(productId==null || date == null){
			return null;
		}
		List<ProductStock> list = stockMapper.getStockByProductIdAndDate(productId, date);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateStockCount(Integer productId, Date itemDate, int count) {		
		return stockMapper.updateStockCount(productId, itemDate, count);
	}

	@Override
	public int updateReserveCount(Integer productId, Date itemDate, int count) {
		String type = "P";
		if(count<0){
			count = 0 - count;
			type = "D";
		}		
		return stockMapper.updateReserveCount(productId, itemDate, count,type);
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
		return stockMapper.updateReserveCount(productId, itemDate, count,"Z");
	}


}
