package com.yimayhd.erpcenter.dal.product.service;

import com.yimayhd.erpcenter.dal.product.vo.ProductTagVo;

/**
 * Created by ZhengZiyu on 2015/7/6.
 */
public interface ProductTagDal {

    boolean saveProductTags(ProductTagVo productTagVo);

    ProductTagVo findProductTagsByProductId(Integer productId);
}
