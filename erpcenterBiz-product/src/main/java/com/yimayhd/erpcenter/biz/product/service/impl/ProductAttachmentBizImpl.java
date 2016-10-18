package com.yimayhd.erpcenter.biz.product.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.product.service.ProductAttachmentBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;
import com.yimayhd.erpcenter.dal.product.service.ProductAttachmentDal;

import java.util.List;

public class ProductAttachmentBizImpl implements ProductAttachmentBiz {

	@Autowired
	private ProductAttachmentDal productAttachmentDal;


    @Override
    public List<ProductAttachment> selectImgByList(Integer objId, Integer objType, Integer type) {
        return productAttachmentDal.selectImgByList(objId,objType,type);
    }
}
