package com.yimayhd.erpcenter.dal.product.solr.converter;

import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrInputDocument;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.dto.ProductStateDTO;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.query.ProductStatePageQueryDTO;
import com.yimayhd.erpcenter.dal.product.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.product.solr.util.ParamCheckUtil;

public class ProductStateConverter {

	public static SolrInputDocument productStateListConverter(ProductStateDTO productStateDTO) {
		if (null == productStateDTO) {
			return null;
		}

		SolrInputDocument doc = new SolrInputDocument();

		doc.addField("infoId", productStateDTO.getInfoId());
		doc.addField("infoBizId", productStateDTO.getInfoBizId());
		doc.addField("infoCode", ParamCheckUtil.checkString(productStateDTO.getInfoCode()));
		doc.addField("infoType", productStateDTO.getInfoType());
		doc.addField("infoBrandId", productStateDTO.getInfoBrandId());
		doc.addField("infoBrandName", ParamCheckUtil.checkString(productStateDTO.getInfoBrandName()));
		doc.addField("infoNameCity", ParamCheckUtil.checkString(productStateDTO.getInfoNameCity()));
		doc.addField("infoNameBrief", ParamCheckUtil.checkString(productStateDTO.getInfoNameBrief()));
		doc.addField("infoTravelDays", productStateDTO.getInfoTravelDays());
		doc.addField("infoOrderNum", productStateDTO.getInfoOrderNum());
		doc.addField("infoDestProvinceId", productStateDTO.getInfoDestProvinceId());
		doc.addField("infoDestProvinceName", ParamCheckUtil.checkString(productStateDTO.getInfoDestProvinceName()));
		doc.addField("infoDestCityId", productStateDTO.getInfoDestCityId());
		doc.addField("infoDestCityName", ParamCheckUtil.checkString(productStateDTO.getInfoDestCityName()));
		doc.addField("infoState", productStateDTO.getInfoState());
		doc.addField("infoCreatorId", productStateDTO.getInfoCreatorId());
		doc.addField("infoCreatorName", ParamCheckUtil.checkString(productStateDTO.getInfoCreatorName()));
		doc.addField("infoCreateTime", productStateDTO.getInfoCreateTime());
		doc.addField("infoOperatorId", productStateDTO.getInfoOperatorId());
		doc.addField("infoOperatorName", ParamCheckUtil.checkString(productStateDTO.getInfoOperatorName()));
		doc.addField("prOrgId", productStateDTO.getPrOrgId());
		doc.addField("prProductId", productStateDTO.getPrProductId());

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
