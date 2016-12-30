package com.yimayhd.erpcenter.facade.sales.service;

import com.yihg.mybatis.utility.PageBean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by DELL on 2016/12/30.
 */
public class QueryResRoomExtraBedResult implements Serializable {
    private static final long serialVersionUID = 740234721783874207L;

    private PageBean pageBean;
    private Map<String,Object> map;

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
