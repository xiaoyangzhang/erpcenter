package com.yimayhd.erpcenter.facade.sales.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGuidesVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.facade.sales.result.BookingGuideResult;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.result.WebResult;

public interface BookingGuideFinanceFacade {

	PageBean getGuideGroupList2(PageBean pageBean,TourGroupVO group, Set<Integer> set);
	
	ResultSupport changeStateLock(Integer groupId);
	
	ResultSupport changeStateUnlock(Integer groupId);
	
	BookingGuideResult guideDetailList(Integer groupId);

	List<BookingGuidesVO> guideDetailListView(Integer groupId);
	
	WebResult<Map<Object, Object>> saveGuide(BookingGuidesVO guidesVO,String bizCode);
	BookingGuideResult toEditGuideView(Integer groupId,Integer bookingGuideId);
	
	ResultSupport financeSave(List<FinanceGuide> financeGuides);
	PageBean bookingGuideList(PageBean pageBean,Integer bizId,Set<Integer> set);
	
	BookingGuideResult createGroupOrder(Integer guideId,Integer num);
}
