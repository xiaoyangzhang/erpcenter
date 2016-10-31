package com.yimayhd.erpcenter.dal.sales.solr.sales.manage;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.common.solr.BaseSolrQueryManager;
import com.yimayhd.erpcenter.common.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.sales.client.solr.dto.GroupOrderDTO;
import com.yimayhd.erpcenter.dal.sales.client.solr.query.GroupOrderPageQueryDTO;
import com.yimayhd.erpcenter.dal.sales.constants.SalesCollectionEnum;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.SaleOrderConverter;


public class SalesSolrQueryManage  {
	
	@Autowired
	private BaseSolrQueryManager solrQueryManager;

	public SolrSearchPageDTO<GroupOrderDTO> searchSalesOrder(GroupOrderPageQueryDTO  queryDTO){
	    
		 SolrSearchPageDTO<GroupOrderDTO> pageResult = new SolrSearchPageDTO<GroupOrderDTO>();
	

		 SolrQuery solrQuery = SaleOrderConverter.queryDTO2SolrQuery(queryDTO);
	     
        QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_GROUPORDER.getCollection(), solrQuery);
        List<GroupOrderDTO> dtoList = response.getBeans(GroupOrderDTO.class);
        
        pageResult.setList(dtoList);
        pageResult.setPageNo(queryDTO.getPageNo());
        pageResult.setPageSize(queryDTO.getPageSize());
        pageResult.setTotalCount(response.getResults().getNumFound());
        
		return pageResult;
}
}
