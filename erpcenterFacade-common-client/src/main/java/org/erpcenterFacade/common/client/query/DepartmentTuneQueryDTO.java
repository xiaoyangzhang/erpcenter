package org.erpcenterFacade.common.client.query;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 19:51
 */
public class DepartmentTuneQueryDTO {
    private static final long serialVersionUID = 7546504127191622861L;
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
