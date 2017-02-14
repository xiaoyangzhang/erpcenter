/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.math.BigDecimal;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class QueryYmgOrderListByOpTable extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private List<DicInfo> typeList;
	private Integer pageTotalAudit = 0;
	private Integer pageTotalChild = 0;
	private Integer pageTotalGuide = 0;
	private BigDecimal pageTotal;
	private PageBean<GroupOrder> page;
	private Integer totalAdult = 0;
	private Integer totalChild = 0;
	private Integer totalGuide = 0;
	private BigDecimal total;
	public List<DicInfo> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<DicInfo> typeList) {
		this.typeList = typeList;
	}
	public Integer getPageTotalAudit() {
		return pageTotalAudit;
	}
	public void setPageTotalAudit(Integer pageTotalAudit) {
		this.pageTotalAudit = pageTotalAudit;
	}
	public Integer getPageTotalChild() {
		return pageTotalChild;
	}
	public void setPageTotalChild(Integer pageTotalChild) {
		this.pageTotalChild = pageTotalChild;
	}
	public Integer getPageTotalGuide() {
		return pageTotalGuide;
	}
	public void setPageTotalGuide(Integer pageTotalGuide) {
		this.pageTotalGuide = pageTotalGuide;
	}
	public BigDecimal getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(BigDecimal pageTotal) {
		this.pageTotal = pageTotal;
	}
	public PageBean<GroupOrder> getPage() {
		return page;
	}
	public void setPage(PageBean<GroupOrder> page) {
		this.page = page;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Integer getTotalAdult() {
		return totalAdult;
	}
	public void setTotalAdult(Integer totalAdult) {
		this.totalAdult = totalAdult;
	}
	public Integer getTotalChild() {
		return totalChild;
	}
	public void setTotalChild(Integer totalChild) {
		this.totalChild = totalChild;
	}
	public Integer getTotalGuide() {
		return totalGuide;
	}
	public void setTotalGuide(Integer totalGuide) {
		this.totalGuide = totalGuide;
	}	
	
	
	
}
