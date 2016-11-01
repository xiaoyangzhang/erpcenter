package com.yimayhd.erpcenter.dal.sales.solr.sales.manage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.FieldStatsInfo;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.common.solr.BaseSolrQueryManager;
import com.yimayhd.erpcenter.common.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.solr.dto.GroupOrderDTO;
import com.yimayhd.erpcenter.dal.sales.client.solr.query.GroupOrderPageQueryDTO;
import com.yimayhd.erpcenter.dal.sales.constants.SalesCollectionEnum;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.FitOrderConverter;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.LockOrderConverter;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.ProfitOrderConverter;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.SaleOrderConverter;
import com.yimayhd.erpcenter.dal.sales.solr.sales.converter.SpecialOrderConverter;


public class SalesSolrQueryManage  {
	
	@Autowired
	private BaseSolrQueryManager solrQueryManager;

	//团队管理
	public SolrSearchPageDTO<GroupOrderDTO> searchSalesOrder(GroupOrderPageQueryDTO  queryDTO){
		SolrSearchPageDTO<GroupOrderDTO> pageResult = new SolrSearchPageDTO<GroupOrderDTO>();
		SolrQuery solrQuery = SaleOrderConverter.queryDTO2SolrQuery(queryDTO);
	     
        QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_GROUPORDER.getCollection(), solrQuery);
        List<GroupOrderDTO> dtoList = response.getBeans(GroupOrderDTO.class);
        
        pageResult.setList(dtoList);
        pageResult.setPageNo(queryDTO.getPageNo());
        pageResult.setPageSize(queryDTO.getPageSize());
        pageResult.setTotalCount(response.getResults().getNumFound());
        
		return pageResult;
	}
	//团队管理(人数合计)
	public GroupOrder searchSalesOrderTotal(GroupOrderPageQueryDTO  queryDTO){
		GroupOrder order=new GroupOrder();
		SolrQuery solrQuery = SaleOrderConverter.queryDTO2SolrQueryTotal(queryDTO);
	     
        QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_GROUPORDER.getCollection(), solrQuery);

        Map<String, FieldStatsInfo> statsMap = response.getFieldStatsInfo();
        FieldStatsInfo auditInfo = statsMap.get("audit");
        FieldStatsInfo childInfo = statsMap.get("child");
        FieldStatsInfo guideInfo = statsMap.get("guide");
        
        if(auditInfo!=null&&childInfo!=null&&guideInfo!=null){
        	 order.setNumAdult(Integer.parseInt(auditInfo.getSum().toString()));
             order.setNumChild(Integer.parseInt(childInfo.getSum().toString()));
             order.setNumGuide(Integer.parseInt(guideInfo.getSum().toString()));
             return order;
        }
		return null;
	}
	
	
	
	
	
	//散客订单
	public SolrSearchPageDTO<GroupOrderDTO> searchFitOrder(GroupOrderPageQueryDTO  queryDTO){
		SolrSearchPageDTO<GroupOrderDTO> pageResult = new SolrSearchPageDTO<GroupOrderDTO>();
	    SolrQuery solrQuery = FitOrderConverter.queryDTO2SolrQuery(queryDTO);
	     
        QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_GROUPORDER.getCollection(), solrQuery);
        List<GroupOrderDTO> dtoList = response.getBeans(GroupOrderDTO.class);
        
        pageResult.setList(dtoList);
        pageResult.setPageNo(queryDTO.getPageNo());
        pageResult.setPageSize(queryDTO.getPageSize());
        pageResult.setTotalCount(response.getResults().getNumFound());
        
		return pageResult;
	}
	//散客订单(人数，金额合计)
		public GroupOrder searchFitOrderTotal(GroupOrderPageQueryDTO  queryDTO){
			GroupOrder order=new GroupOrder();
		    SolrQuery solrQuery = FitOrderConverter.queryDTO2SolrQueryTotal(queryDTO);
		     
	        QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_GROUPORDER.getCollection(), solrQuery);
	        Map<String, FieldStatsInfo> statsMap = response.getFieldStatsInfo();
	        FieldStatsInfo auditInfo = statsMap.get("audit");
	        FieldStatsInfo childInfo = statsMap.get("child");
	        FieldStatsInfo guideInfo = statsMap.get("guide");
	        FieldStatsInfo totalInfo = statsMap.get("total");
	        
	        if(auditInfo!=null&&childInfo!=null&&guideInfo!=null){
	        	 order.setNumAdult(Integer.parseInt(auditInfo.getSum().toString()));
	             order.setNumChild(Integer.parseInt(childInfo.getSum().toString()));
	             order.setNumGuide(Integer.parseInt(guideInfo.getSum().toString()));
	             order.setTotal(new BigDecimal(Double.parseDouble(totalInfo.getSum().toString())));
	             return order;
	        }
			return null;
		}
	
	//一地散订单
	public SolrSearchPageDTO<GroupOrderDTO> searchSpecialOrder(GroupOrderPageQueryDTO  queryDTO){
	   SolrSearchPageDTO<GroupOrderDTO> pageResult = new SolrSearchPageDTO<GroupOrderDTO>();
	   SolrQuery solrQuery = SpecialOrderConverter.queryDTO2SolrQuery(queryDTO,0);
	     
       QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_GROUPORDER.getCollection(), solrQuery);
       List<GroupOrderDTO> dtoList = response.getBeans(GroupOrderDTO.class);
       
       pageResult.setList(dtoList);
       pageResult.setPageNo(queryDTO.getPageNo());
       pageResult.setPageSize(queryDTO.getPageSize());
       pageResult.setTotalCount(response.getResults().getNumFound());
       
		return pageResult;
	}
	//一地散订单(人数，金额合计)
	public GroupOrder searchSpecialOrderTotal(GroupOrderPageQueryDTO  queryDTO){
		GroupOrder order=new GroupOrder();
	   SolrQuery solrQuery = SpecialOrderConverter.queryDTO2SolrQuery(queryDTO,1);
	     
       QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_GROUPORDER.getCollection(), solrQuery);
       Map<String, FieldStatsInfo> statsMap = response.getFieldStatsInfo();
       FieldStatsInfo auditInfo = statsMap.get("audit");
       FieldStatsInfo childInfo = statsMap.get("child");
       FieldStatsInfo guideInfo = statsMap.get("guide");
       FieldStatsInfo totalInfo = statsMap.get("total");
       
       if(auditInfo!=null&&childInfo!=null&&guideInfo!=null){
       	 order.setNumAdult(Integer.parseInt(auditInfo.getSum().toString()));
         order.setNumChild(Integer.parseInt(childInfo.getSum().toString()));
         order.setNumGuide(Integer.parseInt(guideInfo.getSum().toString()));
         order.setTotal(new BigDecimal(Double.parseDouble(totalInfo.getSum().toString())));
         return order;
       }
		return null;
	}
	//锁单管理
	public SolrSearchPageDTO<GroupOrderDTO> searchLockOrder(GroupOrderPageQueryDTO  queryDTO){
	  SolrSearchPageDTO<GroupOrderDTO> pageResult = new SolrSearchPageDTO<GroupOrderDTO>();
	  SolrQuery solrQuery = LockOrderConverter.queryDTO2SolrQuery(queryDTO,0);
	     
      QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_GROUPORDER.getCollection(), solrQuery);
      List<GroupOrderDTO> dtoList = response.getBeans(GroupOrderDTO.class);
      
      pageResult.setList(dtoList);
      pageResult.setPageNo(queryDTO.getPageNo());
      pageResult.setPageSize(queryDTO.getPageSize());
      pageResult.setTotalCount(response.getResults().getNumFound());
      
		return pageResult;
	}
	//锁单管理(人数总计)
	public String searchLockOrderTotal(GroupOrderPageQueryDTO  queryDTO){
		  String result="";
		  SolrQuery solrQuery = LockOrderConverter.queryDTO2SolrQuery(queryDTO,1);
		     
	      QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_GROUPORDER.getCollection(), solrQuery);
	       Map<String, FieldStatsInfo> statsMap = response.getFieldStatsInfo();
	       FieldStatsInfo auditInfo = statsMap.get("audit");
	       FieldStatsInfo childInfo = statsMap.get("child");
	       FieldStatsInfo guideInfo = statsMap.get("guide");
	       FieldStatsInfo totalInfo = statsMap.get("total");
	       
	       if(auditInfo!=null){
	       	result=auditInfo.getSum().toString()+"+";
	       }else{
	    	result="0+";
	       }
	       
	       if(childInfo!=null){
		    result=childInfo.getSum().toString()+"+";
		      }else{
		    result="0+";
		      }
	       if(guideInfo!=null){
		    result=guideInfo.getSum().toString()+"";
		       }else{
		    result="0";
		       }  
		return result;
		}
	
	//预算利润查询，按团
	public SolrSearchPageDTO<GroupOrderDTO> searchProfitOrder(GroupOrderPageQueryDTO  queryDTO){
		SolrSearchPageDTO<GroupOrderDTO> pageResult = new SolrSearchPageDTO<GroupOrderDTO>();
		SolrQuery solrQuery = ProfitOrderConverter.queryDTO2SolrQuery(queryDTO,0);
		     
	    QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_GROUPORDER.getCollection(), solrQuery);
	    List<GroupOrderDTO> dtoList = response.getBeans(GroupOrderDTO.class);
	      
	    pageResult.setList(dtoList);
	    pageResult.setPageNo(queryDTO.getPageNo());
	    pageResult.setPageSize(queryDTO.getPageSize());
	    pageResult.setTotalCount(response.getResults().getNumFound());
	      
	    return pageResult;
		}
	//预算利润查询，按团
	public GroupOrder searchProfitOrderTotal(GroupOrderPageQueryDTO  queryDTO){
		GroupOrder order=new GroupOrder();
		SolrQuery solrQuery = ProfitOrderConverter.queryDTO2SolrQuery(queryDTO,1);
		     
	    QueryResponse response =  solrQueryManager.querySolrDataByFilters(SalesCollectionEnum.SALES_GROUPORDER.getCollection(), solrQuery);
	    Map<String, FieldStatsInfo> statsMap = response.getFieldStatsInfo();
        FieldStatsInfo auditInfo = statsMap.get("audit");
        FieldStatsInfo childInfo = statsMap.get("child");
        FieldStatsInfo guideInfo = statsMap.get("guide");
        FieldStatsInfo mode0Info = statsMap.get("mode0");
        FieldStatsInfo mode1Info = statsMap.get("mode1");
        
        if(auditInfo!=null){
        	 order.setNumAdult(Integer.parseInt(auditInfo.getSum().toString()));
        }else{
        	order.setNumAdult(0);
        }
        if(childInfo!=null){
       	 order.setNumAdult(Integer.parseInt(auditInfo.getSum().toString()));
            order.setNumChild(Integer.parseInt(childInfo.getSum().toString()));
       }else{
       	order.setNumChild(0);
       }
        if(guideInfo!=null){
            order.setNumGuide(Integer.parseInt(guideInfo.getSum().toString()));
       }else{
       	order.setNumGuide(0);
       }
        if(mode0Info!=null){
            order.setIncome(new BigDecimal(Double.parseDouble(mode0Info.getSum().toString())));
       }else{
       	order.setIncome(new BigDecimal(0));
       }
        if(mode1Info!=null){
            order.setBudget(new BigDecimal(Double.parseDouble(mode1Info.getSum().toString())));
       }else{
       	order.setBudget(new BigDecimal(0));
       }
		return order;
		}
}
