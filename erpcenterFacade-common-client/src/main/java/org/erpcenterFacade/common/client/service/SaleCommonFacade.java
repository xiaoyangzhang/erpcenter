
package org.erpcenterFacade.common.client.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

/**
 * @ClassName: SaleCommonFacade
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月25日
 */
public interface SaleCommonFacade {
	/**
	 * 获取地接社项目集合
	* created by zhangxiaoyang
	* @date 2016年10月25日
	* @Description:
	* @param 
	* @return List<DicInfo>
	* @throws
	 */
	List<DicInfo> getDeliveryItemList(Integer bizId);
	
	List<DicInfo> getTransportListByTypeCode(Integer bizId);
	
	List<DicInfo> getCarListByTypeCode();
	List<DicInfo> getShopListByTypeCode();
}
