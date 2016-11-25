package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import com.yihg.sales.po.*;

import java.io.Serializable;
import java.util.List;

public class TransferOrderVO implements Serializable {

	private static final long serialVersionUID = 6755311214985764701L;
	
	private TransferOrder transferOrder;
	private List<TransferOrderFamily>  familys;
	private List<TransferOrderGuest> guests;
	private List<TransferOrderPrice> prices;
	private List<TransferOrderRoute> routes;
	public TransferOrder getTransferOrder() {
		return transferOrder;
	}
	public void setTransferOrder(TransferOrder transferOrder) {
		this.transferOrder = transferOrder;
	}
	public List<TransferOrderFamily> getFamilys() {
		return familys;
	}
	public void setFamilys(List<TransferOrderFamily> familys) {
		this.familys = familys;
	}
	public List<TransferOrderGuest> getGuests() {
		return guests;
	}
	public void setGuests(List<TransferOrderGuest> guests) {
		this.guests = guests;
	}
	public List<TransferOrderPrice> getPrices() {
		return prices;
	}
	public void setPrices(List<TransferOrderPrice> prices) {
		this.prices = prices;
	}
	public List<TransferOrderRoute> getRoutes() {
		return routes;
	}
	public void setRoutes(List<TransferOrderRoute> routes) {
		this.routes = routes;
	}
	
}
