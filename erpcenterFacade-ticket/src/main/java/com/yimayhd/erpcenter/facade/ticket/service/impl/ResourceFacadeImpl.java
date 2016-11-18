package com.yimayhd.erpcenter.facade.ticket.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.AirLineBiz;
import com.yimayhd.erpcenter.biz.basic.service.AirPortBiz;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketRequestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketResourceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderGuestBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderTransportBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.dal.basic.po.AirLine;
import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketResourceBO;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketLeg;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketResource;
import com.yimayhd.erpcenter.facade.ticket.errorcode.TicketErrorCode;
import com.yimayhd.erpcenter.facade.ticket.query.SaveResourceDTO;
import com.yimayhd.erpcenter.facade.ticket.query.ShowListResourceDTO;
import com.yimayhd.erpcenter.facade.ticket.result.EditResourceResult;
import com.yimayhd.erpcenter.facade.ticket.result.ResultSupport;
import com.yimayhd.erpcenter.facade.ticket.result.ShowListResourceResult;
import com.yimayhd.erpcenter.facade.ticket.result.WebResult;
import com.yimayhd.erpcenter.facade.ticket.service.ResourceFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.dal.constants.Constants;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

public class ResourceFacadeImpl implements ResourceFacade{

	@Autowired
	private AirTicketRequestBiz airTicketRequestBiz;
	
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	
	@Autowired
	private AirTicketResourceBiz airTicketResourceBiz;
	
	@Autowired
	private GroupOrderGuestBiz groupOrderGuestBiz; 
	
	@Autowired
	private DicBiz dicBiz;
	
	@Autowired
	private GroupOrderTransportBiz groupOrderTransportBiz;
	
	@Autowired
	private BookingSupplierBiz bookingSupplierBiz;
	
	@Autowired
	private SupplierBiz supplierBiz;
	
	@Autowired
	private TourGroupBiz tourGroupBiz;
	
	@Autowired
	private AirTicketOrderBiz airTicketOrderBiz;
	
	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	
	@Autowired
	private AirPortBiz airPortBiz;
	
	@Autowired
	private AirLineBiz airLineBiz; 
	

	@Override
	public ShowListResourceResult showList(ShowListResourceDTO dto) {
		
		String ajax = dto.getAjax();
		String apply = dto.getApply(); // for new air ticket apply page.
		
		if (ajax!=null && ajax.equals("1")){
			dto.setPageSize(10);
		}
		
		PageBean<AirTicketResource> pageBean = new PageBean<AirTicketResource>();
		pageBean.setPageSize(new Integer(dto.getPageSize()));
		pageBean.setPage(new Integer(dto.getPage()));
		
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("bizId", dto.getBizId());
		//parameter.put("set", WebUtils.getDataUserIdSet(request));
		if (dto.getResourceNumber() != ""){
			parameter.put("resourceNumber", dto.getResourceNumber());
		}
		if (dto.getDepDateFrom() != ""){
			parameter.put("dateType", dto.getDateType()); 
			parameter.put("depDateFrom", dto.getDepDateFrom());
		}
		if (dto.getDepDateTo() != ""){
			parameter.put("dateType", dto.getDateType()); 
			parameter.put("depDateTo", dto.getDepDateTo());
		}
		if (dto.getDepCity() != ""){
			parameter.put("depCity", dto.getDepCity());
		}
		if (dto.getLineName() != ""){
			parameter.put("lineName", dto.getLineName());
		}
		if (dto.getType() !=""){
			parameter.put("type", dto.getType());
		}
		if (dto.getEndIssueDateFrom() != null){
			parameter.put("endIssueDateFrom", dto.getEndIssueDateFrom());
			parameter.put("endIssueTimeFrom", dto.getEndIssueDateFrom() + " 00:00");
		}
		if (dto.getEndIssueDateTo() != null){
			parameter.put("endIssueDateTo", dto.getEndIssueDateTo());
			parameter.put("endIssueTimeTo", dto.getEndIssueDateTo() +" 24:00");
		}
		
		pageBean.setParameter(parameter);
		HashMap<String, Integer> count = airTicketResourceBiz.countResourceList(pageBean);
		pageBean = airTicketResourceBiz.selectResourceListPage(pageBean);

		ArrayList<AirTicketResourceBO> boList = new ArrayList<AirTicketResourceBO>();
		for(int i=0; i<pageBean.getResult().size(); i++){
			AirTicketResourceBO bo = new AirTicketResourceBO(pageBean.getResult().get(i));
			bo.setLegList(airTicketResourceBiz.findLegsByResourceId(bo.getPo().getId()));
			boList.add(bo);
		}
		
		ShowListResourceResult result = new ShowListResourceResult();
		result.setPageBean(pageBean);
		result.setBoList(boList);
		result.setCount(count);
		
		return result;
	}


	@Override
	public List<String> getLineTemplateNames(Integer bizId, String keyword) {
		return airTicketResourceBiz.getLineTemplateNames(bizId, keyword);
	}
	
	@Override
	public List<Map> getLineTemplates(Integer bizId, String templateName) {
		List<Map> templates = airTicketResourceBiz.getLineTemplates(bizId, templateName);
		return templates;
	}

	@Override
	public EditResourceResult editResource(Integer id, Integer bizId) {
		
		AirTicketResource po = airTicketResourceBiz.findResource(id, bizId);
		AirTicketResourceBO resourceBo = new AirTicketResourceBO(po);
		
		EditResourceResult result = new EditResourceResult();
		result.setResourceBo(resourceBo);
		result.setLegList(airTicketResourceBiz.findLegsByResourceId(po.getId()));
		result.setTemplateNames(airTicketResourceBiz.getLineTemplateNames(bizId, ""));

		return result;
	}


	@Override
	public ResultSupport save(SaveResourceDTO dto) {
		
		ResultSupport result = new ResultSupport();
		try{
			AirTicketResource po = dto.getAirTicketResourcePo();
			
			Integer bizId = dto.getBizId();
			po.setEndIssueTime(dto.getEndIssueTime());
			po.setBizId(bizId);
			String saveTemplate = dto.getSaveTemplate();
			if (saveTemplate!=null && saveTemplate.equals("save")){
				airTicketResourceBiz.saveTemplate(bizId, po.getLineName(), dto.getLegList());
			}
			po.setDepCity(dto.getDepCityFirst());
			po.setDepDate(dto.getDepDateFirst());
			if(po.getId() == null || po.getId() == 0){
				po.setCreaterId(dto.getEmployeeId());
				po.setCreaterName(dto.getEmployeeName());
				airTicketResourceBiz.saveResource(po, dto.getLegList());
			}else{
				po.setOperatorId(dto.getEmployeeId());
				po.setOperatorName(dto.getEmployeeName());
				airTicketResourceBiz.updateResource(po, dto.getLegList());
			}
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}
	
	@Override
	public ResultSupport save2(Integer bizId, AirTicketResource ticketResource, ArrayList<AirTicketLeg> legList) {
		ResultSupport result = new ResultSupport();
		try{
			
			airTicketResourceBiz.saveTemplate(bizId, ticketResource.getLineName(), legList);
			airTicketResourceBiz.saveResource(ticketResource, legList);
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}


	@Override
	public ResultSupport deleteResource(Integer id, Integer bizId) {
		
		ResultSupport result = new ResultSupport();
		try{
			airTicketResourceBiz.deleteResource(id, bizId);
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}


	@Override
	public Map<Integer, String> getTicketSuppliers(Integer bizId) {
		Map<Integer, String> ticketSuppliers=new HashMap<Integer, String>();
		SupplierInfo supplierInfo = new SupplierInfo();
		supplierInfo.setSupplierType(Constants.AIRTICKETAGENT);
		PageBean<SupplierInfo> pageBean = new PageBean<SupplierInfo>();
		pageBean.setPageSize(1000);
		pageBean.setParameter(supplierInfo);
		pageBean.setPage(1);
		pageBean = supplierBiz.selectPrivateSupplierList(pageBean, bizId);
		for (int i=0; i<pageBean.getResult().size(); i++){
			SupplierInfo s = pageBean.getResult().get(i);
			ticketSuppliers.put(s.getId(), s.getNameFull());
		}
		return ticketSuppliers;
	}


	@Override
	public List<Map<String, String>> getFuzzySearchList(String cityName) {
		
 		List<Map<String,String>> list = airPortBiz.getFuzzySearchList(cityName);
		return list;
	}


	@Override
	public List<String> getFuzzyDepCityList(String depCity) {
		List<String> list = airTicketResourceBiz.getFuzzyDepCityList(depCity);
		return list;
	}


	@Override
	public WebResult<AirLine> findAirLine(String date, String airCode, String depCity, String arrCity) {
		AirLine line = new AirLine();
		WebResult<AirLine> result = new WebResult<AirLine>();
		try {
			line = airLineBiz.findAirLine(date, airCode, depCity, arrCity);
			result.setValue(line);
			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			result.setResultMsg(e.getMessage());
//			e.printStackTrace();
		}
		return result;
	}


	@Override
	public ResultSupport deleteLineTemplate(Integer bizId, String lineName) {
		
		ResultSupport result = new ResultSupport();
		try{
			airTicketResourceBiz.deleteLineTemplate(bizId, lineName);
		}catch(Exception ex){
			result.setErrorCode(TicketErrorCode.MODIFY_ERROR);
			ex.printStackTrace();
		}
		return result;
	}
	
}
