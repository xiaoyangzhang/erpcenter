/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.errorcode;

import java.io.Serializable;

/**
 * @ClassName: FinanceErrorCode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author hongfei.guo
 * @date 2016年10月25日
 */
public class FinanceErrorCode implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -2487018306971150021L;
	public static final FinanceErrorCode UNABLE_EDIT_ERROR = new FinanceErrorCode("2000", "该团已审核或封存，不允许修改该信息");
	public static final FinanceErrorCode SYSTEM_ERROR = new FinanceErrorCode("1001","系统错误");
    public static final FinanceErrorCode PARAM_ERROR = new FinanceErrorCode("1002","参数错误");
    public static final FinanceErrorCode QUERY_ERROR = new FinanceErrorCode("1003","查询失败");
    public static final FinanceErrorCode MODIFY_ERROR = new FinanceErrorCode("1005","操作失败");

	 public FinanceErrorCode() {
		super();
	}
	public FinanceErrorCode(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	private String errorCode;
	 private String errorMsg;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
