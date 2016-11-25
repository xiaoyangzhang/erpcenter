package com.yimayhd.erpcenter.biz.product.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockDate;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockLog;


public interface ProductStockBiz {
	List<ProductStock> getStocksByProductIdAndDateSpan(Integer productId,Date startDate,Date endDate);
	
	int insertTaobaoStockDateSelective(TaobaoStockDate taobaoStockDate);
    
    int updateTaobaoStockDateSelective(TaobaoStockDate taobaoStockDate);
    
    int insertTaobaoStockLogSelective(TaobaoStockLog taobaoStockLog);
    
    int updateTaobaoStockLogSelective(TaobaoStockLog taobaoStockLog);
    
    int  updateByLog(Integer stockDateId);
    
    List<TaobaoStockDate> selectTaobaoStocksByProductIdAndDate(Integer stockId, Date startDate, Date endDate);
    
    List<TaobaoStockLog>selectTaobaoStockLogByStockId(Integer stockId);
    
    List<TaobaoStockLog>selectByStockDateIdAndCreateTime(Integer stockDateId,String startMax,String startMin);
	
	void saveStock(Integer productId,List<ProductStock> stockList,Date startDate,Date endDate);
	
	ProductStock getStockByProductIdAndDate(Integer productId,Date date);
	
	/**
	 * 根据产品id和日期获取产品库存余量
	 * @param productId
	 * @param date
	 * @return
	 */
	int getRestCountByProductIdAndDate(Integer productId,Date date);
	
	/**
	 * 更新产品收客人数
	 * @param productId 产品id
	 * @param itemDate 日期
	 * @param count 收客人数 >0收客数增加 <0收客数减少
	 * @return
	 */
	int updateStockCount(Integer productId,Date itemDate,int count);

	/**
	 * 更新预留数
	 * @param productId
	 * @param itemDate
	 * @param count 预留数 >0收客数增加 <0收客数减少
	 * @return
	 */
	int updateReserveCount(Integer productId,Date itemDate,int count);
	
	/**
	 * 收客数增加、预留数减少
	 * @param productId 产品id
	 * @param itemDate 日期
	 * @param count 人数：收客人数增加count，预留人数减少count 
	 * @return
	 */
	int stockCntAddAndReserveCntReduce(Integer productId,Date itemDate,int count);
	
	void updateProductStockByTaobao(List<Map<String, String>> mapList);
	
	TaobaoStockDate selectStockDataById(Integer id);

}
