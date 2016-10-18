package com.yimayhd.erpcenter.biz.product.service.impl;

import com.yihg.product.api.ProductRemarkService;
import com.yihg.product.api.ProductRouteService;
import com.yihg.product.dao.ProductAttachmentMapper;
import com.yihg.product.dao.ProductInfoMapper;
import com.yihg.product.dao.ProductRemarkMapper;
import com.yihg.product.dao.ProductRouteMapper;
import com.yihg.product.dao.ProductRouteSupplierMapper;
import com.yihg.product.dao.ProductRouteTrafficMapper;
import com.yihg.product.po.ProductAttachment;
import com.yihg.product.po.ProductInfo;
import com.yihg.product.po.ProductRemark;
import com.yihg.product.po.ProductRoute;
import com.yihg.product.po.ProductRouteSupplier;
import com.yihg.product.po.ProductRouteTraffic;
import com.yihg.product.vo.ProductRouteDayVo;
import com.yihg.product.vo.ProductRouteVo;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.dal.product.service.ProductRouteDal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhengZiyu on 2015/7/7.
 */
public class ProductRouteBizImpl implements ProductRouteBiz{

    @Autowired
    private ProductRouteDal productRouteDal;
    
    
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
