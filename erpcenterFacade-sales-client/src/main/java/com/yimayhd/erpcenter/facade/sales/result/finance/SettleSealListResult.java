/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.result.finance;

import java.io.Serializable;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.sales.result.ResultSupport;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class SettleSealListResult extends ResultSupport implements Serializable {

	private static final long serialVersionUID = -5895100754940754874L;

	private Map<Integer, String> guideMap;
	private PageBean pageBean;
	
	public Map<Integer, String> getGuideMap() {
		return guideMap;
	}
	public void setGuideMap(Map<Integer, String> guideMap) {
		this.guideMap = guideMap;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}
