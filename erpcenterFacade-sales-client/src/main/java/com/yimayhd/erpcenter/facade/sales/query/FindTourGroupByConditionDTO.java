package com.yimayhd.erpcenter.facade.sales.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

import java.io.Serializable;
import java.util.Set;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/25 18:28
 */
public class FindTourGroupByConditionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private GroupOrder groupOrder;
    private int CurBizId;
    private Set<Integer> dataUserIdSet;
    private Integer operatorType;
    private Integer rows;
    private Integer pageSize;
    private Integer page;
    
    public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	public Set<Integer> getDataUserIdSet() {
        return dataUserIdSet;
    }

    public void setDataUserIdSet(Set<Integer> dataUserIdSet) {
        this.dataUserIdSet = dataUserIdSet;
    }

    public int getCurBizId() {
        return CurBizId;
    }

    public void setCurBizId(int curBizId) {
        CurBizId = curBizId;
    }

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }

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
}
