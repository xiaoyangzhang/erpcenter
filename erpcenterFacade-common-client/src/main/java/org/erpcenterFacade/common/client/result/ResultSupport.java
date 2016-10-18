package org.erpcenterFacade.common.client.result;


import org.erpcenterFacade.common.client.errorcode.ProductErrorCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/17.
 */
public class ResultSupport implements Serializable {
    private static final long serialVersionUID = -2235152751651905167L;
    private boolean success = true;
    private String resultMsg;
    private ProductErrorCode errorCode;

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

    public ProductErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ProductErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
