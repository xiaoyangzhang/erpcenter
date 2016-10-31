package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/31 18:23
 */
public class GuestResult extends ResultSupport {
    private GroupOrderGuest groupOrderGuest;
    private Integer it;
    private String certificateNums;
    private List<GroupOrderGuest> groupOrderGuestList;
    private List<GroupOrder> groupOrderList;

    public List<GroupOrder> getGroupOrderList() {
        return groupOrderList;
    }

    public void setGroupOrderList(List<GroupOrder> groupOrderList) {
        this.groupOrderList = groupOrderList;
    }

    public List<GroupOrderGuest> getGroupOrderGuestList() {
        return groupOrderGuestList;
    }

    public void setGroupOrderGuestList(List<GroupOrderGuest> groupOrderGuestList) {
        this.groupOrderGuestList = groupOrderGuestList;
    }

    public String getCertificateNums() {
        return certificateNums;
    }

    public void setCertificateNums(String certificateNums) {
        this.certificateNums = certificateNums;
    }



    public Integer getIt() {
        return it;
    }

    public void setIt(Integer it) {
        this.it = it;
    }

    public GroupOrderGuest getGroupOrderGuest() {
        return groupOrderGuest;
    }

    public void setGroupOrderGuest(GroupOrderGuest groupOrderGuest) {
        this.groupOrderGuest = groupOrderGuest;
    }
}
