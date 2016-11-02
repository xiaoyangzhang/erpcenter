package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.erp.utils.WebUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketRequestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.FitOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupSupplierVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.facade.sales.errorcode.OperationErrorCode;
import com.yimayhd.erpcenter.facade.sales.query.AgencyOrderQueryDTO;
import com.yimayhd.erpcenter.facade.sales.result.AgencyOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.WebResult;
import com.yimayhd.erpcenter.facade.sales.service.AgencyFitFacade;

public class AgencyFitFacadeImpl implements AgencyFitFacade {

	@Autowired
	private ProductGroupBiz productGroupBiz;
	@Autowired
	private ProductGroupSupplierBiz productGroupSupplierBiz;
	@Autowired
	ProductInfoBiz productInfoBiz;
	@Autowired
	private ProductRemarkBiz productRemarkBiz;
	@Autowired
	private ProductStockBiz productStockBiz;
	@Autowired
	private FitOrderBiz fitOrderBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private RegionBiz regionBiz;
	@Autowired 
	private FinanceBiz financeBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private AirTicketRequestBiz airTicketRequestBiz;
	@Autowired
	private GroupRequirementBiz groupRequirementBiz;
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz;
	@Autowired
	private GroupRouteBiz groupRouteBiz;
	@Autowired
	private FitOrderBiz fitOrderBiz2;
	@Override
	public List<ProductGroupVo> selectGroupsByProdctIdAndSupplierId(
			Integer productId, Integer supplierId, Date date) {
		List<ProductGroupVo> priceGroup = productGroupBiz.selectGroupsByProdctIdAndSupplierId(productId, supplierId,date);
		return priceGroup;
	}
	@Override
	public AgencyOrderResult toAddGroupOrder(AgencyOrderQueryDTO queryDTO) {
		AgencyOrderResult result = new AgencyOrderResult();
		List<ProductGroupSupplierVo> groupSuppliersList = productGroupSupplierBiz
				.selectProductGroupSupplierVos(queryDTO.getGroupId(), queryDTO.getPriceId());
		result.setGroupSupplierVos(groupSuppliersList);
		ProductInfo productInfo = productInfoBiz.findProductInfoById(queryDTO.getProductId());
		result.setProductInfo(productInfo);
		ProductGroup group = productGroupBiz.getGroupInfoById(queryDTO.getGroupId());
		result.setProductGroup(group);
		ProductRemark productRemark = productRemarkBiz.findProductRemarkByProductId(queryDTO.getProductId());
		result.setProductRemark(productRemark);
		int count = productStockBiz.getRestCountByProductIdAndDate(queryDTO.getProductId(),queryDTO.getDate());
		result.setCount(count);
		return result;
	}
	@Override
	public AgencyOrderResult toEditFirOrder(Integer orderId) throws Exception {
		AgencyOrderResult result = new AgencyOrderResult();
		FitOrderVO vo = fitOrderBiz.selectFitOrderVOById(orderId);
		result.setFitOrderVO(vo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		GroupOrder groupOrder = groupOrderBiz.findById(orderId);
		result.setGroupOrder(groupOrder);
		int count = productStockBiz.getRestCountByProductIdAndDate(groupOrder.getProductId(),sdf.parse(groupOrder.getDepartureDate()));
		result.setCount(count);
		List<RegionInfo> cityList = null;
		if (groupOrder.getProvinceId() != null && groupOrder.getProvinceId() != -1) {
			cityList = regionBiz.getRegionById(groupOrder.getProvinceId()+ "");
			result.setRegionList(cityList);
		}
		
		List<Map<String, Object>> payDetails = financeBiz.selectDetailByLocOrderId(orderId);
		result.setMapList(payDetails);
		return result;
	}
	@Override
	public WebResult<Map<String, Object>> saveFitOrderInfo(FitOrderVO fitOrderVO,AgencyOrderQueryDTO queryDTO)
			throws Exception {
		WebResult<Map<String, Object>> result = new WebResult<Map<String,Object>>();
		ProductInfo productInfo ;
		Integer orderId;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer newNum = fitOrderVO.getGroupOrder().getNumAdult() + fitOrderVO.getGroupOrder().getNumChild();   //修改后人数
		
		Integer oldNum = 0;  //修改前人数
		try {
			if (fitOrderVO.getGroupOrder().getId() != null) {
				FitOrderVO vo = fitOrderBiz.selectFitOrderVOById(fitOrderVO
						.getGroupOrder().getId());

				oldNum = vo.getGroupOrder().getNumAdult()
						+ vo.getGroupOrder().getNumChild();
			}
			
			//查出库存(剩余人数)
			int freeCount = productStockBiz.getRestCountByProductIdAndDate(fitOrderVO
					.getGroupOrder().getProductId(),sdf.parse(fitOrderVO
							.getGroupOrder().getDepartureDate()));
			//实际库存应该是修改前人数+库存
			freeCount = oldNum + freeCount;
			if(newNum > freeCount){
				//如果新增人数大于库存,则不能保存
				result.setResultMsg("由于库存剩余数有变化，目前剩余库存不足【" + newNum + "】！实际库存还有【" + freeCount + "】");
//				return errorJson("由于库存剩余数有变化，目前剩余库存不足【" + newNum + "】！实际库存还有【" + freeCount + "】");
				return result;
			}
			
			if(fitOrderVO.getGroupOrder().getId()==null){
				fitOrderVO.setProductCode(productInfoBiz.findProductInfoById(fitOrderVO.getGroupOrder().getProductId()).getCode());
			}
			 productInfo = productInfoBiz.findProductInfoById(fitOrderVO.getGroupOrder().getProductId());
			orderId = fitOrderBiz.saveOrUpdateFitOrderInfo(fitOrderVO,queryDTO.getUserId(),queryDTO.getUserName(),
					productInfo==null?null:productInfo.getOperatorId(),productInfo==null?"":productInfo.getOperatorName(),queryDTO.getBizId(), queryDTO.getBizCode(), true);
		} catch (ParseException e) {
			result.setErrorCode(OperationErrorCode.MODIFY_ERROR);
			return result;
//			return errorJson("操作失败,请检查后重试！");
		}
		try {
			if(productInfo!=null){
				if(fitOrderVO.getGroupOrder().getType()==0){ //预留
					productStockBiz.updateReserveCount(fitOrderVO.getGroupOrder().getProductId(), sdf.parse(fitOrderVO.getGroupOrder().getDepartureDate()), newNum - oldNum);
					
				}else{
				

				productStockBiz.updateStockCount(fitOrderVO.getGroupOrder()
						.getProductId(), sdf.parse(fitOrderVO.getGroupOrder()
						.getDepartureDate()), newNum - oldNum);
				
				}
			}
			
			
			
			
		} catch (Exception e) {
			result.setErrorCode(OperationErrorCode.MODIFY_ERROR);
			return result;
//			return errorJson("更新库存失败！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("orderId", orderId+"");
		result.setValue(map);
//		return successJson("orderId", orderId + "");
		return result;
	}
	@Override
	public AgencyOrderResult getFitOrderListForMsglData(AgencyOrderQueryDTO queryDTO) {
		AgencyOrderResult result = new AgencyOrderResult();
		PageBean pageBean = groupOrderBiz.selectFitOrderListPage(queryDTO.getPageBean(),queryDTO.getBizId(),queryDTO.getSet(),0);
		
		List<GroupOrder> list = pageBean.getResult();
		
		if (list != null && list.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (GroupOrder groupOrder2 : list) {
				if (groupOrder2.getCreateTime() != null) {
					Long createTime = groupOrder2.getCreateTime();
					String dateStr = sdf.format(createTime);
					groupOrder2.setCreateTimeStr(dateStr);
					
					if (groupOrder2.getProductId()!=null){ 
						ProductInfo productInfo = productInfoBiz.findProductInfoById(groupOrder2.getProductId());
						groupOrder2.setQuartzTime(productInfo.getObligateHour());
					}
				}

			}
		}
		result.setPageBean(pageBean);
		GroupOrder order = groupOrderBiz.selectFitOrderTotalCount(
				queryDTO.getGroupOrder(), queryDTO.getBizId(),queryDTO.getSet(),0);
		result.setGroupOrder(order);
		return result;
	}
	@Override
	public PageBean getInsertFitGroupList(AgencyOrderQueryDTO queryDTO) throws ParseException {
		if (queryDTO.getOrderId() != null) {
			GroupOrder groupOrder = groupOrderBiz.selectByPrimaryKey(queryDTO.getOrderId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			TourGroup tourGroup = (TourGroup) queryDTO.getPageBean().getParameter();
			tourGroup.setEndTime(sdf.parse(groupOrder.getDepartureDate()));
		}
		PageBean pageBean = tourGroupBiz.selectSKGroupListPage(queryDTO.getPageBean(),
				queryDTO.getBizId(),queryDTO.getSet());
		return pageBean;
	}
	@Override
	public ResultSupport updateGroupOrder(GroupOrder groupOrder) {
		ResultSupport resultSupport = new ResultSupport();
		groupOrderBiz.updateGroupOrder(groupOrder);
		return resultSupport;
	}
	@Override
	public ResultSupport delGroupOrder(Integer orderId,Integer bizId) {
		ResultSupport resultSupport = new ResultSupport();
		if (airTicketRequestBiz.doesOrderhaveRequested(bizId, orderId)) {
			resultSupport.setErrorCode(OperationErrorCode.CANCEL_APP);
			return resultSupport;
//			return errorJson("删除订单前请先取消机票申请。");
		}
		GroupOrder groupOrder = groupOrderBiz.findById(orderId);
		groupOrder.setState(-1);
		groupOrderBiz.updateGroupOrder(groupOrder);

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			if(groupOrder.getType()==0){ //预留
				productStockBiz.updateReserveCount(groupOrder.getProductId(), sdf.parse(groupOrder.getDepartureDate()),-(groupOrder.getNumAdult() + groupOrder.getNumChild()));
				
			}else{
			

				productStockBiz.updateStockCount(groupOrder.getProductId(),
						sdf.parse(groupOrder.getDepartureDate()),
						-(groupOrder.getNumAdult() + groupOrder.getNumChild()));
			
			}
			
		} catch (Exception e) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
//			return errorJson("更新库存失败！");
		}
		return resultSupport;
	}
	@Override
	public AgencyOrderResult modifyGroup(String[] idArr) {
		AgencyOrderResult result = new AgencyOrderResult();
		List<String> datelist = new ArrayList<String>();
		List<Integer> productlist = new ArrayList<Integer>();
		List<Integer> brandlist = new ArrayList<Integer>();
		List<Integer> statelist = new ArrayList<Integer>();
		for (String id : idArr) {
			GroupOrder groupOrder = groupOrderBiz.findById(Integer
					.parseInt(id));
			datelist.add(groupOrder.getDepartureDate());
			productlist.add(groupOrder.getProductId());
			statelist.add(groupOrder.getStateFinance());
			brandlist.add(groupOrder.getProductBrandId());

		}
		result.setStrList(datelist);
		result.setIntList(brandlist);
		return result;
	}
	@Override
	public ResultSupport insertGroupMany(AgencyOrderQueryDTO queryDTO) {
		ResultSupport resultSupport = new ResultSupport();
		TourGroup tourGroup = tourGroupBiz.selectByGroupCode(queryDTO.getCode());
		if (tourGroup == null) {
//			return errorJson("未查到该团号对应的散客团信息!");
			resultSupport.setErrorCode(OperationErrorCode.NOT_FOUND);
			return resultSupport;
		}
		
		Integer userId = queryDTO.getUserId();
		String userName = queryDTO.getUserName();
		String[] idArr = queryDTO.getIdArr();
		for (String str : idArr) {
			GroupOrder groupOrder = groupOrderBiz
					.selectByPrimaryKey(Integer.parseInt(str));
			groupOrder.setGroupId(tourGroup.getId());
			groupOrder.setOperatorId(userId);
			groupOrder.setOperatorName(userName);
			groupOrderBiz.updateGroupOrder(groupOrder);
			tourGroup.setOrderNum(tourGroup.getOrderNum() == null ? 1
					: tourGroup.getOrderNum() + 1);
			tourGroupBiz.updateByPrimaryKey(tourGroup);
			groupOrderBiz.updateGroupPersonNum(tourGroup.getId());
			groupOrderBiz.updateGroupPrice(tourGroup.getId());

			List<GroupRequirement> list = groupRequirementBiz
					.selectByOrderId(Integer.parseInt(str));
			if (list != null && list.size() > 0) {
				for (GroupRequirement groupRequirement : list) {
					groupRequirement.setGroupId(tourGroup.getId());
					groupRequirementBiz
							.updateByPrimaryKeySelective(groupRequirement);
				}
			}
		}
		return resultSupport;
	}
	@Override
	public List<GroupOrder> toMergeGroup(String[] idArr) {
		List<GroupOrder> list = new ArrayList<GroupOrder>();
		for (String str : idArr) {
			GroupOrder groupOrder = groupOrderBiz.findById(Integer
					.parseInt(str));
			groupOrder.setGroupOrderGuestList((groupOrderGuestBiz
					.selectByOrderId(groupOrder.getId())));
			list.add(groupOrder);
		}
		return list;
	}
	@Override
	public PageBean toImpNotGroupList(PageBean pageBean, Integer bizId,
			Set<Integer> set) {
		PageBean bean = groupOrderBiz.selectNotGroupListPage(pageBean,bizId,set);
		return bean;
	}
	@Override
	public ResultSupport mergeGroup(AgencyOrderQueryDTO queryDTO, MergeGroupOrderVO mergeGroupOrderVO) {
		ResultSupport resultSupport = new ResultSupport();
		List<MergeGroupOrderVO> result = queryDTO.getMergeGroupOrderVOs();
		List<GroupOrder> orderList = mergeGroupOrderVO.getOrderList();
		for (MergeGroupOrderVO mgo : result) {
			List<GroupOrder> oList = mergeGroupOrderVO.getOrderList();
			Integer orderId = null;
			Integer dayNum = null;
			// 设置订单
			for (GroupOrder go2 : orderList) {
				List<GroupRoute> rouList = groupRouteBiz.selectByOrderId(go2.getId());
				if (rouList != null && rouList.size() > 0) {

					if (orderId == null) {
						orderId = go2.getId();
					}
					if (dayNum == null) {
						dayNum = rouList.size();
					}
					if (rouList.size() > dayNum) {
						dayNum = rouList.size();
						orderId = go2.getId();
					}
				}
			}
			
			GroupOrder key = groupOrderBiz.selectByPrimaryKey(orderId);
			ProductInfo productInfo = productInfoBiz.findProductInfoById(key.getProductId());
			mgo.setProductCode(productInfo.getCode());
		}

		fitOrderBiz.mergetGroup(result, queryDTO.getBizId(),queryDTO.getUserId(),queryDTO.getUserName(),queryDTO.getBizCode(),true);

		return resultSupport;
	}

}
