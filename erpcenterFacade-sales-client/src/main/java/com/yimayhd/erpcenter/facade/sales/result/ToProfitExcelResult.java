package com.yimayhd.erpcenter.facade.sales.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

import java.io.Serializable;

/**
 * Created by liyong on 2016/12/14.
 */
public class ToProfitExcelResult extends ResultSupport implements Serializable{

    private PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
    private GroupOrder staticInfo = new GroupOrder();
    private GroupOrder groupOrderProfit = new GroupOrder();

    public PageBean<GroupOrder> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<GroupOrder> pageBean) {
        this.pageBean = pageBean;
    }

    public GroupOrder getStaticInfo() {
        return staticInfo;
    }

    public void setStaticInfo(GroupOrder staticInfo) {
        this.staticInfo = staticInfo;
    }

    public GroupOrder getGroupOrderProfit() {
        return groupOrderProfit;
    }

    public void setGroupOrderProfit(GroupOrder groupOrderProfit) {
        this.groupOrderProfit = groupOrderProfit;
    }
}
