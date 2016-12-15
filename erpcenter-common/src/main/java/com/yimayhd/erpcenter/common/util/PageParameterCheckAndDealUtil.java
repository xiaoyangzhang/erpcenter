package com.yimayhd.erpcenter.common.util;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.contants.BasicConstants;

/**
 * Created by DELL on 2016/12/14.
 */
public class PageParameterCheckAndDealUtil {
    public static void pageAndPageSizeCheckAndDealUtil(PageBean pageBean) {
        if (pageBean.getPage() <= 0) {
            pageBean.setPage(BasicConstants.DEFAULT_PAGE);
        }
        if (pageBean.getPageSize() <= 0) {
            pageBean.setPageSize(BasicConstants.DEFAULT_PAGE_SIZE);
        }
    }
}
