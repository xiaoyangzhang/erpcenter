package com.yimayhd.erpcenter.dal.product.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.yimayhd.erpcenter.dal.product.dao.ProductAttachmentMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductInfoMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductRemarkMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductRouteMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductRouteSupplierMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductRouteTrafficMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductRouteTraffic;
import com.yimayhd.erpcenter.dal.product.service.ProductRemarkDal;
import com.yimayhd.erpcenter.dal.product.service.ProductRouteDal;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteDayVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductRouteVo;

/**
 * Created by ZhengZiyu on 2015/7/7.
 */
public class ProductRouteDalImpl implements ProductRouteDal{
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRouteDalImpl.class);

    @Autowired
    private ProductRouteMapper productRouteMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductRouteTrafficMapper productRouteTrafficMapper;

    @Autowired
    private ProductRouteSupplierMapper productRouteSupplierMapper;

    @Autowired
    private ProductAttachmentMapper productAttachmentMapper;
    @Autowired
    private ProductRemarkMapper remarkMapper;
    @Autowired
    private ProductRemarkDal remarkService;
    @Autowired
    private TransactionTemplate transactionTemplate;
    
    @Override
    public boolean saveProductRoute(final ProductRouteVo productRouteVo) {
    	final Integer productId = productRouteVo.getProductId();
       
        final List<ProductRouteDayVo> productRoteDayVoList = productRouteVo.getProductRoteDayVoList();
        //备注信息
        final ProductRemark productRemark = productRouteVo.getProductRemark();
        productRemark.setProductId(productRouteVo.getProductId());
        final ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productId);
         
    	Boolean dbResult = transactionTemplate.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try{
					boolean result = remarkService.saveProductRemark(productRemark);
					if(!result){
						status.setRollbackOnly();
						return false;
					}
					productInfo.setTravelDays(productRoteDayVoList.size());
			        int upresult = productInfoMapper.updateByPrimaryKeySelective(productInfo);
					if (upresult <= 0) {
						status.setRollbackOnly();
						return false;
					}
					for(ProductRouteDayVo productRouteDayVo : productRoteDayVoList){
			            //productRoute主表
			            ProductRoute productRoute = productRouteDayVo.getProductRoute();
			            if(productRoute != null){
			                productRoute.setProductId(productId);
			                productRoute.setCreateTime(System.currentTimeMillis());
			                int result1 = productRouteMapper.insert(productRoute);
			                if (result1 <= 0) {
								status.setRollbackOnly();
								return false;
							}
			                List<ProductRouteTraffic> productRouteTrafficList = productRouteDayVo.getProductRouteTrafficList();
			                if(productRouteTrafficList != null){
			                    for(ProductRouteTraffic productRouteTraffic : productRouteTrafficList){
			                        //交通
			                        productRouteTraffic.setRouteId(productRoute.getId());
			                        productRouteTraffic.setCreateTime(System.currentTimeMillis());
			                        int result2 = productRouteTrafficMapper.insert(productRouteTraffic);
			                        if (result2 <= 0) {
										status.setRollbackOnly();
										return false;
									}
			                    }
			                }

			                List<ProductRouteSupplier> productOptionsSupplierList = productRouteDayVo.getProductOptionsSupplierList();
			                if(productOptionsSupplierList != null){
			                    for(ProductRouteSupplier productRouteSupplier : productOptionsSupplierList){
			                        //备选商家
			                        productRouteSupplier.setRouteId(productRoute.getId());
			                        productRouteSupplier.setCreateTime(System.currentTimeMillis());
			                        int result3 = productRouteSupplierMapper.insert(productRouteSupplier);
			                        if (result3 <= 0) {
										status.setRollbackOnly();
										return false;
									}
			                    }
			                }

			                List<ProductAttachment> productAttachments = productRouteDayVo.getProductAttachments();
			                if(productAttachments != null){
			                    for(ProductAttachment productAttachment : productAttachments){
			                        productAttachment.setObjId(productRoute.getId());
			                        productAttachment.setCreateTime(System.currentTimeMillis());
			                        int result4 = productAttachmentMapper.insert(productAttachment);
			                        if (result4 <= 0) {
										status.setRollbackOnly();
										return false;
									}
			                    }
			                }
			            }

			        }
					
					return true;
				}catch(Exception e){
					status.setRollbackOnly(); 
					LOGGER.error("saveProductRoute failed!  ProductRouteVo={}", JSON.toJSONString(productRouteVo), e);
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
    public boolean editProductRoute(final ProductRouteVo productRouteVo) {
    	 final Integer productId = productRouteVo.getProductId();
         final List<ProductRoute> productRouteList = productRouteMapper.selectByProductId(productId);
         //备注信息
         final ProductRemark productRemark = productRouteVo.getProductRemark();
         productRemark.setProductId(productRouteVo.getProductId());
    	
    	Boolean dbResult = transactionTemplate.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try{
					boolean result = remarkService.saveProductRemark(productRemark);
					if (!result) {
						return false;
					}
					int deleteResult = productRouteMapper.deleteByProductId(productId);
					if (deleteResult < 0) {
						return false;
					}
					
					for(ProductRoute productRoute : productRouteList){
			            int result2 = productRouteTrafficMapper.deleteByRouteId(productRoute.getId());
			            if (result2 < 0) {
							return false;
						}
			            int result3 = productRouteSupplierMapper.deleteByRouteId(productRoute.getId());
			            if (result3 < 0) {
							return false;
						}
			            int result4 = productAttachmentMapper.deleteByobjId(productRoute.getId(), 2);
			            if (result4 < 0) {
							return false;
						}
			        }
					
					return true;
				}catch(Exception e){
					status.setRollbackOnly(); 
					LOGGER.error("editProductRoute failed!  ProductRouteVo={}", JSON.toJSONString(productRouteVo), e);
					return false;
				}
			}
		});
		if( dbResult == null || !dbResult ){
			return false;
		}
        
        return saveProductRoute(productRouteVo);
    }

    @Override
    public ProductRouteVo findByProductId(Integer productId) {
        ProductRouteVo productRouteVo = new ProductRouteVo();
        List<ProductRoute> productRouteList = productRouteMapper.selectByProductId(productId);
        List<ProductRouteDayVo> productRouteDayVoList = new ArrayList<ProductRouteDayVo>();
        for(ProductRoute productRoute : productRouteList){
            ProductRouteDayVo productRouteDayVo = new ProductRouteDayVo();
            List<ProductRouteSupplier> productRouteSupplierList = productRouteSupplierMapper.selectByRouteId(productRoute.getId());
            List<ProductRouteTraffic> productRouteTrafficList = productRouteTrafficMapper.selectByRouteId(productRoute.getId());
            List<ProductAttachment> productAttachments = productAttachmentMapper.selectByAttList(productRoute.getId(), 2);
            productRouteDayVo.setProductRoute(productRoute);
            productRouteDayVo.setProductRouteTrafficList(productRouteTrafficList);
            productRouteDayVo.setProductAttachments(productAttachments);
            List<ProductRouteSupplier> productOptionsSupplierList = new ArrayList<ProductRouteSupplier>();
            for(ProductRouteSupplier productRouteSupplier : productRouteSupplierList){
                productOptionsSupplierList.add(productRouteSupplier);
            }
            productRouteDayVo.setProductOptionsSupplierList(productOptionsSupplierList);

            productRouteDayVoList.add(productRouteDayVo);
        }
        productRouteVo.setProductRoteDayVoList(productRouteDayVoList);
        return productRouteVo;
    }

    @Override
    public List<ProductRoute> findProductRouteByProductId(Integer productId) {
        return productRouteMapper.selectByProductId(productId);
    }
    
    @Override
    public  int saveProductRoute1(ProductRoute productRoute){
        return productRouteMapper.insertSelective(productRoute);
    }
}
