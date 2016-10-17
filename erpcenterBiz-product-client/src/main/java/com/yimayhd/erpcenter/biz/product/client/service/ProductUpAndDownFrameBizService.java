package com.yimayhd.erpcenter.biz.product.client.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/17.
 */
public interface ProductUpAndDownFrameBizService {
    PageBean<ProductInfo> findProductInfos(PageBean<ProductInfo> var1, Map var2);
}
