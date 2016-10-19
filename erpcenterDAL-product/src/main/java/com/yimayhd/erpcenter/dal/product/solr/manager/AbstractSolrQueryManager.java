package com.yimayhd.erpcenter.dal.product.solr.manager;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public abstract class AbstractSolrQueryManager {
	 private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSolrQueryManager.class);
	
	
     public QueryResponse querySolrDataByFilters(String collectionName, SolrQuery solrQuery) {
        try {
            SolrClient solrClient = solrClientInitManager.getClient();
            
            QueryResponse response = solrClient.query(collectionName, solrQuery, METHOD.POST);
           
            return response;
            
        } catch (Exception e) {
        	LOGGER.error("querySolrDataByFilters collection:{}, par:{} error:{}", collectionName,JSONObject.toJSONString(solrQuery), e);
        }
        
        return null;   
     }
     
     public 
}
