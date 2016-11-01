/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpresource.dal.po.SupplierGuide;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class ToBookingShopVerifyListlResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private PageBean pageBean;
	private Map<String,Object> sumMap;
	private List<SupplierGuide> supplierGuides;
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public Map<String, Object> getSumMap() {
		return sumMap;
	}
	public void setSumMap(Map<String, Object> sumMap) {
		this.sumMap = sumMap;
	}
	public List<SupplierGuide> getSupplierGuides() {
		return supplierGuides;
	}
	public void setSupplierGuides(List<SupplierGuide> supplierGuides) {
		this.supplierGuides = supplierGuides;
	} 
	
}
