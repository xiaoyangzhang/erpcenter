package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpresource.dal.vo.SupplierVO;
/**
 *  跳转到银行、发票等其他信息页 返回结构封装
 * @author liyong
 * 2016年10月25日 描述：
 */
public class BusinessInfoResult extends ResultSupport implements Serializable {

	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private SupplierVO supplierVO = new SupplierVO();
	private Integer supplierType;

	private List<DicInfo> bankList = new ArrayList<DicInfo>();

	public SupplierVO getSupplierVO() {
		return supplierVO;
	}

	public void setSupplierVO(SupplierVO supplierVO) {
		this.supplierVO = supplierVO;
	}

	public Integer getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}

	public List<DicInfo> getBankList() {
		return bankList;
	}

	public void setBankList(List<DicInfo> bankList) {
		this.bankList = bankList;
	}
	



}
