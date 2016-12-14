package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2016/12/9.
 */
public class GroupOrderForCarCar implements Serializable {
    private static final long serialVersionUID = -707282179483459490L;

    private GroupOrder groupOrder;
//    private String groupCode;
    private String guestReresentativeName;

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }
    //    public List<GroupOrder> getGroupOrderList() {
//        return groupOrderList;
//    }
//
//    public void setGroupOrderList(List<GroupOrder> groupOrderList) {
//        this.groupOrderList = groupOrderList;
//    }

//    public String getGroupCode() {
//        return groupCode;
//    }
//
//    public void setGroupCode(String groupCode) {
//        this.groupCode = groupCode;
//    }

    public String getGuestReresentativeName() {
        return guestReresentativeName;
    }

    public void setGuestReresentativeName(String guestReresentativeName) {
        this.guestReresentativeName = guestReresentativeName;
    }
}
