package com.yimayhd.erpcenter.dal.product.solr.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.util.CollectionUtils;

import com.yimayhd.erpcenter.common.solr.BaseSolrQueryManager;
import com.yimayhd.erpcenter.common.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.product.constants.ProductCollectionEnum;
import com.yimayhd.erpcenter.dal.product.dto.ProductStateDTO;
import com.yimayhd.erpcenter.dal.product.dto.ProductStockDTO;
import com.yimayhd.erpcenter.dal.product.query.ProductStatePageQueryDTO;
import com.yimayhd.erpcenter.dal.product.query.ProductStockPageQueryDTO;
import com.yimayhd.erpcenter.dal.product.solr.converter.ProductStateConverter;
import com.yimayhd.erpcenter.dal.product.solr.converter.ProductStockConverter;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultVOPlus;


public class ProductSolrQueryManager extends BaseSolrQueryManager{
	
	public SolrSearchPageDTO<ProductStateDTO> searchProductState(ProductStatePageQueryDTO queryDTO){
		    
			 SolrSearchPageDTO<ProductStateDTO> pageResult = new SolrSearchPageDTO<ProductStateDTO>();
		

			 SolrQuery solrQuery = ProductStateConverter.queryDTO2SolrQuery(queryDTO);
		     
             QueryResponse response =  this.querySolrDataByFilters(ProductCollectionEnum.PRODUCT_STATE.getCollection(), solrQuery);
             List<ProductStateDTO> dtoList = response.getBeans(ProductStateDTO.class);
             
             pageResult.setList(dtoList);
             pageResult.setPageNo(queryDTO.getPageNo());
             pageResult.setPageSize(queryDTO.getPageSize());
             pageResult.setTotalCount(response.getResults().getNumFound());
             
			return pageResult;
	}
	
	
	public SolrSearchPageDTO<StockStaticsResultVOPlus> searchProductStock(ProductStockPageQueryDTO queryDTO){
	    
		 SolrSearchPageDTO<StockStaticsResultVOPlus> pageResult = new SolrSearchPageDTO<StockStaticsResultVOPlus>();
	
		 SolrQuery solrQuery = ProductStockConverter.queryDTO2SolrQuery(queryDTO);
	     
        QueryResponse response =  this.querySolrDataByFilters(ProductCollectionEnum.PRODUCT_STOCK.getCollection(), solrQuery);
        
        List<StockStaticsResultVOPlus> dtoList =new ArrayList<StockStaticsResultVOPlus>();
        
        List<GroupCommand> groupCommandList =  response.getGroupResponse().getValues();
        if(!CollectionUtils.isEmpty(groupCommandList)){
        	for(GroupCommand groupCommand : groupCommandList){
        		List<Group> groupList = groupCommand.getValues();
        		for(Group group : groupList){
        			SolrDocumentList doclist = group.getResult();
        			dtoList.add(ProductStockConverter.docList2PageBean(doclist));
        			
        		}
        	}
        }
        pageResult.setList(dtoList);
        pageResult.setPageNo(queryDTO.getPageNo());
        pageResult.setPageSize(queryDTO.getPageSize());
		return pageResult;
} 

}
