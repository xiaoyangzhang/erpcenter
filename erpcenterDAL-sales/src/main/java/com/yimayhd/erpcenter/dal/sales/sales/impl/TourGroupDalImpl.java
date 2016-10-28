package com.yimayhd.erpcenter.dal.sales.sales.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.util.TypeUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.utils.DateUtils;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuideTimes;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierPO;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingDeliveryDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingGuideDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingSupplierDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.AutocompleteInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteAttachment;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRouteTraffic;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupComment;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupPriceAndPersons;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupOrderDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.GroupRouteDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.TourGroupDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.OperatorGroupStatic;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingDeliveryMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingGuideMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingGuideTimesMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingSupplierDetailMapper;
import com.yimayhd.erpcenter.dal.sales.operation.dao.BookingSupplierMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderGuestMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderPriceMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderTransportMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRequirementMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteAttachmentMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteSupplierMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupRouteTrafficMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.TourGroupCommentMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.TourGroupMapper;
import com.yimayhd.erpcenter.dal.sales.sales.util.GenerateCodeUtil;

public class TourGroupDalImpl implements TourGroupDal {

	@Autowired
	private TourGroupMapper tourGroupMapper;
	@Autowired
	private TourGroupCommentMapper tourGroupCommentMapper;

	@Autowired
	private GroupOrderMapper groupOrderMapper;
	@Autowired
	private BookingGuideDal guideDal;
	@Autowired
	private BookingGuideMapper guideMapper;
	@Autowired
	private BookingGuideTimesMapper bookingGuideTimesMapper;

	@Autowired
	private BookingSupplierDetailMapper detailMapper;
	@Autowired
	private BookingSupplierDal supplierDal;
	@Autowired
	private BookingSupplierMapper supplierMapper;
	@Autowired
	private GroupOrderGuestMapper groupOrderGuestMapper;
	@Autowired
	private BookingDeliveryMapper deliveryMapper;
	@Autowired
	private GroupOrderPriceMapper groupOrderPriceMapper;
	@Autowired
	private GroupRouteMapper groupRouteMapper;
	@Autowired
	private GroupOrderDal groupOrderDal;
	@Autowired
	private GroupRouteDal groupRouteDal;
	@Autowired
	private GroupRequirementMapper groupRequirementMapper;
	@Autowired
	private GroupOrderTransportMapper groupOrderTransportMapper;
	@Autowired
	private GroupRouteSupplierMapper groupRouteSupplierMapper;
	@Autowired
	private GroupRouteTrafficMapper groupRouteTrafficMapper;
	@Autowired
	private GroupRouteAttachmentMapper groupRouteAttachmentMapper;
	@Autowired
	private BookingDeliveryDal bookingDeliveryDal;

	@Override
	public List<GroupOrder> selectOrderAndGuestInfoByGroupId(Integer groupId) {
		List<GroupOrder> orderList = groupOrderMapper.selectOrderByGroupId(groupId);
		for (GroupOrder groupOrder : orderList) {
			List<GroupOrderGuest> list = groupOrderGuestMapper.selectByOrderId(groupOrder.getId());
			groupOrder.setGroupOrderGuestList(list);
		}
		return orderList;
	}

	@Transactional
	@Override
	public void delFitTourGroup(Integer groupId) {

		List<GroupOrder> list = groupOrderMapper.selectOrderByGroupId(groupId);
		if (list != null && list.size() > 0) {
			for (GroupOrder groupOrder : list) {
				groupOrder.setGroupId(null);
				groupOrderMapper.updateByPrimaryKey(groupOrder);
			}
		}
		TourGroup tourGroup = tourGroupMapper.selectByPrimaryKey(groupId);
		tourGroup.setGroupState(-1);
		tourGroupMapper.updateByPrimaryKeySelective(tourGroup);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return tourGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TourGroup record) {
		return tourGroupMapper.insert(record);
	}

	@Override
	public TourGroup insertSelective(TourGroup record) {
		tourGroupMapper.insertSelective(record);
		return record;
	}

	@Override
	public TourGroup selectByPrimaryKey(Integer id) {
		return tourGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TourGroup record) {

		int updateReturn = tourGroupMapper.updateByPrimaryKeySelective(record);
		if (record.getTotalAdult() != null) {
			bookingDeliveryDal.updateDeliveryGuestCountOnModifyTeamGuestCount(record.getId(),
					record.getTotalAdult(), record.getTotalChild(), record.getTotalGuide());
		}
		return updateReturn;
	}

	@Override
	public int updateByPrimaryKey(TourGroup record) {
		int updateReturn = tourGroupMapper.updateByPrimaryKey(record);
		bookingDeliveryDal.updateDeliveryGuestCountOnModifyTeamGuestCount(record.getId(), record.getTotalAdult(),
				record.getTotalChild(), record.getTotalGuide());
		return updateReturn;
	}

	/**
	 * 团号规则-地接社专用
	 */
	@Override
	public String makeCodeByMode(String bizCode, Integer mode, String dateTime, String mark, int count) {

		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		try {
			dateTime = sdf.format(sdf.parse(dateTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// int count = tourGroupMapper.selectGroupCodeSort(mode, dateTime);
		String groupCode = "";
		if (mode != null && mode == 0) {
			groupCode = "S";
		} else {
			groupCode = "T";
		}
		mark = (mark == null || "".equals(mark)) ? "" : "(" + mark.toUpperCase() + ")";
		String code = bizCode + "-" + dateTime.replace("-", "") + groupCode + mark + "-" + count;
		return code;
	}

	/**
	 * 团号规则-组团社专用 商家编码-产品编码-160101/02(标识)-序号 2016-01-01
	 */
	@Override
	public String makeCodeForAgency(String bizCode, String productCode, String startTime, String endTime, String mark,
			int sort) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yy-MM-dd");
		try {
			startTime = sdf1.format(sdf1.parse(startTime));
			endTime = sdf1.format(sdf1.parse(endTime));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mark = (mark == null || "".equals(mark)) ? "" : "(" + mark.toUpperCase() + ")";

		String code = "";
		if (productCode == null || "".equals(productCode)) {
			code = bizCode + "-" + startTime.replace("-", "") + "/" + endTime.split("-")[2] + mark + "-" + sort;
		} else {
			code = bizCode + "-" + productCode + "-" + startTime.replace("-", "") + "/" + endTime.split("-")[2] + mark
					+ "-" + sort;
		}
		return code;
	}

	public static void main(String[] args) {
		String code = new TourGroupDalImpl().makeCodeForAgency("test", "ttt", "2016-01-01", "2016-02-01", "哈哈哈",
				100);
		System.out.println(code);

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
		List<TourGroup> result = tourGroupMapper.selectSKGroupListPage(pageBean, bizId, set);
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public int deleteTourGroupById(Integer groupId, Integer orderId) {
		try {

			tourGroupMapper.deleteTourGroupById(groupId);
			groupOrderMapper.delGroupOrder(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delFitOrder(Integer id) {
		GroupOrder groupOrder = groupOrderMapper.selectByPrimaryKey(id);
		TourGroup tourGroup = tourGroupMapper.selectByPrimaryKey(groupOrder.getGroupId());
		groupOrder.setGroupId(null);
		groupOrderMapper.updateByPrimaryKey(groupOrder);

		List<GroupRequirement> list = groupRequirementMapper.selectByOrderId(id);
		if (list != null && list.size() > 0) {
			for (GroupRequirement groupRequirement : list) {
				groupRequirement.setGroupId(null);
				groupRequirementMapper.updateByPrimaryKey(groupRequirement);

			}
		}

		tourGroup.setOrderNum(tourGroup.getOrderNum() - 1);
		tourGroupMapper.updateByPrimaryKeySelective(tourGroup);
		groupOrderDal.updateGroupPersonNum(tourGroup.getId());
		groupOrderDal.updateGroupPrice(tourGroup.getId());

	}

	@Transactional
	@Override
	public void addFitOrder(Integer groupId, Integer orderId) {
		GroupOrder groupOrder = groupOrderMapper.selectByPrimaryKey(orderId);
		groupOrder.setGroupId(groupId);
		groupOrderMapper.updateByPrimaryKeySelective(groupOrder);

		List<GroupRequirement> list = groupRequirementMapper.selectByOrderId(orderId);
		if (list != null && list.size() > 0) {
			for (GroupRequirement groupRequirement : list) {
				groupRequirement.setGroupId(groupId);
				groupRequirementMapper.updateByPrimaryKeySelective(groupRequirement);
			}
		}
		groupOrderDal.updateGroupPersonNum(groupId);
		TourGroup tourGroup = tourGroupMapper.selectByPrimaryKey(groupId);
		tourGroup.setOrderNum(tourGroup.getOrderNum() + 1);
		tourGroupMapper.updateByPrimaryKey(tourGroup);
		supplierMapper.updateGroupIdByOrderId(groupId, orderId);
	}

	@Override
	public Map<String, Object> selectByGroupOrderId(Integer orderId) {
		GroupOrder groupOrder = null;
		TourGroup tourGroup = null;
		groupOrder = groupOrderMapper.selectByPrimaryKey(orderId);
		tourGroup = tourGroupMapper.selectByPrimaryKey(groupOrder.getGroupId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupOrder", groupOrder);
		map.put("tourGroup", tourGroup);
		return map;
	}

	@Transactional
	@Override
	public GroupOrder insertSelective(TourGroup tourGroup, GroupOrder groupOrder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TourGroup groupCodeSort = tourGroupMapper.selectGroupCodeSort(groupOrder.getBizId(), 1,
				sdf.format(tourGroup.getDateStart()));
		tourGroup.setGroupCodeSort(groupCodeSort == null ? 1 : groupCodeSort.getGroupCodeSort() + 1);
		String makeCodeByMode = makeCodeByMode(tourGroup.getGroupCode(), 1, sdf.format(tourGroup.getDateStart()),
				tourGroup.getGroupCodeMark(), groupCodeSort == null ? 1 : groupCodeSort.getGroupCodeSort() + 1);
		tourGroup.setGroupCode(makeCodeByMode);
		tourGroupMapper.insertSelective(tourGroup);

		GroupOrder orderCodeSort = groupOrderMapper.selectGroupOrderCodeSort(tourGroup.getBizId(),
				sdf.format(tourGroup.getDateStart()));
		groupOrder.setOrderNoSort(orderCodeSort == null ? 1 : orderCodeSort.getOrderNoSort() + 1);

		makeCodeByMode = GenerateCodeUtil.makeCodeByMode(groupOrder.getBizId(), groupOrder.getOrderNo(),
				sdf.format(tourGroup.getDateStart()), orderCodeSort == null ? 1 : orderCodeSort.getOrderNoSort() + 1);
		groupOrder.setOrderNo(makeCodeByMode);
		groupOrder.setOrderNoSort(orderCodeSort == null ? 1 : orderCodeSort.getOrderNoSort() + 1);
		groupOrder.setGroupId(tourGroup.getId()); // 组团ID
		groupOrder.setNumAdult(tourGroup.getTotalAdult());
		groupOrder.setNumChild(tourGroup.getTotalChild());
		groupOrder.setNumGuide(tourGroup.getTotalGuide());
		groupOrder.setDepartureDate(DateUtils.format(tourGroup.getDateStart(), DateUtils.FORMAT_SHORT)); // 订单里面的开始日期和旅行团的开始日期一致
		groupOrderMapper.insertSelective(groupOrder);

		return groupOrder;
	}

	@Transactional
	@Override
	public GroupOrder insertSelective(TourGroup tourGroup, GroupOrder groupOrder, Integer groupId, Integer userId,
			String userName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TourGroup groupCodeSort = tourGroupMapper.selectGroupCodeSort(groupOrder.getBizId(), 1,
				sdf.format(tourGroup.getDateStart()));
		tourGroup.setGroupCodeSort(groupCodeSort == null ? 1 : groupCodeSort.getGroupCodeSort() + 1);
		String makeCodeByMode = makeCodeByMode(tourGroup.getGroupCode(), 1, sdf.format(tourGroup.getDateStart()),
				tourGroup.getGroupCodeMark(), groupCodeSort == null ? 1 : groupCodeSort.getGroupCodeSort() + 1);
		tourGroup.setGroupCode(makeCodeByMode);
		tourGroupMapper.insertSelective(tourGroup);

		GroupOrder orderCodeSort = groupOrderMapper.selectGroupOrderCodeSort(tourGroup.getBizId(),
				sdf.format(tourGroup.getDateStart()));
		groupOrder.setOrderNoSort(orderCodeSort == null ? 1 : orderCodeSort.getOrderNoSort() + 1);

		makeCodeByMode = GenerateCodeUtil.makeCodeByMode(groupOrder.getBizId(), groupOrder.getOrderNo(),
				sdf.format(tourGroup.getDateStart()), orderCodeSort == null ? 1 : orderCodeSort.getOrderNoSort() + 1);
		groupOrder.setOrderNo(makeCodeByMode);
		groupOrder.setOrderNoSort(orderCodeSort == null ? 1 : orderCodeSort.getOrderNoSort() + 1);
		groupOrder.setGroupId(tourGroup.getId()); // 组团ID
		groupOrder.setNumAdult(tourGroup.getTotalAdult());
		groupOrder.setNumChild(tourGroup.getTotalChild());
		groupOrder.setNumGuide(tourGroup.getTotalGuide());
		groupOrder.setDepartureDate(DateUtils.format(tourGroup.getDateStart(), DateUtils.FORMAT_SHORT)); // 订单里面的开始日期和旅行团的开始日期一致
		groupOrderMapper.insertSelective(groupOrder);

		List<BookingGuide> guides = guideMapper.selectListByGroupId2(groupId);
		for (BookingGuide bookingGuide : guides) {
			List<BookingGuideTimes> bookingGuideTimes = bookingGuideTimesMapper
					.selectListByGroupId(bookingGuide.getId());
			bookingGuide.setId(null);
			bookingGuide.setCreateTime(System.currentTimeMillis());
			bookingGuide.setGroupId(tourGroup.getId());
			bookingGuide.setUserId(userId);
			bookingGuide.setUserName(userName);
			bookingGuide.setTotal(null);
			bookingGuide.setStateBooking(null);
			bookingGuide.setStateFinance(null);
			guideMapper.insertSelective(bookingGuide);
			for (BookingGuideTimes guideTimes : bookingGuideTimes) {
				guideTimes.setId(null);
				guideTimes.setBookingId(bookingGuide.getId());
				bookingGuideTimesMapper.insertSelective(guideTimes);
			}
		}
		return groupOrder;
	}

	@Transactional
	@Override
	public GroupOrder updateByPrimaryKeySelective(TourGroup tourGroup, GroupOrder groupOrder) {
		tourGroup.setId(groupOrder.getGroupId());
		TourGroup tourGroup2 = tourGroupMapper.selectByPrimaryKey(tourGroup.getId());
		if (tourGroup.getDateStart().compareTo(tourGroup2.getDateStart()) != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			TourGroup groupCodeSort = tourGroupMapper.selectGroupCodeSort(tourGroup2.getBizId(), 1,
					sdf.format(tourGroup.getDateStart()));
			tourGroup.setGroupCodeSort(groupCodeSort == null ? 1 : groupCodeSort.getGroupCodeSort() + 1);
			String makeCodeByMode = makeCodeByMode(tourGroup.getGroupCode(), 1, sdf.format(tourGroup.getDateStart()),
					tourGroup.getGroupCodeMark(), groupCodeSort == null ? 1 : groupCodeSort.getGroupCodeSort() + 1);
			tourGroup.setGroupCode(makeCodeByMode);
		}

		updateByPrimaryKeySelective(tourGroup);
		// updateByPrimaryKey(tourGroup);

		List<GroupRoute> list = groupRouteMapper.selectByGroupId(groupOrder.getGroupId());
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				GroupRoute groupRoute = list.get(i);
				Date startTime = tourGroup.getDateStart();
				Calendar cal = Calendar.getInstance();
				cal.setTime(startTime);
				cal.add(Calendar.DAY_OF_MONTH, +i);
				Date time = cal.getTime();
				groupRoute.setGroupDate(time);
				groupRouteMapper.updateByPrimaryKey(groupRoute);
			}
		}

		if (tourGroup.getDateStart().compareTo(tourGroup2.getDateStart()) != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			GroupOrder orderCodeSort = groupOrderMapper.selectGroupOrderCodeSort(tourGroup.getBizId(),
					sdf.format(tourGroup.getDateStart()));
			groupOrder.setOrderNoSort(orderCodeSort == null ? 1 : orderCodeSort.getOrderNoSort() + 1);

			String makeCodeByMode = GenerateCodeUtil.makeCodeByMode(groupOrder.getBizId(), groupOrder.getOrderNo(),
					sdf.format(tourGroup.getDateStart()),
					orderCodeSort == null ? 1 : orderCodeSort.getOrderNoSort() + 1);
			groupOrder.setOrderNo(makeCodeByMode);
			groupOrder.setOrderNoSort(orderCodeSort == null ? 1 : orderCodeSort.getOrderNoSort() + 1);
		}
		groupOrder.setNumAdult(tourGroup.getTotalAdult());
		groupOrder.setNumChild(tourGroup.getTotalChild());
		groupOrder.setNumGuide(tourGroup.getTotalGuide());
		groupOrder.setDepartureDate(DateUtils.format(tourGroup.getDateStart(), DateUtils.FORMAT_SHORT)); // 订单里面的开始日期和旅行团的开始日期一致
		groupOrderMapper.updateByPrimaryKeySelective(groupOrder);
		groupOrder = groupOrderMapper.selectByPrimaryKey(groupOrder.getId());
		return groupOrder;
	}

	@Override
	public TourGroupPriceAndPersons selectTourGroupInfo(Integer groupId) {
		TourGroupPriceAndPersons tourGroupPriceAndPersons = new TourGroupPriceAndPersons();
		List<GroupOrder> groupOrders = groupOrderMapper.selectOrderByGroupId(groupId);
		Integer totalAdult = 0; // 成人数
		Integer totalChild = 0; // 小孩数
		Integer totalGuide = 0; // 全陪数
		Double costTotalPrice = (double) 0; // 成本
		Double incomeIncome = (double) 0; // 收入
		for (GroupOrder groupOrder : groupOrders) {
			totalAdult += groupOrder.getNumAdult();
			totalChild += groupOrder.getNumChild();
			if (null != groupOrder.getNumGuide()) {
				totalGuide += groupOrder.getNumGuide();
			}
			if (null != groupOrder.getTotal()) {
				incomeIncome += groupOrder.getTotal().doubleValue();
			}
			List<GroupOrderPrice> groupOrderPrices = groupOrderPriceMapper.selectByOrderAndType(groupOrder.getId(), 1);
			for (GroupOrderPrice groupOrderPrice : groupOrderPrices) {
				if (null != groupOrderPrice.getTotalPrice()) {
					costTotalPrice += groupOrderPrice.getTotalPrice();
				}
			}
		}
		tourGroupPriceAndPersons.setTotalAdult(totalAdult);
		tourGroupPriceAndPersons.setTotalChild(totalChild);
		tourGroupPriceAndPersons.setTotalGuide(totalGuide);
		tourGroupPriceAndPersons.setIncomeIncome(incomeIncome);
		tourGroupPriceAndPersons.setCostTotalPrice(costTotalPrice);
		return tourGroupPriceAndPersons;
	}

	@Override
	public List<String> selectGroupRequirementByGroupId(Integer groupId, Integer type) {
		List<String> list = new ArrayList<String>();
		List<GroupOrder> orders = groupOrderDal.selectOrderByGroupId(groupId);
		StringBuilder sb = null;
		for (GroupOrder order : orders) {
			sb = new StringBuilder();
			sb.append(order.getReceiveMode() + "  ");
			StringBuilder sbb = null;
			List<GroupRequirement> grs = groupRequirementMapper.selectGroupRequirementByGroupId(order.getId(), type);
			for (int i = 0; i < grs.size(); i++) {
				sbb = new StringBuilder();
				String sr = "";
				String dr = "";
				String tr = "";
				String ex = "";
				String pf = "";
				if (grs.get(i).getCountSingleRoom() != null && !grs.get(i).getCountSingleRoom().equals(0)) {
					sr = grs.get(i).getCountSingleRoom() + "单间" + " ";
				}
				if (grs.get(i).getCountDoubleRoom() != null && !grs.get(i).getCountDoubleRoom().equals(0)) {
					dr = grs.get(i).getCountDoubleRoom() + "标间" + " ";
				}
				if (grs.get(i).getCountTripleRoom() != null && !grs.get(i).getCountTripleRoom().equals(0)) {
					tr = grs.get(i).getCountTripleRoom() + "三人间" + " ";
				}
				if (grs.get(i).getPeiFang() != null && !grs.get(i).getPeiFang().equals(0)) {
					pf = grs.get(i).getPeiFang() + "陪房" + " ";
				}
				if (grs.get(i).getExtraBed() != null && !grs.get(i).getExtraBed().equals(0)) {
					ex = grs.get(i).getExtraBed() + "加床";
				}
				if ((i + 1) == grs.size()) {
					sbb.append(sr + dr + tr + pf + ex);
				} else {
					sbb.append(sr + dr + tr + pf + ex + "/");
				}
				sb.append(sbb.toString());
			}
			list.add(sb.toString());
		}
		return list;
	}

	/**
	 * 获取审核人列表
	 */
	@Override
	public List<TourGroup> getAuditorList() {
		return tourGroupMapper.getAuditorList();
	}

	@Override
	public PageBean getGroupInfoList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		List<BookingGroup> bookingGroups = null;

		bookingGroups = tourGroupMapper.selectGroupListPage(pageBean, tourGroupVo.getBizId(), set);

		getBookingDetails(tourGroupVo, bookingGroups);
		pageBean.setResult(bookingGroups);

		return pageBean;
	}

	private void getBookingDetails(TourGroupVO tourGroupVo, List<BookingGroup> bookingGroups) {
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bGroup : bookingGroups) {
				List<BookingGuide> bookingGuides = guideMapper.selectListByGroupIdAndName(bGroup.getGroupId(),
						tourGroupVo.getGuideName());
				bGroup.setGuideList(bookingGuides);
				List<BookingSupplierPO> bookingSuppliers = supplierMapper.getBookingSupplierPOByGroupIdAndTypeAndName(
						bGroup.getGroupId(), tourGroupVo.getSupplierType(), tourGroupVo.getSupplierName(),
						tourGroupVo.getDriverName(), tourGroupVo.getCarLisence());
				bGroup.setBookingSuppliers(bookingSuppliers);
				List<BookingSupplierDetail> details = detailMapper.getListByGroupIdAndType(bGroup.getGroupId(),
						Constants.FLEET);
				bGroup.setBookingSupplierDetails(details);
				List<BookingDelivery> deliveries = deliveryMapper.getDeliveryListByGroupIdAndName(bGroup.getGroupId(),
						tourGroupVo.getSupplierName());
				bGroup.setBookingDeliveries(deliveries);
				/*
				 * Map<String, Integer> personCountMap = groupOrderMapper
				 * .selectOrderByGroupIdAndName(bGroup.getGroupId(),
				 * tourGroupVo.getBizId());
				 */
				TourGroup group = tourGroupMapper.selectByPrimaryKey(bGroup.getGroupId());
				// bGroup.setPersonCountMap(personCountMap);
				bGroup.setTourGroup(group);
			}
		}
	}

	@Override
	@Transactional
	public void copyGroup(TourGroup tourGroup, GroupOrder groupOrder, Integer groupId, Integer orderId, String info,
			Integer userId, String userName) {
		insertSelective(tourGroup, groupOrder, groupId, userId, userName);

		TourGroup group = tourGroupMapper.selectByPrimaryKey(groupId);

		List<GroupRoute> list = groupRouteMapper.selectByGroupId(groupId);

		List<GroupRoute> list2 = groupRouteMapper.selectByOrderId(orderId);
		if (list != null && list.size() > 0) {
			int i = 0;
			for (GroupRoute groupRoute : list) {
				Integer routeId = groupRoute.getId();
				groupRoute.setId(null);
				groupRoute.setGroupId(tourGroup.getId());
				groupRoute.setOrderId(groupOrder.getId());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(tourGroup.getDateStart());
				calendar.add(Calendar.DAY_OF_MONTH, i);
				Date date = calendar.getTime();
				groupRoute.setGroupDate(date);
				groupRouteMapper.insert(groupRoute);
				i++;

				List<GroupRouteTraffic> listTra = groupRouteDal.selectGroupRouteTrafficByRouteId(routeId);
				if (listTra != null && listTra.size() > 0) {
					for (GroupRouteTraffic groupRouteTraffic : listTra) {
						groupRouteTraffic.setId(null);
						groupRouteTraffic.setRouteId(groupRoute.getId());
						groupRouteTrafficMapper.insert(groupRouteTraffic);
					}
				}
				List<GroupRouteSupplier> listSup = groupRouteDal.selectGroupRouteSupplierByRouteId(routeId);
				if (listSup != null && listSup.size() > 0) {
					for (GroupRouteSupplier groupRouteSupplier : listSup) {
						groupRouteSupplier.setId(null);
						groupRouteSupplier.setRouteId(groupRoute.getId());
						groupRouteSupplierMapper.insert(groupRouteSupplier);
					}
				}
				List<GroupRouteAttachment> listAtt = groupRouteAttachmentMapper.selectByRouteId(routeId);
				if (listAtt != null && listAtt.size() > 0) {
					for (GroupRouteAttachment groupRouteAttachment : listAtt) {
						groupRouteAttachment.setId(null);
						groupRouteAttachment.setObjId(groupRoute.getId());
						groupRouteAttachmentMapper.insert(groupRouteAttachment);
					}
				}

			}
		}

		if (info != null && !"".equals(info)) {
			String[] ids = info.split(",");
			for (String id : ids) {

				// 3 接送信息
				// 4 计调需求
				// 5 价格信息
				// 6 客人名单

				switch (Integer.parseInt(id)) {
				case 3:
					List<GroupOrderTransport> listTrans = groupOrderTransportMapper.selectByOrderId(orderId);
					if (listTrans != null && listTrans.size() > 0) {
						for (GroupOrderTransport groupOrderTransport : listTrans) {
							groupOrderTransport.setId(null);
							groupOrderTransport.setOrderId(groupOrder.getId());
							groupOrderTransportMapper.insertSelective(groupOrderTransport);
						}
					}
					break;
				case 4:
					List<GroupRequirement> listReq = groupRequirementMapper.selectByOrderId(orderId);
					if (listReq != null && listReq.size() > 0) {
						for (GroupRequirement groupRequirement : listReq) {
							groupRequirement.setId(null);
							groupRequirement.setOrderId(groupOrder.getId());
							groupRequirement.setGroupId(tourGroup.getId());
							groupRequirementMapper.insert(groupRequirement);
						}
					}
					break;
				case 5:
					List<GroupOrderPrice> listPrice = groupOrderPriceMapper.selectAllPriceByOrder(orderId);
					if (listPrice != null && listPrice.size() > 0) {
						for (GroupOrderPrice groupOrderPrice : listPrice) {
							groupOrderPrice.setId(null);
							groupOrderPrice.setOrderId(groupOrder.getId());
							groupOrderPriceMapper.insert(groupOrderPrice);
							tourGroup.setTotalIncome(group.getTotalIncome());
							tourGroupMapper.updateByPrimaryKey(tourGroup);

						}
					}

					break;
				case 6:
					List<GroupOrderGuest> listGuest = groupOrderGuestMapper.selectByOrderId(orderId);
					if (listGuest != null && listGuest.size() > 0) {
						for (GroupOrderGuest groupOrderGuest : listGuest) {
							groupOrderGuest.setId(null);
							groupOrderGuest.setOrderId(groupOrder.getId());
							groupOrderGuestMapper.insert(groupOrderGuest);
						}
					}
					break;
				default:
					break;
				}
			}

		}

	}

	@Override
	@Transactional
	public Integer changeGroup(Integer groupId, TourGroup tourGroup, String orderIds, Integer userId, String userName,
			String info) throws Exception {
		TourGroup group = tourGroupMapper.selectByPrimaryKey(groupId);
		List<GroupRoute> routeList = groupRouteMapper.selectByGroupId(groupId);
		GroupOrder teamOrder = null; // 定制团专用
		Calendar c = Calendar.getInstance();
		c.setTime(tourGroup.getDateStart());
		c.add(Calendar.DAY_OF_MONTH, group.getDaynum() - 1);
		Date t = c.getTime();
		tourGroup.setDateEnd(t);
		tourGroup.setBizId(group.getBizId());
		tourGroup.setGroupCodeSort(group.getGroupCodeSort());
		tourGroup.setPrudctBrandId(group.getPrudctBrandId());
		tourGroup.setProductBrandName(group.getProductBrandName());
		tourGroup.setProductId(group.getProductId());
		tourGroup.setProductName(group.getProductName());
		tourGroup.setGroupMode(group.getGroupMode());
		tourGroup.setServiceStandard(group.getServiceStandard());
		tourGroup.setRemark(group.getRemark());
		tourGroup.setNotice(group.getNotice());
		tourGroup.setOtherNotice(group.getOtherNotice());
		tourGroup.setWarmNotice(group.getWarmNotice());
		tourGroup.setOperatorId(group.getOperatorId());
		tourGroup.setOperatorName(group.getOperatorName());
		tourGroup.setOrderNum(group.getOrderNum());
		tourGroup.setGroupState(1);
		tourGroup.setDaynum(routeList == null ? 0 : routeList.size());
		tourGroup.setTotalAdult(0);
		tourGroup.setTotalChild(0);
		tourGroup.setTotalGuide(0);
		tourGroup.setCreateTime(System.currentTimeMillis());
		tourGroupMapper.insert(tourGroup);

		if (group.getGroupMode() > 0) { // 定制团
			GroupOrder groupOrder = groupOrderMapper.selectOrderByGroupId(groupId).get(0);
			GroupOrder go = new GroupOrder();
			go.setGroupId(tourGroup.getId());
			go.setNumAdult(0);
			go.setNumChild(0);
			go.setNumGuide(0);
			go.setDepartureDate(new SimpleDateFormat("yyyy-MM-dd").format(tourGroup.getDateStart()));
			go.setServiceStandard(tourGroup.getServiceStandard());
			go.setRemark(tourGroup.getRemark());
			go.setBizId(tourGroup.getBizId());
			go.setOrderType(1);
			go.setSupplierId(tourGroup.getSupplierId());
			go.setSupplierName(tourGroup.getSupplierName());
			go.setOrderNo(groupOrder.getOrderNo());
			go.setOrderNoSort(groupOrder.getOrderNoSort());
			go.setSupplierCode(groupOrder.getSupplierCode());
			go.setContactName(groupOrder.getContactName());
			go.setContactMobile(groupOrder.getContactMobile());
			go.setContactFax(groupOrder.getContactFax());
			go.setContactTel(groupOrder.getContactTel());
			go.setOperatorId(groupOrder.getOperatorId());
			go.setOperatorName(groupOrder.getOperatorName());
			go.setSaleOperatorId(groupOrder.getSaleOperatorId());
			go.setSaleOperatorName(groupOrder.getSaleOperatorName());
			go.setProductName(groupOrder.getProductName());
			go.setProductId(groupOrder.getProductId());
			go.setProductBrandId(groupOrder.getProductBrandId());
			go.setProductBrandName(groupOrder.getProductBrandName());
			go.setProductShortName(groupOrder.getProductShortName());
			go.setReceiveMode(groupOrder.getReceiveMode());
			go.setState(1);
			go.setPriceId(groupOrder.getPriceId());
			go.setSourceTypeId(groupOrder.getSourceTypeId());
			go.setSourceTypeName(groupOrder.getSourceTypeName());
			go.setProvinceId(groupOrder.getProvinceId());
			go.setProvinceName(groupOrder.getProvinceName());
			go.setCityId(groupOrder.getCityId());
			go.setCityName(groupOrder.getCityName());
			go.setOrderLockState(0);
			go.setCreateTime(System.currentTimeMillis());
			groupOrderMapper.insert(go);
			teamOrder = go;

		} else {
			String[] split = orderIds.split(",");
			for (int i = 0; i < split.length; i++) {

				GroupOrder groupOrder = groupOrderMapper.selectByPrimaryKey(Integer.parseInt(split[i]));
				GroupOrder go = new GroupOrder();
				go.setGroupId(tourGroup.getId());
				go.setNumAdult(0);
				go.setNumChild(0);
				go.setNumGuide(0);
				go.setDepartureDate(new SimpleDateFormat("yyyy-MM-dd").format(tourGroup.getDateStart()));
				go.setServiceStandard(tourGroup.getServiceStandard());
				go.setRemark(tourGroup.getRemark());
				go.setBizId(tourGroup.getBizId());
				go.setOrderType(group.getGroupMode());
				go.setSupplierId(groupOrder.getSupplierId());
				go.setSupplierName(groupOrder.getSupplierName());
				go.setOrderNo(groupOrder.getOrderNo());
				go.setOrderNoSort(groupOrder.getOrderNoSort());
				go.setSupplierCode(groupOrder.getSupplierCode());
				go.setContactName(groupOrder.getContactName());
				go.setContactMobile(groupOrder.getContactMobile());
				go.setContactFax(groupOrder.getContactFax());
				go.setContactTel(groupOrder.getContactTel());
				go.setOperatorId(groupOrder.getOperatorId());
				go.setOperatorName(groupOrder.getOperatorName());
				go.setSaleOperatorId(groupOrder.getSaleOperatorId());
				go.setSaleOperatorName(groupOrder.getSaleOperatorName());
				go.setProductName(groupOrder.getProductName());
				go.setProductId(groupOrder.getProductId());
				go.setProductBrandId(groupOrder.getProductBrandId());
				go.setProductBrandName(groupOrder.getProductBrandName());
				go.setProductShortName(groupOrder.getProductShortName());
				go.setReceiveMode(groupOrder.getReceiveMode());
				go.setState(1);
				go.setPriceId(groupOrder.getPriceId());
				go.setSourceTypeId(groupOrder.getSourceTypeId());
				go.setSourceTypeName(groupOrder.getSourceTypeName());
				go.setProvinceId(groupOrder.getProvinceId());
				go.setProvinceName(groupOrder.getProvinceName());
				go.setCityId(groupOrder.getCityId());
				go.setCityName(groupOrder.getCityName());
				go.setOrderLockState(0);
				go.setCreateTime(System.currentTimeMillis());
				groupOrderMapper.insert(go);

				List<GroupRoute> orderRouteList = groupRouteMapper.selectByOrderId(groupOrder.getId());
				if (routeList != null) {
					int j = 0;
					for (GroupRoute groupRoute : orderRouteList) {

						Integer routeId = groupRoute.getId();
						groupRoute.setId(null);
						groupRoute.setOrderId(go.getId());
						groupRoute.setGroupId(null);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(tourGroup.getDateStart());
						calendar.add(Calendar.DAY_OF_MONTH, j);
						Date date = calendar.getTime();
						groupRoute.setGroupDate(date);
						groupRouteMapper.insert(groupRoute);
						j++;
						List<GroupRouteTraffic> listTra = groupRouteDal.selectGroupRouteTrafficByRouteId(routeId);
						if (listTra != null && listTra.size() > 0) {
							for (GroupRouteTraffic groupRouteTraffic : listTra) {
								groupRouteTraffic.setId(null);
								groupRouteTraffic.setRouteId(groupRoute.getId());
								groupRouteTrafficMapper.insert(groupRouteTraffic);
							}
						}
						List<GroupRouteSupplier> listSup = groupRouteDal.selectGroupRouteSupplierByRouteId(routeId);
						if (listSup != null && listSup.size() > 0) {
							for (GroupRouteSupplier groupRouteSupplier : listSup) {
								groupRouteSupplier.setId(null);
								groupRouteSupplier.setRouteId(groupRoute.getId());
								groupRouteSupplierMapper.insert(groupRouteSupplier);
							}
						}
						List<GroupRouteAttachment> listAtt = groupRouteAttachmentMapper.selectByRouteId(routeId);
						if (listAtt != null && listAtt.size() > 0) {
							for (GroupRouteAttachment groupRouteAttachment : listAtt) {
								groupRouteAttachment.setId(null);
								groupRouteAttachment.setObjId(groupRoute.getId());
								groupRouteAttachmentMapper.insert(groupRouteAttachment);
							}
						}
					}

				}

			}
			tourGroup.setOrderNum(split.length);
			tourGroupMapper.updateByPrimaryKeySelective(tourGroup);
		}

		if ("3".equals(info)) {
			// 复制导游信息
			List<BookingGuide> guides = guideMapper.selectListByGroupId2(groupId);
			for (BookingGuide bookingGuide : guides) {
				List<BookingGuideTimes> bookingGuideTimes = bookingGuideTimesMapper
						.selectListByGroupId(bookingGuide.getId());
				bookingGuide.setId(null);
				bookingGuide.setCreateTime(System.currentTimeMillis());
				bookingGuide.setGroupId(tourGroup.getId());
				// bookingGuide.setUserId(userId);
				// bookingGuide.setUserName(userName);
				bookingGuide.setTotal(null);
				bookingGuide.setStateBooking(null);
				bookingGuide.setStateFinance(null);
				guideMapper.insertSelective(bookingGuide);
				for (BookingGuideTimes guideTimes : bookingGuideTimes) {
					guideTimes.setId(null);
					guideTimes.setBookingId(bookingGuide.getId());
					bookingGuideTimesMapper.insertSelective(guideTimes);
				}
			}
		}
		if (routeList != null) {
			int i = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (GroupRoute groupRoute : routeList) {

				Integer routeId = groupRoute.getId();
				groupRoute.setId(null);
				groupRoute.setGroupId(tourGroup.getId());
				if (teamOrder != null) {
					groupRoute.setOrderId(teamOrder.getId());
				}
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(tourGroup.getDateStart());
				calendar.add(Calendar.DAY_OF_MONTH, i);
				Date date = calendar.getTime();
				groupRoute.setGroupDate(date);
				groupRouteMapper.insert(groupRoute);

				List<GroupRouteTraffic> listTra = groupRouteDal.selectGroupRouteTrafficByRouteId(routeId);
				if (listTra != null && listTra.size() > 0) {
					for (GroupRouteTraffic groupRouteTraffic : listTra) {
						groupRouteTraffic.setId(null);
						groupRouteTraffic.setRouteId(groupRoute.getId());
						groupRouteTrafficMapper.insert(groupRouteTraffic);
					}
				}
				List<GroupRouteSupplier> listSup = groupRouteDal.selectGroupRouteSupplierByRouteId(routeId);
				if (listSup != null && listSup.size() > 0) {
					for (GroupRouteSupplier groupRouteSupplier : listSup) {
						groupRouteSupplier.setId(null);
						groupRouteSupplier.setRouteId(groupRoute.getId());
						groupRouteSupplierMapper.insert(groupRouteSupplier);
					}
				}
				List<GroupRouteAttachment> listAtt = groupRouteAttachmentMapper.selectByRouteId(routeId);
				if (listAtt != null && listAtt.size() > 0) {
					for (GroupRouteAttachment groupRouteAttachment : listAtt) {
						groupRouteAttachment.setId(null);
						groupRouteAttachment.setObjId(groupRoute.getId());
						groupRouteAttachmentMapper.insert(groupRouteAttachment);
					}
				}
				i++;
			}

		}
		return tourGroup.getId();
	}

	@Override
	public PageBean<TourGroup> selectProfitByTourConListPage(PageBean<TourGroup> pageBean, Integer bizId,
			Set<Integer> set) {
		List<TourGroup> tours = tourGroupMapper.selectProfitByTourConListPage(pageBean, bizId, set);
		for (TourGroup tour : tours) {
			if (tour != null) {
				if (tour.getId() != null) {
					tour.setBudget(tourGroupMapper.selectProfitByModeAndTourId(tour.getId(), 1));
					tour.setTotal(tourGroupMapper.selectProfitByModeAndTourId(tour.getId(), 0));
				}
			}
		}
		
		//solr
		
		
		
		pageBean.setResult(tours);
		return pageBean;
	}

	@Override
	public List<TourGroup> selectTourGroupBycreateTime(Long createTime, Integer bizId) {
		return tourGroupMapper.selectTourGroupListByCreatetime(createTime, bizId);
	}

	@Override
	public List<TourGroup> selectTourGroupLikeByContent(Long createTime, String startTime, String endTime,
			String content) {
		return tourGroupMapper.selectTourGroupLikeByContent(createTime, startTime, endTime, content);
	}

	@Override
	public TourGroup selectByGroupCode(String code) {
		return tourGroupMapper.selectByGroupCode(code);
	}

	@Override
	public List<Map<String, Object>> selectAgeList(Map conditionmMap) {
		return tourGroupMapper.selectAgeList(conditionmMap);
	}

	@Override
	public List<Map<String, Object>> selectAgeListByAgenctAndProduct(Map conditionmMap) {
		return tourGroupMapper.selectAgeListByAgencyAndProduct(conditionmMap);
	}

	@Override
	public List<Map<String, Object>> selectAgeCount(Map conditionmMap) {
		return tourGroupMapper.selectAgeCount(conditionmMap);
	}

	@Override
	public List<Map<String, Object>> selectAgeCountWithAgency(Map conditionmMap) {
		return tourGroupMapper.selectAgeCountWithAgency(conditionmMap);
	}

	@Override
	public List<Map<String, String>> getAuditUserList(Integer bizId, String name) {
		return tourGroupMapper.getAuditUserList(bizId, name);
	}

	@Override
	public PageBean<TourGroup> selectProfitByTourCon(PageBean<TourGroup> pageBean, Integer bizId, Set<Integer> set) {
		List<TourGroup> tours = tourGroupMapper.selectProfitByTourCon(pageBean, bizId, set);
		pageBean.setResult(tours);
		return pageBean;
	}

	@Override
	public TourGroup selectProfitByTourConAndMode(PageBean<TourGroup> pageBean, Integer bizId, Set<Integer> set) {
		return tourGroupMapper.selectProfitByTourConAndMode(pageBean, bizId, set);
	}

	@Override
	public PageBean selectBookingShopListPage(PageBean pageBean, Integer bizId, Set<Integer> set) {
		List<BookingGroup> bookingGroups = tourGroupMapper.selectGroupBookingShopListPage(pageBean, bizId, set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public List<AutocompleteInfo> getSupplierNameList(String supplierType) {

		return null;
	}

	@Override
	public PageBean getLocalTravelAngencyGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		List<BookingGroup> bookingGroups = null;
		tourGroupVo.setSupplierType(Constants.LOCALTRAVEL);
		bookingGroups = tourGroupMapper.selectLocalTravleListPage(pageBean, tourGroupVo.getBizId(), set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getHotelGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		List<BookingGroup> bookingGroups = null;
		tourGroupVo.setSupplierType(Constants.HOTEL);
		bookingGroups = tourGroupMapper.selectHotelListPage(pageBean, tourGroupVo.getBizId(), set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getFleetGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		List<BookingGroup> bookingGroups = null;
		tourGroupVo.setSupplierType(Constants.FLEET);
		bookingGroups = tourGroupMapper.selectFleetListPage(pageBean, tourGroupVo.getBizId(), set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getRestaurantGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.RESTAURANT);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectRestrauntListPage(pageBean, tourGroupVo.getBizId(),
				set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getSightGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.SCENICSPOT);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectSightListPage(pageBean, tourGroupVo.getBizId(), set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getEntertaimentGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.ENTERTAINMENT);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectEntertainmentListPage(pageBean, tourGroupVo.getBizId(),
				set);

		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getGolfGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.GOLF);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectGolfListPage(pageBean, tourGroupVo.getBizId(), set);
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getAirTicketGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.AIRTICKETAGENT);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectAirTicketListPage(pageBean, tourGroupVo.getBizId(),
				set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getTrainTicketGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.TRAINTICKETAGENT);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectTrainTicketListPage(pageBean, tourGroupVo.getBizId(),
				set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getInsuranceGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.INSURANCE);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectInsuranceListPage(pageBean, tourGroupVo.getBizId(),
				set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getIncomeGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.OTHERINCOME);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectIncomeListPage(pageBean, tourGroupVo.getBizId(), set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getOutcomeGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.OTHEROUTCOME);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectOutcomeListPage(pageBean, tourGroupVo.getBizId(), set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getGuideGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.GUIDE);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectGuideListPage(pageBean, tourGroupVo.getBizId(), set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getGuideGroupList2(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.GUIDE);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectGuideListPage2(pageBean, tourGroupVo.getBizId(), set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getShopGroupList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		tourGroupVo.setSupplierType(Constants.SHOPPING);
		List<BookingGroup> bookingGroups = tourGroupMapper.selectShopListPage(pageBean, tourGroupVo.getBizId(), set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean getGroupOperateList(PageBean pageBean, TourGroupVO tourGroupVo, Set<Integer> set) {
		List<BookingGroup> bookingGroups = tourGroupMapper.selectGroupOperateListPage(pageBean, tourGroupVo.getBizId(),
				set);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public PageBean selectBookingFinanceShopGroupListPage(PageBean pageBean, Integer curBizId,
			Set<Integer> dataUserIdSet) {
		List<BookingGroup> bookingGroups = tourGroupMapper.selectBookingFinanceShopGroupListPage(pageBean, curBizId,
				dataUserIdSet);
		if (bookingGroups != null && bookingGroups.size() > 0) {
			for (BookingGroup bg : bookingGroups) {
				if (bg.getGroupMode().intValue() > 0) {
					List<Map<String, Object>> orderMap = groupOrderMapper.getOrderBreifInfoByGroupId(bg.getGroupId());
					if (orderMap != null && orderMap.size() > 0) {
						Map<String, Object> map = orderMap.get(0);
						bg.setOrderId(TypeUtils.castToInt(map.get("orderId")));
						bg.setSupplierName(TypeUtils.castToString(map.get("supplierName")));
					}
				} else {
					bg.setSupplierName("散客团");
				}
			}
		}
		pageBean.setResult(bookingGroups);
		return pageBean;
	}

	@Override
	public BookingGroup selectBookingFinanceShopGroupListPageSum(PageBean pageBean, Integer curBizId,
			Set<Integer> dataUserIdSet) {
		return tourGroupMapper.selectBookingFinanceShopGroupSum(pageBean, curBizId, dataUserIdSet);
	}

	/**
	 * 根据产品统计订单数和总人数
	 */
	@Override
	public Map<String, Integer> getOrderCountAndPersonCountByProduct(Map conditionMap) {
		return tourGroupMapper.getOrderCountAndPersonCountByProduct(conditionMap);
	}

	@Override
	public PageBean<OperatorGroupStatic> selectOperatorGroupStaticListPage(PageBean<OperatorGroupStatic> pageBean,
			Integer bizId, Set<Integer> set) {
		List<OperatorGroupStatic> list = tourGroupMapper.selectOperatorGroupStaticListPage(pageBean, bizId, set);
		pageBean.setResult(list);
		return pageBean;
	}

	@Override
	public OperatorGroupStatic selectOperatorGroupStaticCon(PageBean<OperatorGroupStatic> pageBean, Integer bizId,
			Set<Integer> set) {
		return tourGroupMapper.selectOperatorGroupStaticCon(pageBean, bizId, set);
	}

	@Override
	public TourGroup selectTotalSKGroup(TourGroup tourGroup, Integer bizId, Set<Integer> set) {
		// TODO Auto-generated method stub
		return tourGroupMapper.selectTotalSKGroup(tourGroup, bizId, set);
	}

	@Override
	public List<TourGroup> selectIdList(PageBean pageBean) {
		return tourGroupMapper.selectIdList(pageBean);
	}

	@Override
	public TourGroup selectInitGroupInfo(Integer bizId) {
		// TODO Auto-generated method stub
		return tourGroupMapper.selectInitGroupInfo(bizId);
	}

	/**
	 * 根据团id判断团是否能编辑（已审核或者封存后不能编辑）
	 */
	@Override
	public Boolean checkGroupCanEdit(Integer groupId) {
		Boolean groupCanEdit = true;
		Map<String, Object> map = tourGroupMapper.getGroupInfoById(groupId);
		if (map != null) {
			Integer groupState = TypeUtils.castToInt(map.get("group_state"));
			if (groupState.equals(BasicConstants.GROUP_STATE_AUDIT)
					|| groupState.equals(BasicConstants.GROUP_STATE_SEAL)) {
				groupCanEdit = false;
			}
		} else {
			groupCanEdit = false;
		}
		return groupCanEdit;
	}

	@Override
	public PageBean selectInitGroupList(PageBean pageBean) {
		List<TourGroup> list = tourGroupMapper.selectInitGroupListPage(pageBean);
		pageBean.setResult(list);
		return pageBean;
	}

	@Transactional
	@Override
	public void deleteInitGroupInfo(Integer groupId) {
		tourGroupMapper.deleteByPrimaryKey(groupId);
		groupOrderMapper.deleteByOrderId(groupId);

	}

	@Override
	public List<Map<String, Object>> getGroupHotelBooking(Map paramMap) {
		return tourGroupMapper.getGroupHotelBooking(paramMap);
		// return null;
	}

	@Override
	public PageBean<TourGroup> selectTourGroupToQueryListPage(PageBean<TourGroup> pageBean, Integer bizId,
			Set<Integer> set) {
		List<TourGroup> list = tourGroupMapper.selectTourGroupToQueryListPage(pageBean, bizId, set);
		pageBean.setResult(list);
		return pageBean;
	}

	@Override
	public TourGroup selectTourGroupToQueryCon(TourGroup tourGroup, Integer bizId, Set<Integer> set) {
		// TODO Auto-generated method stub
		return tourGroupMapper.selectTourGroupToQueryCon(tourGroup, bizId, set);
	}

	@Override
	public List<TourGroupComment> getTourGroupComments(Integer bizId, List<Integer> groupIds) {
		if (groupIds.size() == 0) {
			return null;
		}
		String strGroupIds = StringUtils.join(groupIds, ',');
		return tourGroupCommentMapper.getTourGroupComments(bizId, strGroupIds);
	}

	@Override
	public int updateTourGroupComment(Integer bizId, TourGroupComment comment) {
		List<Integer> groupIds = new ArrayList<Integer>();
		groupIds.add(comment.getGroupId());
		List<TourGroupComment> list = this.getTourGroupComments(bizId, groupIds);
		if (list.size() > 0) {
			tourGroupCommentMapper.deleteTourGroupComment(bizId, comment.getGroupId());
		}
		return tourGroupCommentMapper.insertTourGroupComment(bizId, comment);
	}

	@Override
	public List<Map<String, Object>> selectAgeCountWithAgencyOnly(Map conditionmMap) {
		return tourGroupMapper.selectAgeCountWithAgencyOnly(conditionmMap);
	}

	@Override
	public List<TourGroup> selectTravelRecordsByIdCard(String idCard, Integer bizId) {
		return tourGroupMapper.selectTravelRecordsByIdCard(idCard, bizId);
	}

	@Override
	public PageBean selectBookingProfitList(PageBean pageBean, Integer bizId, Set<Integer> set) {
		List<TourGroup> tourGroups = tourGroupMapper.selectBookingProfitListPage(pageBean, bizId, set);
		pageBean.setResult(tourGroups);
		return pageBean;
	}

	@Override
	public Map<String, Object> selectBookingProfitTotal(PageBean pageBean, Integer bizId, Set<Integer> set) {
		return tourGroupMapper.selectBookingProfitTotal(pageBean, bizId, set);
	}


	@Override
	public List<TourGroup> selectGroupByDateZone(String startTime,
			String endTime, Integer bizId) {
		// TODO Auto-generated method stub
		return tourGroupMapper.selectGroupByDateZone(startTime, endTime, bizId);
	}

	@Override
	public void changeGroup(Integer groupId, Integer guideId) {
		
	}
	
	@Override
	public List<TourGroup> selecGroupBefAutoMergerGroup(Integer bizId,String startTime,Integer productId) {
		List<TourGroup> list= tourGroupMapper.selecGroupBefAutoMergerGroup(bizId,startTime,productId);
		return list;
	}
}
