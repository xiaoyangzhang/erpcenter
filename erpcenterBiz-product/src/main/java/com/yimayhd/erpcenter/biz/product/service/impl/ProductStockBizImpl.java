package com.yimayhd.erpcenter.biz.product.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockDate;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockLog;
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
    public int insertTaobaoStockDateSelective(TaobaoStockDate taobaoStockDate){
		productStockDal.insertTaobaoStockDateSelective(taobaoStockDate);
    	return taobaoStockDate.getId();
    }
    
    @Override
    public int updateTaobaoStockDateSelective(TaobaoStockDate taobaoStockDate){
    	productStockDal.updateTaobaoStockDateSelective(taobaoStockDate);
    	return taobaoStockDate.getId();
    }
    
    @Override
    public int insertTaobaoStockLogSelective(TaobaoStockLog taobaoStockLog){
    	productStockDal.insertTaobaoStockLogSelective(taobaoStockLog);
    	return taobaoStockLog.getId();
    }
    
    @Override
    public int updateTaobaoStockLogSelective(TaobaoStockLog taobaoStockLog){
    	productStockDal.updateTaobaoStockLogSelective(taobaoStockLog);
    	return taobaoStockLog.getId();
    }

    @Override
    public int  updateByLog(Integer stockDateId){
    	productStockDal.updateByLog(stockDateId);
    	return 1;
    }
    
    @Override
    public List<TaobaoStockDate> selectTaobaoStocksByProductIdAndDate(Integer stockId, Date startDate,
            Date endDate) {
        return productStockDal.selectTaobaoStocksByProductIdAndDate(stockId, startDate, endDate);
    }
    
    @Override
    public List<TaobaoStockLog>selectTaobaoStockLogByStockId(Integer stockId){
    	List<TaobaoStockLog> list = productStockDal.selectTaobaoStockLogByStockId(stockId);
    	return list;
    }
    
    @Override
    public List<TaobaoStockLog> selectByStockDateIdAndCreateTime(Integer stockDateId,String startMax,String startMin){
    	List<TaobaoStockLog> list = productStockDal.selectByStockDateIdAndCreateTime(stockDateId, startMax, startMin);
    	return list;
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
	
    @Override
    public void updateProductStockByTaobao(List<Map<String, String>> mapList) {
    	productStockDal.updateProductStockByTaobao(mapList);
    }

	@Override
	public TaobaoStockDate selectStockDataById(Integer id) {
		return productStockDal.selectStockDataById(id);
	}

	@Override
	public int updateRemark(TaobaoStockDate taobaoStockDate) {
		return productStockDal.updateRemark(taobaoStockDate);
	}
}
