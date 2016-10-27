package com.yimayhd.erpcenter.dal.sales.solr.sales.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.solr.dto.GroupOrderDTO;
import com.yimayhd.erpcenter.dal.sales.client.solr.query.GroupOrderPageQueryDTO;


public class SaleOrderConverter {

	public static GroupOrderPageQueryDTO toQueryDTO(GroupOrder order) {
		//根据pageBean和parameters组合获得dto参数
		GroupOrderPageQueryDTO dto=new GroupOrderPageQueryDTO();

		
		return dto;
	}

	/**
	 * 
	 * @param solrPageResult
	 * @return
	 */
	public static PageBean<GroupOrder> dto2PageBean(SolrSearchPageDTO<GroupOrderDTO> solrPageResult) {
		PageBean<GroupOrder> pageBean=new PageBean<GroupOrder>();
			List<GroupOrderDTO> list=solrPageResult.getList();
			List<GroupOrder> resultList=new ArrayList<GroupOrder>();

				for(GroupOrderDTO dto:list){
					GroupOrder info=new GroupOrder();
//					info.setCode(dto.getInfoCode());
//					info.setBrandName(dto.getInfoBrandName());
//					info.setNameCity(dto.getInfoNameCity());
//					info.setState(dto.getInfoState().byteValue());
//					info.setOperatorName(dto.getInfoOperatorName());
//					info.setCreatorName(dto.getInfoCreatorName());
//					info.setNameBrief(dto.getInfoNameBrief());
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
	public static SolrQuery queryDTO2SolrQuery(GroupOrderPageQueryDTO queryDTO) {

		SolrQuery solrQuery = new SolrQuery("*:*");
		StringBuffer q_sb = new StringBuffer();
		String two = "-1";
		q_sb.append("-infoState:" + "\""+two+"\"");
		solrQuery.addFilterQuery(q_sb.toString());
		
//		if(!StringUtils.isEmpty(queryDTO.getPrOrgId())){
//			String orgIdQuery = "prOrgId:*" + queryDTO.getPrOrgId() + ",*";
//			solrQuery.addFilterQuery(orgIdQuery);
//		}
//		
//		if(!StringUtils.isEmpty(queryDTO.getInfoCode())&&!"".equals(queryDTO.getInfoCode())){
//			String codeQuery = "infoCode:" + queryDTO.getInfoCode();
//			solrQuery.addFilterQuery(codeQuery);
//		}
//		
//		if(queryDTO.getInfoBrandId() != null){
//			String brandQuery = "infoBrandId:" + queryDTO.getInfoBrandId();
//			solrQuery.addFilterQuery(brandQuery);
//		}
//		
//		if(queryDTO.getInfoNameCity() != null){
//			String cityQuery = "infoNameCity:*" + queryDTO.getInfoNameCity() + "*";
//			solrQuery.addFilterQuery(cityQuery);
//		}
//		
//		if(queryDTO.getInfoBizId() != null){
//			String bizQuery = "infoBizId:" + queryDTO.getInfoBizId();
//			solrQuery.addFilterQuery(bizQuery);
//		}
		
//		if(queryDTO.getInfoOperatorIds()!= null){
//			String infoOperatorIdsQuery = "infoNameCity:*" + queryDTO.getInfoNameCity() + "*";
//			if(queryDTO.getInfoOperatorIds()==1){
//				
//			}else{}
//			solrQuery.addFilterQuery(infoOperatorIdsQuery);
//		}
		
		solrQuery.setStart(queryDTO.getStartRow());
		solrQuery.setRows(queryDTO.getOldPageSize());

		return solrQuery;
	}
}
