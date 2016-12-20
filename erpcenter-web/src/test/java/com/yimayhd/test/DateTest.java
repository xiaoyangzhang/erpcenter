package com.yimayhd.test;

import com.yimayhd.erpcenter.facade.sales.utils.DateUtils;
import org.junit.Test;

/**
 * Created by DELL on 2016/12/20.
 */
public class DateTest {

    @Test
    public void testFormatDate() {
        System.out.println(DateUtils.fmt(DateUtils.getMonthFirstDay(),"yyyy-MM-dd"));
        System.out.println(DateUtils.fmt(DateUtils.getMonthLastDay(),"yyyy-MM-dd"));
    }
}
