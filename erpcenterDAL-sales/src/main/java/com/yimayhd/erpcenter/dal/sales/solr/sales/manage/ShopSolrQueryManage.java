package com.yimayhd.erpcenter.dal.sales.solr.sales.manage;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.FieldStatsInfo;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.common.solr.BaseSolrQueryManager;
import com.yimayhd.erpcenter.common.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.sales.client.solr.dto.ShopItemsDTO;
import com.yimayhd.erpcenter.dal.sales.client.solr.query.ShopItemsPageQueryDTO;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.ShopItems;
import com.yimayhd.erpcenter.dal.sales.constants.SalesCollectionEnum;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.ShopConverter;


public class ShopSolrQueryManage  {
	
	@Autowired
	private BaseSolrQueryManager solrQueryManager;

	/**
	 * 查询购物项目
	 * @param queryDTO
	 * @return
	 * @author wangjun
	 */
	public SolrSearchPageDTO<ShopItemsDTO> searchShopItems(ShopItemsPageQueryDTO queryDTO){
		SolrSearchPageDTO<ShopItemsDTO> pageResult = new SolrSearchPageDTO<ShopItemsDTO>();
		SolrQuery solrQuery = ShopConverter.queryShopItemDTO2SolrQuery(queryDTO,0);
	     
        QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SHOP_ITEMS.getCollection(), solrQuery);
        
        List<ShopItemsDTO> dtoList = response.getBeans(ShopItemsDTO.class);
        
        pageResult.setList(dtoList);
        pageResult.setPageNo(queryDTO.getPageNo());
        pageResult.setPageSize(queryDTO.getPageSize());
        pageResult.setTotalCount(response.getResults().getNumFound());
        
		return pageResult;
	}
	
	/**
	 * 查询购物项目2
	 * @param queryDTO
	 * @return
	 * @author wangjun
	 */
	public ShopItems searchShopItems2(ShopItemsPageQueryDTO queryDTO){
		ShopItems items = new ShopItems();
		SolrQuery solrQuery = ShopConverter.queryShopItemDTO2SolrQuery(queryDTO,1);
	     
        QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SHOP_ITEMS.getCollection(), solrQuery);
        Map<String, FieldStatsInfo> statsMap = response.getFieldStatsInfo();
        FieldStatsInfo bsdBuyTotal = statsMap.get("bsdBuyTotal");
        FieldStatsInfo bsdRepayTotal = statsMap.get("bsdRepayTotal");
        items.setBsdBuyTotal(null != bsdBuyTotal.getSum()?Long.parseLong(bsdBuyTotal.getSum().toString()):0);
        items.setBsdRepayTotal(null != bsdRepayTotal.getSum()?Long.parseLong(bsdRepayTotal.getSum().toString()):0);
		return items;
	}
	
	/**
	 * 查询购物项目-购物店
	 * @param queryDTO
	 * @return
	 * @author wangjun
	 */
	public SolrSearchPageDTO<ShopItemsDTO> searchShop(ShopItemsPageQueryDTO  queryDTO){
		SolrSearchPageDTO<ShopItemsDTO> pageResult = new SolrSearchPageDTO<ShopItemsDTO>();
		SolrQuery solrQuery = ShopConverter.queryShopDTO2SolrQuery(queryDTO);
	     
        QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SHOP_COL.getCollection(), solrQuery);
//        Map<String, FieldStatsInfo> statsMap = response.getFieldStatsInfo();
//        FieldStatsInfo bsdBuyTotal = statsMap.get("bsdBuyTotal");
//        FieldStatsInfo bsdRepayTotal = statsMap.get("bsdRepayTotal");
//        bsdBuyTotal.getSum().toString();
//        bsdRepayTotal.getSum().toString();
        
        List<ShopItemsDTO> dtoList = response.getBeans(ShopItemsDTO.class);
        
        pageResult.setList(dtoList);
        pageResult.setPageNo(queryDTO.getPageNo());
        pageResult.setPageSize(queryDTO.getPageSize());
        pageResult.setTotalCount(response.getResults().getNumFound());
        
		return pageResult;
	}
	
}
