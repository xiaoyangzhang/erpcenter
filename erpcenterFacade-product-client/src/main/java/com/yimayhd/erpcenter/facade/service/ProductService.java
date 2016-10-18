/**
 * 
 */
package com.yimayhd.erpcenter.facade.service;

import java.util.Map;

import com.yihg.mybatis.utility.PageBean;

/**
 * @ClassName: ProductService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月17日
 */
public interface ProductService {

	public PageBean selectProductList(PageBean pageBean,Map<String, Object> parameters);
}
