package com.yimayhd.erpcenter.facade.service;

import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.query.ProductSaveDTO;
import com.yimayhd.erpcenter.facade.query.ProductTagDTO;


/**
 * 
* @ClassName: ProductFacade 
* @Description: 
* @author wangjun
* @date 2016年10月17日 下午4:26:09 
*
 */
public interface ProductFacade {
	/**
	 * 保存产品基本信息
	 * @param productSaveDTO
	 * @return
	 * @author wangjun
	 */
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
	
	/**
	 * 校验编码是否存在
	 * @param bizId
	 * @param productId
	 * @param code
	 * @return
	 * @author wangjun
	 */
	boolean codeValidate(int bizId, int productId, String code);
	
	/**
	 * 保存产品标签
	 * @param productTagDTO
	 * @return
	 * @author wangjun
	 */
	boolean saveProductTags(ProductTagDTO productTagDTO);
}
