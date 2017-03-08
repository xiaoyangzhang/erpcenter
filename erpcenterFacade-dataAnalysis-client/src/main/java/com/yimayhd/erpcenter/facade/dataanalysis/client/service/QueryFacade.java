package com.yimayhd.erpcenter.facade.dataanalysis.client.service;

import com.yimayhd.erpcenter.facade.dataanalysis.client.query.QueryDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GuestInfoStatisticsResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.QueryResult;

import java.util.Map;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/11/1 11:41
 */
public interface QueryFacade {

    public QueryResult productTrendTableList(QueryDTO queryDTO);
    public QueryResult productTrendList(QueryDTO queryDTO);
    public QueryResult receivePersonCountTable(QueryDTO queryDTO);
    public QueryResult departmentOrderList(QueryDTO queryDTO);
    public QueryResult groupProfitExportExcel(QueryDTO queryDTO);
    public QueryResult settleListPage(QueryDTO queryDTO);
    public QueryResult groupProfitPreview(QueryDTO queryDTO);
    public QueryResult settleList();
    public GuestInfoStatisticsResult guestInfoStatistics(QueryDTO queryDTO);
    public QueryResult supplierGuestSourceShop(QueryDTO queryDTO);
    public QueryResult supplierGuestSourceShopList();
    public QueryResult groupDateQueryData(QueryDTO queryDTO);
    public QueryResult deservedCashTable(QueryDTO queryDTO);
    public QueryResult deservedCashList(QueryDTO queryDTO);
    public QueryResult productGuestNumTable(QueryDTO queryDTO);
    public QueryResult exportExcel7(QueryDTO queryDTO);
    public QueryResult productSourcePreview(QueryDTO queryDTO);
    public QueryResult exportExcel6(QueryDTO queryDTO);
    public QueryResult productGuestSourceShoppingStatics(QueryDTO queryDTO);
    public QueryResult productGuestSourceShoppingList();
    public QueryResult productProfitList(QueryDTO queryDTO);
    public QueryResult loadOrderId(QueryDTO queryDTO);
    public QueryResult productGuestDetailPreview(QueryDTO queryDTO);
    public QueryResult getAccountDetail(QueryDTO queryDTO);
    public QueryResult departmentOrderListPage(QueryDTO queryDTO);
    public QueryResult toOrdersPreview(QueryDTO queryDTO);


    public QueryResult commonQuery(QueryDTO queryDTO);
    public Map commonQuerySum(QueryDTO queryDTO);
    public Map saleDeliveryDetailList(Map parameters);

  //  public QueryResult toBookingShopList(QueryDTO queryDTO);
   // public QueryResult queryGuestSourceStatics(QueryDTO queryDTO);
   // public QueryResult queryGroupNumber(QueryDTO queryDTO);


}
