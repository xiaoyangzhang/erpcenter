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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.mq.MsgSenderService;
import com.yimayhd.erpcenter.common.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.product.dao.ProductAttachmentMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductContactMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductGroupPriceMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductInfoMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductRemarkMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductRightMapper;
import com.yimayhd.erpcenter.dal.product.dao.ProductRouteMapper;
import com.yimayhd.erpcenter.dal.product.dto.ProductStateDTO;
import com.yimayhd.erpcenter.dal.product.dto.ProductStockDTO;
import com.yimayhd.erpcenter.dal.product.message.ProductInfoUpdateMessageDTO;
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
import com.yimayhd.erpcenter.dal.product.solr.converter.ProductStateConverter;
import com.yimayhd.erpcenter.dal.product.solr.converter.ProductStockConverter;
import com.yimayhd.erpcenter.dal.product.solr.manager.ProductSolrQueryManager;
import com.yimayhd.erpcenter.dal.product.topic.ProductTopic;
import com.yimayhd.erpcenter.dal.product.vo.ProductInfoVo;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultItemVo;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultVOPlus;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultVo;

public class ProductInfoDalImpl implements ProductInfoDal{

	private static final Logger LOGGER = LoggerFactory.getLogger("ProductInfoDalImpl");
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
    private ProductSolrQueryManager productSolrQueryManager;
    @Autowired
    private  ProductRightMapper productRightMapper;
    @Autowired
    private TransactionTemplate transactionTemplateProduct;
    
    @Autowired
    private MsgSenderService msgSender;

	@Override
	public int insertSelective(final ProductInfo record) {
		final ProductInfoUpdateMessageDTO msgDTO = new ProductInfoUpdateMessageDTO();
		
		infoMapper.insertSelective(record);
		msgDTO.setProductId(record.getId());
		SendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_MODIFY.getTopic(), ProductTopic.PRODUCT_MODIFY.getTags());
		
		if(sendResult.getSendStatus() != SendStatus.SEND_OK){
			LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
		}
		
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
		if(1==1){
			List<ProductInfo> list = infoMapper.selectProductInfoListPage(pageBean, parameters);
			pageBean.setResult(list);
		}else{
			ProductStatePageQueryDTO queryDTO = ProductStateConverter.toQueryDTO(pageBean, parameters);
			SolrSearchPageDTO<ProductStateDTO> solrPageResult  = productSolrQueryManager.searchProductState(queryDTO);
			return ProductStateConverter.dto2PageBean(solrPageResult);
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
		
		if(1==1){
			List<ProductSales> list = infoMapper.selectProductSalesPlusListPage(pageBean, bizId,orgId);
			pageBean.setResult(list);
		}else{
			ProductStatePageQueryDTO queryDTO = ProductStateConverter.toQueryDTOSales(pageBean, bizId, orgId);
			SolrSearchPageDTO<ProductStateDTO> solrPageResult  = productSolrQueryManager.searchProductState(queryDTO);
			return ProductStateConverter.dto2PageBeanSales(solrPageResult);
		}
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

	//@Transactional
	@Override
	public int saveProductInfo(final ProductInfoVo productInfoVo,final String bizCode,final String brandCode) {
		
		final ProductInfoUpdateMessageDTO msgDTO = new ProductInfoUpdateMessageDTO();
		
		final ProductInfo info = productInfoVo.getProductInfo();
		final List<ProductContact> productContacts = productInfoVo.getProductContacts();
		final List<ProductAttachment> productAttachments = productInfoVo.getProductAttachments();
		final List<ProductAttachment> attachments = productInfoVo.getAttachments();
		
		final List<ProductAttachment> imgList = attachmentMapper.selectByAttList(info.getId(), 1);
		final List<ProductAttachment> attachList = attachmentMapper.selectByAttList(info.getId(), 2);
		Boolean dbResult = transactionTemplateProduct.execute(new TransactionCallback<Boolean>() {
			 int i = 0;
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
					if(null!=productInfoVo.getProductInfo().getId()){
						msgDTO.setProductId(productInfoVo.getProductInfo().getId());
						//修改
						i= infoMapper.updateByPrimaryKeySelective(info);
						if (i < 1) {
							status.setRollbackOnly();
							LOGGER.error("infoMapper.updateByPrimaryKeySelective error,params:info={},result:{}",JSON.toJSONString(info),i);
							return false;
						}
						//删除责任人 该表已废弃
						//contactMapper.deleteByproductId(info.getId());
						//删除图片
						int deleteImgResult = attachmentMapper.deleteByobjId(info.getId(),1);
						if (deleteImgResult != imgList.size()) {
							status.setRollbackOnly();
							LOGGER.error("attachmentMapper.deleteByobjId error,params:objId={},objType={},result:{}",info.getId(),1,deleteImgResult);
							return false;
						}
						int deleteAttachResult = attachmentMapper.deleteByobjId(info.getId(),2);
						if (deleteAttachResult != attachList.size()) {
							status.setRollbackOnly();
							LOGGER.error("attachmentMapper.deleteByobjId error,params:objId={},objType={},result:{}",info.getId(),2,deleteAttachResult);
							return false;
						}
					}else{
						if ("".equals(info.getCode())){ //若为空，则由系统产生订单号
							int count=infoMapper.getBizAndBrandCodeCount(productInfoVo.getProductInfo().getBizId(), productInfoVo.getProductInfo().getBrandId());
							info.setCode(getProductCode(bizCode, brandCode, count));
						}
						info.setState((byte) 1);
						info.setCreateTime(System.currentTimeMillis());
						i=infoMapper.insertSelective(info);
						msgDTO.setProductId(i);
						
						if (i < 1) {
							status.setRollbackOnly();
							LOGGER.error("infoMapper.insertSelective error,params:info={},result:{}",JSON.toJSONString(info),i);
							return false;
						}
						//新增时默认把当前人的数据权限加在上
						int insertResult = productRightMapper.insertBatch(info.getId(), productInfoVo.getOrgIdSet());
						if (i < 1) {
							status.setRollbackOnly();
							LOGGER.error("productRightMapper.insertBatch error,params:id={},orgIdSet={},result:{}",info.getId(),JSON.toJSONString(productInfoVo.getOrgIdSet()),insertResult);
							return false;
						}
					}
					//插入责任人
					/*if(productContacts!=null&&!productContacts.isEmpty()){
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
					}*/
					//插入图片
					if(productAttachments!=null&&!productAttachments.isEmpty()){
						for (ProductAttachment productAttachment : productAttachments) {
							if(StringUtils.isNotBlank(productAttachment.getName())
									||StringUtils.isNotBlank(productAttachment.getPath())){
								productAttachment.setCreateTime(System.currentTimeMillis());
								productAttachment.setObjId(info.getId());
								productAttachment.setObjType((byte)1);
								//attachmentMapper.insertSelective(productAttachment);
							}
						}
						int insertImgResult = attachmentMapper.insertBatch(productAttachments);
						if (insertImgResult != productAttachments.size()) {
							status.setRollbackOnly();
							LOGGER.error("attachmentMapper.insertBatch error,params:productAttachments={},result:{}",JSON.toJSONString(productAttachments),insertImgResult);
							return false;
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
								//attachmentMapper.insertSelective(attachment);
							}
						}
						int insertAttachResult = attachmentMapper.insertBatch(attachments);
						if (insertAttachResult != attachments.size()) {
							status.setRollbackOnly();
							LOGGER.error("attachmentMapper.insertBatch error,params:productAttachments={},result:{}",JSON.toJSONString(attachments),insertAttachResult);
							return false;
						}
					}
					return true;
				} catch (Exception e) {
					status.setRollbackOnly();
					LOGGER.error("error:{}",e);
					return false;
				}
			}
		});
		if (dbResult == null || !dbResult) {
			LOGGER.error("save productInfo failed ,params:productInfoVo={},bizCode={},brandCode={}",JSON.toJSONString(productInfoVo),bizCode,brandCode);
			return -1;
		}else{
			
			SendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_MODIFY.getTopic(),  ProductTopic.PRODUCT_MODIFY.getTags());
			if(sendResult.getSendStatus() != SendStatus.SEND_OK){
				LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
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
	public int updateProductInfo(final ProductInfo productInfo) {
		final ProductInfoUpdateMessageDTO msgDTO = new ProductInfoUpdateMessageDTO();
		msgDTO.setProductId(productInfo.getId());
		
		TransactionSendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_MODIFY.getTopic(), ProductTopic.PRODUCT_MODIFY.getTags(), new LocalTransactionExecuter(){

			@Override
			public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
				
				infoMapper.updateByPrimaryKeySelective(productInfo);
				
				return LocalTransactionState.COMMIT_MESSAGE;
			}
			
		});
		
		if(sendResult.getSendStatus() != SendStatus.SEND_OK){
			LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
		}
		
		return 1;

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
	public void saveProductRight(final Integer productId,final Set<Integer> orgIdSet) {
		
		final ProductInfoUpdateMessageDTO msgDTO = new ProductInfoUpdateMessageDTO();
		msgDTO.setProductId(productId);
		final List<ProductRight> productRights = getRightListByProductId(productId);
		final int size = orgIdSet.size();
		Boolean dbResult = transactionTemplateProduct.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
					int deleteResult = productRightMapper.deleteByProductId(productId);
					if (deleteResult != productRights.size()) {
						LOGGER.error("productRightMapper.deleteByProductId error,param:productId={},result:deleteResult={}",productId,deleteResult);
						status.setRollbackOnly();
						return false;
					}
					int insertResult = productRightMapper.insertBatch(productId, orgIdSet);
					if (insertResult != size) {
						LOGGER.error("productRightMapper.insertBatch error,param:productId={},orgIdSet={},result:insertResult={}",productId,JSON.toJSONString(orgIdSet),insertResult);
						status.setRollbackOnly();
						return false;
					}
					return true;
				} catch (Exception e) {
					status.setRollbackOnly();
					LOGGER.error("error:{}",e);
					return false;
				}
			}
		});
		
		
		SendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_MODIFY.getTopic(),  ProductTopic.PRODUCT_MODIFY.getTags());
		if(sendResult.getSendStatus() != SendStatus.SEND_OK){
			LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
		}
		
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
		if(1== 1){
			list=infoMapper.getProductStockListPage(pageBean);
			
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
		}else{
			ProductStockPageQueryDTO queryDTO = ProductStockConverter.toQueryDTO(pageBean);
			SolrSearchPageDTO<StockStaticsResultVOPlus> solrPageResult  = productSolrQueryManager.searchProductStock(queryDTO);
			list=solrPageResult.getList();
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

		}
		
		//SimpleDateFormat sdf=new SimpleDateFormat();
		
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
	public void updateProductSysId(final Integer productId,final Integer productSysId) {
		
	final ProductInfoUpdateMessageDTO msgDTO = new ProductInfoUpdateMessageDTO();
	msgDTO.setProductId(productId);	
	
	SendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_MODIFY.getTopic(),ProductTopic.PRODUCT_MODIFY.getTags(), new LocalTransactionExecuter() {
		@Override
		public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
			infoMapper.updateProductSysId(productId, productSysId);
						
			return LocalTransactionState.COMMIT_MESSAGE;
		}
	 });
	
	if(sendResult.getSendStatus() != SendStatus.SEND_OK){
		LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
	}
	
	
	}
	
	@Override
	public ProductInfo selectProductInfoByPsId(Integer productSysId) {
		return infoMapper.selectProductInfoByPsId(productSysId);
	}
	
	@Override
	public PageBean<ProductInfo> getProductInfoDumpList(
			PageBean<ProductInfo> pageBean) {
		 List<ProductInfo> productInfoList = infoMapper.selectProductDumpListPage(pageBean);
		 pageBean.setResult(productInfoList);
		 return pageBean;
	}
}

