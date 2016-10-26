package com.yimayhd.erpcenter.biz.sales.client.service.sales;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.AutocompleteInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupComment;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupPriceAndPersons;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.OperatorGroupStatic;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;

/**
 * 
 * @author qindz
 *
 */
public interface TourGroupBiz {
	/**
	 * 旅行团的删除属性
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 旅行团的新增属性1
	 * 
	 * @param record
	 * @return
	 */
	int insert(TourGroup tourGroup);

	/**
	 * 旅行团的新增属性2
	 * 
	 * @param record
	 * @return
	 */
	TourGroup insertSelective(TourGroup tourGroup);

	/**
	 * 新增旅行团和订单信息
	 * @param record
	 * @return
	 */
	GroupOrder insertSelective(TourGroup tourGroup,GroupOrder groupOrder);
	/**
	 * 新增旅行团和订单信息、导游信息
	 * @param record
	 * @return
	 */
	GroupOrder insertSelective(TourGroup tourGroup,GroupOrder groupOrder,Integer groupId,Integer userId,String userName);
	/**
	 * 旅行团的查询属性
	 * 
	 * @param id
	 * @return
	 */
	TourGroup selectByPrimaryKey(Integer id);
	
	/**
	 * 根据订单查询订单和旅行团信息
	 * @return
	 */
	Map<String, Object> selectByGroupOrderId(Integer orderId) ;
	
	/**
	 * 旅行团的修改属性1
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(TourGroup tourGroup);

	/**
	 * 旅行团订单修改
	 * 
	 * @param record
	 * @return
	 */
	GroupOrder updateByPrimaryKeySelective(TourGroup tourGroup,GroupOrder groupOrder);
	/**
	 * 旅行团的修改属性1
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(TourGroup tourGroup);

	/**
	 * 
	 * @param supplierCode
	 *            当前商家编码
	 * @param mode
	 *            团队类型 0散客团 其他数字为定制团
	 * @return
	 */
	String makeCodeByMode(String bizCode, Integer mode,String dateTime,String mark ,int sort);
	
	String makeCodeForAgency(String bizCode,String productCode,String startTime,String endTime,String mark ,int sort);
	
	/**
	 * 散客团列表
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean selectSKGroupListPage(PageBean pageBean,Integer bizId,Set<Integer> set);
	
	/**
	 * 散客团总共
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	TourGroup selectTotalSKGroup(TourGroup tourGroup,Integer bizId,Set<Integer> set);
	
	/**
	 * 旅行团的逻辑删除
	 * @param id
	 * @return
	 */
	int deleteTourGroupById(Integer groupId,Integer orderId);
	
	/**
	 * 团列表
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	//PageBean selectGroupListPage(PageBean pageBean, Integer bizId,Set<Integer> set);
	
	
	/**
	 * 删除散客团里的订单
	 * @param id
	 */
	void delFitOrder(Integer id);
	
	/**
	 * 增加散客团里的订单
	 * @param id
	 */
	void addFitOrder(Integer groupId,Integer id);
	
	/**
	 * 根据团id返回该旅行团下订单的收入和成本的总价和不同类型客人的人数
	 * @param groupId
	 * @return
	 */
	TourGroupPriceAndPersons selectTourGroupInfo(Integer groupId);
	
	
	/**
	 * 删除散客团
	 */
	
	void delFitTourGroup(Integer groupId);
	
	List<GroupOrder> selectOrderAndGuestInfoByGroupId(Integer groupId);
	
	/**
	 * 根据团ID和创建时间查询计调需求
	 * @param groupId
	 * @param dateList
	 * @return
	 */
	List<String> selectGroupRequirementByGroupId(Integer groupId,Integer type) ;
	//计调安排统计专用
	PageBean getGroupInfoList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 获取审核人列表
	 * @return
	 */
	List<TourGroup> getAuditorList();
	
/**
 * 复制团
 * @param tourGroup
 * @param groupOrder
 * @param groupId
 * @param orderId
 * @param info
 */
	void copyGroup(TourGroup tourGroup,GroupOrder groupOrder,Integer groupId,Integer orderId,String info,Integer userId,String userName);
	
	Integer changeGroup(Integer groupId,TourGroup tourGroup,String orderIds,Integer userId,String userName,String info) throws Exception;
	
	/**
	 * 根据团id和导游id获取billList
	 * @param bizId
	 * @param id
	 * @param guideId
	 * @return
	 */
	
	/**
	 * 利润查询 --按团查询
	 * @param pageBean
	 * @return
	 */
	PageBean<TourGroup> selectProfitByTourConListPage(PageBean<TourGroup> pageBean,Integer bizId,Set<Integer> set);
	/**
	 * 统计所有团成人小孩全陪
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean<TourGroup> selectProfitByTourCon(PageBean<TourGroup> pageBean,Integer bizId,Set<Integer> set);
	/**
	 * 统计所有团总收入总成本
	 * @param pageBean
	 * @param bizId
	 * @param mode
	 * @return
	 */
	TourGroup selectProfitByTourConAndMode(PageBean<TourGroup> pageBean,Integer bizId,Set<Integer> set);

	/*
	 * 计调 - 首页
	 * 根据时间进行分页查询（app端）
	 * 
	 */
	List<TourGroup> selectTourGroupBycreateTime(Long createTime,Integer bizId);
	
	/*
	 * 计调 - 首页 
	 * 搜索
	 * 根据输入内容或者时间段进行模糊搜索   
	 * createTime  时间用于分页      startTime 搜索开始时间     endTime 搜索结束时间    content 搜索内容 （计调 团号）
	 */
	List<TourGroup> selectTourGroupLikeByContent(Long createTime,String startTime,String endTime,String content);
	
	
	TourGroup selectByGroupCode(String code); 
	/**
	 * 可查询可下拉审核人列表
	 * @param bizId
	 * @param name
	 * @return
	 */
	List<Map<String, String>> getAuditUserList(Integer bizId, String name);
	
	
	/**
	 * 客人购物录入团列表
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean selectBookingShopListPage(PageBean pageBean, Integer bizId,Set<Integer> set);
	
	/**
	 * 根据类型获取对应的商家
	 * @param supplierType
	 * @return
	 */
	public List<AutocompleteInfo> getSupplierNameList(String supplierType);

	List<Map<String, Object>> selectAgeList(Map conditionmMap);

	List<Map<String, Object>> selectAgeListByAgenctAndProduct(Map conditionmMap);
	/**
	 * 根据产品统计各年龄段人数
	 * @param conditionmMap
	 * @return
	 */
	List<Map<String, Object>> selectAgeCount(Map conditionmMap);
	/**
	 * 根据产品+组团社统计各年龄段人数
	 * @param conditionmMap
	 * @return
	 */
	List<Map<String, Object>> selectAgeCountWithAgency(Map conditionmMap);
	/**
	 * 根据组团社统计各年龄段人数
	 * @param conditionmMap
	 * @return
	 */
	List<Map<String, Object>> selectAgeCountWithAgencyOnly(Map conditionmMap);
	
	//计调团列表
	/**
	 * 下接社团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getLocalTravelAngencyGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);	
	
	/**
	 * 计调-酒店订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getHotelGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 计调-餐厅订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getRestaurantGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 计调-门票订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getSightGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 计调-娱乐订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getEntertaimentGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 计调-高尔夫订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getGolfGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 计调-机票订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getAirTicketGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 计调-火车票订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getTrainTicketGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 计调-保险订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getInsuranceGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 计调-收入订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getIncomeGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 计调-导游订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getGuideGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 计调-导游报账单列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getGuideGroupList2(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	
	/**
	 * 计调-购物订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getShopGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 计调-支出订单团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getOutcomeGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);
	/**
	 * 订车团列表
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getFleetGroupList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);

	/**
	 * 计调安排查询
	 * @param pageBean
	 * @param tourGroupVo
	 * @param set
	 * @return
	 */
	PageBean getGroupOperateList(PageBean pageBean,TourGroupVO tourGroupVo,Set<Integer> set);

	/**
	 * 购物管理-购物录入
	 * @param pageBean
	 * @param curBizId
	 * @param dataUserIdSet
	 * @return
	 */
	PageBean selectBookingFinanceShopGroupListPage(PageBean pageBean,
			Integer curBizId, Set<Integer> dataUserIdSet);
	BookingGroup selectBookingFinanceShopGroupListPageSum(PageBean pageBean,
			Integer curBizId, Set<Integer> dataUserIdSet);
	
	Map<String, Integer>  getOrderCountAndPersonCountByProduct(Map conditionmMap);
	/**
	 * 计调业绩统计
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean<OperatorGroupStatic> selectOperatorGroupStaticListPage(PageBean<OperatorGroupStatic> pageBean,Integer bizId,Set<Integer> set);
	/**
	 * 计调业绩统计总合计
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	OperatorGroupStatic selectOperatorGroupStaticCon(PageBean<OperatorGroupStatic> pageBean,Integer bizId,Set<Integer> set);
	
	/**
	 * 查询团ID列表
	 * @param pageBean
	 * @return
	 */
	List<TourGroup> selectIdList(PageBean pageBean);

	
	TourGroup selectInitGroupInfo(Integer bizId);

	
	/**
	 * 判断团是否能编辑（已审核和已归档的团相关信息包括计调和财务不能再修改）
	 * @param groupId
	 * @return
	 */
	Boolean checkGroupCanEdit(Integer groupId);

	PageBean selectInitGroupList(PageBean pageBean);
	/**
	 * 删除期初数据
	 * @param groupId
	 */
	void deleteInitGroupInfo(Integer groupId);
//获取某个团的所有酒店计调订单
	List<Map<String, Object>> getGroupHotelBooking(Map paramMap);
	
	/**
	 * 出团离团查询 --分页
	 * @param pageBean
	 * @return
	 */
	PageBean<TourGroup> selectTourGroupToQueryListPage(PageBean<TourGroup> pageBean,Integer bizId,Set<Integer> set);
	TourGroup selectTourGroupToQueryCon(TourGroup tourGroup,Integer bizId,Set<Integer> set);
	
	List<TourGroupComment> getTourGroupComments(Integer bizId, List<Integer> groupIds);
	int updateTourGroupComment(Integer bizId, TourGroupComment comment);
	/**
	 * 根据客人身份证号查询旅游记录
	 * @param idCard
	 * @return
	 */
	List<TourGroup> selectTravelRecordsByIdCard(String idCard,Integer bizId);
	/**
	 * 计调订单利润查询
	 * @param pageBean
	 * @param bizId
	 * @param set
	 * @return
	 */
	PageBean selectBookingProfitList(PageBean pageBean,Integer bizId,Set<Integer> set);
	Map<String, Object> selectBookingProfitTotal(PageBean pageBean,Integer bizId,Set<Integer> set);
	
	/*
	 * 根据日期查询团记录 --ou 2016-06-15
	 */
	List<TourGroup> selectGroupByDateZone(String startTime,String endTime,Integer bizId);

	void changeGroup(Integer groupId, Integer guideId);
	
	List<TourGroup> selecGroupBefAutoMergerGroup(Integer bizId,String startTime,Integer productId);
}
