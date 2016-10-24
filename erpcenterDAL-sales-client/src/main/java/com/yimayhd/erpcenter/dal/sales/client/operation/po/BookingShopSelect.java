package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookingShopSelect implements Serializable{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer shopCount;//进店数
	private BigDecimal factSales;//实际销售
	private BigDecimal jhSales;//计划销售
	private BigDecimal personRepayTotal;//人员返款
	private BigDecimal totalRepay;//购物返款
	private Integer auditCount; // 已审核的店数
	public Integer getShopCount() {
		return shopCount;
	}
	public void setShopCount(Integer shopCount) {
		this.shopCount = shopCount;
	}
	public BigDecimal getFactSales() {
		return factSales;
	}
	public void setFactSales(BigDecimal factSales) {
		this.factSales = factSales;
	}
	public BigDecimal getJhSales() {
		return jhSales;
	}
	public void setJhSales(BigDecimal jhSales) {
		this.jhSales = jhSales;
	}
	public BigDecimal getPersonRepayTotal() {
		return personRepayTotal;
	}
	public void setPersonRepayTotal(BigDecimal personRepayTotal) {
		this.personRepayTotal = personRepayTotal;
	}
	public BigDecimal getTotalRepay() {
		return totalRepay;
	}
	public void setTotalRepay(BigDecimal totalRepay) {
		this.totalRepay = totalRepay;
	}
	public Integer getAuditCount() {
		return auditCount;
	}
	public void setAuditCount(Integer auditCount) {
		this.auditCount = auditCount;
	}
	
	public boolean isAudited(){
		return (this.shopCount<=this.auditCount)?true:false;
	}
	
    
}