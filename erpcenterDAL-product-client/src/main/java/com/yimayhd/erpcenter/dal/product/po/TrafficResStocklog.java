package com.yimayhd.erpcenter.dal.product.po;

import java.io.Serializable;
import java.util.Date;

import com.yimayhd.erpcenter.common.util.LogFieldAnno;

public class TrafficResStocklog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 721224443770837650L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_stocklog.id
	 * @mbggenerated
	 */
	@LogFieldAnno(isKey = true)
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_stocklog.res_id
	 * @mbggenerated
	 */
	private Integer resId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_stocklog.order_id
	 * @mbggenerated
	 */
	private Integer orderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_stocklog.adjust_num
	 * @mbggenerated
	 */
	@LogFieldAnno(description="库位变动", delOrIns = true)
	private Integer adjustNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_stocklog.adjust_action
	 * @mbggenerated 调整库存、STOCK_DISABLE调整机动位、ORDER_RESERVE预留订单、ORDER_SOLD全款下单、ORDER_CANCEL取消订单、ORDER_CLEAN系统清位
	 */
	@LogFieldAnno(description="库位类型", delOrIns = true, needExtDescription = "[{\"key\":\"STOCK\", \"value\":\"总库位\"},{\"key\":\"STOCK_DISABLE\", \"value\":\"机动位\"},{\"key\":\"ORDER_RESERVE\", \"value\":\"预留订单\"},{\"key\":\"ORDER_SOLD\", \"value\":\"全款订单\"},{\"key\":\"ORDER_CANCEL\", \"value\":\"取消订单\"},{\"key\":\"ORDER_CLEAN\", \"value\":\"系统清位\"}]")
	private String adjustAction;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_stocklog.adjust_time
	 * @mbggenerated
	 */
	private Date adjustTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_stocklog.remark
	 * @mbggenerated
	 */
	@LogFieldAnno(description="备注", delOrIns = true)
	private String remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_stocklog.user_id
	 * @mbggenerated
	 */
	private Integer userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_stocklog.user_name
	 * @mbggenerated
	 */
	private String userName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_stocklog.time_create
	 * @mbggenerated
	 */
	private Date timeCreate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_stocklog.time_update
	 * @mbggenerated
	 */
	private Date timeUpdate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_stocklog.id
	 * @return  the value of traffic_res_stocklog.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_stocklog.id
	 * @param id  the value for traffic_res_stocklog.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_stocklog.res_id
	 * @return  the value of traffic_res_stocklog.res_id
	 * @mbggenerated
	 */
	public Integer getResId() {
		return resId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_stocklog.res_id
	 * @param resId  the value for traffic_res_stocklog.res_id
	 * @mbggenerated
	 */
	public void setResId(Integer resId) {
		this.resId = resId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_stocklog.order_id
	 * @return  the value of traffic_res_stocklog.order_id
	 * @mbggenerated
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_stocklog.order_id
	 * @param orderId  the value for traffic_res_stocklog.order_id
	 * @mbggenerated
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_stocklog.adjust_num
	 * @return  the value of traffic_res_stocklog.adjust_num
	 * @mbggenerated
	 */
	public Integer getAdjustNum() {
		return adjustNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_stocklog.adjust_num
	 * @param adjustNum  the value for traffic_res_stocklog.adjust_num
	 * @mbggenerated
	 */
	public void setAdjustNum(Integer adjustNum) {
		this.adjustNum = adjustNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_stocklog.adjust_action
	 * @return  the value of traffic_res_stocklog.adjust_action
	 * @mbggenerated
	 */
	public String getAdjustAction() {
		return adjustAction;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_stocklog.adjust_action
	 * @param adjustAction  the value for traffic_res_stocklog.adjust_action
	 * @mbggenerated
	 */
	public void setAdjustAction(String adjustAction) {
		this.adjustAction = adjustAction == null ? null : adjustAction.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_stocklog.adjust_time
	 * @return  the value of traffic_res_stocklog.adjust_time
	 * @mbggenerated
	 */
	public Date getAdjustTime() {
		return adjustTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_stocklog.adjust_time
	 * @param adjustTime  the value for traffic_res_stocklog.adjust_time
	 * @mbggenerated
	 */
	public void setAdjustTime(Date adjustTime) {
		this.adjustTime = adjustTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_stocklog.remark
	 * @return  the value of traffic_res_stocklog.remark
	 * @mbggenerated
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_stocklog.remark
	 * @param remark  the value for traffic_res_stocklog.remark
	 * @mbggenerated
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_stocklog.user_id
	 * @return  the value of traffic_res_stocklog.user_id
	 * @mbggenerated
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_stocklog.user_id
	 * @param userId  the value for traffic_res_stocklog.user_id
	 * @mbggenerated
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_stocklog.user_name
	 * @return  the value of traffic_res_stocklog.user_name
	 * @mbggenerated
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_stocklog.user_name
	 * @param userName  the value for traffic_res_stocklog.user_name
	 * @mbggenerated
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_stocklog.time_create
	 * @return  the value of traffic_res_stocklog.time_create
	 * @mbggenerated
	 */
	public Date getTimeCreate() {
		return timeCreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_stocklog.time_create
	 * @param timeCreate  the value for traffic_res_stocklog.time_create
	 * @mbggenerated
	 */
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_stocklog.time_update
	 * @return  the value of traffic_res_stocklog.time_update
	 * @mbggenerated
	 */
	public Date getTimeUpdate() {
		return timeUpdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_stocklog.time_update
	 * @param timeUpdate  the value for traffic_res_stocklog.time_update
	 * @mbggenerated
	 */
	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}
}