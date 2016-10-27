package com.yimayhd.erpcenter.facade.supplier.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.facade.supplier.errorcode.SupplierErrorCode;
import com.yimayhd.erpcenter.facade.supplier.query.GuestConsultDTO;
import com.yimayhd.erpcenter.facade.supplier.query.GuestConsultFollowDTO;
import com.yimayhd.erpcenter.facade.supplier.result.ConsultGuestListResult;
import com.yimayhd.erpcenter.facade.supplier.result.FollowConsultResult;
import com.yimayhd.erpcenter.facade.supplier.result.WebResult;
import com.yimayhd.erpcenter.facade.supplier.service.ConsultFacade;
import com.yimayhd.erpresource.biz.service.ConsultBiz;
import com.yimayhd.erpresource.dal.po.GuestConsult;
import com.yimayhd.erpresource.dal.po.GuestConsultFollow;

public class ConsultFacadeImpl implements ConsultFacade {
	private static final Logger log = LoggerFactory
			.getLogger(ConsultFacadeImpl.class);

	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private ConsultBiz consultBiz;
	@Autowired
	private RegionBiz regionBiz;
	/**
	 * 客户咨询登记
	 */
	@Override
	public ConsultGuestListResult consultGuestList(String msglyxym, String msglxxqd, Integer bizId) {
		//意向游玩
		List<DicInfo> intentionDestList = dicBiz.getListByTypeCode(msglyxym,bizId);
		//信息渠道
		List<DicInfo> infoSourceList = dicBiz.getListByTypeCode(msglxxqd,bizId);
		ConsultGuestListResult consultGuestListResult = new ConsultGuestListResult();
		consultGuestListResult.setIntentionDestList(intentionDestList);
		consultGuestListResult.setInfoSourceList(infoSourceList);
		return consultGuestListResult;
	}
	@Override
	public WebResult<PageBean> getGuestConsultList(PageBean pageBean, Integer bizId) {
		WebResult<PageBean> webResult = new WebResult<PageBean>();
		try{
			pageBean = consultBiz.getGuestConsultList(pageBean, bizId);
			
			webResult.setValue(pageBean);
		}catch (Exception e) {
			log.error("客户登记列表",e);
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}
	@Override
	public FollowConsultResult addConsult(String msglkrly, String msglyxyw, String msglxxqd, Integer bizId) {
		
		FollowConsultResult webResult = new FollowConsultResult();
		try{
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			List<DicInfo> guestSources = dicBiz.getListByTypeCode(msglkrly,bizId);
			webResult.setGuestSources(guestSources);
			List<DicInfo> intentionDests = dicBiz.getListByTypeCode(msglyxyw,bizId);
			webResult.setIntentionDests(intentionDests);
			List<DicInfo> infoSources = dicBiz.getListByTypeCode(msglxxqd,bizId);
			webResult.setInfoSources(infoSources);
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
		
		
	
	}
	@Override
	public FollowConsultResult followConsult(String msglkrly, String msglyxyw, String msglxxqd, String msglgjfs,
			Integer guestId, Integer bizId) {
		
		FollowConsultResult webResult = new FollowConsultResult();
		try{
			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			List<DicInfo> guestSources = dicBiz.getListByTypeCode(msglkrly,bizId);
			webResult.setGuestSources(guestSources);
			List<DicInfo> intentionDests = dicBiz.getListByTypeCode(msglyxyw,bizId);
			webResult.setIntentionDests(intentionDests);
			List<DicInfo> infoSources = dicBiz.getListByTypeCode(msglxxqd,bizId);
			webResult.setInfoSources(infoSources);
			List<DicInfo> followWays = dicBiz.getListByTypeCode(msglgjfs,bizId);
			webResult.setFollowWays(followWays);
			GuestConsult guestConsult = consultBiz.selectGuestConsultByPrimaryKey(guestId);
			webResult.setGuestConsult(guestConsult);
			List<GuestConsultFollow> consultFollows = consultBiz.selectConsultFollowByConsultId(guestId, bizId);
			webResult.setConsultFollows(consultFollows);
			if (guestConsult.getProvinceId()!=null) {
				
				List<RegionInfo> cityList = regionBiz.getRegionById(guestConsult.getProvinceId().toString());
				webResult.setCityList(cityList);
			}
			
		}catch (Exception e) {
			log.error(e.getMessage());
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
		
		
		
	}
	@Override
	public FollowConsultResult viewConsult(String msglkrly, String msglyxyw, String msglxxqd, Integer guestId,
			Integer bizId) {
		FollowConsultResult webResult = new FollowConsultResult();
		try{

			List<RegionInfo> allProvince = regionBiz.getAllProvince();
			webResult.setAllProvince(allProvince);
			List<DicInfo> guestSources = dicBiz.getListByTypeCode(msglkrly,bizId);
			webResult.setGuestSources(guestSources);
			List<DicInfo> intentionDests = dicBiz.getListByTypeCode(msglyxyw,bizId);
			webResult.setIntentionDests(intentionDests);
			List<DicInfo> infoSources = dicBiz.getListByTypeCode(msglxxqd,bizId);
			webResult.setInfoSources(infoSources);
			GuestConsult guestConsult = consultBiz.selectGuestConsultByPrimaryKey(guestId);
			webResult.setGuestConsult(guestConsult);
			List<GuestConsultFollow> consultFollows = consultBiz.selectConsultFollowByConsultId(guestId, bizId);
			webResult.setConsultFollows(consultFollows);
			if (guestConsult.getProvinceId()!=null) {
				
				List<RegionInfo> cityList = regionBiz.getRegionById(guestConsult.getProvinceId().toString());
				webResult.setCityList(cityList);
			}
			
		
		}catch (Exception e) {
			log.error("客户登记列表",e);
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}
	@Override
	public String validatePhone(String phone) {
		return consultBiz.validatePhone(phone)>0?"false":"true";
	}
	@Override
	public WebResult<Integer> saveConsultGuest(GuestConsultDTO guestConsultDTO) {
		WebResult<Integer> webResult = new WebResult<Integer>();
		try{
			webResult.setValue(consultBiz.save(guestConsultDTO.getGuestConsult()));
		}catch (Exception e) {
			log.error("客户登记列表",e);
			webResult.setErrorCode(SupplierErrorCode.SYSTEM_ERROR);
		}
		return webResult;
	}
	@Override
	public Integer delConsultGuest(Integer guestId) {
		return consultBiz.delConsultGuest(guestId);
	}
	@Override
	public Integer saveConsultFollow(Integer type,GuestConsultFollowDTO followDTO) {
		 return consultBiz.save(followDTO.getGuestConsultFollow(),type);
	}
}
