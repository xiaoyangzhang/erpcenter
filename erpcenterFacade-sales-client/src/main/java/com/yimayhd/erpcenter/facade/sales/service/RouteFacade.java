package com.yimayhd.erpcenter.facade.sales.service;

import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.facade.sales.result.RouteResult;
import com.yimayhd.erpcenter.facade.sales.result.SaveSeatInCoachResult;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 16:34
 */
public interface RouteFacade {
    public RouteResult stockProduct_list(ProductInfo productInfo,String productName, String name,Integer page,Integer bizId);
    public RouteResult StockProduct_list_table(ProductInfo productInfo,String productName, String name,Integer page,Integer pageSize, Integer bizId);
    public RouteResult getRouteVo(Integer productId);



}
