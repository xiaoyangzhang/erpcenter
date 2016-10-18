package com.yimayhd.erpcenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yimayhd.erpcenter.dal.basic.service.DicDal;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	@Autowired
	private DicDal dicDal;
	
	@RequestMapping(value = "/testDal")
	public String testDal(){
		dicDal.getListByTypeCode("123");
		return "success";
	}
}
