package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpresource.dal.po.SupplierImgType;
/**
 * 跳转到图片页面 结果封装类
 * @author liyong
 * 2016年10月25日 描述：
 */
public class FolderListResult extends ResultSupport implements Serializable {
	
	/**
	 * @author liyong
	 * 2016年10月25日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private List<SupplierImgType> bussList = new ArrayList<SupplierImgType>();
	private List<SupplierImgType> huanList = new ArrayList<SupplierImgType>();
	public List<SupplierImgType> getBussList() {
		return bussList;
	}
	public void setBussList(List<SupplierImgType> bussList) {
		this.bussList = bussList;
	}
	public List<SupplierImgType> getHuanList() {
		return huanList;
	}
	public void setHuanList(List<SupplierImgType> huanList) {
		this.huanList = huanList;
	}




}
