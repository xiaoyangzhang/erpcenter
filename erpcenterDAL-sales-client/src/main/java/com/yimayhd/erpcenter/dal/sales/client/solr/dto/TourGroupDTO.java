package com.yimayhd.erpcenter.dal.sales.client.solr.dto;

import java.io.Serializable;

public class TourGroupDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3898684074431147626L;
	
	/**
	 * 团id
	 */
	private Integer tgGroupId;
	
	/**
	 * 团编号
	 */
	private String  tgGroupCode;
	
	/**
	 * 0时为散客团，>0时为定制团(字典为团类别) -1一地散 -2初始团
	 */
    private Integer tgGroupMode;
    
    /**
     * 供应商名称
     */
    private String gOsupplierName;
    
    
    
    
	tg.date_start,
	tg.product_brand_name,
	tg.product_name,
	tg.total_adult,
	tg.total_child,
	tg.total_guide,
	tg.operator_name,
	tg.group_state,
	tg.create_time,
	tg.operator_id

}
