package com.yimayhd.erpcenter.dal.product.service.impl;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.dao.ProductAttachmentMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductContactMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupPriceMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductInfoMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductRemarkMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductRightMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductRouteMapper;
import com.yimayhd.erpcenter.dal.product.dto.ProductStateDTO;
import com.yimayhd.erpcenter.dal.product.dto.ProductStockDTO;
import com.yimayhd.erpcenter.dal.product.po.PriceView;
import com.yimayhd.erpcenter.dal.product.po.ProductAttachment;
import com.yimayhd.erpcenter.dal.product.po.ProductContact;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.po.ProductRight;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.product.po.ProductSales;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.query.ProductStatePageQueryDTO;
import com.yimayhd.erpcenter.dal.product.query.ProductStockPageQueryDTO;
import com.yimayhd.erpcenter.dal.product.service.ProductInfoDal;
import com.yimayhd.erpcenter.dal.product.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.product.solr.converter.ProductStateConverter;
import com.yimayhd.erpcenter.dal.product.solr.converter.ProductStockConverter;
import com.yimayhd.erpcenter.dal.product.solr.manager.ProductSolrQueryManager;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultItemVo;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultVOPlus;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultVo;

public class ProductInfoDalImpl implements ProductInfoDal{

	@Autowired
	private ProductInfoMapper infoMapper;
	@Autowired
	private ProductContactMapper contactMapper;
	@Autowired
	private ProductAttachmentMapper attachmentMapper;

    @Autowired
    private ProductGroupPriceMapper productGroupPriceMapper;
   
    @Autowired
    private ProductRemarkMapper remarkMapper ;
    @Autowired
    private ProductRouteMapper productRouteMapper ;
    @Autowired
    private ProductRightMapper productRightMapper;
    
   // @Autowired
    private ProductSolrQueryManager productSolrQueryManager;
    
	@Override
	public int insertSelective(ProductInfo record) {
		infoMapper.insertSelective(record);
		return record.getId();
	}

//	@Override
//	public PageBean<ProductInfo> findProductInfos(
//			PageBean<ProductInfo> pageBean, Integer bizId,String name, String productName,Integer orgId) {
//		 List<ProductInfo> list = infoMapper.selectProductInfoListPage(pageBean, bizId,name, productName,orgId);
//		 pageBean.setResult(list);
//		 return pageBean;
//	}
	@Override
	public PageBean<ProductInfo> findProductInfos(
			PageBean<ProductInfo> pageBean, Map parameters) {
		List<ProductInfo> list = infoMapper.selectProductInfoListPage(pageBean, parameters);
		pageBean.setResult(list);
		if(1==0){
			ProductStatePageQueryDTO queryDTO = ProductStateConverter.toQueryDTO(pageBean, parameters);
			SolrSearchPageDTO<ProductStateDTO> solrPageResult  = productSolrQueryManager.searchProductState(queryDTO);
			return ProductStateConverter.dto2PageBean(solrPageResult);
		}else{
			
		}
		return pageBean;
	}
	
	@Override
	public PageBean<ProductInfo> selectProductListPage(
			PageBean<ProductInfo> pageBean, Map parameters) {
		List<ProductInfo> list = infoMapper.selectProductListPage(pageBean, parameters);
		pageBean.setResult(list);
		return pageBean;
	}
	
	@Override
	public PageBean<ProductSales> findProductSales(
			PageBean<ProductSales> pageBean, Integer bizId,Integer orgId) {
		 List<ProductSales> list = infoMapper.selectProductSalesListPage(pageBean, bizId,orgId);
		 if(list!=null && list.size()>0){
			 /*Date date = com.yihg.images.util.DateUtils.formatDate(new Date(), com.yihg.images.util.DateUtils.FORMAT_SHORT);
			 for (ProductSales productSales : list) {
				 /*List<String> dates = productGroupPriceMapper.selectDatesByProductId(productSales.getId(), productSales.getGroupDate());
				 productSales.setGroupDates(listToString(dates,'、'));
				 
				 List<Map> priceList = productGroupPriceMapper.selectMinPriceByProductIdAndDate(productSales.getId(), date);
				 if(priceList!=null && priceList.size()>0){
					 productSales.setBprice(TypeUtils.castToString(priceList.get(0).get("price_settlement_adult")));
					 productSales.setEprice(TypeUtils.castToString(priceList.get(0).get("price_settlement_child")));
				 }		
			}*/
		 }
		 pageBean.setResult(list);
		return pageBean;
	}

	@Override
	public PageBean<ProductSales> findProductSalesPlus(
			PageBean<ProductSales> pageBean, Integer bizId, Integer orgId) {
		List<ProductSales> list = infoMapper.selectProductSalesPlusListPage(pageBean, bizId,orgId);
		pageBean.setResult(list);
		return pageBean;
	}
	
	 public  String listToString(List list, char separator) {
		 return StringUtils.join(list.toArray(),separator);   
	}
	
	
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
		int i = 0;
		
		ProductInfo info = productInfoVo.getProductInfo();
		List<ProductContact> productContacts = productInfoVo.getProductContacts();
		List<ProductAttachment> productAttachments = productInfoVo.getProductAttachments();
		List<ProductAttachment> attachments = productInfoVo.getAttachments();
		if(null!=productInfoVo.getProductInfo().getId()){
			
			//修改
			i= infoMapper.updateByPrimaryKeySelective(info);
			//删除责任人
			contactMapper.deleteByproductId(info.getId());
			//删除图片
			attachmentMapper.deleteByobjId(info.getId(),1);
			attachmentMapper.deleteByobjId(info.getId(),2);
		}else{
			if ("".equals(info.getCode())){ //若为空，则由系统产生订单号
				int count=infoMapper.getBizAndBrandCodeCount(productInfoVo.getProductInfo().getBizId(), productInfoVo.getProductInfo().getBrandId());
				info.setCode(getProductCode(bizCode, brandCode, count));
			}
			info.setState((byte) 1);
			info.setCreateTime(System.currentTimeMillis());
			i=infoMapper.insertSelective(info);
			
			//新增时默认把当前人的数据权限加在上
			productRightMapper.insertBatch(info.getId(), productInfoVo.getOrgIdSet());
		}
		//插入责任人
		if(productContacts!=null&&!productContacts.isEmpty()){
			for (ProductContact productContact : productContacts) {
				if(StringUtils.isNotBlank(productContact.getName())
						||StringUtils.isNotBlank(productContact.getMobile())
						||StringUtils.isNotBlank(productContact.getFax())
						||StringUtils.isNotBlank(productContact.getTel())){
					productContact.setProductId(info.getId());
					productContact.setCreateTime(System.currentTimeMillis());
					contactMapper.insertSelective(productContact);
				}
			}
		}
		//插入图片
		if(productAttachments!=null&&!productAttachments.isEmpty()){
			for (ProductAttachment productAttachment : productAttachments) {
				if(StringUtils.isNotBlank(productAttachment.getName())
						||StringUtils.isNotBlank(productAttachment.getPath())){
					productAttachment.setCreateTime(System.currentTimeMillis());
					productAttachment.setObjId(info.getId());
					productAttachment.setObjType((byte)1);
					attachmentMapper.insertSelective(productAttachment);
				}
			}
			}
        //附件
		if(attachments != null){
			for(ProductAttachment attachment : attachments){
				if(StringUtils.isNotBlank(attachment.getName())
						||StringUtils.isNotBlank(attachment.getPath())){
					attachment.setCreateTime(System.currentTimeMillis());
					attachment.setObjId(info.getId());
					attachment.setObjType((byte)1);
					attachmentMapper.insertSelective(attachment);
				}
			}

		}
		return info.getId();
	}

	@Override
	public ProductInfoVo findProductInfoVoById(Integer id) {
		ProductInfoVo vo = new ProductInfoVo();
		ProductInfo productInfo = infoMapper.selectByPrimaryKey(id);
		//List<ProductContact> productContacts = contactMapper.selectByConList(id);
		List<ProductAttachment> productAttachments = attachmentMapper.selectImgByList(id, 1, 1);
		List<ProductAttachment> attachments = attachmentMapper.selectImgByList(id, 1, 2);

		vo.setProductInfo(productInfo);
		//vo.setProductContacts(productContacts);
		vo.setProductAttachments(productAttachments);
        if(attachments != null && !attachments.isEmpty()){
            vo.setAttachments(attachments);
        }
		return vo;
	}

	@Override
	public int updateProductInfo(ProductInfo productInfo) {
		
		return infoMapper.updateByPrimaryKeySelective(productInfo);
	}

	@Override
	public ProductInfo findProductInfoById(Integer id) {
		
		return infoMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public ProductInfo selectStockCount(Integer productId,String itemDate) {
		
		return infoMapper.selectStockCount(productId,itemDate);
	}

    @Override
    public String getProductPriceState(Integer productId) {
        List<Date> dates = productGroupPriceMapper.selectDateByProductId(productId);
        List<Date> validDates = new ArrayList<Date>();
        List<Date> invalidDates = new ArrayList<Date>();
        for(Date date : dates){
            if(date.getTime() > System.currentTimeMillis()){
                validDates.add(date);
            }else{
                invalidDates.add(date);
            }
        }
        if(validDates.isEmpty() && !invalidDates.isEmpty()){
            return "已过期";
        }else if(!validDates.isEmpty() && invalidDates.isEmpty()){
            return "有效";
        }else if(!validDates.isEmpty() && !invalidDates.isEmpty()){
            return "部分有效";
        }else{
            return "无";
        }
    }

	@Override
	public List<PriceView> getPriceViewsByDate(Integer groupId, Date startDate,Date endDate) {		
        return productGroupPriceMapper.selectPricesByGroupId(groupId, startDate, endDate);
	}

	@Override
	public Map<String, Object> findProductInfos(Integer productId) {
		Map<String, Object> map = new HashMap<String, Object>() ;
		ProductInfo productInfo = infoMapper.selectByPrimaryKey(productId) ;
		ProductRemark productRemark = remarkMapper.selectByProductId(productId) ;
		List<ProductRoute> productRoutes = productRouteMapper.selectByProductId(productId) ;
		map.put("productInfo", productInfo);
		map.put("productRemark", productRemark);
		map.put("productRoutes", productRoutes);
		return map;
	}

	public PageBean getStockStaticsList(StockStaticCondition condition){
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(condition.getPageSize());
		pageBean.setParameter(condition);
		pageBean.setPage(condition.getPage());
		List<StockStaticsResultVo> list = infoMapper.getProductBreifListPage(pageBean);
		if(list!=null && list.size()>0){
			//condition.setToGroupDate(DateUtils.addDays(condition.getGroupDate(), 7));
			for(StockStaticsResultVo result : list){
				List<StockStaticsResultItemVo> itemList = productGroupPriceMapper.getStockStatics(result.getProductId(), DateUtils.addDays(condition.getGroupDate(),-1), DateUtils.addDays(condition.getToGroupDate(),1));
				List<StockStaticsResultItemVo> voList = new ArrayList<StockStaticsResultItemVo>();
				if(itemList!=null && itemList.size()>0){
					for(int day=0;day<7;day++){//显示七天
						voList.add(getResultByDate(itemList,DateUtils.addDays(condition.getGroupDate(), day)));
					}
				}else{
					for(int day=0;day<7;day++){
						StockStaticsResultItemVo vo = new StockStaticsResultItemVo();
						vo.setGroupDate(DateUtils.addDays(condition.getGroupDate(), day));
						vo.setStockCount(0);
						vo.setReceiveCount(0);
						vo.setLeftCount(0);
						voList.add(vo);
					}
				}
				result.setItemVoList(voList);
			}			
		}	
		pageBean.setResult(list);
		return pageBean;
	}
	
	private StockStaticsResultItemVo getResultByDate(List<StockStaticsResultItemVo> itemList,Date date){
		for(StockStaticsResultItemVo item : itemList){
			if(item!=null){
				if(DateUtils.isSameDay(item.getGroupDate(), date)){
					return item;
				}
			}
		}
		StockStaticsResultItemVo vo = new StockStaticsResultItemVo();
		vo.setGroupDate(date);
		vo.setStockCount(0);
		vo.setReceiveCount(0);
		vo.setLeftCount(0);
		return vo;
	}

	@Override
	public void saveProductRight(Integer productId, Set<Integer> orgIdSet) {
		productRightMapper.deleteByProductId(productId);
		productRightMapper.insertBatch(productId, orgIdSet);
	}

	@Override
	public List<ProductRight> getRightListByProductId(Integer productId) {
		return productRightMapper.selectRightListByProductId(productId);
	}

	@Override
	public ProductInfo findProductInfoBySupplierId(Integer supplierId) {
		return infoMapper.selectBySupplierId(supplierId);
	}

	@Override
	public PageBean getStockStaticsList2(StockStaticCondition condition) throws ParseException {
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(condition.getPageSize());
		pageBean.setParameter(condition);
		pageBean.setPage(condition.getPage());
		List<StockStaticsResultVOPlus> list =new ArrayList<StockStaticsResultVOPlus>(); 
		list=infoMapper.getProductStockListPage(pageBean);
		//SimpleDateFormat sdf=new SimpleDateFormat();
		if(list!=null && list.size()>0){
			for (StockStaticsResultVOPlus voPlus : list) {
				List<ProductStock> stockList=new ArrayList<ProductStock>();
				String[] stockInfoStrs = voPlus.getStockInfo().split(",");
				for (int i = 0; i < 7; i++) {
				stockList.add(getResultByDate2(DateUtils.addDays(condition.getGroupDate(),i),stockInfoStrs));
				}
				voPlus.setStockList(stockList);
			}
			
		}
		/*if(list!=null && list.size()>0){
			for(StockStaticsResultVOPlus result : list){
				List<StockStaticsResultItemVo> itemList = productGroupPriceMapper.getStockStatics(result.getProductId(), DateUtils.addDays(condition.getGroupDate(),-1), DateUtils.addDays(condition.getToGroupDate(),1));
				List<StockStaticsResultItemVo> voList = new ArrayList<StockStaticsResultItemVo>();
				if(itemList!=null && itemList.size()>0){
					for(int day=0;day<7;day++){//显示七天
						voList.add(getResultByDate(itemList,DateUtils.addDays(condition.getGroupDate(), day)));
					}
				}else{
					for(int day=0;day<7;day++){
						StockStaticsResultItemVo vo = new StockStaticsResultItemVo();
						vo.setGroupDate(DateUtils.addDays(condition.getGroupDate(), day));
						vo.setStockCount(0);
						vo.setReceiveCount(0);
						vo.setLeftCount(0);
						voList.add(vo);
					}
				}
				result.setItemVoList(voList);
			}			
		}	*/
		
		if(1==1){
			ProductStockPageQueryDTO queryDTO = ProductStockConverter.toQueryDTO(pageBean);
			SolrSearchPageDTO<ProductStockDTO> solrPageResult  = productSolrQueryManager.searchProductStock(queryDTO);
			list= ProductStockConverter.dto2PageBean(solrPageResult);
			if(list!=null && list.size()>0){
				for (StockStaticsResultVOPlus voPlus : list) {
					List<ProductStock> stockList=new ArrayList<ProductStock>();
					String[] stockInfoStrs = voPlus.getStockInfo().split(",");
					for (int i = 0; i < 7; i++) {
					stockList.add(getResultByDate2(DateUtils.addDays(condition.getGroupDate(),i),stockInfoStrs));
					}
					voPlus.setStockList(stockList);
				}
				
			}
			pageBean.setResult(list);
			return pageBean;
		}else{
			
		}
		pageBean.setResult(list);
		return pageBean;
	}
	private ProductStock getResultByDate2(Date date,String[] stockInfoStrs) throws NumberFormatException, ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for (String str : stockInfoStrs) {
			String[] stockInfoStr = str.split("/");
					if (DateUtils.isSameDay(date, sdf.parse(stockInfoStr[0]))) {
						
						ProductStock pStock=new ProductStock();
						pStock.setItemDate(sdf.parse(stockInfoStr[0]));
						pStock.setReceiveCount(Integer.parseInt(stockInfoStr[2]));
						pStock.setStockCount(Integer.parseInt(stockInfoStr[1]));
						return pStock;
					}
				
			}
		ProductStock pStock=new ProductStock();
		pStock.setItemDate(date);
		pStock.setReceiveCount(0);
		pStock.setStockCount(0);
		
			
			
		return pStock;
		
	}

	@Override
	public boolean checkProductCodeExist(Integer productId, Integer bizId,
			String code) {
		Integer count = infoMapper.getCurBizCountByProductIdAndCode(bizId,productId,code);
		if(count==null || count==0){
			return false;
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> getAllId(Integer bizId,Integer state) {
		return infoMapper.getAllIdByBizAndState(bizId, state);
	}
	
	@Override
	public List<ProductInfo> searchProductByNameAndDate(Integer bizId, String prodKeyword, String dateStart){
		return infoMapper.searchProductByNameAndDate(bizId, prodKeyword, dateStart);
	}

	@Override
	public PageBean<ProductInfo> findProductRoutes(
			PageBean<ProductInfo> pageBean, Map parameters) {
		List<ProductInfo> list = infoMapper.selectProductRouteListPage(pageBean, parameters);
		pageBean.setResult(list);
		return pageBean;
	}
	
	@Override
	public PageBean<ProductInfo> selectStockProductListPage(
			PageBean<ProductInfo> pageBean, Map parameters) {
		List<ProductInfo> list = infoMapper.selectStockProductListPage(pageBean, parameters);
		pageBean.setResult(list);
		return pageBean;
	}


	@Override
	public ProductInfo findProductByIdAndBizId(Integer id, Integer bizId) {
		return infoMapper.selectByIdAndBizId(id, bizId);
	}

	@Override
	public PageBean<ProductInfo> findProductAndPriceGroup(
			PageBean<ProductInfo> pageBean) {
		 List<ProductInfo> selectProductAndPriceGroup = infoMapper.selectProductAndPriceListPage(pageBean);
		 pageBean.setResult(selectProductAndPriceGroup);
		 return pageBean;
	}

	@Override
	public int fix_SupplierName(Integer supplierId, String supplierName) {
		// TODO Auto-generated method stub
		return infoMapper.fix_SupplierName_productGroupSupplier(supplierId, supplierName);
	}

	@Override
	public void updateProductSysId(Integer productId,Integer productSysId) {
	infoMapper.updateProductSysId(productId, productSysId);
	}
	
	@Override
	public ProductInfo selectProductInfoByPsId(Integer productSysId) {
		return infoMapper.selectProductInfoByPsId(productSysId);
	}
	
}

