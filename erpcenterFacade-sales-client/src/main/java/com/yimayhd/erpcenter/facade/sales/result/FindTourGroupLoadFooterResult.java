package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/12/14 14:16
 */
public class FindTourGroupLoadFooterResult  extends ResultSupport {

    private GroupOrder groupOrder;

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }
}
