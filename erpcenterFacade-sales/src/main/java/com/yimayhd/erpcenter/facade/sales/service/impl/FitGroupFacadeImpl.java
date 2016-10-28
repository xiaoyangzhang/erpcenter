package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitGroupInfoVO;
import com.yimayhd.erpcenter.facade.sales.query.FitGroupInfoQueryDTO;
import com.yimayhd.erpcenter.facade.sales.query.FitGroupInfoUpdateDTO;
import com.yimayhd.erpcenter.facade.sales.query.FitTotalSKGroupQueryDTO;
import com.yimayhd.erpcenter.facade.sales.query.FitUpdateTourGroupDTO;
import com.yimayhd.erpcenter.facade.sales.query.grouporder.ToSecImpNotGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.FitGroupInfoQueryResult;
import com.yimayhd.erpcenter.facade.sales.result.FitTotalSKGroupQueryResult;
import com.yimayhd.erpcenter.facade.sales.result.grouporder.ToSecImpNotGroupListResult;
import com.yimayhd.erpcenter.facade.sales.service.FitGroupFacade;

public class FitGroupFacadeImpl implements FitGroupFacade{
	
	@Autowired
	private DicBiz dicService;
	
	@Autowired
	private FitGroupBiz fitGroupService;
	
	@Autowired
	private TourGroupBiz tourGroupService;
	
	@Autowired
	private GroupOrderBiz groupOrderService;
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeService;
	
	@Autowired
	private FinanceBiz financeService;
	
	@Autowired
	private BookingGuideBiz bookingGuideService;
	
	@Autowired
	private BookingSupplierBiz bookingSupplierService ;

	@SuppressWarnings("unchecked")
	@Override
	public FitTotalSKGroupQueryResult toFitGroupList(FitTotalSKGroupQueryDTO totalSKGroupQueryDTO) {
		
		TourGroup tourGroup = totalSKGroupQueryDTO.getTourGroup();
		Integer bizId = totalSKGroupQueryDTO.getBizId();
		Set<Integer> userIdSet = totalSKGroupQueryDTO.getUserIdSet();
		
		if (null == tourGroup.getStartTime() && null == tourGroup.getEndTime()) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			c.set(year, month, 1, 0, 0, 0);
			tourGroup.setStartTime(c.getTime());
			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			tourGroup.setEndTime(c.getTime());
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
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String operatorIds = "";
			for (Integer usrId : set) {
				operatorIds += usrId + ",";
			}
			if (!operatorIds.equals("")) {
				tourGroup.setOperatorIds(operatorIds.substring(0,operatorIds.length() - 1));
			}
		}
		pageBean.setParameter(tourGroup);
		pageBean = tourGroupService.selectSKGroupListPage(pageBean,bizId,userIdSet);
		
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
				List<BookingGuide> guideList = bookingGuideService.selectGuidesByGroupId(t.getId());
				t.setGuideList(guideList);
			}
		}
		
		TourGroup group = tourGroupService.selectTotalSKGroup(tourGroup,bizId,userIdSet);
		
		FitTotalSKGroupQueryResult totalSKGroupQueryResult=new FitTotalSKGroupQueryResult();
		totalSKGroupQueryResult.setGroup(group);
		totalSKGroupQueryResult.setTourGroup(tourGroup);
		totalSKGroupQueryResult.setPageBean(pageBean);
		
		return totalSKGroupQueryResult;
	}

	@Override
	public FitGroupInfoQueryResult toFitGroupInfo(FitGroupInfoQueryDTO fitGroupInfoQueryDTO) {
		
		FitGroupInfoQueryResult result=new FitGroupInfoQueryResult();
		
		result.setFitGroupInfoVO(fitGroupService.selectFitGroupInfoById(fitGroupInfoQueryDTO.getGroupId()));
		result.setPp(dicService.getListByTypeCode(fitGroupInfoQueryDTO.getTypeCode(),fitGroupInfoQueryDTO.getCurBizId()));
		
		return result;
	}
	
	@Override
	public void delFitOrderBatch(String ids) {
		String[] split = ids.split(",");
		for (int i = 0; i < split.length; i++) {
			tourGroupService.delFitOrder(Integer.parseInt(split[i]));
			bookingSupplierService.upateGroupIdAfterDelOrderFromGroup(Integer.parseInt(split[i]));
		}
	}
	
	@Override
	public void delFitOrder(Integer id) {
		tourGroupService.delFitOrder(id);
		bookingSupplierService.upateGroupIdAfterDelOrderFromGroup(id);
	}
	
	@Override
	public void updateFitGroupInfo(FitGroupInfoUpdateDTO fitGroupInfoUpdateDTO) {
		
		FitGroupInfoVO fitGroupInfoVO = fitGroupInfoUpdateDTO.getFitGroupInfoVO();
		TourGroup tourGroup = fitGroupInfoVO.getTourGroup();
		TourGroup group=tourGroupService.selectByPrimaryKey(tourGroup.getId());
		fitGroupService.updateFitGroupInfo(fitGroupInfoVO,fitGroupInfoUpdateDTO.getUserId(),fitGroupInfoUpdateDTO.getUserName());
		if(!group.getOperatorId().equals(tourGroup.getOperatorId())){
			List<GroupOrder> orderList = groupOrderService.selectOrderByGroupIdAndBizId(group.getId(), group.getBizId());
			if(orderList!=null){
				for (GroupOrder groupOrder : orderList) {
					groupOrder.setOperatorId(tourGroup.getOperatorId());
					groupOrder.setOperatorName(tourGroup.getOperatorName());
					groupOrderService.updateGroupOrder(groupOrder);
				}
			}
		}
	}
	
	@Override
	public BaseStateResult delFitTourGroup(Integer groupId) {
		
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(false);
		
		if(financeService.hasAuditOrder(groupId)){
			result.setError("该团有已审核的订单,不允许删除！");
		}else if(financeService.hasPayOrIncomeRecord(groupId)){
			result.setError("该团有收付款记录,不允许删除！");
		}else if(financeService.hasHotelOrder(groupId)){
			result.setError("该团有酒、车队订单,不允许删除！");
		}
		
		tourGroupService.delFitTourGroup(groupId);
		
		result.setSuccess(true);
		
		return result;
	}
	
	@Override
	public BaseStateResult updateFitTourGroup(FitUpdateTourGroupDTO fitUpdateTourGroupDTO) {
		
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(false);
		
		TourGroup tourGroup = fitUpdateTourGroupDTO.getTourGroup();
		if (tourGroup.getGroupState() == 2) {
			List<FinanceCommission> fc1 = groupOrderService.selectFinanceCommissionByGroupId(tourGroup.getId());
			if (fc1 != null && fc1.size() > 0) {
				result.setError("该团已有购物及佣金被审核！");
				return result;
			}
			List<FinanceCommission> fc2 = groupOrderService.selectFCByGroupId(tourGroup.getId());
			if (fc2 != null && fc2.size() > 0) {
				result.setError("该团已有购物及佣金被审核！");
				return result;
			}
		}
		
		tourGroupService.updateByPrimaryKeySelective(tourGroup);
		
		result.setSuccess(true);
		
		return result;
	}
	
	@Override
	public void addOrderToTourGroup(Integer groupId, String ids) {
		
		List<GroupOrder> glist = groupOrderService.selectOrderByGroupId(groupId);
		
		List<String> datelist = new ArrayList<String>();
		List<Integer> productlist = new ArrayList<Integer>();
		List<Integer> statelist = new ArrayList<Integer>();

		if (glist != null && glist.size() > 0) {
			datelist.add(glist.get(0).getDepartureDate());
		}
		
		String[] split = ids.split(",");
		for (String id : split) {
			datelist.add(groupOrderService.findById(Integer.parseInt(id)).getDepartureDate());
			productlist.add(groupOrderService.findById(Integer.parseInt(id)).getProductId());
			statelist.add(groupOrderService.findById(Integer.parseInt(id)).getStateFinance());
		}
		
		for (String str : split) {
			tourGroupService.addFitOrder(groupId, Integer.parseInt(str));
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
		pageBean = groupOrderService.selectNotGroupListPage(pageBean, curBizId,userIdSet);
		//List<GroupOrder> result = pageBean.getResult();
		List<DicInfo> pp = dicService.getListByTypeCode(BasicConstants.CPXL_PP, curBizId);

		ToSecImpNotGroupListResult result=new ToSecImpNotGroupListResult();
		result.setGroupOrder(groupOrder);
		result.setPageBean(pageBean);
		result.setPp(pp);
		
		return result;
	}
}
