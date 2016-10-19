package com.yimayhd.erpcenter.facade.service;

import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.facade.query.ProductListParam;
import com.yimayhd.erpcenter.facade.query.ProductRemarkDTO;
import com.yimayhd.erpcenter.facade.query.ProductSaveDTO;
import com.yimayhd.erpcenter.facade.query.ProductTagDTO;
import com.yimayhd.erpcenter.facade.result.GetProductRouteResult;
import com.yimayhd.erpcenter.facade.result.ToProductAddResult;
import com.yimayhd.erpcenter.facade.result.WebResult;


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
	public WebResult<PageBean<ProductInfo>> selectProductList(PageBean pageBean,Map<String, Object> parameters);
	
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
	
	/**
	 * 保存产品备注信息
	 * @param productRemarkDTO
	 * @return
	 * @author wangjun
	 */
	boolean saveProductRemark(ProductRemarkDTO productRemarkDTO);
	
	/**
	 * 跳转产品添加页
	 * @param typeCode
	 * @param bizId
	 * @return
	 * @author wangjun
	 */
	ToProductAddResult toProductAdd(String typeCode, int bizId);
	
	/**
	 * 跳转产品修改页
	 * @param productId
	 * @param typeCode
	 * @param bizId
	 * @return
	 * @author wangjun
	 */
	ToProductAddResult toProductEdit(int productId,String typeCode, int bizId);
	
	/**
	 * 
	 * @param productId
	 * @return
	 * @author wangjun
	 */
	GetProductRouteResult findProductRouteById(int productId);
	
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月18日
	* @Description:获取编辑产品的信息
	* @param 
	* @return ProductInfoVo
	* @throws
	 */
	ProductInfoVo getProductInfoVOById(ProductListParam param);
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月18日
	* @Description:获取编辑产品页面的备注信息
	* @param 
	* @return ProductRemark
	* @throws
	 */
	//ProductRemark getProductRemarkByProductId(int productId);
}
