package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;

public class ToFactShopResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BookingShop shop;
	
	private List<BookingShopDetailDeploy> detailDeploys;

	public BookingShop getShop() {
		return shop;
	}

	public void setShop(BookingShop shop) {
		this.shop = shop;
	}

	public List<BookingShopDetailDeploy> getDetailDeploys() {
		return detailDeploys;
	}

	public void setDetailDeploys(List<BookingShopDetailDeploy> detailDeploys) {
		this.detailDeploys = detailDeploys;
	}
	
	

}
