package com.yimayhd.erpcenter.facade.supplier.query;

import java.io.Serializable;

import com.yimayhd.erpresource.dal.vo.SupplierContractVo;
/**
 * 供应商资源分装传参对象
 * @author liyong
 * 2016年10月25日 
 */
public class SupplierContractVoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private SupplierContractVo supplierContractVo;
	
	public void setSupplierContractVo(SupplierContractVo supplierContractVo) {
		this.supplierContractVo = supplierContractVo;
	}
	public SupplierContractVo getSupplierContractVo() {
		return supplierContractVo;
	}
}
