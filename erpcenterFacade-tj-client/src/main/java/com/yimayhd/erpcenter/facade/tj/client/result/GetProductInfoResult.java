package com.yimayhd.erpcenter.facade.tj.client.result;

import java.util.Map;

public class GetProductInfoResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int productIdCount;
	private Map<String,Object> map;
	public int getProductIdCount() {
		return productIdCount;
	}
	public void setProductIdCount(int productIdCount) {
		this.productIdCount = productIdCount;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
}
