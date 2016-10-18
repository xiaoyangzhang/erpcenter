package com.yimayhd.erpcenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.sys.service.SettleApplyBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.service.DicDal;
import com.yimayhd.erpcenter.dal.product.service.ProductInfoDal;
import com.yimayhd.erpcenter.dal.product.service.ProductRemarkDal;
import com.yimayhd.erpcenter.dal.sys.service.SettleApplyDal;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private ProductRemarkBiz productRemarkBiz;
	@Autowired
	private SettleApplyBiz settleApplyBiz;
	
	@RequestMapping(value = "/testBasicDal")
	public Object testBasicDal(){
		List<DicInfo>  list = dicBiz.getListByTypeCode("YH");
		return list;
	}
	
	@RequestMapping(value = "/testProductDal")
	public Object testProductDal(){
		return productRemarkBiz.findProductRemarkByProductId(1);
	}
	
	@RequestMapping(value = "/testSysDal")
	public Object testSysDal(){
		return settleApplyBiz.getSettleApplyResults();
	}
}
