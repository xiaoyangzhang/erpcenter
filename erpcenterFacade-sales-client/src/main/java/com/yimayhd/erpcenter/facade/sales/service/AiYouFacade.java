package com.yimayhd.erpcenter.facade.sales.service;

import java.util.Date;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.facade.sales.query.SaveFitOrderInfoDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToImpAiYouOrderDTO;
import com.yimayhd.erpcenter.facade.sales.result.ToImpAiYouOrderResult;


/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther hongfei.guo
 * @Date 2016/10/27 10:58
 */
public interface AiYouFacade {

	List<ProductInfo> searchProductByNameAndDate(Integer bizId, String name, String date);
	
	Integer getRestCountByProductIdAndDate(Integer productId, Date date);
	
	Integer saveFitOrderInfo(SaveFitOrderInfoDTO dto);
	
	ToImpAiYouOrderResult toImpAiYouOrder(ToImpAiYouOrderDTO dto);
	
	DicInfo getDicInfoByTypeCodeAndDicCode(Integer bizId, String typeCode, String dicCode);
	
	DicInfo getDicInfoByTypeCodeAndDicCode(String typeCode, String dicCode);
	
	ProductRemark findProductRemarkByProductId(Integer productId);
    
	ProductInfo findProductInfoById(Integer productId);
    
    List<ProductRoute> findProductRouteByProductId(Integer productId);
}
