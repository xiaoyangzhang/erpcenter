package com.yimayhd.erpcenter.common.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.erpcenter.common.solr.SolrClientInitManager;

public  class BaseSolrQueryManager {
	 private static final Logger LOGGER = LoggerFactory.getLogger(BaseSolrQueryManager.class);
	 
	 private SolrClientInitManager solrClientManager;
	
     public QueryResponse querySolrDataByFilters(String collectionName, SolrQuery solrQuery) {
        try {
            SolrClient solrClient = solrClientManager.getClient();
            
            QueryResponse response = solrClient.query(collectionName, solrQuery, METHOD.POST);
           
            return response;
            
        } catch (Exception e) {
        	LOGGER.error("querySolrDataByFilters collection:{}, par:{} error:{}", collectionName,JSONObject.toJSONString(solrQuery), e);
        }
        
        return null;   
     }

     
	public SolrClientInitManager getSolrClientManager() {
		return solrClientManager;
	}

	public void setSolrClientManager(SolrClientInitManager solrClientManager) {
		this.solrClientManager = solrClientManager;
	}
     
     
}
