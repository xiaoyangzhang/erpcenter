/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDeliveryOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.FitOrderVO;

/**
 * @ClassName: BookingDeliveryQueryDTO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author hongfei.guo
 * @date 2016年10月26日
 */
public class SaveFitOrderInfoDTO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -2363857675969236868L;
	
	private FitOrderVO fitOrderVO;
	private String bizCode;
	private Integer bizId;
	private String employeeName;
	private Integer employeeId;
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public FitOrderVO getFitOrderVO() {
		return fitOrderVO;
	}
	public void setFitOrderVO(FitOrderVO fitOrderVO) {
		this.fitOrderVO = fitOrderVO;
	}
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
