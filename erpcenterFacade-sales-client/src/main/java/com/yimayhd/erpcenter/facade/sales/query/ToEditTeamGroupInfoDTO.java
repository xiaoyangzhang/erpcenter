package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/26 15:23
 */
public class ToEditTeamGroupInfoDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer groupId;
    private Integer operType;
    private Integer curBizId;
    private Integer bizId;

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public Integer getCurBizId() {
        return curBizId;
    }

    public void setCurBizId(Integer curBizId) {
        this.curBizId = curBizId;
    }
}
