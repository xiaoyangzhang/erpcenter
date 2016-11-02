package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketRequestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TeamGroupBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.facade.sales.errorcode.OperationErrorCode;
import com.yimayhd.erpcenter.facade.sales.result.AgencyOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.WebResult;
import com.yimayhd.erpcenter.facade.sales.service.AgencyTeamFacade;
import com.yimayhd.erpcenter.facade.sales.utils.GroupCodeUtil;

public class AgencyTeamFacadeImpl implements AgencyTeamFacade {

	@Autowired
	private ProductInfoBiz productInfoBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private TeamGroupBiz teamGroupBiz;
	@Autowired
	private FinanceBiz financeBiz;
	@Autowired
	private AirTicketRequestBiz airTicketRequestBiz;
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	@Autowired
	private PlatformOrgBiz platformOrgBiz;
	@Autowired
	private RegionBiz regionBiz;
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	@Override
	public WebResult<Integer> saveTeamGroupInfo(TeamGroupVO teamGroupVO, Integer bizId,
			String userName, Integer userId,String bizCode) throws ParseException {
		WebResult<Integer> result = new WebResult<Integer>();
		ProductInfo productInfo = productInfoBiz.findProductInfoById(teamGroupVO.getGroupOrder().getProductId());
		
		if(teamGroupVO.getTourGroup().getId()==null){
			if(productInfo != null){
				teamGroupVO.setProductCode(productInfo.getCode());
			}
		}else{	
			TourGroup tourGroup = teamGroupVO.getTourGroup();
			TourGroup group=tourGroupBiz.selectByPrimaryKey(tourGroup.getId());
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			
			String productInfoCode = "";
			if(productInfo != null){
				String brandName = productInfo.getBrandName() != null ? productInfo.getBrandName() : "";
				String nameCity = productInfo.getNameCity() != null ? productInfo.getNameCity() : "";
				GroupOrder order = teamGroupVO.getGroupOrder();
				if(order != null && brandName.equals(order.getProductBrandName()) && nameCity.equals(order.getProductName())){
					productInfoCode = productInfo.getCode();
				}
			}
			
			tourGroup.setGroupCode(GroupCodeUtil.getCodeForAgency(bizCode,
					tourGroup.getGroupCode(), tourGroup.getGroupCodeMark(),productInfoCode,
							sdf1.format(group.getDateStart()),sdf1.format(group.getDateEnd())));
		}
		
		TeamGroupVO tgv = teamGroupBiz.saveOrUpdateTeamGroupVO(bizId, userId, userName, teamGroupVO);
		result.setValue(tgv.getTourGroup().getId());
		return result;
	}
	@Override
	public ResultSupport deleteGroupOrderById(Integer orderId, Integer groupId,
			Integer curBizId) {
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
		if (airTicketRequestBiz.doesOrderhaveRequested(curBizId, orderId)){
//			return errorJson("删除订单前请先取消机票申请。");
			resultSupport.setErrorCode(OperationErrorCode.CANCEL_APP);
			return resultSupport;
		}
		
		int i = tourGroupBiz.deleteTourGroupById(groupId, orderId);
		if (i != 1) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	@Override
	public AgencyOrderResult toEditTeamGroupInfo(Integer groupId,
			Integer curBizId) {
		AgencyOrderResult result = new AgencyOrderResult();
		TeamGroupVO teamGroupVO = teamGroupBiz.selectTeamGroupVOByGroupId(groupId,curBizId);
		result.setTeamGroupVO(teamGroupVO);
		List<RegionInfo> cityList = null;
		if (teamGroupVO.getGroupOrder().getProvinceId() != null
				&& teamGroupVO.getGroupOrder().getProvinceId() != -1) {
			cityList = regionBiz.getRegionById(teamGroupVO.getGroupOrder()
					.getProvinceId() + "");
		}
		if (cityList != null) {
			
			result.setRegionList(cityList);
		}
		String guideStr="";
		List<GroupOrderGuest> guestList = groupOrderGuestBiz.selectByOrderId(teamGroupVO.getGroupOrder().getId());
		if(guestList!=null){
			for (GroupOrderGuest groupOrderGuest : guestList) {
				if(groupOrderGuest.getType()==3){
					guideStr=("".equals(guideStr)?"":(guideStr+" | "))+groupOrderGuest.getName()+" "+groupOrderGuest.getMobile();
				}
			}
		}
		result.setGuideStr(guideStr);
		List<Map<String, Object>> payDetails = financeBiz.selectDetailByLocOrderId(teamGroupVO.getGroupOrder().getId());
		
		if(payDetails != null && payDetails.size() > 0){
			Map<String, Object> detail = null;
			for(int i = 0; i < payDetails.size(); i++){
				detail = payDetails.get(i);
				Object userId = detail.get("user_id"); 
				if(userId == null){
					continue;
				}
				PlatformEmployeePo employeePo = platformEmployeeBiz.findByEmployeeId(Integer.parseInt(userId.toString()));
				PlatformOrgPo orgPo = platformOrgBiz.findByOrgId(employeePo.getOrgId());
				detail.put("department", orgPo.getName());
			}
		}
		result.setMapList(payDetails);
		return result;
	}

}
