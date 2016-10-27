package com.yimayhd.erpcenter.facade.supplier.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.facade.supplier.result.ResultSupport;
import com.yimayhd.erpresource.dal.vo.SupplierCarVO;
/**
 * 供应商汽车信息对象封装参数
 * @author liyong
 * 2016年10月25日 
 */
public class SupplierCarVODTO extends ResultSupport implements Serializable{
	
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private SupplierCarVO supplierCarVO =new SupplierCarVO();
	
	public void setSupplierCarVO(SupplierCarVO supplierCarVO) {
		this.supplierCarVO = supplierCarVO;
	}
	public SupplierCarVO getSupplierCarVO() {
		return supplierCarVO;
	}

}
