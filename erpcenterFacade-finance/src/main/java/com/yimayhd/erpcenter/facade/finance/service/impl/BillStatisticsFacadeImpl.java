package com.yimayhd.erpcenter.facade.finance.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.yimayhd.erpcenter.facade.finance.errorcode.FinanceErrorCode;
import org.yimayhd.erpcenter.facade.finance.query.InRecordDTO;
import org.yimayhd.erpcenter.facade.finance.result.InRecordResult;
import org.yimayhd.erpcenter.facade.finance.service.BillStatisticsFacade;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBillBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpresource.dal.constants.BasicConstants;

public class BillStatisticsFacadeImpl implements BillStatisticsFacade{

	@Autowired
	private DicBiz dicBiz;
	
	@Autowired
	private FinanceBillBiz financeBillBiz;
	
	private Map<String, String> billStates;
	
	public BillStatisticsFacadeImpl(){
		super();
		this.billStates = new HashMap<String, String>();
		billStates.put("APPLIED", "已申请");
		billStates.put("RECEIVED", "已领单");
		billStates.put("VERIFIED", "已销单");

	}
		
	@Override
	public InRecordResult inrecord(InRecordDTO dto) {
		
		InRecordResult result = new InRecordResult();
		try{
			Integer bizId = dto.getBizId();
			
			List<DicInfo> billTypeList = dicBiz.getListByTypeCode(BasicConstants.LD_DJLX, bizId);
			
			PageBean<Map> pageBean = new PageBean<Map>();
			
			pageBean.setPageSize(dto.getPageSize());
			pageBean.setPage(dto.getPage());
			
			HashMap<String, Object> parameter = new HashMap<String, Object>();
			
			String depDateFrom = dto.getDepDateFrom();
			String depDateTo = dto.getDepDateTo();
			
			if(StringUtils.isEmpty(depDateFrom) && StringUtils.isEmpty(depDateTo)){
				Calendar c=Calendar.getInstance();
				int year=c.get(Calendar.YEAR);
				int month=c.get(Calendar.MONTH);
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				c.set(year, month, 1);
				parameter.put("depDateFrom", c.getTime());
				//group.setStartTime(c.getTime());
				c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
				parameter.put("depDateTo", c.getTime());
				//group.setEndTime(c.getTime());
				
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (StringUtils.isNotEmpty(depDateFrom)){
				parameter.put("depDateFrom", sdf.parse(depDateFrom));
			}
			if (StringUtils.isNotEmpty(depDateTo)){
				parameter.put("depDateTo", sdf.parse(depDateTo));
			}
			parameter.put("groupCode", dto.getGroupCode());
			parameter.put("billType", dto.getBillType());
			parameter.put("guideName", dto.getGuideName());
			parameter.put("productName", dto.getProductName());
			parameter.put("billState", dto.getBillState());
			parameter.put("billSupplier", dto.getBillSupplier());
			parameter.put("bizId", dto.getBizId());
			parameter.put("set", dto.getSet());
			pageBean.setParameter(parameter);

			pageBean = financeBillBiz.statisBillList(pageBean);
			
			this.makeShow(pageBean.getResult(), billTypeList);
			
			Map<String, Integer> pageTotal=new HashMap<String, Integer>();
			for(Map map: pageBean.getResult()){
				Integer n1 = (pageTotal.get("num")==null)?0:pageTotal.get("num");
				Integer n2 = (pageTotal.get("received_num")==null)?0:pageTotal.get("received_num");
				Integer n3 = (pageTotal.get("returned_num")==null)?0:pageTotal.get("returned_num");
				Integer m1 = (map.get("num")==null)?0:(Integer)map.get("num");
				Integer m2 = (map.get("received_num")==null)?0:(Integer)map.get("received_num");
				Integer m3 = (map.get("returned_num")==null)?0:(Integer)map.get("returned_num");
				pageTotal.put("num", n1+ m1);
				pageTotal.put("received_num", n2 + m2);
				pageTotal.put("returned_num", n3 + m3);
			}
			Map total = financeBillBiz.statisBillTotal(parameter);
			
			result.setPageBean(pageBean);
			result.setPageTotal(pageTotal);
			result.setTotal(total);
			result.setBillTypeList(billTypeList);
		}catch(Exception ex){
			result.setErrorCode(FinanceErrorCode.MODIFY_ERROR);
		}
		
		return result;
	}
	
	private void makeShow(List<Map> result, List<DicInfo> billTypeList){
		for(int i=0; i<result.size(); i++){
			Map map = result.get(i);
			if (map.get("total_adult")==null){
				map.put("total_adult", 0);
			}
			if (map.get("total_child")==null){
				map.put("total_child", 0);
			}
			if (map.get("total_guide")==null){
				map.put("total_guide", 0);
			}
			
			if(billTypeList != null){
				DicInfo info = null;
				for(int j = 0; j < billTypeList.size(); j++){
					info = billTypeList.get(j);
					if(map.get("bill_type").equals(info.getCode())){
						map.put("bill_type", info.getValue());
						break;
					}
				}
			}else{
				map.put("bill_type", "");
			}
			
			map.put("appli_state", billStates.get(map.get("appli_state")));
		}
	}
	
}
