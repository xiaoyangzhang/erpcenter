/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class SettleListResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private List<DicInfo> payTypeList;
	private List<SysBizBankAccount> bizAccountList;
	private List<DicInfo> bankList;
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
	public List<DicInfo> getBankList() {
		return bankList;
	}
	public void setBankList(List<DicInfo> bankList) {
		this.bankList = bankList;
	}
	
	
}
