package org.erpcenterFacade.common.client.errorcode;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/17.
 */
public class ProductErrorCode implements Serializable {
    private static final long serialVersionUID = 1L;
    //任务
    public static final ProductErrorCode SYSTEM_ERROR = new ProductErrorCode("1001","系统错误");
    public static final ProductErrorCode PARAM_ERROR = new ProductErrorCode("1002","参数错误");
    public static final ProductErrorCode QUERY_ERROR = new ProductErrorCode("1003","查询失败");


    private String errorCode;
    private String errorMsg;

    public ProductErrorCode(){

    }

    public ProductErrorCode(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && obj instanceof ProductErrorCode && this.errorCode == ((ProductErrorCode) obj).errorCode);
    }
}
