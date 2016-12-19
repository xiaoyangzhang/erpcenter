package com.yimayhd.erpcenter.facade.sales.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

import java.io.Serializable;
import java.util.Set;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/12/14 13:25
 */
public class ToFitGroupTableDTO  implements Serializable {
    private static final long serialVersionUID = 671446424613816424L;
    private Integer rows;
    private Integer pageSize;
    private Integer page;
    private TourGroup tourGroup;
    private Integer curBizId;
    private Set<Integer> userIdSet;

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

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

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = tourGroup;
    }

    public Integer getCurBizId() {
        return curBizId;
    }

    public void setCurBizId(Integer curBizId) {
        this.curBizId = curBizId;
    }

    public Set<Integer> getUserIdSet() {
        return userIdSet;
    }

    public void setUserIdSet(Set<Integer> userIdSet) {
        this.userIdSet = userIdSet;
    }
}
