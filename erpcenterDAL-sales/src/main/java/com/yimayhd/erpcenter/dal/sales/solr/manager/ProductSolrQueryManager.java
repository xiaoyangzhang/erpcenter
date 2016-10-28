package com.yimayhd.erpcenter.dal.sales.solr.manager;


import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.yimayhd.erpcenter.common.solr.BaseSolrQueryManager;
import com.yimayhd.erpcenter.common.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.sales.client.dto.TourProfitQueryDTO;
import com.yimayhd.erpcenter.dal.sales.client.solr.dto.TourGroupDTO;
import com.yimayhd.erpcenter.dal.sales.constants.SalesCollectionEnum;
import com.yimayhd.erpcenter.dal.sales.converter.TourGroupConverter;


public class ProductSolrQueryManager extends BaseSolrQueryManager{
	
	public SolrSearchPageDTO<TourGroupDTO> searchTourGroupList(TourProfitQueryDTO queryDTO){
		    
			 SolrSearchPageDTO<TourGroupDTO> pageResult = new SolrSearchPageDTO<TourGroupDTO>();
		

			 SolrQuery solrQuery = TourGroupConverter.convert2SolrQuery(queryDTO);
		     
             QueryResponse response =  this.querySolrDataByFilters(SalesCollectionEnum.SALES_TOURGROUP.getCollection(), solrQuery);
             
             List<TourGroupDTO> dtoList = response.getBeans(TourGroupDTO.class);
             
             pageResult.setList(dtoList);
             pageResult.setPageNo(queryDTO.getPageNo());
             pageResult.setPageSize(queryDTO.getPageSize());
             pageResult.setTotalCount(response.getResults().getNumFound());
             
			return pageResult;
	}
	


}
