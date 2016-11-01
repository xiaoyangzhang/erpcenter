package com.yimayhd.erpcenter.dal.sales.solr.sales.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.solr.SolrSearchPageDTO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.solr.dto.GroupOrderDTO;
import com.yimayhd.erpcenter.dal.sales.client.solr.query.GroupOrderPageQueryDTO;

public class SaleOrderConverter {

	public static GroupOrderPageQueryDTO toQueryDTO(PageBean<GroupOrder> pageBean, Integer bizId, Set<Integer> set, Integer listType) {
		//根据pageBean和parameters组合获得dto参数
		//tg.group_mode > 0-------
		//go.state != -1-------
		//go.order_type = 1-------
	    //go.biz_id = #{bizId,jdbcType=INTEGER}
		//go.sale_operator_id (listType==0)
		//go.operator_id in    (listType==1)
//		tg.date_start  #{page.parameter.startTime} <tg.date_start< #{page.parameter.endTime} page.parameter.dateType == 1
//		tg.create_time  #{page.parameter.startTime} <tg.date_start< #{page.parameter.endTime} page.parameter.dateType == 2
//		tg.group_state 
//		go.total != total_cash  page.parameter.cashState == 0
//		go.total = total_cash   page.parameter.cashState ==1
//		go.receive_mode LIKE
//		go.order_lock_state
//		and go.province_id page.parameter.provinceId !=-1
//		go.city_id =#{page.parameter.cityId} page.parameter.cityId
//		go.sale_operator_id in    page.parameter.operType==1
//		go.operator_id in    page.parameter.operType==2
//		go.creator_id in    page.parameter.operType==3
//		go.source_type_id page.parameter.sourceTypeId !=-1
		GroupOrderPageQueryDTO dto=new GroupOrderPageQueryDTO();
		GroupOrder order=(GroupOrder) pageBean.getParameter();
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
		try {
		dto.setGoBizId(bizId);
		dto.setListType(listType);
		dto.setListTypeIds(set);
		dto.setDateType(order.getDateType());
		if(!StringUtils.isEmpty(order.getStartTime())){
			dto.setStartTime(fmt.parse(order.getStartTime()).getTime());
		}
		if(!StringUtils.isEmpty(order.getEndTime())){
			dto.setEndTime(fmt.parse(order.getEndTime()).getTime());
		}
		if(!StringUtils.isEmpty(order.getTourState())){
			dto.setTourGroupState(Integer.parseInt(order.getTourState()));
		}
		dto.setCashState(order.getCashState());
		dto.setGoReceiveMode(order.getReceiveMode());
		dto.setGoOrderLockState(order.getOrderLockState());
		dto.setGoProvinceId(order.getProvinceId());
		dto.setGoCityId(order.getCityId());
		dto.setOperType(order.getOperType());
		dto.setGoCreatorId(order.getSaleOperatorIds());
		if(!StringUtils.isEmpty(order.getSourceTypeId())){
			dto.setGoSourcetypeId(Integer.parseInt(order.getSourceTypeId()));
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	/**
	 * 
	 * @param solrPageResult
	 * @return
	 */
	public static PageBean<GroupOrder> dto2PageBean(SolrSearchPageDTO<GroupOrderDTO> solrPageResult,PageBean<GroupOrder> pageBean) {
			List<GroupOrderDTO> list=solrPageResult.getList();
			List<GroupOrder> resultList=new ArrayList<GroupOrder>();
				for(GroupOrderDTO dto:list){
					GroupOrder order=new GroupOrder();
					TourGroup tourGroup=new TourGroup();
					//${gl.tourGroup.id}${gl.tourGroup.groupCode}
					tourGroup.setId(dto.getTourGroupId());
					tourGroup.setGroupCode(dto.getTourGroupCode());
//					//${gl.tourGroup.dateStart}
//					tourGroup.setDateStart(dto.getTourDateStart());
					//【${gl.productBrandName}】${gl.productName}
					order.setProductBrandName(dto.getGoProductBrandName());
					order.setProductName(dto.getGoProductName());
					//${gl.tourGroup.daynum}
					tourGroup.setDaynum(dto.getTourDayNum());//---------------暂时没有需要加(已添加)
					//<td style="text-align: left;">${gl.supplierName}</td>
					order.setSupplierName(dto.getGoSupplierName());
		             // <td>${gl.provinceName}${gl.cityName}</td>
					order.setCityName(dto.getGoCityName());
		             // <td>${gl.sourceTypeName}</td>
					order.setSourceTypeName(dto.getGoSourceTypeName());
		             // <td>${gl.contactName}</td>
					order.setContactName(dto.getGoContactName());
		             // <td>${gl.tourGroup.totalAdult}大${gl.tourGroup.totalChild}小${gl.tourGroup.totalGuide}陪</td>
					tourGroup.setTotalAdult(dto.getTourTotalAdult());
					tourGroup.setTotalChild(dto.getTourTotalChild());
					tourGroup.setTotalGuide(dto.getTourTotalGuide());
		             // <td>${gl.saleOperatorName}</td>
					order.setSaleOperatorName(dto.getGoSaleOperatorName());
		             // <td>${gl.operatorName}</td>
					order.setOperatorName(dto.getGoOperatorName());
					//gl.tourGroup.groupState
					tourGroup.setGroupState(dto.getTourGroupState());
					//gl.tourGroup.dateStart
					//gl.tourGroup.dateEnd 
					tourGroup.setDateStart(new Date(dto.getTourDateStart()));
					tourGroup.setDateEnd(new Date(dto.getTourDateEnd()));
					//${gl.id},${gl.tourGroup.groupState},${gl.tourGroup.id}后两个上面已有
					order.setId(dto.getGoId());
					//gl.orderLockState 
					order.setOrderLockState(dto.getGoOrderLockState());
					order.setTourGroup(tourGroup);
					resultList.add(order);
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
		q_sb.append("-goState:" + "\""+two+"\"");
		solrQuery.addFilterQuery(q_sb.toString());
		solrQuery.addFilterQuery("goOrderType:1");
		solrQuery.addFilterQuery("tourGroupMode:(0 TO *)");
		//go.biz_id = #{bizId,jdbcType=INTEGER}
		if(queryDTO.getGoBizId()!=null){
			solrQuery.addFilterQuery("goBizId:" + queryDTO.getGoBizId());
		}
		//go.sale_operator_id (listType==0)
		//go.operator_id in    (listType==1)
		if(queryDTO.getListType()!=null){
			Set<Integer> set=queryDTO.getListTypeIds();
			String idsIn="";
			if(set!=null&&set.size()>0){
				for(Integer id:set){
					idsIn=id+",";
				}
				idsIn=idsIn.substring(0,idsIn.length()-1);
				if(idsIn.contains(",")){
					
					idsIn=idsIn.replace("," , " OR ");
				}
				if(queryDTO.getListType()==0){
					solrQuery.addFilterQuery("goSaleOperatorId:(" + idsIn + ")");
				}else{
					solrQuery.addFilterQuery("goOperatorId:(" + idsIn + ")");
				}
				
			}
			
		}
		//tg.date_start  #{page.parameter.startTime} <tg.date_start< #{page.parameter.endTime} page.parameter.dateType == 1
		//tg.create_time  #{page.parameter.startTime} <tg.date_start< #{page.parameter.endTime} page.parameter.dateType == 2
		if(queryDTO.getDateType()!=null){
			String ss="";
			if(queryDTO.getDateType()==1){
				ss="tourDateStart";
			}else if(queryDTO.getDateType()==2){
				ss="tourCreateTime"; 
			}
			String dateQuery="";
			if(queryDTO.getStartTime()!=null&&queryDTO.getEndTime()!=null){
				dateQuery="["+queryDTO.getStartTime()+" TO "+queryDTO.getEndTime()+"]";
			}else if(queryDTO.getStartTime()==null&&queryDTO.getEndTime()!=null){
				dateQuery="(* TO "+queryDTO.getEndTime()+"]";
			}else if(queryDTO.getStartTime()!=null&&queryDTO.getEndTime()==null){
				dateQuery="["+queryDTO.getStartTime()+" TO *)";
				}
			if(!StringUtils.isEmpty(dateQuery)){
				solrQuery.addFilterQuery(ss+":"+dateQuery);
			}
		}
		
		//tg.group_state 
		if(queryDTO.getTourGroupState()!=null){
			solrQuery.addFilterQuery("tourGroupState:" + queryDTO.getTourGroupState());
		}
		//go.total != total_cash  page.parameter.cashState == 0
		//go.total = total_cash   page.parameter.cashState ==1
		if(queryDTO.getCashState()!=null){
			if(queryDTO.getCashState()==0){
				solrQuery.addFilterQuery("-goTotal:goTotalCash");
			}else if(queryDTO.getCashState()==1){
				solrQuery.addFilterQuery("goTotal:goTotalCash");
			}
			solrQuery.addFilterQuery("tourGroupState:" + queryDTO.getTourGroupState());
		}
		//go.receive_mode LIKE
		if(!StringUtils.isEmpty(queryDTO.getGoReceiveMode())){
			solrQuery.addFilterQuery("goReceiveMode:*"+queryDTO.getGoReceiveMode()+"*");
		}
		//go.order_lock_state
		if(queryDTO.getGoOrderLockState()!=null){
			solrQuery.addFilterQuery("goReceiveMode:"+queryDTO.getGoOrderLockState());
		}
		//and go.province_id page.parameter.provinceId !=-1
		if(queryDTO.getGoProvinceId()!=null&&queryDTO.getGoProvinceId()!=-1){
			solrQuery.addFilterQuery("goProvinceId:"+queryDTO.getGoProvinceId());
		}
		//go.city_id =#{page.parameter.cityId} page.parameter.cityId
		if(queryDTO.getGoCityId()!=null&&queryDTO.getGoCityId()!=-1){
			solrQuery.addFilterQuery("goCityId:"+queryDTO.getGoCityId());
		}
		//	and tg.group_code LIKE CONCAT('%','${page.parameter.tourGroup.groupCode}','%')
		if(!StringUtils.isEmpty(queryDTO.getTourGroupCode())){
			solrQuery.addFilterQuery("tourGroupCode:*"+queryDTO.getTourGroupCode()+"*");
		}
		//	and go.supplier_name LIKE CONCAT('%','${page.parameter.supplierName}','%')
		if(!StringUtils.isEmpty(queryDTO.getGoSupplierName())){
			solrQuery.addFilterQuery("goSupplierName:*"+queryDTO.getGoSupplierName()+"*");
		}
		//test="page.parameter.tourGroup.productName!=null and page.parameter.tourGroup.productName!=''">
		//and CONCAT('【',tg.product_brand_name,'】',tg.product_name) LIKE CONCAT('%','${page.parameter.tourGroup.productName}','%')
		if(!StringUtils.isEmpty(queryDTO.getTourProductName())){
			solrQuery.addFilterQuery("tourProductName:*"+queryDTO.getTourProductName()+"* OR tourProductBrandName:*"+queryDTO.getTourProductName());
		}
		//go.sale_operator_id in    page.parameter.operType==1
		//go.operator_id in    page.parameter.operType==2
		//go.creator_id in    page.parameter.operType==3
		if(queryDTO.getOperType()!=null){
			String query="";
			if(!StringUtils.isEmpty(queryDTO.getGoSaleOperatorId())){
				if(queryDTO.getGoSaleOperatorId().contains(",")){
					query=queryDTO.getGoSaleOperatorId().replace(",", " OR ");
				}else{
					query=queryDTO.getGoSaleOperatorId();
				}
				if(queryDTO.getOperType()==1){
					solrQuery.addFilterQuery("goSaleOperatorId:("+query+")");
				}else if(queryDTO.getOperType()==2){
					solrQuery.addFilterQuery("goOperatorId:("+query+")");
				}else if(queryDTO.getOperType()==3){
					solrQuery.addFilterQuery("goCreatorId:("+query+")");
				}
			}
			
		}
		//go.source_type_id page.parameter.sourceTypeId !=-1
		if(queryDTO.getGoSourcetypeId()!=null&&queryDTO.getGoCityId()!=-1){
			solrQuery.addFilterQuery("goCityId:"+queryDTO.getGoSourcetypeId());
		}
		
		solrQuery.setParam("sort", "tourDateStart asc");
		solrQuery.setStart(queryDTO.getStartRow());
		solrQuery.setRows(queryDTO.getOldPageSize());

		return solrQuery;
	}
}
