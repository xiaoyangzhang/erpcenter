package com.yimayhd.erpcenter.dal.product.solr.manager;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.erpcenter.common.solr.SolrClientInitManager;

public  class BaseSolrQueryManager {
	 private static final Logger LOGGER = LoggerFactory.getLogger(BaseSolrQueryManager.class);
	 @Autowired
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

}
