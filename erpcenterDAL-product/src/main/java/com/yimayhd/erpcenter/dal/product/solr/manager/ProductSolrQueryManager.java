package com.yimayhd.erpcenter.dal.product.solr.manager;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.yimayhd.erpcenter.common.solr.BaseSolrQueryManager;
import com.yimayhd.erpcenter.dal.product.constants.ProductCollectionEnum;
import com.yimayhd.erpcenter.dal.product.dto.ProductStateDTO;
import com.yimayhd.erpcenter.dal.product.query.ProductStatePageQueryDTO;
import com.yimayhd.erpcenter.dal.product.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.product.solr.converter.ProductStateConverter;

public class ProductSolrQueryManager extends BaseSolrQueryManager{
	
	public SolrSearchPageDTO<ProductStateDTO> searchProductState(ProductStatePageQueryDTO queryDTO){
		    
			 SolrSearchPageDTO<ProductStateDTO> pageResult = new SolrSearchPageDTO<ProductStateDTO>();
		
			 SolrQuery solrQuery = ProductStateConverter.queryDTO2SolrQuery(queryDTO);
		     
             QueryResponse response =  this.querySolrDataByFilters(ProductCollectionEnum.PRODUCT_STATE.getCollection(), solrQuery);
             List<ProductStateDTO> dtoList = response.getBeans(ProductStateDTO.class);
             
             pageResult.setList(dtoList);
             
			return pageResult;
	}
}
