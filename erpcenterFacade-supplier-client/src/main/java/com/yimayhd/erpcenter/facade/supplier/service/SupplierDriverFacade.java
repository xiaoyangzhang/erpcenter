package com.yimayhd.erpcenter.facade.supplier.service;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierDriverDTO;
import com.yimayhd.erpcenter.facade.supplier.result.DriverEditResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;

/**
 * 司机facade
 * 里面的方法和对应的Controller一一对应
 * @author liyong
 * 2016年10月26日 
 */
public interface SupplierDriverFacade  {

	/**
	 * 
	 * @author liyong
	 * 2016年10月25日 描述：
	 * @return
	 */
	public WebResult<List<RegionInfo>> driverList();

	public DriverEditResult driverEdit( Integer id);
	
	public DriverEditResult driverDetail(Integer id);
	
	public WebResult<Map<String,Object>> driverSave(SupplierDriverDTO driverDTO,Integer bizId);

	public WebResult<Boolean> driverDelete(Integer id);

	public WebResult<Boolean> joinDriver(Integer bizId,String ids) ;

	public WebResult<Boolean> cancelJoinDriver(Integer bizId,Integer driverId);
}
