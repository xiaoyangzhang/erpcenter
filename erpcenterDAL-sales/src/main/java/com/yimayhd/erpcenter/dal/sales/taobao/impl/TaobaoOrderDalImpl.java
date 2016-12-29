package com.yimayhd.erpcenter.dal.sales.taobao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTradeOrder;
import com.yimayhd.erpcenter.dal.sales.client.taobao.pojo.TaobaoTrade;
import com.yimayhd.erpcenter.dal.sales.client.taobao.pojo.TaobaoTradeOrder;
import com.yimayhd.erpcenter.dal.sales.client.taobao.service.TaobaoOrderDal;
import com.yimayhd.erpcenter.dal.sales.sales.util.HttpUtil;
import com.yimayhd.erpcenter.dal.sales.taobao.dao.PlatTaobaoTradeMapper;
import com.yimayhd.erpcenter.dal.sales.taobao.dao.PlatTaobaoTradeOrderMapper;
import com.yimayhd.erpcenter.dal.sales.taobao.dao.TaobaoOrderMapper;

public class TaobaoOrderDalImpl implements TaobaoOrderDal {@Autowired
    private PlatTaobaoTradeMapper platTaobaoTradeMapper;
    @Autowired
    private PlatTaobaoTradeOrderMapper platTaobaoTradeOrderMapper;

    private static final String TAOBAO_API_URL = "http://121.41.173.162:30000/yihg-top-api";

    @Override
    public void updateOrderId(Integer orderId) {
        List<PlatTaobaoTrade> pttLists = platTaobaoTradeMapper.selectAllByOrderId(orderId);
        List<PlatTaobaoTradeOrder> pttoLists = platTaobaoTradeOrderMapper.selectByOrderId(orderId);
        if (pttLists != null && pttLists.size() > 0) {
            for (PlatTaobaoTrade item : pttLists) {
                item.setOrderId(0);
                item.setMyState("NEW");
                platTaobaoTradeMapper.updateByPrimaryKeySelective(item);
            }
        }
        if (pttoLists != null && pttoLists.size() > 0) {
            for (PlatTaobaoTradeOrder item : pttoLists) {
                item.setOrderId(0);
                item.setMyState("NEW");
                platTaobaoTradeOrderMapper.updateByPrimaryKeySelective(item);
            }
        }
    }

    @Override
    public PageBean<PlatTaobaoTrade> selectTaobaoOrder(PageBean<PlatTaobaoTrade> pageBean, Integer bizId) {
        List<PlatTaobaoTrade> result = platTaobaoTradeMapper.selectTaobaoOrderListPage(pageBean, bizId);
        pageBean.setResult(result);
        return pageBean;
    }

    @Transactional
    @Override
    public PageBean<PlatTaobaoTrade> syncTaobaoOrderByTime(PageBean<PlatTaobaoTrade> pageBean, Integer bizId) {
        Map<String, String> obj = (Map<String, String>) pageBean.getParameter();

        Map<String, String> map = new HashMap<String, String>();
        map.put("authClient", obj.get("authClient"));
        String objTime = obj.get("startTime");
        map.put("startTime", objTime + " 00:00:00");
        map.put("endTime", objTime + " 00:59:59");
        // 时间同步 每小时插入或更新
        for(int i = 0; i < 24; i++){
            if(i == 1) {
                map.put("startTime", objTime + " 01:00:00");
                map.put("endTime", objTime + " 01:59:59");    
            } else if (i == 2) {
                map.put("startTime", objTime + " 02:00:00");
                map.put("endTime", objTime + " 02:59:59");
            } else if (i == 3) {
                map.put("startTime", objTime + " 03:00:00");
                map.put("endTime", objTime + " 03:59:59");
            } else if (i == 4) {
                map.put("startTime", objTime + " 04:00:00");
                map.put("endTime", objTime + " 04:59:59");
            } else if (i == 5) {
                map.put("startTime", objTime + " 05:00:00");
                map.put("endTime", objTime + " 05:59:59");
            } else if (i == 6) {
                map.put("startTime", objTime + " 06:00:00");
                map.put("endTime", objTime + " 06:59:59");
            } else if (i == 7) {
                map.put("startTime", objTime + " 07:00:00");
                map.put("endTime", objTime + " 07:59:59");
            } else if (i == 8) {
                map.put("startTime", objTime + " 08:00:00");
                map.put("endTime", objTime + " 08:59:59");
            } else if (i == 9) {
                map.put("startTime", objTime + " 09:00:00");
                map.put("endTime", objTime + " 09:59:59");
            } else if (i == 10) {
                map.put("startTime", objTime + " 10:00:00");
                map.put("endTime", objTime + " 10:59:59");
            } else if (i == 11) {
                map.put("startTime", objTime + " 11:00:00");
                map.put("endTime", objTime + " 11:59:59");
            } else if (i == 12) {
                map.put("startTime", objTime + " 12:00:00");
                map.put("endTime", objTime + " 12:59:59");
            } else if (i == 13) {
                map.put("startTime", objTime + " 13:00:00");
                map.put("endTime", objTime + " 13:59:59");
            } else if (i == 14) {
                map.put("startTime", objTime + " 14:00:00");
                map.put("endTime", objTime + " 14:59:59");
            } else if (i == 15) {
                map.put("startTime", objTime + " 15:00:00");
                map.put("endTime", objTime + " 15:59:59");
            } else if (i == 16) {
                map.put("startTime", objTime + " 16:00:00");
                map.put("endTime", objTime + " 16:59:59");
            } else if (i == 17) {
                map.put("startTime", objTime + " 17:00:00");
                map.put("endTime", objTime + " 17:59:59");
            } else if (i == 18) {
                map.put("startTime", objTime + " 18:00:00");
                map.put("endTime", objTime + " 18:59:59");
            } else if (i == 19) {
                map.put("startTime", objTime + " 19:00:00");
                map.put("endTime", objTime + " 19:59:59");
            } else if (i == 20) {
                map.put("startTime", objTime + " 20:00:00");
                map.put("endTime", objTime + " 20:59:59");
            } else if (i == 21) {
                map.put("startTime", objTime + " 21:00:00");
                map.put("endTime", objTime + " 21:59:59");
            } else if (i == 22) {
                map.put("startTime", objTime + " 22:00:00");
                map.put("endTime", objTime + " 22:59:59");
            } else if (i == 23) {
                map.put("startTime", objTime + " 23:00:00");
                map.put("endTime", objTime + " 23:59:59");
            }
            
            String response = HttpUtil.doPost(TAOBAO_API_URL + "/taoBaoTrades/tradesList.do", map);
        
            if (response.indexOf("[") > -1) {
                if (!"null".equals(response)) {
                    // 访问淘宝交易API
                    List<TaobaoTrade> taobaoTradeList = JSONArray.parseArray(response, TaobaoTrade.class);
        
                    if (taobaoTradeList.size() > 0) {
                        saveOrUpdatePlatTaobaoTrade(taobaoTradeList, bizId, obj.get("curUserName"), obj.get("authClient"));
                    }
                }
            }
        }

        List<PlatTaobaoTrade> result = platTaobaoTradeMapper.selectTaobaoOrderListPage(pageBean, bizId);
        pageBean.setResult(result);
        return pageBean;
    }

    // 按订单号
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
                String trade = JSONObject.parseObject(response).getJSONObject("trade_fullinfo_get_response")
                        .getJSONObject("trade").toString();
                List<TaobaoTrade> taobaoTradeList = new ArrayList<TaobaoTrade>();
                taobaoTradeList.add(JSONArray.parseObject(trade, TaobaoTrade.class));
                if (taobaoTradeList.size() > 0) {
                    saveOrUpdatePlatTaobaoTrade(taobaoTradeList, bizId, obj.get("curUserName"), obj.get("authClient"));
                }
            }
        }
        List<PlatTaobaoTrade> platTaobaoTrade = platTaobaoTradeMapper.selectByTid(obj.get("tid"));

        pageBean.setResult(platTaobaoTrade);
        return pageBean;
    }

    @Transactional
    @Override
    public PageBean<PlatTaobaoTrade> savePushTrade(String tid,String authClient, String response) {
        if (StringUtils.isNotBlank(response)) {
            if (response.contains("trade_fullinfo_get_response")) {
                String trade = JSONObject.parseObject(response).
                      getJSONObject("trade_fullinfo_get_response").getJSONObject("trade").toString();
                List<TaobaoTrade> taobaoTradeList = new ArrayList<TaobaoTrade>();
                taobaoTradeList.add(JSONArray.parseObject(trade, TaobaoTrade.class));
                if (taobaoTradeList.size() > 0) {
                    saveOrUpdatePlatTaobaoTrade(taobaoTradeList, 5, "自动推送", authClient);
                }
            }
        }
        
        List<PlatTaobaoTrade> platTaobaoTrade = platTaobaoTradeMapper.selectByTid(tid);
        PageBean<PlatTaobaoTrade> pageBean=new PageBean<PlatTaobaoTrade>();
        pageBean.setResult(platTaobaoTrade);
        return pageBean;
    }
    
    private void saveOrUpdatePlatTaobaoTrade(List<TaobaoTrade> taobaoTradeList, Integer bizId, String curUserName,
            String authClient) {
        for (TaobaoTrade taobaoTrade : taobaoTradeList) {
            List<PlatTaobaoTrade> platTaobaoTradeRow = platTaobaoTradeMapper.selectByTid(taobaoTrade.getTid());
            if (platTaobaoTradeRow != null && platTaobaoTradeRow.size() > 0) {
                for (PlatTaobaoTrade item : platTaobaoTradeRow) {
                    if (item.getTid().equals(taobaoTrade.getTid())) {
                        // 更新操作
                        updateTaobaoTradeAndOrder(taobaoTrade, item, bizId, curUserName, authClient);
                    }
                }
            } else {
                // 添加操作
                saveTaobaoTradeAndOrder(taobaoTrade, bizId, curUserName, authClient);
            }
        }
    }

    private void saveTaobaoTradeAndOrder(TaobaoTrade taobaoTrade, Integer bizId, String curUserName,
            String authClient) {
        PlatTaobaoTrade ptt = new PlatTaobaoTrade();

        String sellerMemo = taobaoTrade.getSeller_memo() == null ? "" : taobaoTrade.getSeller_memo();

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
        ptt.setSellerMemo(sellerMemo);
        ptt.setAlipayNo(taobaoTrade.getAlipay_no());
        ptt.setBuyerMessage(taobaoTrade.getBuyer_message() == null ? "" : taobaoTrade.getBuyer_message());
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

        // 是否是刷单
        ptt.setIsBrushSingle(getBrushSingle(sellerMemo));

        // 出团日期
        ptt.setDepartureDate(getMemo(sellerMemo, 0));
        // 出发地
        ptt.setDeparturePlace(getMemo(sellerMemo, 1));
        // 人数
        ptt.setReceiveCount(StringUtils.isBlank(getMemo(sellerMemo, 2)) ? "0" : getMemo(sellerMemo, 2));
        // 客服
        ptt.setCustomerService(getCustomerService(sellerMemo));

        // 保存交易信息
        platTaobaoTradeMapper.insertSelective(ptt);

        List<TaobaoTradeOrder> taobaoTradeOrderList = JSONArray.parseArray(
                JSONObject.parseObject(taobaoTrade.getOrders()).getJSONArray("order").toString(),
                TaobaoTradeOrder.class);

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
            platTaobaoTradeOrder.setDivideOrderFee(taobaoTradeOrder.getDivide_order_fee());
            platTaobaoTradeOrder.setDiscountFee(taobaoTradeOrder.getDiscount_fee());
            platTaobaoTradeOrder.setAdjustFee(taobaoTradeOrder.getAdjust_fee());
            platTaobaoTradeOrder.setSkuPropertiesName(taobaoTradeOrder.getSku_properties_name());
            platTaobaoTradeOrder.setRefundStatus(taobaoTradeOrder.getRefund_status());
            platTaobaoTradeOrder
                    .setOuterIid(taobaoTradeOrder.getOuter_iid() == null ? "" : taobaoTradeOrder.getOuter_iid());
            platTaobaoTradeOrder.setRefundId(taobaoTradeOrder.getRefund_id());
            platTaobaoTradeOrder.setSyncTime(new Date());
            platTaobaoTradeOrder.setSyncUsername(curUserName);
            platTaobaoTradeOrder.setMyState("NEW");
            platTaobaoTradeOrder.setMyStoreId(authClient);

            // 保存交易订单信息
            platTaobaoTradeOrderMapper.insert(platTaobaoTradeOrder);
        }
    }

    private void updateTaobaoTradeAndOrder(TaobaoTrade taobaoTrade, PlatTaobaoTrade platTaobaoTrade, Integer bizId,
            String curUserName, String authClient) {
        String sellerMemo = taobaoTrade.getSeller_memo();
        // 更新交易信息
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
        platTaobaoTrade.setSellerMemo(sellerMemo);
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
        // 是否是刷单
        platTaobaoTrade.setIsBrushSingle(getBrushSingle(platTaobaoTrade.getSellerMemo()));

        // 出团日期和订单数
        platTaobaoTrade.setDepartureDate(getMemo(sellerMemo, 0));
        // 出发地
        platTaobaoTrade.setDeparturePlace(getMemo(sellerMemo, 1));
        // 人数
        platTaobaoTrade.setReceiveCount(StringUtils.isBlank(getMemo(sellerMemo, 2)) ? "0"
                : getMemo(sellerMemo, 2));
        // 客服
        platTaobaoTrade.setCustomerService(getCustomerService(sellerMemo));

        platTaobaoTradeMapper.updateByPrimaryKeySelective(platTaobaoTrade);

        // 更新交易订单信息
        List<TaobaoTradeOrder> taobaoTradeOrderList = JSONArray.parseArray(
                JSONObject.parseObject(taobaoTrade.getOrders()).getJSONArray("order").toString(),
                TaobaoTradeOrder.class);

        for (TaobaoTradeOrder taobaoTradeOrder : taobaoTradeOrderList) {
//            // 根据子订单编号获得订单信息
            PlatTaobaoTradeOrder platTaobaoTradeOrder = platTaobaoTradeOrderMapper.
                    selectByTid(platTaobaoTrade.getTid(), taobaoTradeOrder.getOid());
//            // 判断订单是否存在
//            if (platTaobaoTradeOrder == null) { // 新增保存操作
//                platTaobaoTradeOrder.setBizId(bizId);
//                platTaobaoTradeOrder.setTradeId(platTaobaoTrade.getId());
//                platTaobaoTradeOrder.setTid(taobaoTrade.getTid());
//                platTaobaoTradeOrder.setOid(taobaoTradeOrder.getOid());
//                platTaobaoTradeOrder.setStatus(taobaoTradeOrder.getStatus());
//                platTaobaoTradeOrder.setNumIid(taobaoTradeOrder.getNum_iid());
//                platTaobaoTradeOrder.setTitle(taobaoTradeOrder.getTitle());
//                platTaobaoTradeOrder.setPrice(taobaoTradeOrder.getPrice());
//                platTaobaoTradeOrder.setSkuId(taobaoTradeOrder.getSku_id());
//                platTaobaoTradeOrder.setOrderFrom(taobaoTradeOrder.getOrder_from());
//                platTaobaoTradeOrder.setModified(taobaoTradeOrder.getModified());
//                platTaobaoTradeOrder.setEndTime(taobaoTradeOrder.getEnd_time());
//                platTaobaoTradeOrder.setNum(taobaoTradeOrder.getNum());
//                platTaobaoTradeOrder.setTotalFee(taobaoTradeOrder.getTotal_fee());
//                platTaobaoTradeOrder.setPayment(taobaoTradeOrder.getPayment());
//                platTaobaoTradeOrder.setDivideOrderFee(taobaoTradeOrder.getDivide_order_fee());
//                platTaobaoTradeOrder.setDiscountFee(taobaoTradeOrder.getDiscount_fee());
//                platTaobaoTradeOrder.setAdjustFee(taobaoTradeOrder.getAdjust_fee());
//                platTaobaoTradeOrder.setSkuPropertiesName(taobaoTradeOrder.getSku_properties_name());
//                platTaobaoTradeOrder.setRefundStatus(taobaoTradeOrder.getRefund_status());
//                platTaobaoTradeOrder
//                        .setOuterIid(taobaoTradeOrder.getOuter_iid() == null ? "" : taobaoTradeOrder.getOuter_iid());
//                platTaobaoTradeOrder.setRefundId(taobaoTradeOrder.getRefund_id());
//                platTaobaoTradeOrder.setSyncTime(new Date());
//                platTaobaoTradeOrder.setSyncUsername(curUserName);
//                platTaobaoTradeOrder.setMyState("NEW");
//                platTaobaoTradeOrder.setMyStoreId(authClient);
//
//                // 保存交易订单信息
//                platTaobaoTradeOrderMapper.insert(platTaobaoTradeOrder);
//            } else { // 更新
                // 验证修改时间是否更改
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
                platTaobaoTradeOrder.setDivideOrderFee(taobaoTradeOrder.getDivide_order_fee());
                platTaobaoTradeOrder.setDiscountFee(taobaoTradeOrder.getDiscount_fee());
                platTaobaoTradeOrder.setAdjustFee(taobaoTradeOrder.getAdjust_fee());
                platTaobaoTradeOrder.setSkuPropertiesName(taobaoTradeOrder.getSku_properties_name());
                platTaobaoTradeOrder.setRefundStatus(taobaoTradeOrder.getRefund_status());
                platTaobaoTradeOrder.setOuterIid(taobaoTradeOrder.getOuter_iid());
                platTaobaoTradeOrder.setRefundId(taobaoTradeOrder.getRefund_id());

                // 保存交易订单信息
                platTaobaoTradeOrderMapper.updateByPrimaryKeySelective(platTaobaoTradeOrder);
//            }
        }
    }

    /**
     * 通过备注获取出团日期、出发地、人数.
     *
     * @param sellerMemo
     *            卖家备注 格式为：(出发日期,出发地,人数) (2016-10-01,杭州,2)
     * @param index
     *            返回值 0 出团日期 1出发地 2人数
     * @return 出团日期、出发地、人数
     */
    private String getMemo(String sellerMemo, Integer index) {
        if (StringUtils.isNotBlank(sellerMemo)) {
        	if (sellerMemo.indexOf("(") > -1 && sellerMemo.substring(sellerMemo.indexOf("(") + 1).indexOf(")") > -1) {
                String[] memo = sellerMemo.substring(sellerMemo.indexOf("(") + 1, sellerMemo.indexOf(")")).split(",");
                if (memo.length > index && memo.length > 2) {
                    return memo[index];
                }
            }
        }
        return "";
    }

    /**
     * 通过备注获取是否是刷单. 格式为：{000}
     *
     * @param sellerMemo
     *            卖家备注
     * @return 0 否 1 是
     */
    private Integer getBrushSingle(String sellerMemo) {
        if (StringUtils.isNotBlank(sellerMemo)) {
            if (StringUtils.contains(sellerMemo, "{000}")) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 通过备注获取客服名字. 格式为：{名字}
     * 
     * @param sellerMemo
     *            卖家备注
     * @return 名字
     */
    private String getCustomerService(String sellerMemo) {
        if (StringUtils.isNotBlank(sellerMemo) && !StringUtils.contains(sellerMemo, "{000}")) {
            int customerServiceBegin = sellerMemo.indexOf("{");
            int customerServiceEnd = sellerMemo.indexOf("}");
            if (customerServiceBegin != -1 && customerServiceEnd != -1 && customerServiceEnd > customerServiceBegin) {
                return sellerMemo.substring(customerServiceBegin + 1, customerServiceEnd);
            }
        }
        return "";
    }

    @Override
    public PageBean<PlatTaobaoTrade> selectTaobaoOrderImport(PageBean<PlatTaobaoTrade> pageBean, Integer bizId) {
        List<PlatTaobaoTrade> result = platTaobaoTradeMapper.selectTaobaoOrderListPage(pageBean, bizId);
        pageBean.setResult(result);
        return pageBean;
    }

    @Override
    public PageBean<PlatTaobaoTrade> selectPresellProductStatisticsListPage(PageBean<PlatTaobaoTrade> pageBean,
            Integer bizId) {
        List<PlatTaobaoTrade> result = platTaobaoTradeMapper.selectPresellProductStatisticsListPage(pageBean, bizId);
        pageBean.setResult(result);
        return pageBean;
    }

    @Override
    public PageBean<PlatTaobaoTrade> selectPresellTaobaoOrderListPage(PageBean<PlatTaobaoTrade> pageBean,
            Integer bizId) {
        List<PlatTaobaoTrade> result = platTaobaoTradeMapper.selectPresellTaobaoOrderListPage(pageBean, bizId);
        pageBean.setResult(result);
        return pageBean;
    }
    
    @Override
    public PageBean<PlatTaobaoTrade> selectSaleOperatorSalesStatisticsListPage(PageBean<PlatTaobaoTrade> pageBean,
            Integer bizId) {
        List<PlatTaobaoTrade> result = platTaobaoTradeMapper.selectSaleOperatorSalesStatisticsListPage(pageBean, bizId);
        pageBean.setResult(result);
        return pageBean;
    }
    
    @Override
    public PageBean<PlatTaobaoTrade> selectNotPresellProductStatisticsListPage(PageBean<PlatTaobaoTrade> pageBean,
            Integer bizId) {
        List<PlatTaobaoTrade> result = platTaobaoTradeMapper.selectNotPresellProductStatisticsListPage(pageBean, bizId);
        pageBean.setResult(result);
        return pageBean;
    }

    @Override
    public PlatTaobaoTrade selectTaobaoshopSalesStatistics(PlatTaobaoTrade platTaobaoTrade, Integer bizId) {
        PlatTaobaoTrade ptt = platTaobaoTradeMapper.selectTaobaoshopSalesStatistics(platTaobaoTrade, bizId);
        PlatTaobaoTrade ppt = platTaobaoTradeMapper.selectTaobaoshopSalesStatisticsOther(platTaobaoTrade, bizId);
        Integer orders=ptt.getOrders();
        Integer buyers=ptt.getBuyers();
        Integer TotalFee=ptt.getTotalFee();
        Double payment=Double.parseDouble(ptt.getPayment())+Double.parseDouble(ppt.getPayment());
        ptt.setOrders(orders+ppt.getOrders());
        ptt.setBuyers(buyers+ppt.getBuyers());
        ptt.setPayment(payment.toString());
        ptt.setTotalFee(TotalFee+ppt.getTotalFee());
        return ptt;
    }

    @Override
    public List<PlatTaobaoTrade> selectTaobaoOrderById(String ids) {
        return platTaobaoTradeMapper.selectTaobaoOrderById(ids);
    }

    @Override
    public void updateTaobaoOrderId(Integer orderId, String ids) {
        platTaobaoTradeMapper.updateTaobaoOrderId(orderId, ids); // ids=
                                                                 // platTaobaoTradeOrderId
        // platTaobaoTradeMapper.updateTaobaoOrderIdToOrder(orderId, ids);
    }

    @Override
    public List<PlatTaobaoTrade> selectTaobaoOrderByOrderId(Integer id) {
        return platTaobaoTradeMapper.selectTaobaoOrderByOrderId(id);
    }

    @Override
    public void updateTaobaoOrderIdToZero(String id) {
        platTaobaoTradeMapper.updateTaobaoOrderIdToZero(id); // id=
                                                             // platTaobaoTradeOrderId
        // platTaobaoTradeMapper.updateTaobaoOrderIdToZeroToOrder(id);
    }

    @Override
    public void updateCANCEL(String idss) {
        platTaobaoTradeMapper.updateCANCEL(idss);
    }

    @Override
    public void updateNEW(String idss) {
        platTaobaoTradeMapper.updateNEW(idss);
    }}
