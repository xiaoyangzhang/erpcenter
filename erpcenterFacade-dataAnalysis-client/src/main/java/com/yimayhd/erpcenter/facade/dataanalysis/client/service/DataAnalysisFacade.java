package com.yimayhd.erpcenter.facade.dataanalysis.client.service;

import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.facade.dataanalysis.client.query.AirTicketDetailQueriesDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.DeliveryDetailListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetAgeListByProductDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetAirTicketDetailDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetEmployeeIdsDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetPaymentDataDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetUserIdsDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.OpearteGroupListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.PaymentStaticPreviewDTO;
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

	GetNumAndOrderResult searchSupplierByArea(Map map);

	GetNumAndOrderResult findSupplierLevelCode(Map map);

	GetAgeListByProductResult getAgeListByProduct(GetAgeListByProductDTO getAgeListByProductDTO);

	Set<Integer> getUserIdListByOrgIdList(GetUserIdsDTO getUserIdsDTO);

	BookingSupplierDetailListResult getBookingSupplierDetailList(Integer id);

	TranportListResult tranportList(Integer bizId);

	OpearteGroupListResult opearteGroupList(OpearteGroupListDTO opearteGroupListDTO);
}
