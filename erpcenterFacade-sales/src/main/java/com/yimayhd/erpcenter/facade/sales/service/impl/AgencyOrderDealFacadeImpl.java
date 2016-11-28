package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductStockBiz;
import com.yimayhd.erpcenter.biz.product.service.TrafficResBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.FitOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.dal.product.constans.Constants.TRAFFICRES_STOCK_ACTION;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.TrafficResStocklog;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;
import com.yimayhd.erpcenter.facade.sales.service.AgencyOrderDealFacade;

public class AgencyOrderDealFacadeImpl implements AgencyOrderDealFacade {

	private static final Logger log = LoggerFactory.getLogger(AgencyOrderDealFacadeImpl.class);
	@Autowired
	private FitOrderBiz fitOrderBiz;
	@Autowired
	private ProductInfoBiz productInfoBiz;
	@Autowired
	private ProductStockBiz productStockBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private TrafficResBiz trafficResBiz;
	@Override
	public ResultSupport execute() {
		ResultSupport resultSupport = new ResultSupport();
		long startTime = System.currentTimeMillis();
		List<GroupOrder> orderList = fitOrderBiz.selectReserveOrderList();

		if (!CollectionUtils.isEmpty(orderList)) {

			try {
				for (GroupOrder groupOrder : orderList) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					ProductInfo productInfo = productInfoBiz.findProductInfoById(groupOrder.getProductId());

					if (productInfo.getObligateHour() != null) {
						long ObligatMin = productInfo.getObligateHour() * 60 * 60 * 1000;
						if (groupOrder.getCreateTime() + ObligatMin < System.currentTimeMillis()) {
							log.info("发现可删除的订单,开始执行....");
							productStockBiz.updateReserveCount(groupOrder.getProductId(),
									sdf.parse(groupOrder.getDepartureDate()),
									-(groupOrder.getNumAdult() + groupOrder.getNumChild()));
							groupOrderBiz.delGroupOrder(groupOrder.getId());
							continue;
						}

					}
					if (productInfo.getObligateCount() != null) {
						int stock = productStockBiz.getRestCountByProductIdAndDate(groupOrder.getProductId(),
								sdf.parse(groupOrder.getDepartureDate()));
						if (stock < productInfo.getObligateCount()) {
							log.info("发现可删除的订单,开始执行....");
							productStockBiz.updateReserveCount(groupOrder.getProductId(),
									sdf.parse(groupOrder.getDepartureDate()),
									-(groupOrder.getNumAdult() + groupOrder.getNumChild()));
							groupOrderBiz.delGroupOrder(groupOrder.getId());

						}
					}

				}
			} catch (ParseException e) {
				log.error("error:{}",e);
			}
		}
		return resultSupport;
	}

	@Override
	public ResultSupport executeUpdateResOrderState(Integer orderState) {
		ResultSupport resultSupport = new ResultSupport();
		List<GroupOrder> orderList = groupOrderBiz.selectOrderOverTime();
		if (null != orderList && orderList.size() > 0) {
			for (GroupOrder groupOrder : orderList) {
				groupOrder.setExtResState(3);
				groupOrder.setState(orderState);
//				groupOrder.setExtResConfirmId(null);
//				groupOrder.setExtResConfirmName("");
				groupOrderBiz.loadUpdateExtResState(groupOrder);
				
				//修改 traffic_res_stocklog 预留订单状态为已确认
				TrafficResStocklog updateStockLog=new TrafficResStocklog();
				updateStockLog.setAdjustState(2);
				updateStockLog.setResId(groupOrder.getExtResId());
				updateStockLog.setOrderId(groupOrder.getId());
				trafficResBiz.updateStockLog_AdjustState(updateStockLog);
				
				
				trafficResBiz.updateStockOrStockDisable(groupOrder.getExtResId());
			}
		}
		return null;
	}

	
}
