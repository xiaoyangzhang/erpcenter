package com.yimayhd.erpcenter.facade.sales.result;

import java.io.Serializable;

public class FitUpdateStateResult implements Serializable {

	private boolean success=true;
	private String error;

	public FitUpdateStateResult() {
		super();
	}
	
	public FitUpdateStateResult(String error) {
		super();
		this.success = false;
		this.error = error;
	}

	public FitUpdateStateResult(boolean success, String error) {
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
		this.error = error;
	}
}
