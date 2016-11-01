/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class QuerySettleListResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private List<Map<String, Object>> list;
	private List<BookingGuide> bookingGuides; 
	private List<DicInfo> cashTypes;
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public List<BookingGuide> getBookingGuides() {
		return bookingGuides;
	}
	public void setBookingGuides(List<BookingGuide> bookingGuides) {
		this.bookingGuides = bookingGuides;
	}
	public List<DicInfo> getCashTypes() {
		return cashTypes;
	}
	public void setCashTypes(List<DicInfo> cashTypes) {
		this.cashTypes = cashTypes;
	}
	
	
}
