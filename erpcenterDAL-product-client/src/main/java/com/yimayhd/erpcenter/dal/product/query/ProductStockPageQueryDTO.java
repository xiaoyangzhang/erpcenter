package com.yimayhd.erpcenter.dal.product.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.common.query.PageQuery;

public class ProductStockPageQueryDTO extends PageQuery implements Serializable{
	
	private static final long serialVersionUID = 1514932164244446916L;
	
	 private Integer infoId;//产品id------=prProductId
	 private Integer infoBizId;//商家id--------可选
	 private Integer infoBrandId;//名称-品牌--------
	 private String infoNameCity;//名称-城市----------like
	 private Byte infoState;//产品状态,1待上架2上架3下架-1删除--------必须!=-1
	 private Integer prProductId;//pr表产品id
	 private Long psItemDateStart;//ps表时间-------必须
	 private Long psItemDateEnd;//ps表时间-------必须
	 private Integer psState;//ps表状态1正常 -1删除--------必须!=-1
	 private Integer prOrgId;//pr表机构id--------必须
	 
	 
	 

}
