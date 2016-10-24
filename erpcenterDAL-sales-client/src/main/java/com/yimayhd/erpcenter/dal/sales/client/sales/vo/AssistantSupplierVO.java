package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.List;

import com.yihg.sales.po.AssistantSupplier;
import com.yihg.sales.po.AssistantSupplierImg;
import com.yihg.sales.po.AssistantSupplierImgType;

/**
 * 行程助手商家信息
 * 
 * @author ou
 * @date 2016年9月1日 下午3:29:13
 */
public class AssistantSupplierVO implements Serializable {

	private static final long serialVersionUID = -1491813934125364357L;

	private List<AssistantSupplier> supplier;//商家信息

	private List<AssistantSupplierImgType> supplierImgTypes;//商家图片类型信息

	private List<AssistantSupplierImg> supplierImgs;//图片信息

	public List<AssistantSupplier> getSupplier() {
		return supplier;
	}

	public void setSupplier(List<AssistantSupplier> supplier) {
		this.supplier = supplier;
	}

	public List<AssistantSupplierImgType> getSupplierImgTypes() {
		return supplierImgTypes;
	}

	public void setSupplierImgTypes(List<AssistantSupplierImgType> supplierImgTypes) {
		this.supplierImgTypes = supplierImgTypes;
	}

	public List<AssistantSupplierImg> getSupplierImgs() {
		return supplierImgs;
	}

	public void setSupplierImgs(List<AssistantSupplierImg> supplierImgs) {
		this.supplierImgs = supplierImgs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("supplier=");
		builder.append(supplier);
		builder.append(", supplierImgTypes=");
		builder.append(supplierImgTypes);
		builder.append(", supplierImgs=");
		builder.append(supplierImgs);
		return builder.toString();
	}

}
