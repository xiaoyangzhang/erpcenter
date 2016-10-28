/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class TourGroupDetiailsResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	//旅行团信息
	private TourGroup tourGroup;
	// 销售订单
	private List<GroupOrder> orderList;
	// 购物店收入
	private List<BookingShop> shoppingList;
	// 其他收入
	private List<BookingSupplier> otherList;
	// 地接社支出
	private List<BookingDelivery> deliveryList;
	// 供应商支出
	private List<BookingSupplier> paymentList; 
	
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	public List<GroupOrder> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<GroupOrder> orderList) {
		this.orderList = orderList;
	}
	public List<BookingShop> getShoppingList() {
		return shoppingList;
	}
	public void setShoppingList(List<BookingShop> shoppingList) {
		this.shoppingList = shoppingList;
	}
	public List<BookingSupplier> getOtherList() {
		return otherList;
	}
	public void setOtherList(List<BookingSupplier> otherList) {
		this.otherList = otherList;
	}
	public List<BookingDelivery> getDeliveryList() {
		return deliveryList;
	}
	public void setDeliveryList(List<BookingDelivery> deliveryList) {
		this.deliveryList = deliveryList;
	}
	public List<BookingSupplier> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<BookingSupplier> paymentList) {
		this.paymentList = paymentList;
	}
	
	
}
