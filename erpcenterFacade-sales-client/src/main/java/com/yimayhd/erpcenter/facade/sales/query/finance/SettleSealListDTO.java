package com.yimayhd.erpcenter.facade.sales.query.finance;

import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.facade.sales.BaseListPage;


public class SettleSealListDTO extends BaseListPage{
    private static final long serialVersionUID = -5738945525593633404L;
    
    private String sl;
    private String ssl;
	private String rp;  
	private String svc;
	private TourGroupVO group;
	private int bizId;
	private Map<String,Object> pms;
	private Set<Integer> set;
   
}