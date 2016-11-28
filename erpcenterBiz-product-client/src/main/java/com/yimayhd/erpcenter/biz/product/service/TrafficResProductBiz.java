package com.yimayhd.erpcenter.biz.product.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;


public interface TrafficResProductBiz {
	
	List<TrafficResProduct> loadTrafficResProductInfo(Integer resId);
	
	int insertTrafficResProduct(TrafficResProduct product);
	
	TrafficResProduct loadTrafficResProduct(Integer id);
	
	int saveTrafficResProduct(TrafficResProduct productBean);
	
	int delTrafficResProduct(Integer id);
	
	int loadByResProductId(String id,String suggest_price_id,String adjust_uprodown_num,String price);
	
	TrafficResProduct selectByResProductId(Integer id);
	
	Integer selectResProductNameCount(Integer productCode,Integer  resId);

	TrafficResProduct selectNumSoldCount(Integer resId);

	int updateResProductNumStock(Integer id,Integer numStock);

}
