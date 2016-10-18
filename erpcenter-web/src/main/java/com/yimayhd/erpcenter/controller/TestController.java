package com.yimayhd.erpcenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.service.DicDal;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	//@Autowired
	private DicDal dicDal;
	
	@RequestMapping(value = "/testDal")
	public String testDal(){
		List<DicInfo>  list = dicDal.getListByTypeCode("YH");
		return "success";
	}
}
