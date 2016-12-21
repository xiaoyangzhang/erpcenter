package com.yimayhd.erpcenter.facade.sales.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/25 18:28
 */
public class FindTourGroupByConditionResult extends ResultSupport  {
    private int pageTotalAudit;
    private int pageTotalChild;
    private int pageTotalGuide;

    private int totalAudit;
    private int totalChild;
    private int totalGuide;

    private GroupOrder order;
    private GroupOrder groupOrder;
    private PageBean<GroupOrder> pageBean;
    private List<GroupOrder> groupOrderList;

    public List<GroupOrder> getGroupOrderList() {
        return groupOrderList;
    }

    public void setGroupOrderList(List<GroupOrder> groupOrderList) {
        this.groupOrderList = groupOrderList;
    }

    public PageBean<GroupOrder> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<GroupOrder> pageBean) {
        this.pageBean = pageBean;
    }

    public int getPageTotalAudit() {
        return pageTotalAudit;
    }

    public void setPageTotalAudit(int pageTotalAudit) {
        this.pageTotalAudit = pageTotalAudit;
    }

    public int getPageTotalChild() {
        return pageTotalChild;
    }

    public void setPageTotalChild(int pageTotalChild) {
        this.pageTotalChild = pageTotalChild;
    }

    public int getPageTotalGuide() {
        return pageTotalGuide;
    }

    public void setPageTotalGuide(int pageTotalGuide) {
        this.pageTotalGuide = pageTotalGuide;
    }

    public GroupOrder getOrder() {
        return order;
    }

    public void setOrder(GroupOrder order) {
        this.order = order;
    }

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }

    public int getTotalAudit() {
        return totalAudit;
    }

    public void setTotalAudit(int totalAudit) {
        this.totalAudit = totalAudit;
    }

    public int getTotalChild() {
        return totalChild;
    }

    public void setTotalChild(int totalChild) {
        this.totalChild = totalChild;
    }

    public int getTotalGuide() {
        return totalGuide;
    }

    public void setTotalGuide(int totalGuide) {
        this.totalGuide = totalGuide;
    }
}
