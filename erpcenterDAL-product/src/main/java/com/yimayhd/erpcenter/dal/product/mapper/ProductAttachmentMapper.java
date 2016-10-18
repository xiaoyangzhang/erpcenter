package com.yimayhd.erpcenter.dal.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;

public interface ProductAttachmentMapper {
    int deleteByobjId(@Param("objId")Integer objId,@Param("objType")Integer objType);

    int insert(ProductAttachment record);

    int insertSelective(ProductAttachment record);

    ProductAttachment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductAttachment record);

    int updateByPrimaryKey(ProductAttachment record);
    /**
     * 查询图片附件
     * @param productId 产品id
     * @return
     */
	List<ProductAttachment> selectByAttList(@Param("objId")Integer objId,@Param("objType")Integer objType);

    /**
     * 查询图片
     * @param productId 产品id
     * @return
     */
    List<ProductAttachment> selectImgByList(@Param("objId")Integer objId,@Param("objType")Integer objType,@Param("type")Integer type);
	
}