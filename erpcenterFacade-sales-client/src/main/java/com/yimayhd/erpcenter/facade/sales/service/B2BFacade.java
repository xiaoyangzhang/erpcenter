package com.yimayhd.erpcenter.facade.sales.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;


/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther hongfei.guo
 * @Date 2016/10/27 10:58
 */
public interface B2BFacade {

	List<GroupOrder> findGroupOrder(Integer bizId, String dateStart, String dateEnd, String clientName, Integer exports);
	
	boolean updateB2bExportState(String ids);
	
	JSONArray findGroupOrderDetailByIdsToB2B(String ids);
	
	List<ProductInfo> findProductInfoDetailByProductIdToB2B(String productIds);
	
	JSONArray findSupplierInfoListToB2B(Integer bizId, Integer supplierType, String supplierName);
}
