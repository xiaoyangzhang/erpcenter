package com.yimayhd.erpcenter.facade.supplier.service.impl;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductInfoBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizInfoBiz;
import com.yimayhd.erpcenter.dal.basic.constant.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.facade.supplier.errorcode.SupplierErrorCode;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierBankaccountDTO;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierBillDTO;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierContactManDTO;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierGuideDTO;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierInfoDTO;
import com.yimayhd.erpcenter.facade.supplier.result.AddSupplierResult;
import com.yimayhd.erpcenter.facade.supplier.result.BusinessInfoResult;
import com.yimayhd.erpcenter.facade.supplier.result.ContactManListResult;
import com.yimayhd.erpcenter.facade.supplier.result.EditSupplierResult;
import com.yimayhd.erpcenter.facade.supplier.result.FolderListResult;
import com.yimayhd.erpcenter.facade.supplier.result.GuideAddResult;
import com.yimayhd.erpcenter.facade.supplier.result.GuideListResult;
import com.yimayhd.erpcenter.facade.supplier.result.PictureListResult;
import com.yimayhd.erpcenter.facade.supplier.result.SuplierListResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;
import com.yimayhd.erpcenter.facade.supplier.service.SupplierFacade;
import com.yimayhd.erpresource.biz.service.BizSupplierRelationBiz;
import com.yimayhd.erpresource.biz.service.ContractBiz;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.biz.service.SupplierDriverBiz;
import com.yimayhd.erpresource.biz.service.SupplierGuideBiz;
import com.yimayhd.erpresource.biz.service.SupplierImgBiz;
import com.yimayhd.erpresource.biz.service.SupplierItemBiz;
import com.yimayhd.erpresource.dal.constants.Constants;
import com.yimayhd.erpresource.dal.po.BizGuideRelation;
import com.yimayhd.erpresource.dal.po.BizSupplierRelation;
import com.yimayhd.erpresource.dal.po.SupplierBankaccount;
import com.yimayhd.erpresource.dal.po.SupplierBill;
import com.yimayhd.erpresource.dal.po.SupplierContactMan;
import com.yimayhd.erpresource.dal.po.SupplierContract;
import com.yimayhd.erpresource.dal.po.SupplierGuide;
import com.yimayhd.erpresource.dal.po.SupplierImg;
import com.yimayhd.erpresource.dal.po.SupplierImgType;
import com.yimayhd.erpresource.dal.po.SupplierInfo;
import com.yimayhd.erpresource.dal.po.SupplierItem;
import com.yimayhd.erpresource.dal.vo.SupplierVO;

public class SupplierFacadeImpl implements SupplierFacade {
	private static final Logger log = LoggerFactory
			.getLogger(SupplierFacadeImpl.class);
	@Autowired
	private BizSupplierRelationBiz bizSupplierRelationBiz;
	@Autowired
	private SupplierBiz supplierBiz;
	@Autowired
	private RegionBiz regionBiz;
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private SupplierGuideBiz guideBiz;
	@Autowired
	private SupplierDriverBiz driverBiz;
	@Autowired
	private SupplierImgBiz supplierImgBiz;
	@Autowired
	private ContractBiz contractBiz;

	@Autowired
	private SysBizInfoBiz bizInfoBiz;

	@Autowired
	private SupplierItemBiz supplierItemBiz;

	@Autowired
	private GroupOrderBiz groupOrderBiz;
	
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;
	
	@Autowired
	private BookingShopBiz bookingShopBiz;
	
	@Autowired
	private ProductInfoBiz productBiz;
	
	@Override
	public WebResult<Boolean> deleteSupplierItem(Integer id) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierItemBiz.deleteByPrimaryKey(id);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
	}


	@Override
	public AddSupplierResult toAddSupplier() {
		
		AddSupplierResult webResult = new AddSupplierResult();
		try{
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			List<DicInfo> travelagencylevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_TRAVELAGENCY);
			webResult.setTravelagencylevelList(travelagencylevelList);
			List<DicInfo> restaurantlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_RESTAURANT);
			webResult.setRestaurantlevelList(restaurantlevelList);
			List<DicInfo> levelList = dicBiz
					.getListByTypeCode(BasicConstants.GYXX_JDXJ);
			webResult.setLevelList(levelList);
			List<DicInfo> fleetlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_FLEET);
			webResult.setFleetlevelList(fleetlevelList);
			List<DicInfo> scenicspotlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_SCENICSPOT);
			webResult.setScenicspotlevelList(scenicspotlevelList);
			List<DicInfo> shoppinglevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_SHOPPING);
			webResult.setShoppinglevelList(shoppinglevelList);

			List<DicInfo> entertainmentlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_ENTERTAINMENT);
			webResult.setEntertainmentlevelList(entertainmentlevelList);
			List<DicInfo> guidelevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_GUIDE);
			webResult.setGuidelevelList(guidelevelList);
			List<DicInfo> airticketagentlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_AIRTICKETAGENT);
			webResult.setAirticketagentlevelList(airticketagentlevelList);
			List<DicInfo> trainticketagentlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_TRAINTICKETAGENT);
			webResult.setTrainticketagentlevelList(trainticketagentlevelList);
			List<DicInfo> golflevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_GOLF);
			webResult.setGolflevelList(golflevelList);
			
			List<DicInfo> insuranclevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_INSURANCE);
			webResult.setInsuranclevelList(insuranclevelList);
			
			List<DicInfo> otherlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_OTHER);
			webResult.setOtherlevelList(otherlevelList);
			
			List<DicInfo> localtravelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_LOCALTRAVEL);
			webResult.setLocaltravelList(localtravelList);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
	}

	/**
	 * 跳转到供应商编辑页面
	 *
	 * @param request
	 * @param reponse
	 * @param model
	 * @param id
	 * @return
	 */
	@Override
	public EditSupplierResult toEditSupplier(Integer id,Integer operType) {
		
		EditSupplierResult webResult = new EditSupplierResult();
		try{
			SupplierInfo supplierInfo = supplierBiz.selectBySupplierId(id);
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			List<RegionInfo> cityList = regionBiz.getRegionById(supplierInfo
					.getProvinceId() + "");
			webResult.setCityList(cityList);
			List<RegionInfo> areaList = regionBiz.getRegionById(supplierInfo
					.getCityId() + "");
			webResult.setAreaList(areaList);
			List<RegionInfo> townList = regionBiz.getRegionById(supplierInfo
					.getAreaId() + "");
			webResult.setTownList(townList);
			webResult.setSupplierInfo(supplierInfo);
			List<DicInfo> travelagencylevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_TRAVELAGENCY);
			webResult.setTravelagencylevelList(travelagencylevelList);
			List<DicInfo> restaurantlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_RESTAURANT);
			webResult.setRestaurantlevelList(restaurantlevelList);
			List<DicInfo> levelList = dicBiz
					.getListByTypeCode(BasicConstants.GYXX_JDXJ);
			webResult.setLevelList(levelList);
			List<DicInfo> fleetlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_FLEET);
			webResult.setFleetlevelList(fleetlevelList);
			List<DicInfo> scenicspotlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_SCENICSPOT);
			webResult.setScenicspotlevelList(scenicspotlevelList);
			List<DicInfo> shoppinglevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_SHOPPING);
			webResult.setShoppinglevelList(shoppinglevelList);

			List<DicInfo> entertainmentlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_ENTERTAINMENT);
			webResult.setEntertainmentlevelList(entertainmentlevelList);
			List<DicInfo> guidelevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_GUIDE);
			webResult.setGuidelevelList(guidelevelList);
			List<DicInfo> airticketagentlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_AIRTICKETAGENT);
			webResult.setAirticketagentlevelList(airticketagentlevelList);
			List<DicInfo> trainticketagentlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_TRAINTICKETAGENT);
			webResult.setTrainticketagentlevelList(trainticketagentlevelList);
			List<DicInfo> golflevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_GOLF);
			webResult.setGolflevelList(golflevelList);
			
			List<DicInfo> insuranclevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_INSURANCE);
			webResult.setInsuranclevelList(insuranclevelList);
			
			List<DicInfo> otherlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_OTHER);
			webResult.setOtherlevelList(otherlevelList);
			
			List<DicInfo> localtravelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_LOCALTRAVEL);
			webResult.setLocaltravelList(localtravelList);
			
			List<SupplierItem> supplierItems = supplierItemBiz
					.findSupplierItemBySupplierId(id);
			webResult.setSupplierItems(supplierItems);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}

	/**
	 * 验证当前供应商类别下全称是否存在
	 *
	 * @param request
	 * @param reponse
	 * @param supplierId
	 * @param mobile
	 * @return
	 */
	@Override
	public WebResult<String> verifyNameFull(Integer supplierId,
			Integer supplierType, String nameFull) {
		
		WebResult<String> webResult = new WebResult<String>();
		try{
			int verifyNameFull = supplierBiz.verifyNameFull(supplierId,
					supplierType, nameFull);
			if (verifyNameFull > 0) {
				webResult.setValue("false");
			}else{
				webResult.setValue("true");
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
	}

	/**
	 * 添加供应商
	 *
	 * @param request
	 * @param reponse
	 * @param model
	 * @param supplierInfo
	 * @return
	 */
	@Override
	public WebResult<String> saveRest(SupplierInfoDTO supplierInfoDTO, String items,Integer bizId) {
		
		WebResult<String> webResult = new WebResult<String>();
		try{
			Integer id;
			SupplierInfo supplierInfo = supplierInfoDTO.getSupplierInfo();
			supplierInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
			if (supplierInfo.getProvinceId() != null) {
				supplierInfo.setProvinceName(regionBiz.getById(
						supplierInfo.getProvinceId() + "").getName());
			}
			if (supplierInfo.getCityId() != null) {
				supplierInfo.setCityName(regionBiz.getById(
						supplierInfo.getCityId() + "").getName());
			}
			if (supplierInfo.getAreaId() != null) {
				supplierInfo.setAreaName(regionBiz.getById(
						supplierInfo.getAreaId() + "").getName());
			}
			if (supplierInfo.getTownId() != null) {
				supplierInfo.setTownName(regionBiz.getById(
						supplierInfo.getTownId() + "").getName());
			}

			try {

				// 1、根据supplier_type获取文件夹对应字典类型编码

				// 2、根据文件夹字典类型编码获取字典
				Map<Integer, String> map = Constants.dictType3Map;

				List<DicInfo> dicList = new ArrayList<DicInfo>();
				for (Map.Entry<Integer, String> entry : map.entrySet()) {
					Integer key = entry.getKey();
					if (key == supplierInfo.getSupplierType()) {

						dicList = dicBiz.getListByTypeCode(entry.getValue());
					}
				}
				// 从字典获取业务类型下的文件夹类型
				List<DicInfo> busType = dicBiz
						.getListByTypeCode(Constants.SUPPLIER_IMG_TYPE_BUSSINESS);
				List<SupplierImgType> imgTypeList = new ArrayList<SupplierImgType>();

				if (dicList != null && dicList.size() > 0) {
					for (DicInfo dicInfo : dicList) {
						SupplierImgType imgType = new SupplierImgType();
						imgType.setTypeName(dicInfo.getValue());
						imgType.setTypeCode(dicInfo.getCode());
						imgType.setBussinessType(1);
						imgTypeList.add(imgType);
					}

				}

				if (busType != null && busType.size() > 0) {
					for (DicInfo dic : busType) {
						SupplierImgType imgType = new SupplierImgType();
						imgType.setBussinessType(0);
						imgType.setTypeCode(dic.getCode());
						imgType.setTypeName(dic.getValue());
						imgTypeList.add(imgType);
					}
				}

				id = supplierBiz.saveSupplier(supplierInfo,bizId, imgTypeList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			if (items != null && items != "") {
				supplierItemBiz.saveSupplierItem(items, id);
			}
			webResult.setValue(String.valueOf(id));
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
		
	}

	/**
	 * 编辑供应商
	 *
	 * @param request
	 * @param reponse
	 * @param model
	 * @param supplierInfo
	 * @return
	 */
	@Override
	public WebResult<Boolean> editRest(SupplierInfoDTO supplierInfoDTO, String items) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			SupplierInfo supplierInfo = supplierInfoDTO.getSupplierInfo();
			if (supplierInfo.getProvinceId() != null) {
				supplierInfo.setProvinceName(regionBiz.getById(
						supplierInfo.getProvinceId() + "").getName());
			}
			if (supplierInfo.getCityId() != null) {
				supplierInfo.setCityName(regionBiz.getById(
						supplierInfo.getCityId() + "").getName());
			}
			if (supplierInfo.getAreaId() != null) {
				supplierInfo.setAreaName(regionBiz.getById(
						supplierInfo.getAreaId() + "").getName());
			}
			if (supplierInfo.getTownId() != null) {
				supplierInfo.setTownName(regionBiz.getById(
						supplierInfo.getTownId() + "").getName());
			}

			try {
				supplierBiz.updateSupplier(supplierInfo);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			if (items != null && items != "") {
				supplierItemBiz.saveSupplierItem(items, supplierInfo.getId());
			}
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
	}

	/**
	 * 启用停用
	 *
	 */
	@Override
	public WebResult<Boolean> changeState(Integer supplierId, Integer state) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			SupplierInfo supplierInfo = supplierBiz
					.selectBySupplierId(supplierId);
			supplierInfo.setState(state);
			supplierBiz.updateSupplier(supplierInfo);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> delSupplier(Integer bizId, Integer supplierId) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			List<SupplierContract> list = contractBiz.findContracts(
					bizId, supplierId);
			if (list != null && list.size() > 0) {
				throw new  RuntimeException("存在已签订的合同协议,暂时无法删除");
			}
			existOrderServer(supplierId);
			supplierBiz.delPrivateSupplier(supplierId,bizId);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}

	/**
	 * 判断是否存在相应订单业务
	 * @param supplierId
	 * @return
	 */
	public String existOrderServer(int supplierId) {
		
		int exist = bookingSupplierBiz.getOrderCountBySupplierId(supplierId);
		if (exist >0) {
			throw new  RuntimeException("存在已发生的计调订单业务,暂时无法删除");
		}else {
			int existGroupOrder = groupOrderBiz.existgroupOrder(supplierId);
			if (existGroupOrder >0) {
				throw new  RuntimeException("存在已发生的组团社订单业务,暂时无法删除");
			}else {
				int exitBookingShop = bookingShopBiz.existBookingShop(supplierId);
				if (exitBookingShop >0) {
					throw new  RuntimeException("存在已发生的购物订单业务,暂时无法删除");
				}
			}
		}
		return "";
	}
	
	

	@Override
	public WebResult<Boolean> fixSupplierName(Integer supplierId, Integer supplierType, String supplierName) {
		//supplierName = new String(supplierName.getBytes("iso-8859-1"),"GB2312");
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierName = URLDecoder.decode(supplierName,"utf-8");
			
			System.out.println(supplierName);
			bookingSupplierBiz.fix_SupplierName_All(supplierId, supplierName);
			productBiz.fix_SupplierName(supplierId, supplierName);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
		
	}
	
//	public String isUpdate(boolean flag) {
//		if (!flag) {
//			return errorJson("更新失败");
//		}
//		return "";
//	}
	
	
	/**
	 * 跳转到银行、发票等其他信息页
	 *
	 * @param supplierId
	 * @return
	 */
	@Override
	public BusinessInfoResult toBusinessInfo(Integer supplierId) {
		BusinessInfoResult webResult = new BusinessInfoResult();
		try{
			List<SupplierBankaccount> supplierBankaccountList = supplierBiz
					.selectBankBySupplierId(supplierId);

			List<SupplierBill> supplierBillList = supplierBiz
					.selectBillBySupplierId(supplierId);

			SupplierInfo supplierInfo = supplierBiz
					.selectBySupplierId(supplierId);

			SupplierVO supplierVO = new SupplierVO();
			supplierVO.setSupplierBankaccountList(supplierBankaccountList);
			supplierVO.setSupplierBillList(supplierBillList);

			webResult.setSupplierVO(supplierVO);
			webResult.setSupplierType(supplierInfo.getSupplierType());

			List<DicInfo> bankList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_BANK);
			webResult.setBankList(bankList);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}

	/**
	 * 添加银行帐号信息
	 *
	 * @param request
	 * @param reponse
	 * @param supplierBankaccount
	 */
	@Override
	public WebResult<Boolean> addBankInfo(SupplierBankaccountDTO supplierBankaccountDTO) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			SupplierBankaccount supplierBankaccount = supplierBankaccountDTO.getSupplierBankaccount();
			supplierBankaccount.setCreateTime(System.currentTimeMillis());

			DicInfo di = dicBiz.getById(supplierBankaccount.getBankId() + "");
			supplierBankaccount.setBankName(di.getValue());

			supplierBiz.saveBankaccount(supplierBankaccount);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}

	/**
	 * 查找所编辑的银行信息
	 * @param id
	 * @return
	 */
	@Override
	public WebResult<SupplierBankaccount> getBankInfo(Integer id) {
		WebResult<SupplierBankaccount> webResult = new WebResult<SupplierBankaccount>();
		try{
			SupplierBankaccount supplierBankaccount = supplierBiz
					.selectSupplierBankaccountById(id);
			webResult.setValue(supplierBankaccount);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> editBankInfo(SupplierBankaccountDTO supplierBankaccountDTO) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			SupplierBankaccount supplierBankaccount = supplierBankaccountDTO.getSupplierBankaccount();
			DicInfo dicInfo = dicBiz.getById(supplierBankaccount.getBankId()
					+ "");
			supplierBankaccount.setBankName(dicInfo.getValue());
			supplierBiz.updateBankaccount(supplierBankaccount);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}

	@Override
	public WebResult<Boolean> delBankInfo(Integer id) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierBiz.delBankaccount(id);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> addBillInfo(SupplierBillDTO supplierBillDTO) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			SupplierBill supplierBill = supplierBillDTO.getSupplierBill();
			supplierBill.setCreateTime(System.currentTimeMillis());
			supplierBiz.saveBill(supplierBill);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> setDefault(Integer billId, Integer supplierId) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			List<SupplierBill> selectBillBySupplierId = supplierBiz
					.selectBillBySupplierId(supplierId);
			for (SupplierBill supplierBill : selectBillBySupplierId) {
				if (supplierBill.getId() == billId) {
					supplierBill.setIsDefault(0);
				} else {
					supplierBill.setIsDefault(1);
				}
				supplierBiz.updateBill(supplierBill);
			}
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}

	@Override
	public WebResult<SupplierBill> getBillInfo(Integer id) {
		
		WebResult<SupplierBill> webResult = new WebResult<SupplierBill>();
		try{
			SupplierBill supplierBill = supplierBiz.selectSupplierBillById(id);
			webResult.setValue(supplierBill);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> editBillInfo(SupplierBillDTO supplierBillDTO) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierBiz.updateBill(supplierBillDTO.getSupplierBill());
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> delBillInfo(Integer id) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierBiz.delBill(id);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public ContactManListResult toContactList(Integer bizId,Integer id) {
		
		ContactManListResult webResult = new ContactManListResult();
		try{
			List<SupplierContactMan> list = supplierBiz
					.selectPrivateManBySupplierId(bizId, id);

			webResult.setManList(list);

			SupplierInfo supplierInfo = supplierBiz.selectBySupplierId(id);
			webResult.setSupplierType(supplierInfo.getSupplierType());
			List<SupplierContactMan> allManList = supplierBiz
					.selectAllManBySupplierId(bizId, id);
			webResult.setAllManList(allManList);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	
	}

	@Override
	public  WebResult<List<SupplierContactMan>> getContactManList(Integer bizId,Integer id) {
		
		WebResult<List<SupplierContactMan>> webResult = new WebResult<List<SupplierContactMan>>();
		try{
			List<SupplierContactMan> list = supplierBiz
					.selectPrivateManBySupplierId(bizId, id);
			webResult.setValue(list);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}

	@Override
	public WebResult<Boolean> addPrivateMan(Integer bizId, String ids, Integer supplierId) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierBiz.savePrivateMan(bizId,supplierId, ids);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> addContactMan(Integer bizId,SupplierContactManDTO supplierContactManDTO) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierBiz.saveSupplierContactMan(bizId,supplierContactManDTO.getSupplierContactMan());
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public WebResult<String> verifyMobile(Integer manId, String mobile) {
		WebResult<String> webResult = new WebResult<String>();
		try{
			List<SupplierContactMan> list = supplierBiz.validateMobile(manId,
					mobile);

			if (list != null && list.size() > 0) {
				webResult.setValue("false");
			}else{
				webResult.setValue("true");
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		

	}

	@Override
	public WebResult<Boolean> delContactMan(Integer bizId, Integer supplierId, Integer manId) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierBiz.delRalationMan(supplierId,
					bizId, manId);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public WebResult<SupplierContactMan> getManInfo( Integer manId) {
		
		WebResult<SupplierContactMan> webResult = new WebResult<SupplierContactMan>();
		try{
			SupplierContactMan man = supplierBiz.selectSupplierContactManById(manId);
			webResult.setValue(man);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> editContactMan(SupplierContactManDTO supplierContactManDTO) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierBiz.updateContactMan(supplierContactManDTO.getSupplierContactMan());
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

//	@Override
//	public String toTravelagencyList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		
//		supplierInfo.setSupplierType(Constants.TRAVELAGENCY);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	public String toRestaurant(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.RESTAURANT);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	@RequiresPermissions(PermissionConstants.SUPPLIER_HOTEL)
//	public String toHotelList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.HOTEL);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	public String toFleetList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.FLEET);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	public String toScenicspotList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.SCENICSPOT);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	public String toShoppingList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.SHOPPING);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	public String toEntertainmentList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.ENTERTAINMENT);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	public String toAirticketagentList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.AIRTICKETAGENT);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	public String toTrainticketagentList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.TRAINTICKETAGENT);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	public String toGolfList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.GOLF);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	public String toOtherList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.OTHER);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	public String toInsuranceList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.INSURANCE);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}
//
//	@Override
//	public String toLocalTravelList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model,
//			SupplierInfo supplierInfo) {
//		supplierInfo.setSupplierType(Constants.LOCALTRAVEL);
//		return toSuplierList(request, reponse, model, supplierInfo);
//	}

	@Override
	public SuplierListResult toSuplierList(SupplierInfoDTO supplierInfoDTO,Integer bizId) {
		
		SuplierListResult webResult = new SuplierListResult();
		try{
			SupplierInfo supplierInfo = supplierInfoDTO.getSupplierInfo();
			
			// 根据供应商类型查询当前登录商家所属的供应商
			PageBean pageBean = new PageBean();
			pageBean.setPageSize(supplierInfo.getPageSize());
			pageBean.setParameter(supplierInfo);
			pageBean.setPage(supplierInfo.getPage());
			pageBean = supplierBiz.selectPrivateSupplierList(pageBean,bizId);
			List result = pageBean.getResult();
			if (result!=null && result.size()>0) {
				//查询每个供应商的协议状态，有一个协议有效即为有效
				for (Object object : result) {
				 SupplierInfo	supplier=(SupplierInfo)object;
				 BizSupplierRelation bizSupplierRelation = bizSupplierRelationBiz.getByBizIdAndSupplierId(bizId, Integer.valueOf(supplier.getId()));
					if (bizSupplierRelation != null) {
						Integer stateCount = supplierBiz.getSupplierContractState(bizSupplierRelation.getId(), new Date());
						if (supplierInfo.getSupplierType() != 4) {
							if (stateCount > 0) {
								supplier.setContractState("<font color='blue'>有效</font>");
							} else {
								supplier.setContractState("<font color='red'>无效</font>");
							}
						} else {
							//车队(SupplierType为 4)共用协议，不存在有效/无效，显示为"--"
							supplier.setContractState("<font color='blue'>--</font>");
						}
					}
				}
			}
			webResult.setPageBean(pageBean);
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			List<RegionInfo> cityList = regionBiz.getRegionById(supplierInfo
					.getProvinceId() + "");
			webResult.setCityList(cityList);
			List<RegionInfo> areaList = regionBiz.getRegionById(supplierInfo
					.getCityId() + "");
			webResult.setAreaList(areaList);
			List<DicInfo> travelagencylevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_TRAVELAGENCY);
			webResult.setTravelagencylevelList(travelagencylevelList);
			List<DicInfo> restaurantlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_RESTAURANT);
			webResult.setRestaurantlevelList(restaurantlevelList);
			List<DicInfo> levelList = dicBiz
					.getListByTypeCode(BasicConstants.GYXX_JDXJ);
			webResult.setLevelList(levelList);
			List<DicInfo> fleetlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_FLEET);
			webResult.setFleetlevelList(fleetlevelList);
			List<DicInfo> scenicspotlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_SCENICSPOT);
			webResult.setScenicspotlevelList(scenicspotlevelList);
			List<DicInfo> shoppinglevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_SHOPPING);
			webResult.setShoppinglevelList(shoppinglevelList);
			List<DicInfo> entertainmentlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_ENTERTAINMENT);
			webResult.setEntertainmentlevelList(entertainmentlevelList);
			List<DicInfo> guidelevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_GUIDE);
			webResult.setGuidelevelList(guidelevelList);
			List<DicInfo> airticketagentlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_AIRTICKETAGENT);
			webResult.setAirticketagentlevelList(airticketagentlevelList);
			List<DicInfo> trainticketagentlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_TRAINTICKETAGENT);
			webResult.setTrainticketagentlevelList(trainticketagentlevelList);
			List<DicInfo> golflevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_GOLF);
			webResult.setGolflevelList(golflevelList);
			List<DicInfo> insuranclevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_INSURANCE);
			webResult.setInsuranclevelList(insuranclevelList);
			List<DicInfo> otherlevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_OTHER);
			webResult.setOtherlevelList(otherlevelList);
			List<DicInfo> localtravelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_LOCALTRAVEL);
			webResult.setLocaltravelList(localtravelList);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
		
	}

	@Override
	public SuplierListResult toImpSupplierList(SupplierInfoDTO supplierInfoDTO,Integer bizId) {
		SuplierListResult webResult = new SuplierListResult();
		try{
			SupplierInfo supplierInfo = supplierInfoDTO.getSupplierInfo();
			PageBean pageBean = new PageBean();
			pageBean.setPageSize(supplierInfo.getPageSize());
			pageBean.setParameter(supplierInfo);
			pageBean.setPage(supplierInfo.getPage());
			pageBean = supplierBiz.selectAllSupplierListPage(pageBean,bizId);
			webResult.setPageBean(pageBean);
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			List<RegionInfo> cityList = regionBiz.getRegionById(supplierInfo
					.getProvinceId() + "");
			webResult.setCityList(cityList);
			List<RegionInfo> areaList = regionBiz.getRegionById(supplierInfo
					.getCityId() + "");
			webResult.setAreaList(areaList);

			List<DicInfo> levelList = dicBiz
					.getListByTypeCode(BasicConstants.GYXX_JDXJ);
			webResult.setLevelList(levelList);
			List<DicInfo> travelagencylevelList = dicBiz
					.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_TRAVELAGENCY);
			webResult.setTravelagencylevelList(travelagencylevelList);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
		
	}

	@Override
	public WebResult<Boolean> impSupplier(Integer bizId,String ids) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierBiz.addPrivateSupplier(bizId, ids);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}

//	@Override
//	public String guideList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, SupplierGuide guide,
//			Integer page, Integer pageSize) {
//		loadGuideList(model, guide, page, pageSize, null);
//		return "supplier/guide/guide-list";
//	}
//
//	@Override
//	public String guideListQuery(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, SupplierGuide guide,
//			Integer page, Integer pageSize) {
//		loadGuideList(model, guide, page, pageSize, null);
//		return "supplier/guide/guide-list-table";
//	}
	@Override
	public GuideListResult loadGuideList(SupplierGuideDTO guideDTO, Integer page, Integer pageSize,Integer bizId) {
		
		GuideListResult webResult = new GuideListResult();
		try{

			PageBean pageBean = guideBiz.getGuideList(guideDTO.getSupplierGuide(), page, pageSize,
					bizId);
			webResult.setPageBean(pageBean);
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}
	@Override
	public GuideListResult loadMyGuideList(SupplierGuideDTO guideDTO, Integer page, Integer pageSize,Integer bizId) {
		
		GuideListResult webResult = new GuideListResult();
		try{
			if (page == null) {
				page = 1;
			}
			if (pageSize == null) {
				pageSize = Constants.PAGESIZE;
			}

			PageBean pageBean = guideBiz.getGuideListByBizId(guideDTO.getSupplierGuide(), bizId,page, pageSize);
			webResult.setPageBean(pageBean);
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;

	}

	@Override
	public GuideAddResult guideAdd() {
		GuideAddResult webResult = new GuideAddResult();
		try{
			List<DicInfo> mzList = dicBiz
					.getListByTypeCode(BasicConstants.GYXX_MZ);
			List<DicInfo> djList = dicBiz
					.getListByTypeCode(BasicConstants.DYXX_DJ);
			List<DicInfo> xjpdList = dicBiz
					.getListByTypeCode(BasicConstants.DYXX_XJPD);
			List<DicInfo> shdtrsList = dicBiz
					.getListByTypeCode(BasicConstants.DYXX_SHDTRS);
			webResult.setMzList(mzList);
			webResult.setDjList(djList);
			webResult.setXjpdList(xjpdList);
			webResult.setShdtrsList(shdtrsList);

			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
		
	}

	@Override
	public GuideAddResult editGuide(Integer id) {
		
		GuideAddResult webResult = new GuideAddResult();
		try{
			List<DicInfo> mzList = dicBiz
					.getListByTypeCode(BasicConstants.GYXX_MZ);
			List<DicInfo> djList = dicBiz
					.getListByTypeCode(BasicConstants.DYXX_DJ);
			List<DicInfo> xjpdList = dicBiz
					.getListByTypeCode(BasicConstants.DYXX_XJPD);
			List<DicInfo> shdtrsList = dicBiz
					.getListByTypeCode(BasicConstants.DYXX_SHDTRS);
			webResult.setMzList(mzList);
			webResult.setDjList(djList);
			webResult.setXjpdList(xjpdList);
			webResult.setShdtrsList(shdtrsList);

			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			
			SupplierGuide guide = null;
			guide = guideBiz.getGuideInfoById(id);
			webResult.setSupplierGuide(guide);
			if (guide.getProvinceId() != null) {
				List<RegionInfo> cityList = regionBiz.getRegionById(guide
						.getProvinceId() + "");
				webResult.setCityList(cityList);
			}
			if (guide.getCityId() != null) {
				List<RegionInfo> areaList = regionBiz.getRegionById(guide
						.getCityId() + "");
				webResult.setAreaList(areaList);
			}
			if (guide.getAreaId() != null) {
				List<RegionInfo> townList = regionBiz.getRegionById(guide
						.getAreaId() + "");
				webResult.setTownList(townList);
			}
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}
	
	@Override
	public GuideAddResult guideDetail(Integer id) {
		
		GuideAddResult webResult = new GuideAddResult();
		try{
			List<DicInfo> mzList = dicBiz
					.getListByTypeCode(BasicConstants.GYXX_MZ);
			List<DicInfo> djList = dicBiz
					.getListByTypeCode(BasicConstants.DYXX_DJ);
			List<DicInfo> xjpdList = dicBiz
					.getListByTypeCode(BasicConstants.DYXX_XJPD);
			List<DicInfo> shdtrsList = dicBiz
					.getListByTypeCode(BasicConstants.DYXX_SHDTRS);
			webResult.setMzList(mzList);
			webResult.setDjList(djList);
			webResult.setXjpdList(xjpdList);
			webResult.setShdtrsList(shdtrsList);

			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			
			SupplierGuide guide = null;
			guide = guideBiz.getGuideInfoById(id);
			webResult.setSupplierGuide(guide);
			if (guide.getProvinceId() != null) {
				List<RegionInfo> cityList = regionBiz.getRegionById(guide
						.getProvinceId() + "");
				webResult.setCityList(cityList);
			}
			if (guide.getCityId() != null) {
				List<RegionInfo> areaList = regionBiz.getRegionById(guide
						.getCityId() + "");
				webResult.setAreaList(areaList);
			}
			if (guide.getAreaId() != null) {
				List<RegionInfo> townList = regionBiz.getRegionById(guide
						.getAreaId() + "");
				webResult.setTownList(townList);
			}
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}

	@Override
	public WebResult<Boolean> delGuide(Integer bizId, Integer id) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			guideBiz.deleteBizGuideRelaion(bizId, id);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public WebResult<Boolean> delPublicGuide(Integer id) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			List<BizGuideRelation> list = guideBiz.getByGuideId(id);
			if (list != null && list.size() > 0) {
				throw new RuntimeException("商家导游存在关联关系,请先清除关联关系后再进行删除操作");
			}

			guideBiz.delById(id);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}

	@Override
	public WebResult<Map<String,Object>> saveGuide(SupplierGuideDTO guideDTO,Integer bizId) {
		
		WebResult<Map<String,Object>> webResult = new WebResult<Map<String,Object>>();
		try{
			SupplierGuide guide = guideDTO.getSupplierGuide();
			if (guide.getId() != null) {
				if (guide.getNationality() != -1) {
					DicInfo dicInfo = dicBiz.getById(guide.getNationality()
							+ "");
					guide.setNationalityName(dicInfo.getValue());
				}
				guideBiz.updateGuideInfo(guide);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("result", "true"); //不同的更新方式对相应不同的返回结果值
				webResult.setValue(map); 
			} else {
				int id = guideBiz.addGuideInfo(guide,bizId);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("guideId", id);//不同的更新方式对相应不同的返回结果值
				webResult.setValue(map);
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}

	@Override
	public GuideListResult guideArchivesList(SupplierGuideDTO guideDTO, Integer page, Integer pageSize,Integer bizId) {
		
		GuideListResult webResult = new GuideListResult();
		try{
			List<DicInfo> list = dicBiz
					.getListByTypeCode(BasicConstants.DYXX_DJ);
			webResult.setDjList(list);
			if (page == null) {
				page = 1;
			}
			if (pageSize == null) {
				pageSize = Constants.PAGESIZE;
			}

			PageBean pageBean = guideBiz.getGuideListByBizId(guideDTO.getSupplierGuide(), bizId,page, pageSize);
			webResult.setPageBean(pageBean);
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

//	@Override
//	public String queryGuideArchivesList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, SupplierGuide guide,
//			Integer page, Integer pageSize) {
//		loadMyGuideList(request, model, guide, page, pageSize);
//		return "supplier/guide/guideArchivesList-table";
//	}
	@Override
	public WebResult<List<DicInfo>> getListByTypeCode(String typecode){
		WebResult<List<DicInfo>> webResult = new WebResult<List<DicInfo>>();
		try{
			List<DicInfo> list = dicBiz.getListByTypeCode(typecode);
			webResult.setValue(list);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

	@Override
	public GuideListResult impGuideArchivesList(SupplierGuideDTO guideDTO, Integer page, Integer pageSize,Integer bizId) {
		GuideListResult webResult = new GuideListResult();
		try{
			List<DicInfo> list = dicBiz
					.getListByTypeCode(BasicConstants.DYXX_DJ);
			webResult.setDjList(list);
			if (page == null) {
				page = 1;
			}
			if (pageSize == null) {
				pageSize = Constants.PAGESIZE;
			}

			PageBean pageBean = guideBiz.getGuideList(guideDTO.getSupplierGuide(), page, pageSize,
					bizId);
			webResult.setPageBean(pageBean);
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

//	@Override
//	public String queryImpGuideArchivesList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, SupplierGuide guide,
//			Integer page, Integer pageSize) {
//		Integer bizId = WebUtils.getCurBizId(request);
//		loadGuideList(model, guide, page, pageSize, bizId);
//		return "supplier/guide/impGuideArchivesList-table";
//	}

	@Override
	public WebResult<PageBean> expGuide(SupplierGuideDTO guideDTO,Integer bizId, Integer page, Integer pageSize) {
		
		WebResult<PageBean> webResult = new WebResult<PageBean>();
		try{
			PageBean pageBean = guideBiz.getGuideListByBizId(guideDTO.getSupplierGuide(), bizId, page,
					pageSize);
			webResult.setValue(pageBean);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}

//	@Override
//	public String bizGuideList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, SupplierGuide guide,
//			Integer page, Integer pageSize) {
//		loadMyGuideList(request, model, guide, page, pageSize);
//		return "supplier/guide/supplier-guide-list";
//	}

//	@Override
//	public String queryBizGuideList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, SupplierGuide guide,
//			Integer page, Integer pageSize) {
//		loadMyGuideList(request, model, guide, page, pageSize);
//		return "supplier/guide/supplier-guide-list-table";
//	}
//
//	@Override
//	public String impGuideList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, SupplierGuide guide,
//			Integer page, Integer pageSize) {
//		Integer bizId = WebUtils.getCurBizId(request);
//		loadGuideList(model, guide, page, pageSize, bizId);
//		return "supplier/guide/imp-guide-list";
//	}
//
//	@Override
//	public String queryImpGuideList(HttpServletRequest request,
//			HttpServletResponse reponse, ModelMap model, SupplierGuide guide,
//			Integer page, Integer pageSize) {
//		Integer bizId = WebUtils.getCurBizId(request);
//		loadGuideList(model, guide, page, pageSize, bizId);
//		return "supplier/guide/imp-guide-list-table";
//	}

	@Override
	public WebResult<Boolean> impGuideList(Integer bizId,String guideIds) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			if (StringUtils.isNotBlank(guideIds)) {
				String[] guidArr = guideIds.split(",");

				guideBiz.addBizGuideRelation(bizId, guidArr);
				webResult.setValue(true);
			} else {
				throw new  RuntimeException("没有选择导游");
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;

		
	}

	/**
	 * 跳转到图片页面
	 * 
	 */
	@Override
	public FolderListResult toFolderList(Integer id,Integer supplierType, Integer operType) {
		
		FolderListResult webResult = new FolderListResult();
		try{
			List<SupplierImgType> imgTypeList = supplierBiz
					.getImgTypeListBySupplierId(id);
			List<SupplierImgType> bussList = new ArrayList<SupplierImgType>();
			List<SupplierImgType> huanList = new ArrayList<SupplierImgType>();
			for (SupplierImgType supplierImgType : imgTypeList) {
				if (supplierImgType.getBussinessType() == 0) {
					bussList.add(supplierImgType);
				} else {
					huanList.add(supplierImgType);
				}
			}
			webResult.setBussList(bussList);
			webResult.setHuanList(huanList);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
		

	}

	/**
	 * 跳转到图片展示页面
	 * 
	 */
	@Override
	public PictureListResult toPictureList(Integer id,Integer supplierId, Integer supplierType, Integer operType) {
		
		PictureListResult webResult = new PictureListResult();
		try{
			List<SupplierImg> imgList = supplierImgBiz.getImgListBySupplierId(id);

			SupplierImgType supplierImgType = supplierBiz
					.selectBySupplierImgTypeId(id);
			webResult.setImgList(imgList);
			webResult.setSupplierImgType(supplierImgType);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
		

	}

	/**
	 * 保存图片
	 * 
	 * @param request
	 * @param reponse
	 * @param imgs
	 * @return
	 */
	@Override
	public WebResult<Boolean> saveSupplierImg(String imgs) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			List<SupplierImg> imgList = JSON.parseArray(imgs, SupplierImg.class);
			for (SupplierImg supplierImg : imgList) {
				supplierImg.setCreateTime(System.currentTimeMillis());
				supplierImg.setBussniessType(1);
				supplierImgBiz.insert(supplierImg);
			}
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;

		

	}

	@Override
	public WebResult<Boolean> delPicture( Integer id) {
		

		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierImgBiz.deleteByPrimaryKey(id);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}
	
	@Override
	public WebResult<String> searchSupplier(Integer bizId, Integer supplierType, String keyword) {
		
		WebResult<String> webResult = new WebResult<String>();
		try{
			HashMap<String, String> parameter = new HashMap<String, String>();
			parameter.put("bizId", bizId.toString());
			parameter.put("supplierType", supplierType.toString());
			parameter.put("supplierName", keyword);
			List<SupplierInfo> ret = supplierBiz.searchSupplierByKeyword(parameter);
			HashMap<String, Object> json = new HashMap<String, Object>();
			json.put("result", ret);
			json.put("success", "true");
			webResult.setValue(JSON.toJSONString(json));
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;

		
	}
	/**
	 * 获取待审核的供应商列表
	 * @param request
	 * @param response
	 * @param model
	 * @param supplierInfo
	 * @return
	 */
	@Override
	public WebResult<PageBean> supplierCheckList(PageBean pageBean,Integer bizId){
		
		WebResult<PageBean> webResult = new WebResult<PageBean>();
		try{
			pageBean= supplierBiz.selectCheckingSupplierListPage(pageBean, bizId);
			webResult.setValue(pageBean);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
		
		
	}
	/**
	 * 审核供应商
	 * @param checkedSupplierIds
	 * @return
	 */
	
	@Override
	public WebResult<Boolean> checkSupplier(String checkedSupplierIds){
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			List<SupplierInfo> supplierInfos = JSONArray.parseArray(checkedSupplierIds, SupplierInfo.class);
			
			for (SupplierInfo info : supplierInfos) {
				info.setState(1);
				supplierBiz.updateSupplier(info);
				webResult.setValue(true);
				break;
			}
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
		
		
	}
}
