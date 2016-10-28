/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceVerify;

/**
 * @ClassName: QueryVerifyDetailResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class QueryVerifyDetailResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private FinanceVerify verify;
	private List<Map<String, Object>> financeVerifyDetailList;
	
	public FinanceVerify getVerify() {
		return verify;
	}
	public void setVerify(FinanceVerify verify) {
		this.verify = verify;
	}
	public List<Map<String, Object>> getFinanceVerifyDetailList() {
		return financeVerifyDetailList;
	}
	public void setFinanceVerifyDetailList(
			List<Map<String, Object>> financeVerifyDetailList) {
		this.financeVerifyDetailList = financeVerifyDetailList;
	}
	
	
}
