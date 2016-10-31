package com.yimayhd.erpcenter.dal.sales.solr.manager;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.FieldStatsInfo;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.common.solr.BaseSolrQueryManager;
import com.yimayhd.erpcenter.common.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.sales.client.dto.TourProfitQueryDTO;
import com.yimayhd.erpcenter.dal.sales.client.dto.TourTotalProfitQueryDTO;
import com.yimayhd.erpcenter.dal.sales.client.solr.dto.TourGroupDTO;
import com.yimayhd.erpcenter.dal.sales.constants.SalesCollectionEnum;
import com.yimayhd.erpcenter.dal.sales.converter.TourGroupConverter;


public class TourGroupSolrQueryManager {
	
	@Autowired
	private BaseSolrQueryManager solrQueryManager;
	
	public SolrSearchPageDTO<TourGroupDTO> searchTourGroupList(TourProfitQueryDTO queryDTO){
		    
			 SolrSearchPageDTO<TourGroupDTO> pageResult = new SolrSearchPageDTO<TourGroupDTO>();
		

			 SolrQuery solrQuery = TourGroupConverter.convert2SolrQuery(queryDTO);
		     
             QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_TOURGROUP.getCollection(), solrQuery);
             
             List<TourGroupDTO> dtoList = response.getBeans(TourGroupDTO.class);
             
             pageResult.setList(dtoList);
             pageResult.setPageNo(queryDTO.getPageNo());
             pageResult.setPageSize(queryDTO.getPageSize());
             pageResult.setTotalCount(response.getResults().getNumFound());
             
			return pageResult;
	}
	
	public TourGroupDTO searchTotalTourGroup(TourTotalProfitQueryDTO queryDTO){
		
		SolrQuery solrQuery = TourGroupConverter.convert2SolrQuery(queryDTO);
	     
        QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_TOURGROUP.getCollection(), solrQuery);
        
        Map<String, FieldStatsInfo> statsMap = response.getFieldStatsInfo();
        FieldStatsInfo opBudgetStatsInfo = statsMap.get("opBudget");
        FieldStatsInfo opIncomeStatInfo = statsMap.get("opIncome");
        
        if(opBudgetStatsInfo != null && opIncomeStatInfo != null){
        	TourGroupDTO tourGroupDTO = new TourGroupDTO();
        	tourGroupDTO.setOpIncome(BigDecimal.valueOf((Double)opIncomeStatInfo.getSum()));
        	tourGroupDTO.setOpBudget(BigDecimal.valueOf((Double)opBudgetStatsInfo.getSum()));
        	
        	return tourGroupDTO;
        }
        
		return null;
	}
	
}
