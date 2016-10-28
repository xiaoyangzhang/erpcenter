package com.yimayhd.erpcenter.facade.supplier.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.supplier.errorcode.SupplierErrorCode;
import com.yimayhd.erpcenter.facade.supplier.query.SupplierGuestDTO;
import com.yimayhd.erpcenter.facade.supplier.result.GuestLabelEditResult;
import com.yimayhd.erpcenter.facade.supplier.result.GuestLabelListResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;
import com.yimayhd.erpcenter.facade.supplier.service.SupplierGuestFacade;
import com.yimayhd.erpresource.biz.service.SupplierGuestBiz;
import com.yimayhd.erpresource.biz.service.SupplierImgBiz;
import com.yimayhd.erpresource.dal.po.SupplierGuest;
import com.yimayhd.erpresource.dal.po.SupplierGuestLabel;


public class SupplierGuestFacadeImpl implements SupplierGuestFacade {
	private static final Logger log = LoggerFactory
			.getLogger(SupplierGuestFacadeImpl.class);
	@Autowired
	private SupplierGuestBiz supplierGuestBiz;
	@Autowired
	private SupplierImgBiz supplierImgBiz;
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private RegionBiz regionBiz;
	
	@Override
	public GuestLabelListResult guestLabelList(Integer bizId) {
		
		GuestLabelListResult webResult = new GuestLabelListResult();
		try{
			List<List<SupplierGuestLabel>> lists = new ArrayList<List<SupplierGuestLabel>>();
			PageBean pageBean = new PageBean();
			List<SupplierGuestLabel> supplierGuestLabels = supplierGuestBiz.selectSupplierGuestLabelList(bizId);
			double dou = (double)supplierGuestLabels.size()/5;
			int length = (int)Math.ceil(dou);
			for(int i=0;i<length;i++){
				pageBean.setPage(i+1);
				pageBean.setPageSize(5);
				List<SupplierGuestLabel> supplier = supplierGuestBiz.selectSupplierGuestLabelListPage(bizId,pageBean);
				lists.add(supplier);
			}
			webResult.setLength(length);
			webResult.setSupplierGuestLabels(lists);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
	}
	
	@Override
	public WebResult<Boolean> deleteLabel(Integer id) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierGuestBiz.deleteByPrimaryKey(id);
			supplierGuestBiz.deleteRelationsByLabelId(id);
			webResult.setValue(true);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}
	
	@Override
	public WebResult<Boolean> addLabel(Integer bizId, String name) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			try{
				if(StringUtils.isEmpty(name)){
					throw new RuntimeException("标签名称不能为空");
				}
				SupplierGuestLabel extLabel = supplierGuestBiz.selectLabelByName(bizId,name.trim());
				if(null!=extLabel){
					throw new RuntimeException("此标签已存在");
				}
				SupplierGuestLabel label = new SupplierGuestLabel();
				label.setBizId(bizId);
				label.setName(name.trim());
				label.setNum(0);
				label.setCreateTime(System.currentTimeMillis());
				supplierGuestBiz.addGuestLabel(label);
				webResult.setValue(true);
			}catch(Exception e){
				log.error(e.getMessage());
				throw new RuntimeException("操作失败");
			}	
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}
	@Override
	public GuestLabelListResult guestList(Integer bizId) {
		
		GuestLabelListResult webResult = new GuestLabelListResult();
		try{
			List<List<SupplierGuestLabel>> lists = new ArrayList<List<SupplierGuestLabel>>();
			PageBean pageBean = new PageBean();
			List<SupplierGuestLabel> supplierGuestLabels = supplierGuestBiz.selectSupplierGuestLabelList(bizId);
			double dou = (double)supplierGuestLabels.size()/9;
			int length = (int)Math.ceil(dou);
			for(int i=0;i<length;i++){
				pageBean.setPage(i+1);
				pageBean.setPageSize(9);
				List<SupplierGuestLabel> supplier = supplierGuestBiz.selectSupplierGuestLabelListPage(bizId,pageBean);
				lists.add(supplier);
			}
			webResult.setLength(length);
			webResult.setSupplierGuestLabels(lists);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	}
	@Override
	public WebResult<PageBean> guestListDo(PageBean pageBean) {
		
		WebResult<PageBean> webResult = new WebResult<PageBean>();
		try{
			pageBean = supplierGuestBiz.selectSupplierGuestListPage(pageBean);
			
			webResult.setValue(pageBean);
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;

		
	}
	
	@Override
	public WebResult<Boolean> deleteGuest(Integer id) {
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			supplierGuestBiz.deleteByGuestPrimaryKey(id);
			supplierGuestBiz.deleteRelationsByGuestId(id);
			//统计标签人数
			supplierGuestBiz.tjLabelNum();
			webResult.setValue(true);
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
		
	}
	@Override
	public WebResult<List<TourGroup>> selectTravelRecords(Integer bizId, String idCard) {
		
		WebResult<List<TourGroup>> webResult = new WebResult<List<TourGroup>>();
		try{
			List<TourGroup> groups = tourGroupBiz.selectTravelRecordsByIdCard(idCard.trim(),bizId);
			webResult.setValue(groups);
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
	
	}
	
	@Override
	public GuestLabelListResult addGuest(Integer bizId, Integer id) {
		
		GuestLabelListResult webResult = new GuestLabelListResult();
		try{
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			//每行5个显示标签
			List<List<SupplierGuestLabel>> lists = new ArrayList<List<SupplierGuestLabel>>();
			PageBean pageBean = new PageBean();
			List<SupplierGuestLabel> supplierGuestLabels = supplierGuestBiz.selectSupplierGuestLabelList(bizId);
			double dou = (double)supplierGuestLabels.size()/5;
			int length = (int)Math.ceil(dou);
			for(int i=0;i<length;i++){
				pageBean.setPage(i+1);
				pageBean.setPageSize(5);
				List<SupplierGuestLabel> supplier = supplierGuestBiz.selectSupplierGuestLabelListPage(bizId,pageBean);
				lists.add(supplier);
			}
			webResult.setLength(length);
			webResult.setSupplierGuestLabels(lists);
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
		
	}
	@Override
	public GuestLabelEditResult editGuest(Integer bizId, Integer id) {
		GuestLabelEditResult webResult = new GuestLabelEditResult();
		try{
			SupplierGuest guest = supplierGuestBiz.selectGuestListById(id);
			
			webResult.setGuest(guest);
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			if (guest.getProvinceId() != null) {
				List<RegionInfo> cityList = regionBiz.getRegionById(guest
						.getProvinceId() + "");
				webResult.setCityList(cityList);
			}
			if (guest.getAdProvinceId() != null) {
				List<RegionInfo> adCityList = regionBiz.getRegionById(guest
						.getAdProvinceId() + "");
				webResult.setAdCityList(adCityList);
			}
			
			///每行5个显示标签
			List<List<SupplierGuestLabel>> lists = new ArrayList<List<SupplierGuestLabel>>();
			PageBean pageBean = new PageBean();
			List<SupplierGuestLabel> supplierGuestLabels = supplierGuestBiz.selectSupplierGuestLabelList(bizId);
			double dou = (double)supplierGuestLabels.size()/5;
			int length = (int)Math.ceil(dou);
			for(int i=0;i<length;i++){
				pageBean.setPage(i+1);
				pageBean.setPageSize(5);
				List<SupplierGuestLabel> supplier = supplierGuestBiz.selectSupplierGuestLabelListPage(bizId,pageBean);
				for (SupplierGuestLabel supplierGuestLabel : supplier) {
					Map<String,Object> lation = supplierGuestBiz.selectGuestLabelRelationByGuestIdAndLabelId(id,supplierGuestLabel.getId());
					if(null != lation){
						supplierGuestLabel.setChoose(true);
					}
				}
				
				lists.add(supplier);
			}
			webResult.setLength(length);
			webResult.setSupplierGuestLabels(lists);

			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
		
		
		
	}
	@Override
	public  WebResult<Boolean> saveGuest(SupplierGuestDTO guestDTO,String choseIds,Integer bizId) {
		
		WebResult<Boolean> webResult = new WebResult<Boolean>();
		try{
			SupplierGuest guest = guestDTO.getSupplierGuest();
			SupplierGuest supplierGuest = supplierGuestBiz.selectGuestByMobile(bizId,guest.getMobile(),guest.getId());
			if(null!=supplierGuest){
				throw new RuntimeException("已存在应用此手机的乘客！");
			}
			if (guest.getId() != null) {
				supplierGuestBiz.deleteRelationsByGuestId(guest.getId());
				supplierGuestBiz.updateGuest(guest);
				
			} else {
				int id = supplierGuestBiz.addGuestInfo(guest);
			}
			//添加关系
			SupplierGuest supp = supplierGuestBiz.selectGuestByMobile(bizId,guest.getMobile(),null);
			if(!StringUtils.isEmpty(choseIds)){
				String ids[] =choseIds.split(",");
				for(int i=0;i<ids.length;i++){
					supplierGuestBiz.insertRelation(supp.getId(),ids[i]);
				}
			}
			//统计标签人数
			supplierGuestBiz.tjLabelNum();
			webResult.setValue(true);
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(new SupplierErrorCode("2001", e.getMessage()));
		}
		return webResult;
		
	}

}
