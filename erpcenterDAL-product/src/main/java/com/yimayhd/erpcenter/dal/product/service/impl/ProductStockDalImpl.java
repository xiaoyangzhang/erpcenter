package com.yimayhd.erpcenter.dal.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.dao.ProductStockMapper;
import com.yimayhd.erpcenter.dal.product.dao.TaobaoStockDateMapper;
import com.yimayhd.erpcenter.dal.product.dao.TaobaoStockLogMapper;
import com.yimayhd.erpcenter.dal.product.message.ProductStockUpdateMessageDTO;
import com.yimayhd.erpcenter.dal.product.po.ProductStock;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockDate;
import com.yimayhd.erpcenter.dal.product.po.TaobaoStockLog;
import com.yimayhd.erpcenter.dal.product.query.StockQueryDTO;
import com.yimayhd.erpcenter.dal.product.service.ProductStockDal;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ProductStockDalImpl implements ProductStockDal {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductStockDalImpl.class);

	@Resource
	private ProductStockMapper stockMapper;
	@Autowired
    private TransactionTemplate transactionTemplateProduct;
//	@Autowired
//    private MsgSenderService msgSender;
	@Resource
    private TaobaoStockDateMapper taobaoStockDateMapper;
    @Resource
    private TaobaoStockLogMapper taobaoStockLogMapper;
	
	@Override
	public List<ProductStock> getStocksByProductIdAndDateSpan(Integer productId,
			Date startDate, Date endDate) {
		return stockMapper.getStocksByProductIdAndDateSpan(productId, startDate,endDate);
	}
	
	@Override
    public int insertTaobaoStockDateSelective(TaobaoStockDate taobaoStockDate){
    	taobaoStockDateMapper.insertSelective(taobaoStockDate);
    	return taobaoStockDate.getId();
    }
    
    @Override
    public int updateTaobaoStockDateSelective(TaobaoStockDate taobaoStockDate){
    	taobaoStockDateMapper.updateByPrimaryKeySelective(taobaoStockDate);
    	return taobaoStockDate.getId();
    }
    
    @Override
    public int insertTaobaoStockLogSelective(TaobaoStockLog taobaoStockLog){
    	taobaoStockLogMapper.insertSelective(taobaoStockLog);
    	return taobaoStockLog.getId();
    }
    
    @Override
    public int updateTaobaoStockLogSelective(TaobaoStockLog taobaoStockLog){
    	taobaoStockLogMapper.updateByPrimaryKeySelective(taobaoStockLog);
    	return taobaoStockLog.getId();
    }

    @Override
    public int  updateByLog(Integer stockDateId){
    	taobaoStockDateMapper.updateByLog(stockDateId);
    	return 1;
    }
    
    @Override
    public List<TaobaoStockDate> selectTaobaoStocksByProductIdAndDate(Integer stockId, Date startDate,
            Date endDate) {
        return taobaoStockDateMapper.selectTaobaoStocksByProductIdAndDate(stockId, startDate, endDate);
    }
    
    @Override
    public List<TaobaoStockLog>selectTaobaoStockLogByStockId(Integer stockId){
    	List<TaobaoStockLog> list =taobaoStockLogMapper.selectByStockId(stockId);
    	return list;
    }
    
    @Override
    public List<TaobaoStockLog>selectByStockDateIdAndCreateTime(Integer stockDateId,String startMax,String startMin){
    	List<TaobaoStockLog> list =taobaoStockLogMapper.selectByStockDateIdAndCreateTime(stockDateId,startMax,startMin);
    	return list;
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
//			SendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_STOCK_MODIFY.getTopic(),  ProductTopic.PRODUCT_STOCK_MODIFY.getTags());
//			if(sendResult.getSendStatus() != SendStatus.SEND_OK){
//				LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
//			}
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
		
//		TransactionSendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_STOCK_MODIFY.getTopic(), ProductTopic.PRODUCT_STOCK_MODIFY.getTags(), new LocalTransactionExecuter(){
//
//			@Override
//			public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
				
				stockMapper.updateStockCount(productId, itemDate, count);
				
//				return LocalTransactionState.COMMIT_MESSAGE;
//			}
//
//		});
//
//		if(sendResult.getSendStatus() != SendStatus.SEND_OK){
//			LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
//		}
		
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
//			SendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_STOCK_MODIFY.getTopic(),  ProductTopic.PRODUCT_STOCK_MODIFY.getTags());
//			if(sendResult.getSendStatus() != SendStatus.SEND_OK){
//				LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
//			}
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
//			SendResult sendResult = msgSender.sendMessage(msgDTO, ProductTopic.PRODUCT_STOCK_MODIFY.getTopic(),  ProductTopic.PRODUCT_STOCK_MODIFY.getTags());
//			if(sendResult.getSendStatus() != SendStatus.SEND_OK){
//				LOGGER.error("sendMessage error,sendResult={},msgDTO={}",JSONObject.toJSONString(sendResult),JSONObject.toJSONString(msgDTO));
//			}
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
	
    @Override
    public void updateProductStockByTaobao(List<Map<String, String>> mapList) {
        for (Map<String, String> map : mapList) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                if (StringUtils.isNotBlank(map.get("depDate"))) {
                    date = sdf.parse(map.get("depDate"));

                    stockMapper.updateStockCount(Integer.parseInt(map.get("productId")), date,
                            Integer.parseInt(map.get("receiveCount")));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public TaobaoStockDate selectStockDataById(Integer id) {
		return taobaoStockDateMapper.selectByPrimaryKey(id);
	}

}
