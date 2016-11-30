package com.yimayhd.erpcenter.facade.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yimayhd.erpcenter.biz.product.service.TrafficResBiz;
import com.yimayhd.erpcenter.dal.product.po.TrafficRes;
import com.yimayhd.erpcenter.facade.service.TrafficResFacade;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/25.
 */
public class TrafficResFacadeImpl implements TrafficResFacade {
     @Autowired
    TrafficResBiz trafficResBiz;
    @Override
    public TrafficRes selectTrafficResAndLineInfoById1(Integer id) {
        return trafficResBiz.selectTrafficResAndLineInfoById1(id);
    }
}
