package com.yimayhd.erpcenter.facade.supplier.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpresource.dal.po.SupplierImg;
import com.yimayhd.erpresource.dal.po.SupplierImgType;
/**
 * 到图片列表页面结果分装
 * @author liyong
 * 2016年10月25日 描述：
 */
public class PictureListResult extends ResultSupport implements Serializable {

	/**
	 * @author liyong
	 * 2016年10月27日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private List<SupplierImg> imgList = new ArrayList<SupplierImg>();
	private SupplierImgType supplierImgType = new SupplierImgType();
	public List<SupplierImg> getImgList() {
		return imgList;
	}
	public void setImgList(List<SupplierImg> imgList) {
		this.imgList = imgList;
	}
	public SupplierImgType getSupplierImgType() {
		return supplierImgType;
	}
	public void setSupplierImgType(SupplierImgType supplierImgType) {
		this.supplierImgType = supplierImgType;
	}
	
	
}
