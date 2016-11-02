/**
 * 
 */
package com.yimayhd.erpcenter.facade.operation.errorcode;

import java.io.Serializable;

/**
 * @ClassName: SaleErrorCode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月25日
 */
public class OperationErrorCode implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -2487018306971150021L;
	public static final OperationErrorCode UNABLE_EDIT_ERROR = new OperationErrorCode("2000", "该团已审核或封存，不允许修改该信息");
	public static final OperationErrorCode SYSTEM_ERROR = new OperationErrorCode("1001","系统错误");
    public static final OperationErrorCode PARAM_ERROR = new OperationErrorCode("1002","参数错误");
    public static final OperationErrorCode QUERY_ERROR = new OperationErrorCode("1003","查询失败");
    public static final OperationErrorCode PRODUCT_NO_ROUTE_ERROR = new OperationErrorCode("1004","产品内无行程内容");
    public static final OperationErrorCode MODIFY_ERROR = new OperationErrorCode("1005","操作失败");
    public static final OperationErrorCode GUIDE_EXISTED = new OperationErrorCode("1006","该团已安排此导游，不允许重复安排");
    public static final OperationErrorCode CHECKED = new OperationErrorCode("1007","已审核");
    public static final OperationErrorCode CANCEL_APP = new OperationErrorCode("1008","删除订单前请先取消机票申请。");
    public static final OperationErrorCode NOT_FOUND = new OperationErrorCode("1009","未查到该团号对应的散客团信息!");
    public static final OperationErrorCode UNALLOWED_DELETE_CHECKED_ORDER = new OperationErrorCode("1010","该团有已审核的订单,不允许删除！");
    public static final OperationErrorCode UNALLOWED_DELETE_FINANCE_RECORD = new OperationErrorCode("1011","该团有收付款记录,不允许删除！");

	 public OperationErrorCode() {
		super();
	}
	public OperationErrorCode(String errorCode, String errorMsg) {
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
