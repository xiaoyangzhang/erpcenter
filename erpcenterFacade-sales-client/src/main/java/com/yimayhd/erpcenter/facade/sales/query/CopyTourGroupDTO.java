package com.yimayhd.erpcenter.facade.sales.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

import java.io.Serializable;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/26 17:15
 */
public class CopyTourGroupDTO  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String info;
    private Integer orderId;
    private Integer groupId;
    private GroupOrder groupOrder;
    private TourGroup tourGroup;
    private Integer curBizId;
    private Integer curUserOrgId;
    private Integer curUserId;
    private String curUserName;

    public String getCurUserName() {
        return curUserName;
    }

    public void setCurUserName(String curUserName) {
        this.curUserName = curUserName;
    }

    public Integer getCurUserId() {
        return curUserId;
    }

    public void setCurUserId(Integer curUserId) {
        this.curUserId = curUserId;
    }

    public Integer getCurBizId() {
        return curBizId;
    }

    public void setCurBizId(Integer curBizId) {
        this.curBizId = curBizId;
    }

    public Integer getCurUserOrgId() {
        return curUserOrgId;
    }

    public void setCurUserOrgId(Integer curUserOrgId) {
        this.curUserOrgId = curUserOrgId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = tourGroup;
    }
}
