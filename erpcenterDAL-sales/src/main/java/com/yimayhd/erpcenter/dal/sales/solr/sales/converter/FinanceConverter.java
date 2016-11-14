package com.yimayhd.erpcenter.dal.sales.solr.sales.converter;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.util.DateUtils;
import com.yimayhd.erpcenter.dal.sales.client.solr.query.SubjectSummaryPageQueryDTO;

public class FinanceConverter {

	private static String getOperateIds(Set<Integer> set, String operatorIds){
		
		if(set == null){
			return operatorIds;
		}
		
		if(StringUtils.isNotEmpty(operatorIds)){
			String[] arr = operatorIds.split(",");
			for(String item : arr){
				set.add(Integer.parseInt(item));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int i = 0; 
		for(Integer item : set){
			if(i > 0){
				sb.append(",");
			}
			sb.append(item);
			i++;
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static SubjectSummaryPageQueryDTO toSubjectSummaryQueryDTO(PageBean pageBean) {
		
		
		SubjectSummaryPageQueryDTO dto = new SubjectSummaryPageQueryDTO();
		
		Map<String,Object> paramters = (Map<String,Object>)pageBean.getParameter();
		
		Object obj = paramters.get("bizId");
		if(obj != null){
			dto.setBizId(Integer.parseInt(obj.toString()));
		}
		
		obj = paramters.get("startTime");
		if(obj != null){
			Date date = DateUtils.parse(obj.toString(), DateUtils.FORMAT_SHORT);
			dto.setStartTime(date.getTime());
		}
		
		obj = paramters.get("endTime");
		if(obj != null){
			Date date = DateUtils.parse(obj.toString(), DateUtils.FORMAT_SHORT);
			dto.setStartTime(date.getTime());
		}
		
		obj = paramters.get("supplierId");
		if(obj != null){
			dto.setStartTime(Integer.parseInt(obj.toString()));
		}
		
		obj = paramters.get("sjType"); //supplier_type
		if(obj != null){
			dto.setCashType(obj.toString());
		}
		
		obj = paramters.get("sjType2"); //supplier_type
		if(obj != null){
			dto.setCashType2(obj.toString());
		}
		
		obj = paramters.get("sjType3"); //supplier_type
		if(obj != null){
			dto.setCashType3(obj.toString());
		}
		
		Set<Integer> set = null;
		obj = paramters.get("set");
		if(obj != null){
			set = (Set<Integer>)obj;
		}
		
		String operatorIds = "";
		obj = paramters.get("operator_id");
		if(obj != null){
			operatorIds = obj.toString();
		}
		
		String retOperatorIds = getOperateIds(set, operatorIds);
		dto.setOperatorIds(retOperatorIds);
		return dto;
	}
	
	public static void main(String[] args){
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		
		String ret = getOperateIds(set, "4,5,6,7,8,999,10");
		System.out.println(ret);
	}
}
