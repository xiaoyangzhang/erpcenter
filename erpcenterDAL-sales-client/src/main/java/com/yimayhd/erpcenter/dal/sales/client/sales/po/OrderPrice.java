package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;

public class OrderPrice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Float priceSettlementAdult;// 结算价格-成人

	private Float priceSettlementChild;// 结算价格-儿童

	private Float priceSettlementRoomeSpread;// 结算价格-单房差

	private Float priceCostAdult;// 成本价-成人

	private Float priceCostChild;// 成本价-儿童

	private Float priceCostRoomSpread;// 成本价-单房差

	public Float getPriceSettlementAdult() {
		return priceSettlementAdult;
	}

	public void setPriceSettlementAdult(Float priceSettlementAdult) {
		this.priceSettlementAdult = priceSettlementAdult;
	}

	public Float getPriceSettlementChild() {
		return priceSettlementChild;
	}

	public void setPriceSettlementChild(Float priceSettlementChild) {
		this.priceSettlementChild = priceSettlementChild;
	}

	public Float getPriceSettlementRoomeSpread() {
		return priceSettlementRoomeSpread;
	}

	public void setPriceSettlementRoomeSpread(Float priceSettlementRoomeSpread) {
		this.priceSettlementRoomeSpread = priceSettlementRoomeSpread;
	}

	public Float getPriceCostAdult() {
		return priceCostAdult;
	}

	public void setPriceCostAdult(Float priceCostAdult) {
		this.priceCostAdult = priceCostAdult;
	}

	public Float getPriceCostChild() {
		return priceCostChild;
	}

	public void setPriceCostChild(Float priceCostChild) {
		this.priceCostChild = priceCostChild;
	}

	public Float getPriceCostRoomSpread() {
		return priceCostRoomSpread;
	}

	public void setPriceCostRoomSpread(Float priceCostRoomSpread) {
		this.priceCostRoomSpread = priceCostRoomSpread;
	}
	
	
	
}
