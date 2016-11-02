/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRemarkBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductRouteBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.b2b.B2BGroupOrderBiz;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductRemark;
import com.yimayhd.erpcenter.dal.product.po.ProductRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.facade.sales.service.B2BFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.dal.po.SupplierContactMan;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

/**
 * @ClassName: BookingGuideFacadeImpl
 * @author hongfei.guo
 * @date 2016年10月27日
 */
public class B2BFacadeImpl implements B2BFacade {

	@Autowired
	private B2BGroupOrderBiz b2BGroupOrderBiz;
	
	@Autowired
	private ProductInfoBiz productInfoBiz;

	@Autowired
	private ProductRouteBiz productRouteBiz;
	
	@Autowired
	private ProductRemarkBiz productRemarkBiz;
	
	@Autowired
	private SupplierBiz supplierBiz;
	
	@Override
	public List<GroupOrder> findGroupOrder(Integer bizId, String dateStart,
			String dateEnd, String clientName, Integer exports) {
		List<GroupOrder> list = b2BGroupOrderBiz.findGroupOrder(bizId, dateStart, dateEnd, clientName, exports);
		return list;
	}

	@Override
	public boolean updateB2bExportState(String ids) {
		
		// 更新多个导出状态
		boolean bl = b2BGroupOrderBiz.updateB2bExportState(ids);
		return bl;
	}

	@Override
	public JSONArray findGroupOrderDetailByIdsToB2B(String ids) {
		List<GroupOrder> groupOrderList = b2BGroupOrderBiz.findGroupOrderDetailByIds(ids);

		JSONArray jsonarray = new JSONArray();

		for (GroupOrder groupOrders : groupOrderList) {
			GroupOrder groupOrder;
			groupOrder = groupOrders;

			List<GroupOrderGuest> groupOrderGuests = b2BGroupOrderBiz.findGroupOrderGuestByOrderId(groupOrders.getId());
			groupOrder.setGroupOrderGuestList(groupOrderGuests);

			List<GroupOrderPrice> groupOrderPrices = b2BGroupOrderBiz.findGroupOrderPriceByOrderId(groupOrders.getId());
			groupOrder.setOrderPrices(groupOrderPrices);

			List<GroupRoute> groupRoutes = b2BGroupOrderBiz.findGroupRouteByOrderId(groupOrders.getId());
			groupOrder.setGroupRouteList(groupRoutes);
			jsonarray.add(groupOrder);
		}
		return jsonarray;

	}

	@Override
	public List<ProductInfo> findProductInfoDetailByProductIdToB2B(String productIds) {
		
		String[] proIds = productIds.split(",");

		List<ProductInfo> list = new ArrayList<ProductInfo>();
		for (String productId : proIds) {
			ProductInfo productInfo;
			productInfo = productInfoBiz.findProductInfoById(Integer.parseInt(productId));

			List<ProductRoute> productRouteList =
					productRouteBiz.findProductRouteByProductId(Integer.parseInt(productId));
			productInfo.setProductRouteList(productRouteList);

			ProductRemark productRemark =
					productRemarkBiz.findProductRemarkByProductId(Integer.parseInt(productId));
			productInfo.setProductRemark(productRemark);
			list.add(productInfo);
		}

		return list;
	}

	@Override
	public JSONArray findSupplierInfoListToB2B(Integer bizId, Integer supplierType, String supplierName) {
		if (supplierType == null) {
			supplierType = 1;
		}

		List<SupplierInfo> supplierInfoList = supplierBiz.findSupplierInfoListToB2B(bizId, supplierType, supplierName);
		JSONArray jsonarray = new JSONArray();
		for (SupplierInfo supplierInfos : supplierInfoList) {

			List<SupplierContactMan> supplierContactManList = supplierBiz.findSupplierContactManListBySupplierId(supplierInfos.getId());
			supplierInfos.setSupplierContactManList(supplierContactManList);

			jsonarray.add(supplierInfos);
		}
		return jsonarray;
	}
}
