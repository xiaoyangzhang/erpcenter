package org.yimayhd.erpcenter.facade.finance.query;

import java.io.Serializable;
import java.math.BigDecimal;

public class DeleteVerifyDetialDTO implements Serializable{
	
	private static final long serialVersionUID = 2667687338405333546L;
	
	private Integer bizId;
	private Integer verifyId;
	private Integer detailId;
	private BigDecimal total;
	private BigDecimal totalCash;
	private BigDecimal totalAdjust;
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getVerifyId() {
		return verifyId;
	}
	public void setVerifyId(Integer verifyId) {
		this.verifyId = verifyId;
	}
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getTotalCash() {
		return totalCash;
	}
	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}
	public BigDecimal getTotalAdjust() {
		return totalAdjust;
	}
	public void setTotalAdjust(BigDecimal totalAdjust) {
		this.totalAdjust = totalAdjust;
	}
	
	
}
