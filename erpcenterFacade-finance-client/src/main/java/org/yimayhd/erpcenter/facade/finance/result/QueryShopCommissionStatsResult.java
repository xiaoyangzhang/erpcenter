/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class QueryShopCommissionStatsResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private PageBean pageBean; 
	private List<DicInfo> dicInfoList;  
	private Map<String, Object> sums;
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
	public Map<String, Object> getSums() {
		return sums;
	}
	public void setSums(Map<String, Object> sums) {
		this.sums = sums;
	}	
	
	
}
