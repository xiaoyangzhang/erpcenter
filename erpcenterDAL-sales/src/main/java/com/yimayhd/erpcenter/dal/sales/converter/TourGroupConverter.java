package com.yimayhd.erpcenter.dal.sales.converter;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.util.CollectionUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.dto.TourProfitQueryDTO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.utils.ParmUtil;

public class TourGroupConverter {
	
	public static TourProfitQueryDTO convert2TourProfitQueryDTO(PageBean<TourGroup> pageBean, Integer bizId,Set<Integer> set) {
		
		TourProfitQueryDTO queryDTO = new TourProfitQueryDTO();
		

		TourGroup tourGroup = (TourGroup) pageBean.getParameter();
		queryDTO.setBizId(bizId);
		queryDTO.setEndTime(tourGroup.getEndTime());
		queryDTO.setStartTime(tourGroup.getStartTime());
		queryDTO.setGroupCodeLike(tourGroup.getGroupCode());
		
		if(StringUtils.isEmpty(tourGroup.getSaleOperatorIds())){
			List<Integer> saleOperatorIds = new ArrayList<Integer>();
			String [] ids = tourGroup.getOperatorIds().split(",");
			for(String id : ids){
				saleOperatorIds.add(Integer.valueOf(id));
			}
			
			queryDTO.setSaleOperatorIds(saleOperatorIds);
		}
		
		queryDTO.setGroupCodeLike(tourGroup.getGroupCode());
		queryDTO.setProductNameLike(tourGroup.getProductName());
		if(!CollectionUtils.isEmpty(set)){
			queryDTO.setOperatorIds(new ArrayList<Integer>(set));
		}
		 
		queryDTO.setPageNo(pageBean.getPage());
		queryDTO.setPageSize(pageBean.getPageSize());
		
		return queryDTO;

	}
	
	public static SolrQuery convert2SolrQuery(TourProfitQueryDTO queryDTO){
		SolrQuery query = new SolrQuery("*:*");
		if(queryDTO.getBizId() != null){
			query.addFilterQuery("tgBizId:" + queryDTO.getBizId());
		}
		
		if(queryDTO.getEndTime() != null && queryDTO.getStartTime() != null){
			query.addFilterQuery("tgDateStart:[" + queryDTO.getStartTime().getTime() + " TO " + queryDTO.getEndTime() + "]");
		}else if(queryDTO.getEndTime() != null){
			query.addFilterQuery("tgDateStart:" + queryDTO.getEndTime());
		}else if(queryDTO.getStartTime() != null){
			query.addFilterQuery("tgDateStart:" + queryDTO.getStartTime());
		}
		
		if(queryDTO.getGroupCodeLike() != null){
			query.addFilterQuery("tgGroupCode:*" + queryDTO.getGroupCodeLike() + "*");
		}
		
		if(queryDTO.getOperatorIds() != null){
			Integer [] ids = new Integer [queryDTO.getOperatorIds().size()];
			ParmUtil.getQueryParm("tgOperatorId", queryDTO.getOperatorIds().toArray(ids), query);
		}
		
		if(queryDTO.getSaleOperatorIds() != null){
			Integer [] ids = new Integer [queryDTO.getSaleOperatorIds().size()];
			ParmUtil.getQueryParm("tgOperatorId", queryDTO.getSaleOperatorIds().toArray(ids), query);
		}
		
		if(queryDTO.getProductNameLike() != null){
			query.addFilterQuery("productName:*" + queryDTO.getProductNameLike() + "*");
		}
		
		return query;
	}
}
