package com.yimayhd.erpcenter.facade.sales.result;

import com.yihg.mybatis.utility.PageBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by DELL on 2016/12/30.
 */
public class QueryResAdminOrderResult implements Serializable {
    private static final long serialVersionUID = 642338556283343514L;

    private PageBean pageBean;
    private Map<String,BigDecimal> map;

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Map<String, BigDecimal> getMap() {
        return map;
    }

    public void setMap(Map<String, BigDecimal> map) {
        this.map = map;
    }
}
