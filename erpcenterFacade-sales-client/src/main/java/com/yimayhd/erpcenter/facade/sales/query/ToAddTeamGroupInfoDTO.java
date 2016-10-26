package com.yimayhd.erpcenter.facade.sales.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

import java.io.Serializable;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/26 10:12
 */
public class ToAddTeamGroupInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int curBizId;
    private GroupOrder groupOrder;

    private int groupId;

    public int getCurBizId() {
        return curBizId;
    }

    public void setCurBizId(int curBizId) {
        this.curBizId = curBizId;
    }

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
