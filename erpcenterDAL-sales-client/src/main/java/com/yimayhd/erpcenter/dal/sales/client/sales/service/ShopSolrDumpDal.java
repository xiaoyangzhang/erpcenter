package com.yimayhd.erpcenter.dal.sales.client.sales.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.ShopItems;

/**
 * 
* @ClassName: ShopSolrDumpDal 
* @Description: 
* @author wangjun
* @date 2016年11月11日 下午2:11:49 
*
 */
public interface ShopSolrDumpDal{
	
	/**
	 * 购物项目统计全量dump查询
	 * @param pageBean
	 * @return
	 * @author wangjun
	 */
	PageBean<ShopItems> selectShopItemsListPage(PageBean<ShopItems> pageBean);
}
