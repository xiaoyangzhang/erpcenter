/**
 * 
 */
package com.yimayhd.erpcenter.facade.sales.query;

import java.io.Serializable;

import com.yimayhd.erpresource.dal.po.SupplierGuide;

/**
 * @ClassName: BookingGuideQueryDTO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月27日
 */
public class BookingGuideQueryDTO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private Integer groupId;
	private Integer bookingGuideId;
	private Integer mGuideId;
	private Integer guideId;
	private Integer bizId;
	private Integer page;
	private Integer pageSize;
	private SupplierGuide supplierGuide;
	private Integer bookingIdLink;
	
	public Integer getBookingIdLink() {
		return bookingIdLink;
	}
	public void setBookingIdLink(Integer bookingIdLink) {
		this.bookingIdLink = bookingIdLink;
	}
	public SupplierGuide getSupplierGuide() {
		return supplierGuide;
	}
	public void setSupplierGuide(SupplierGuide supplierGuide) {
		this.supplierGuide = supplierGuide;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getBookingGuideId() {
		return bookingGuideId;
	}
	public void setBookingGuideId(Integer bookingGuideId) {
		this.bookingGuideId = bookingGuideId;
	}
	public Integer getmGuideId() {
		return mGuideId;
	}
	public void setmGuideId(Integer mGuideId) {
		this.mGuideId = mGuideId;
	}
	public Integer getGuideId() {
		return guideId;
	}
	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}
	
	
}
