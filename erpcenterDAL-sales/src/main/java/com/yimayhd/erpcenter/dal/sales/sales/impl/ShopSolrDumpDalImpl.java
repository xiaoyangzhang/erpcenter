package com.yimayhd.erpcenter.dal.sales.sales.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.sales.service.ShopSolrDumpDal;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.ShopItems;
import com.yimayhd.erpcenter.dal.sales.tj.dao.TJMapper;


public class ShopSolrDumpDalImpl implements ShopSolrDumpDal{
	@Autowired
	private TJMapper tjMapper;

	@Override
	public PageBean<ShopItems> selectShopItemsListPage(
			PageBean<ShopItems> pageBean) {
		List<ShopItems> result = tjMapper.selectShopItemsListPage(pageBean);
		pageBean.setResult(result);
		return pageBean;
	}
	
}
