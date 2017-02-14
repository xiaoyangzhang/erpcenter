/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;


/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class SaveSpecialGroupResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private Integer orderId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
}
