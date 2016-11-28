package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import com.yihg.mybatis.utility.PageBean;

import java.io.Serializable;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/11/28 15:25
 */
public class ImportOrderTableResult implements Serializable {
    private static final long serialVersionUID = -5474445530545179886L;

    private PageBean pageBean;
    private Integer groupOrderId;

    public Integer getGroupOrderId() {
        return groupOrderId;
    }

    public void setGroupOrderId(Integer groupOrderId) {
        this.groupOrderId = groupOrderId;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
}
