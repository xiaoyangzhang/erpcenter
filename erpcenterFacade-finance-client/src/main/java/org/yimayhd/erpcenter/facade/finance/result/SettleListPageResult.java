/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.Map;

import com.yihg.mybatis.utility.PageBean;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class SettleListPageResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private PageBean pageBean; 
	private Map<Integer, String> guideMap; 
	private Map sumMap;
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public Map<Integer, String> getGuideMap() {
		return guideMap;
	}
	public void setGuideMap(Map<Integer, String> guideMap) {
		this.guideMap = guideMap;
	}
	public Map getSumMap() {
		return sumMap;
	}
	public void setSumMap(Map sumMap) {
		this.sumMap = sumMap;
	}
	
	
}
