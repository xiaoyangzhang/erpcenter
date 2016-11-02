package com.yimayhd.erpcenter.facade.ticket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.basic.po.AirLine;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketLeg;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketResource;
import com.yimayhd.erpcenter.facade.ticket.query.SaveResourceDTO;
import com.yimayhd.erpcenter.facade.ticket.query.ShowListResourceDTO;
import com.yimayhd.erpcenter.facade.ticket.result.EditResourceResult;
import com.yimayhd.erpcenter.facade.ticket.result.ResultSupport;
import com.yimayhd.erpcenter.facade.ticket.result.ShowListResourceResult;

public interface ResourceFacade {
	
	ShowListResourceResult showList(ShowListResourceDTO dto);
	
	/*
	 * return ["北京-昆明", "北京-昆明-大理-北京", ...]
	 */
	List<String> getLineTemplateNames(Integer bizId, String keyword);
	
	List<Map> getLineTemplates(Integer bizId, String templateName);
	
	EditResourceResult editResource(Integer id, Integer bizId);
	
	ResultSupport save(SaveResourceDTO dto);
	
	ResultSupport save2(Integer bizId, AirTicketResource ticketResource, ArrayList<AirTicketLeg> legList);
	
	ResultSupport deleteResource(Integer id, Integer bizId);
	
	Map<Integer, String> getTicketSuppliers(Integer bizId);
	
	List<Map<String,String>> getFuzzySearchList(String cityName);
	
	List<String> getFuzzyDepCityList(String depCity);
	
	AirLine findAirLine(String date, String airCode, String depCity, String arrCity);
	
	ResultSupport deleteLineTemplate(Integer bizId, String lineName);
}
