package com.yimayhd.erpcenter.facade.service;

import java.text.ParseException;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.query.ProductStockStaticDto;

/**
 * 描述：产品库存对应的facade
 * @author liyong
 * 2016年10月18日
 */
public interface ProductStockFacade {
	/**
	 * 描述：新版产品库存统计
	 * @author liyong
	 * 2016年10月18日 
	 * @param condition
	 * @return PageBean
	 */
	PageBean getStockStaticsListNew(ProductStockStaticDto condition) throws ParseException;

}
