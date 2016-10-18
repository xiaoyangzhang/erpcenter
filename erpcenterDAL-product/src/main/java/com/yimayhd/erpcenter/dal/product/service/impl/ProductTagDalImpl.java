package com.yimayhd.erpcenter.dal.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.dal.product.dao.ProductTagMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductTag;
import com.yimayhd.erpcenter.dal.product.service.ProductTagDal;
import com.yimayhd.erpcenter.dal.product.vo.ProductTagVo;

import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/6.
 */
public class ProductTagDalImpl implements ProductTagDal {

    @Autowired
    private ProductTagMapper productTagMapper;

    @Transactional
    @Override
    public boolean saveProductTags(ProductTagVo productTagVo) {
        if(productTagVo != null && productTagVo.getProductId() != null && productTagVo.getProductTags() != null){
            try {
                productTagMapper.deleteByProductId(productTagVo.getProductId());

                for(ProductTag productTag : productTagVo.getProductTags()){
                    productTag.setProductId(productTagVo.getProductId());
                    productTag.setCreateTime(System.currentTimeMillis());
                    productTag.setIsExtra(0);
                    productTagMapper.insert(productTag);
                }
            } catch (Exception e) {
                return false;
            }
            return true;

        }else{
            return false;
        }
    }

    @Override
    public ProductTagVo findProductTagsByProductId(Integer productId) {
        List<ProductTag> productTagList = productTagMapper.selectByProductId(productId);
        ProductTagVo productTagVo = new ProductTagVo();
        productTagVo.setProductId(productId);
        productTagVo.setProductTags(productTagList);
        return productTagVo;
    }
}
