package com.yimayhd.erpcenter.facade.supplier.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.facade.supplier.errorcode.SupplierErrorCode;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierDriverDTO;
import com.yimayhd.erpcenter.facade.supplier.result.DriverEditResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;
import com.yimayhd.erpcenter.facade.supplier.service.SupplierDriverFacade;
import com.yimayhd.erpresource.biz.service.SupplierDriverBiz;
import com.yimayhd.erpresource.dal.po.SupplierDriver;


public class SupplierDriverFacadeImpl implements SupplierDriverFacade {
	private static final Logger log = LoggerFactory
			.getLogger(SupplierDriverFacadeImpl.class);

	@Autowired
	private RegionBiz regionBiz;
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private SupplierDriverBiz supplierDriverBiz;

	@Override
	public WebResult<List<RegionInfo>> driverList() {
		WebResult<List<RegionInfo>> webResult = new WebResult<List<RegionInfo>>();
		try{
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setValue(allProvince);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
	}

	@Override
	public DriverEditResult driverEdit(Integer id) {
		
		DriverEditResult webResult = new DriverEditResult();
		try{
			List<DicInfo> mzList = dicBiz.getListByTypeCode(BasicConstants.GYXX_MZ);
			List<DicInfo> djList = dicBiz.getListByTypeCode(BasicConstants.DYXX_DJ);
			List<DicInfo> xjpdList = dicBiz.getListByTypeCode(BasicConstants.DYXX_XJPD);
			List<DicInfo> shdtrsList = dicBiz.getListByTypeCode(BasicConstants.DYXX_SHDTRS);
			List<DicInfo> carList = dicBiz.getListByTypeCode(BasicConstants.GYS_SJ_ZJCX);
			webResult.setMzList(mzList);
			webResult.setDjList(djList);
			webResult.setXjpdList(xjpdList);
			webResult.setShdtrsList(shdtrsList);
			webResult.setCarList(carList);

			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			SupplierDriver driver = null;
			driver = supplierDriverBiz.getDriverInfoById(id);
			webResult.setDriver(driver);
			if (driver != null) {
				if (driver.getProvinceId() != null) {
					List<RegionInfo> cityList = regionBiz.getRegionById(driver.getProvinceId() + "");
					webResult.setCityList(cityList);
				}
				if (driver.getCityId() != null) {
					List<RegionInfo> areaList = regionBiz.getRegionById(driver.getCityId() + "");
					webResult.setAreaList(areaList);
				}
				if (driver.getAreaId() != null) {
					List<RegionInfo> townList = regionBiz.getRegionById(driver.getAreaId() + "");
					webResult.setTownList(townList);
				}
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;

		
	}
	@Override
	public DriverEditResult driverDetail(Integer id) {
		
		DriverEditResult webResult = new DriverEditResult();
		try{
			List<DicInfo> mzList = dicBiz.getListByTypeCode(BasicConstants.GYXX_MZ);
			List<DicInfo> djList = dicBiz.getListByTypeCode(BasicConstants.DYXX_DJ);
			List<DicInfo> xjpdList = dicBiz.getListByTypeCode(BasicConstants.DYXX_XJPD);
			List<DicInfo> shdtrsList = dicBiz.getListByTypeCode(BasicConstants.DYXX_SHDTRS);
			List<DicInfo> carList = dicBiz.getListByTypeCode(BasicConstants.GYS_SJ_ZJCX);
			webResult.setMzList(mzList);
			webResult.setDjList(djList);
			webResult.setXjpdList(xjpdList);
			webResult.setShdtrsList(shdtrsList);
			webResult.setCarList(carList);

			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			SupplierDriver driver = null;
			driver = supplierDriverBiz.getDriverInfoById(id);
			webResult.setDriver(driver);
			if (driver != null) {
				if (driver.getProvinceId() != null) {
					List<RegionInfo> cityList = regionBiz.getRegionById(driver.getProvinceId() + "");
					webResult.setCityList(cityList);
				}
				if (driver.getCityId() != null) {
					List<RegionInfo> areaList = regionBiz.getRegionById(driver.getCityId() + "");
					webResult.setAreaList(areaList);
				}
				if (driver.getAreaId() != null) {
					List<RegionInfo> townList = regionBiz.getRegionById(driver.getAreaId() + "");
					webResult.setTownList(townList);
				}
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;

		
	}
	
	@Override
	public WebResult<Map<String,Object>> driverSave(SupplierDriverDTO driverDTO,Integer bizId) {
		
		WebResult<Map<String,Object>> webResult = new WebResult<Map<String,Object>>();
		try{
			Map<String,Object> map = new HashMap<String, Object>();
			int id = 0;
			boolean isAdd = false;
			SupplierDriver  driver=driverDTO.getSupplierDriver();
			if (driver.getId() != null) {
				supplierDriverBiz.updateDriverInfo(driver);
			} else {
				id = supplierDriverBiz.addDriverInfoAndRelation(driver, bizId);
				isAdd = true;
			}
			map.put("id", id);
			map.put("isAdd", isAdd);
			webResult.setValue(map);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
	
	}

	@Override
	public WebResult<Boolean> driverDelete(Integer id) {
		
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierDriverBiz.deleteByPrimaryKey(id);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> joinDriver(Integer bizId,String ids) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierDriverBiz.addBizDriverRelation(bizId, null, StringUtils.split(ids, ','));
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> cancelJoinDriver(Integer bizId,Integer driverId) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierDriverBiz.deleteBizDriverRelation(bizId, driverId);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}
}
