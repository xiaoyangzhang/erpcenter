package com.yimayhd.erpcenter.dal.sales.client.tj.po;

import java.io.Serializable;

public class ShopItems implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long bsdId;
	private long bsdGoodsId;
	private String bsdGoodsName;
	private String bsdRepayValStr;
	private long bsdBuyTotal;
	private long bsdRepayTotal;
	
	private long bsId;
	private long bsSupplierId;
	private String bsSupplierName;
	private long bsPersonNum;
	private long bsTotalFace;
	private long bsShopDate;
	
	private long goId;
	private long goSaleOperatorId;
	
	private long tgId;
	private long tgBizId;
	private long tgOperatorId;
	private long tgPrudctBrandId;
	private long tgDateStart;
	private String tgProductName;
	private String tgProductBrandName;
	private int tgGroupMode;
	private int tgGroupState;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getBsdId() {
		return bsdId;
	}
	public void setBsdId(long bsdId) {
		this.bsdId = bsdId;
	}
	public long getBsdGoodsId() {
		return bsdGoodsId;
	}
	public void setBsdGoodsId(long bsdGoodsId) {
		this.bsdGoodsId = bsdGoodsId;
	}
	public String getBsdGoodsName() {
		return bsdGoodsName;
	}
	public void setBsdGoodsName(String bsdGoodsName) {
		this.bsdGoodsName = bsdGoodsName;
	}
	public String getBsdRepayValStr() {
		return bsdRepayValStr;
	}
	public void setBsdRepayValStr(String bsdRepayValStr) {
		this.bsdRepayValStr = bsdRepayValStr;
	}
	public long getBsdBuyTotal() {
		return bsdBuyTotal;
	}
	public void setBsdBuyTotal(long bsdBuyTotal) {
		this.bsdBuyTotal = bsdBuyTotal;
	}
	public long getBsdRepayTotal() {
		return bsdRepayTotal;
	}
	public void setBsdRepayTotal(long bsdRepayTotal) {
		this.bsdRepayTotal = bsdRepayTotal;
	}
	public long getBsId() {
		return bsId;
	}
	public void setBsId(long bsId) {
		this.bsId = bsId;
	}
	public long getBsSupplierId() {
		return bsSupplierId;
	}
	public void setBsSupplierId(long bsSupplierId) {
		this.bsSupplierId = bsSupplierId;
	}
	public String getBsSupplierName() {
		return bsSupplierName;
	}
	public void setBsSupplierName(String bsSupplierName) {
		this.bsSupplierName = bsSupplierName;
	}
	public long getBsPersonNum() {
		return bsPersonNum;
	}
	public void setBsPersonNum(long bsPersonNum) {
		this.bsPersonNum = bsPersonNum;
	}
	public long getBsTotalFace() {
		return bsTotalFace;
	}
	public void setBsTotalFace(long bsTotalFace) {
		this.bsTotalFace = bsTotalFace;
	}
	public long getBsShopDate() {
		return bsShopDate;
	}
	public void setBsShopDate(long bsShopDate) {
		this.bsShopDate = bsShopDate;
	}
	public long getGoId() {
		return goId;
	}
	public void setGoId(long goId) {
		this.goId = goId;
	}
	public long getGoSaleOperatorId() {
		return goSaleOperatorId;
	}
	public void setGoSaleOperatorId(long goSaleOperatorId) {
		this.goSaleOperatorId = goSaleOperatorId;
	}
	public long getTgId() {
		return tgId;
	}
	public void setTgId(long tgId) {
		this.tgId = tgId;
	}
	public long getTgBizId() {
		return tgBizId;
	}
	public void setTgBizId(long tgBizId) {
		this.tgBizId = tgBizId;
	}
	public long getTgOperatorId() {
		return tgOperatorId;
	}
	public void setTgOperatorId(long tgOperatorId) {
		this.tgOperatorId = tgOperatorId;
	}
	public long getTgPrudctBrandId() {
		return tgPrudctBrandId;
	}
	public void setTgPrudctBrandId(long tgPrudctBrandId) {
		this.tgPrudctBrandId = tgPrudctBrandId;
	}
	public long getTgDateStart() {
		return tgDateStart;
	}
	public void setTgDateStart(long tgDateStart) {
		this.tgDateStart = tgDateStart;
	}
	public String getTgProductName() {
		return tgProductName;
	}
	public void setTgProductName(String tgProductName) {
		this.tgProductName = tgProductName;
	}
	public String getTgProductBrandName() {
		return tgProductBrandName;
	}
	public void setTgProductBrandName(String tgProductBrandName) {
		this.tgProductBrandName = tgProductBrandName;
	}
	public int getTgGroupMode() {
		return tgGroupMode;
	}
	public void setTgGroupMode(int tgGroupMode) {
		this.tgGroupMode = tgGroupMode;
	}
	public int getTgGroupState() {
		return tgGroupState;
	}
	public void setTgGroupState(int tgGroupState) {
		this.tgGroupState = tgGroupState;
	}
	
	
	
}
