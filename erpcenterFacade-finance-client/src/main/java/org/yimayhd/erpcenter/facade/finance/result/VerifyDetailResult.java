/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerify;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerifyDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: VerifyDetailResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class VerifyDetailResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private FinanceVerify verify;
	private BigDecimal totalPrice;
	private BigDecimal totalCash;
	private BigDecimal totalNot;
	private BigDecimal totalAdjust;

	List<FinanceVerifyDetail> financeVerifyDetailSum;
	public FinanceVerify getVerify() {
		return verify;
	}
	public void setVerify(FinanceVerify verify) {
		this.verify = verify;
	}

	public BigDecimal getTotalAdjust() {
		return totalAdjust;
	}

	public void setTotalAdjust(BigDecimal totalAdjust) {
		this.totalAdjust = totalAdjust;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}

	public BigDecimal getTotalNot() {
		return totalNot;
	}

	public void setTotalNot(BigDecimal totalNot) {
		this.totalNot = totalNot;
	}

	public List<FinanceVerifyDetail> getFinanceVerifyDetailSum() {
		return financeVerifyDetailSum;
	}

	public void setFinanceVerifyDetailSum(List<FinanceVerifyDetail> financeVerifyDetailSum) {
		this.financeVerifyDetailSum = financeVerifyDetailSum;
	}
}
