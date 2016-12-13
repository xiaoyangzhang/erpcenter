package com.yimayhd.erpcenter.biz.sales.client.service.sales.result;

import java.io.Serializable;

import com.yimayhd.erpcenter.biz.sales.client.service.sales.errorCode.DoubleCarErrorCode;

public class ResultSupport implements Serializable {
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -3374909615237307272L;
	private boolean success = true;
    private String resultMsg;
    private DoubleCarErrorCode errorCode;

    public ResultSupport() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public DoubleCarErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(DoubleCarErrorCode errorCode) {
        this.errorCode = errorCode;
        this.success = false;
        this.resultMsg = errorCode.getErrorMsg();
    }

}
