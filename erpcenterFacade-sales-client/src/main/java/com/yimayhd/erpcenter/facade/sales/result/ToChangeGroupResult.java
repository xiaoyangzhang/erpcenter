package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 11:00
 */
public class ToChangeGroupResult extends ResultSupport{
    private int groupId;
    private TourGroup tourGroup;
    private List<GroupOrder> orderList;
    private GroupOrder groupOrde;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = tourGroup;
    }

    public List<GroupOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<GroupOrder> orderList) {
        this.orderList = orderList;
    }

    public GroupOrder getGroupOrde() {
        return groupOrde;
    }

    public void setGroupOrde(GroupOrder groupOrde) {
        this.groupOrde = groupOrde;
    }
}
