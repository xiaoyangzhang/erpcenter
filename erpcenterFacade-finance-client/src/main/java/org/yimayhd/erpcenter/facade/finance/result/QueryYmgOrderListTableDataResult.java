/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpresource.dal.constants.BasicConstants;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class QueryYmgOrderListTableDataResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private List<DicInfo> typeList;
	
	private PageBean<GroupOrder> page;

	public List<DicInfo> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<DicInfo> typeList) {
		this.typeList = typeList;
	}

	public PageBean<GroupOrder> getPage() {
		return page;
	}

	public void setPage(PageBean<GroupOrder> page) {
		this.page = page;
	}
	
	
	
}
