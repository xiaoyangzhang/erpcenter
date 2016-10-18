package com.yimayhd.erpcenter.biz.product.service;

import com.yimayhd.erpcenter.dal.product.vo.ProductTagVo;


/**
 * Created by ZhengZiyu on 2015/7/6.
 */
public interface ProductTagBiz {

    boolean saveProductTags(ProductTagVo productTagVo);

    ProductTagVo findProductTagsByProductId(Integer productId);
}
