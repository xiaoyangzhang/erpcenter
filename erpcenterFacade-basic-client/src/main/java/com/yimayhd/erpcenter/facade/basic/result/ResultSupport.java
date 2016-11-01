package com.yimayhd.erpcenter.facade.basic.result;


import java.io.Serializable;

import com.yimayhd.erpcenter.facade.basic.errorcode.BasicErrorCode;

/**
 * Created by Administrator on 2016/10/17.
 */
public class ResultSupport implements Serializable {
    private static final long serialVersionUID = -2235152751651905167L;
    private boolean success = true;
    private String resultMsg;
    private BasicErrorCode errorCode;

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

	public BasicErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(BasicErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
