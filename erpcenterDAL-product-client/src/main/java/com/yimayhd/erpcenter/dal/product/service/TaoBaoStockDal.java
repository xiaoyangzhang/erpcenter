package com.yimayhd.erpcenter.dal.product.service;


import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.TaobaoProduct;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStock;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockLog;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockProduct;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : zhangsq
 * @Description: 淘宝库存信息接口
 */
public interface TaoBaoStockDal {
	
	void updateProductStockByTaobao(List<Map<String, String>> mapList);
	/**
	 * 查询淘宝库存信息
	 * @param pageBean
	 * @return
	 */
	PageBean<TaobaoStock> selectByStockListPage(PageBean<TaobaoStock> pageBean);
	
	/**
	 * 根基ID进行查询
	 * @param id
	 * @return
	 */
	TaobaoStock selectByPrimaryKey(Integer id);
	
	/**
	 * 保存修改后的要淘宝库存信息
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(TaobaoStock record);
	
	/**
	 * 查询已关联的库存产品
	 * @param stockId
	 * @return
	 */
	List<TaobaoStockProduct> findStockProductStockId(Integer stockId);
	
	List<TaobaoProduct> findTaobaoProductById(Set<Integer> set);
	
	int insertTaobaoProduct(TaobaoProduct taobaoProduct);
	
	int insertTbStockProduct(TaobaoStockProduct record);
	
	PageBean<TaobaoProduct> findTaoBaoProductListPage(PageBean<TaobaoProduct> pageBean,Integer bizId);
	
	PageBean<TaobaoProduct> selectTPBytpdIdListPage(PageBean<TaobaoProduct> pageBean,Integer bizId);
	 TaobaoProduct findByPrimaryKey(Integer id);
	 
	 int deleteTaoBaoStockProduct(TaobaoStockProduct taobaoStockProduct);
	 
	 int deleteByPrimaryKey(Integer id);
	 
	 int insertSelective(TaobaoStock record);
	 
	 TaobaoStockProduct findStockProductInfo(TaobaoStockProduct record);
	 
	 TaobaoStockLog selectLogNumByOrderId(Integer stockId);
	 TaobaoStockLog selectLogByStockDateIdAndOrderId(Integer stockDateId, Integer orderId);
	 
	 PageBean findTaoBaoProductStockListPage(PageBean pageBean);

	int delTaoBaoProductStock(TaobaoStockLog stockLog);

	TaobaoStockLog selectStockLogAllByOrderId(Integer orderId);

	TaobaoStockLog selectStockLogAllByTaobaoOrderId(Integer taobaoOrderId);
	
	 TaobaoProductSkus selectByVid(String vid,String numIid);
	 
	 int updateTaobaoProductSkus(TaobaoProductSkus record);
	 
	 int insertTaobaoProductSkus(TaobaoProductSkus record);
	 
	 int updateTaobaoProduct(TaobaoProduct record);
	 
	 TaobaoProductSkus selectSkusById(Integer id);

}
