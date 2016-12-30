package com.yimayhd.erpcenter.facade.dataanalysis.client.service;

import java.text.ParseException;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.AirTicketDetailQueriesDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.DeliveryDetailListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetAgeListByProductDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetEmployeeIdsDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetNumAndOrderDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetPaymentDataDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.HotelDetailPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.OpearteGroupListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.PaymentStaticPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ProductGuestStaticsDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.QueryGroupNumberDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.SaleOperatorExcelDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ShopInfoDetailDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ShopSelectListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToBookingShopListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToOperatorGroupStaticTableDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToOrdersPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToSaleOperatorOrderStaticTableDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToSaleOperatorPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.*;

/**
 * 
 * @author gaotingping
 * 2016年10月31日
 */
public interface DataAnalysisFacade {


	ToSaleOperatorPreviewResult toSaleOperatorPreview(PageBean pageBean,ToSaleOperatorPreviewDTO toSaleOperatorPreviewDTO);


	ToSaleOperatorTableResult toSaleOperatorTable(PageBean pageBean,ToSaleOperatorPreviewDTO toSaleOperatorPreviewDTO);


	SaleOperatorExcelResult saleOperatorExcel(PageBean pageBean,SaleOperatorExcelDTO saleOperatorExcelDTO);

	ToSaleOperatorOrderStaticTableResult toSaleOperatorOrderStaticTable(PageBean pageBean,
			ToSaleOperatorOrderStaticTableDTO toSaleOperatorOrderStaticTableDTO);

	ToOperatorGroupStaticTableResult toOperatorGroupStaticTable(PageBean pageBean,
			ToOperatorGroupStaticTableDTO toOperatorGroupStaticTableDTO);


	ToOrdersPreviewResult toOrdersPreview(PageBean pageBean,ToOrdersPreviewDTO toOrdersPreviewDTO);

	ToPaymentPreviewResult toPaymentPreview(PageBean pageBean,ToOrdersPreviewDTO toOrdersPreviewDTO);

	GetOrdersResult getOrders(PageBean pageBean,ToOrdersPreviewDTO toOrdersPreviewDTO);

	GetPaymentDataResult getPaymentData(PageBean pageBean,GetPaymentDataDTO getPaymentDataDTO);

	ShopInfoDetailResult shopInfoDetail(PageBean pageBean,ShopInfoDetailDTO shopInfoDetailDTO);

	ShopDetailListResult shopDetailList(Integer id);

	ShopSelectListResult shopSelectList(PageBean pageBean,ShopSelectListDTO shopSelectListDTO);

	String getEmployeeIds(GetEmployeeIdsDTO getEmployeeIdsDTO);

	PaymentStaticPreviewResult paymentStaticPreview(PageBean pageBean,PaymentStaticPreviewDTO paymentStaticPreviewDTO);

	PaymentStaticPreviewResult paymentStaticExport(PageBean pageBean,PaymentStaticPreviewDTO paymentStaticPreviewDTO);

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

	GetAirTicketDetailResult getAirTicketDetail(PageBean pageBean);

	GetAirTicketDetailResult getTrainTicketDetail(PageBean pageBean);

	GetAgeListByProductResult getAgeListByProduct(GetAgeListByProductDTO getAgeListByProductDTO);

	BookingSupplierDetailListResult getBookingSupplierDetailList(Integer id);

	TranportListResult tranportList(Integer bizId);

	OpearteGroupListResult opearteGroupList(OpearteGroupListDTO opearteGroupListDTO);

	GetNumAndOrderResult getNumAndOrder(GetNumAndOrderDTO getNumAndOrderDTO);

	GetNumAndOrderResult getSupplierOrder(GetNumAndOrderDTO getNumAndOrderDTO);

	GetNumAndOrderResult getSupplierDetails(GetNumAndOrderDTO getNumAndOrderDTO);

	GetNumAndOrderResult getDetail2(GetNumAndOrderDTO getNumAndOrderDTO);

	GroupBookingListResult groupBookingList(ShopSelectListDTO shopSelectListDTO);

	GroupInfoListResult groupInfoList(ShopSelectListDTO shopSelectListDTO);

	ToSubsidiaryDebtResult toSubsidiaryDebt();

	GetNumAndOrderResult toSubsidiaryDebt(GetNumAndOrderDTO getNumAndOrderDTO);

	ToProductListResult toProductList(Integer curBizId);

	ProductGuestStaticsResult productGuestStatics(ProductGuestStaticsDTO productGuestStaticsDTO);

	QueryGroupNumberResult queryGroupNumber(QueryGroupNumberDTO queryGroupNumberDTO);

	ExpGroupNumberResult expGroupNumber(QueryGroupNumberDTO queryGroupNumberDTO) throws ParseException;

	ProductGuestStaticsResult guestSourceStatics(ProductGuestStaticsDTO productGuestStaticsDTO);

	ToBookingShopListResult toBookingShopList(ToBookingShopListDTO toBookingShopListDTO);

	HotelDetailPreviewResult hotelDetailPreview(HotelDetailPreviewDTO hotelDetailPreviewDTO);

	ScheduledQueryResult getScheduledList(PageBean pageBean, Integer bizId, Set<Integer> dataUserIdSet);

}
