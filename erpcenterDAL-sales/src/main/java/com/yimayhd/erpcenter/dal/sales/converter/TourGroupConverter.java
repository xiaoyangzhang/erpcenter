package com.yimayhd.erpcenter.dal.sales.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.util.CollectionUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.dto.TourProfitQueryDTO;
import com.yimayhd.erpcenter.dal.sales.client.dto.TourTotalProfitQueryDTO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.solr.dto.TourGroupDTO;
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
	
	public static TourTotalProfitQueryDTO convert2TourTotalProfitQueryDTO(PageBean<TourGroup> pageBean, Integer bizId,Set<Integer> set) {
		
		TourTotalProfitQueryDTO queryDTO = new TourTotalProfitQueryDTO();
		

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
			query.addFilterQuery("tgDateStart:" + queryDTO.getEndTime().getTime());
		}else if(queryDTO.getStartTime() != null){
			query.addFilterQuery("tgDateStart:" + queryDTO.getStartTime().getTime());
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
			String productNameLike = queryDTO.getProductNameLike().replace("【","");
			productNameLike = productNameLike.replace("】", "");
			
			query.addFilterQuery("productName:*" + productNameLike + "*" + "OR tgProductBrandName:*" + productNameLike  + "*");
		}
		
		query.addSort("tgDateStart", ORDER.asc);
		query.addSort("tgCreateTime", ORDER.asc);
		
		query.setStart(queryDTO.getStartRow());
		query.setRows(queryDTO.getOldPageSize());
		
		return query;
	}
	
	
	public static SolrQuery convert2SolrQuery(TourTotalProfitQueryDTO queryDTO){
		SolrQuery query = new SolrQuery("*:*");
		if(queryDTO.getBizId() != null){
			query.addFilterQuery("tgBizId:" + queryDTO.getBizId());
		}
		
		if(queryDTO.getEndTime() != null && queryDTO.getStartTime() != null){
			query.addFilterQuery("tgDateStart:[" + queryDTO.getStartTime().getTime() + " TO " + queryDTO.getEndTime() + "]");
		}else if(queryDTO.getEndTime() != null){
			query.addFilterQuery("tgDateStart:" + queryDTO.getEndTime().getTime());
		}else if(queryDTO.getStartTime() != null){
			query.addFilterQuery("tgDateStart:" + queryDTO.getStartTime().getTime());
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
			String productNameLike = queryDTO.getProductNameLike().replace("【","");
			productNameLike = productNameLike.replace("】", "");
			
			query.addFilterQuery("productName:*" + productNameLike + "*" + "OR tgProductBrandName:*" + productNameLike  + "*");
		}
		
		query.setParam("stats", true);
		query.set("indent", true);
		query.add("stats.field","opBudget");
		query.add("stats.field","opIncome");
		query.setRows(0);
		
		
		return query;
	}
	
	
	
	public static List<TourGroup> convert2TourGroupList(List<TourGroupDTO> toursDTOList){
		if(toursDTOList == null){
			return null;
		}
		
		List<TourGroup> tourGroupList = new ArrayList<TourGroup>();
		for(TourGroupDTO tourDTO :  toursDTOList){
			tourGroupList.add(convert2TourTotalGroup(tourDTO));
		}
		
		return tourGroupList;
		
	}
	
	
	public static TourGroup convert2TourTotalGroup(TourGroupDTO tourDTO) {
		if (tourDTO == null) {
			return null;
		}

		TourGroup tourGroup = new TourGroup();
		tourGroup.setId(tourDTO.getTgGroupId());
		tourGroup.setGroupCode(tourDTO.getTgGroupCode());
		tourGroup.setGroupMode(tourDTO.getTgGroupMode());
		tourGroup.setSupplierName(tourDTO.getgOsupplierName());
		tourGroup.setDateStart(tourDTO.getTgDateStart());
		tourGroup.setProductBrandName(tourDTO.getTgProductBrandName());
		tourGroup.setProductName(tourDTO.getTgProductName());
		tourGroup.setTotalAdult(tourDTO.getTgTotalAdult());
		tourGroup.setTotalChild(tourDTO.getTgTotalChild());
		tourGroup.setTotalGuide(tourDTO.getTgTotalGuide());
		tourGroup.setOperatorName(tourDTO.getTgOperatorName());
		tourGroup.setGroupState(tourDTO.getTgGroupState());
		tourGroup.setCreateTime(tourDTO.getTgCreateTime().getTime());
		tourGroup.setOperatorId(tourDTO.getTgOperatorId());
		tourGroup.setBudget(tourDTO.getOpBudget());
		tourGroup.setTotal(tourDTO.getOpIncome());
		tourGroup.setBizId(tourDTO.getTgBizId());

		return tourGroup;

	}
}
