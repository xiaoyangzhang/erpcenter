package com.yimayhd.erpcenter.biz.product.service.impl;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.dal.product.po.PriceView;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRight;
import com.yimayhd.erpcenter.dal.product.po.ProductSales;
import com.yimayhd.erpcenter.dal.product.service.ProductInfoDal;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;

public class ProductInfoBizImpl implements ProductInfoBiz{

	@Autowired
	private ProductInfoDal productInfoDal;
    
	@Override
	public int insertSelective(ProductInfo record) {
		return productInfoDal.insertSelective(record);
	}

	@Override
	public PageBean<ProductInfo> findProductInfos(
			PageBean<ProductInfo> pageBean, Map parameters) {
		return productInfoDal.findProductInfos(pageBean, parameters);
	}
	
	@Override
	public PageBean<ProductInfo> selectProductListPage(
			PageBean<ProductInfo> pageBean, Map parameters) {
		return productInfoDal.selectProductListPage(pageBean, parameters);
	}
	
	@Override
	public PageBean<ProductSales> findProductSales(
			PageBean<ProductSales> pageBean, Integer bizId,Integer orgId) {
		return productInfoDal.findProductSales(pageBean, bizId, orgId);
	}

	@Override
	public PageBean<ProductSales> findProductSalesPlus(
			PageBean<ProductSales> pageBean, Integer bizId, Integer orgId) {
		return productInfoDal.findProductSalesPlus(pageBean, bizId, orgId);
	}
	
//	public  String listToString(List list, char separator) {
//		 return StringUtils.join(list.toArray(),separator);   
//	}
	
	/**
	 * 拼成这样： YMQCYM10001
	 * @param bizCode 商家编码，从WebUtils中获取
	 * @param brandCode 产品品牌编码
	 * @param count 根据biz_id 和brand_id查询出来的个数
	 * @return
	 */
	private String getProductCode(String bizCode,String brandCode,Integer count){
		String format = "{0}{1}{2}";
		return MessageFormat.format(format, bizCode,brandCode,String.valueOf(count+1));
	}

	@Transactional
	@Override
	public int saveProductInfo(ProductInfoVo productInfoVo,String bizCode,String brandCode) {
		return productInfoDal.saveProductInfo(productInfoVo, bizCode, brandCode);
	}

	@Override
	public ProductInfoVo findProductInfoVoById(Integer id) {
		return productInfoDal.findProductInfoVoById(id);
	}

	@Override
	public int updateProductInfo(ProductInfo productInfo) {
		return productInfoDal.updateProductInfo(productInfo);
	}

	@Override
	public ProductInfo findProductInfoById(Integer id) {
		return productInfoDal.findProductInfoById(id);
	}
	
	@Override
	public ProductInfo selectStockCount(Integer productId,String itemDate) {
		return productInfoDal.selectStockCount(productId,itemDate);
	}

    @Override
    public String getProductPriceState(Integer productId) {
    	return productInfoDal.getProductPriceState(productId);
    }

	@Override
	public List<PriceView> getPriceViewsByDate(Integer groupId, Date startDate,Date endDate) {		
        return productInfoDal.getPriceViewsByDate(groupId, startDate, endDate);
	}

	@Override
	public Map<String, Object> findProductInfos(Integer productId) {
		return productInfoDal.findProductInfos(productId);
	}

	public PageBean getStockStaticsList(StockStaticCondition condition){
		return productInfoDal.getStockStaticsList(condition);
	}
	
//	private StockStaticsResultItemVo getResultByDate(List<StockStaticsResultItemVo> itemList,Date date){
//		for(StockStaticsResultItemVo item : itemList){
//			if(item!=null){
//				if(DateUtils.isSameDay(item.getGroupDate(), date)){
//					return item;
//				}
//			}
//		}
//		StockStaticsResultItemVo vo = new StockStaticsResultItemVo();
//		vo.setGroupDate(date);
//		vo.setStockCount(0);
//		vo.setReceiveCount(0);
//		vo.setLeftCount(0);
//		return vo;
//	}

	@Override
	public void saveProductRight(Integer productId, Set<Integer> orgIdSet) {
		productInfoDal.saveProductRight(productId, orgIdSet);
	}

	@Override
	public List<ProductRight> getRightListByProductId(Integer productId) {
		return productInfoDal.getRightListByProductId(productId);
	}

	@Override
	public ProductInfo findProductInfoBySupplierId(Integer supplierId) {
		return productInfoDal.findProductInfoBySupplierId(supplierId);
	}

	@Override
	public PageBean getStockStaticsList2(StockStaticCondition condition) throws ParseException {
		return productInfoDal.getStockStaticsList2(condition);
	}
	
//	private ProductStock getResultByDate2(Date date,String[] stockInfoStrs) throws NumberFormatException, ParseException {
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		for (String str : stockInfoStrs) {
//			String[] stockInfoStr = str.split("/");
//					if (DateUtils.isSameDay(date, sdf.parse(stockInfoStr[0]))) {
//						
//						ProductStock pStock=new ProductStock();
//						pStock.setItemDate(sdf.parse(stockInfoStr[0]));
//						pStock.setReceiveCount(Integer.parseInt(stockInfoStr[2]));
//						pStock.setStockCount(Integer.parseInt(stockInfoStr[1]));
//						return pStock;
//					}
//				
//			}
//		ProductStock pStock=new ProductStock();
//		pStock.setItemDate(date);
//		pStock.setReceiveCount(0);
//		pStock.setStockCount(0);
//		
//			
//			
//		return pStock;
//		
//	}

	@Override
	public boolean checkProductCodeExist(Integer productId, Integer bizId,
			String code) {
		return productInfoDal.checkProductCodeExist(productId, bizId, code);
	}

	@Override
	public List<Map<String, Object>> getAllId(Integer bizId,Integer state) {
		return productInfoDal.getAllId(bizId, state);
	}
	
	@Override
	public List<ProductInfo> searchProductByNameAndDate(Integer bizId, String prodKeyword, String dateStart){
		return productInfoDal.searchProductByNameAndDate(bizId, prodKeyword, dateStart);
	}

	@Override
	public PageBean<ProductInfo> findProductRoutes(
			PageBean<ProductInfo> pageBean, Map parameters) {
		return productInfoDal.findProductRoutes(pageBean, parameters);
	}
	
	@Override
	public PageBean<ProductInfo> selectStockProductListPage(
			PageBean<ProductInfo> pageBean, Map parameters) {
		return productInfoDal.selectStockProductListPage(pageBean, parameters);
	}


	@Override
	public ProductInfo findProductByIdAndBizId(Integer id, Integer bizId) {
		return productInfoDal.findProductByIdAndBizId(id, bizId);
	}

	@Override
	public PageBean<ProductInfo> findProductAndPriceGroup(
			PageBean<ProductInfo> pageBean) {
		 return productInfoDal.findProductAndPriceGroup(pageBean);
	}

	@Override
	public int fix_SupplierName(Integer supplierId, String supplierName) {
		return productInfoDal.fix_SupplierName(supplierId, supplierName);
	}

	@Override
	public void updateProductSysId(Integer productId,Integer productSysId) {
		productInfoDal.updateProductSysId(productId, productSysId);
	}
	
	@Override
	public ProductInfo selectProductInfoByPsId(Integer productSysId) {
		return productInfoDal.selectProductInfoByPsId(productSysId);
	}
	
}

