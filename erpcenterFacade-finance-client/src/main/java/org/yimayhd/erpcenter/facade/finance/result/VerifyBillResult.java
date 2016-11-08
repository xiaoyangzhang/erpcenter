/**
 * 
 */
package org.yimayhd.erpcenter.facade.finance.result;

import java.util.Date;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.FinanceBillDetail;

/**
 * @ClassName: SettleSealListResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class VerifyBillResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;

	private String guideName;
	private String applicant;
	private Date appliTime;
	private Date apprTime;
	private List<FinanceBillDetail> financeBillDetailList;
	private Date nowDate;
	private List<DicInfo> billTypeList;
	
	
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
	
	public Date getAppliTime() {
		return appliTime;
	}
	public void setAppliTime(Date appliTime) {
		this.appliTime = appliTime;
	}
	public Date getApprTime() {
		return apprTime;
	}
	public void setApprTime(Date apprTime) {
		this.apprTime = apprTime;
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
	
}
