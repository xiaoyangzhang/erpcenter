package com.yimayhd.erpcenter.service;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;



/**
 * 
* @ClassName: ProductService 
* @Description: 
* @author wangjun
* @date 2016年10月17日 下午6:32:01 
*
 */
public interface ProductDal {
	int saveBasicInfo(ProductInfoVo productInfoVo,String bizCode,String brandCode);
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月18日
	* @Description:产品列表
	* @param 
	* @return List<ProductInfo>
	* @throws
	 */
	List<ProductInfo> selectProductListPage(PageBean pageBean,Map<String, Object> parameters);
}
