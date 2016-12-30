package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import com.yihg.mybatis.utility.PageBean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by DELL on 2016/12/30.
 */
public class ScheduledQueryResult implements Serializable {


    private static final long serialVersionUID = 5663927100247010413L;

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
