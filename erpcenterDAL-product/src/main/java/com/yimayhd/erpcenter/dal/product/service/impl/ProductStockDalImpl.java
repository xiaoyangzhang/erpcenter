package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.yimayhd.erpcenter.dal.product.dao.ProductStockMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.service.ProductStockDal;


public class ProductStockDalImpl implements ProductStockDal {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductStockDalImpl.class);

	@Resource
	private ProductStockMapper stockMapper;
	@Autowired
    private TransactionTemplate transactionTemplate;
	
	@Override
	public List<ProductStock> getStocksByProductIdAndDateSpan(Integer productId,
			Date startDate, Date endDate) {
		return stockMapper.getStocksByProductIdAndDateSpan(productId, startDate,endDate);
	}

	
	@Override
	public void saveStock(final Integer productId,final List<ProductStock> stockList,final Date startDate,final Date endDate) {
		Boolean dbResult = transactionTemplate.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try{
					int result = stockMapper.setDeleteByProductIdAndDateSpan(productId,startDate,endDate);
					if (result < 0) {
						return false;
					}
					for(ProductStock stock : stockList){
						if(stock.getId() == null){
							int result2 = stockMapper.deleteByProductIdAndDate(productId, stock.getItemDate());
							if (result2 < 0) {
								return false;
							}
							stock.setState(1);
							stock.setCreateTime(new Date().getTime());
							stock.setReceiveCount(0);
							int result3 = stockMapper.insert(stock);
							if (result3 <= 0) {
								return false;
							}
						}else{
							stock.setState(1);
							int result4 = stockMapper.updateByPrimaryKeySelective(stock);
							if (result4 <= 0) {
								return false;
							}
						}
					}
					return true;
				}catch(Exception e){
					status.setRollbackOnly(); 
					LOGGER.error("saveStock failed!  productId={},stockList={}", JSON.toJSONString(productId),JSON.toJSONString(stockList), e);
					return false;
				}
			}
		});
		if( dbResult == null || !dbResult ){
			LOGGER.error("saveStock failed!  productId={},stockList={}", JSON.toJSONString(productId),JSON.toJSONString(stockList));
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
