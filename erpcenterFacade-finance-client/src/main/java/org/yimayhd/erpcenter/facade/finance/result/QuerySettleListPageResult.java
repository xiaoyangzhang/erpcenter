/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.math.BigDecimal;

import com.yihg.mybatis.utility.PageBean;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class QuerySettleListPageResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private BigDecimal sumTotalAmount;
	private BigDecimal sumTotalCash;
	private BigDecimal sumCommTotal;
	private BigDecimal sumCommTotalCash;
	private PageBean pageBean;
	public BigDecimal getSumTotalAmount() {
		return sumTotalAmount;
	}
	public void setSumTotalAmount(BigDecimal sumTotalAmount) {
		this.sumTotalAmount = sumTotalAmount;
	}
	public BigDecimal getSumTotalCash() {
		return sumTotalCash;
	}
	public void setSumTotalCash(BigDecimal sumTotalCash) {
		this.sumTotalCash = sumTotalCash;
	}
	public BigDecimal getSumCommTotal() {
		return sumCommTotal;
	}
	public void setSumCommTotal(BigDecimal sumCommTotal) {
		this.sumCommTotal = sumCommTotal;
	}
	public BigDecimal getSumCommTotalCash() {
		return sumCommTotalCash;
	}
	public void setSumCommTotalCash(BigDecimal sumCommTotalCash) {
		this.sumCommTotalCash = sumCommTotalCash;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
