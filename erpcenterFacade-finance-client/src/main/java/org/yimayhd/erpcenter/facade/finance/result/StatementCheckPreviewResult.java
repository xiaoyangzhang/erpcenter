/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.io.Serializable;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;

/**
 * @ClassName: StatementCheckPreviewResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class StatementCheckPreviewResult extends ResultSupport implements Serializable {

	private static final long serialVersionUID = -5895100754940754874L;

	private PageBean pageBean;
	private Map<Integer, String> guideMap;
	
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
}
