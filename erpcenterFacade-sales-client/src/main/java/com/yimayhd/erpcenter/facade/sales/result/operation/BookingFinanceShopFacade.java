/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.result.operation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingShopDetailDeployVO;
import com.yimayhd.erpcenter.facade.sales.query.BookingFinanceShopQueryDTO;
import com.yimayhd.erpcenter.facade.sales.result.BookingShopResult;
import com.yimayhd.erpcenter.facade.sales.result.FinanceShopResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;

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
	String toSaveExcelData(BookingFinanceShopQueryDTO queryDTO) ;
}
