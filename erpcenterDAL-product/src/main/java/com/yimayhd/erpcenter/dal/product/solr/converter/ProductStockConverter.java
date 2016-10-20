package com.yimayhd.erpcenter.dal.product.solr.converter;

import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrInputDocument;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.dto.ProductStateDTO;
import com.yimayhd.erpcenter.dal.product.dto.ProductStockDTO;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.query.ProductStatePageQueryDTO;
import com.yimayhd.erpcenter.dal.product.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.product.solr.util.ParamCheckUtil;

public class ProductStockConverter {


	public static SolrInputDocument productStateListConverter(ProductStockDTO productStockDTO) {
		if (null == productStockDTO) {
			return null;
		}

		SolrInputDocument doc = new SolrInputDocument();
		
		doc.addField("infoId", productStockDTO.getInfoId());
		doc.addField("infoBizId", productStockDTO.getInfoBizId());
		doc.addField("infoBrandId", productStockDTO.getInfoBrandId());
		doc.addField("infoBrandName", ParamCheckUtil.checkString(productStockDTO.getInfoBrandName()));
		doc.addField("infoNameCity", ParamCheckUtil.checkString(productStockDTO.getInfoNameCity()));
		doc.addField("infoState", productStockDTO.getInfoState());
		doc.addField("prProductId", productStockDTO.getPrProductId());
		doc.addField("psItemDate", productStockDTO.getPsItemDate());
		doc.addField("psStockCount", productStockDTO.getPsStockCount());
		doc.addField("psReceiveCount", productStockDTO.getPsReceiveCount());
		doc.addField("psState", productStockDTO.getPsState());
		doc.addField("prOrgId", productStockDTO.getPrOrgId());

		return doc;
	}

	/**
	 * 
	 * @param pageBean
	 * @param parameters
	 * @return
	 */
	public static ProductStatePageQueryDTO toQueryDTO(PageBean<ProductInfo> pageBean, Map parameters) {
		return null;
	}

	/**
	 * 
	 * @param solrPageResult
	 * @return
	 */
	public static PageBean<ProductInfo> dto2PageBean(SolrSearchPageDTO<ProductStateDTO> solrPageResult) {
		return null;
	}

	/**
	 * 
	 * @param queryDTO
	 * @return
	 */
	public static SolrQuery queryDTO2SolrQuery(ProductStatePageQueryDTO queryDTO) {

		SolrQuery solrQuery = new SolrQuery();
		if (queryDTO.getId() != null) {
			solrQuery.addFilterQuery("id:" + queryDTO.getId());
		}

		solrQuery.setStart(queryDTO.getStartRow());
		solrQuery.setRows(queryDTO.getOldPageSize());

		return solrQuery;
	}

}
