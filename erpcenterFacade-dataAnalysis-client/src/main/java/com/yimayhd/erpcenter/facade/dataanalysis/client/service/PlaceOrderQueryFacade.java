package com.yimayhd.erpcenter.facade.dataanalysis.client.service;

import com.yimayhd.erpcenter.facade.dataanalysis.client.query.PlaceOrderQueryDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.PlaceOrderQueryResult;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/12/1 15:22
 */
public interface PlaceOrderQueryFacade {

    public PlaceOrderQueryResult getSupplierDetails(PlaceOrderQueryDTO placeOrderQueryDTO);

    public PlaceOrderQueryResult sightList(PlaceOrderQueryDTO placeOrderQueryDTO);
}
