/**
 * 
 */
package com.yimayhd.erpcenter.facade.operation.result;

import java.io.Serializable;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGroup;

/**
 * @ClassName: financeShopResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public class FinanceShopResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private PageBean pageBean;
	private BookingGroup bookingGroup;
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public BookingGroup getBookingGroup() {
		return bookingGroup;
	}
	public void setBookingGroup(BookingGroup bookingGroup) {
		this.bookingGroup = bookingGroup;
	}
	
}
