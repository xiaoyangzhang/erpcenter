/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ContractQueryDTO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月26日
 */
public class ContractQueryDTO  implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private Integer bizId;
	private Integer supplierId;
	private Date date;
	private Integer type1;
	private Integer type2;
	private String type2Name;
	private int bizCarContractId;
	private Date dateTo;
	private Integer seatCnt;
	private Integer goodsId;
	private List<Date> dateList;
	private Integer groupId;
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public List<Date> getDateList() {
		return dateList;
	}
	public void setDateList(List<Date> dateList) {
		this.dateList = dateList;
	}
	public int getBizCarContractId() {
		return bizCarContractId;
	}
	public void setBizCarContractId(int bizCarContractId) {
		this.bizCarContractId = bizCarContractId;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public Integer getSeatCnt() {
		return seatCnt;
	}
	public void setSeatCnt(Integer seatCnt) {
		this.seatCnt = seatCnt;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getType1() {
		return type1;
	}
	public void setType1(Integer type1) {
		this.type1 = type1;
	}
	public Integer getType2() {
		return type2;
	}
	public void setType2(Integer type2) {
		this.type2 = type2;
	}
	public String getType2Name() {
		return type2Name;
	}
	public void setType2Name(String type2Name) {
		this.type2Name = type2Name;
	}
	
	
}
