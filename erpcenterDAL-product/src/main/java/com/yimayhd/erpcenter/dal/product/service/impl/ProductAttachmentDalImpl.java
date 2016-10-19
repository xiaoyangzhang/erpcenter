package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.product.dao.ProductAttachmentMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;
import com.yimayhd.erpcenter.dal.product.service.ProductAttachmentDal;

public class ProductAttachmentDalImpl implements ProductAttachmentDal {

	@Autowired
	private ProductAttachmentMapper attachmentMapper;


    @Override
    public List<ProductAttachment> selectImgByList(Integer objId, Integer objType, Integer type) {
        return attachmentMapper.selectImgByList(objId,objType,type);
    }
}
