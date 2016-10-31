/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpresource.dal.po.SupplierGuide;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class ToAddCommission2Result extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private List<FinanceCommission> comList;
	private List<BookingGuide> guideList;
	private List<DicInfo> dicInfoList;
	private List<DicInfo> dicInfoList2;
	
	private TourGroup tourGroup;
	private List<FinanceCommission> financeCommissions;
	private List<FinanceCommission> financeCommissionDeductions;
	private SupplierGuide supplierGuide;
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
	public List<DicInfo> getDicInfoList2() {
		return dicInfoList2;
	}
	public void setDicInfoList2(List<DicInfo> dicInfoList2) {
		this.dicInfoList2 = dicInfoList2;
	}
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	public List<FinanceCommission> getFinanceCommissions() {
		return financeCommissions;
	}
	public void setFinanceCommissions(List<FinanceCommission> financeCommissions) {
		this.financeCommissions = financeCommissions;
	}
	public List<FinanceCommission> getFinanceCommissionDeductions() {
		return financeCommissionDeductions;
	}
	public void setFinanceCommissionDeductions(
			List<FinanceCommission> financeCommissionDeductions) {
		this.financeCommissionDeductions = financeCommissionDeductions;
	}
	public SupplierGuide getSupplierGuide() {
		return supplierGuide;
	}
	public void setSupplierGuide(SupplierGuide supplierGuide) {
		this.supplierGuide = supplierGuide;
	}
	
	
	
}
