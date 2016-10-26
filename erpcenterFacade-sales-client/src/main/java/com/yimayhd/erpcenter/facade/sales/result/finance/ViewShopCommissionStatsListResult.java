/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.result.finance;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class ViewShopCommissionStatsListResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private List<DicInfo> brand;
	private String projectTypeJsonStr;
	
	public List<DicInfo> getBrand() {
		return brand;
	}
	public void setBrand(List<DicInfo> brand) {
		this.brand = brand;
	}
	public String getProjectTypeJsonStr() {
		return projectTypeJsonStr;
	}
	public void setProjectTypeJsonStr(String projectTypeJsonStr) {
		this.projectTypeJsonStr = projectTypeJsonStr;
	}
}
