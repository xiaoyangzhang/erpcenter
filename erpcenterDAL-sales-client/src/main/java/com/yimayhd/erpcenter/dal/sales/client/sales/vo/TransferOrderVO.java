package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.TransferOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TransferOrderFamily;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TransferOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TransferOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TransferOrderRoute;

public class TransferOrderVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6755311214985764701L;
	private TransferOrder order;
	private List<TransferOrderFamily>  familyList;
	private List<TransferOrderGuest> guestList;
	private List<TransferOrderPrice> priceList;
	private List<TransferOrderRoute> pouteList;
	public TransferOrder getOrder() {
		return order;
	}
	public void setOrder(TransferOrder order) {
		this.order = order;
	}
	public List<TransferOrderFamily> getFamilyList() {
		return familyList;
	}
	public void setFamilyList(List<TransferOrderFamily> familyList) {
		this.familyList = familyList;
	}
	public List<TransferOrderGuest> getGuestList() {
		return guestList;
	}
	public void setGuestList(List<TransferOrderGuest> guestList) {
		this.guestList = guestList;
	}
	public List<TransferOrderPrice> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<TransferOrderPrice> priceList) {
		this.priceList = priceList;
	}
	public List<TransferOrderRoute> getPouteList() {
		return pouteList;
	}
	public void setPouteList(List<TransferOrderRoute> pouteList) {
		this.pouteList = pouteList;
	}
	
	

}
