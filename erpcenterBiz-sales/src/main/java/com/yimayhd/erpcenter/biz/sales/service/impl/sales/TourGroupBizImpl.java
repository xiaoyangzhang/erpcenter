package com.yimayhd.erpcenter.biz.sales.service.impl.sales;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGroup;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.AutocompleteInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupComment;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupPriceAndPersons;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.TourGroupDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.OperatorGroupStatic;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;

public class TourGroupBizImpl implements TourGroupBiz {

	@Autowired
	private TourGroupDal tourGroupDal;

	@Override
	public List<GroupOrder> selectOrderAndGuestInfoByGroupId(Integer groupId) {
		return tourGroupDal.selectOrderAndGuestInfoByGroupId(groupId);
	}

	
	@Override
	public void delFitTourGroup(Integer groupId) {
		tourGroupDal.delFitTourGroup(groupId);
	}

	@Override
	public void updateWapType(Integer groupId) {
		tourGroupDal.updateWapType(groupId);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return tourGroupDal.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TourGroup record) {
		return tourGroupDal.insert(record);
	}

	@Override
	public TourGroup insertSelective(TourGroup record) {
		return tourGroupDal.insertSelective(record);
	}

	@Override
	public TourGroup selectByPrimaryKey(Integer id) {
		return tourGroupDal.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TourGroup record) {
		return tourGroupDal.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TourGroup record) {
		return tourGroupDal.updateByPrimaryKey(record);
	}

	/**
	 * 团号规则-地接社专用
	 */
	@Override
	public String makeCodeByMode(String bizCode, Integer mode, String dateTime, String mark, int count) {
		return tourGroupDal.makeCodeByMode(bizCode, mode, dateTime, mark, count);
	}

	/**
	 * 团号规则-组团社专用 商家编码-产品编码-160101/02(标识)-序号 2016-01-01
	 */
	@Override
	public String makeCodeForAgency(String bizCode, String productCode, String startTime, String endTime, String mark,
			int sort) {
		return tourGroupDal.makeCodeForAgency(bizCode, productCode, startTime, endTime, mark, sort);
	}

	// 获得当天0点时间
	public long getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	// 获得当天24点时间
	public long getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	@Override
	public PageBean selectSKGroupListPage(PageBean pageBean, Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectSKGroupListPage(pageBean, bizId, set);
	}

	@Override
	public int deleteTourGroupById(Integer groupId, Integer orderId) {
		return tourGroupDal.deleteTourGroupById(groupId, orderId);
	}

	@Override
	public void delFitOrder(Integer id) {
		tourGroupDal.delFitOrder(id);
	}

	
	@Override
	public void addFitOrder(Integer groupId, Integer orderId) {
		tourGroupDal.addFitOrder(groupId, orderId);
	}

	@Override
	public Map<String, Object> selectByGroupOrderId(Integer orderId) {
		return tourGroupDal.selectByGroupOrderId(orderId);
	}

	
	@Override
	public GroupOrder insertSelective(TourGroup tourGroup, GroupOrder groupOrder) {
		return tourGroupDal.insertSelective(tourGroup, groupOrder);
	}

	
	@Override
	public GroupOrder insertSelective(TourGroup tourGroup, GroupOrder groupOrder, Integer groupId, Integer userId,
			String userName) {
		return tourGroupDal.insertSelective(tourGroup, groupOrder, groupId, userId, userName);
	}

	
	@Override
	public GroupOrder updateByPrimaryKeySelective(TourGroup tourGroup, GroupOrder groupOrder) {
		return tourGroupDal.updateByPrimaryKeySelective(tourGroup, groupOrder);
	}

	@Override
	public TourGroupPriceAndPersons selectTourGroupInfo(Integer groupId) {
		return tourGroupDal.selectTourGroupInfo(groupId);
	}

	@Override
	public List<String> selectGroupRequirementByGroupId(Integer groupId, Integer type) {
		return tourGroupDal.selectGroupRequirementByGroupId(groupId, type);
	}

	/**
	 * 获取审核人列表
	 */
	@Override
	public List<TourGroup> getAuditorList() {
		return tourGroupDal.getAuditorList();
	}

	@Override
	public PageBean getGroupInfoList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getGroupInfoList(pageBean, tourGroupVo, set);
	}

//	private void getBookingDetails(TourGroupVO tourGroupVo, List<BookingGroup> bookingGroups) {
//		if (bookingGroups != null && bookingGroups.size() > 0) {
//			for (BookingGroup bGroup : bookingGroups) {
//				List<BookingGuide> bookingGuides = guideMapper.selectListByGroupIdAndName(bGroup.getGroupId(),
//						tourGroupVo.getGuideName());
//				bGroup.setGuideList(bookingGuides);
//				List<BookingSupplierPO> bookingSuppliers = supplierMapper.getBookingSupplierPOByGroupIdAndTypeAndName(
//						bGroup.getGroupId(), tourGroupVo.getSupplierType(), tourGroupVo.getSupplierName(),
//						tourGroupVo.getDriverName(), tourGroupVo.getCarLisence());
//				bGroup.setBookingSuppliers(bookingSuppliers);
//				List<BookingSupplierDetail> details = detailMapper.getListByGroupIdAndType(bGroup.getGroupId(),
//						Constants.FLEET);
//				bGroup.setBookingSupplierDetails(details);
//				List<BookingDelivery> deliveries = deliveryMapper.getDeliveryListByGroupIdAndName(bGroup.getGroupId(),
//						tourGroupVo.getSupplierName());
//				bGroup.setBookingDeliveries(deliveries);
//				/*
//				 * Map<String, Integer> personCountMap = groupOrderMapper
//				 * .selectOrderByGroupIdAndName(bGroup.getGroupId(),
//				 * tourGroupVo.getBizId());
//				 */
//				TourGroup group = tourGroupMapper.selectByPrimaryKey(bGroup.getGroupId());
//				// bGroup.setPersonCountMap(personCountMap);
//				bGroup.setTourGroup(group);
//			}
//		}
//	}

	@Override
	
	public void copyGroup(TourGroup tourGroup, GroupOrder groupOrder, Integer groupId, Integer orderId, String info,
			Integer userId, String userName) {
		tourGroupDal.copyGroup(tourGroup, groupOrder, groupId, orderId, info, userId, userName);
	}

	@Override
	
	public Integer changeGroup(Integer groupId, TourGroup tourGroup, String orderIds, Integer userId, String userName,
			String info) throws Exception {
		return tourGroupDal.changeGroup(groupId, tourGroup, orderIds, userId, userName, info);
	}

	@Override
	public PageBean<TourGroup> selectProfitByTourConListPage(PageBean<TourGroup> pageBean, Integer bizId,
			Set<Integer> set) {
		return tourGroupDal.selectProfitByTourConListPage(pageBean, bizId, set);
	}

	@Override
	public PageBean<TourGroup> selectSaleProfitByTourListPage(PageBean<TourGroup> pageBean, Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectSaleProfitByTourListPage(pageBean, bizId, set);
	}

	@Override
	public List<TourGroup> selectTourGroupBycreateTime(Long createTime, Integer bizId) {
		return tourGroupDal.selectTourGroupBycreateTime(createTime, bizId);
	}

	@Override
	public List<TourGroup> selectTourGroupLikeByContent(Long createTime, String startTime, String endTime,
			String content) {
		return tourGroupDal.selectTourGroupLikeByContent(createTime, startTime, endTime, content);
	}

	@Override
	public TourGroup selectByGroupCode(String code) {
		return tourGroupDal.selectByGroupCode(code);
	}

	@Override
	public List<Map<String, Object>> selectAgeList(Map conditionmMap) {
		return tourGroupDal.selectAgeList(conditionmMap);
	}

	@Override
	public List<Map<String, Object>> selectAgeListByAgenctAndProduct(Map conditionmMap) {
		return tourGroupDal.selectAgeListByAgenctAndProduct(conditionmMap);
	}

	@Override
	public List<Map<String, Object>> selectAgeCount(Map conditionmMap) {
		return tourGroupDal.selectAgeCount(conditionmMap);
	}

	@Override
	public List<Map<String, Object>> selectAgeCountWithAgency(Map conditionmMap) {
		return tourGroupDal.selectAgeCountWithAgency(conditionmMap);
	}

	@Override
	public List<Map<String, String>> getAuditUserList(Integer bizId, String name) {
		return tourGroupDal.getAuditUserList(bizId, name);
	}

	@Override
	public PageBean<TourGroup> selectProfitByTourCon(PageBean<TourGroup> pageBean, Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectProfitByTourCon(pageBean, bizId, set);
	}

	@Override
	public PageBean<TourGroup> selectSaleProfitByTourCon(PageBean<TourGroup> pageBean, Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectSaleProfitByTourCon( pageBean, bizId, set);
	}

	@Override
	public TourGroup selectProfitByTourConAndMode(PageBean<TourGroup> pageBean, Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectProfitByTourConAndMode(pageBean, bizId, set);
	}

	@Override
	public TourGroup selectSaleProfitByTourConAndMode(PageBean<TourGroup> pageBean, Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectSaleProfitByTourConAndMode( pageBean, bizId, set);
	}

	@Override
	public TourGroup selectSumCostProfit(TourGroup tourGroup, Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectSumCostProfit( tourGroup, bizId, set);
	}

	@Override
	public PageBean selectBookingShopListPage(PageBean pageBean, Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectBookingShopListPage(pageBean, bizId, set);
	}

	@Override
	public List<AutocompleteInfo> getSupplierNameList(String supplierType) {

		return tourGroupDal.getSupplierNameList( supplierType);
	}

	@Override
	public PageBean getLocalTravelAngencyGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getLocalTravelAngencyGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getHotelGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getHotelGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getFleetGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getFleetGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getRestaurantGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getRestaurantGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getSightGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getSightGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getEntertaimentGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getEntertaimentGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getGolfGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getGolfGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getAirTicketGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getAirTicketGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getTrainTicketGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getTrainTicketGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getInsuranceGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getInsuranceGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getIncomeGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getIncomeGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getOutcomeGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getOutcomeGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getGuideGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getGuideGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getGuideGroupList2(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getGuideGroupList2(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getShopGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getShopGroupList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean getGroupOperateList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		return tourGroupDal.getGroupOperateList(pageBean, tourGroupVo, set);
	}

	@Override
	public PageBean selectBookingFinanceShopGroupListPage(PageBean pageBean, Integer curBizId,
			Set<Integer> dataUserIdSet) {
		return tourGroupDal.selectBookingFinanceShopGroupListPage(pageBean, curBizId, dataUserIdSet);
	}

	@Override
	public BookingGroup selectBookingFinanceShopGroupListPageSum(PageBean pageBean, Integer curBizId,
																 Set<Integer> dataUserIdSet) {
		return tourGroupDal.selectBookingFinanceShopGroupListPageSum(pageBean, curBizId, dataUserIdSet);
	}

	/**
	 * 根据产品统计订单数和总人数
	 */
	@Override
	public Map<String, Integer> getOrderCountAndPersonCountByProduct(Map conditionMap) {
		return tourGroupDal.getOrderCountAndPersonCountByProduct(conditionMap);
	}

	@Override
	public PageBean<OperatorGroupStatic> selectOperatorGroupStaticListPage(PageBean<OperatorGroupStatic> pageBean,
			Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectOperatorGroupStaticListPage(pageBean, bizId, set);
	}

	@Override
	public OperatorGroupStatic selectOperatorGroupStaticCon(PageBean<OperatorGroupStatic> pageBean, Integer bizId,
			Set<Integer> set) {
		return tourGroupDal.selectOperatorGroupStaticCon(pageBean, bizId, set);
	}

	@Override
	public TourGroup selectTotalSKGroup(TourGroup tourGroup, Integer bizId, Set<Integer> set) {
		
		return tourGroupDal.selectTotalSKGroup(tourGroup, bizId, set);
	}

	@Override
	public List<TourGroup> selectIdList(PageBean pageBean) {
		return tourGroupDal.selectIdList(pageBean);
	}

	@Override
	public TourGroup selectInitGroupInfo(Integer bizId) {
		
		return tourGroupDal.selectInitGroupInfo(bizId);
	}

	/**
	 * 根据团id判断团是否能编辑（已审核或者封存后不能编辑）
	 */
	@Override
	public Boolean checkGroupCanEdit(Integer groupId) {
		return tourGroupDal.checkGroupCanEdit(groupId);
	}

	@Override
	public PageBean selectInitGroupList(PageBean pageBean) {
		return tourGroupDal.selectInitGroupList(pageBean);
	}

	
	@Override
	public void deleteInitGroupInfo(Integer groupId) {
		tourGroupDal.deleteInitGroupInfo(groupId);
	}

	@Override
	public List<Map<String, Object>> getGroupHotelBooking(Map paramMap) {
		return tourGroupDal.getGroupHotelBooking(paramMap);
	}

	@Override
	public PageBean<TourGroup> selectTourGroupToQueryListPage(PageBean<TourGroup> pageBean, Integer bizId,
			Set<Integer> set) {
		return tourGroupDal.selectTourGroupToQueryListPage(pageBean, bizId, set);
	}

	@Override
	public TourGroup selectTourGroupToQueryCon(TourGroup tourGroup, Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectTourGroupToQueryCon(tourGroup, bizId, set);
	}

	@Override
	public List<TourGroupComment> getTourGroupComments(Integer bizId, List<Integer> groupIds) {
		return tourGroupDal.getTourGroupComments(bizId, groupIds);
	}

	@Override
	public int updateTourGroupComment(Integer bizId, TourGroupComment comment) {
		return tourGroupDal.updateTourGroupComment(bizId, comment);
	}

	@Override
	public List<Map<String, Object>> selectAgeCountWithAgencyOnly(Map conditionmMap) {
		return tourGroupDal.selectAgeCountWithAgencyOnly(conditionmMap);
	}

	@Override
	public List<TourGroup> selectTravelRecordsByIdCard(String idCard, Integer bizId) {
		return tourGroupDal.selectTravelRecordsByIdCard(idCard, bizId);
	}

	@Override
	public PageBean selectBookingProfitList(PageBean pageBean, Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectBookingProfitList(pageBean, bizId, set);
	}

	@Override
	public Map<String, Object> selectBookingProfitTotal(PageBean pageBean, Integer bizId, Set<Integer> set) {
		return tourGroupDal.selectBookingProfitTotal(pageBean, bizId, set);
	}


	@Override
	public List<TourGroup> selectGroupByDateZone(String startTime,
			String endTime, Integer bizId) {
		return tourGroupDal.selectGroupByDateZone(startTime, endTime, bizId);
	}

	@Override
	public TourGroup findByGroupCode(String code) {
		return tourGroupDal.findByGroupCode(code);
	}

	@Override
	public void changeGroup(Integer groupId, Integer guideId) {
		tourGroupDal.changeGroup(groupId,guideId);
	}
	
	@Override
	public List<TourGroup> selecGroupBefAutoMergerGroup(Integer bizId,String startTime,Integer productId) {
		return tourGroupDal.selecGroupBefAutoMergerGroup(bizId,startTime,productId);
	}

	@Override
	public PageBean getPushDeliveryList(PageBean pageBean, Integer bizId) {
		return tourGroupDal.getPushDeliveryList(pageBean, bizId);
	}
}
