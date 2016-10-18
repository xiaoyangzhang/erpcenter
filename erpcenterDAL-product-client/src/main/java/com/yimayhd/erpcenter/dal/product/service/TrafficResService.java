package com.yihg.product.api;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.product.po.ProductInfo;
import com.yihg.product.po.TrafficRes;
import com.yihg.product.po.TrafficResLine;
import com.yihg.product.po.TrafficResProduct;
import com.yihg.product.po.TrafficResStocklog;
import com.yihg.product.vo.ProductInfoVo;
import com.yihg.product.vo.TrafficResVo;

public interface TrafficResService {
	
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
