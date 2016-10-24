package com.yihg.taobao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihg.sales.util.HttpUtil;
import com.yihg.taobao.dao.PlatTaobaoTradeOrderMapper;
import com.yihg.taobao.po.PlatTaobaoTradeOrder;
import com.yihg.taobao.pojo.TaobaoTrade;
import com.yihg.taobao.pojo.TaobaoTradeOrder;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.yihg.mybatis.utility.PageBean;
import com.yihg.sales.po.GroupOrder;
import com.yihg.taobao.api.TaobaoOrderService;
import com.yihg.taobao.po.PlatTaobaoTrade;
import com.yihg.taobao.po.TaobaoOrder;
import com.yihg.taobao.dao.PlatTaobaoTradeMapper;
import com.yihg.taobao.dao.TaobaoOrderMapper;

public class TaobaoOrderServiceImpl implements TaobaoOrderService {
    
    
    @Autowired
    private TaobaoOrderMapper taobaoOrderMapper;
    @Autowired
    private PlatTaobaoTradeMapper platTaobaoTradeMapper;
    @Autowired
    private PlatTaobaoTradeOrderMapper platTaobaoTradeOrderMapper;
    
    private static final String TAOBAO_API_URL = "http://121.41.173.162:30000/yihg-top-api";
    
    @Transactional
    @Override
    public PageBean<PlatTaobaoTrade> selectTaobaoOrder(PageBean<PlatTaobaoTrade> pageBean, Integer bizId) {
        Map<String, String> obj = (Map<String, String>) pageBean.getParameter();
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("startTime", obj.get("startMin"));
        map.put("endTime", obj.get("startMax"));
        map.put("authClient", obj.get("authClient"));
        
        String response = HttpUtil.doPost(TAOBAO_API_URL + "/taoBaoTrades/tradesList.do", map);
        
        if (StringUtils.isNotBlank(response)) {
            System.out.println(JSONObject.parseObject(response));
            // 访问淘宝交易API
            Integer totalResults = JSONObject.parseObject(response).getJSONObject("trades_sold_get_response").getInteger("total_results");
            
            if (totalResults > 0) {
                String trade = JSONObject.parseObject(response).getJSONObject("trades_sold_get_response").getJSONObject("trades").getJSONArray("trade").toString();
                List<TaobaoTrade> taobaoTradeList = JSONArray.parseArray(trade, TaobaoTrade.class);
                
                if (taobaoTradeList.size() > 0) {
                    saveOrUpdatePlatTaobaoTrade(taobaoTradeList, bizId, obj.get("curUserName"), obj.get("authClient"));
                }
            }
        }
        List<PlatTaobaoTrade> result = platTaobaoTradeMapper.selectTaobaoOrderListPage(pageBean, bizId);
        pageBean.setResult(result);
        return pageBean;
    }
    
    //按订单号
    @Transactional
    @Override
    public PageBean<PlatTaobaoTrade> selectTaobaoOrderByTid(PageBean<PlatTaobaoTrade> pageBean, Integer bizId) {
        Map<String, String> obj = (Map<String, String>) pageBean.getParameter();
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("tid", obj.get("tid"));
        map.put("authClient", obj.get("authClient"));
        
        String response = HttpUtil.doPost(TAOBAO_API_URL + "/taoBaoTrades/tradeDetail.do", map);
        
        if (StringUtils.isNotBlank(response)) {
            if (response.contains("trade_fullinfo_get_response")) {
                // 访问淘宝交易API
                String trade = JSONObject.parseObject(response).getJSONObject("trade_fullinfo_get_response").getJSONObject("trade").toString();
                List<TaobaoTrade> taobaoTradeList = new ArrayList<TaobaoTrade>();
                taobaoTradeList.add(JSONArray.parseObject(trade, TaobaoTrade.class));
                if (taobaoTradeList.size() > 0) {
                    saveOrUpdatePlatTaobaoTrade(taobaoTradeList, bizId, obj.get("curUserName"), obj.get("authClient"));
                }
            }
        }
        PlatTaobaoTrade platTaobaoTrade = platTaobaoTradeMapper.selectByTid(obj.get("tid"));
        List<PlatTaobaoTrade> result = new ArrayList<PlatTaobaoTrade>();
        result.add(platTaobaoTrade);
        pageBean.setResult(result);
        return pageBean;
    }
    
    private void saveOrUpdatePlatTaobaoTrade(List<TaobaoTrade> taobaoTradeList,
                                             Integer bizId,
                                             String curUserName,
                                             String authClient) {
        for (TaobaoTrade taobaoTrade : taobaoTradeList) {
            PlatTaobaoTrade platTaobaoTrade = platTaobaoTradeMapper.selectByTid(taobaoTrade.getTid());
            if (platTaobaoTrade != null) {
                if (platTaobaoTrade.getTid().equals(taobaoTrade.getTid())) {
                    if (!platTaobaoTrade.getModified().equals(taobaoTrade.getModified())) {
                        // 更新操作
                        updateTaobaoTradeAndOrder(taobaoTrade, platTaobaoTrade);
                    }
                }
            } else {
                // 添加操作
                saveTaobaoTradeAndOrder(taobaoTrade, bizId, curUserName, authClient);
            }
        }
    }
    
    private void saveTaobaoTradeAndOrder(TaobaoTrade taobaoTrade,
                                         Integer bizId,
                                         String curUserName,
                                         String authClient) {
        PlatTaobaoTrade ptt = new PlatTaobaoTrade();
        ptt.setBizId(bizId);
        ptt.setTid(taobaoTrade.getTid());
        ptt.setPayment(taobaoTrade.getPayment());
        ptt.setPostFee(taobaoTrade.getPost_fee());
        ptt.setReceiverName(taobaoTrade.getReceiver_name());
        ptt.setReceiverState(taobaoTrade.getReceiver_state());
        ptt.setReceiverAddress(taobaoTrade.getReceiver_address());
        ptt.setReceiverZip(taobaoTrade.getReceiver_zip());
        ptt.setReceiverMobile(taobaoTrade.getReceiver_mobile());
        ptt.setReceiverPhone(taobaoTrade.getReceiver_phone());
        ptt.setReceiverCity(taobaoTrade.getReceiver_city());
        ptt.setReceiverDistrict(taobaoTrade.getReceiver_district());
        ptt.setCreated(taobaoTrade.getCreated());
        ptt.setPayTime(taobaoTrade.getPay_time());
        ptt.setModified(taobaoTrade.getModified());
        ptt.setEndTime(taobaoTrade.getEnd_time());
        ptt.setSellerMemo(taobaoTrade.getSeller_memo()==null?"":taobaoTrade.getSeller_memo());
        ptt.setAlipayNo(taobaoTrade.getAlipay_no());
        ptt.setBuyerMessage(taobaoTrade.getBuyer_message()==null?"":taobaoTrade.getBuyer_message());
        ptt.setBuyerMemo(taobaoTrade.getBuyer_memo());
        ptt.setAlipayId(taobaoTrade.getAlipay_id());
        ptt.setBuyerAlipayNo(taobaoTrade.getBuyer_alipay_no());
        ptt.setBuyerNick(taobaoTrade.getBuyer_nick());
        ptt.setBuyerEmail(taobaoTrade.getBuyer_email());
        ptt.setTradeFrom(taobaoTrade.getTrade_from());
        ptt.setStatus(taobaoTrade.getStatus());
        ptt.setType(taobaoTrade.getType());
        ptt.setStepTradeStatus(taobaoTrade.getStep_trade_status());
        ptt.setStepPaidFee(taobaoTrade.getStep_paid_fee());
        ptt.setSyncTime(new Date());
        ptt.setSyncUsername(curUserName);
        ptt.setMyState("NEW");
        ptt.setMyStoreId(authClient);
        
        // 保存交易信息
        platTaobaoTradeMapper.insertSelective(ptt);
        
        List<TaobaoTradeOrder> taobaoTradeOrderList = JSONArray.parseArray(JSONObject.parseObject(taobaoTrade.getOrders()).getJSONArray("order").toString(), TaobaoTradeOrder.class);
        
        for (TaobaoTradeOrder taobaoTradeOrder : taobaoTradeOrderList) {
            PlatTaobaoTradeOrder platTaobaoTradeOrder = new PlatTaobaoTradeOrder();
            
            platTaobaoTradeOrder.setBizId(bizId);
            platTaobaoTradeOrder.setTradeId(ptt.getId());
            platTaobaoTradeOrder.setTid(taobaoTrade.getTid());
            platTaobaoTradeOrder.setOid(taobaoTradeOrder.getOid());
            platTaobaoTradeOrder.setStatus(taobaoTradeOrder.getStatus());
            platTaobaoTradeOrder.setNumIid(taobaoTradeOrder.getNum_iid());
            platTaobaoTradeOrder.setTitle(taobaoTradeOrder.getTitle());
            platTaobaoTradeOrder.setPrice(taobaoTradeOrder.getPrice());
            platTaobaoTradeOrder.setSkuId(taobaoTradeOrder.getSku_id());
            platTaobaoTradeOrder.setOrderFrom(taobaoTradeOrder.getOrder_from());
            platTaobaoTradeOrder.setModified(taobaoTradeOrder.getModified());
            platTaobaoTradeOrder.setEndTime(taobaoTradeOrder.getEnd_time());
            platTaobaoTradeOrder.setNum(taobaoTradeOrder.getNum());
            platTaobaoTradeOrder.setTotalFee(taobaoTradeOrder.getTotal_fee());
            platTaobaoTradeOrder.setPayment(taobaoTradeOrder.getPayment());
            platTaobaoTradeOrder.setDiscountFee(taobaoTradeOrder.getDiscount_fee());
            platTaobaoTradeOrder.setAdjustFee(taobaoTradeOrder.getAdjust_fee());
            platTaobaoTradeOrder.setSkuPropertiesName(taobaoTradeOrder.getSku_properties_name());
            platTaobaoTradeOrder.setRefundStatus(taobaoTradeOrder.getRefund_status());
            platTaobaoTradeOrder.setOuterIid(taobaoTradeOrder.getOuter_iid()==null?"":taobaoTradeOrder.getOuter_iid());
            platTaobaoTradeOrder.setRefundId(taobaoTradeOrder.getRefund_id());
            platTaobaoTradeOrder.setSyncTime(new Date());
            platTaobaoTradeOrder.setSyncUsername(curUserName);
            platTaobaoTradeOrder.setMyState("NEW");
            platTaobaoTradeOrder.setMyStoreId(authClient);
            
            // 保存交易订单信息
            platTaobaoTradeOrderMapper.insert(platTaobaoTradeOrder);
        }
        
    }
    
    private void updateTaobaoTradeAndOrder(TaobaoTrade taobaoTrade,
                                           PlatTaobaoTrade platTaobaoTrade) {
        //更新交易信息
        platTaobaoTrade.setPayment(taobaoTrade.getPayment());
        platTaobaoTrade.setPostFee(taobaoTrade.getPost_fee());
        platTaobaoTrade.setReceiverName(taobaoTrade.getReceiver_name());
        platTaobaoTrade.setReceiverState(taobaoTrade.getReceiver_state());
        platTaobaoTrade.setReceiverAddress(taobaoTrade.getReceiver_address());
        platTaobaoTrade.setReceiverZip(taobaoTrade.getReceiver_zip());
        platTaobaoTrade.setReceiverMobile(taobaoTrade.getReceiver_mobile());
        platTaobaoTrade.setReceiverPhone(taobaoTrade.getReceiver_phone());
        platTaobaoTrade.setReceiverCity(taobaoTrade.getReceiver_city());
        platTaobaoTrade.setReceiverDistrict(taobaoTrade.getReceiver_district());
        platTaobaoTrade.setCreated(taobaoTrade.getCreated());
        platTaobaoTrade.setPayTime(taobaoTrade.getPay_time());
        platTaobaoTrade.setModified(taobaoTrade.getModified());
        platTaobaoTrade.setEndTime(taobaoTrade.getEnd_time());
        platTaobaoTrade.setSellerMemo(taobaoTrade.getSeller_memo());
        platTaobaoTrade.setAlipayNo(taobaoTrade.getAlipay_no());
        platTaobaoTrade.setBuyerMessage(taobaoTrade.getBuyer_message());
        platTaobaoTrade.setBuyerMemo(taobaoTrade.getBuyer_memo());
        platTaobaoTrade.setAlipayId(taobaoTrade.getAlipay_id());
        platTaobaoTrade.setBuyerAlipayNo(taobaoTrade.getBuyer_alipay_no());
        platTaobaoTrade.setBuyerNick(taobaoTrade.getBuyer_nick());
        platTaobaoTrade.setBuyerEmail(taobaoTrade.getBuyer_email());
        platTaobaoTrade.setTradeFrom(taobaoTrade.getTrade_from());
        platTaobaoTrade.setStatus(taobaoTrade.getStatus());
        platTaobaoTrade.setType(taobaoTrade.getType());
        platTaobaoTrade.setStepTradeStatus(taobaoTrade.getStep_trade_status());
        platTaobaoTrade.setStepPaidFee(taobaoTrade.getStep_paid_fee());
        
        platTaobaoTradeMapper.updateByPrimaryKeySelective(platTaobaoTrade);
        
        // 更新交易订单信息
        List<TaobaoTradeOrder> taobaoTradeOrderList = JSONArray.parseArray(
                JSONObject.parseObject(taobaoTrade.getOrders()).getJSONArray("order").toString(), TaobaoTradeOrder.class);
        
        for (TaobaoTradeOrder taobaoTradeOrder : taobaoTradeOrderList) {
            
            List<PlatTaobaoTradeOrder> platTaobaoTradeOrderList = platTaobaoTradeOrderMapper.selectByTid(platTaobaoTrade.getTid());
            if (platTaobaoTradeOrderList.size() > 0) {
                for (PlatTaobaoTradeOrder platTaobaoTradeOrder : platTaobaoTradeOrderList) {
                    platTaobaoTradeOrder.setStatus(taobaoTradeOrder.getStatus());
                    platTaobaoTradeOrder.setNumIid(taobaoTradeOrder.getNum_iid());
                    platTaobaoTradeOrder.setTitle(taobaoTradeOrder.getTitle());
                    platTaobaoTradeOrder.setPrice(taobaoTradeOrder.getPrice());
                    platTaobaoTradeOrder.setSkuId(taobaoTradeOrder.getSku_id());
                    platTaobaoTradeOrder.setOrderFrom(taobaoTradeOrder.getOrder_from());
                    platTaobaoTradeOrder.setModified(taobaoTradeOrder.getModified());
                    platTaobaoTradeOrder.setEndTime(taobaoTradeOrder.getEnd_time());
                    platTaobaoTradeOrder.setNum(taobaoTradeOrder.getNum());
                    platTaobaoTradeOrder.setTotalFee(taobaoTradeOrder.getTotal_fee());
                    platTaobaoTradeOrder.setPayment(taobaoTradeOrder.getPayment());
                    platTaobaoTradeOrder.setDiscountFee(taobaoTradeOrder.getDiscount_fee());
                    platTaobaoTradeOrder.setAdjustFee(taobaoTradeOrder.getAdjust_fee());
                    platTaobaoTradeOrder.setSkuPropertiesName(taobaoTradeOrder.getSku_properties_name());
                    platTaobaoTradeOrder.setRefundStatus(taobaoTradeOrder.getRefund_status());
                    platTaobaoTradeOrder.setOuterIid(taobaoTradeOrder.getOuter_iid());
                    platTaobaoTradeOrder.setRefundId(taobaoTradeOrder.getRefund_id());
                    
                    // 保存交易订单信息
                    platTaobaoTradeOrderMapper.updateByPrimaryKeySelective(platTaobaoTradeOrder);
                }
            }
        }
        
        
    }
    
    @Override
    public PageBean<PlatTaobaoTrade> selectTaobaoOrderImport(PageBean<PlatTaobaoTrade> pageBean, Integer bizId) {
        List<PlatTaobaoTrade> result = platTaobaoTradeMapper.selectTaobaoOrderListPage(pageBean, bizId);
        pageBean.setResult(result);
        return pageBean;
    }
    
    @Override
    public List<PlatTaobaoTrade> selectTaobaoOrderById(String ids) {
        return platTaobaoTradeMapper.selectTaobaoOrderById(ids);
    }
    
    @Override
    public void updateTaobaoOrderId(Integer orderId, String ids) {
        platTaobaoTradeMapper.updateTaobaoOrderId(orderId, ids);
        platTaobaoTradeMapper.updateTaobaoOrderIdToOrder(orderId, ids);
    }
    
    @Override
    public List<PlatTaobaoTrade> selectTaobaoOrderByOrderId(Integer id) {
        return platTaobaoTradeMapper.selectTaobaoOrderByOrderId(id);
    }
    
    @Override
    public void updateTaobaoOrderIdToZero(String id) {
        platTaobaoTradeMapper.updateTaobaoOrderIdToZero(id);
        platTaobaoTradeMapper.updateTaobaoOrderIdToZeroToOrder(id);
    }
    
    @Override
    public void updateCANCEL(String idss) {
        platTaobaoTradeMapper.updateCANCEL(idss);
    }
    
    @Override
    public void updateNEW(String idss) {
        platTaobaoTradeMapper.updateNEW(idss);
    }
}
