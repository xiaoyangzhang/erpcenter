package com.yimayhd.erpcenter.biz.sales.client.service.sales.errorCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/17.
 */
public class DoubleCarErrorCode  implements Serializable {
    private static final long serialVersionUID = 1L;
    //任务
    public static final DoubleCarErrorCode SYSTEM_ERROR = new DoubleCarErrorCode("1001","系统错误");
    public static final DoubleCarErrorCode PARAM_ERROR = new DoubleCarErrorCode("1002","参数错误");
    public static final DoubleCarErrorCode QUERY_ERROR = new DoubleCarErrorCode("1003","查询失败");
    public static final DoubleCarErrorCode UPDATE_ERROR = new DoubleCarErrorCode("1004","更新失败");
    public static final DoubleCarErrorCode MODIFY_ERROR = new DoubleCarErrorCode("1005","操作失败");
    public static final DoubleCarErrorCode QUERY_DATE_ERROR = new DoubleCarErrorCode("1006","日期参数错误");


    private String errorCode;
    private String errorMsg;

    public DoubleCarErrorCode(){

    }

    public DoubleCarErrorCode(String errorCode, String errorMsg) {
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
        return (obj != null && obj instanceof DoubleCarErrorCode && this.errorCode == ((DoubleCarErrorCode) obj).errorCode);
    }
}
