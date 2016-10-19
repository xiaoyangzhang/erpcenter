package com.yimayhd.erpcenter.dal.basic;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yimayhd.erpcenter.dal.basic.po.AirLine;
import com.yimayhd.erpcenter.dal.basic.service.AirLineDal;
import com.yimayhd.erpcenter.dal.basic.utils.DateUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AirLineServiceTest {
	@Resource 
	private AirLineDal airLineDal;
	
	@Ignore
	@Test
	public void findAirLineByCityTest() throws IOException{
//		String depCity = "北京",arrCity = "昆明";
//		List<AirLine> list = service.findAirLineByCity("2015-10-23", depCity, arrCity,null,null);
//		System.out.println("--------------------");
//		System.out.println(list.size());
//		System.out.println("--------------------");
	}
	@Ignore
	@Test
	public void getCityCodeByAirCodeTest(){
//		String air_code = "MU5491",airCodeTest = "111222";
//		Map<String,String> map1 = mapper.findCityCodeByAirCode(air_code);
//		System.out.println("11111111111111111");
//		System.out.println((map1 == null) +"    " + map1.get("dep_city_code") + "  " + map1.get("arr_city_code"));
//		System.out.println("22222222222222222");
//		Map<String,String> map2 = mapper.findCityCodeByAirCode(airCodeTest);
//		System.out.println(map2 == null);		//true
	//	System.out.print(map2.containsKey("dep_city_code"));
	//	System.out.println(map2.containsKey("dep_city_code") ? map2.get("dep_city_code") : "no include");
	}
	@Ignore
	@Test
	public void findAirLineByCodeTest() throws IOException{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH,22);
		List<AirLine> list = airLineDal.findAirLineByCity(cal.getTime(), "北京", "武汉");
		System.out.println(list.size());
		System.out.println("----------------------------");
		for(AirLine airLine : list){
			System.out.println(airLine.getAirCode() + "  " + airLine.getDepTime()+"-----" + airLine.getArrTime());
		}
		System.out.println("----------------------------");
	}
	@Ignore
	@Test
	public void findAirLineByAirCodeTest() throws IOException{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH,1);
		List<AirLine> list = airLineDal.findAirLineByAirCode(DateUtils.format(cal.getTime(), "yyyy-MM-dd"),"ZH9428");
		for(AirLine airLine : list){
			System.out.println(airLine.getAirCode()+"---"+airLine.getDepCity()+"   ---->   " +airLine.getArrCity()+"   ----->" + airLine.getStopCity());
		}
		System.out.println("----------------------------");
	}
}
