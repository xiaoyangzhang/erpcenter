package com.yimayhd.erpcenter.dal.product.solr.exception;

public class SolrdumpException extends RuntimeException{


    /**
     */
    private static final long serialVersionUID = 1507193782272776036L;

    public SolrdumpException() {
        super();
    }

    public SolrdumpException(String msg) {
        super(msg);
    }

    public SolrdumpException(Throwable cause) {
        super(cause);
    }

    public SolrdumpException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
