/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.Date;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.FinanceBillDetail;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class CheckBillResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private String guideName;
	private String applicant;
	private String appliTime;
	private String apprTime;
	private String veriTime;
	private String appliState;
	private List<FinanceBillDetail> financeBillDetailList;
	private Date nowDate;
	private List<DicInfo> billTypeList;
	private TourGroup tourGroup; 
	
	
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	public String getApprTime() {
		return apprTime;
	}
	public void setApprTime(String apprTime) {
		this.apprTime = apprTime;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getAppliTime() {
		return appliTime;
	}
	public void setAppliTime(String appliTime) {
		this.appliTime = appliTime;
	}
	public List<FinanceBillDetail> getFinanceBillDetailList() {
		return financeBillDetailList;
	}
	public void setFinanceBillDetailList(
			List<FinanceBillDetail> financeBillDetailList) {
		this.financeBillDetailList = financeBillDetailList;
	}
	public Date getNowDate() {
		return nowDate;
	}
	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}
	public List<DicInfo> getBillTypeList() {
		return billTypeList;
	}
	public void setBillTypeList(List<DicInfo> billTypeList) {
		this.billTypeList = billTypeList;
	}
	public String getVeriTime() {
		return veriTime;
	}
	public void setVeriTime(String veriTime) {
		this.veriTime = veriTime;
	}
	public String getAppliState() {
		return appliState;
	}
	public void setAppliState(String appliState) {
		this.appliState = appliState;
	}
	
}
