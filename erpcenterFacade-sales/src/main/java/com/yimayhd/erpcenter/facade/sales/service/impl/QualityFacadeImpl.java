package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.quality.QualityBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupFeedbackBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.quality.vo.QualityGroupGuestFeedback;
import com.yimayhd.erpcenter.dal.sales.client.quality.vo.QualityTourGroupCondition;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupFeedbackGroupStaticsVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupFeedbackPersonalStaticsVO;
import com.yimayhd.erpcenter.facade.sales.query.quality.LoadQualityListDTO;
import com.yimayhd.erpcenter.facade.sales.query.quality.SaveReplyDTO;
import com.yimayhd.erpcenter.facade.sales.query.quality.SupplierCommentListDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.quality.GroupQualityInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.quality.LoadQualityListResult;
import com.yimayhd.erpcenter.facade.sales.result.quality.QualityListResult;
import com.yimayhd.erpcenter.facade.sales.result.quality.SupplierCommentListResult;
import com.yimayhd.erpcenter.facade.sales.service.QualityFacade;
import com.yimayhd.erpresource.biz.service.SupplierCommentBiz;

public class QualityFacadeImpl implements QualityFacade{

	@Autowired
	private QualityBiz qualityService;
	
	@Autowired
	private DicBiz dicService;
	
	@Autowired
	private GroupOrderBiz orderService;
	
	@Autowired
	private TourGroupBiz groupService;
	
	@Autowired
	private GroupOrderGuestBiz orderGuestService;
	
	@Autowired
	private GroupFeedbackBiz feedbackService;
	
	//@Autowired
	//private CustomerService customerService;
	
	@Autowired
	private SupplierCommentBiz supplierCommentService;
	
	public QualityListResult qualityList(Integer curBizId){
		
		List<DicInfo> brandList = dicService.getListByTypeCode(BasicConstants.CPXL_PP,curBizId);
		QualityListResult result=new QualityListResult();
		result.setBrandList(brandList);
		
		return result;
	}
	
	public LoadQualityListResult loadQualityList(LoadQualityListDTO loadQualityListDTO){
		QualityTourGroupCondition condition=loadQualityListDTO.getCondition();
		Integer curBizId=loadQualityListDTO.getCurBizId();
		
		PageBean pageBean = new PageBean();
		pageBean.setPage(condition.getPage());
		if(condition.getPageSize() == null){
			pageBean.setPageSize(Constants.PAGESIZE);			
		}else{
			pageBean.setPageSize(condition.getPageSize());
		}
		condition.setBizId(curBizId);
		pageBean.setParameter(condition);		
		pageBean = qualityService.getQualityTourGroupList(pageBean);
		
		LoadQualityListResult result=new LoadQualityListResult();
		result.setPageBean(pageBean);
		return result;
	}
	
	public GroupQualityInfoResult groupQualityInfo(Integer groupId){
		
		//团基本信息
		TourGroup groupInfo = groupService.selectByPrimaryKey(groupId);
		GroupFeedbackGroupStaticsVO groupStaticsVO = feedbackService.getGroupStaticsByGroupId(groupId);
		List<GroupOrder> orderList = orderService.selectOrderByGroupId(groupId);
		List<QualityGroupGuestFeedback> guestFeedbackStaticsList = getGuestFeedbackStatics(groupId,orderList);
		
		GroupQualityInfoResult result=new GroupQualityInfoResult();
		result.setGroupInfo(groupInfo);
		result.setGroupStaticsVO(groupStaticsVO);
		result.setGuestFeedbackStaticsList(guestFeedbackStaticsList);
		
		return result;
	}
	
	/**
	 * 顾客反馈统计
	 * @param groupId
	 * @return
	 */	
	private List<QualityGroupGuestFeedback> getGuestFeedbackStatics(Integer groupId,List<GroupOrder> orderList){		
		List<QualityGroupGuestFeedback> guestFeedbackList = new ArrayList<QualityGroupGuestFeedback>();
		if(orderList!=null && orderList.size()>0){
			for(GroupOrder guestOrder : orderList){
				List<GroupOrderGuest> guestList = orderGuestService.selectByOrderId(guestOrder.getId());
				if(guestList!=null && guestList.size()>0){
					for(GroupOrderGuest orderGuest : guestList){
						QualityGroupGuestFeedback guest = new QualityGroupGuestFeedback();
						guest.setGroupId(guestOrder.getGroupId());
						guest.setSupplierName(guestOrder.getSupplierName());
						guest.setReceiverMode(guestOrder.getReceiveMode());
						guest.setGuestName(orderGuest.getName());
						guest.setMobile(orderGuest.getMobile());
						guest.setOrderNo(guestOrder.getOrderNo());
						
						GroupFeedbackPersonalStaticsVO staticsVo = feedbackService.getPersonalStaticsByGroupIdAndIdNo(groupId, orderGuest.getCertificateNum());
						if(staticsVo!=null){
							guest.setDriverAttitude(staticsVo.getDriverAttitude());
							guest.setDriverEnvironment(staticsVo.getDriverEnvironment());
							guest.setFoodEnvironment(staticsVo.getFoodEnvironment());
							guest.setFoodQuality(staticsVo.getFoodQuality());
							guest.setGuideAttitude(staticsVo.getGuideAttitude());
							guest.setGuideProfession(staticsVo.getGuideProfession());
							guest.setHotelEnvironment(staticsVo.getHotelEnvironment());
							guest.setHotelQuality(staticsVo.getHotelQuality());
							guest.setScenicWhole(staticsVo.getScenicWhole());
							guest.setSuggest(staticsVo.getSuggestion());
							guest.setScore(staticsVo.getScore());
						}
						guestFeedbackList.add(guest);
					}					
				}
			}
		}
		return guestFeedbackList;
	}
	
	public SupplierCommentListResult supplierCommentList(SupplierCommentListDTO supplierCommentListDTO) {

		PageBean pageBean = new PageBean();
		if (supplierCommentListDTO.getPage() == null) {
			pageBean.setPage(1);
		} else {
			pageBean.setPage(supplierCommentListDTO.getPage());
		}
		if (supplierCommentListDTO.getPageSize() == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(supplierCommentListDTO.getPageSize());
		}
		pageBean.setParameter(supplierCommentListDTO.getQueryParamters());
		pageBean = supplierCommentService.supplierCommentListSelectPage(pageBean, supplierCommentListDTO.getGroupId(),
				supplierCommentListDTO.getSupplierId(), supplierCommentListDTO.getTheKey());

		SupplierCommentListResult result = new SupplierCommentListResult();
		result.setPageBean(pageBean);
		return result;
	}
	
	public BaseStateResult updateCommentState(int id, int state){
		supplierCommentService.updateCommentState(id,state);
		return new BaseStateResult(true,null);
	}
	
	public BaseStateResult updateTagState(String name, int state){
		supplierCommentService.updateTagState(name,state);
		return new BaseStateResult(true,null);
	}
	
	public BaseStateResult saveReply(SaveReplyDTO saveReplyDTO){
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(false);
		try{
			supplierCommentService.saveReply(saveReplyDTO.getId(),
					saveReplyDTO.getReply(),
					saveReplyDTO.getEmployeeId(),
					saveReplyDTO.getUserName(),
					System.currentTimeMillis());
			result.setSuccess(false);
			return result;
		}catch(Exception e){
			result.setError("操作失败");
			return result;
		}	
	}
}
