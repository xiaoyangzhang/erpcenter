package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;



public class BookingShopDetailVO implements Serializable{
   private List<BookingShopDetail> detail;

public List<BookingShopDetail> getDetail() {
	return detail;
}

public void setDetail(List<BookingShopDetail> detail) {
	this.detail = detail;
}

   
   
}