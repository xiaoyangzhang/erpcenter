package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.yihg.operation.po.BookingShopDetail;
import com.yihg.operation.po.BookingShopDetailDeploy;

public class BookingShopDetailVO implements Serializable{
   private List<BookingShopDetail> detail;

public List<BookingShopDetail> getDetail() {
	return detail;
}

public void setDetail(List<BookingShopDetail> detail) {
	this.detail = detail;
}

   
   
}