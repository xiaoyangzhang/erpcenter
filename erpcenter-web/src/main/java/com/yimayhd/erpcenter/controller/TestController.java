package com.yimayhd.erpcenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.car.DoubleCarBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.SettleApplyBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.query.ProductStockPageQueryDTO;
import com.yimayhd.erpcenter.dal.product.service.ProductInfoDal;
import com.yimayhd.erpcenter.dal.product.solr.manager.ProductSolrQueryManager;
import com.yimayhd.erpcenter.facade.service.ProductStockFacade;
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

	@Autowired
	private ProductStockFacade productStockFacade;
	
	@Autowired
	private TourGroupBiz tourGroupBiz;
	
	@Autowired
	private DoubleCarBiz doubleCarBiz;
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	
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
	

	@RequestMapping(value = "/testSalessDal")
	public Object testSalessDal(){
		return tourGroupBiz.getAuditorList();
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
	
	@RequestMapping(value = "/testSelectDeliveryPrice")
	public Object testSelectDeliveryPrice(){
		return doubleCarBiz.selectDeliveryPrice(9146, 1, 10);
	}
	
	@RequestMapping(value = "/testSelectDeliveryPrice2")
	public Object testSelectDeliveryPrice2(){
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(9146);
		ids.add(9130);
		ids.add(9129);
		ids.add(9034);
		ids.add(9032);
		return doubleCarBiz.selectDeliveryPrice(ids, 0, 100);
	}
	
	@RequestMapping(value = "/testSelectOrderGuest")
	public Object testSelectOrderGuest(){
		return doubleCarBiz.selectOrderGuest(2, 1, 10);
	}
	
	@RequestMapping(value = "/testSelectOrderGuest2")
	public Object testSelectOrderGuest2(){
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(2);
		ids.add(3);
		ids.add(35);
		return doubleCarBiz.selectOrderGuest(ids, 0, 100);
	}
	
	@RequestMapping(value = "/testSelectTransport")
	public Object testSelectTransport(){

		return doubleCarBiz.selectTransportByOrderId(1060);
	}
	
	@RequestMapping(value = "/testGetOrgEmployeeListPage1")
	public Object testGetOrgEmployeeListPage1(){
		return platformEmployeeBiz.getOrgEmployeeListPage(5, 1, 100);
	}
	
	@RequestMapping(value = "/testGetOrgEmployeeListPage2")
	public Object testGetOrgEmployeeListPage2(){
		List<Integer> orgIds = new ArrayList<Integer>();
		orgIds.add(5);
		orgIds.add(6);
		return platformEmployeeBiz.getOrgEmployeeListPage(orgIds, 1, 100);
	}
	
	
	
}
