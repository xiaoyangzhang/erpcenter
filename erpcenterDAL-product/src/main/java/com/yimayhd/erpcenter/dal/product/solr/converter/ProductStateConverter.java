package com.yimayhd.erpcenter.dal.product.solr.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrInputDocument;


import org.springframework.util.CollectionUtils;

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
		doc.addField("prOrgId", ParamCheckUtil.checkString(productStateDTO.getPrOrgId()));

		return doc;
	}

	/**
	 * 
	 * @param pageBean
	 * @param parameters
	 * @return
	 */
	public static ProductStatePageQueryDTO toQueryDTO(PageBean<ProductInfo> pageBean, Map parameters) {
		//根据pageBean和parameters组合获得dto参数
		ProductStatePageQueryDTO dto=new ProductStatePageQueryDTO();
		ProductInfo info=(ProductInfo) pageBean.getParameter();
		
		dto.setInfoBizId(info.getBizId());
		dto.setInfoBrandId(info.getBrandId());
		dto.setInfoBrandName(info.getBrandName());
		dto.setInfoCode(info.getCode());
		dto.setInfoId(info.getId());
		dto.setInfoNameCity(info.getNameCity());
		//dto.setInfoOperatorId(info.getOperatorIds());//传过来的是字符串，solr里是int，条件是in
		dto.setInfoOperatorIds(info.getOperatorIds());
		dto.setInfoState(info.getState());//状态是固定值
		dto.setPrOrgId((String) parameters.get("orgId"));
		dto.setPageNo(pageBean.getPage());
		dto.setPageSize(pageBean.getPageSize());
		
		return dto;
	}

	/**
	 * 
	 * @param solrPageResult
	 * @return
	 */
	public static PageBean<ProductInfo> dto2PageBean(SolrSearchPageDTO<ProductStateDTO> solrPageResult) {
		PageBean<ProductInfo> pageBean=new PageBean<ProductInfo>();
			List<ProductStateDTO> list=solrPageResult.getList();
			List<ProductInfo> resultList=new ArrayList<ProductInfo>();

				for(ProductStateDTO dto:list){
					ProductInfo info=new ProductInfo();
					info.setCode(dto.getInfoCode());
					info.setBrandName(dto.getInfoBrandName());
					info.setNameCity(dto.getInfoNameCity());
					info.setState(dto.getInfoState().byteValue());
					info.setOperatorName(dto.getInfoOperatorName());
					info.setCreatorName(dto.getInfoCreatorName());
					info.setNameBrief(dto.getInfoNameBrief());
					resultList.add(info);
				}
				
				pageBean.setPageSize(solrPageResult.getPageSize());
				pageBean.setTotalCount(solrPageResult.getTotalCount());
				pageBean.setPage(solrPageResult.getPageNo());
				pageBean.setResult(resultList);
				
				return pageBean;
		
	}

	/**
	 * 
	 * @param queryDTO
	 * @return
	 */
	public static SolrQuery queryDTO2SolrQuery(ProductStatePageQueryDTO queryDTO) {

		SolrQuery solrQuery = new SolrQuery();
		
		if(!StringUtils.isEmpty(queryDTO.getPrOrgId())){
			String orgIdQuery = "prOrgId:*" + queryDTO.getPrOrgId() + ",*";
			solrQuery.addFilterQuery(orgIdQuery);
		}
		
		if(!StringUtils.isEmpty(queryDTO.getInfoCode())){
			String codeQuery = "infoCode:" + queryDTO.getInfoCode();
			solrQuery.addFilterQuery(codeQuery);
		}
		
		if(queryDTO.getInfoBrandId() != null){
			String brandQuery = "infoBrandId:" + queryDTO.getInfoBrandId();
			solrQuery.addFilterQuery(brandQuery);
		}
		
		if(queryDTO.getInfoNameCity() != null){
			String cityQuery = "infoNameCity:*" + queryDTO.getInfoNameCity() + "*";
			solrQuery.addFilterQuery(cityQuery);
		}
		
		solrQuery.setStart(queryDTO.getStartRow());
		solrQuery.setRows(queryDTO.getOldPageSize());

		return solrQuery;
	}

}
