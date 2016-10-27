package com.yimayhd.erpcenter.facade.supplier.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.facade.supplier.result.ResultSupport;
import com.yimayhd.erpresource.dal.po.SupplierGuide;
/**
 * 导游查询参数对象
 * @author liyong
 * 2016年10月25日 描述：
 */
public class SupplierGuideDTO extends ResultSupport implements Serializable{
	
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private SupplierGuide supplierGuide = new SupplierGuide();
	
	public void setSupplierGuide(SupplierGuide supplierGuide) {
		this.supplierGuide = supplierGuide;
	}
	
	public SupplierGuide getSupplierGuide() {
		return supplierGuide;
	}

}
