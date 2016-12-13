package com.yimayhd.test;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.sales.service.TourGroupFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by DELL on 2016/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:/main/webapp/WEB-INF/application.xml"})
public class CarCarInterfaceTest {
    @Autowired
    private TourGroupFacade tourGroupFacade;


    @Test
    public void testTourGroupForCarCar() {
        selectTourGroupsForCarCar();
    }

    private void selectTourGroupsForCarCar() {
        //PageBean<GroupInfoQuery>
       // tourGroupFacade.selectGroupInfoWithArrangedTransAndGuideForCarCar();
    }

}
