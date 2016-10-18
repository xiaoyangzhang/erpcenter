package com.yihg.product.impl;

import com.yihg.product.api.ProductTagService;
import com.yihg.product.dao.ProductTagMapper;
import com.yihg.product.po.ProductTag;
import com.yihg.product.vo.ProductTagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/6.
 */
public class ProductTagServiceImpl implements ProductTagService {

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
