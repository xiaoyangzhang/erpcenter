package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;

/**
 * 状态类返回结果封装
 * 
 * @author gaotingping
 * 2016年10月26日
 */
public class BaseStateResult implements Serializable {
	
	private static final long serialVersionUID = 527184517257839033L;
	
	private boolean success=false;
	
	private String error;

	public BaseStateResult() {
		super();
	}
	
	public BaseStateResult(String error) {
		super();
		this.success = false;
		this.error = error;
	}

	public BaseStateResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.success=false;
		this.error = error;
	}
}
