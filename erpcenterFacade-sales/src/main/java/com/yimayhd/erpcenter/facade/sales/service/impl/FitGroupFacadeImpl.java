package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yimayhd.erpcenter.facade.sales.query.*;
import com.yimayhd.erpcenter.facade.sales.result.ToFitGroupTableResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.FitGroupBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitGroupInfoVO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToSecImpNotGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.FitGroupInfoQueryResult;
import com.yimayhd.erpcenter.facade.sales.result.FitTotalSKGroupQueryResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToSecImpNotGroupListResult;
import com.yimayhd.erpcenter.facade.sales.service.FitGroupFacade;
import com.yimayhd.erpcenter.facade.sales.utils.DateUtils;

public class FitGroupFacadeImpl implements FitGroupFacade{
	
	@Autowired
	private DicBiz dicBiz;
	
	@Autowired
	private FitGroupBiz fitGroupBiz;
	
	@Autowired
	private TourGroupBiz tourGroupBiz;
	
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	
	@Autowired
	private FinanceBiz financeBiz;
	
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz ;
	
	@Autowired
	private PlatformOrgBiz platformOrgBiz;

	@SuppressWarnings("unchecked")
	@Override
	public FitTotalSKGroupQueryResult toFitGroupList(FitTotalSKGroupQueryDTO totalSKGroupQueryDTO) {
		
		TourGroup tourGroup = totalSKGroupQueryDTO.getTourGroup();
		Integer bizId = totalSKGroupQueryDTO.getBizId();
		Set<Integer> userIdSet = totalSKGroupQueryDTO.getUserIdSet();
		
		if (null == tourGroup.getStartTime() && null == tourGroup.getEndTime()) {
//			Calendar c = Calendar.getInstance();
//			int year = c.get(Calendar.YEAR);
//			int month = c.get(Calendar.MONTH);
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//			c.set(year, month, 1, 0, 0, 0);
//			tourGroup.setStartTime(c.getTime());
//			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
//			tourGroup.setEndTime(c.getTime());
//			Calendar c = Calendar.getInstance();
//			int year = c.get(Calendar.YEAR);
//			int month = c.get(Calendar.MONTH);
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//			c.set(year, month, 1, 0, 0, 0);
//			tourGroup.setStartTime(c.getTime());
//			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
//			tourGroup.setEndTime(c.getTime());
			tourGroup.setStartTime(DateUtils.fmt(DateUtils.getMonthFirstDay(), "yyyy-MM-dd"));
			tourGroup.setEndTime(DateUtils.fmt(DateUtils.getMonthLastDay(), "yyyy-MM-dd"));
		}
		
		if (null == tourGroup.getGroupState()) {
			tourGroup.setGroupState(-2);
		}
		
		PageBean<TourGroup> pageBean = new PageBean<TourGroup>();
		pageBean.setPageSize(tourGroup.getPageSize() == null ? Constants.PAGESIZE : tourGroup.getPageSize());
		pageBean.setPage(tourGroup.getPage());

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(tourGroup.getOperatorIds()) && StringUtils.isNotBlank(tourGroup.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = tourGroup.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String operatorIds = "";
			for (Integer usrId : set) {
				operatorIds += usrId + ",";
			}
			if (!operatorIds.equals("")) {
				tourGroup.setOperatorIds(operatorIds.substring(0,operatorIds.length() - 1));
			}
		}
		pageBean.setParameter(tourGroup);
		pageBean = tourGroupBiz.selectSKGroupListPage(pageBean,bizId,userIdSet);
		
		List<TourGroup> result = pageBean.getResult();
		if (result != null && result.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (TourGroup t : result) {
				if (t.getCreateTime() != null) {
					Long createTime = t.getCreateTime();
					String dateStr = sdf.format(createTime);
					t.setCreateTimeStr(dateStr);
				}
				if (t.getUpdateTime() != null) {
					Long updateTime = t.getUpdateTime();
					String dateStr = sdf.format(updateTime);
					t.setUpdateTimeStr(dateStr);
				} else {
					t.setUpdateTimeStr("无");
					t.setUpdateName("无");
				}
				List<BookingGuide> guideList = bookingGuideBiz.selectGuidesByGroupId(t.getId());
				t.setGuideList(guideList);
			}
		}
		
		TourGroup group = tourGroupBiz.selectTotalSKGroup(tourGroup,bizId,userIdSet);
		
		FitTotalSKGroupQueryResult totalSKGroupQueryResult=new FitTotalSKGroupQueryResult();
		totalSKGroupQueryResult.setGroup(group);
		totalSKGroupQueryResult.setTourGroup(tourGroup);
		totalSKGroupQueryResult.setPageBean(pageBean);
		totalSKGroupQueryResult.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(bizId));
		totalSKGroupQueryResult.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(bizId));
		
		return totalSKGroupQueryResult;
	}

	@Override
	public FitGroupInfoQueryResult toFitGroupInfo(FitGroupInfoQueryDTO fitGroupInfoQueryDTO) {
		
		FitGroupInfoQueryResult result=new FitGroupInfoQueryResult();
		
		result.setFitGroupInfoVO(fitGroupBiz.selectFitGroupInfoById(fitGroupInfoQueryDTO.getGroupId()));
		result.setPp(dicBiz.getListByTypeCode(fitGroupInfoQueryDTO.getTypeCode(),fitGroupInfoQueryDTO.getCurBizId()));
		
		return result;
	}
	
	@Override
	public void delFitOrderBatch(String ids) {
		String[] split = ids.split(",");
		for (int i = 0; i < split.length; i++) {
			tourGroupBiz.delFitOrder(Integer.parseInt(split[i]));
			bookingSupplierBiz.upateGroupIdAfterDelOrderFromGroup(Integer.parseInt(split[i]));
		}
	}
	
	@Override
	public void delFitOrder(Integer id) {
		tourGroupBiz.delFitOrder(id);
		bookingSupplierBiz.upateGroupIdAfterDelOrderFromGroup(id);
	}
	
	@Override
	public void updateFitGroupInfo(FitGroupInfoUpdateDTO fitGroupInfoUpdateDTO) {
		
		FitGroupInfoVO fitGroupInfoVO = fitGroupInfoUpdateDTO.getFitGroupInfoVO();
		TourGroup tourGroup = fitGroupInfoVO.getTourGroup();
		TourGroup group=tourGroupBiz.selectByPrimaryKey(tourGroup.getId());
		fitGroupBiz.updateFitGroupInfo(fitGroupInfoVO,fitGroupInfoUpdateDTO.getUserId(),fitGroupInfoUpdateDTO.getUserName());
		if(!group.getOperatorId().equals(tourGroup.getOperatorId())){
			List<GroupOrder> orderList = groupOrderBiz.selectOrderByGroupIdAndBizId(group.getId(), group.getBizId());
			if(orderList!=null){
				for (GroupOrder groupOrder : orderList) {
					groupOrder.setOperatorId(tourGroup.getOperatorId());
					groupOrder.setOperatorName(tourGroup.getOperatorName());
					groupOrderBiz.updateGroupOrder(groupOrder);
				}
			}
		}
	}
	
	@Override
	public BaseStateResult delFitTourGroup(Integer groupId) {
		
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(false);
		
		if(financeBiz.hasAuditOrder(groupId)){
			result.setError("该团有已审核的订单,不允许删除！");
		}else if(financeBiz.hasPayOrIncomeRecord(groupId)){
			result.setError("该团有收付款记录,不允许删除！");
		}else if(financeBiz.hasHotelOrder(groupId)){
			result.setError("该团有酒、车队订单,不允许删除！");
		}
		
		tourGroupBiz.delFitTourGroup(groupId);
		
		result.setSuccess(true);
		
		return result;
	}
	
	@Override
	public BaseStateResult updateFitTourGroup(FitUpdateTourGroupDTO fitUpdateTourGroupDTO) {
		
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(false);
		
		TourGroup tourGroup = fitUpdateTourGroupDTO.getTourGroup();
		if (tourGroup.getGroupState() == 2) {
			List<FinanceCommission> fc1 = groupOrderBiz.selectFinanceCommissionByGroupId(tourGroup.getId());
			if (fc1 != null && fc1.size() > 0) {
				result.setError("该团已有购物及佣金被审核！");
				return result;
			}
			List<FinanceCommission> fc2 = groupOrderBiz.selectFCByGroupId(tourGroup.getId());
			if (fc2 != null && fc2.size() > 0) {
				result.setError("该团已有购物及佣金被审核！");
				return result;
			}
		}
		
		tourGroupBiz.updateByPrimaryKeySelective(tourGroup);
		
		result.setSuccess(true);
		
		return result;
	}
	
	@Override
	public void addOrderToTourGroup(Integer groupId, String ids) {
		
		List<GroupOrder> glist = groupOrderBiz.selectOrderByGroupId(groupId);
		
		List<String> datelist = new ArrayList<String>();
		List<Integer> productlist = new ArrayList<Integer>();
		List<Integer> statelist = new ArrayList<Integer>();

		if (glist != null && glist.size() > 0) {
			datelist.add(glist.get(0).getDepartureDate());
		}
		
		String[] split = ids.split(",");
		for (String id : split) {
			datelist.add(groupOrderBiz.findById(Integer.parseInt(id)).getDepartureDate());
			productlist.add(groupOrderBiz.findById(Integer.parseInt(id)).getProductId());
			statelist.add(groupOrderBiz.findById(Integer.parseInt(id)).getStateFinance());
		}
		
		for (String str : split) {
			tourGroupBiz.addFitOrder(groupId, Integer.parseInt(str));
		}
	}
	
	public ToSecImpNotGroupListResult toSecImpNotGroupList(ToSecImpNotGroupListDTO toSecImpNotGroupListDTO){
		
		GroupOrder groupOrder = toSecImpNotGroupListDTO.getGroupOrder();
		Integer curBizId = toSecImpNotGroupListDTO.getCurBizId();
		Set<Integer> userIdSet = toSecImpNotGroupListDTO.getUserIdSet();
		
		if (groupOrder.getReqType() != null && groupOrder.getReqType() == 0) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			c.set(year, month, 1);
			groupOrder.setDepartureDate(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-01");
			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			groupOrder.setEndTime(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));
			groupOrder.setDateType(1);
		}
		
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
		pageBean.setPage(groupOrder.getPage() == null ? 1 : groupOrder.getPage());
		pageBean.setParameter(groupOrder);
		pageBean = groupOrderBiz.selectNotGroupListPage(pageBean, curBizId,userIdSet);
		//List<GroupOrder> result = pageBean.getResult();
		List<DicInfo> pp = dicBiz.getListByTypeCode(BasicConstants.CPXL_PP, curBizId);

		ToSecImpNotGroupListResult result=new ToSecImpNotGroupListResult();
		result.setGroupOrder(groupOrder);
		result.setPageBean(pageBean);
		result.setPp(pp);
		
		return result;
	}

	@Override
	public ToFitGroupTableResult toFitGroupTable(ToFitGroupTableDTO toFitGroupTableDTO) {
		ToFitGroupTableResult toFitGroupTableResult = new ToFitGroupTableResult();
		PageBean pageBean = new PageBean();
		if (toFitGroupTableDTO.getPage() == null) {
			pageBean.setPage(1);
		} else {
			pageBean.setPage(toFitGroupTableDTO.getPage());
		}
		if (toFitGroupTableDTO.getPageSize() == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(toFitGroupTableDTO.getRows());
		}

		if (null == toFitGroupTableDTO.getTourGroup().getStartTime() && null == toFitGroupTableDTO.getTourGroup().getEndTime()) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			c.set(year, month, 1, 0, 0, 0);
			toFitGroupTableDTO.getTourGroup().setStartTime(c.getTime());
			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			toFitGroupTableDTO.getTourGroup().setEndTime(c.getTime());

		}
		if (null == toFitGroupTableDTO.getTourGroup().getGroupState()) {
			toFitGroupTableDTO.getTourGroup().setGroupState(-2);
		}

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(toFitGroupTableDTO.getTourGroup().getOperatorIds())
				&& StringUtils.isNotBlank(toFitGroupTableDTO.getTourGroup().getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = toFitGroupTableDTO.getTourGroup().getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(
					toFitGroupTableDTO.getCurBizId(), set);
			String operatorIds = "";
			for (Integer usrId : set) {
				operatorIds += usrId + ",";
			}
			if (!operatorIds.equals("")) {
				toFitGroupTableDTO.getTourGroup().setOperatorIds(operatorIds.substring(0,
						operatorIds.length() - 1));
			}
		}
		pageBean.setParameter(toFitGroupTableDTO.getTourGroup());
		pageBean = tourGroupBiz.selectSKGroupListPage(pageBean,
				toFitGroupTableDTO.getCurBizId(),
				toFitGroupTableDTO.getUserIdSet());

		List<TourGroup> result = pageBean.getResult();
		if (result != null && result.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (TourGroup t : result) {
				if (t.getCreateTime() != null) {
					Long createTime = t.getCreateTime();
					String dateStr = sdf.format(createTime);
					t.setCreateTimeStr(dateStr);
				}
				if (t.getUpdateTime() != null) {
					Long updateTime = t.getUpdateTime();
					String dateStr = sdf.format(updateTime);
					t.setUpdateTimeStr(dateStr);
				} else {
					t.setUpdateTimeStr("无");
					t.setUpdateName("无");
				}
				List<BookingGuide> guideList = bookingGuideBiz.selectGuidesByGroupId(t.getId());
				t.setGuideList(guideList);
			}
		}
		toFitGroupTableResult.setPageBean(pageBean);
		return toFitGroupTableResult;
	}

	@Override
	public ToFitGroupTableResult toSelectTotalPerson(ToFitGroupTableDTO toFitGroupTableDTO) {
		ToFitGroupTableResult toFitGroupTableResult = new ToFitGroupTableResult();

		if (null == toFitGroupTableDTO.getTourGroup().getStartTime() && null == toFitGroupTableDTO.getTourGroup().getEndTime()) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			c.set(year, month, 1, 0, 0, 0);
			toFitGroupTableDTO.getTourGroup().setStartTime(c.getTime());
			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			toFitGroupTableDTO.getTourGroup().setEndTime(c.getTime());

		}
		if (null == toFitGroupTableDTO.getTourGroup().getGroupState()) {
			toFitGroupTableDTO.getTourGroup().setGroupState(-2);
		}

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(toFitGroupTableDTO.getTourGroup().getOperatorIds())
				&& StringUtils.isNotBlank(toFitGroupTableDTO.getTourGroup().getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = toFitGroupTableDTO.getTourGroup().getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(
					toFitGroupTableDTO.getCurBizId(), set);
			String operatorIds = "";
			for (Integer usrId : set) {
				operatorIds += usrId + ",";
			}
			if (!operatorIds.equals("")) {
				toFitGroupTableDTO.getTourGroup().setOperatorIds(operatorIds.substring(0,
						operatorIds.length() - 1));
			}
		}

		TourGroup group = tourGroupBiz.selectTotalSKGroup(toFitGroupTableDTO.getTourGroup(),
				toFitGroupTableDTO.getCurBizId(),
				toFitGroupTableDTO.getUserIdSet());
		toFitGroupTableResult.setGroup(group);
		return toFitGroupTableResult;
	}
}
