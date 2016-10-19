package com.yimayhd.erpcenter.dal.product.solr.manager;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.yimayhd.erpcenter.dal.product.constants.ProductCollectionEnum;
import com.yimayhd.erpcenter.dal.product.dto.ProductStateDTO;
import com.yimayhd.erpcenter.dal.product.query.ProductStatePageQueryDTO;
import com.yimayhd.erpcenter.dal.product.solr.SolrSearchPageDTO;

public class ProductSolrQueryManager extends BaseSolrQueryManager{
	
	public SolrSearchPageDTO<ProductStateDTO> searchProductState(ProductStatePageQueryDTO queryDTO){
		    
			 SolrSearchPageDTO<ProductStateDTO> pageResult = new SolrSearchPageDTO<ProductStateDTO>();
		
		     SolrQuery solrQuery = new SolrQuery();
		     if(queryDTO.getId() != null){
		    	 solrQuery.addFilterQuery("id:" + queryDTO.getId());
		     }
		     
		     solrQuery.setStart(queryDTO.getStartRow());
             solrQuery.setRows(queryDTO.getOldPageSize());
		     
             QueryResponse response =  this.querySolrDataByFilters(ProductCollectionEnum.PRODUCT_STATE.getCollection(), solrQuery);
             List<ProductStateDTO> dtoList = response.getBeans(ProductStateDTO.class);
             
             pageResult.setList(dtoList);
             
			return pageResult;
	}
}
