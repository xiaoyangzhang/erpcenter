package com.yimayhd.erpcenter.facade.dataanalysis.client.service;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.AirTicketDetailQueriesDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.DeliveryDetailListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetAgeListByProductDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetAirTicketDetailDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetEmployeeIdsDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetNumAndOrderDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetPaymentDataDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GroupOrderDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.OpearteGroupListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.PaymentStaticPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ProductGuestConditionDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.SaleOperatorExcelDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ShopInfoDetailDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ShopSelectListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToOperatorGroupStaticTableDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToOrdersPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToSaleOperatorOrderStaticTableDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToSaleOperatorPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.AirTicketDetailQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.AllProvinceResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.BookingSupplierDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.DataAnalysisWebResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.DeliveryDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetAgeListByProductResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetAirTicketDetailResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetLevelNameResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetNumAndOrderResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetOrdersResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetOrgAndUserTreeJsonStrResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetPaymentDataResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.HotelQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.OpearteGroupListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.PaymentStaticPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.RestaurantQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.SaleOperatorExcelResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopInfoDetailResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopSelectListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToOperatorGroupStaticTableResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToOrdersPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToPaymentPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorOrderStaticTableResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorTableResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.TranportListResult;

/**
 * 
 * @author gaotingping
 * 2016年10月31日
 */
public interface DataAnalysisFacade {

	GetOrgAndUserTreeJsonStrResult getOrgAndUserTreeJsonStr(Integer bizId);

	ToSaleOperatorPreviewResult toSaleOperatorPreview(ToSaleOperatorPreviewDTO toSaleOperatorPreviewDTO);

	ToSaleOperatorListResult toSaleOperatorList();

	ToSaleOperatorTableResult toSaleOperatorTable(ToSaleOperatorPreviewDTO toSaleOperatorPreviewDTO);

	GetLevelNameResult getLevelName(String code);

	SaleOperatorExcelResult saleOperatorExcel(SaleOperatorExcelDTO saleOperatorExcelDTO);

	ToSaleOperatorOrderStaticTableResult toSaleOperatorOrderStaticTable(
			ToSaleOperatorOrderStaticTableDTO toSaleOperatorOrderStaticTableDTO);

	ToOperatorGroupStaticTableResult toOperatorGroupStaticTable(
			ToOperatorGroupStaticTableDTO toOperatorGroupStaticTableDTO);

	AllProvinceResult getAllProvince();

	ToOrdersPreviewResult toOrdersPreview(ToOrdersPreviewDTO toOrdersPreviewDTO);

	ToPaymentPreviewResult toPaymentPreview(ToOrdersPreviewDTO toOrdersPreviewDTO);

	GetOrdersResult getOrders(ToOrdersPreviewDTO toOrdersPreviewDTO);

	GetPaymentDataResult getPaymentData(GetPaymentDataDTO getPaymentDataDTO);

	ShopInfoDetailResult shopInfoDetail(ShopInfoDetailDTO shopInfoDetailDTO);

	ShopDetailListResult shopDetailList(Integer id);

	ShopSelectListResult shopSelectList(ShopSelectListDTO shopSelectListDTO);

	String getEmployeeIds(GetEmployeeIdsDTO getEmployeeIdsDTO);

	PaymentStaticPreviewResult paymentStaticPreview(PaymentStaticPreviewDTO paymentStaticPreviewDTO);

	PaymentStaticPreviewResult paymentStaticExport(PaymentStaticPreviewDTO paymentStaticPreviewDTO);

	RestaurantQueriesResult restaurantQueries(Integer bizId);

	RestaurantQueriesResult restaurantBooking(Integer bizId);

	RestaurantQueriesResult restaurantJSFS(Integer bizId);

	RestaurantQueriesResult restaurantDetailList(Integer bizId);

	HotelQueriesResult hotelQueries(Integer bizId);

	RestaurantQueriesResult hotelBookingQueries(Integer bizId);

	RestaurantQueriesResult hotelJSFS(Integer bizId);

	RestaurantQueriesResult hoteldetailQueries(Integer bizId);

	RestaurantQueriesResult fleetQueries(Integer bizId);

	RestaurantQueriesResult fleetDetailList(Integer bizId);

	RestaurantQueriesResult fleetJSFSList(Integer bizId);

	RestaurantQueriesResult entertainmentDetailQueries(Integer bizId);

	RestaurantQueriesResult sightList(Integer bizId);

	RestaurantQueriesResult sightBookingList(Integer bizId);

	RestaurantQueriesResult sightJSFS(Integer bizId);

	RestaurantQueriesResult sightDetailList(Integer bizId);

	RestaurantQueriesResult airTicketQueries(Integer bizId);

	RestaurantQueriesResult airTicketBookingQueries(Integer bizId);

	RestaurantQueriesResult airTicketJSFS(Integer bizId);

	AirTicketDetailQueriesResult airTicketDetailQueries(AirTicketDetailQueriesDTO airTicketDetailQueriesDTO);

	RestaurantQueriesResult trainTicketQueries(Integer bizId);

	RestaurantQueriesResult trainTicketBookingQueries(Integer bizId);

	RestaurantQueriesResult trainTicketJSFS(Integer bizId);

	AirTicketDetailQueriesResult trainTicketDetailQueries(AirTicketDetailQueriesDTO airTicketDetailQueriesDTO);

	RestaurantQueriesResult insuranceQueries(Integer bizId);

	RestaurantQueriesResult insuranceDetailQueries(Integer bizId);

	RestaurantQueriesResult insuranceBookingQueries(Integer bizId);

	RestaurantQueriesResult insuranceJSFS(Integer bizId);

	RestaurantQueriesResult incomeQueries(Integer bizId);

	RestaurantQueriesResult incomeDetailQueries(Integer bizId);

	RestaurantQueriesResult outcomeQueries(Integer bizId);

	RestaurantQueriesResult outcomeDetailQueries(Integer bizId);

	DeliveryDetailListResult deliveryDetailList(DeliveryDetailListDTO deliveryDetailListDTO);

	GetAirTicketDetailResult getAirTicketDetail(GetAirTicketDetailDTO getAirTicketDetailDTO);

	GetAirTicketDetailResult getTrainTicketDetail(GetAirTicketDetailDTO getAirTicketDetailDTO);

	GetAgeListByProductResult getAgeListByProduct(GetAgeListByProductDTO getAgeListByProductDTO);

	BookingSupplierDetailListResult getBookingSupplierDetailList(Integer id);

	TranportListResult tranportList(Integer bizId);

	OpearteGroupListResult opearteGroupList(OpearteGroupListDTO opearteGroupListDTO);

	GetNumAndOrderResult getNumAndOrder(GetNumAndOrderDTO getNumAndOrderDTO);

	GetNumAndOrderResult getSupplierOrder(GetNumAndOrderDTO getNumAndOrderDTO);

	GetNumAndOrderResult getSupplierDetails(GetNumAndOrderDTO getNumAndOrderDTO);

	GetNumAndOrderResult getDetail2(GetNumAndOrderDTO getNumAndOrderDTO);
	
	
	
	

	

	public DataAnalysisWebResult<Map<String, Object>> groupBookingList(
			ShopSelectListDTO shopSelectListDTO);


	public DataAnalysisWebResult<PageBean> groupInfoList(ShopSelectListDTO shopSelectListDTO);

	/**
	 * 子公司未收债务查询页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public DataAnalysisWebResult<Map<String, Object>> toSubsidiaryDebt();

	public DataAnalysisWebResult<Map<String, Object>> toSubsidiaryDebt(String sl, String ssl,
			String rp, Integer page, Integer pageSize, String svc,
			ShopSelectListDTO shopSelectListDTO,Map<String, Object> pms);

	

	/* 购物统计-产品 */
	public DataAnalysisWebResult<List<DicInfo>> toProductList(String typecode,Integer bizId);







	public DataAnalysisWebResult<String> productGuestStatics(ProductGuestConditionDTO productGuestConditionDTO);

	

	public DataAnalysisWebResult<Map<String, Object>> queryGroupNumber(GroupOrderDTO groupOrderDTO, Integer type, Integer dataType);

	public DataAnalysisWebResult<Map<String, Object>> expGroupNumber(GroupOrderDTO groupOrderDTO);


	public DataAnalysisWebResult<String> guestSourceStatics(ProductGuestConditionDTO productGuestConditionDTO);



	/**
	 * 购物项目统计
	 * 
	 * @param request
	 * @param model
	 * @param guide
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public DataAnalysisWebResult<PageBean> toBookingShopList(PageBean pageBean,Integer bizId);









	

	/**
	 * 点击团号时，根据团id加载订单id
	 * 
	 * @param request
	 * @param response
	 * @param groupId
	 * @return
	 */
	public DataAnalysisWebResult<Map<String, Object>> loadOrderId(Integer groupId);

	/**
	 * 客户产品人数统计
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
//	@RequestMapping("productWithCustomerList.htm")
//	public String productWithCustomerQueries(HttpServletRequest request,
//			HttpServletResponse response, ModelMap modelMap) {
//		// Integer bizId = WebUtils.getCurBizId(request);
//		// getOrgAndUserTreeJsonStr(modelMap, bizId);
//		modelMap.addAttribute("bizId", WebUtils.getCurBizId(request));
//		return "queries/productWithCustomer/productWithCustomerList";
//	}

//	@RequestMapping("productWithCustomerList.do")
//	public String productWithCustomerList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, String sl, String ssl,
//			String rp, Integer page, Integer pageSize, String svc) {
//		PageBean pb = commonQuery(request, model, sl, page, pageSize, svc);
//		/*
//		 * List result = pb.getResult(); Map paramters =
//		 * WebUtils.getQueryParamters(request); if (result != null &&
//		 * result.size() > 0) { for (int i = 0; i < result.size(); i++) { Map
//		 * map = (Map) result.get(i); String productBrandName = (String)
//		 * map.get("productBrandName"); String productName = (String)
//		 * map.get("productName"); paramters.put("productBrandName",
//		 * productBrandName); paramters.put("productName", productName); int
//		 * buyTotal = detailDeployService .selectBuyTotalByProduct(paramters);
//		 * map.put("buyTotal", buyTotal); } }
//		 */
//		return rp;
//
//	}


	public DataAnalysisWebResult<Integer> productProfitList(Map paramters);

	/**
	 * 其他收入、其他支出预览页面
	 * 
	 * @param request
	 * @param reponse
	 * @param model
	 * @param sl
	 * @param ssl
	 * @param rp
	 * @param page
	 * @param pageSize
	 * @param svc
	 * @return
	 */
//	@RequestMapping(value = "otherDetailPreview.htm")
//	public String otherDetailPreview(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, String sl, String ssl,
//			String rp, Integer page, Integer pageSize, String svc) {
//		PageBean pb = commonQuery(request, model, sl, page, 10000, svc);
//		String imgPath = bizSettingCommon.getMyBizLogo(request);
//		model.addAttribute("imgPath", imgPath);
//
//		model.addAttribute("printName", WebUtils.getCurUser(request).getName());
//		model.addAttribute("supplierType", WebUtils.getQueryParamters(request)
//				.get("supplierType"));
//		return "/queries/otherDetailPreview";
//	}


}
