package com.yimayhd.erpcenter.dal.sales.client.sales.query;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by DELL on 2016/12/12.
 */
public class GroupOrderQueryForCarCar implements Serializable {
    private static final long serialVersionUID = -3003044803922816125L;

    private Set<Integer> groupIdSet;

    public Set<Integer> getGroupIdSet() {
        return groupIdSet;
    }

    public void setGroupIdSet(Set<Integer> groupIdSet) {
        this.groupIdSet = groupIdSet;
    }
}

