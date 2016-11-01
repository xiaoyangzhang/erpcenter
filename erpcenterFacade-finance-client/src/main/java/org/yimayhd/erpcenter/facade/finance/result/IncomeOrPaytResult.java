/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class IncomeOrPaytResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private List<DicInfo> payTypeList;
	private List<SysBizBankAccount> bizAccountList;
	private Map<Integer, String> supplierTypeMapIn;
	private String employee;
	private FinancePay pay;
	private Date currDate;
	
	public List<DicInfo> getPayTypeList() {
		return payTypeList;
	}
	public void setPayTypeList(List<DicInfo> payTypeList) {
		this.payTypeList = payTypeList;
	}
	public List<SysBizBankAccount> getBizAccountList() {
		return bizAccountList;
	}
	public void setBizAccountList(List<SysBizBankAccount> bizAccountList) {
		this.bizAccountList = bizAccountList;
	}
	public Map<Integer, String> getSupplierTypeMapIn() {
		return supplierTypeMapIn;
	}
	public void setSupplierTypeMapIn(Map<Integer, String> supplierTypeMapIn) {
		this.supplierTypeMapIn = supplierTypeMapIn;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public FinancePay getPay() {
		return pay;
	}
	public void setPay(FinancePay pay) {
		this.pay = pay;
	}
	public Date getCurrDate() {
		return currDate;
	}
	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}
}
