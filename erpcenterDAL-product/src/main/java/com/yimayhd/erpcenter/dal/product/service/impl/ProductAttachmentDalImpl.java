package com.yimayhd.erpcenter.dal.product.service.impl;

import com.yimayhd.erpcenter.dal.product.dao.ProductAttachmentMapper;
import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;
import com.yimayhd.erpcenter.dal.product.service.ProductAttachmentDal;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductAttachmentDalImpl implements ProductAttachmentDal {

	@Autowired
	private ProductAttachmentMapper attachmentMapper;


    @Override
    public List<ProductAttachment> selectImgByList(Integer objId, Integer objType, Integer type) {
        return attachmentMapper.selectImgByList(objId,objType,type);
    }
}
