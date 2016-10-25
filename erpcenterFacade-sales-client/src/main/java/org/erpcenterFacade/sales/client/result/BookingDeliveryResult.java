/**
 * 
 */
package org.erpcenterFacade.sales.client.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;

/**
 * @ClassName: BookingDeliveryResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月25日
 */
public class BookingDeliveryResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -5509307324118124849L;
	List<BookingDelivery> bookingDeliveries;
	boolean groupCanEdit;
	public List<BookingDelivery> getBookingDeliveries() {
		return bookingDeliveries;
	}
	public void setBookingDeliveries(List<BookingDelivery> bookingDeliveries) {
		this.bookingDeliveries = bookingDeliveries;
	}
	public boolean isGroupCanEdit() {
		return groupCanEdit;
	}
	public void setGroupCanEdit(boolean groupCanEdit) {
		this.groupCanEdit = groupCanEdit;
	}
	
	
}
