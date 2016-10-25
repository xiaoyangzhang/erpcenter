package com.yimayhd.erpcenter.facade.sales.errorcode;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/17.
 */
public class SalesErrorCode implements Serializable {
    private static final long serialVersionUID = 1L;
    //任务
    public static final SalesErrorCode SYSTEM_ERROR = new SalesErrorCode("1001","系统错误");
    public static final SalesErrorCode PARAM_ERROR = new SalesErrorCode("1002","参数错误");
    public static final SalesErrorCode QUERY_ERROR = new SalesErrorCode("1003","查询失败");
    public static final SalesErrorCode PRODUCT_NO_ROUTE_ERROR = new SalesErrorCode("1004","产品内无行程内容");
    public static final SalesErrorCode MODIFY_ERROR = new SalesErrorCode("1005","操作失败");


    private String errorCode;
    private String errorMsg;

    public SalesErrorCode(){

    }

    public SalesErrorCode(String errorCode, String errorMsg) {
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
        return (obj != null && obj instanceof SalesErrorCode && this.errorCode == ((SalesErrorCode) obj).errorCode);
    }
}
