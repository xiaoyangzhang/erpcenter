package com.yimayhd.erpcenter.dal.sales.client.finance.po.solr;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hongfei.guo
 *
 */
public class SubjectSummary implements Serializable {

	private static final long serialVersionUID = 3223416524741369105L;
	
	private int tgGroupId;
	private int tgGroupState;
	private long tgDataStart;
	private int tgOperatorId;
	private int bizId;
	//booking_delivery.id
	private int bdId;
	//booking_supplier.id
	private int bsId;
	private int bsSupplierId;
	private String bsSupplierName;
	private long bsBookingDate;
	private int bsSupplierType;
	private BigDecimal bsTotal;
	private BigDecimal bsTotalCash;
	private String bsCashType;
	private int fgId;
	private BigDecimal fgTotal;
	public int getTgGroupId() {
		return tgGroupId;
	}
	public void setTgGroupId(int tgGroupId) {
		this.tgGroupId = tgGroupId;
	}
	public int getTgGroupState() {
		return tgGroupState;
	}
	public void setTgGroupState(int tgGroupState) {
		this.tgGroupState = tgGroupState;
	}
	public long getTgDataStart() {
		return tgDataStart;
	}
	public void setTgDataStart(long tgDataStart) {
		this.tgDataStart = tgDataStart;
	}
	public int getTgOperatorId() {
		return tgOperatorId;
	}
	public void setTgOperatorId(int tgOperatorId) {
		this.tgOperatorId = tgOperatorId;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public int getBdId() {
		return bdId;
	}
	public void setBdId(int bdId) {
		this.bdId = bdId;
	}
	public int getBsId() {
		return bsId;
	}
	public void setBsId(int bsId) {
		this.bsId = bsId;
	}
	public int getBsSupplierId() {
		return bsSupplierId;
	}
	public void setBsSupplierId(int bsSupplierId) {
		this.bsSupplierId = bsSupplierId;
	}
	public String getBsSupplierName() {
		return bsSupplierName;
	}
	public void setBsSupplierName(String bsSupplierName) {
		this.bsSupplierName = bsSupplierName;
	}
	public long getBsBookingDate() {
		return bsBookingDate;
	}
	public void setBsBookingDate(long bsBookingDate) {
		this.bsBookingDate = bsBookingDate;
	}
	public int getBsSupplierType() {
		return bsSupplierType;
	}
	public void setBsSupplierType(int bsSupplierType) {
		this.bsSupplierType = bsSupplierType;
	}
	public BigDecimal getBsTotal() {
		return bsTotal;
	}
	public void setBsTotal(BigDecimal bsTotal) {
		this.bsTotal = bsTotal;
	}
	public BigDecimal getBsTotalCash() {
		return bsTotalCash;
	}
	public void setBsTotalCash(BigDecimal bsTotalCash) {
		this.bsTotalCash = bsTotalCash;
	}
	public String getBsCashType() {
		return bsCashType;
	}
	public void setBsCashType(String bsCashType) {
		this.bsCashType = bsCashType;
	}
	public int getFgId() {
		return fgId;
	}
	public void setFgId(int fgId) {
		this.fgId = fgId;
	}
	public BigDecimal getFgTotal() {
		return fgTotal;
	}
	public void setFgTotal(BigDecimal fgTotal) {
		this.fgTotal = fgTotal;
	}
	
	
}
