package com.yimayhd.erpcenter.biz.sales.service.impl.b2b;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.sales.client.service.b2b.B2BGroupOrderBiz;
import com.yimayhd.erpcenter.dal.sales.client.b2b.service.B2BGroupOrderDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;

/**
 * Created by zm on 2016/6/17.
 */
public class B2BGroupOrderBizImpl implements B2BGroupOrderBiz {

	@Autowired
	private B2BGroupOrderDal b2BGroupOrderDal;

	@Override
	public List<GroupOrder> findGroupOrder(Integer bizId, String dateStart, String dateEnd, String clientName,Integer exports) {
		return b2BGroupOrderDal.findGroupOrder(bizId, dateStart, dateEnd, clientName, exports);
	}

	@Override
	public boolean updateB2bExportState(String ids) {
		return b2BGroupOrderDal.updateB2bExportState(ids);
	}

	@Override
	public List<GroupOrder> findGroupOrderDetailByIds(String ids) {
		return b2BGroupOrderDal.findGroupOrderDetailByIds(ids);
	}

	@Override
	public List<GroupOrderGuest> findGroupOrderGuestByOrderId(Integer orderId) {
		return b2BGroupOrderDal.findGroupOrderGuestByOrderId(orderId);
	}

	@Override
	public List<GroupOrderPrice> findGroupOrderPriceByOrderId(Integer orderId) {
		return b2BGroupOrderDal.findGroupOrderPriceByOrderId(orderId);
	}

	@Override
	public List<GroupRoute> findGroupRouteByOrderId(Integer orderId) {
		return b2BGroupOrderDal.findGroupRouteByOrderId(orderId);
	}
}