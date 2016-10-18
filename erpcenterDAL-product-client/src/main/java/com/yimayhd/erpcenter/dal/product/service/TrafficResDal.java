package com.yimayhd.erpcenter.dal.product.service;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.TrafficRes;
import com.yimayhd.erpcenter.dal.product.po.TrafficResLine;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.dal.product.po.TrafficResStocklog;
import com.yimayhd.erpcenter.dal.product.vo.TrafficResVo;

public interface TrafficResDal {
	
	int saveTrafficRes(TrafficResVo trafficResVo);
	
	TrafficRes findTrafficResById(Integer id); 

	PageBean<TrafficRes> selectTrafficResListPage(PageBean<TrafficRes> pageBean);
	
	 List<TrafficResLine> selectTrafficResLine(Integer resId); 
	 
	 List<TrafficRes> selectResDetails(Integer id); 
	 
	 int copyRes(Integer resId,String date);
	 
	 int insertTrafficResStocklog(TrafficResStocklog record);
	 
	 int updateStockOrStockDisable(Integer id);
	 
	 TrafficResProduct selectTrafficProductInfo(Integer id);
	 
	 int updateNumSoldById (TrafficResProduct record);
	 
	 TrafficResProduct selectTrafficProductInfoByProductCode(Integer productCode,Integer  resId);
	 
	 PageBean<TrafficResProduct> selectResProductListPage(PageBean<TrafficResProduct> pageBean);
	 
	 int updateTrafficResState(Integer id,Integer state);
	 
	  List<TrafficRes> selectDetailsStocklog(Integer id,String adjustTime); 
}
