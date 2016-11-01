/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class SubjectSummaryResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private PageBean pageBean;
	private List<DicInfo> dicInfoList;
	private PageBean pageBeanSum;
	
	public PageBean getPageBeanSum() {
		return pageBeanSum;
	}
	public void setPageBeanSum(PageBean pageBeanSum) {
		this.pageBeanSum = pageBeanSum;
	}
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
