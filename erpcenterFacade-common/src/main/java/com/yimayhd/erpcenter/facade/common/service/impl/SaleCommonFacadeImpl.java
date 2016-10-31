
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

}
