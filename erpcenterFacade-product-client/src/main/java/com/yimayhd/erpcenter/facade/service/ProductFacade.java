package com.yimayhd.erpcenter.facade.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.facade.query.ProductListParam;
import com.yimayhd.erpcenter.facade.query.ProductPriceListDTO;
import com.yimayhd.erpcenter.facade.query.ProductRemarkDTO;
import com.yimayhd.erpcenter.facade.query.ProductSaveDTO;
import com.yimayhd.erpcenter.facade.query.ProductTagDTO;
import com.yimayhd.erpcenter.facade.result.GetProductRouteResult;
import com.yimayhd.erpcenter.facade.result.ProductDataRightResult;
import com.yimayhd.erpcenter.facade.result.ProductInfoResult;
import com.yimayhd.erpcenter.facade.result.ProductPriceListResult;
import com.yimayhd.erpcenter.facade.result.ResultSupport;
import com.yimayhd.erpcenter.facade.result.ToProductAddResult;
import com.yimayhd.erpcenter.facade.result.ToProductRemarkResult;
import com.yimayhd.erpcenter.facade.result.ToProductTagResult;
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
	 * 跳转产品添加页
	 * @param typeCode
	 * @param bizId
	 * @return
	 * @author wangjun
	 */
	ToProductAddResult toProductAdd(String typeCode, int bizId);
	/**
	 * 保存产品基本信息
	 * @param productSaveDTO
	 * @return
	 * @author wangjun
	 */
	int saveBasicInfo(ProductSaveDTO productSaveDTO);
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
	* @Description:产品列表
	* @param 
	* @return PageBean
	* @throws
	 */
	public WebResult<PageBean<ProductInfo>> selectProductList(PageBean pageBean,Map<String, Object> parameters);
	/**
	 * 跳转到产品标签修改页
	 * @param productId
	 * @param bizId
	 * @return
	 * @author wangjun
	 */
	ToProductTagResult toProductTags(int productId, int bizId);
	/**
	 * 保存产品标签
	 * @param productTagDTO
	 * @return
	 * @author wangjun
	 */
	boolean saveProductTags(ProductTagDTO productTagDTO);
	/**
	 * 跳转到产品备注修改页
	 * @param productId
	 * @return
	 * @author wangjun
	 */
	ToProductRemarkResult toProductRemark(int productId);
	/**
	 * 保存产品备注信息
	 * @param productRemarkDTO
	 * @return
	 * @author wangjun
	 */
	boolean saveProductRemark(ProductRemarkDTO productRemarkDTO);
	
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月18日
	* @Description:获取编辑产品的信息
	* @param 
	* @return ProductInfoVo
	* @throws
	 */
	ProductInfoVo toEditProductInfoVOById(ProductListParam param);
	/**
	 * 
	* created by zhangxiaoyang
	* @date 2016年10月18日
	* @Description:获取编辑产品页面的备注信息
	* @param 
	* @return ProductRemark
	* @throws
	 */
	ProductInfoResult toProductPreview(int productId);
	//ProductRemark getProductRemarkByProductId(int productId);
	
	/**
	 * 产品列表/删除
	* created by zhangxiaoyang
	* @date 2016年10月19日
	* @Description:
	* @param 
	* @return ResultSupport
	* @throws
	 */
	ResultSupport updateProductState(int productId,byte state);
	/**
	 * 产品列表/导出
	* created by zhangxiaoyang
	* @date 2016年10月19日
	* @Description:
	* @param 
	* @return Map<String,Object>
	* @throws
	 */
	WebResult<Map<String, Object>> toExportProduct(int productId);
	
	/**
	 * 产品价格列表
	 * @param productInfo
	 * @param productName
	 * @param name
	 * @return
	 */
	ProductPriceListResult productPriceList(ProductPriceListDTO productPriceListDTO);
	/**
	 * 获取组织机构列表和某个产品权限集合
	* created by zhangxiaoyang
	* @date 2016年10月19日
	* @Description:
	* @param 
	* @return ProductDataRightResult
	* @throws
	 */
	ProductDataRightResult getOrgListAndProductRights(int productId,int bizId);
	/**
	 * 产品列表/权限
	* created by zhangxiaoyang
	* @date 2016年10月19日
	* @Description:
	* @param 
	* @return List<Map<String,String>>
	* @throws
	 */
	List<Map<String, String>> getProductRight(int productId,int bizId);
	/**
	 * 保存产品权限设置结果
	* created by zhangxiaoyang
	* @date 2016年10月19日
	* @Description:
	* @param 
	* @return ResultSupport
	* @throws
	 */
	ResultSupport setProductRight(int productId,Set<Integer> orgIdSet);
}
