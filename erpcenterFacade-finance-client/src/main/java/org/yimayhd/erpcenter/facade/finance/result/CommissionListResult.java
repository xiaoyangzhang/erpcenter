/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class CommissionListResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private List<FinanceCommission> comList;
	private List<BookingGuide> guideList;
	private List<DicInfo> dicInfoList;
	public List<FinanceCommission> getComList() {
		return comList;
	}
	public void setComList(List<FinanceCommission> comList) {
		this.comList = comList;
	}
	public List<BookingGuide> getGuideList() {
		return guideList;
	}
	public void setGuideList(List<BookingGuide> guideList) {
		this.guideList = guideList;
	}
	public List<DicInfo> getDicInfoList() {
		return dicInfoList;
	}
	public void setDicInfoList(List<DicInfo> dicInfoList) {
		this.dicInfoList = dicInfoList;
	}
}
