package com.yimayhd.erpcenter.dal.product.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.yimayhd.erpcenter.dal.product.dao.ProductTagMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductTag;
import com.yimayhd.erpcenter.dal.product.service.ProductTagDal;
import com.yimayhd.erpcenter.dal.product.vo.ProductTagVo;

import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/6.
 */
public class ProductTagDalImpl implements ProductTagDal {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductTagDalImpl.class);
	
    @Autowired
    private ProductTagMapper productTagMapper;
    @Autowired
    private TransactionTemplate transactionTemplateProduct;

    @Transactional
    @Override
    public boolean saveProductTags(final ProductTagVo productTagVo) {
    	if(productTagVo == null || productTagVo.getProductId() == null || productTagVo.getProductTags() == null){
    		return false;
    	}
    	Boolean dbResult = transactionTemplateProduct.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try{
					int result = productTagMapper.deleteByProductId(productTagVo.getProductId());
					if (result < 0) {
						return false;
					}
	                for(ProductTag productTag : productTagVo.getProductTags()){
	                    productTag.setProductId(productTagVo.getProductId());
	                    productTag.setCreateTime(System.currentTimeMillis());
	                    productTag.setIsExtra(0);
	                    int addresult = productTagMapper.insert(productTag);
	                    if (addresult <= 0) {
							return false;
						}
	                }
					return true;
				}catch(Exception e){
					status.setRollbackOnly(); 
					LOGGER.error("saveProductTags failed!  ProductTagVo={}", JSON.toJSONString(productTagVo), e);
					return false;
				}
			}
		});
		if( dbResult == null || !dbResult ){
			return false;
		}
		return true;
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
