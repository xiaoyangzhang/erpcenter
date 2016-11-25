package com.yimayhd.erpcenter.dal.product.dao;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaobaoStockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaobaoStock record);

    int insertSelective(TaobaoStock record);

    TaobaoStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaobaoStock record);

    int updateByPrimaryKey(TaobaoStock record);
    
    List<TaobaoStock> selectByStockListPage(@Param("page") PageBean pageBean);
}