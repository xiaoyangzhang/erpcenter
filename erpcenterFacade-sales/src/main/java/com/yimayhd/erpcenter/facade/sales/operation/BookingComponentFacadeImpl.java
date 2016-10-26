/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteVO;
import com.yimayhd.erpcenter.facade.sales.query.ContractQueryDTO;
import com.yimayhd.erpcenter.facade.sales.result.operation.TourGroupInfoResult;
import com.yimayhd.erpcenter.facade.sales.service.BookingComponentFacade;
import com.yimayhd.erpresource.biz.service.ContractBiz;
import com.yimayhd.erpresource.dal.po.SupplierContractPriceDateInfo;

/**
 * @ClassName: BookingComponentFacadeImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public class BookingComponentFacadeImpl implements BookingComponentFacade {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingComponentFacadeImpl.class);
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	@Autowired
	private GroupOrderTransportBiz groupOrderTransportBiz;
	@Autowired
	private GroupRequirementBiz groupRequirementBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private GroupRouteBiz groupRouteBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private ContractBiz contractBiz;
	@Override
	public List<GroupOrderGuest> selectGuestByOrderId(Integer orderId) {
		return groupOrderGuestBiz.selectByOrderId(orderId);
	}

	@Override
	public List<GroupOrderTransport> selectTransportByOrderId(Integer orderId) {
		return groupOrderTransportBiz.selectByOrderId(orderId);
	}

	@Override
	public TourGroupInfoResult getTourGroupInfo(Integer groupId, Integer type) {
		TourGroupInfoResult result = new TourGroupInfoResult();
		TourGroup groupInfo = tourGroupBiz.selectByPrimaryKey(groupId);	
		if (groupInfo!=null) {
			if (groupInfo.getServiceStandard() != null) {
				groupInfo.setServiceStandard(groupInfo.getServiceStandard().replaceAll("[\\n]", "<br/>"));
			}
			if (groupInfo.getRemarkInternal() != null) {
				groupInfo.setRemarkInternal(groupInfo.getRemarkInternal().replaceAll("[\\n]", "<br/>"));
			}
			if (groupInfo.getRemark() != null) {
				groupInfo.setRemark(groupInfo.getRemark().replaceAll("[\\n]", "<br/>"));
			}
		}
		result.setTourGroup(groupInfo);
		String supplierName = "";
		//团队才有组团社
		if(groupInfo!=null && groupInfo.getGroupMode()>0){
			List<GroupOrder> list = groupOrderBiz.selectOrderByGroupId(groupId);
//			if(list!=null && list.size()>0){
//				supplierName=list.get(0).getSupplierName();
//			}
			result.setOrders(list);
		}
		if(type!=null && type==1){
			List<GroupRequirement> requirementList = groupRequirementBiz.selectByGroupIdAndType(groupId, type);
			result.setGroupRequirements(requirementList);
		}
		GroupRouteVO routeVo = groupRouteBiz.findGroupRouteByGroupId(groupId);
		result.setGroupRoute(routeVo);
		return null;
	}

	@Override
	public List<SupplierContractPriceDateInfo> getOperateContractPrice(
			ContractQueryDTO queryDTO) {
		if (queryDTO == null || queryDTO.getBizId() <= 0 || queryDTO.getSupplierId() <= 0) {
			LOGGER.error("params:ContractQueryDTO={}",JSON.toJSONString(queryDTO));
			return new ArrayList<SupplierContractPriceDateInfo>();
		}
		return contractBiz.getOperateContractPrice(queryDTO.getBizId(),queryDTO.getSupplierId(),queryDTO.getDate(),queryDTO.getType1()
				,queryDTO.getType2(),queryDTO.getType2Name());
	}

	@Override
	public List<SupplierContractPriceDateInfo> getCarContractPrice(
			ContractQueryDTO queryDTO) {
		if (queryDTO == null ) {
			LOGGER.error("params:ContractQueryDTO={}",JSON.toJSONString(queryDTO));
			return new ArrayList<SupplierContractPriceDateInfo>();
		}
		return contractBiz.getCarContractPrice(queryDTO.getBizCarContractId(),queryDTO.getDate(),
				queryDTO.getDateTo(),queryDTO.getType1(),queryDTO.getSeatCnt());
	}

	@Override
	public String getContractPriceJson(
			ContractQueryDTO queryDTO) {
		if (queryDTO == null || queryDTO.getBizId() <= 0 || queryDTO.getSupplierId() <= 0) {
			LOGGER.error("params:ContractQueryDTO={}",JSON.toJSONString(queryDTO));
			return "[]";
		}
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(queryDTO.getGroupId());
		List<Date> dateList = new ArrayList<Date>();
		if(tourGroup!=null && tourGroup.getDaynum()!=null){
			for(int i=0;i<tourGroup.getDaynum();i++){
				dateList.add(DateUtils.addDays(tourGroup.getDateStart(),i));
			}
		}
		if(dateList.size()==0){
			return "[]";
		}
		List<SupplierContractPriceDateInfo> priceList = contractBiz.getContractPriceByPramas(queryDTO.getBizId(),queryDTO.getSupplierId(),null,queryDTO.getDateList());
		if(priceList==null){
			return "[]";
		}
		return JSON.toJSONString(priceList);
	}

	
	@Override
	public List<SupplierContractPriceDateInfo> getContractPriceByPramas(
			ContractQueryDTO queryDTO) {
		if (queryDTO == null || queryDTO.getBizId() <= 0 || queryDTO.getSupplierId() <= 0) {
			LOGGER.error("params:ContractQueryDTO={}",JSON.toJSONString(queryDTO));
			return new ArrayList<SupplierContractPriceDateInfo>();
		}
		List<SupplierContractPriceDateInfo> contractList = contractBiz.getContractPriceByPramas(queryDTO.getBizId(), queryDTO.getSupplierId(), queryDTO.getGoodsId(), queryDTO.getDateList());
		return contractList;
	}

}
