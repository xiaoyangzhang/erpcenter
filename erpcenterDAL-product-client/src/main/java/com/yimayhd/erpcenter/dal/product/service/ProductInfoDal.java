package com.yimayhd.erpcenter.dal.product.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.PriceView;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRight;
import com.yimayhd.erpcenter.dal.product.po.ProductSales;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : xuzejun
 * @date : 2015年7月2日 下午3:58:56
 * @Description: 产品基本信息接口
 */
public interface ProductInfoDal {
	/**
	 * 插入
	 * @param record
	 * @return
	 */
	int insertSelective(ProductInfo record);

	/**
	 * 插入product
	 * @param productInfoVo
	 * @return
	 */
	int saveProductInfo(ProductInfoVo productInfoVo,String bizCode,String brandCode);
	
	/**
	 * 分页查询产品列表
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	//PageBean<ProductInfo> findProductInfos(PageBean<ProductInfo> pageBean,Integer bizId,String name, String productName,Integer orgId);
	PageBean<ProductInfo> findProductInfos(PageBean<ProductInfo> pageBean,Map parameters);
	
	/**
	 * 分页查询产品列表（按创建者过滤）
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean<ProductInfo> findProductInfos2(PageBean<ProductInfo> pageBean,Map parameters);
	
	/**
	 * 查询产品和价格组信息
	 * @param pageBean
	 * @return
	 */
	PageBean<ProductInfo> findProductAndPriceGroup(PageBean<ProductInfo> pageBean);
	PageBean<ProductInfo> findProductRoutes(PageBean<ProductInfo> pageBean,Map parameters);
	PageBean<ProductInfo> selectStockProductListPage(PageBean<ProductInfo> pageBean,Map parameters);

	
	/**
	 * 分页查询销售产品列表（修改产品价格方案后ge）
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean<ProductSales> findProductSalesPlus(PageBean<ProductSales> pageBean,Integer bizId,Integer orgId);
	
	/**
	 * 分页查询销售产品列表
	 * @param pageBean
	 * @param bizId
	 * @return
	 */
	PageBean<ProductSales> findProductSales(PageBean<ProductSales> pageBean,Integer bizId,Integer orgId);
	
	/**
	 * 查询VO
	 * @param id
	 * @return
	 */
	ProductInfoVo findProductInfoVoById(Integer id);
	/**
	 * 修改
	 * @param productInfo
	 * @return
	 */
	int updateProductInfo(ProductInfo productInfo);
	/**
	 * 查询Product
	 * @param id
	 * @return
	 */
	ProductInfo findProductInfoById(Integer id);
	
	ProductInfo selectStockCount(Integer productId,String itemDate);
	
	ProductInfo findProductInfoBySupplierId(Integer supplierId);

	String getProductPriceState(Integer productId);

	List<PriceView> getPriceViewsByDate(Integer groupId, Date startDate,Date endDate);
	
	/**
	 * 产品信息打印
	 * @param productId
	 * @return
	 */
	Map<String, Object> findProductInfos(Integer productId) ;
	
	/**
	 * 产品库存统计
	 * @param condition
	 * @return
	 */
	PageBean getStockStaticsList(StockStaticCondition condition);
	/**
	 * 新版产品库存统计
	 * @param condition
	 * @return
	 */
	PageBean getStockStaticsList2(StockStaticCondition condition) throws ParseException;
	
	
	/**
	 * 保存产品权限
	 * @param productId
	 * @param orgList
	 */
	void saveProductRight(Integer productId,Set<Integer> orgIdSet);
	/**
	 * 获取产品权限
	 * @param productId
	 * @return
	 */
	List<ProductRight> getRightListByProductId(Integer productId);

	boolean checkProductCodeExist(Integer productId, Integer bizId, String code);
	
	/**
	 * 获取所有已上架产品id
	 * 用于库存对账
	 * @return
	 */
	List<Map<String,Object>> getAllId(Integer bizId,Integer state);
	
	List<ProductInfo> searchProductByNameAndDate(Integer bizId, String prodKeyword, String dateStart);
	ProductInfo findProductByIdAndBizId(Integer id,Integer bizId);
	
	int fix_SupplierName(Integer supplierId, String supplierName);
	//int fix_SupplierName_GroupSupplier(Integer supplierId, String supplierName);
	
	void updateProductSysId(Integer productId, Integer productSysId);
	
	ProductInfo selectProductInfoByPsId(Integer productSysId);
}
