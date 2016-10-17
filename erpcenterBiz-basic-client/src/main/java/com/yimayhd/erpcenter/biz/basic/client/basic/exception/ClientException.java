package com.yimayhd.erpcenter.biz.basic.client.basic.exception;

/**
 * 展示到前端页面提示的异常
 * 配合PostHandler注解使用
 * 支持在Service使用该异常
 * 
 * @author Jing.Zhuo
 * @create 2015年7月23日 下午8:55:56
 */
public class ClientException extends RuntimeException {

    private static final long serialVersionUID = 3629354685413780033L;

    public ClientException() {
        super();
    }

    public ClientException(String msg) {
        super(msg);
    }

    public ClientException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }
}
