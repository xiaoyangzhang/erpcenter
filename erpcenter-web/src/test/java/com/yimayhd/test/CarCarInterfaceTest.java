package com.yimayhd.test;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderForCarCar;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupForCarCar;
import com.yimayhd.erpcenter.dal.sales.client.sales.query.GroupInfoQueryForCarCar;
import com.yimayhd.erpcenter.dal.sales.client.sales.query.GroupOrderQueryForCarCar;
import com.yimayhd.erpcenter.facade.sales.result.WebResult;
import com.yimayhd.erpcenter.facade.sales.service.GroupOrderFacade;
import com.yimayhd.erpcenter.facade.sales.service.TourGroupFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DELL on 2016/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application.xml"})
public class CarCarInterfaceTest {
    @Autowired
    private TourGroupFacade tourGroupFacade;
    @Autowired
    private GroupOrderFacade groupOrderFacade;

    @Test
    public void testInterface() {
//        selectTourGroupsForCarCar();
        selectGroupOrdersForCarCar();
    }

    private void selectTourGroupsForCarCar() {
        PageBean<GroupInfoQueryForCarCar> pageBean  = new PageBean<GroupInfoQueryForCarCar>();
        pageBean.setPage(1);
        pageBean.setPageSize(50);
        GroupInfoQueryForCarCar car = new GroupInfoQueryForCarCar();
        car.setSupplierName("接送公司");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,6,12,0,0,0);
        car.setStartTime(calendar.getTimeInMillis());
        calendar.set(2016,6,13,0,0,0);
        car.setEndTime(calendar.getTimeInMillis());
        pageBean.setParameter(car);
        WebResult<List<TourGroupForCarCar>> result =
                tourGroupFacade.selectGroupInfoWithArrangedTransAndGuideForCarCar(pageBean);
        System.out.println("--------------------"+ JSON.toJSONString(result));
    }

    private void selectGroupOrdersForCarCar() {
        PageBean<GroupOrderQueryForCarCar> pageBean  = new PageBean<GroupOrderQueryForCarCar>();
        pageBean.setPage(1);
        pageBean.setPageSize(50);
        GroupOrderQueryForCarCar car = new GroupOrderQueryForCarCar();
        Set<Integer> set = new HashSet<Integer>();
        set.add(14185);
        set.add(14186);
        car.setGroupIdSet(set);
        pageBean.setParameter(car);
        WebResult<List<GroupOrderForCarCar>> result = groupOrderFacade.selectGroupOrdersInGroupsForCarCar(pageBean);
    }

}
