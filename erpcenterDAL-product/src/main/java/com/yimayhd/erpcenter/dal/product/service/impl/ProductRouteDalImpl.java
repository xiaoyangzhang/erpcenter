package com.yimayhd.erpcenter.dal.product.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/7.
 */
public class ProductRouteDalImpl implements ProductRouteDal{

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
    @Transactional
    @Override
    public boolean saveProductRoute(ProductRouteVo productRouteVo) {
        Integer productId = productRouteVo.getProductId();
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productId);
        List<ProductRouteDayVo> productRoteDayVoList = productRouteVo.getProductRoteDayVoList();
        //备注信息
        ProductRemark productRemark = productRouteVo.getProductRemark();
        productRemark.setProductId(productRouteVo.getProductId());
//        remarkMapper.insertSelective(productRemark);
        remarkService.saveProductRemark(productRemark);
		productInfo.setTravelDays(productRoteDayVoList.size());
        productInfoMapper.updateByPrimaryKeySelective(productInfo);
        for(ProductRouteDayVo productRouteDayVo : productRoteDayVoList){
            //productRoute主表
            ProductRoute productRoute = productRouteDayVo.getProductRoute();
            if(productRoute != null){
                productRoute.setProductId(productId);
                productRoute.setCreateTime(System.currentTimeMillis());
                productRouteMapper.insert(productRoute);
                List<ProductRouteTraffic> productRouteTrafficList = productRouteDayVo.getProductRouteTrafficList();
                if(productRouteTrafficList != null){
                    for(ProductRouteTraffic productRouteTraffic : productRouteTrafficList){
                        //交通
                        productRouteTraffic.setRouteId(productRoute.getId());
                        productRouteTraffic.setCreateTime(System.currentTimeMillis());
                        productRouteTrafficMapper.insert(productRouteTraffic);
                    }
                }

                List<ProductRouteSupplier> productOptionsSupplierList = productRouteDayVo.getProductOptionsSupplierList();
                if(productOptionsSupplierList != null){
                    for(ProductRouteSupplier productRouteSupplier : productOptionsSupplierList){
                        //备选商家
                        productRouteSupplier.setRouteId(productRoute.getId());
                        productRouteSupplier.setCreateTime(System.currentTimeMillis());
                        productRouteSupplierMapper.insert(productRouteSupplier);
                    }
                }

                List<ProductAttachment> productAttachments = productRouteDayVo.getProductAttachments();
                if(productAttachments != null){
                    for(ProductAttachment productAttachment : productAttachments){
                        productAttachment.setObjId(productRoute.getId());
                        productAttachment.setCreateTime(System.currentTimeMillis());
                        productAttachmentMapper.insert(productAttachment);
                    }
                }
            }

        }
        return true;
    }

    @Transactional
    @Override
    public boolean editProductRoute(ProductRouteVo productRouteVo) {
        Integer productId = productRouteVo.getProductId();
        List<ProductRoute> productRouteList = productRouteMapper.selectByProductId(productId);
        //备注信息
        ProductRemark productRemark = productRouteVo.getProductRemark();
        productRemark.setProductId(productRouteVo.getProductId());
//        remarkMapper.updateByPrimaryKeySelective(productRemark);
        remarkService.saveProductRemark(productRemark);
        productRouteMapper.deleteByProductId(productId);
        for(ProductRoute productRoute : productRouteList){
            productRouteTrafficMapper.deleteByRouteId(productRoute.getId());
            productRouteSupplierMapper.deleteByRouteId(productRoute.getId());
            productAttachmentMapper.deleteByobjId(productRoute.getId(), 2);
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
