package com.yimayhd.erpcenter.facade.service;

import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.query.ProductSaveDTO;


/**
 * 
* @ClassName: ProductFacade 
* @Description: 
* @author wangjun
* @date 2016年10月17日 下午4:26:09 
*
 */
public interface ProductFacade {
	int saveBasicInfo(ProductSaveDTO productSaveDTO);
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月18日
	* @Description:产品列表
	* @param 
	* @return PageBean
	* @throws
	 */
	public PageBean selectProductList(PageBean pageBean,Map<String, Object> parameters);
}
