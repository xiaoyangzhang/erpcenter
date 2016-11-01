/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryOrder;

/**
 * @ClassName: BookingDeliveryQueryDTO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public class BookingDeliveryQueryDTO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -2363857675969236868L;
	private Integer groupId;
	private Integer bookingId;
	private Integer orderId;
	private Integer BizId;
	private Integer supplierId;
	private Integer groupMode;
	private List<BookingDeliveryOrder> orderList;
	private PageBean pageBean;
	private Integer bizSupplierRelationId;
	
	public Integer getBizSupplierRelationId() {
		return bizSupplierRelationId;
	}
	public void setBizSupplierRelationId(Integer bizSupplierRelationId) {
		this.bizSupplierRelationId = bizSupplierRelationId;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<BookingDeliveryOrder> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<BookingDeliveryOrder> orderList) {
		this.orderList = orderList;
	}
	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getBizId() {
		return BizId;
	}
	public void setBizId(Integer bizId) {
		BizId = bizId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
}
