package com.yimayhd.erpcenter.dal.product.solr.converter;

import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrInputDocument;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.dto.ProductStateDTO;
import com.yimayhd.erpcenter.dal.product.dto.ProductStockDTO;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;
import com.yimayhd.erpcenter.dal.product.query.ProductStatePageQueryDTO;
import com.yimayhd.erpcenter.dal.product.query.ProductStockPageQueryDTO;
import com.yimayhd.erpcenter.dal.product.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.product.solr.util.ParamCheckUtil;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticCondition;
import com.yimayhd.erpcenter.dal.product.vo.StockStaticsResultVOPlus;

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
	public static ProductStockPageQueryDTO toQueryDTO(PageBean<StockStaticCondition> pageBean) {
		ProductStockPageQueryDTO dto=new ProductStockPageQueryDTO();
		StockStaticCondition stock=(StockStaticCondition) pageBean.getParameter();
//		dto.setInfoBizId(info.getBizId());
//		dto.setInfoBrandId(info.getBrandId());
//		dto.setInfoBrandName(info.getBrandName());
//		dto.setInfoCode(info.getCode());
//		dto.setInfoId(info.getId());
//		dto.setInfoNameCity(info.getNameCity());
//		//dto.setInfoOperatorId(info.getOperatorIds());//传过来的是字符串，solr里是int，条件是in
//		dto.setInfoOperatorIds(info.getOperatorIds());
//		dto.setInfoState(info.getState());//状态是固定值
//		dto.setPrOrgId(Integer.parseInt((String) parameters.get("orgId")));
//		dto.setPrProductId(info.getId());
		return dto;
	}

	/**
	 * 
	 * @param solrPageResult
	 * @return
	 */
	public static PageBean<StockStaticsResultVOPlus> dto2PageBean(SolrSearchPageDTO<ProductStockDTO> solrPageResult) {
		return null;
	}

	/**
	 * 
	 * @param queryDTO
	 * @return
	 */
	public static SolrQuery queryDTO2SolrQuery(ProductStockPageQueryDTO queryDTO) {

		SolrQuery solrQuery = new SolrQuery();
//		if (queryDTO.getId() != null) {
//			solrQuery.addFilterQuery("id:" + queryDTO.getId());
//		}

		solrQuery.setStart(queryDTO.getStartRow());
		solrQuery.setRows(queryDTO.getOldPageSize());

		return solrQuery;
	}

}
