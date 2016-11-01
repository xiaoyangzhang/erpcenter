package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.io.Serializable;

/**
 * 公共抽取
 * 
 * @author gaotingping
 * 2016年10月31日
 */
public class BaseResult implements Serializable {
	
	private static final long serialVersionUID = 527184517257839033L;
	
	private boolean success=false;
	
	private String error;

	public BaseResult() {
		super();
	}
	
	public BaseResult(String error) {
		super();
		this.success = false;
		this.error = error;
	}

	public BaseResult(boolean success, String error) {
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
