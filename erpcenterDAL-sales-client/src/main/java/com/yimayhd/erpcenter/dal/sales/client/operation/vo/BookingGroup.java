package com.yimayhd.erpcenter.dal.sales.client.operation.vo;


import com.yimayhd.erpcenter.dal.sales.client.operation.po.*;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



public class BookingGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer groupId;

	private String groupCode;
	
	private Date dateStart;
	private Date dateEnd;

	private Integer groupMode;
	private String groupModel;

	private String productName;
	private String productBrandName;
	private Integer supplierId;

	private String supplierName;

	private Integer personCount;
	
	private Integer adultCount;
	private Integer childCount;
	private Integer guideCount;

	private String operatorName;
	private Integer groupState;
	private String groupStatus;

	private Integer count;

	private BigDecimal price;

	private Integer bookingId;
	private Integer orderId;	
	
	private String bookSupplierName;
	private String bookSupplierIds;
	private String driverName;
	private String carLisence;
	
	private Integer supplierType;
	private List<BookingGuide> guideList;

	private List<GroupOrder> groupOrderList;
	private List<BookingSupplierPO> bookingSuppliers;
	private List<BookingSupplierDetail> bookingSupplierDetails;
	private List<BookingDelivery> bookingDeliveries;
	//private Map<String, Integer> personCountMap;
	private BookingShopSelect bookingShopSelect;
	private TourGroup tourGroup;

	private Integer guideId;
	private String guideName;
	private String guideMobile;
	private BigDecimal total;
	private String bookingGuideId;
	private Integer stateBooking;
	private String[] bookSupplierIdArr;
	//private String guideInfo;
	private Integer stateLock;
	
	private Integer pushStatus;

	public String getGuideMobile() {
		return guideMobile;
	}

	public void setGuideMobile(String guideMobile) {
		this.guideMobile = guideMobile;
	}

	public Integer getStateLock() {
		return stateLock;
	}

	public void setStateLock(Integer stateLock) {
		this.stateLock = stateLock;
	}

	public String[] getBookSupplierIdArr() {
		if(!isEmpty(bookSupplierIds)){
			return bookSupplierIds.split(",");
		}
		return bookSupplierIdArr;
	}

	public String getBookSupplierIds() {
		return bookSupplierIds;
	}

	public void setBookSupplierIds(String bookSupplierIds) {
		this.bookSupplierIds = bookSupplierIds;
	}
	public Integer getStateBooking() {
		return stateBooking;
	}

	public void setStateBooking(Integer stateBooking) {
		this.stateBooking = stateBooking;
	}

	public String getBookingGuideId() {
		return bookingGuideId;
	}

	public void setBookingGuideId(String bookingGuideId) {
		this.bookingGuideId = bookingGuideId;
	}

	public Integer getGuideId() {
		return guideId;
	}

	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		tourGroup = tourGroup;
	}

	public BookingShopSelect getBookingShopSelect() {
		return bookingShopSelect;
	}

	public void setBookingShopSelect(BookingShopSelect bookingShopSelect) {
		this.bookingShopSelect = bookingShopSelect;
	}

	/*public Map<String, Integer> getPersonCountMap() {
		return personCountMap;
	}

	public void setPersonCountMap(Map<String, Integer> personCountMap) {
		this.personCountMap = personCountMap;
	}*/

	public List<BookingDelivery> getBookingDeliveries() {
		return bookingDeliveries;
	}

	public void setBookingDeliveries(List<BookingDelivery> bookingDeliveries) {
		this.bookingDeliveries = bookingDeliveries;
	}

	public List<BookingSupplierPO> getBookingSuppliers() {
		return bookingSuppliers;
	}

	public void setBookingSuppliers(List<BookingSupplierPO> bookingSuppliers) {
		this.bookingSuppliers = bookingSuppliers;
	}

	public List<BookingSupplierDetail> getBookingSupplierDetails() {
		return bookingSupplierDetails;
	}

	public void setBookingSupplierDetails(
			List<BookingSupplierDetail> bookingSupplierDetails) {
		this.bookingSupplierDetails = bookingSupplierDetails;
	}

	public List<GroupOrder> getGroupOrderList() {
		return groupOrderList;
	}

	public void setGroupOrderList(List<GroupOrder> groupOrderList) {
		this.groupOrderList = groupOrderList;
	}

	public List<BookingGuide> getGuideList() {
		return guideList;
	}

	public void setGuideList(List<BookingGuide> guideList) {
		this.guideList = guideList;
	}

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public Integer getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public String getGroupModel() {
		return groupModel;
	}

	public void setGroupModel(String groupModel) {
		this.groupModel = groupModel;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getGroupStatus() {
		if(groupState!=null){
			switch(groupState.intValue()){
			case 0:
				return "未确认";
			case 1:
				if(new Date().before(dateStart)){
					return "已确认（未出团）";
				}else{
					if(dateEnd == null){
						return "已确认";
					}if(dateEnd.after(new Date())){
						return "已确认（行程中）";
					}else{
						return "已确认（已离开）";
					}
				}
			case 3:
				return "已审核";
			case 4:
				return "封存";
			default:
				return "已删除";
			}
		}
		return "";
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(Integer adultCount) {
		this.adultCount = adultCount;
	}

	public Integer getChildCount() {
		return childCount;
	}

	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}

	public Integer getGuideCount() {
		return guideCount;
	}

	public void setGuideCount(Integer guideCount) {
		this.guideCount = guideCount;
	}

	public Integer getGroupMode() {
		return groupMode;
	}

	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}

	public String getBookSupplierName() {
		return bookSupplierName;
	}

	public void setBookSupplierName(String bookSupplierName) {
		this.bookSupplierName = bookSupplierName;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getCarLisence() {
		return carLisence;
	}

	public void setCarLisence(String carLisence) {
		this.carLisence = carLisence;
	}

	//计调安排统计功能
	private String hotelNames;
	private String[] hotelNameArr;
	private String hotelIds;
	private String[] hotelIdArr;
	
	private String eatNames;
	private String[] eatNameArr;
	private String eatIds;
	private String[] eatIdArr;
	
	private String sightNames;
	private String[] sightNameArr;
	private String sightIds;
	private String[] sightIdArr;
	
	private String carNames;
	private String[] carNameArr;
	private String carIds;
	private String[] carIdArr;
	
	private String guideNames;
	private String[] guideNameArr;
	private String guideIds;
	private String[] guideIdArr;
	
	private String deliveryNames;
	private String[] deliveryNameArr;
	private String deliveryIds;
	private String[] deliveryIdArr;
	
	private String userNames;
	
	public String getHotelNames() {
		return hotelNames;
	}

	public void setHotelNames(String hotelNames) {
		this.hotelNames = hotelNames;
	}

	public String getHotelIds() {
		return hotelIds;
	}

	public void setHotelIds(String hotelIds) {
		this.hotelIds = hotelIds;
	}

	public String getEatNames() {
		return eatNames;
	}

	public void setEatNames(String eatNames) {
		this.eatNames = eatNames;
	}

	public String getEatIds() {
		return eatIds;
	}

	public void setEatIds(String eatIds) {
		this.eatIds = eatIds;
	}

	public String getSightNames() {
		return sightNames;
	}

	public void setSightNames(String sightNames) {
		this.sightNames = sightNames;
	}

	public String getSightIds() {
		return sightIds;
	}

	public void setSightIds(String sightIds) {
		this.sightIds = sightIds;
	}

	public String getCarNames() {
		return carNames;
	}

	public void setCarNames(String carNames) {
		this.carNames = carNames;
	}

	public String getCarIds() {
		return carIds;
	}

	public void setCarIds(String carIds) {
		this.carIds = carIds;
	}

	public String getGuideNames() {
		return guideNames;
	}

	public void setGuideNames(String guideNames) {
		this.guideNames = guideNames;
	}

	public String getGuideIds() {
		return guideIds;
	}

	public void setGuideIds(String guideIds) {
		this.guideIds = guideIds;
	}

	public String getDeliveryNames() {
		return deliveryNames;
	}

	public void setDeliveryNames(String deliveryNames) {
		this.deliveryNames = deliveryNames;
	}

	public String getDeliveryIds() {
		return deliveryIds;
	}

	public void setDeliveryIds(String deliveryIds) {
		this.deliveryIds = deliveryIds;
	}

	public String[] getHotelNameArr() {
		if(!isEmpty(hotelNames)){
			return hotelNames.split(",");
		}
		return hotelNameArr;
	}

	public String[] getHotelIdArr() {
		if(!isEmpty(hotelIds)){
			return hotelIds.split(",");
		}
		return hotelIdArr;
	}

	public String[] getEatNameArr() {
		if(!isEmpty(eatNames)){
			return eatNames.split(",");
		}
		return eatNameArr;
	}

	public String[] getEatIdArr() {
		if(!isEmpty(eatIds)){
			return eatIds.split(",");
		}
		return eatIdArr;
	}

	public String[] getSightNameArr() {
		if(!isEmpty(sightNames)){
			return sightNames.split(",");
		}
		return sightNameArr;
	}

	public String[] getSightIdArr() {
		if(!isEmpty(sightIds)){
			return sightIds.split(",");
		}
		return sightIdArr;
	}

	public String[] getCarNameArr() {
		if(!isEmpty(carNames)){
			return carNames.split(",");
		}
		return carNameArr;
	}

	public String[] getCarIdArr() {
		if(!isEmpty(carIds)){
			return carIds.split(",");
		}
		return carIdArr;
	}

	public String[] getGuideNameArr() {
		if(!isEmpty(guideNames)){
			return guideNames.split(",");			
		}
		return guideNameArr;
	}

	public String[] getGuideIdArr() {
		if(!isEmpty(guideIds)){
			return guideIds.split(",");
		}
		return guideIdArr;
	}

	public String[] getDeliveryNameArr() {
		if(!isEmpty(deliveryNames)){
			return deliveryNames.split(",");
		}
		return deliveryNameArr;
	}

	public String[] getDeliveryIdArr() {
		if(!isEmpty(deliveryIds)){
			return deliveryIds.split(",");
		}
		return deliveryIdArr;
	}
	
	private boolean isEmpty(String arg){
		return arg==null || arg.length()==0;
	}
	private Integer stateFinance;

	public Integer getStateFinance() {
		return stateFinance;
	}

	public void setStateFinance(Integer stateFinance) {
		this.stateFinance = stateFinance;
	}

	public Integer getGroupState() {
		return groupState;
	}

	public void setGroupState(Integer groupState) {
		this.groupState = groupState;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }
	
}
