package com.yimayhd.erpcenter.facade.query;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 18:08
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
