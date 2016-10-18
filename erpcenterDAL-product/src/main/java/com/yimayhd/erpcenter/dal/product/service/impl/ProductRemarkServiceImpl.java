package com.yihg.product.impl;

import com.yihg.product.api.ProductRemarkService;
import com.yihg.product.dao.ProductRemarkMapper;
import com.yihg.product.po.ProductRemark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZhengZiyu on 2015/7/6.
 */
public class ProductRemarkServiceImpl implements ProductRemarkService{

    @Autowired
    private ProductRemarkMapper productRemarkMapper;

    @Transactional
    @Override
    public boolean saveProductRemark(ProductRemark productRemark) {
        boolean success;
        try {
        	//保存前判断是否存在，如果存在则修改即可
        	//获取备注简短信息，只要id和productId
        	ProductRemark remark = productRemarkMapper.selectBriefByProductId(productRemark.getProductId());
        	if(remark!=null){
        		productRemark.setId(remark.getId());
        	}
            if(productRemark.getId() != null){
                productRemarkMapper.updateByPrimaryKeySelective(productRemark);
            }else{
                productRemark.setCreateTime(System.currentTimeMillis());
                productRemarkMapper.insert(productRemark);
            }
            success = true;
        } catch (Exception e) {
            success = false;
        }
        return success;
    }

    @Override
    public ProductRemark findProductRemarkByProductId(Integer productId) {
        return productRemarkMapper.selectByProductId(productId);
    }
}
