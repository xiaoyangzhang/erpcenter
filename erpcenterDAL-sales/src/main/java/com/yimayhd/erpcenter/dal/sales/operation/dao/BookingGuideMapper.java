package com.yimayhd.erpcenter.dal.sales.operation.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuideListCount;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuideListSelect;
import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;


public interface BookingGuideMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingGuide record);

    int insertSelective(BookingGuide record);

    BookingGuide selectByPrimaryKey(Integer id);
    
	BookingGuide selectByGroupIdAndGuideId(@Param("groupId")Integer groupId,@Param("guideId")Integer guideId);
	
	List<BookingGuide> selectByGroupId(@Param("groupId")Integer groupId);
	
	List<BookingGuide> selectListByGroupId(@Param("groupId")Integer groupId);
	List<BookingGuide> selectListByGroupIdAndName(@Param("groupId")Integer groupId,@Param("guideName")String guideName);
	List<BookingGuide> selectListBySupplierIdAndSupplierType(@Param("supplierId")Integer supplierId,@Param("supplierType")Integer supplierType);
	
    int updateByPrimaryKeySelective(BookingGuide record);

    int updateByPrimaryKey(BookingGuide record);

	Integer getSelectCountByGruopId(Integer groupId);

	int updateBygroupId(Integer groupId);
	
	void updateStateLock(Integer groupId);
	
	void updateStateUnlock(Integer groupId);

	int getBookingCountByTime(@Param("curDay")Long curDay,@Param("nextDay")Long nextDay);

	/**
	 * 导游业务统计
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	List<BookingGuideListCount> selectBookingGuideListCountListPage(
			@Param("page")PageBean pageBean, @Param("bizId")Integer bizId,@Param("set")Set<Integer> set);
	/**
	 * 导游业务总计
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	Map selectBookingGuideTotalCount(@Param("page")PageBean pageBean, @Param("bizId")Integer bizId,@Param("set")Set<Integer> set);

	List<BookingGuide> selectByGuideIdAndGroupId(@Param("guideId")Integer guideId,@Param("groupId")Integer groupId);

	List<BookingGuideListSelect> selectBookingGuideListSelectListPage(
			@Param("page")PageBean pageBean, @Param("bizId")Integer bizId,@Param("set")Set<Integer> set);

	List<BookingGuide> selectDistinctListByGroupId(@Param("groupId")Integer groupId);

	List<Map<String, Object>> selectShopTjByGroupId(@Param("groupId")Integer groupId);

	List<BookingGuide> selectListByGroupId2(@Param("groupId")Integer groupId);

}