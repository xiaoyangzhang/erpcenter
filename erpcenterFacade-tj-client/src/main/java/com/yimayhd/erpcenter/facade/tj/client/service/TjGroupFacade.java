package com.yimayhd.erpcenter.facade.tj.client.service;

import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupProfit;
import com.yimayhd.erpcenter.facade.tj.client.query.TjSearchDTO;
import com.yimayhd.erpcenter.facade.tj.client.result.SelectLineProfitListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.SelectShopProjectListResult;
import com.yimayhd.erpcenter.facade.tj.client.result.TJGroupProfitResult;
import com.yimayhd.erpcenter.facade.tj.client.result.TjSearchResult;
import com.yimayhd.erpcenter.facade.tj.client.result.ToGroupListResult;
/**
 * 财务统计
 * @author wj
 *
 */
public interface TjGroupFacade {
	
	public ToGroupListResult list(TjSearchDTO tjSearchDTO);
	
	public TjSearchResult GroupProfitList(int bizId);

	public ToGroupListResult GroupProfitList_get(TjSearchDTO tjSearchDTO);
	
	public PageBean GroupProfitList_GetData(TjSearchDTO tjSearchDTO);
	
	public TJGroupProfitResult GroupProfitList_PostFooter(TjSearchDTO tjSearchDTO);
	
	public SelectShopProjectListResult toGroupProfitPrint(TjSearchDTO tjSearchDTO);
	
	public PageBean<TJGroupProfit> selectGroupProfitListPageOu(PageBean pageBean,int bizId,String dataUser);
	
	/**
	 * 线路利润统计
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public TjSearchResult toLineProfitList(int bizId) ;
	
	/**
	 * 线路利润统计2
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */             
	public TjSearchResult toLineProfits(int bizId) ;
	
	/**
	 * 查询线路利润
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public SelectLineProfitListResult selectLineProfitList(TjSearchDTO tjSearchDTO);
	

	
	/**
	 * 查询线路利润2
	 * @param request
	 * @param model
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public SelectLineProfitListResult selectLineProfits(TjSearchDTO tjSearchDTO) ;
	
	/**
	 * 线路利润统计打印页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public SelectShopProjectListResult lineProfitPrint(TjSearchDTO tjSearchDTO);
	
	public PageBean selectLineProfitListPage(PageBean pageBean);
	
	public Map<String,Object> selectLineProfitCount(PageBean pageBean);
	
}
