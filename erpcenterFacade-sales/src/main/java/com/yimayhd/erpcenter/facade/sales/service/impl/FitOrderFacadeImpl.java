package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupExtraItemBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupPriceBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketRequestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.FitOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupRequirementBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.SpecialGroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.vo.ProductGroupVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.MergeGroupOrderVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.DelGroupOrderDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.GetFitOrdersDataDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.GetGroupPirceDataDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.GetInsertFitGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.InsertGroupDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.MergeGroupDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.SaveFitOrderInfoDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.ToAddGroupOrderDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.ToEditFirOrderDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.ToFitOrderListDTO;
import com.yimayhd.erpcenter.facade.sales.query.fitorder.ToImpNotGroupListDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.GetFitOrdersDataResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.GetGroupPirceDataResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.GetInsertFitGroupListResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.GroupOrderListResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.SaveFitOrderInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.ToAddGroupOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.ToEditFirOrderResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.ToFitOrderListResult;
import com.yimayhd.erpcenter.facade.sales.result.fitorder.ToImpNotGroupListResult;
import com.yimayhd.erpcenter.facade.sales.service.FitOrderFacade;
import com.yimayhd.erpcenter.facade.sales.utils.MergeGroupUtils;

public class FitOrderFacadeImpl implements FitOrderFacade{

	@Autowired
	private GroupOrderBiz groupOrderService;
	
	@Autowired
	private TourGroupBiz tourGroupService;
	
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestService;
	
	@Autowired
	private DicBiz dicService;
	
	@Autowired
	private RegionBiz regionService;
	
	@Autowired
	private ProductGroupSupplierBiz productGroupSupplierService;
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeService;
	
	@Autowired
	private PlatformOrgBiz orgService;
	
	@Autowired
	private ProductGroupBiz productGroupService;
	
	@Autowired
	private SpecialGroupOrderBiz specialGroupOrderService;
	
	@Autowired
	private ProductGroupPriceBiz productGroupPriceService;
	
	@Autowired
	private GroupRequirementBiz groupRequirementService;
	
	@Autowired
	private ProductRemarkBiz productRemarkService;
	
	@Autowired
	private ProductRouteBiz productRouteService;
	
	@Autowired
	private ProductInfoBiz productInfoService;
	
	@Autowired
	private ProductStockBiz productStockService;
	
	@Autowired
	private ProductGroupExtraItemBiz productGroupExtraItemService;
	
	@Autowired
	private FitOrderBiz fitOrderService;
	
	@Autowired
	private AirTicketRequestBiz airTicketRequestService;
	
	@Autowired
	private BookingSupplierBiz bookingSupplierService ;
	
	public GetGroupPirceDataResult getGroupPirceData(GetGroupPirceDataDTO getGroupPirceDataDTO) {
		
		List<ProductGroupVo> priceGroup = productGroupService.selectGroupsByProdctIdAndSupplierId(getGroupPirceDataDTO.getProductId(), 
				getGroupPirceDataDTO.getSupplierId(),
				getGroupPirceDataDTO.getDate());

		GetGroupPirceDataResult result=new GetGroupPirceDataResult();
		result.setPriceGroup(priceGroup);
		
		return result;
	}

	public ToAddGroupOrderResult toAddGroupOrder(ToAddGroupOrderDTO toAddGroupOrderDTO){
		
		PlatformEmployeePo curUser=toAddGroupOrderDTO.getCurUser();
		Integer productId=toAddGroupOrderDTO.getProductId();
		Date date=toAddGroupOrderDTO.getDate();
		Integer bizId=toAddGroupOrderDTO.getCurBizId();
		
		List<ProductGroupSupplier> supplierList = productGroupSupplierService.selectProductGroupSuppliers2(productId); // 获取价格下的组团社列表

		ProductRemark productRemark = productRemarkService.findProductRemarkByProductId(productId);
		ProductInfo productInfo = productInfoService.findProductInfoById(productId);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		FitOrderVO vo = new FitOrderVO();
		
		GroupOrder groupOrder = new GroupOrder();
		groupOrder.setSaleOperatorId(curUser.getEmployeeId());
		groupOrder.setSaleOperatorName(curUser.getName());
		groupOrder.setOperatorId(curUser.getEmployeeId());
		groupOrder.setOperatorName(curUser.getName());
		groupOrder.setRemark(productRemark.getRemarkInfo());
		groupOrder.setServiceStandard(productRemark.getServeLevel());
		groupOrder.setProductBrandId(productInfo.getBrandId());
		groupOrder.setProductBrandName(productInfo.getBrandName());
		groupOrder.setProductId(productInfo.getId());
		groupOrder.setProductName(productInfo.getNameCity());
		groupOrder.setDepartureDate(sdf.format(date));
		
		vo.setGroupOrder(groupOrder);
		
		
		int count = productStockService.getRestCountByProductIdAndDate(groupOrder.getProductId(),date);
		
		List<DicInfo> jdxjList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		List<DicInfo> jtfsList = dicService.getListByTypeCode(BasicConstants.GYXX_JTFS,bizId);
		List<DicInfo> zjlxList = dicService.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		

		DicInfo dicInfoCR = dicService.getDicInfoByTypeCodeAndDicCode(bizId, BasicConstants.GYXX_LYSFXM,BasicConstants.CR);
		DicInfo dicInfoET = dicService.getDicInfoByTypeCodeAndDicCode(bizId, BasicConstants.GYXX_LYSFXM,BasicConstants.ERT);
		List<DicInfo> dicInfoCRList = new ArrayList<DicInfo>();
		dicInfoCRList.add(dicInfoCR);
		List<DicInfo> dicInfoETList = new ArrayList<DicInfo>();
		dicInfoETList.add(dicInfoET);
		
		List<DicInfo> sourceTypeList = dicService.getListByTypeCode(Constants.GUEST_SOURCE_TYPE,bizId);
		List<RegionInfo> allProvince = regionService.getAllProvince();
		List<DicInfo> lysfxmList = dicService.getListByTypeCode(BasicConstants.GYXX_LYSFXM, bizId);
		
		ToAddGroupOrderResult result=new ToAddGroupOrderResult();
		
		result.setAllProvince(allProvince);
		result.setCount(count);
		result.setDicInfoCRList(dicInfoCRList);
		result.setDicInfoETList(dicInfoETList);
		result.setJdxjList(jdxjList);
		result.setJtfsList(jtfsList);
		result.setLysfxmList(lysfxmList);
		result.setSourceTypeList(sourceTypeList);
		result.setSupplierList(supplierList);
		result.setVo(vo);
		result.setZjlxList(zjlxList);
		
		return result;
		
	}

	public ToEditFirOrderResult toEditFirOrder(ToEditFirOrderDTO toEditFirOrderDTO) throws ParseException{
		
		Integer orderId=toEditFirOrderDTO.getOrderId();
		Integer bizId=toEditFirOrderDTO.getBizId();
		
		FitOrderVO vo = fitOrderService.selectFitOrderVOById(orderId);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		GroupOrder groupOrder = groupOrderService.findById(orderId);
		int count = productStockService.getRestCountByProductIdAndDate(groupOrder.getProductId(),sdf.parse(groupOrder.getDepartureDate()));
		
		List<DicInfo> jdxjList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		
		List<DicInfo> jtfsList = dicService.getListByTypeCode(BasicConstants.GYXX_JTFS,bizId);
		
		List<DicInfo> zjlxList = dicService.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		
		List<DicInfo> sourceTypeList = dicService.getListByTypeCode(Constants.GUEST_SOURCE_TYPE,bizId);
		
		List<RegionInfo> allProvince = regionService.getAllProvince();
		
		List<RegionInfo> cityList = null;
		if (groupOrder.getProvinceId() != null
				&& groupOrder.getProvinceId() != -1) {
			cityList = regionService.getRegionById(groupOrder.getProvinceId()+ "");
		}
		List<DicInfo> lysfxmList = dicService.getListByTypeCode(BasicConstants.GYXX_LYSFXM, bizId);
		
		ToEditFirOrderResult result=new ToEditFirOrderResult();
		
		result.setAllProvince(allProvince);
		result.setCityList(cityList);
		result.setJdxjList(jdxjList);
		result.setJtfsList(jtfsList);
		result.setLysfxmList(lysfxmList);
		result.setSourceTypeList(sourceTypeList);
		result.setVo(vo);
		result.setZjlxList(zjlxList);
		result.setCount(count);
		
		return result;
	}

	public SaveFitOrderInfoResult saveFitOrderInfo(SaveFitOrderInfoDTO saveFitOrderInfoDTO) throws ParseException{
		
		SaveFitOrderInfoResult result=new SaveFitOrderInfoResult();
		result.setSuccess(false);
		
		Integer orderId;
		FitOrderVO fitOrderVO = saveFitOrderInfoDTO.getFitOrderVO();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer newNum = fitOrderVO.getGroupOrder().getNumAdult()+ fitOrderVO.getGroupOrder().getNumChild();
		Integer oldNum = 0;
		try {
			if (fitOrderVO.getGroupOrder().getId() != null) {
				FitOrderVO vo = fitOrderService.selectFitOrderVOById(fitOrderVO.getGroupOrder().getId());
				oldNum = vo.getGroupOrder().getNumAdult()+ vo.getGroupOrder().getNumChild();
			}
			
			//查出库存(剩余人数)
			int freeCount = productStockService.getRestCountByProductIdAndDate(fitOrderVO.getGroupOrder().getProductId(),
					sdf.parse(fitOrderVO.getGroupOrder().getDepartureDate()));
			
			//实际库存应该是修改前人数+库存
			freeCount = oldNum + freeCount;
			if(newNum > freeCount){
				//如果新增人数大于库存,则不能保存
				result.setError("由于库存剩余数有变化，目前剩余库存不足【" + newNum + "】！实际库存还有【" + freeCount + "】");
				return result;
			}
			
			//FIXME 待修复
			//fitOrderVO.getGroupOrder().setOrderNo(settingCommon.getMyBizCode(request));
			//String bizConfigValue = WebUtils.getBizConfigValue(request,BizConfigConstant.AUTO_MERGE_ORDER);
			String bizConfigValue="";
			
			boolean mergeGroup = false;
			if (bizConfigValue != null && "1".equals(bizConfigValue)) {
				mergeGroup = true;
			}
			
			ProductInfo productInfo = productInfoService.findProductInfoById(fitOrderVO.getGroupOrder().getProductId());
			
			orderId = fitOrderService.saveOrUpdateFitOrderInfo(fitOrderVO,
					saveFitOrderInfoDTO.getCurUserId(), 
					saveFitOrderInfoDTO.getCurUserName(),
							productInfo.getOperatorId(),
							productInfo.getOperatorName(), 
							saveFitOrderInfoDTO.getCurBizId(), 
							//settingCommon.getMyBizCode(request),
							saveFitOrderInfoDTO.getFitOrderVO().getGroupOrder().getOrderNo(),
							mergeGroup);
		} catch (ParseException e) {
			result.setError("操作失败,请检查后重试！");
			return result;
		}
		
		try {
			productStockService.updateStockCount(fitOrderVO.getGroupOrder()
					.getProductId(), sdf.parse(fitOrderVO.getGroupOrder()
					.getDepartureDate()), newNum - oldNum);
		} catch (Exception e) {
			result.setError("更新库存失败！");
			return result;
		}

		result.setSuccess(true);
		result.setOrderId(orderId);
		return result;
	}

	public ToFitOrderListResult toFitOrderList(ToFitOrderListDTO toFitOrderListDTO){
		
		Integer bizId = toFitOrderListDTO.getBizId();
		
		List<DicInfo> pp = dicService.getListByTypeCode(BasicConstants.CPXL_PP,bizId);
		List<RegionInfo> allProvince = regionService.getAllProvince();
		List<DicInfo> sourceTypeList = dicService.getListByTypeCode(Constants.GUEST_SOURCE_TYPE,bizId);
		
		String orgJsonStr=orgService.getComponentOrgTreeJsonStr(bizId);
		String orgUserJsonStr=platformEmployeeService.getComponentOrgUserTreeJsonStr(bizId);
		
		ToFitOrderListResult result=new ToFitOrderListResult();
		
		result.setAllProvince(allProvince);
		result.setOrgJsonStr(orgJsonStr);
		result.setOrgUserJsonStr(orgUserJsonStr);
		result.setPp(pp);
		result.setSourceTypeList(sourceTypeList);
		
		return result;
	}

	public GetFitOrdersDataResult getFitOrdersData(GetFitOrdersDataDTO getFitOrdersDataDTO) throws ParseException {
		
		GroupOrder groupOrder=getFitOrdersDataDTO.getGroupOrder();
		Integer curBizId=getFitOrdersDataDTO.getCurBizId();
		Set<Integer> userIdSet=getFitOrdersDataDTO.getUserIdSet();
		
		if (groupOrder.getDateType() != null && groupOrder.getDateType() == 2) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (!"".equals(groupOrder.getStartTime())) {
				groupOrder.setStartTime(sdf.parse(groupOrder.getStartTime())
						.getTime() + "");
			}
			if (!"".equals(groupOrder.getEndTime())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(groupOrder.getEndTime()));
				calendar.add(Calendar.DAY_OF_MONTH, +1);// 让日期加1
				groupOrder.setEndTime(calendar.getTime().getTime() + "");
			}
		}

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(groupOrder.getSaleOperatorIds())
				&& StringUtils.isNotBlank(groupOrder.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = groupOrder.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(curBizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE
				: groupOrder.getPageSize());
		pageBean.setPage(groupOrder.getPage() == null ? 1 : groupOrder
				.getPage());
		pageBean.setParameter(groupOrder);
		pageBean = groupOrderService.selectFitOrderListPage(pageBean,
				curBizId,
				userIdSet,0);
		List<GroupOrder> list = pageBean.getResult();
		if (list != null && list.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (GroupOrder groupOrder2 : list) {
				if (groupOrder2.getCreateTime() != null) {
					Long createTime = groupOrder2.getCreateTime();
					String dateStr = sdf.format(createTime);
					groupOrder2.setCreateTimeStr(dateStr);
				}

			}
		}

		GroupOrder order = groupOrderService.selectFitOrderTotalCount(
				groupOrder, curBizId,
				userIdSet,0);
		
		GetFitOrdersDataResult result=new GetFitOrdersDataResult();
		
		result.setOrder(order);
		result.setPageBean(pageBean);
		
		return result;
	}
	
	public GroupOrderListResult getSubOrderListData(Integer groupId) {
		List<GroupOrder> orders = groupOrderService.selectSubOrderList(groupId) ;
		
		GroupOrderListResult result=new GroupOrderListResult();
		result.setOrders(orders);
		
		return result;
	}

	public GetInsertFitGroupListResult getInsertFitGroupList(GetInsertFitGroupListDTO 
			getInsertFitGroupListDTO) throws ParseException{
		
		Integer tid=getInsertFitGroupListDTO.getTid();
		TourGroup tourGroup=getInsertFitGroupListDTO.getTourGroup();
		
		if (tid != null) {
			GroupOrder groupOrder = groupOrderService.selectByPrimaryKey(tid);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			tourGroup.setEndTime(sdf.parse(groupOrder.getDepartureDate()));
		}
		
		tourGroup.setGroupMode(0);
		tourGroup.setContainSealedGroup(false);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(tourGroup.getPageSize() == null ? Constants.PAGESIZE: tourGroup.getPageSize());
		pageBean.setPage(tourGroup.getPage());
		pageBean.setParameter(tourGroup);
		
		pageBean = tourGroupService.selectSKGroupListPage(pageBean,
				getInsertFitGroupListDTO.getBizId(),
				getInsertFitGroupListDTO.getUserIdSet());
		
		GetInsertFitGroupListResult result=new GetInsertFitGroupListResult();
		result.setPageBean(pageBean);
		result.setTourGroup(tourGroup);

		return result;
	}

	public BaseStateResult delGroupOrder(DelGroupOrderDTO delGroupOrderDTO) {
		
		Integer id = delGroupOrderDTO.getId();
		Integer bizId = delGroupOrderDTO.getBizId();
		
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(false);
		
		if (airTicketRequestService.doesOrderhaveRequested(bizId, id)) {
			result.setError("删除订单前请先取消机票申请。");
			return result;
		}
		GroupOrder groupOrder = groupOrderService.findById(id);

		//2016-6-23 ou 增加是否审核，是否收款检查
		if (groupOrder.getTotalCash() != null) {
			int a = groupOrder.getTotalCash().compareTo(BigDecimal.ZERO);
			if (a == 1 || a == -1) {
				result.setError("订单已经存在收款，不能删除！");
				return result;
			}
		}
		if (groupOrder.getStateFinance() != null){
			Integer initState = 1;
			if (groupOrder.getStateFinance().equals(initState) ){
				result.setError("订单已经审核，不能删除！");
				return result;
			}
		}
		groupOrder.setState(-1);
		
		groupOrderService.updateGroupOrder(groupOrder);
		if (groupOrder.getPriceId() != null) {
			boolean updateStock = productGroupPriceService.updateStock(
					groupOrder.getPriceId(), groupOrder.getSupplierId(),
					-(groupOrder.getNumAdult() + groupOrder.getNumChild()));
		} else {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				productStockService.updateStockCount(groupOrder.getProductId(),
						sdf.parse(groupOrder.getDepartureDate()),
						-(groupOrder.getNumAdult() + groupOrder.getNumChild()));
			} catch (Exception e) {
				result.setError("更新库存失败！");
				return result;
			}
		}
		
		bookingSupplierService.upateGroupIdAfterDelOrderFromGroup(id);

		result.setSuccess(true);
		return result;
	}

	
	public BaseStateResult beforeInsertGroup(String ids) {
		
		String[] split = ids.split(",");
		
		List<String> datelist = new ArrayList<String>();
		
		for (String id : split) {
			GroupOrder groupOrder = groupOrderService.findById(Integer.parseInt(id));
			datelist.add(groupOrder.getDepartureDate());
		}

		BaseStateResult result=new BaseStateResult();
		if (!MergeGroupUtils.hasSame(datelist)) {
			result.setError("发团日期一致的订单才允许加入到团中!");
			result.setSuccess(false);
		}
		result.setSuccess(true);
		
		return result;
	}

	public BaseStateResult judgeMergeGroup(String ids) {
		
		String[] split = ids.split(",");
		
		List<String> datelist = new ArrayList<String>();
		List<Integer> productlist = new ArrayList<Integer>();
		List<Integer> brandlist = new ArrayList<Integer>();
		List<Integer> statelist = new ArrayList<Integer>();
		
		for (String id : split) {
			GroupOrder groupOrder = groupOrderService.findById(Integer.parseInt(id));
			datelist.add(groupOrder.getDepartureDate());
			productlist.add(groupOrder.getProductId());
			statelist.add(groupOrder.getStateFinance());
			brandlist.add(groupOrder.getProductBrandId());
		}
		
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(false);
		
		if (!MergeGroupUtils.hasSame(datelist)) {
			result.setError("发团日期一致的订单才允许并团!");
			return result;
		}
		
		if (!MergeGroupUtils.hasSame(brandlist)) {
			result.setError("产品品牌一致的订单才允许并团!");
			return result;
		}

		result.setSuccess(true);
		return result;
	}

	public BaseStateResult insertGroupMany(String ids, String code) {
		
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(false);
		
		TourGroup tourGroup = tourGroupService.selectByGroupCode(code);
		if (tourGroup == null) {
			result.setError("未查到该团号对应的散客团信息!");
			return result;
		}
		
		String[] split = ids.split(",");
		for (String str : split) {
			GroupOrder groupOrder = groupOrderService.selectByPrimaryKey(Integer.parseInt(str));
			groupOrder.setGroupId(tourGroup.getId());
			groupOrder.setOperatorId(tourGroup.getOperatorId());
			groupOrder.setOperatorName(tourGroup.getOperatorName());
			groupOrderService.updateGroupOrder(groupOrder);
			tourGroup.setOrderNum(tourGroup.getOrderNum() == null ? 1: tourGroup.getOrderNum() + 1);
			tourGroupService.updateByPrimaryKey(tourGroup);
			groupOrderService.updateGroupPersonNum(tourGroup.getId());
			groupOrderService.updateGroupPrice(tourGroup.getId());

			List<GroupRequirement> list = groupRequirementService.selectByOrderId(Integer.parseInt(str));
			if (list != null && list.size() > 0) {
				for (GroupRequirement groupRequirement : list) {
					groupRequirement.setGroupId(tourGroup.getId());
					groupRequirementService.updateByPrimaryKeySelective(groupRequirement);
				}
			}
			
			bookingSupplierService.updateGroupIdByOrderId(tourGroup.getId(), groupOrder.getId());
		}
		
		result.setSuccess(true);
		return result;
	}

	public BaseStateResult insertGroup(InsertGroupDTO insertGroupDTO) {
		
		Integer id=insertGroupDTO.getId();
		String code=insertGroupDTO.getCode();
		String curUserName=insertGroupDTO.getCurUserName();
		Integer curUserId=insertGroupDTO.getCurUserId();
		
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(false);
		
		TourGroup tourGroup = tourGroupService.selectByGroupCode(code);
		if (tourGroup == null) {
			result.setError("未查到该团号对应的散客团信息!");
			return result;
		}
		
		GroupOrder groupOrder = groupOrderService.selectByPrimaryKey(id);
		groupOrder.setGroupId(tourGroup.getId());
		groupOrder.setOperatorId(curUserId);
		groupOrder.setOperatorName(curUserName);
		groupOrderService.updateGroupOrder(groupOrder);
		tourGroup.setOrderNum(tourGroup.getOrderNum() == null ? 1 : tourGroup.getOrderNum() + 1);
		tourGroupService.updateByPrimaryKey(tourGroup);
		groupOrderService.updateGroupPersonNum(tourGroup.getId());
		groupOrderService.updateGroupPrice(tourGroup.getId());
		
		List<GroupRequirement> list = groupRequirementService.selectByOrderId(id);
		if (list != null && list.size() > 0) {
			for (GroupRequirement groupRequirement : list) {
				groupRequirement.setGroupId(tourGroup.getId());
				groupRequirementService.updateByPrimaryKeySelective(groupRequirement);
			}
		}
		
		bookingSupplierService.updateGroupIdByOrderId(tourGroup.getId(), id);

		result.setSuccess(true);
		return result;
	}

	public GroupOrderListResult toMergeGroup(String ids) {
		
		List<GroupOrder> list = new ArrayList<GroupOrder>();
		String[] split = ids.split(",");
		for (String str : split) {
			GroupOrder groupOrder = groupOrderService.findById(Integer.parseInt(str));
			groupOrder.setGroupOrderGuestList((groupOrderGuestService.selectByOrderId(groupOrder.getId())));
			list.add(groupOrder);
		}
		
		GroupOrderListResult result=new GroupOrderListResult();
		result.setOrders(list);
		
		return result;
	}

	
	public BaseStateResult mergeGroup(MergeGroupDTO mergeGroupDTO) throws ParseException{
		
		
		MergeGroupOrderVO mergeGroupOrderVO = mergeGroupDTO.getMergeGroupOrderVO();
		String bizCode = mergeGroupDTO.getBizCode();
		Integer bizId = mergeGroupDTO.getBizId();
		
		List<GroupOrder> orderList = mergeGroupOrderVO.getOrderList();

		List<MergeGroupOrderVO> result = new ArrayList<MergeGroupOrderVO>();
		for (int i = 0; i < orderList.size();) {
			GroupOrder order = orderList.get(i);
			GroupOrder groupOrder = groupOrderService.findById(order.getId());
			groupOrder.setGroupCode(order.getGroupCode());
			orderList.remove(order);
			MergeGroupOrderVO mov = new MergeGroupOrderVO();
			mov.getOrderList().add(groupOrder);

			for (int j = 0; j < orderList.size();) {
				GroupOrder order2 = orderList.get(j);
				GroupOrder go = groupOrderService.findById(order2.getId());
				go.setGroupCode(order2.getGroupCode());
				// 相同，分组，并加入到组容器集合
				if (go.getGroupCode().equals(groupOrder.getGroupCode())) {
					mov.getOrderList().add(go);
					orderList.remove(order2);
				} else {
					j++;
				}
			}
			result.add(mov);
		}
		// -------------------------------------------------------------------------------
		fitOrderService.mergetGroup(result,bizId,
				mergeGroupOrderVO.getChoseOperator(), 
				mergeGroupOrderVO.getChoseOperatorName(), 
				bizCode,false);

		return new BaseStateResult(true,null);
	}


	public ToImpNotGroupListResult toImpNotGroupList(ToImpNotGroupListDTO toImpNotGroupListDTO) {
		
		GroupOrder groupOrder = toImpNotGroupListDTO.getGroupOrder();
		String idLists = toImpNotGroupListDTO.getIdLists();
		Integer bizId=toImpNotGroupListDTO.getBizId();
		Set<Integer> userIdSet=toImpNotGroupListDTO.getUserIdSet();

		if (null == groupOrder.getEndTime() && null == groupOrder.getDepartureDate()) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			c.set(year, month, 1);
			groupOrder.setDepartureDate(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-01");
			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			groupOrder
					.setEndTime(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));

		}

		String[] split = idLists.split(",");
		List<Integer> intIds = new ArrayList<Integer>();
		for (String id : split) {
			intIds.add(Integer.parseInt(id));
		}

		groupOrder.setIdList(intIds);
		groupOrder.setDateType(1);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(groupOrder.getPageSize() == null ? Constants.PAGESIZE : groupOrder.getPageSize());
		pageBean.setPage(groupOrder.getPage() == null ? 1 : groupOrder.getPage());
		pageBean.setParameter(groupOrder);
		pageBean = groupOrderService.selectNotGroupListPage(pageBean, bizId, userIdSet);
		List<DicInfo> pp = dicService.getListByTypeCode(BasicConstants.CPXL_PP, bizId);

		ToImpNotGroupListResult result = new ToImpNotGroupListResult();

		result.setGroupOrder(groupOrder);
		result.setPageBean(pageBean);
		result.setPp(pp);

		return result;
	}
	
	public BaseStateResult saveTransportInfo(SaveFitOrderInfoDTO saveFitOrderInfoDTO) {
		
		BaseStateResult result=new BaseStateResult();
		try {
			fitOrderService.saveTransportInfo(saveFitOrderInfoDTO.getFitOrderVO());
		} catch (Exception e) {
			result.setError("操作失败,请检查后重试！");
			result.setSuccess(false);
			return result;
		}
		result.setSuccess(true);
		return result;
	}
	
	public BaseStateResult saveGuestInfo(SaveFitOrderInfoDTO saveFitOrderInfoDTO){
		
		BaseStateResult result=new BaseStateResult();
		try {
			fitOrderService.saveGuestInfo(saveFitOrderInfoDTO.getFitOrderVO());
		} catch (Exception e) {
			result.setError("操作失败,请检查后重试！");
			result.setSuccess(false);
			return result;
		}
		
		result.setSuccess(true);
		return result;
	}
}
