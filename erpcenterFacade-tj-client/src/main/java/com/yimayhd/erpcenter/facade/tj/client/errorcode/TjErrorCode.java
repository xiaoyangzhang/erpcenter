package com.yimayhd.erpcenter.facade.tj.client.errorcode;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/17.
 */
public class TjErrorCode  implements Serializable {
    private static final long serialVersionUID = 1L;
    //任务
    public static final TjErrorCode SYSTEM_ERROR = new TjErrorCode("1001","系统错误");
    public static final TjErrorCode PARAM_ERROR = new TjErrorCode("1002","参数错误");
    public static final TjErrorCode QUERY_ERROR = new TjErrorCode("1003","查询失败");
    public static final TjErrorCode UPDATE_ERROR = new TjErrorCode("1004","更新失败");
    public static final TjErrorCode MODIFY_ERROR = new TjErrorCode("1005","操作失败");
    public static final TjErrorCode INVENTORY_SHORTAGE = new TjErrorCode("1006","库存不足");
    public static final TjErrorCode DATE_CONVERSION_ERROR = new TjErrorCode("1007","日期转换错误");


    private String errorCode;
    private String errorMsg;

    public TjErrorCode(){

    }

    public TjErrorCode(String errorCode, String errorMsg) {
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
        return (obj != null && obj instanceof TjErrorCode && this.errorCode == ((TjErrorCode) obj).errorCode);
    }
}
