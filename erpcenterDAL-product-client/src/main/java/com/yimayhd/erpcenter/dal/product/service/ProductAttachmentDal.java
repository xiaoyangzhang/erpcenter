package com.yimayhd.erpcenter.dal.product.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;

public interface ProductAttachmentDal {


    List<ProductAttachment> selectImgByList(Integer objId,Integer objType,Integer type);
}
