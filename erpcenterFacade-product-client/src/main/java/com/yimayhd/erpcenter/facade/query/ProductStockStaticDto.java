package com.yimayhd.erpcenter.facade.query;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * 描述：产品库存查询的封装类
 * @author liyong
 * 2016年10月18日
 */

import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;
public class ProductStockStaticDto implements Serializable {
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月18日 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产品库存查询参数对象
	 */
	StockStaticCondition condition = new StockStaticCondition();
	public StockStaticCondition getCondition() {
		return condition;
	}
	public void setCondition(StockStaticCondition condition) {
		this.condition = condition;
	}
	
	
	
}
