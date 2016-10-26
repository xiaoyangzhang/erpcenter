/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.errorcode;

import java.io.Serializable;

/**
 * @ClassName: SaleErrorCode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月25日
 */
public class SaleErrorCode implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -2487018306971150021L;
	public static final SaleErrorCode UNABLE_EDIT_ERROR = new SaleErrorCode("2000", "该团已审核或封存，不允许修改该信息");
	 public SaleErrorCode() {
		super();
	}
	public SaleErrorCode(String errorCode, String errorMsg) {
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
