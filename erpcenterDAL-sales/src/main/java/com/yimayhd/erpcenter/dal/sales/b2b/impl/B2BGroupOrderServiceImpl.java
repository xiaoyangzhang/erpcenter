package com.yimayhd.erpcenter.dal.sales.b2b.impl;

import com.yihg.b2b.api.B2BGroupOrderService;
import com.yihg.sales.dao.GroupOrderGuestMapper;
import com.yihg.sales.dao.GroupOrderMapper;
import com.yihg.sales.dao.GroupOrderPriceMapper;
import com.yihg.sales.dao.GroupRouteMapper;
import com.yihg.sales.po.GroupOrder;
import com.yihg.sales.po.GroupOrderGuest;
import com.yihg.sales.po.GroupOrderPrice;
import com.yihg.sales.po.GroupRoute;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zm on 2016/6/17.
 */
public class B2BGroupOrderServiceImpl implements B2BGroupOrderService {

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