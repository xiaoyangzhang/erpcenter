/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

/**
 * @ClassName: InRecordResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class InRecordResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private PageBean<Map> pageBean;
	private Map<String, Integer> pageTotal;
	private Map total;
	private List<DicInfo> billTypeList;
	
	public PageBean<Map> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<Map> pageBean) {
		this.pageBean = pageBean;
	}
	public Map<String, Integer> getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Map<String, Integer> pageTotal) {
		this.pageTotal = pageTotal;
	}
	public Map getTotal() {
		return total;
	}
	public void setTotal(Map total) {
		this.total = total;
	}
	public List<DicInfo> getBillTypeList() {
		return billTypeList;
	}
	public void setBillTypeList(List<DicInfo> billTypeList) {
		this.billTypeList = billTypeList;
	}
	
}
