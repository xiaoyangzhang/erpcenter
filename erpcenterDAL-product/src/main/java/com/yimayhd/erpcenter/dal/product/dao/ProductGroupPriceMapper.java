package com.yimayhd.erpcenter.dal.product.dao;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.erpcenter.dal.product.po.PriceView;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultItemVo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductGroupPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductGroupPrice record);

    int insertSelective(ProductGroupPrice record);

    ProductGroupPrice selectByPrimaryKey(Integer id);

    List<Date> selectDateByProductId(Integer productId);

    List<PriceView> selectPricesByGroupId(@Param("groupId")Integer groupId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    List<ProductGroupPrice> selectByGroupId(@Param("groupId")Integer groupId, @Param("year")String year, @Param("month") String month);

    int updateByPrimaryKeySelective(ProductGroupPrice record);

    int updateByPrimaryKey(ProductGroupPrice record);
    int updateByGroupId(@Param("groupId")Integer groupId);
    
    List<String> selectDatesByProductId(@Param("productId")Integer productId,@Param("groupDate") String groupDate);
   
    int increaseStockCount(@Param("priceId")Integer priceId, @Param("increaseCount")Integer increaseCount);
    
    List<StockStaticsResultItemVo> getStockStatics(@Param("productId")Integer productId,@Param("fromDate")Date fromDate,@Param("toDate")Date toDate);
    
    List<ProductGroupPrice> selectByGroupIdAndDateSpan(@Param("groupId")Integer groupId,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
    
    List<ProductGroupPrice> selectByGroupIdAndGroupDate(@Param("groupId")Integer groupId,@Param("groupDate")Date groupDate);
   Integer selectProductGroupByProduct(@Param("parameter")Map parameters);
   
   int copyPrices(@Param("fromGroupId")Integer fromGroupId,@Param("toGroupId")Integer toGroupId);
   
   int deleteByGroupIdList(@Param("groupIdList")List<Integer> groupIdList);
   
   /**
    * 根据参考
    * @param productId
    * @return
    */		
   List<Map> selectMinPriceByProductIdAndDate(@Param("list")List<Integer> productId,@Param("groupDate") Date groupDate);

   List<ProductGroupPrice> selectPricesByGroupId2(@Param("groupId")Integer groupId);

   void deleteByGroupIdNotInIds(@Param("bizId")Integer bizId, @Param("groupId")Integer groupId, @Param("notIds")String notIds);

   List<ProductGroupPrice> getPriceByPidAndUserIdAndDate(@Param("bizId")Integer bizId,@Param("productId")Integer productId,
		   @Param("operatorId")Integer operatorId, @Param("date")Date date);
}