package org.erpcenterFacade.common.client.service;

import java.util.List;

import org.erpcenterFacade.common.client.result.CheckProductStockResult;
import org.erpcenterFacade.common.client.result.ResultSupport;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.product.vo.ProductSupplierCondition;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpresource.dal.po.SupplierDriver;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

/**
 * @ClassName: ${ClassName}
 * @Auther hongfei.guo
 * @Date 2016/10/18 19:48
 */
public interface ComponentFacade {

	List<PlatformOrgPo> getOrgTree(Integer bizId, Integer parentId);
	
	PageBean supplierList(Integer bizId, SupplierInfo supplierInfo,String canEditPrice,Integer orgId);
	
	PageBean getMyDriverList(SupplierDriver driver, Integer bizId, Integer supplierId, Integer page, Integer pageSize);
	
	List<ProductGroupSupplier> selectSupplierList(ProductSupplierCondition condition);
	
	ResultSupport uploadRegion();
	
	CheckProductStockResult checkProductStock(Integer bizId, Integer year, Integer month);

}
