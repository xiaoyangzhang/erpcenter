package com.yimayhd.erpcenter.facade.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.biz.sys.service.SysDataRightSupplierBiz;
import com.yimayhd.erpcenter.dal.sys.po.SysDataRightSupplier;
import org.erpcenterFacade.common.client.errorcode.ProductErrorCode;
import org.erpcenterFacade.common.client.result.CheckProductStockResult;
import org.erpcenterFacade.common.client.result.ResultSupport;
import org.erpcenterFacade.common.client.service.ComponentFacade;
import org.erpcenterFacade.common.client.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.util.TypeUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupSupplierBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.util.DateUtils;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierDriverBiz;
import com.yimayhd.erpresource.dal.po.SupplierDriver;
import com.yimayhd.erpresource.dal.po.SupplierInfo;
import org.springframework.web.util.WebUtils;

/**
 * @ClassName: ${ClassName}
 * @Auther hongfei.guo
 * @Date 2016/10/18 19:55
 */

public class ComponentFacadeImpl implements ComponentFacade {

	@Autowired
	private PlatformOrgBiz orgBiz;
	
	@Autowired
	private SupplierBiz supplierBiz;
	
	@Autowired
	private SupplierDriverBiz supplierDriverBiz;
	
	@Autowired
	private ProductGroupSupplierBiz productSupplierBiz;
	
	@Autowired
	private RegionBiz regionBiz;
	
	@Autowired
	private ProductInfoBiz productInfoBiz;
	
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	
	@Autowired
	private ProductStockBiz stockBiz;
	@Autowired
	SysDataRightSupplierBiz sysDataRightSupplierBiz;
	@Override
	public List<PlatformOrgPo> getOrgTree(Integer bizId, Integer parentId) {
		
		List<PlatformOrgPo> orgList = orgBiz.getOrgTree(bizId, null);
		return orgList;
	}

	@Override
	public PageBean supplierList(Integer bizId, SupplierInfo supplierInfo) {
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(supplierInfo.getPageSize());
		pageBean.setParameter(supplierInfo);
		pageBean.setPage(supplierInfo.getPage());
		pageBean = supplierBiz.selectPrivateSupplierList(pageBean, bizId);
		return pageBean;
	}

	@Override
	public PageBean getMyDriverList(SupplierDriver driver, Integer bizId, Integer supplierId, Integer page, Integer pageSize) {
		
		PageBean pageBean = supplierDriverBiz.getMyDriverList(driver, bizId, supplierId, page, pageSize);
		return pageBean;
	}

	@Override
	public List<ProductGroupSupplier> selectSupplierList(ProductSupplierCondition condition) {
		List<ProductGroupSupplier> supplierList = productSupplierBiz.selectSupplierList(condition);
		return supplierList;
	}

	@Override
	public ResultSupport uploadRegion() {
		
		ResultSupport result = new ResultSupport();
		try{
			regionBiz.uploadRegion();
		}catch(Exception ex){
			result.setErrorCode(ProductErrorCode.SYSTEM_ERROR);
		}
		return null;
	}

	@Override
	public CheckProductStockResult checkProductStock(Integer bizId, Integer year, Integer month) {
		
		String beginDateStr = year+"-"+(month<10 ? ("0"+month):(""+month))+"-01";
    	String endDateStr = month==12 ? ((year+1)+"-01-01"):(year+"-"+(month<9 ? ("0"+(month+1)):(""+(month+1)))+"-01");    	
    	Date startDate = DateUtils.parse(beginDateStr, "yyyy-MM-dd");
    	Date endDate = DateUtils.parse(endDateStr,"yyyy-MM-dd");
    	List<Map<String,Object>> list = productInfoBiz.getAllId(bizId, 2);
    	StringBuilder sb = new StringBuilder();
    	StringBuilder sqlSb = new StringBuilder();
    	if(list!=null && list.size()>0){
    		int count = DateUtil.getIntervalDays(endDate, startDate);
    		for(Map<String,Object> map : list){    			
    			Integer productId = TypeUtils.castToInt(map.get("id"));
    			sb.append("产品id【"+productId+"】<br>");
    			for(int i=0;i<count;i++){
    				Date itemDate = DateUtils.addDay(startDate, i);
    				Map<String,Object> orderMap = groupOrderBiz.getCountByProductIdAndDate(bizId, productId, itemDate);
    				Integer adult = 0;
    				Integer child = 0;
    				Integer guide = 0;
    				if(orderMap!=null && orderMap.size()>0){
    					adult = TypeUtils.castToInt(orderMap.get("totalAdult"));
    					child = TypeUtils.castToInt(orderMap.get("totalChild"));
        				guide = TypeUtils.castToInt(orderMap.get("totalGuide"));
    				}
    				
    				int orderTotal = adult+child+guide;
    				
    				ProductStock stock = stockBiz.getStockByProductIdAndDate(productId, itemDate);
    				Integer receiveCnt = 0;
    				Integer reserveCnt = 0;
    				if(stock!=null){
    					receiveCnt = stock.getReceiveCount();    					
    					reserveCnt = stock.getReserveCount();
    				}
    				
    				int stockTotal = receiveCnt+reserveCnt;
    				
    				if(orderTotal == stockTotal){
    					//sb.append("产品id【"+productId+"】，日期【"+DateUtils.format(itemDate, "yyyy-MM-dd")+"】对账成功<br>");
    				}else{
    					sb.append("      日期【"+DateUtils.format(itemDate, "yyyy-MM-dd")+"】对账失败："+"订单人数【"+orderTotal+"】库存数【"+stockTotal+"】<br>");
    					sqlSb.append("      update product_stock set receive_count="+orderTotal+" where state=1 and produdct_id="+productId+" and item_date='"+DateUtils.format(itemDate, "yyyy-MM-dd")+"';<br>");
    				}    				
    			}
    		}
    	}
    	
    	CheckProductStockResult result = new CheckProductStockResult();
		result.setSb(sb.toString());
		result.setSqlSb(sqlSb.toString());
		return result;
	}

	@Override
	public void setSupplierIds(SupplierInfo supplierInfo,String canEditPrice, Integer orgId) {
		if(canEditPrice !=null && ( /*supplierInfo.getSupplierType()==1 ||*/ supplierInfo.getStypes().contains("1")) &&canEditPrice.equals("1")){
			supplierInfo.setChooseType(1);
			List<SysDataRightSupplier> lists=sysDataRightSupplierBiz.selectSysDataRightSupplierInOrgIds(orgId);
			String ids = "";
			if(lists !=null &&lists.size()>0){
				for(SysDataRightSupplier item:lists){
					ids += item.getSupplierId() + ",";
				}
				supplierInfo.setSupplierIds(ids.substring(0, ids.length() - 1));
			}
		}
	}


}
