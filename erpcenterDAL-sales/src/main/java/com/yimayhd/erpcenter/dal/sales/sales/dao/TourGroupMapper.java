package com.yimayhd.erpcenter.dal.sales.sales.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGroup;
import com.yimayhd.erpcenter.dal.sales.client.quality.vo.QualityTourGroupVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.OperatorGroupStatic;

public interface TourGroupMapper {
	
	TourGroup selectByGroupCode(@Param("code")String code);
	
	
	void updateTotalIncome(@Param("groupId")Integer groupId);
	
	void updateStateToSeal(@Param("groupIds")String groupIds, @Param("operateLog")String operateLog);
	
	int deleteByPrimaryKey(Integer id);

	int insert(TourGroup record);

	int insertSelective(TourGroup record);

	TourGroup selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TourGroup record);

	int updateByPrimaryKey(TourGroup record);

	TourGroup  selectGroupCodeSort(@Param("bizId")Integer bizId, @Param("mode") Integer mode,@Param("startTime") String startTime);
	
	TourGroup  selectGroupCodeSortNoMode(@Param("bizId")Integer bizId, @Param("startTime") String startTime);


	List<TourGroup> selectSKGroupListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	TourGroup selectTotalSKGroup(@Param("tourGroup") TourGroup tourGroup,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);

	int deleteTourGroupById(Integer id);

	List<BookingGroup> selectGroupListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	List<BookingGroup> selectGroup2ListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	List<BookingGroup> selectGroup3ListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	List<BookingGroup> selectGroup4ListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	List<BookingGroup> selectGroup5ListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	//条件查询计调各菜单
	List<BookingGroup> selectLocalTravleListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	List<BookingGroup> selectGuideListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	List<BookingGroup> selectGuideListPage2(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	List<BookingGroup> selectShopListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	List<BookingGroup> selectHotelListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	List<BookingGroup> selectFleetListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	List<BookingGroup> selectRestrauntListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	List<BookingGroup> selectEntertainmentListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	List<BookingGroup> selectGolfListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	List<BookingGroup> selectAirTicketListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	List<BookingGroup> selectTrainTicketListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	List<BookingGroup> selectIncomeListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	List<BookingGroup> selectOutcomeListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	List<BookingGroup> selectSightListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	List<BookingGroup> selectInsuranceListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	/**
	 * 获取审核人列表
	 */
	List<TourGroup> getAuditorList();
	
	/**
	 * 查询团的操作日志
	 * @param groupId
	 * @return
	 */
	List<Map> selectOperateLogPage(@Param("groupId")Integer groupId);
	
	/**
	 * 查询团未审核的订单数量
	 * @param groupId
	 * @return
	 */
	int getUnauditedCount(@Param("groupId")Integer groupId); 
	
	/**
	 * 按团查询利润
	 * @param pageBean
	 * @return
	 */
	List<TourGroup> selectProfitByTourConListPage(@Param("page")PageBean<TourGroup> pageBean,@Param("bizId")Integer bizId,@Param("set")Set<Integer> set);
	
	/**
	 * 统计所有团信息
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	List<TourGroup> selectProfitByTourCon(@Param("page")PageBean<TourGroup> pageBean,@Param("bizId")Integer bizId,@Param("set")Set<Integer> set);
	/**
	 * 统计所有团总成本、总收入（带查询条件）
	 * @param pageBean
	 * @param bizId
	 * @param mode
	 * @return
	 */
	TourGroup selectProfitByTourConAndMode(@Param("page")PageBean<TourGroup> pageBean,@Param("bizId")Integer bizId,@Param("set")Set<Integer> set);
	/**
	 * 统计当前团总收入和总成本
	 * @param bizId
	 * @param mode
	 * @return
	 */
	BigDecimal selectProfitByModeAndTourId(@Param("id")Integer id,@Param("mode")Integer mode);
	/**
	 * 质量管理-团列表
	 * @param pageBean
	 * @return
	 */
	List<QualityTourGroupVo> selectQualityTourGroupListPage(@Param("page")PageBean<QualityTourGroupVo> pageBean);
	List<Map<String, Object>> selectAgeList(@Param("productBrandName")String productBrandName,@Param("productName")String productName,@Param("gender")Integer gender,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("bizId")Integer bizId);
	List<Map<String, Object>> selectAgeList(@Param("map")Map conditionMap); 
	List<Map<String, Object>> selectAgeCount(@Param("productBrandName")String productBrandName,@Param("productName")String productName,@Param("gender")Integer gender,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("bizId")Integer bizId);
	List<Map<String, Object>> selectAgeCount(@Param("map")Map conditionMap);
	List<Map<String, Object>> selectAgeListByAgencyAndProduct(@Param("productBrandName")String productBrandName,@Param("productName")String productName,@Param("gender")Integer gender,@Param("supplierName")String supplierName,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("bizId")Integer bizId);
	List<Map<String, Object>> selectAgeListByAgencyAndProduct(@Param("map")Map conditionMap);
	
	List<Map<String, Object>> selectAgeCountWithAgency(@Param("productBrandName")String productBrandName,@Param("productName")String productName,@Param("supplierName")String supplierName,@Param("gender")Integer gender,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("bizId")Integer bizId);
	List<Map<String, Object>> selectAgeCountWithAgency(@Param("map")Map conditionMap);
	List<Map<String, Object>> selectAgeCountWithAgencyOnly(@Param("map")Map conditionMap);
	
	/*
	 * 计调 首页
	 * 根据时间进行分页查询（app端）
	 */
	List<TourGroup> selectTourGroupListByCreatetime(@Param("createTime")Long createTime,@Param("bizId")Integer bizId);
	
	/*
	 * 计调 - 首页 
	 * 搜索
	 * 根据输入内容或者时间段进行模糊搜索   
	 * createTime  时间用于分页      startTime 搜索开始时间     endTime 搜索结束时间    content 搜索内容 （计调 团号）
	 */
	List<TourGroup> selectTourGroupLikeByContent(@Param("createTime")Long createTime,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("content")String content);

	/**
	 * 可查询可下拉审核人列表
	 * @param bizId
	 * @param decode
	 * @return
	 */
	List<Map<String, String>> getAuditUserList(@Param("bizId")Integer bizId,@Param("name")String name);
	List<BookingGroup> selectGroupBookingShopListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);


	/**
	 * 计调安排查询
	 * @param pageBean
	 * @param bizId
	 * @param set
	 * @return
	 */
	List<BookingGroup> selectGroupOperateListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	/**
	 * 购物管理-购物录入（财务）
	 * @param pageBean
	 * @param bizId
	 * @param set
	 * @return
	 */
	List<BookingGroup> selectBookingFinanceShopGroupListPage(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	BookingGroup selectBookingFinanceShopGroupSum(@Param("page") PageBean pageBean,
			@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	Map<String, Integer> getOrderCountAndPersonCountByProduct(@Param("map")Map conditionMap);
	
	/**
	 * 计调业绩统计
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	List<OperatorGroupStatic> selectOperatorGroupStaticListPage(@Param("page") PageBean<OperatorGroupStatic> pageBean,@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	OperatorGroupStatic selectOperatorGroupStaticCon(@Param("page") PageBean<OperatorGroupStatic> pageBean,@Param("bizId") Integer bizId,@Param("set")Set<Integer> set);
	
	/**
	 * 根据条件查询团ID列表
	 * @param pageBean
	 * @return
	 */
	List<TourGroup> selectIdList(@Param("page") PageBean pageBean); 
	
	/**
	 * 更新团的时间戳
	 */
	void updateTimestampByPrimaryKey(@Param("id") Integer id);

	
	/**
	 * 查询初始团
	 * @param bizId
	 * @return
	 */
	TourGroup selectInitGroupInfo(Integer bizId);

	
	/**
	 * 根据id获取团信息（根据需要获取）
	 * @param id
	 * @return
	 */
	Map<String,Object> getGroupInfoById(@Param("id")Integer id);


	List<TourGroup> selectInitGroupListPage(PageBean pageBean);
	
	List<TourGroup> selecGroupBefAutoMergerGroup(@Param("bizId")Integer bizId,@Param("startTime")String startTime,@Param("productId")Integer productId);

	List<Map<String,Object>> selectGroupOrderListPage(PageBean pageBean);
	//获取某个团下的所有酒店计调订单
	List<Map<String, Object>> getGroupHotelBooking(@Param("parameter")Map parameters);
	
	/**
	 * 出团离团查询 --分页
	 * @param pageBean
	 * @return
	 */
	List<TourGroup> selectTourGroupToQueryListPage(@Param("page")PageBean<TourGroup> pageBean,@Param("bizId")Integer bizId,@Param("set")Set<Integer> set);
	
	TourGroup selectTourGroupToQueryCon(@Param("tourGroup")TourGroup tourGroup,@Param("bizId")Integer bizId,@Param("set")Set<Integer> set);


	List<TourGroup> selectTravelRecordsByIdCard(@Param("idCard")String idCard,@Param("bizId")Integer bizId);
	/**
	 * 计调订单利润查询
	 * @param pageBean
	 * @param bizId
	 * @param set
	 * @return
	 */
	List<TourGroup> selectBookingProfitListPage(@Param("page")PageBean pageBean,@Param("bizId")Integer bizId,@Param("set")Set<Integer> set);
	Map<String, Object> selectBookingProfitTotal(@Param("page")PageBean pageBean,@Param("bizId")Integer bizId,@Param("set")Set<Integer> set);
	
	/*
	 * 根据日期查询团记录 --ou 2016.06.15
	 */
	List<TourGroup> selectGroupByDateZone(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("bizId")Integer bizId);
}
