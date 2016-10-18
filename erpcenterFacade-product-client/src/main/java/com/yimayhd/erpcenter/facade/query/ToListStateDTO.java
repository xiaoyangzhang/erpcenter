package com.yimayhd.erpcenter.facade.query;

/**
 * Created by Administrator on 2016/10/18.
 */
public class ToListStateDTO {
    private static final long serialVersionUID = 1L;
    private Integer bizId;
    private Byte state;

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
