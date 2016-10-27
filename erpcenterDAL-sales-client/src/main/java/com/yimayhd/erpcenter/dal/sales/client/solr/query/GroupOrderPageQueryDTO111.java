package com.yimayhd.erpcenter.dal.sales.client.solr.query;

import java.math.BigDecimal;

public class GroupOrderPageQueryDTO111 {
	  private Integer goId ;//
	  private Integer goBizId ;//
	  private Integer goGroupId ;//'散客订单并团后才有',
	  private Integer goOrderType;//'0散客订单 1定制订单 -1一地散  -2初始订单',
	  private String  goSupplierName ;// '组团社名称',
	  private Integer goSupplierId ;//'组团社id',
	  private Integer goOrderNoSort ;//
	  private String  goOrderNo ;//
	  private String goSupplierCode ;//
	  private String goContactName ;//'选择后直接带入姓名电话等，可以修改，不关联id',
	  private String goContactTel ;//
	  private String goContactMobile ;//
	  private String goContactFax ;//
	  private Integer goOperatorId ;//'当前登录人员',
	  private String goOperatorName ;// '当前登录人员',
	  private Integer goSaleOperatorId ;//'销售员id',
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
	  private String goReceiveMode ;//'客人接站牌',
	  private String goServiceStandard ;//text,
	  private String goRemark ;//text,
	  private String goRemarkInternal ;//text,
	  private Integer goCreatorId ;//
	  private String goCreatorName ;//
	  private Integer goCreateTime ;//
	  private Integer goState ;//'订单状态 1正常 -1删除',
	  private Integer goStateFinance ;//DEFAULT '0' COMMENT '财务状态(0未审核、1已审核)',
	  private BigDecimal goTotal ;//decimal(18,4) DEFAULT '0.0000' '订单金额',
	  private BigDecimal goTotalCash ;//decimal(18,4) DEFAULT '0.0000' '订单已收款金额',
	  private String goAuditUser ;// DEFAULT NULL  '审核人',
	  private Integer goAuditUserId ;//DEFAULT NULL  '审核人id',
	  private Integer goAuditTime ;//DEFAULT NULL '审核时间',
	  private Integer goPriceId ;//
	  private String goOperateLog ;//text,
	  private Integer goStateSeal ;//DEFAULT '0'  '封存状态 0未封存 1已封存',
	  private String goSealUser ;// '封存人',
	  private Integer goSealUserId ;//'封存人id',
	  private Integer goSealTime ;//'封存时间',
	  private Integer goSourceTypeId ;//'客源类别：同行，直客等；组团版使用agencyFit/toEditFirOrder.htm',
	  private String goSourceTypeName ;//'客源类别：同行，直客等；组团版使用agencyFit/toEditFirOrder.htm',
	  private Integer goProvinceId ;//
	  private String goProvinceName ;//
	  private Integer goCityId ;//
	  private String goCityName ;//
	  private Integer goOrderLockState ;//DEFAULT '0' '1是锁单状态，0是解锁状态，默认0',
	  private Integer goType ;//'组团版使用：１正常订单,０预留订单　',
	  private String goContractNo ;//'合同号：组团版功能使用',
	  private Integer goGuestSourceId ;// '客人来源id：yihg_erp_basic->sys_dic 组团版功能使用',
	  private String goGuestSourceName ;// '客人来源名称 组团版使用agencyFit/toEditFirOrder.htm',
	  private String goOrderBusiness ;//'是否选择库存，"stock"为按库存导入产品',
	  private String goAiyouGroupId ;//'爱游订单id，来自爱游系统接口',
	  private Integer goB2bExportState ;//'B2B订单导出状态 0-未导出 1-导出',
	  private Integer goExtResId ;//'扩展：大交通资源（yihg_erp_product.traffic_res.id）',
	  private Integer goExtResState ;//'扩展：大交通资源 ；订单状态（0待确认（占位）、1已确认（占位）、2正常取消（退还），3系统执行自动任务取消（清位））',
	  private BigDecimal goExtResPrepay ;//'扩展：大交通资源 ；定金',
	  private Long goExtResCleanTime ;//'扩展：大交通资源 ；系统执行自动任务清退规则（订单确认时限）',
	  private Integer goExtResCleanStock ;// '扩展：大交通资源 ；系统执行自动任务清退规则（库存少于该值的下限）',
	  private Integer goExtResConfirmId ;//'资源业务员id',
	  private String goExtResConfirmName ;//'资源业务员姓名',
	  private Integer goPushOrderId ;//'推送订单ID',
	  private Long goPushTimeUpdate ;//'推送订单更新时间',
	  
	  //tour_group
	  private Integer tourGroupMode ;//0时为散客团，>0时为定制团(字典为团类别) -1一地散 -2初始团，',
	  private String tourGroupCode ;//'自动生成',
	  private Long tourDateStart ;//,
	  private Long tourCreateTime ;//
	  private Integer tourGroupState ;//'0.未确认  1.已确认（待出团，行程中，已离开） 2.作废  3审核 4封存',
	  private String tourProductBrandName ;//
	  private String tourProductName ;//
	  
	  //group_order_guest
	  private String guestName ;//客人名字
	  
	  //group_order_price
	  private BigDecimal priceMode0 ;// '0收入1预算成本sum值',
	  private BigDecimal priceMode1 ;//'0收入1预算成本sum值',
	  

}
