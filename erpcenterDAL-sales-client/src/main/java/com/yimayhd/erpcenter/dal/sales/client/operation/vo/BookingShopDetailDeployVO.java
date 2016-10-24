package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.yihg.operation.po.BookingShopDetailDeploy;

public class BookingShopDetailDeployVO implements Serializable{
   private List<BookingShopDetailDeploy> detail;

public List<BookingShopDetailDeploy> getDetail() {
	return detail;
}

public void setDetail(List<BookingShopDetailDeploy> detail) {
	this.detail = detail;
}

   
   
}