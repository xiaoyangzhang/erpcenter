/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.service;

import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.facade.sales.query.BookingDeliveryQueryDTO;
import com.yimayhd.erpcenter.facade.sales.query.ContractQueryDTO;
import com.yimayhd.erpcenter.facade.sales.result.operation.BookingSupplierResult;
import com.yimayhd.erpcenter.facade.sales.result.operation.TourGroupInfoResult;
import com.yimayhd.erpresource.dal.po.SupplierContractPrice;
import com.yimayhd.erpresource.dal.po.SupplierContractPriceDateInfo;

/**
 * @ClassName: BookingComponentFacade
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public interface BookingComponentFacade {

	List<GroupOrderGuest> selectGuestByOrderId(Integer orderId);
	
	List<GroupOrderTransport> selectTransportByOrderId(Integer orderId);
	
	TourGroupInfoResult getTourGroupInfo(Integer groupId,Integer type);
	
	List<SupplierContractPriceDateInfo> getOperateContractPrice(ContractQueryDTO queryDTO);
	
	List<SupplierContractPriceDateInfo> getCarContractPrice(ContractQueryDTO queryDTO);
	
	String getContractPriceJson(ContractQueryDTO queryDTO);
	
	List<SupplierContractPriceDateInfo> getContractPriceByPramas(ContractQueryDTO queryDTO);
	
	BookingSupplierResult selectPrivateCarListPage(PageBean pageBean,Integer bizId);
	
	PageBean deliveryContract(BookingDeliveryQueryDTO queryDTO);
	
	List<SupplierContractPrice> getContractPriceListByContractId(Integer contractId);
}
