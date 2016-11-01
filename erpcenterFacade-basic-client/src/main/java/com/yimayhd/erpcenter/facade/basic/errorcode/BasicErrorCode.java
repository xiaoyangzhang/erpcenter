/**
 * 
 */
package com.yimayhd.erpcenter.facade.basic.errorcode;

import java.io.Serializable;

/**
 * @ClassName: FinanceErrorCode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author hongfei.guo
 * @date 2016年10月25日
 */
public class BasicErrorCode implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -2487018306971150021L;
	public static final BasicErrorCode UNABLE_EDIT_ERROR = new BasicErrorCode("2000", "该团已审核或封存，不允许修改该信息");
	public static final BasicErrorCode SYSTEM_ERROR = new BasicErrorCode("1001","系统错误");
    public static final BasicErrorCode PARAM_ERROR = new BasicErrorCode("1002","参数错误");
    public static final BasicErrorCode QUERY_ERROR = new BasicErrorCode("1003","查询失败");
    public static final BasicErrorCode MODIFY_ERROR = new BasicErrorCode("1005","操作失败");

	 public BasicErrorCode() {
		super();
	}
	public BasicErrorCode(String errorCode, String errorMsg) {
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
