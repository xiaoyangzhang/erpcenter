package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpresource.dal.po.SupplierItem;
import com.yimayhd.erpresource.dal.vo.SupplierContractVo;
/**
 * 
 * @author liyong
 * 2016年10月25日 
 */
public class NewContractPagaResult extends ResultSupport implements Serializable {
	
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;

	private SupplierContractVo supplierContractVo = new SupplierContractVo();
	
	private List<SupplierItem> dictTypeList = new ArrayList<SupplierItem>();
	private List<DicInfo> DicInfoList = new ArrayList<DicInfo>();
	/**
	 * 车队二级类型
	 */
	private  List<DicInfo> dictSecLevelTypeList = new ArrayList<DicInfo>();
	/**
	 * 酒店两种字典类型
	 */
	private  List<DicInfo> dictType2List = new ArrayList<DicInfo>();
	/**
	 * 
	 */
	private List<DicInfo> cashTypes = new ArrayList<DicInfo>();
	/**
	 * @return getSupplierContractVo获取 supplierContractVo 的值
	 */
	public SupplierContractVo getSupplierContractVo() {
		return supplierContractVo;
	}
	/**
	 * @param setSupplierContractVo 设置  supplierContractVo 的值
	 */
	public void setSupplierContractVo(SupplierContractVo supplierContractVo) {
		this.supplierContractVo = supplierContractVo;
	}
	/**
	 * @return getDictTypeList获取 dictTypeList 的值
	 */
	public List<SupplierItem> getDictTypeList() {
		return dictTypeList;
	}
	/**
	 * @param setDictTypeList 设置  dictTypeList 的值
	 */
	public void setDictTypeList(List<SupplierItem> dictTypeList) {
		this.dictTypeList = dictTypeList;
	}
	/**
	 * @return getDictSecLevelTypeList获取 dictSecLevelTypeList 的值
	 */
	public List<DicInfo> getDictSecLevelTypeList() {
		return dictSecLevelTypeList;
	}
	/**
	 * @param setDictSecLevelTypeList 设置  dictSecLevelTypeList 的值
	 */
	public void setDictSecLevelTypeList(List<DicInfo> dictSecLevelTypeList) {
		this.dictSecLevelTypeList = dictSecLevelTypeList;
	}
	/**
	 * @return getCashTypes获取 cashTypes 的值
	 */
	public List<DicInfo> getCashTypes() {
		return cashTypes;
	}
	/**
	 * @param setCashTypes 设置  cashTypes 的值
	 */
	public void setCashTypes(List<DicInfo> cashTypes) {
		this.cashTypes = cashTypes;
	}
	/**
	 * @return getDictType2List获取 dictType2List 的值
	 */
	public List<DicInfo> getDictType2List() {
		return dictType2List;
	}
	/**
	 * @param setDictType2List 设置  dictType2List 的值
	 */
	public void setDictType2List(List<DicInfo> dictType2List) {
		this.dictType2List = dictType2List;
	}
	public List<DicInfo> getDicInfoList() {
		return DicInfoList;
	}
	public void setDicInfoList(List<DicInfo> dicInfoList) {
		DicInfoList = dicInfoList;
	}
	
	

}
