package com.yimayhd.erpcenter.facade.sales.result;

import java.util.List;

/**
 * ListResultSupport
 *
 * @author lilin
 * @date 16/10/25
 */
public class ListResultSupport<T> extends ResultSupport {
    public List<T> values;

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
