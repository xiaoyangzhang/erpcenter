package com.yimayhd.erpcenter.dal.sales.b2b.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.sales.client.b2b.service.B2BGroupOrderDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderGuestMapper;
import com.yimayhd.erpcenter.dal.sales.sales.dao.GroupOrderMapper;

/**
 * Created by zm on 2016/6/17.
 */
public class B2BGroupOrderDalImpl implements B2BGroupOrderDal {

	@Autowired
	private GroupOrderMapper groupOrderMapper;
	@Autowired
	private GroupOrderGuestMapper groupOrderGuestMapper;
	@Autowired
	private GroupOrderPriceMapper groupOrderPriceMapper;
	@Autowired
	private GroupRouteMapper groupRouteMapper;

	@Override
	public List<GroupOrder> findGroupOrder(Integer bizId, String dateStart, String dateEnd, String clientName,Integer exports) {
		return groupOrderMapper.selectGroupOrderListToB2B(bizId, dateStart, dateEnd, clientName,exports);
	}

	@Override
	public boolean updateB2bExportState(String ids) {
		return groupOrderMapper.updateB2bExportState(ids);
	}

	@Override
	public List<GroupOrder> findGroupOrderDetailByIds(String ids) {
		return groupOrderMapper.selectGroupOrderDetailByIds(ids);
	}

	@Override
	public List<GroupOrderGuest> findGroupOrderGuestByOrderId(Integer orderId) {
		return groupOrderGuestMapper.selectByOrderId(orderId);
	}

	@Override
	public List<GroupOrderPrice> findGroupOrderPriceByOrderId(Integer orderId) {
		return groupOrderPriceMapper.getPriceInfoByOrderId(orderId);
	}

	@Override
	public List<GroupRoute> findGroupRouteByOrderId(Integer orderId) {
		return groupRouteMapper.selectByOrderId(orderId);
	}
}