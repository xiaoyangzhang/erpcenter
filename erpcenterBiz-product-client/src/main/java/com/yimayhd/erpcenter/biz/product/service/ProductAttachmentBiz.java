package com.yimayhd.erpcenter.biz.product.service;


import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;

public interface ProductAttachmentBiz {


    List<ProductAttachment> selectImgByList(Integer objId,Integer objType,Integer type);
}
