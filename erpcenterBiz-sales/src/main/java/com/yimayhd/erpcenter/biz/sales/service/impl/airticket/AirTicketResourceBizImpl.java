package com.yimayhd.erpcenter.biz.sales.service.impl.airticket;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.airticket.AirTicketResourceBiz;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketLeg;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketResource;
import com.yimayhd.erpcenter.dal.sales.client.airticket.service.AirTicketResourceDal;

public class AirTicketResourceBizImpl implements AirTicketResourceBiz{
	
	@Autowired
	private AirTicketResourceDal airTicketResourceDal;
	
	@Override
	@Transactional
	public void saveResource(AirTicketResource airTicketResource ,String legList) throws ParseException {
		airTicketResourceDal.saveResource(airTicketResource, legList);
	}
	
	public void saveResource(AirTicketResource airTicketResource, ArrayList<AirTicketLeg> legList) throws ParseException {
		airTicketResourceDal.saveResource(airTicketResource, legList);
	}
	
	@Override
	@Transactional
	public void deleteResource(Integer resourceId,Integer bizId){
		airTicketResourceDal.deleteResource(resourceId, bizId);
	}
	@Override
	@Transactional
	public void updateResource(AirTicketResource airTicketResource, String legList) throws ParseException{
		airTicketResourceDal.updateResource(airTicketResource, legList);
	}
	
	@Override
	public void refreshAppliedNumber(Integer id){
		airTicketResourceDal.refreshAppliedNumber(id);
	}
	
	@Override
	@Transactional
	public AirTicketResource findResource(Integer resourceId,Integer bizId){
		return airTicketResourceDal.findResource(resourceId, bizId);
	}
	
	@Override
	public Map<Integer, AirTicketResource> findResourceByIdList(Integer bizId, List<Integer> resourceIdList){
		return airTicketResourceDal.findResourceByIdList(bizId, resourceIdList);
	}

	@Override
	public PageBean<AirTicketResource> selectResourceListPage(PageBean<AirTicketResource> pageBean){
		return airTicketResourceDal.selectResourceListPage(pageBean);
	}
	
	@Override
	public HashMap<String, Integer> countResourceList(PageBean<AirTicketResource> pageBean){
		return airTicketResourceDal.countResourceList(pageBean);
	}
	
	@Override
	public Integer getOrderNum(Integer resourceId,Integer bizId) {
		return airTicketResourceDal.getOrderNum(resourceId, bizId);
	}
	@Override
	public List<String> getFuzzyDepCityList(String prefixDepCity) {
		return airTicketResourceDal.getFuzzyDepCityList(prefixDepCity);
	}
	@Override
	public List<AirTicketLeg> findLegsByResourceId(Integer resourceId) {
		return airTicketResourceDal.findLegsByResourceId(resourceId);
	}
	
	@Override 
	public Map<Integer, List<AirTicketLeg>> findLegsByResourceIdList(List<Integer> resourceIdList){
		return airTicketResourceDal.findLegsByResourceIdList(resourceIdList);
	}
	
	@Override
	public List<String> getLineTemplateNames(Integer bizId, String keyword){
		return airTicketResourceDal.getLineTemplateNames(bizId, keyword);
	}
	@Override
	public List<Map> getLineTemplates(Integer bizId, String lineName){
		return airTicketResourceDal.getLineTemplates(bizId, lineName);
	}
	
	@Override
	public void saveTemplate(Integer bizId, String lineName, String legList) throws ParseException{
		airTicketResourceDal.saveTemplate(bizId, lineName, legList);
	}
	
	@Override
	public void saveTemplate(Integer bizId, String lineName, List<AirTicketLeg> legList){
		airTicketResourceDal.saveTemplate(bizId, lineName, legList);
	}
	
	@Override
	public void deleteLineTemplate(Integer bizId, String lineName){
		airTicketResourceDal.deleteLineTemplate(bizId, lineName);
	}
}
