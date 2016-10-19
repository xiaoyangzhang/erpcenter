package com.yimayhd.erpcenter.dal.product.solr.service.impl;

import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.dal.product.solr.converter.ProductStateConverter;
import com.yimayhd.erpcenter.dal.product.solr.manager.ProductSolrDumpManage;
import com.yimayhd.erpcenter.dal.product.solrdump.domin.ProductStateDO;
import com.yimayhd.erpcenter.dal.product.solrdump.enums.SolrdumpClient;
import com.yimayhd.erpcenter.dal.product.solrdump.service.ProductSolrdumpDal;


public class ProductSolrdumpDalImpl implements ProductSolrdumpDal{

	 private static final Logger logger = LoggerFactory.getLogger(ProductSolrdumpDalImpl.class);
	
	@Autowired
	ProductSolrDumpManage solrdumpManager;
	
	@Override
	public boolean productInfoByIdIntoSolr(ProductStateDO productStateDO) {
		if (null == productStateDO) {
            logger.info("productInfoByIdIntoSolr par:{} is null");
            return false;
        }

        // 数据转换
        SolrInputDocument doc = ProductStateConverter.productStateListConverter(productStateDO);
        // dump productState信息
        return solrdumpManager.dumpDataIntoSolr(SolrdumpClient.HOTEL_LIST, doc);
	}

}
