
package com.yimayhd.erpcenter.facade.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductGroup;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupExtraItem;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupPrice;
import com.yimayhd.erpcenter.dal.product.vo.PriceCopyVo;
import com.yimayhd.erpcenter.dal.product.vo.ProductPriceVo;
import com.yimayhd.erpcenter.facade.query.ProductGroupSupplierDTO;
import com.yimayhd.erpcenter.facade.query.ProductSupplierConditionDTO;
import com.yimayhd.erpcenter.facade.result.ProductGroupResult;
import com.yimayhd.erpcenter.facade.result.ProductInfoResult;
import com.yimayhd.erpcenter.facade.result.ResultSupport;
import com.yimayhd.erpcenter.facade.result.ToAddPriceGroupResult;
import com.yimayhd.erpcenter.facade.result.ToSupplierListResult;



/**
 * 
* @ClassName: ProductPriceFacade 
* @Description: 
* @author hongfei.guo
* @date 2016年10月17日 下午4:26:09 
*
 */
public interface ProductPricePlusFacade {
	
	
	/**
	 * 保存组团社
	 * @param data
	 * @return
	 */
	ResultSupport supplierSave(String data);
	
	/**
	 * 删除组团社
	 * @param groupSupplier
	 * @return
	 */
	ResultSupport delSupplier(ProductGroupSupplierDTO productGroupSupplierDTO);
	
	/**
	 * 复制到其他产品
	 * @param data
	 * @param productId
	 * @return
	 */
	ResultSupport copyProductSuppliers(String data, Integer productId);
	
	/**
	 * 库存设置
	 * @param productId
	 * @param stockStr
	 * @param year
	 * @param month
	 * @return
	 */
	ResultSupport saveStock(Integer productId,String stockStr,Integer year,Integer month);
	
	/**
	 * 添加价格组
	 * @param productGroups
	 * @param groupSupplierId
	 * @return
	 */
	ResultSupport savePriceGroup(String productGroups,Integer groupSupplierId);
	
	/**
	 * 将价格组复制到其他组
	 * @param model
	 * @param groupIds
	 * @param destGroupSupplierIds
	 * @return
	 */
	ResultSupport copyGroup(String groupIds,String destGroupSupplierIds);
	
	/**
	 * 跳转到价格设置页面
	 * @param model
	 * @param condition
	 * @return
	 */
	ToSupplierListResult toSupplierList(ProductSupplierConditionDTO conditionDTO);
	
	/**
	 * 跳转到添加价格组页面
	 * @param id 产品组团社表id
	 * @return
	 */
	ToAddPriceGroupResult addPriceGroup(Integer id);
	
	/**
	 * 根据年、月得到产品的库存
	 * @param productId
	 * @param year
	 * @param month
	 * @return
	 */
	String stockMonth(Integer productId,Integer year,Integer month);
	
	/**
	 * 将某个产品下的组团社和价格组复制到其他产品
	 * @param data
	 * @param productId
	 * @return
	 */
	public ResultSupport copyProduct(String data,Integer productId);
	List<Map> loadMinPrice(List<Integer> productIds,Date date);
	ProductGroupResult toPriceSetting(Integer productId);
	ResultSupport save(ProductGroup productGroup);
	String validateName(String name,Integer productId,Integer id);
	ResultSupport copyGroupPrice(PriceCopyVo copyVo);
	List<ProductGroupPrice> selectPriceByGroupId(Integer groupId);
	ResultSupport batchInsertPriceGroup(Integer bizId,String json);
	ProductGroupExtraItem save(ProductGroupExtraItem productGroupExtraItem);
	ProductGroupExtraItem edit(ProductGroupExtraItem productGroupExtraItem);
	ProductGroupExtraItem findById(Integer id);
	boolean deleteById(Integer id);
	ProductGroupResult queryGroupPriceList(PageBean pageBean,Map paramMap);
	ProductGroupResult ToAddPrice(Integer groupId,Integer productId);
	ProductGroupResult ToEditPrice(Integer groupId,Integer productId,Integer productGroupPriceID);
	ResultSupport save(ProductPriceVo priceVo);
	List<ProductGroupPrice> selectProductGroupPrices(Integer groupId,String year,String month);
	ResultSupport delete(Integer id);
	ResultSupport batchDel(String[] idArr);
}
