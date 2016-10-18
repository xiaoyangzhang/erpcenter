package com.yimayhd.erpcenter.facade.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;

public class ProductGroupSupplierDTO implements Serializable {
    private static final long serialVersionUID = -5738945525593633404L;
    
    private ProductGroupSupplier groupSupplier = null;
    
    public ProductGroupSupplier getGroupSupplier() {
		return groupSupplier;
	}

	public void setGroupSupplier(ProductGroupSupplier groupSupplier) {
		this.groupSupplier = groupSupplier;
	}
   
}