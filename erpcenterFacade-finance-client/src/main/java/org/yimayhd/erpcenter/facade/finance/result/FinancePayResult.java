package org.yimayhd.erpcenter.facade.finance.result;

import com.yihg.mybatis.utility.PageBean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhangxy on 2017/2/14.
 */
public class FinancePayResult implements Serializable {
    private static final long serialVersionUID = 8206071902529911979L;

    private PageBean pageBean;
    private Map<String,Object> resultMap;

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
