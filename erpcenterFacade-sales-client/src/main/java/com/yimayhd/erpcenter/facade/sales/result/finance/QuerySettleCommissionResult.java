/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.result.finance;

import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class QuerySettleCommissionResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private PageBean pageBean;
	private List<DicInfo> dicInfoList;
	
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<DicInfo> getDicInfoList() {
		return dicInfoList;
	}
	public void setDicInfoList(List<DicInfo> dicInfoList) {
		this.dicInfoList = dicInfoList;
	} 
	
	
}
