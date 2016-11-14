package com.yimayhd.erpcenter.dal.sales.solr.sales.converter;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.GroupParams;

import com.yimayhd.erpcenter.dal.sales.client.solr.query.ShopItemsPageQueryDTO;

public class ShopConverter {

	/**
	 * 
	 * @param queryDTO
	 * @return
	 */
	public static SolrQuery queryShopItemDTO2SolrQuery(ShopItemsPageQueryDTO queryDTO,int type) {

		SolrQuery solrQuery = new SolrQuery("*:*");
		
		//g.biz_id = #{bizId,jdbcType=INTEGER}
		solrQuery.addFilterQuery("tgBizId:" + queryDTO.getTgBizId());
		//g.group_state IN (1,3,4)
		solrQuery.addFilterQuery("tgGroupState:(1,3,4)");
		//s.supplier_id = #{id,jdbcType=INTEGER}
		solrQuery.addFilterQuery("bsSupplierId:" + queryDTO.getBsSupplierId());
//		<IF test="page.parameter.saleType == 0 and page.parameter.operator_id != null">
//		AND o.sale_operator_id IN (${page.parameter.operator_id})
//		</IF>
//		<IF test="page.parameter.saleType == 1 and page.parameter.operator_id != null">
//			AND g.operator_id IN (${page.parameter.operator_id})
//		</IF>
		if(queryDTO.getSaleType()!=null){
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
				if(queryDTO.getSaleType()==0){
					solrQuery.addFilterQuery("goSaleOperatorId:(" + idsIn + ")");
				}else{
					solrQuery.addFilterQuery("tgOperatorId:(" + idsIn + ")");
				}
				
			}
			
		}
		//AND t.goods_name = #{page.parameter.shopItem}
		if(!StringUtils.isEmpty(queryDTO.getBsdGoodsName())){
			solrQuery.addFilterQuery("bsdGoodsName:"+queryDTO.getBsdGoodsName());
		}
//		<IF test="'groupDate'==page.parameter.dateType and page.parameter.startTime != null">AND g.date_start <![CDATA[>=]]> #{page.parameter.startTime} </if>
//		<IF test="'groupDate'==page.parameter.dateType and page.parameter.endTime != null">AND g.date_start <![CDATA[<=]]> #{page.parameter.endTime}	</if>
//		<IF test="'appliDate'==page.parameter.dateType and page.parameter.startTime != null">AND s.shop_date <![CDATA[>=]]> #{page.parameter.startTime} </if>
//		<IF test="'appliDate'==page.parameter.dateType and page.parameter.endTime != null">AND s.shop_date <![CDATA[<=]]> #{page.parameter.endTime} </if>
		if(queryDTO.getDateType()!=null){
			String ss="";
			if(queryDTO.getDateType().equals("groupDate")){
				ss="tgDateStart";
			}else if(queryDTO.getDateType().equals("appliDate")){
				ss="bsShopDate"; 
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
		//AND s.supplier_name LIKE CONCAT('%','${page.parameter.shopStore}','%')
		if(!StringUtils.isEmpty(queryDTO.getBsSupplierName())){
			solrQuery.addFilterQuery("bsSupplierName:*"+queryDTO.getBsSupplierName()+"*");
		}
		//AND g.prudct_brand_id =#{page.parameter.productBrandId}
		if(null != queryDTO.getTgPrudctBrandId() && queryDTO.getTgPrudctBrandId() != -1){
			solrQuery.addFilterQuery("tgPrudctBrandId:"+queryDTO.getTgPrudctBrandId());
		}
		//AND (g.product_brand_name LIKE CONCAT('%',#{page.parameter.productName},'%') 
		//OR g.product_name LIKE CONCAT('%',#{page.parameter.productName},'%'))
		if(!StringUtils.isEmpty(queryDTO.getTgProductName())){
			solrQuery.addFilterQuery("tgProductName:*"+queryDTO.getTgProductName()+"* OR tgProductBrandName:*"+queryDTO.getTgProductName());
		}
//		<IF test="page.parameter.groupMode == 0">
//		AND g.group_mode <![CDATA[<]]>1
//		</IF>
//		<IF test="page.parameter.groupMode == 1">
//			AND g.group_mode <![CDATA[>]]>0
//		</IF>
		if(queryDTO.getTgGroupMode()==0){
			solrQuery.addFilterQuery("tgGroupMode:(* TO 1)");
		}else if(queryDTO.getTgGroupMode()==1){
			solrQuery.addFilterQuery("tgGroupMode:(0 TO *)");
		}
		
		//!=-1
//		StringBuffer q_sb = new StringBuffer();
//		String one = "-1";
//		q_sb.append("-psState:" + "\""+one+"\"");

		//GROUP BY t.goodsId
		solrQuery.setParam(GroupParams.GROUP, true);
		solrQuery.setParam(GroupParams.GROUP_FIELD, "bsdGoodsId");
		solrQuery.setParam("group.limit", "1");//每组显示的个数，默认为1
		solrQuery.setParam("group.ngroups", true);
		
		if(type==0){
			solrQuery.setStart(queryDTO.getStartRow());
			solrQuery.setRows(queryDTO.getOldPageSize());
		}else{
			//SUM
			solrQuery.setParam("stats", true);
			solrQuery.set("indent", true);
			solrQuery.add("stats.field","bsdBuyTotal");
			solrQuery.add("stats.field","bsdRepayTotal");
			solrQuery.setRows(0);
		}
		return solrQuery;
	}
	
	/**
	 * 
	 * @param queryDTO
	 * @return
	 */
	public static SolrQuery queryShopDTO2SolrQuery(ShopItemsPageQueryDTO queryDTO) {

		SolrQuery solrQuery = new SolrQuery("*:*");
		
		//g.biz_id = #{bizId,jdbcType=INTEGER}
		solrQuery.addFilterQuery("tgBizId:" + queryDTO.getTgBizId());
		//g.group_state IN (1,3,4)
		solrQuery.addFilterQuery("tgGroupState:(1,3,4)");
//		<IF test="page.parameter.saleType == 0 and page.parameter.operator_id != null">
//		AND o.sale_operator_id IN (${page.parameter.operator_id})
//		</IF>
//		<IF test="page.parameter.saleType == 1 and page.parameter.operator_id != null">
//			AND g.operator_id IN (${page.parameter.operator_id})
//		</IF>
		if(queryDTO.getSaleType()!=null){
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
				if(queryDTO.getSaleType()==0){
					solrQuery.addFilterQuery("goSaleOperatorId:(" + idsIn + ")");
				}else{
					solrQuery.addFilterQuery("tgOperatorId:(" + idsIn + ")");
				}
				
			}
			
		}
		//AND t.goods_name = #{page.parameter.shopItem}
		if(!StringUtils.isEmpty(queryDTO.getBsdGoodsName())){
//			solrQuery.addFilterQuery("bsdGoodsName:"+queryDTO.getBsdGoodsName());
			solrQuery.addFilterQuery("bsdGoodsNameStr:*"+queryDTO.getBsdGoodsName()+","+"*");
		}
//		<IF test="'groupDate'==page.parameter.dateType and page.parameter.startTime != null">AND g.date_start <![CDATA[>=]]> #{page.parameter.startTime} </if>
//		<IF test="'groupDate'==page.parameter.dateType and page.parameter.endTime != null">AND g.date_start <![CDATA[<=]]> #{page.parameter.endTime}	</if>
//		<IF test="'appliDate'==page.parameter.dateType and page.parameter.startTime != null">AND s.shop_date <![CDATA[>=]]> #{page.parameter.startTime} </if>
//		<IF test="'appliDate'==page.parameter.dateType and page.parameter.endTime != null">AND s.shop_date <![CDATA[<=]]> #{page.parameter.endTime} </if>
		if(queryDTO.getDateType()!=null){
			String ss="";
			if(queryDTO.getDateType().equals("groupDate")){
				ss="tgDateStart";
			}else if(queryDTO.getDateType().equals("appliDate")){
				ss="bsShopDate"; 
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
		//AND s.supplier_name LIKE CONCAT('%','${page.parameter.shopStore}','%')
		if(!StringUtils.isEmpty(queryDTO.getBsSupplierName())){
			solrQuery.addFilterQuery("bsSupplierName:*"+queryDTO.getBsSupplierName()+"*");
		}
		//AND g.prudct_brand_id =#{page.parameter.productBrandId}
		if(null != queryDTO.getTgPrudctBrandId() && queryDTO.getTgPrudctBrandId() != -1){
			solrQuery.addFilterQuery("tgPrudctBrandId:"+queryDTO.getTgPrudctBrandId());
		}
		//AND (g.product_brand_name LIKE CONCAT('%',#{page.parameter.productName},'%') 
		//OR g.product_name LIKE CONCAT('%',#{page.parameter.productName},'%'))
		if(!StringUtils.isEmpty(queryDTO.getTgProductName())){
			solrQuery.addFilterQuery("tgProductName:*"+queryDTO.getTgProductName()+"* OR tgProductBrandName:*"+queryDTO.getTgProductName());
		}
//		<IF test="page.parameter.groupMode == 0">
//		AND g.group_mode <![CDATA[<]]>1
//		</IF>
//		<IF test="page.parameter.groupMode == 1">
//			AND g.group_mode <![CDATA[>]]>0
//		</IF>
		if(queryDTO.getTgGroupMode()==0){
			solrQuery.addFilterQuery("tgGroupMode:(* TO 1)");
		}else if(queryDTO.getTgGroupMode()==1){
			solrQuery.addFilterQuery("tgGroupMode:(0 TO *)");
		}
				
//		solrQuery.set(GroupParams.GROUP_SORT,"psItemDate asc");
		solrQuery.setStart(queryDTO.getStartRow());
		solrQuery.setRows(queryDTO.getOldPageSize());

		return solrQuery;
	}
	
}
