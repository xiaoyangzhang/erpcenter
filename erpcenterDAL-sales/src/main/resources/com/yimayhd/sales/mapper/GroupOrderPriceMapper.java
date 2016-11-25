package com.yihg.sales.dao;

import com.yihg.sales.po.GroupOrderPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupOrderPriceMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(GroupOrderPrice record);

	int insertSelective(GroupOrderPrice record);

	GroupOrderPrice selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(GroupOrderPrice record);

	int updateByPrimaryKey(GroupOrderPrice record);

	List<GroupOrderPrice> selectByOrderAndType(
            @Param("orderId") Integer orderId, @Param("mode") Integer mode);

	List<GroupOrderPrice> selectByOrder(Integer orderId);

	List<GroupOrderPrice> selectAllPriceByOrder(Integer orderId);

	void updateOrderAndGroupPrice(Integer orderId);

	void updateGroupPrice(Integer groupId);
    
    void updatePriceLockState(@Param("orderId") Integer orderId, @Param("priceLockState") Integer priceLockState);
    List<GroupOrderPrice> getPriceInfoByOrderId(@Param("orderId") Integer orderId);
    
    void auditPriceByIds(@Param("ids") String ids, @Param("stateFinance") Integer stateFinance);
    
    
    GroupOrderPrice selectByOrderAndTypeAndRowState(
            @Param("orderId") Integer orderId, @Param("mode") Integer mode, @Param("rowState") Integer rowState);
}