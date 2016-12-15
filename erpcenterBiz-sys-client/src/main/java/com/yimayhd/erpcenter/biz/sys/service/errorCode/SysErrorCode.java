package com.yimayhd.erpcenter.biz.sys.service.errorCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/17.
 */
public class SysErrorCode  implements Serializable {
    private static final long serialVersionUID = 1L;
    //任务
    public static final SysErrorCode SYSTEM_ERROR = new SysErrorCode("1001","系统错误");
    public static final SysErrorCode PARAM_ERROR = new SysErrorCode("1002","参数错误");
    public static final SysErrorCode QUERY_ERROR = new SysErrorCode("1003","查询失败");
    public static final SysErrorCode UPDATE_ERROR = new SysErrorCode("1004","更新失败");
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
