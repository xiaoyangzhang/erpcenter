
/**
 * 
 */
package com.yimayhd.erpcenter.facade.common.service.impl;

import java.util.List;

import org.erpcenterFacade.common.client.result.DicListResult;
import org.erpcenterFacade.common.client.service.SaleCommonFacade;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
import com.yimayhd.erpresource.dal.constants.Constants;

/**
 * @ClassName: SaleCommonFacadeImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月25日
 */
public class SaleCommonFacadeImpl implements SaleCommonFacade {

	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private SysBizBankAccountBiz sysBizBankAccountBiz;
	@Override
	public List<DicInfo> getDeliveryItemList(Integer bizId) {
		List<DicInfo> typeList = dicBiz.getListByTypeCode(BasicConstants.XMFY_DJXM, bizId);
		return typeList;
	}
	
	@Override
	public List<DicInfo> getTransportListByTypeCode(Integer bizId) {
		return dicBiz.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
	}

	
	@Override
	public List<DicInfo> getCarListByTypeCode() {
		return dicBiz.getListByTypeCode(Constants.FLEET_TYPE_CODE);
	}

	
	@Override
	public List<DicInfo> getShopListByTypeCode() {
		return dicBiz.getListByTypeCode(Constants.SHOPPING_TYPE_CODE);
	}

	@Override
	public List<DicInfo> getCommissionItemListByTypeCode(Integer bizId) {
	 return 	dicBiz.getListByTypeCode(BasicConstants.YJ_XMLX, bizId);
	}

	@Override
	public DicListResult getDicList(Integer bizId) {
		DicListResult result = new DicListResult();
		List<DicInfo> commissionTypes = dicBiz.getListByTypeCode(BasicConstants.YJ_XMLX, bizId);
		result.setCommissionTypes(commissionTypes);
		List<DicInfo> bankList = dicBiz.getListByTypeCode(BasicConstants.SUPPLIER_BANK);
		result.setBankList(bankList);
		List<DicInfo> payChannels = dicBiz.getListByTypeCode(BasicConstants.CW_ZFFS);
		result.setPayChannels(payChannels);
		List<SysBizBankAccount> bizAccounts = sysBizBankAccountBiz.getListByBizId(bizId);
		result.setBizAccountList(bizAccounts);
		return result;
	}

	@Override
	public List<DicInfo> getCouponListByTypeCode(Integer bizId) {
		List<DicInfo> dicInfos = dicBiz.getListByTypeCode(BasicConstants.LD_DJLX, bizId);
		return dicInfos;
	}

	@Override
	public List<DicInfo> getHotelLevelListByTypeCode() {
		List<DicInfo> hotelLevels = dicBiz.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		return hotelLevels;
	}

	@Override
	public List<DicInfo> getHotelTypeListByTypeCode() {
		List<DicInfo> hotelTypes = dicBiz.getListByTypeCode(Constants.HOTEL_TYPE_CODE_1);
		return hotelTypes;
	}

	@Override
	public List<DicInfo> getSettleWayListByTypeCode(Integer bizId) {
		List<DicInfo> settleWays = dicBiz.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
		return settleWays;
	}

	@Override
	public List<DicInfo> getEatListByTypeCode() {
		List<DicInfo> eatTypes = dicBiz.getListByTypeCode(Constants.RESTAURANT_TYPE_CODE);
		return eatTypes;
	}

	@Override
	public List<DicInfo> getGolfListByTypeCode() {
		List<DicInfo> golfTypes = dicBiz.getListByTypeCode(Constants.GOLF_TYPE_CODE);
		return golfTypes;
	}

	@Override
	public List<DicInfo> getOtherListByTypeCode() {
		List<DicInfo> otherTypes = dicBiz.getListByTypeCode(Constants.OTHER_TYPE_CODE);
		return otherTypes;
	}

	@Override
	public List<DicInfo> getAirticketListByTypeCode() {
		List<DicInfo> airTicketTypes = dicBiz.getListByTypeCode(Constants.AIRTICKET_TYPE_CODE);
		return airTicketTypes;
	}

	@Override
	public List<DicInfo> getTrainTicketListByTypeCode() {
		List<DicInfo> trainTicketTypes = dicBiz.getListByTypeCode(Constants.TRAINTICKET_TYPE_CODE);
		return trainTicketTypes;
	}

	@Override
	public List<DicInfo> getInsuranceListByTypeCode() {
		List<DicInfo> insuranceTypes = dicBiz.getListByTypeCode(Constants.INSURANCE_TYPE_CODE);
		return insuranceTypes;
	}

	

	@Override
	public List<DicInfo> getEntainmentListByTypeCode() {
		List<DicInfo> entainmentTypes = dicBiz.getListByTypeCode(Constants.ENTERTAINMENT_TYPE_CODE);
		return entainmentTypes;
	}

	@Override
	public List<DicInfo> getIncomeTypeListByTypeCode(Integer bizId) {
		List<DicInfo> cashTypes = dicBiz.getListByTypeCode(BasicConstants.QTSR_JSFS, bizId);
		return cashTypes;
	}

	@Override
	public List<DicInfo> getIncomeTypeListByTypeCode() {
		List<DicInfo> incomeTypes = dicBiz.getListByTypeCode(BasicConstants.GYS_QT_SR);
		return incomeTypes;
	}

	@Override
	public List<DicInfo> getGuideListByTypeCode() {
		List<DicInfo> guideTypes = dicBiz.getListByTypeCode(Constants.GUIDE_TYPE_CODE);
		return guideTypes;
	}

	@Override
	public List<DicInfo> getInsuranceItemsByTypeCode() {
		List<DicInfo> insuranceTypes = dicBiz.getListByTypeCode(Constants.GYS_BX_XM);
		return insuranceTypes;
	}

	@Override
	public List<DicInfo> getTrainTicketTypesByTypeCode() {
		List<DicInfo> trainTypes = dicBiz.getListByTypeCode(Constants.GYS_HCP_LB);
		return trainTypes;
	}

	@Override
	public List<DicInfo> getAirTicketTypesByTypeCode() {
		List<DicInfo> airTypes = dicBiz.getListByTypeCode(Constants.GYS_JP_LB);
		return airTypes;
	}

	@Override
	public List<DicInfo> getFeeItemsByTypeCode(Integer bizId) {
		List<DicInfo> extraTypeList = dicBiz.getListByTypeCode(BasicConstants.GGXX_LYSFXM,bizId);
		return extraTypeList;
	}

	@Override
	public List<DicInfo> getCertificateTypesByTypeCode() {
		List<DicInfo> zjlxList = dicBiz.getListByTypeCode(BasicConstants.GYXX_ZJLX);
		return zjlxList;
	}

	@Override
	public List<DicInfo> getGuestSourcesByTypeCode(Integer bizId) {
		List<DicInfo> guestSourceList = dicBiz.getListByTypeCode(BasicConstants.GYXX_GUESTSOURCE,bizId);
		return guestSourceList;
	}

	@Override
	public DicInfo getAdultFeeItems(Integer bizId) {
		DicInfo dicInfoCR = dicBiz.getDicInfoByTypeCodeAndDicCode(
				bizId, BasicConstants.GYXX_LYSFXM,
				BasicConstants.CR);
		return dicInfoCR;
	}

	@Override
	public DicInfo getEatFeeItems(Integer bizId) {
		DicInfo dicInfoET = dicBiz.getDicInfoByTypeCodeAndDicCode(
				bizId, BasicConstants.GYXX_LYSFXM,
				BasicConstants.ERT);
		return dicInfoET;
	}

	@Override
	public List<DicInfo> getGuestSourceTypes(Integer bizId) {
		List<DicInfo> sourceTypeList = dicBiz.getListByTypeCode(BasicConstants.GYXX_AGENCY_SOURCE_TYPE,bizId);
		return sourceTypeList;
	}

	@Override
	public List<DicInfo> getFeeItems2(Integer bizId) {
		List<DicInfo> lysfxmList = dicBiz.getListByTypeCode(BasicConstants.GYXX_LYSFXM, bizId);
		return lysfxmList;
	}

	@Override
	public List<DicInfo> getTeamTypesByTypeCode(Integer bizId) {
		List<DicInfo> typeList = dicBiz				
				.getListByTypeCode(BasicConstants.SALES_TEAM_TYPE,bizId);	
		return typeList;
	}

}
