package com.yimayhd.erpcenter.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.sys.service.SettleApplyBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.service.DicDal;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.query.ProductStockPageQueryDTO;
import com.yimayhd.erpcenter.dal.product.service.ProductInfoDal;
import com.yimayhd.erpcenter.dal.product.service.ProductRemarkDal;
import com.yimayhd.erpcenter.dal.product.solr.manager.ProductSolrQueryManager;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;
import com.yimayhd.erpcenter.dal.sys.service.SettleApplyDal;
import com.yimayhd.erpcenter.facade.query.ToSearchListStateDTO;
import com.yimayhd.erpcenter.facade.service.ProductUpAndDownFrameFacade;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private ProductRemarkBiz productRemarkBiz;
	@Autowired
	private SettleApplyBiz settleApplyBiz;
	@Autowired
	private ProductUpAndDownFrameFacade productUpAndDownFrameFacade;
	@Autowired
	private ProductInfoDal productInfoDal;
	@Autowired
	private ProductSolrQueryManager productSolrQueryManager;
	
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
	
	@RequestMapping(value = "/testSolrState")
	public Object testSolrState(){
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		PageBean<ProductInfo> pageBean=new PageBean<ProductInfo>();
		Map parameters=new HashMap();
		ProductInfo info = new ProductInfo();
		//info.setOrgIds("10");
		info.setCode("YMYZXLYZMYN");
		info.setNameCity("云南");
		//info.setBrandId(299);
		pageBean.setParameter(info);
		//parameters.put("orgId","10");
		
		return productInfoDal.findProductInfos(pageBean,parameters);
//		ToSearchListStateDTO toSearchListStateDTO = new ToSearchListStateDTO();
//		return productUpAndDownFrameFacade.toSearchListState(toSearchListStateDTO);
	}
	
	@RequestMapping(value = "/testQueryStock")
	public Object testQueryStock(){

//		StockStaticCondition stockCondition = new StockStaticCondition();
//		stockCondition.setPage(1);
//		stockCondition.setPageSize(10);
//		
//		try {
//			return productInfoDal.getStockStaticsList2(stockCondition);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		ProductStockPageQueryDTO queryDTO = new ProductStockPageQueryDTO();
		return productSolrQueryManager.searchProductStock(queryDTO);

	}
	
}
