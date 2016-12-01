/**
 * 
 */
package com.yimayhd.erpcenter.facade.operation.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGuidesVO;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingShopDetailDeployVO;
import com.yimayhd.erpcenter.facade.operation.query.BookingFinanceShopQueryDTO;
import com.yimayhd.erpcenter.facade.operation.result.BookingShopResult;
import com.yimayhd.erpcenter.facade.operation.result.FinanceShopResult;
import com.yimayhd.erpcenter.facade.operation.result.ResultSupport;
import com.yimayhd.erpcenter.facade.operation.result.WebResult;
import com.yimayhd.erpresource.dal.po.SupplierContractPriceDateInfo;

/**
 * @ClassName: BookingFinanceShopFacade
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public interface BookingFinanceShopFacade {

	FinanceShopResult financeShopList(PageBean pageBean,Integer bizId,Set<Integer> set);
	
	BookingShopResult shopDetailList(Integer groupId);
	
	BookingShopResult toEditBookingShop(Integer groupId,Integer type);
	
	BookingShopResult editShop(Integer groupId,Integer bookingShopId);
	
	int saveShop(BookingShop bookingShop,String bizCode);
	int saveShopAndUpdateFinance(BookingShop bookingShop,String bizCode);
	
	void deldetailGuide(Integer bookingId);
	
	BookingShopResult toFactShop(Integer groupId,Integer bookingShopId);
	
	BookingShopResult editFactShop(BookingShopDetailDeploy shopDetailDeploy);
	
	BookingShopDetail getShopDetailById(Integer shopDetailId);
	
	Map<String, Object> saveShopDetail(List<BookingShopDetail> shopDetails,BookingShop bookingShop,String bizCode);
	
	ResultSupport delShopDetail(Integer id,Integer groupId);
	
	ResultSupport saveShopDetail(BookingShopDetailDeployVO deployVO);
	BookingShopResult toAddShop(Integer groupId);
	
	Map<String, Object> getMatchedDriver(Integer guideId,Integer groupId);
	
	ResultSupport delBookingShop(Integer bookingId);
	
	ResultSupport delShopAndDetail(Integer bookingId);
//	String toSaveExcelData(BookingFinanceShopQueryDTO queryDTO) ;


	BookingShopResult findSupplierItemBySupplierId(Integer supplierId);

	BookingShopResult selectByGroupCode(String groupCode);

	WebResult<List<BookingGuidesVO>> selectBookingGuideVoByGroupId(Integer id);

	WebResult<List<SupplierContractPriceDateInfo>> getContractPriceByPramas(Integer bizId, Integer supplierId, Integer supplierItemId, List<Date> dateList);

	WebResult<Boolean> saveShopAndDetail(BookingFinanceShopQueryDTO queryDTO);
}
