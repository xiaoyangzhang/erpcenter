package com.yimayhd.erpcenter.dal.product.dao;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.product.po.TaobaoProductSkus;


public interface TaobaoProductSkusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaobaoProductSkus record);

    int insertSelective(TaobaoProductSkus record);

    TaobaoProductSkus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaobaoProductSkus record);

    int updateByPrimaryKey(TaobaoProductSkus record);
    
    TaobaoProductSkus selectByVid(@Param("vid")String vid,@Param("numIid")String numIid);
}