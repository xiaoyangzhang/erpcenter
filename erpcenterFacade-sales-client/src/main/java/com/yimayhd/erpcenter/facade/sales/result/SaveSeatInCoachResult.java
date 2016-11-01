package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 16:19
 */
public class SaveSeatInCoachResult extends ResultSupport{
    private int c;
    private GroupOrderTransport groupOrderTransport;

    public GroupOrderTransport getGroupOrderTransport() {
        return groupOrderTransport;
    }

    public void setGroupOrderTransport(GroupOrderTransport groupOrderTransport) {
        this.groupOrderTransport = groupOrderTransport;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}
