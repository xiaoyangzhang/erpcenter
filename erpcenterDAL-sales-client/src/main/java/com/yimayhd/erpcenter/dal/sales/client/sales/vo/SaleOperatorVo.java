package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;

public class SaleOperatorVo implements Serializable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer groupId;
	private Integer orderType ;
	private String supplierName;
	private String operatorName;
	private Integer type ;
	private String saleOperatorIds;
	private String operatorIds ;
	private String saleOperatorName;
	private Integer numAdult;
	private Integer numChild;
	private Integer numGuide;
	private String startTime;
	private String endTime;
	private String productBrandName;
	private String productName;
	private String remark;
	private Integer pageSize = 15;
	private Integer page = 1;
	private String groupCode;
	private Integer select; // 预算利润查询里面标记操作计调和销售计调
	private String orgNames;
	private String orgIds;
	private String  nums;
	private String  guestNames;
	private String  mobiles;
	private String  cerNums;
	private String hotelLevels;
	private String hotelNums;
	private String  guestName;
	private String  mobile;
	private String upAir ; //省外接机信息
	private String offAir ;//省外送机信息
	private String trans ; //省内交通
	private String mergeGroupState; //并团状态
	
	private Set<Integer> orderIdSet;
	
	public Set<Integer> getOrderIdSet() {
		return orderIdSet;
	}
	public void setOrderIdSet(Set<Integer> orderIdSet) {
		this.orderIdSet = orderIdSet;
	}
	private List<GroupOrderGuest> guests = new ArrayList<GroupOrderGuest>() ;
	
	private String contactName ;
	
	public List<GroupOrderGuest> getGuests() {
		return guests;
	}
	public void setGuests(List<GroupOrderGuest> guests) {
		this.guests = guests;
	}
	public String getOperatorIds() {
		return operatorIds;
	}
	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public String getContactName() {
		return contactName;
	}
	
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public String getSaleOperatorName() {
		return saleOperatorName;
	}
	public void setSaleOperatorName(String saleOperatorName) {
		this.saleOperatorName = saleOperatorName;
	}
	public Integer getNumAdult() {
		return numAdult;
	}
	public void setNumAdult(Integer numAdult) {
		this.numAdult = numAdult;
	}
	public Integer getNumChild() {
		return numChild;
	}
	public void setNumChild(Integer numChild) {
		this.numChild = numChild;
	}
	public Integer getNumGuide() {
		return numGuide;
	}
	public void setNumGuide(Integer numGuide) {
		this.numGuide = numGuide;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public Integer getSelect() {
		return select;
	}
	public void setSelect(Integer select) {
		this.select = select;
	}
	public String getOrgNames() {
		return orgNames;
	}
	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public String getNums() {
		return nums;
	}
	public void setNums(String nums) {
		this.nums = nums;
	}
	public String getGuestNames() {
		if(null!=guestNames){
			this.guestNames = guestNames.replace("@@", "@");
		}
		return guestNames ;
	}
	public void setGuestNames(String guestNames) {
		this.guestNames = guestNames;
	}
	
	public String getMobiles() {
		return mobiles;
	}
	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	public String getCerNums() {
		return cerNums;
	}
	public void setCerNums(String cerNums) {
		this.cerNums = cerNums;
	}
	public String getHotelLevels() {
		return hotelLevels;
	}
	public void setHotelLevels(String hotelLevels) {
		this.hotelLevels = hotelLevels;
	}
	public String getHotelNums() {
		return hotelNums;
	}
	public void setHotelNums(String hotelNums) {
		this.hotelNums = hotelNums;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUpAir() {
		StringBuilder sb = new StringBuilder() ;
		if(null!=upAir && ""!=upAir){
		String[] ups = upAir.split(",") ;
			for (String string : ups) {
				if(null!=string && ""!=string){
					sb.append(string+",") ;
				}
			}
		}
		return sb.toString();
	}
	public void setUpAir(String upAir) {
		this.upAir = upAir;
	}
	public String getOffAir() {
		StringBuilder sb = new StringBuilder() ;
		if(null!=offAir && ""!=offAir){
			String[] offs = offAir.split(",") ;
			for (String string : offs) {
				if(null!=string && ""!=string){
					sb.append(string+",") ;
				}
			}
		}
		return sb.toString();
	}
	public void setOffAir(String offAir) {
		this.offAir = offAir;
	}
	public String getTrans() {
		StringBuilder sb = new StringBuilder() ;
			if(null!=trans && ""!=trans){
			String[] trs = trans.split(",") ;
			for (String string : trs) {
				if(null!=string && ""!=string){
					sb.append(string+",") ;
				}
			}
		}
		return sb.toString();
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getMergeGroupState() {
		return mergeGroupState;
	}
	public void setMergeGroupState(String mergeGroupState) {
		this.mergeGroupState = mergeGroupState;
	}
}
