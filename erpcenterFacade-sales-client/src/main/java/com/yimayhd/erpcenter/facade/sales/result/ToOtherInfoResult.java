package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TeamGroupVO;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 15:22
 */
public class ToOtherInfoResult extends ResultSupport {
    private List<GroupOrderPrice> groupOrderPricesZero;
    private List<GroupOrderPrice> groupOrderPricesOne;
    private List<GroupOrderTransport> groupOrderTransports;
    private List<GroupOrderGuest> groupOrderGuests;
    private List<DicInfo> jtfsList;
    private List<DicInfo> zjlxList;
    private List<DicInfo> lysfxmList;
    private GroupOrder groupOrder;

    public List<GroupOrderPrice> getGroupOrderPricesZero() {
        return groupOrderPricesZero;
    }

    public void setGroupOrderPricesZero(List<GroupOrderPrice> groupOrderPricesZero) {
        this.groupOrderPricesZero = groupOrderPricesZero;
    }

    public List<GroupOrderPrice> getGroupOrderPricesOne() {
        return groupOrderPricesOne;
    }

    public void setGroupOrderPricesOne(List<GroupOrderPrice> groupOrderPricesOne) {
        this.groupOrderPricesOne = groupOrderPricesOne;
    }

    public List<GroupOrderTransport> getGroupOrderTransports() {
        return groupOrderTransports;
    }

    public void setGroupOrderTransports(List<GroupOrderTransport> groupOrderTransports) {
        this.groupOrderTransports = groupOrderTransports;
    }

    public List<GroupOrderGuest> getGroupOrderGuests() {
        return groupOrderGuests;
    }

    public void setGroupOrderGuests(List<GroupOrderGuest> groupOrderGuests) {
        this.groupOrderGuests = groupOrderGuests;
    }

    public List<DicInfo> getJtfsList() {
        return jtfsList;
    }

    public void setJtfsList(List<DicInfo> jtfsList) {
        this.jtfsList = jtfsList;
    }

    public List<DicInfo> getZjlxList() {
        return zjlxList;
    }

    public void setZjlxList(List<DicInfo> zjlxList) {
        this.zjlxList = zjlxList;
    }

    public List<DicInfo> getLysfxmList() {
        return lysfxmList;
    }

    public void setLysfxmList(List<DicInfo> lysfxmList) {
        this.lysfxmList = lysfxmList;
    }

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }
}
