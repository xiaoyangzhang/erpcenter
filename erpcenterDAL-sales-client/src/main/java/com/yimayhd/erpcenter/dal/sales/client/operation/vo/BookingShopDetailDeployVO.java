package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class BookingShopDetailDeployVO implements Serializable{
   private List<BookingShopDetailDeploy> detail;

public List<BookingShopDetailDeploy> getDetail() {
	return detail;
}

public void setDetail(List<BookingShopDetailDeploy> detail) {
	this.detail = detail;
}

   
   
}