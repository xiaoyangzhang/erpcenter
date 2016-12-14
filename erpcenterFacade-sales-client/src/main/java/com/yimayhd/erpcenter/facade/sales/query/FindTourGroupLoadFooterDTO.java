package com.yimayhd.erpcenter.facade.sales.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

import java.io.Serializable;
import java.util.Set;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/12/14 14:16
 */
public class FindTourGroupLoadFooterDTO implements Serializable {
    private static final long serialVersionUID = -3374909615237307272L;
    private Integer pageSize;
    private Integer page;
    private GroupOrder groupOrder;
    private Integer bizId;
    private Set<Integer> dataUserIds;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Set<Integer> getDataUserIds() {
        return dataUserIds;
    }

    public void setDataUserIds(Set<Integer> dataUserIds) {
        this.dataUserIds = dataUserIds;
    }
}
