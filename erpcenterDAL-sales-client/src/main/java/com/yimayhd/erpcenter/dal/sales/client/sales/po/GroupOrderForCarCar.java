package com.yimayhd.erpcenter.dal.sales.client.sales.po;


import com.yimayhd.erpcenter.dal.sales.client.car.po.BookingDeliveryPrice;
import com.yimayhd.erpcenter.dal.sales.client.car.po.TransPort;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2016/12/9.
 */
public class GroupOrderForCarCar implements Serializable {
    private static final long serialVersionUID = -707282179483459490L;

    private List<GroupOrder> groupOrderList;
    private String groupCode;


    public List<GroupOrder> getGroupOrderList() {
        return groupOrderList;
    }

    public void setGroupOrderList(List<GroupOrder> groupOrderList) {
        this.groupOrderList = groupOrderList;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

}
