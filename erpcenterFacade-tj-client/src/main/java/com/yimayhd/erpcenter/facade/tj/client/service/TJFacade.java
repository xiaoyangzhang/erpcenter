package com.yimayhd.erpcenter.facade.tj.client.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.tj.client.query.TjSearchDTO;
import com.yimayhd.erpcenter.facade.tj.client.result.SelectShopProjectListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.SelectTJGroupShopListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ShopProjectResult;
import com.yimayhd.erpcenter.facade.tj.client.result.TjSearchResult;

/**
 * 财务统计
 * @author wj
 *
 */
public interface TJFacade{
	/**
	 * 购物项目统计
	 * 
	 */
	public TjSearchResult toShopProjectList(int bizId);
	
	public SelectShopProjectListResult selectShopProjectList(TjSearchDTO tjSearchDTO);
	
	/**
	 * 单团购物统计
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public TjSearchResult toGroupShopList(int bizId);
	
	public SelectTJGroupShopListResult SelectTJGroupShopListResult(TjSearchDTO tjSearchDTO);
	
	/**
	 * 跳转到单团购物统计打印页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public PageBean toGroupShopListPrint(TjSearchDTO tjSearchDTO);
	
	public PageBean selectTJGroupListPage(PageBean pageBean);
	
	public List<Map<String, Object>> selectTJShopOfGroup(Map<String,Object> pm);
	
	/**
	 * 按购物店统计
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public TjSearchResult toShopList(int bizId) ;
	
	public SelectTJGroupShopListResult selectShopList(TjSearchDTO tjSearchDTO);
	
	/**
	 * 跳转到购物店统计打印页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public PageBean toShopListPrint(TjSearchDTO tjSearchDTO);
	
	public Set<Integer> getUserIdListByOrgIdList(Set<Integer> set,int bizId);
	
	public PageBean selectTJShopListPage(PageBean pageBean);
	
	/**
	 * 购物项目统计打印页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public SelectShopProjectListResult shopProjectPrint(TjSearchDTO tjSearchDTO) ;
	
	public ShopProjectResult selectShopProject(int bizId,PageBean pageBean);
	
	public String findByOrgPath(String str);
	
}
