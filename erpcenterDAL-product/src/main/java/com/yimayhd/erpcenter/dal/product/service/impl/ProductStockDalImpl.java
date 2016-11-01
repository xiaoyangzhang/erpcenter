package com.yimayhd.erpcenter.dal.product.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import com.yimayhd.erpcenter.dal.product.dao.ProductStockMapper;
import com.yimayhd.erpcenter.dal.product.message.ProductInfoUpdateMessageDTO;
import com.yimayhd.erpcenter.dal.product.message.ProductStockUpdateMessageDTO;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.query.StockQueryDTO;
import com.yimayhd.erpcenter.dal.product.service.ProductStockDal;
import com.yimayhd.erpcenter.dal.product.topic.ProductTopic;


public class ProductStockDalImpl implements ProductStockDal {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductStockDalImpl.class);

	@Resource
	private ProductStockMapper stockMapper;
	@Autowired
    private TransactionTemplate transactionTemplateProduct;
	@Autowired
    private MsgSenderService msgSender;
	
	@Override
	public List<ProductStock> getStocksByProductIdAndDateSpan(Integer productId,
			Date startDate, Date endDate) {
		return stockMapper.getStocksByProductIdAndDateSpan(productId, startDate,endDate);
	}

	
	@Override
	public void saveStock(final Integer productId,final List<ProductStock> stockList,final Date startDate,final Date endDate) {
		final ProductStockUpdateMessageDTO msgDTO = new ProductStockUpdateMessageDTO();
		msgDTO.setProductId(productId);
		msgDTO.setItemStartDate(startDate);
		msgDTO.setItemEndDate(endDate);
		Boolean dbResult = transactionTemplateProduct.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try{
					int result = stockMapper.setDeleteByProductIdAndDateSpan(productId,startDate,endDate);
					if (result < 0) {
						return false;
					}
					for(ProductStock stock : stockList){
						if(stock.getId() == null){
							int result2 = stockMapper.deleteByProductIdAndDate(productId, stock.getItemDate());
							if (result2 < 0) {
								return false;
							}
							stock.setState(1);
							stock.setCreateTime(new Date().getTime());
							stock.setReceiveCount(0);
							int result3 = stockMapper.insert(stock);
							if (result3 <= 0) {
								return false;
							}
						}else{
							stock.setState(1);
							int result4 = stockMapper.updateByPrimaryKeySelective(stock);
							if (result4 <= 0) {
								return false;
							}
						}
					}
					
					return true;
				}catch(Exception e){
					status.setRollbackOnly(); 
					LOGGER.error("saveStock failed!  productId={},stockList={}", JSON.toJSONString(productId),JSON.toJSONString(stockList), e);
					return false;
				}
			}
		});
		if( dbResult == null || !dbResult ){
			LOGGER.error("saveStock failed!  productId={},stockList={}", JSON.toJSONString(productId),JSON.toJSONString(stockList));
		}else{
			SendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_STOCK_MODIFY.getTopic(),  ProductTopic.PRODUCT_STOCK_MODIFY.getTags());
			if(sendResult.getSendStatus() != SendStatus.SEND_OK){
				LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
			}
		}
	}



	@Override
	public ProductStock getStockByProductIdAndDate(Integer productId, Date date) {
		if(productId==null || date == null){
			return null;
		}
		List<ProductStock> list = stockMapper.getStockByProductIdAndDate(productId, date);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateStockCount(final Integer productId, final Date itemDate, final int count) {		
		
		final ProductStockUpdateMessageDTO msgDTO = new ProductStockUpdateMessageDTO();
		msgDTO.setProductId(productId);
		msgDTO.setItemStartDate(itemDate);
		msgDTO.setItemEndDate(itemDate);
		
		TransactionSendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_STOCK_MODIFY.getTopic(), ProductTopic.PRODUCT_STOCK_MODIFY.getTags(), new LocalTransactionExecuter(){

			@Override
			public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
				
				stockMapper.updateStockCount(productId, itemDate, count);
				
				return LocalTransactionState.COMMIT_MESSAGE;
			}
			
		});
		
		if(sendResult.getSendStatus() != SendStatus.SEND_OK){
			LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
		}
		
		return 1;
	}

	@Override
	public int updateReserveCount(Integer productId, Date itemDate, int count) {
		String type = "P";
		if(count<0){
			count = 0 - count;
			type = "D";
		}		
		int i=stockMapper.updateReserveCount(productId, itemDate, count,type);
		 ProductStockUpdateMessageDTO msgDTO = new ProductStockUpdateMessageDTO();
			msgDTO.setProductId(productId);
			msgDTO.setItemStartDate(itemDate);
			msgDTO.setItemEndDate(itemDate);
			SendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_STOCK_MODIFY.getTopic(),  ProductTopic.PRODUCT_STOCK_MODIFY.getTags());
			if(sendResult.getSendStatus() != SendStatus.SEND_OK){
				LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
			}
		return i;
	}


	@Override
	public int getRestCountByProductIdAndDate(Integer productId, Date date) {
		ProductStock stock = getStockByProductIdAndDate(productId,date);
		if(stock==null){
			return 0;
		}
		return stock.getStockCount()-stock.getReceiveCount();
	}


	@Override
	public int stockCntAddAndReserveCntReduce(Integer productId, Date itemDate,int count) {
		int i=stockMapper.updateReserveCount(productId, itemDate, count,"Z");
		 ProductStockUpdateMessageDTO msgDTO = new ProductStockUpdateMessageDTO();
			msgDTO.setProductId(productId);
			msgDTO.setItemStartDate(itemDate);
			msgDTO.setItemEndDate(itemDate);
			SendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_STOCK_MODIFY.getTopic(),  ProductTopic.PRODUCT_STOCK_MODIFY.getTags());
			if(sendResult.getSendStatus() != SendStatus.SEND_OK){
				LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
			}
		return i;
	}


	@Override
	public List<ProductStock> getStockListByCondition(StockQueryDTO queryDTO) {
		return stockMapper.getStockListByCondition(queryDTO);
	}

	@Override
	public PageBean<ProductStock> getProductStockDumpList(PageBean<ProductStock> pageBean) {
		 List<ProductStock> productInfoList = stockMapper.selectProductStockDumpListPage(pageBean);
		 pageBean.setResult(productInfoList);
		 
		 return pageBean;
	}

}
