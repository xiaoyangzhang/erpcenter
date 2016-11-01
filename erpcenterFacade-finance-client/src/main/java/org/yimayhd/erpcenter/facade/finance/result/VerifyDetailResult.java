/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerify;

/**
 * @ClassName: VerifyDetailResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class VerifyDetailResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private FinanceVerify verify;
	private String totalPrice;
	private String totalCash;
	private String totalNot;
	private String totalAdjust;
	public FinanceVerify getVerify() {
		return verify;
	}
	public void setVerify(FinanceVerify verify) {
		this.verify = verify;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTotalCash() {
		return totalCash;
	}
	public void setTotalCash(String totalCash) {
		this.totalCash = totalCash;
	}
	public String getTotalNot() {
		return totalNot;
	}
	public void setTotalNot(String totalNot) {
		this.totalNot = totalNot;
	}
	public String getTotalAdjust() {
		return totalAdjust;
	}
	public void setTotalAdjust(String totalAdjust) {
		this.totalAdjust = totalAdjust;
	}
	
	
}
