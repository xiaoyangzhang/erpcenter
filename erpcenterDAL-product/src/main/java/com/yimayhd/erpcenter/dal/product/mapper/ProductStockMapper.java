package com.yimayhd.erpcenter.dal.product.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.product.po.ProductStock;

public interface ProductStockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductStock record);

    int insertSelective(ProductStock record);

    ProductStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductStock record);

    int updateByPrimaryKey(ProductStock record);
    
    List<ProductStock> getStocksByProductIdAndDateSpan(@Param("productId") Integer productId,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
    
    List<ProductStock> getStockByProductIdAndDate(@Param("productId") Integer productId,@Param("date")Date itemDate);
    
    int setDeleteByProductIdAndDateSpan(@Param("productId")Integer productId,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
    
    //int insertBatch(@Param("stockList")List<ProductStock> list);
    
    int updateStockCount(@Param("productId") Integer productId,@Param("date")Date itemDate,@Param("count")Integer count);
    
    int deleteByProductIdAndDate(@Param("productId") Integer productId,@Param("date")Date itemDate);

    /**
     * P（加） D（减） Z（预留减收客加）
     * @param productId
     * @param itemDate
     * @param count
     * @param type
     * @return
     */
	int updateReserveCount(@Param("productId") Integer productId,@Param("date")Date itemDate,@Param("count")Integer count,
			@Param("type") String type);

}