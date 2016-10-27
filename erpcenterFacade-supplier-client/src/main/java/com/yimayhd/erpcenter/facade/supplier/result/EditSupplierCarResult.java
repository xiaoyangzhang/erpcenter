package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpresource.dal.vo.SupplierCarVO;
/**
 * 编辑汽车信息页面所需的分装对象
 * @author liyong
 * 2016年10月25日 描述：
 */
public class EditSupplierCarResult extends ResultSupport implements Serializable {
	
	private SupplierCarVO supplierCarVO = new SupplierCarVO();
	
	private List<DicInfo> list = new ArrayList<DicInfo>();

	/**
	 * @return getSupplierCarVO获取 supplierCarVO 的值
	 */
	public SupplierCarVO getSupplierCarVO() {
		return supplierCarVO;
	}

	/**
	 * @param setSupplierCarVO 设置  supplierCarVO 的值
	 */
	public void setSupplierCarVO(SupplierCarVO supplierCarVO) {
		this.supplierCarVO = supplierCarVO;
	}

	/**
	 * @return getList获取 list 的值
	 */
	public List<DicInfo> getList() {
		return list;
	}

	/**
	 * @param setList 设置  list 的值
	 */
	public void setList(List<DicInfo> list) {
		this.list = list;
	}
	
	

}
