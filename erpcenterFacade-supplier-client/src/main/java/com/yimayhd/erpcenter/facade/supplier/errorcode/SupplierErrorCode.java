package com.yimayhd.erpcenter.facade.supplier.errorcode;

import java.io.Serializable;


public class SupplierErrorCode  implements Serializable {
    private static final long serialVersionUID = 1L;
    //任务
    public static final SupplierErrorCode SYSTEM_ERROR = new SupplierErrorCode("1001","系统错误");
    public static final SupplierErrorCode PARAM_ERROR = new SupplierErrorCode("1002","参数错误");
    public static final SupplierErrorCode QUERY_ERROR = new SupplierErrorCode("1003","查询失败");
//    public static final SupplierErrorCode PRODUCT_NO_ROUTE_ERROR = new SupplierErrorCode("1004","产品内无行程内容");
    public static final SupplierErrorCode MODIFY_ERROR = new SupplierErrorCode("1005","操作失败");


    private String errorCode;
    private String errorMsg;

    public SupplierErrorCode(){

    }

    public SupplierErrorCode(String errorCode, String errorMsg) {
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
        return (obj != null && obj instanceof SupplierErrorCode && this.errorCode == ((SupplierErrorCode) obj).errorCode);
    }
}
