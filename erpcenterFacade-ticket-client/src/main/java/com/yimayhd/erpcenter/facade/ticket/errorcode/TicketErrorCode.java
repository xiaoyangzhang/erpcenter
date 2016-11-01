/**
 * 
 */
package com.yimayhd.erpcenter.facade.ticket.errorcode;

import java.io.Serializable;

/**
 * @ClassName: FinanceErrorCode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author hongfei.guo
 * @date 2016年10月25日
 */
public class TicketErrorCode implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -2487018306971150021L;
	public static final TicketErrorCode UNABLE_EDIT_ERROR = new TicketErrorCode("2000", "该团已审核或封存，不允许修改该信息");
	public static final TicketErrorCode SYSTEM_ERROR = new TicketErrorCode("1001","系统错误");
    public static final TicketErrorCode PARAM_ERROR = new TicketErrorCode("1002","参数错误");
    public static final TicketErrorCode QUERY_ERROR = new TicketErrorCode("1003","查询失败");
    public static final TicketErrorCode MODIFY_ERROR = new TicketErrorCode("1005","操作失败");

	 public TicketErrorCode() {
		super();
	}
	public TicketErrorCode(String errorCode, String errorMsg) {
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
