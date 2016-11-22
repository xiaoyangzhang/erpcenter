/**
 * 
 */
package com.yimayhd.erpcenter.facade.operation.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGuidesVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupGuidePrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.facade.operation.query.BookingGuideQueryDTO;
import com.yimayhd.erpcenter.facade.operation.result.BookingGuideResult;
import com.yimayhd.erpcenter.facade.operation.result.ResultSupport;
import com.yimayhd.erpcenter.facade.operation.result.WebResult;

/**
 * @ClassName: BookingGuideFacade
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月27日
 */
public interface BookingGuideFacade {

	PageBean getGuideGroupList(PageBean pageBean,TourGroupVO group, Set<Integer> set);
	BookingGuideResult guideDetailList(Integer groupId);
	BookingGuideResult guideDetailListView(Integer groupId);
	
	Map<String, Object> moveCoupon(BookingGuideQueryDTO queryDTO);
	
	BookingGuideResult toGuideDetailListView(Integer groupId);
	
	WebResult<Map<String, Object>> saveGuide(BookingGuidesVO guidesVO,String bizCode);
	
	BookingGuideResult toEditGuideView(Integer groupId,Integer bookingGuideId);
	BookingGuideResult financeBill(Integer groupId,Integer bookingGuideId);
	
	PageBean getGuideListByBizId(BookingGuideQueryDTO queryDTO);
	
	ResultSupport deldetailGuide(Integer bookingGuideId);
	
	ResultSupport defTetailGuide(BookingGuide bookingGuide,Integer groupId);
	BookingGuideResult financeSupplierView(FinanceGuide financeGuide);
	List<BookingSupplier> getFinanceSupplierByFinanceGuide(FinanceGuide financeGuide);
	
	ResultSupport financeSave(List<FinanceGuide> financeGuides);
	
	ResultSupport delFinance(BookingGuideQueryDTO queryDTO);
	
	BookingGuideResult bookingGuideListCount(PageBean pageBean,Integer bizId,Set<Integer> set);
	PageBean  bookingGuideListSelect(PageBean pageBean,Integer bizId,Set<Integer> set);
	BookingGuideResult getGuideRouteInfo(Integer groupId);
	BookingGuideResult getGroupRouteInfo(Integer groupId);
	
	List<GroupGuidePrintPo> getOperatorInfo(Integer groupId,Integer id,Integer num);
	
	List<GroupOrderPrintPo> getPrintPoList(Integer groupId);
	BookingGuideResult financeSupplierGuideView(FinanceGuide financeGuide,Integer bizId);
	BookingGuideResult paymentDetail(Integer groupId,Integer bookingId,Integer bizId);
	BookingGuideResult printDetail(Integer groupId,Integer bookingId,Integer bizId);
	BookingGuideResult guideFinance(Integer groupId,Integer bookingGuideId);
	
}
