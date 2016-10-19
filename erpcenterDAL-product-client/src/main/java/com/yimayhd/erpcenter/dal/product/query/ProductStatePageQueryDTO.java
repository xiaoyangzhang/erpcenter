package com.yimayhd.erpcenter.dal.product.query;

import com.yimayhd.erpcenter.common.query.PageQuery;

public class ProductStatePageQueryDTO extends PageQuery{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1514932164244446916L;
	
	/**
	 * 产品id
	 */
	private Long id ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
