package com.yimayhd.erpcenter.dal.product.dao;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.product.po.TaobaoProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TaobaoProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaobaoProduct record);

    int insertSelective(TaobaoProduct record);

    TaobaoProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaobaoProduct record);

    int updateByPrimaryKey(TaobaoProduct record);
    
    List<TaobaoProduct> findTaobaoProductById(@Param("set") Set<Integer> Set);
    
    int insertTaobaoProduct(TaobaoProduct record);
    
    List<TaobaoProduct> findTaoBaoProductListPage(@Param("page") PageBean pageBean);
    
    List<TaobaoProduct> selectTaobaoProductListPage(@Param("page") PageBean pageBean, @Param("bizId") Integer bizId);
    
    TaobaoProduct selectTaobaoProductBynumIidAndModified(@Param("numIid") String numIid);
    List<Map<String,Object>> findTaoBaoProductStockListPage(@Param("page") PageBean pageBean);
}