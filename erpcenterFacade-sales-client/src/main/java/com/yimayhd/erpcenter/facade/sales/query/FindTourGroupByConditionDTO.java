package com.yimayhd.erpcenter.facade.sales.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

import java.io.Serializable;
import java.util.Set;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/25 18:28
 */
public class FindTourGroupByConditionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private GroupOrder groupOrder;
    private int CurBizId;
    private Set<Integer> dataUserIdSet;

    public Set<Integer> getDataUserIdSet() {
        return dataUserIdSet;
    }

    public void setDataUserIdSet(Set<Integer> dataUserIdSet) {
        this.dataUserIdSet = dataUserIdSet;
    }

    public int getCurBizId() {
        return CurBizId;
    }

    public void setCurBizId(int curBizId) {
        CurBizId = curBizId;
    }

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }
}
