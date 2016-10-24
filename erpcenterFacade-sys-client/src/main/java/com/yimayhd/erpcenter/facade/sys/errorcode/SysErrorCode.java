package com.yimayhd.erpcenter.facade.sys.errorcode;

import java.io.Serializable;

/**
 * 
 * 描述：SysErrorCode 错误码 和对应信息的封装
 * @author liyong
 * 2016年10月21日
 */
public class SysErrorCode  implements Serializable {
    private static final long serialVersionUID = 1L;
    //任务
    public static final SysErrorCode SYSTEM_ERROR = new SysErrorCode("1001","系统错误");
    public static final SysErrorCode PARAM_ERROR = new SysErrorCode("1002","参数错误");
    public static final SysErrorCode QUERY_ERROR = new SysErrorCode("1003","查询失败");
    public static final SysErrorCode PRODUCT_NO_ROUTE_ERROR = new SysErrorCode("1004","产品内无行程内容");
    public static final SysErrorCode MODIFY_ERROR = new SysErrorCode("1005","操作失败");


    private String errorCode;
    private String errorMsg;

    public SysErrorCode(){

    }

    public SysErrorCode(String errorCode, String errorMsg) {
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
        return (obj != null && obj instanceof SysErrorCode && this.errorCode == ((SysErrorCode) obj).errorCode);
    }
}
