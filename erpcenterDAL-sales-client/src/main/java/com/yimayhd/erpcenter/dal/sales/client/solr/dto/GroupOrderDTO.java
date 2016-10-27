package com.yimayhd.erpcenter.dal.sales.client.solr.dto;

import java.io.Serializable;

public class GroupOrderDTO implements Serializable{



	   private Integer goId ;//主键--------
	   private Integer goBizId ;//----------
	   private Integer goGroupId ;//'散客订单并团后才有',----------
	   private Integer goOrderType;//'0散客订单 1定制订单 -1一地散  -2初始订单',-------------
	   private String  goSupplierName ;// '组团社名称',-----------
	   private Integer goSupplierId ;//'组团社id',---------------
	   private Integer goOrderNoSort ;//------------------
	   private String  goOrderNo ;//--------------
	   private String goSupplierCode ;//
	   private String goContactName ;//'选择后直接带入姓名电话等，可以修改，不关联id',
	   private Integer goOperatorId ;//'当前登录人员',----------
	   private String goOperatorName ;// '当前登录人员',
	   private Integer goSaleOperatorId ;//'销售员id',---------------
	   private String goSaleOperatorName ;// '销售员名称',
	   private Integer goNumAdult ;//'人数：成人',
	   private Integer goNumChild ;// '人数：儿童',
	   private Integer goNumGuide ;// DEFAULT '0' COMMENT '人数：导演',
	   private Integer goNumChildBaby ;//DEFAULT '0' COMMENT '婴儿',
	   private Long goDepartureDate ;//'订单出发日期',
	   private Integer goProductId ;// '产品id',
	   private Integer goProductBrandId ;// '产品品牌id',
	   private String goProductBrandName ;//
	   private String goProductName ;//
	   private String goProductShortName ;// '产品简称',
	   private String goReceiveMode ;//'客人接站牌',--------
	   private Integer goCreatorId ;//-----------
	   private String goCreatorName ;//
	   private Integer goCreateTime ;//----------
	   private Integer goState ;//'订单状态 1正常 -1删除',---------
	   private Double goTotal ;//decimal(18,4) DEFAULT '0.0000' '订单金额',-------------
	   private Double goTotalCash ;//decimal(18,4) DEFAULT '0.0000' '订单已收款金额',
	   private Integer goPriceId ;//----------
	   private Integer goStateSeal ;//DEFAULT '0'  '封存状态 0未封存 1已封存',------------
	   private String goSourceTypeName ;//'客源类别：同行，直客等；组团版使用agencyFit/toEditFirOrder.htm',
	   private Integer goProvinceId ;//-----------
	   private String goProvinceName ;//
	   private Integer goCityId ;//------------------
	   private String goCityName ;//
	   private Integer goOrderLockState ;//DEFAULT '0' '1是锁单状态，0是解锁状态，默认0',-----------------

	   
	   //tour_group
	   private Integer tourGroupId;//------------
	   private Integer tourGroupMode ;//0时为散客团，>0时为定制团(字典为团类别) -1一地散 -2初始团，',----------
	   private String tourGroupCode ;//'自动生成',--------------
	   private Long tourDateStart ;//,----------
	   private Long tourDateEnd ;//,------------
	   private Long tourCreateTime ;//------------
	   private Integer tourGroupState ;//'0.未确认  1.已确认（待出团，行程中，已离开） 2.作废  3审核 4封存',-------------
	   private String tourProductBrandName ;//----------------
	   private String tourProductName ;//-----------------
	   private String tourOperatorName;//计调-----------
	   private Integer tourOperatorId ;//计调--------------
	   private Integer tourTotalAdult;//成人
	   private Integer tourTotalChild;//儿童
	   private Integer tourTotalGuide;//导游
	   //group_order_guest
	   private String guestGuestName ;//客人名字---------------
	   
	   //group_order_price
	   private Double priceMode0 ;// '0收入1预算成本sum值',
	   private Double priceMode1 ;//'0收入1预算成本sum值',



}
