package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.WebUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.FitGroupBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupComment;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitGroupInfoVO;
import com.yimayhd.erpcenter.facade.sales.errorcode.OperationErrorCode;
import com.yimayhd.erpcenter.facade.sales.query.AgencyOrderQueryDTO;
import com.yimayhd.erpcenter.facade.sales.result.AgencyOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.service.AgencyFitGroupFacade;
import com.yimayhd.erpcenter.facade.sales.utils.GroupCodeUtil;

public class AgencyFitGroupFacadeImpl implements AgencyFitGroupFacade {

	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private FinanceBiz financeBiz;
	@Autowired
	private FitGroupBiz fitGroupBiz;
	@Autowired
	private ProductInfoBiz productInfoBiz;
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	@Autowired
	private GroupRouteBiz groupRouteBiz;
	@Override
	public ResultSupport secMergeGroup(Integer groupId, String[] idArr) {
		ResultSupport resultSupport = new ResultSupport();
		List<GroupOrder> glist = groupOrderBiz
				.selectOrderByGroupId(groupId);
		List<String> datelist = new ArrayList<String>();
		List<Integer> productlist = new ArrayList<Integer>();
		List<Integer> statelist = new ArrayList<Integer>();
		if (glist != null && glist.size() > 0) {
			datelist.add(glist.get(0).getDepartureDate());
		}
		for (String id : idArr) {
			datelist.add(groupOrderBiz.findById(Integer.parseInt(id))
					.getDepartureDate());
			productlist.add(groupOrderBiz.findById(Integer.parseInt(id))
					.getProductId());
			statelist.add(groupOrderBiz.findById(Integer.parseInt(id))
					.getStateFinance());
			tourGroupBiz.addFitOrder(groupId, Integer.parseInt(id));
		}
		return resultSupport;
	}
	@Override
	public ResultSupport updateByPrimaryKeySelective(TourGroup tourGroup) {
		ResultSupport resultSupport = new ResultSupport();
		int updateResult = tourGroupBiz.updateByPrimaryKeySelective(tourGroup);
		if (updateResult < 1) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	@Override
	public ResultSupport delFitTour(Integer groupId) {
		ResultSupport resultSupport = new ResultSupport();
		if(financeBiz.hasAuditOrder(groupId)){
//			return errorJson("该团有已审核的订单,不允许删除！");
			resultSupport.setErrorCode(OperationErrorCode.UNALLOWED_DELETE_CHECKED_ORDER);
			return resultSupport;
		}
		
		if(financeBiz.hasPayOrIncomeRecord(groupId)){
//			return errorJson("该团有收付款记录,不允许删除！");
			resultSupport.setErrorCode(OperationErrorCode.UNALLOWED_DELETE_FINANCE_RECORD);
			return resultSupport;
		}
		tourGroupBiz.delFitTourGroup(groupId);
		return resultSupport;
	}
	@Override
	public ResultSupport updateFitGroupInfo(FitGroupInfoVO fitgroupVO,Integer userId,String userName) {
		ResultSupport resultSupport = new ResultSupport();

		TourGroup tourGroup = fitgroupVO.getTourGroup();
		TourGroup group=tourGroupBiz.selectByPrimaryKey(fitgroupVO.getTourGroup().getId());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		fitGroupBiz.updateFitGroupInfo(fitgroupVO,userId,userName);
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
		return resultSupport;
	}
	@Override
	public ResultSupport delFitOrder(Integer groupId) {
		ResultSupport resultSupport = new ResultSupport();
		tourGroupBiz.delFitOrder(groupId);
		return resultSupport;
	}
	@Override
	public FitGroupInfoVO selectFitGroupInfoById(Integer groupId) {
		FitGroupInfoVO fitGroupInfoVO = fitGroupBiz.selectFitGroupInfoById(groupId);
		return fitGroupInfoVO;
	}
	@Override
	public AgencyOrderResult toFitGroupList(AgencyOrderQueryDTO queryDTO) {
		AgencyOrderResult result = new AgencyOrderResult();
		PageBean pageBean = tourGroupBiz.selectSKGroupListPage(queryDTO.getPageBean(),queryDTO.getBizId(),queryDTO.getSet());
		List<TourGroup> queryResult = pageBean.getResult();
		if (!CollectionUtils.isEmpty(queryResult)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (TourGroup t : queryResult) {
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
				
		result.setPageBean(pageBean);
		TourGroup group = tourGroupBiz.selectTotalSKGroup(queryDTO.getTourGroup(),queryDTO.getBizId(),queryDTO.getSet());
		result.setTourGroup(group);
		return result;
	}
	@Override
	public AgencyOrderResult toEditGroupComment(Integer bizId,
			List<Integer> groupIds) {
		AgencyOrderResult result = new AgencyOrderResult();
		TourGroup g = tourGroupBiz.selectByPrimaryKey(groupIds.get(0));
		result.setTourGroup(g);
		List<TourGroupComment> commentList = tourGroupBiz.getTourGroupComments(bizId, groupIds);
		result.setGroupComments(commentList);
		return result;
	}
	@Override
	public ResultSupport updateTourGroupComment(Integer bizId,
			TourGroupComment comment) {
		ResultSupport resultSupport = new ResultSupport();
		int updateResult = tourGroupBiz.updateTourGroupComment(bizId, comment);
		if (updateResult < 1) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	@Override
	public AgencyOrderResult toSKConfirmPreview(Integer bizId,
			List<Integer> groupIds) {
		AgencyOrderResult result = new AgencyOrderResult();
		AgencyOrderResult agencyOrderResult = toEditGroupComment(bizId, groupIds);
		result.setTourGroup(agencyOrderResult.getTourGroup());
		result.setGroupComments(agencyOrderResult.getGroupComments());
		List<GroupRoute> routeList = groupRouteBiz.selectByGroupId(groupIds.get(0));
		result.setGroupRoutes(routeList);
		return result;
	}

}
