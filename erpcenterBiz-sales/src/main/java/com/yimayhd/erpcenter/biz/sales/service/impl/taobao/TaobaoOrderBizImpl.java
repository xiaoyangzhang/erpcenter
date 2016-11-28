package com.yimayhd.erpcenter.biz.sales.service.impl.taobao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.taobao.TaobaoOrderBiz;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;
import com.yimayhd.erpcenter.dal.sales.client.taobao.service.TaobaoOrderDal;

public class TaobaoOrderBizImpl implements TaobaoOrderBiz {
    
    
    @Autowired
    private TaobaoOrderDal taobaoOrderDal;
    
    
    @Override
    public PageBean<PlatTaobaoTrade> selectTaobaoOrder(PageBean<PlatTaobaoTrade> pageBean, Integer bizId) {
    	return taobaoOrderDal.selectTaobaoOrder(pageBean, bizId);
    }
    
    //按订单号
    
    @Override
    public PageBean<PlatTaobaoTrade> selectTaobaoOrderByTid(PageBean<PlatTaobaoTrade> pageBean, Integer bizId) {
    	return taobaoOrderDal.selectTaobaoOrderByTid(pageBean, bizId);
    }
    
//    private void saveOrUpdatePlatTaobaoTrade(List<TaobaoTrade> taobaoTradeList,
//                                             Integer bizId,
//                                             String curUserName,
//                                             String authClient) {
//        for (TaobaoTrade taobaoTrade : taobaoTradeList) {
//            PlatTaobaoTrade platTaobaoTrade = platTaobaoTradeMapper.selectByTid(taobaoTrade.getTid());
//            if (platTaobaoTrade != null) {
//                if (platTaobaoTrade.getTid().equals(taobaoTrade.getTid())) {
//                    if (!platTaobaoTrade.getModified().equals(taobaoTrade.getModified())) {
//                        // 更新操作
//                        updateTaobaoTradeAndOrder(taobaoTrade, platTaobaoTrade);
//                    }
//                }
//            } else {
//                // 添加操作
//                saveTaobaoTradeAndOrder(taobaoTrade, bizId, curUserName, authClient);
//            }
//        }
//    }
    
//    private void saveTaobaoTradeAndOrder(TaobaoTrade taobaoTrade,
//                                         Integer bizId,
//                                         String curUserName,
//                                         String authClient) {
//        PlatTaobaoTrade ptt = new PlatTaobaoTrade();
//        ptt.setBizId(bizId);
//        ptt.setTid(taobaoTrade.getTid());
//        ptt.setPayment(taobaoTrade.getPayment());
//        ptt.setPostFee(taobaoTrade.getPost_fee());
//        ptt.setReceiverName(taobaoTrade.getReceiver_name());
//        ptt.setReceiverState(taobaoTrade.getReceiver_state());
//        ptt.setReceiverAddress(taobaoTrade.getReceiver_address());
//        ptt.setReceiverZip(taobaoTrade.getReceiver_zip());
//        ptt.setReceiverMobile(taobaoTrade.getReceiver_mobile());
//        ptt.setReceiverPhone(taobaoTrade.getReceiver_phone());
//        ptt.setReceiverCity(taobaoTrade.getReceiver_city());
//        ptt.setReceiverDistrict(taobaoTrade.getReceiver_district());
//        ptt.setCreated(taobaoTrade.getCreated());
//        ptt.setPayTime(taobaoTrade.getPay_time());
//        ptt.setModified(taobaoTrade.getModified());
//        ptt.setEndTime(taobaoTrade.getEnd_time());
//        ptt.setSellerMemo(taobaoTrade.getSeller_memo()==null?"":taobaoTrade.getSeller_memo());
//        ptt.setAlipayNo(taobaoTrade.getAlipay_no());
//        ptt.setBuyerMessage(taobaoTrade.getBuyer_message()==null?"":taobaoTrade.getBuyer_message());
//        ptt.setBuyerMemo(taobaoTrade.getBuyer_memo());
//        ptt.setAlipayId(taobaoTrade.getAlipay_id());
//        ptt.setBuyerAlipayNo(taobaoTrade.getBuyer_alipay_no());
//        ptt.setBuyerNick(taobaoTrade.getBuyer_nick());
//        ptt.setBuyerEmail(taobaoTrade.getBuyer_email());
//        ptt.setTradeFrom(taobaoTrade.getTrade_from());
//        ptt.setStatus(taobaoTrade.getStatus());
//        ptt.setType(taobaoTrade.getType());
//        ptt.setStepTradeStatus(taobaoTrade.getStep_trade_status());
//        ptt.setStepPaidFee(taobaoTrade.getStep_paid_fee());
//        ptt.setSyncTime(new Date());
//        ptt.setSyncUsername(curUserName);
//        ptt.setMyState("NEW");
//        ptt.setMyStoreId(authClient);
//        
//        // 保存交易信息
//        platTaobaoTradeMapper.insertSelective(ptt);
//        
//        List<TaobaoTradeOrder> taobaoTradeOrderList = JSONArray.parseArray(JSONObject.parseObject(taobaoTrade.getOrders()).getJSONArray("order").toString(), TaobaoTradeOrder.class);
//        
//        for (TaobaoTradeOrder taobaoTradeOrder : taobaoTradeOrderList) {
//            PlatTaobaoTradeOrder platTaobaoTradeOrder = new PlatTaobaoTradeOrder();
//            
//            platTaobaoTradeOrder.setBizId(bizId);
//            platTaobaoTradeOrder.setTradeId(ptt.getId());
//            platTaobaoTradeOrder.setTid(taobaoTrade.getTid());
//            platTaobaoTradeOrder.setOid(taobaoTradeOrder.getOid());
//            platTaobaoTradeOrder.setStatus(taobaoTradeOrder.getStatus());
//            platTaobaoTradeOrder.setNumIid(taobaoTradeOrder.getNum_iid());
//            platTaobaoTradeOrder.setTitle(taobaoTradeOrder.getTitle());
//            platTaobaoTradeOrder.setPrice(taobaoTradeOrder.getPrice());
//            platTaobaoTradeOrder.setSkuId(taobaoTradeOrder.getSku_id());
//            platTaobaoTradeOrder.setOrderFrom(taobaoTradeOrder.getOrder_from());
//            platTaobaoTradeOrder.setModified(taobaoTradeOrder.getModified());
//            platTaobaoTradeOrder.setEndTime(taobaoTradeOrder.getEnd_time());
//            platTaobaoTradeOrder.setNum(taobaoTradeOrder.getNum());
//            platTaobaoTradeOrder.setTotalFee(taobaoTradeOrder.getTotal_fee());
//            platTaobaoTradeOrder.setPayment(taobaoTradeOrder.getPayment());
//            platTaobaoTradeOrder.setDiscountFee(taobaoTradeOrder.getDiscount_fee());
//            platTaobaoTradeOrder.setAdjustFee(taobaoTradeOrder.getAdjust_fee());
//            platTaobaoTradeOrder.setSkuPropertiesName(taobaoTradeOrder.getSku_properties_name());
//            platTaobaoTradeOrder.setRefundStatus(taobaoTradeOrder.getRefund_status());
//            platTaobaoTradeOrder.setOuterIid(taobaoTradeOrder.getOuter_iid()==null?"":taobaoTradeOrder.getOuter_iid());
//            platTaobaoTradeOrder.setRefundId(taobaoTradeOrder.getRefund_id());
//            platTaobaoTradeOrder.setSyncTime(new Date());
//            platTaobaoTradeOrder.setSyncUsername(curUserName);
//            platTaobaoTradeOrder.setMyState("NEW");
//            platTaobaoTradeOrder.setMyStoreId(authClient);
//            
//            // 保存交易订单信息
//            platTaobaoTradeOrderMapper.insert(platTaobaoTradeOrder);
//        }
//        
//    }
    
//    private void updateTaobaoTradeAndOrder(TaobaoTrade taobaoTrade,
//                                           PlatTaobaoTrade platTaobaoTrade) {
//        //更新交易信息
//        platTaobaoTrade.setPayment(taobaoTrade.getPayment());
//        platTaobaoTrade.setPostFee(taobaoTrade.getPost_fee());
//        platTaobaoTrade.setReceiverName(taobaoTrade.getReceiver_name());
//        platTaobaoTrade.setReceiverState(taobaoTrade.getReceiver_state());
//        platTaobaoTrade.setReceiverAddress(taobaoTrade.getReceiver_address());
//        platTaobaoTrade.setReceiverZip(taobaoTrade.getReceiver_zip());
//        platTaobaoTrade.setReceiverMobile(taobaoTrade.getReceiver_mobile());
//        platTaobaoTrade.setReceiverPhone(taobaoTrade.getReceiver_phone());
//        platTaobaoTrade.setReceiverCity(taobaoTrade.getReceiver_city());
//        platTaobaoTrade.setReceiverDistrict(taobaoTrade.getReceiver_district());
//        platTaobaoTrade.setCreated(taobaoTrade.getCreated());
//        platTaobaoTrade.setPayTime(taobaoTrade.getPay_time());
//        platTaobaoTrade.setModified(taobaoTrade.getModified());
//        platTaobaoTrade.setEndTime(taobaoTrade.getEnd_time());
//        platTaobaoTrade.setSellerMemo(taobaoTrade.getSeller_memo());
//        platTaobaoTrade.setAlipayNo(taobaoTrade.getAlipay_no());
//        platTaobaoTrade.setBuyerMessage(taobaoTrade.getBuyer_message());
//        platTaobaoTrade.setBuyerMemo(taobaoTrade.getBuyer_memo());
//        platTaobaoTrade.setAlipayId(taobaoTrade.getAlipay_id());
//        platTaobaoTrade.setBuyerAlipayNo(taobaoTrade.getBuyer_alipay_no());
//        platTaobaoTrade.setBuyerNick(taobaoTrade.getBuyer_nick());
//        platTaobaoTrade.setBuyerEmail(taobaoTrade.getBuyer_email());
//        platTaobaoTrade.setTradeFrom(taobaoTrade.getTrade_from());
//        platTaobaoTrade.setStatus(taobaoTrade.getStatus());
//        platTaobaoTrade.setType(taobaoTrade.getType());
//        platTaobaoTrade.setStepTradeStatus(taobaoTrade.getStep_trade_status());
//        platTaobaoTrade.setStepPaidFee(taobaoTrade.getStep_paid_fee());
//        
//        platTaobaoTradeMapper.updateByPrimaryKeySelective(platTaobaoTrade);
//        
//        // 更新交易订单信息
//        List<TaobaoTradeOrder> taobaoTradeOrderList = JSONArray.parseArray(
//                JSONObject.parseObject(taobaoTrade.getOrders()).getJSONArray("order").toString(), TaobaoTradeOrder.class);
//        
//        for (TaobaoTradeOrder taobaoTradeOrder : taobaoTradeOrderList) {
//            
//            List<PlatTaobaoTradeOrder> platTaobaoTradeOrderList = platTaobaoTradeOrderMapper.selectByTid(platTaobaoTrade.getTid());
//            if (platTaobaoTradeOrderList.size() > 0) {
//                for (PlatTaobaoTradeOrder platTaobaoTradeOrder : platTaobaoTradeOrderList) {
//                    platTaobaoTradeOrder.setStatus(taobaoTradeOrder.getStatus());
//                    platTaobaoTradeOrder.setNumIid(taobaoTradeOrder.getNum_iid());
//                    platTaobaoTradeOrder.setTitle(taobaoTradeOrder.getTitle());
//                    platTaobaoTradeOrder.setPrice(taobaoTradeOrder.getPrice());
//                    platTaobaoTradeOrder.setSkuId(taobaoTradeOrder.getSku_id());
//                    platTaobaoTradeOrder.setOrderFrom(taobaoTradeOrder.getOrder_from());
//                    platTaobaoTradeOrder.setModified(taobaoTradeOrder.getModified());
//                    platTaobaoTradeOrder.setEndTime(taobaoTradeOrder.getEnd_time());
//                    platTaobaoTradeOrder.setNum(taobaoTradeOrder.getNum());
//                    platTaobaoTradeOrder.setTotalFee(taobaoTradeOrder.getTotal_fee());
//                    platTaobaoTradeOrder.setPayment(taobaoTradeOrder.getPayment());
//                    platTaobaoTradeOrder.setDiscountFee(taobaoTradeOrder.getDiscount_fee());
//                    platTaobaoTradeOrder.setAdjustFee(taobaoTradeOrder.getAdjust_fee());
//                    platTaobaoTradeOrder.setSkuPropertiesName(taobaoTradeOrder.getSku_properties_name());
//                    platTaobaoTradeOrder.setRefundStatus(taobaoTradeOrder.getRefund_status());
//                    platTaobaoTradeOrder.setOuterIid(taobaoTradeOrder.getOuter_iid());
//                    platTaobaoTradeOrder.setRefundId(taobaoTradeOrder.getRefund_id());
//                    
//                    // 保存交易订单信息
//                    platTaobaoTradeOrderMapper.updateByPrimaryKeySelective(platTaobaoTradeOrder);
//                }
//            }
//        }
//        
//        
//    }
//    
    @Override
    public PageBean<PlatTaobaoTrade> selectTaobaoOrderImport(PageBean<PlatTaobaoTrade> pageBean, Integer bizId) {
        return taobaoOrderDal.selectTaobaoOrderImport(pageBean, bizId);
    }
    
    @Override
    public List<PlatTaobaoTrade> selectTaobaoOrderById(String ids) {
        return taobaoOrderDal.selectTaobaoOrderById(ids);
    }
    
    @Override
    public void updateTaobaoOrderId(Integer orderId, String ids) {
    	taobaoOrderDal.updateTaobaoOrderId(orderId, ids);
    }
    
    @Override
    public List<PlatTaobaoTrade> selectTaobaoOrderByOrderId(Integer id) {
        return taobaoOrderDal.selectTaobaoOrderByOrderId(id);
    }
    
    @Override
    public void updateTaobaoOrderIdToZero(String id) {
    	taobaoOrderDal.updateTaobaoOrderIdToZero(id);
    }
    
    @Override
    public void updateCANCEL(String idss) {
    	taobaoOrderDal.updateCANCEL(idss);
    }
    
    @Override
    public void updateNEW(String idss) {
    	taobaoOrderDal.updateNEW(idss);
    }

	@Override
	public void updateOrderId(Integer orderId) {
		taobaoOrderDal.updateOrderId(orderId);
	}

	@Override
	public PageBean<PlatTaobaoTrade> syncTaobaoOrderByTime(PageBean<PlatTaobaoTrade> pageBean, Integer bizId) {
		return taobaoOrderDal.syncTaobaoOrderByTime(pageBean, bizId);
	}

	@Override
	public PlatTaobaoTrade selectTaobaoshopSalesStatistics(PlatTaobaoTrade platTaobaoTrade, Integer bizId) {
		return taobaoOrderDal.selectTaobaoshopSalesStatistics(platTaobaoTrade, bizId);
	}

	@Override
	public PageBean<PlatTaobaoTrade> selectPresellProductStatisticsListPage(PageBean<PlatTaobaoTrade> pageBean,
			Integer bizId) {
		
		return  taobaoOrderDal.selectPresellProductStatisticsListPage(pageBean, bizId);
	}

	@Override
	public PageBean<PlatTaobaoTrade> selectPresellTaobaoOrderListPage(PageBean<PlatTaobaoTrade> pageBean,
			Integer bizId) {
		
		return taobaoOrderDal.selectPresellTaobaoOrderListPage(pageBean, bizId);
	}

	@Override
	public PageBean<PlatTaobaoTrade> selectNotPresellProductStatisticsListPage(PageBean<PlatTaobaoTrade> pageBean,
			Integer bizId) {
		return taobaoOrderDal.selectNotPresellProductStatisticsListPage(pageBean, bizId);
	}

	@Override
	public PageBean<PlatTaobaoTrade> selectSaleOperatorSalesStatisticsListPage(PageBean<PlatTaobaoTrade> pageBean,
			Integer bizId) {
		
		return taobaoOrderDal.selectSaleOperatorSalesStatisticsListPage(pageBean, bizId);
	}

	@Override
	public PageBean<PlatTaobaoTrade> savePushTrade(String tid, String authClient, String response) {
		
		return taobaoOrderDal.savePushTrade(tid, authClient, response);
	}
}
