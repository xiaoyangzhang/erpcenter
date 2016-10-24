package com.yimayhd.erpcenter.biz.sales.service.impl.airticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.basic.utils.DateUtils;
import com.yimayhd.erpcenter.dal.sales.airticket.dao.AirTicketLegMapper;
import com.yimayhd.erpcenter.dal.sales.airticket.dao.AirTicketResourceMapper;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketLeg;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketResource;
import com.yimayhd.erpcenter.dal.sales.client.airticket.service.AirTicketRequestDal;
import com.yimayhd.erpcenter.dal.sales.client.airticket.service.AirTicketResourceDal;

public class AirTicketResourceDalImpl implements AirTicketResourceDal{
	
	@Autowired
	private AirTicketResourceMapper resourceMapper;
	@Autowired
	private AirTicketRequestDal requestDal;
	@Autowired
	private AirTicketLegMapper legMapper;
	
	@Override
	@Transactional
	public void saveResource(AirTicketResource airTicketResource ,String legList) throws ParseException {
		/* 赋值采购单号
		 * param  : 查询参数({date:'150818',biz_id:'商家ID'})
		 * prefix : 六位日期格式
		 * suffix : currMaxNumber + 1 并补全位数
		 */
		airTicketResource.setResourceNumber(newResourceNumber(airTicketResource));
		resourceMapper.insert(airTicketResource);
		legBatchSave(airTicketResource.getId(), legList);
	}
	
	public void saveResource(AirTicketResource airTicketResource, ArrayList<AirTicketLeg> legList){
		airTicketResource.setResourceNumber(newResourceNumber(airTicketResource));
		resourceMapper.insert(airTicketResource);
		for (AirTicketLeg leg : legList){
			leg.setResourceId(airTicketResource.getId());
			legMapper.save(leg);
		}
		return ;
	}
	
	private String newResourceNumber(AirTicketResource airTicketResource){
		Map<String,Object> param = new HashMap<String,Object>();
		String prefix = DateUtils.format(new Date(), "yyMMdd");
		param.put("date", prefix);
		param.put("bizId", airTicketResource.getBizId());
		Integer currMaxNumber = getCurrMaxResourceNumber(param);
		String suffix = supplementAndPlusOne(currMaxNumber == null ? 0 : currMaxNumber, 3, "0");
		return prefix + suffix;
	}
	
	@Transactional
	private void legBatchSave(Integer resourceId,String legList) throws ParseException{
		JSONArray legArray = JSON.parseArray(legList);
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(int i = 0 ; i < legArray.size() ; i++){
			JSONObject sepObj = legArray.getJSONObject(i);
			AirTicketLeg leg = new AirTicketLeg();
			leg.setResourceId(resourceId);
			leg.setAirCode(sepObj.getString("airCode"));
			leg.setDepCity(sepObj.getString("depCity"));
			leg.setDepDate(sepObj.getDate("depDate"));
			leg.setArrCity(sepObj.getString("arrCity"));
			if (!sepObj.getString("depTime").equals("")){
				leg.setDepTime(sdfTime.parse(sepObj.getString("depTime")));
			}
			if (!sepObj.getString("arrTime").equals("")){
				leg.setArrTime(sdfTime.parse(sepObj.getString("arrTime")));
			}
			legMapper.save(leg);
		}
	}
	@Override
	@Transactional
	public void deleteResource(Integer resourceId,Integer bizId){
		Integer countRequest = requestDal.countRequestByResource(resourceId, bizId);
		if (countRequest>0){
			throw new ClientException("请先删除资源下所有申请");
		}else {
			legMapper.deleteByResourceId(resourceId);
			resourceMapper.delete(resourceId , bizId);
		}
	}
	@Override
	@Transactional
	public void updateResource(AirTicketResource airTicketResource, String legList) throws ParseException{
		Integer resourceId = airTicketResource.getId();
		legMapper.deleteByResourceId(resourceId);
		legMapper.deleteByResourceId(resourceId);
		legBatchSave(resourceId, legList);
		resourceMapper.update(airTicketResource);
		resourceMapper.updateUnissuedOrderPrice(resourceId, airTicketResource.getPrice());
	}
	
	@Override
	public void refreshAppliedNumber(Integer id){
		resourceMapper.refreshAppliedNumber(id);
	}
	
	@Override
	@Transactional
	public AirTicketResource findResource(Integer resourceId,Integer bizId){
		AirTicketResource resource = resourceMapper.findResource(resourceId , bizId); 
		if(resource == null){
			return null;
		} 
		//Integer orderNum = resourceMapper.getOrderNum(resourceId, bizId);
		//orderNum = orderNum == null ? 0 :orderNum;
		//resource.setAvailableNumber((resource.getTotalNumber() - orderNum) + "");
		return resource;
	}
	
	@Override
	public Map<Integer, AirTicketResource> findResourceByIdList(Integer bizId, List<Integer> resourceIdList){
		String strIdList = StringUtils.join(resourceIdList, ",");
		HashMap<Integer, AirTicketResource> resourceMap = new HashMap<Integer, AirTicketResource>();
		if (resourceIdList.size()==0){return resourceMap;}
		List<AirTicketResource> resourceList = resourceMapper.findResourceByIdList(bizId, strIdList);
		for (AirTicketResource resource : resourceList){
			resourceMap.put(resource.getId(), resource);
		}
		return resourceMap;
	}
	/**
	 * 返回当前商家当天最大采购单号后三位
	 * @param param{date:'150818',biz_id:'商家ID'}
	 * @return
	 */
	private Integer getCurrMaxResourceNumber(Map<String, Object> param) {
		return resourceMapper.getCurrMaxResourceNumber(param);
	}
	/**
	 * 
	 * @param number 		被加的数字
	 * @param len			总共几位
	 * @param supplement	补充位 字符
	 * @return
	 */
	private String supplementAndPlusOne(Integer number,int len,String supplement){
		String blank = "" , prefix = blank;
		int resultNumber = number + 1 , resultLen = (resultNumber  + blank).length();
		for(int i = 0; i < len - resultLen; i++){
			prefix += supplement;
		}
		return prefix + resultNumber;
	}
	@Override
	public PageBean<AirTicketResource> selectResourceListPage(PageBean<AirTicketResource> pageBean){
		List<AirTicketResource> list = resourceMapper.selectResourceListPage(pageBean);
		pageBean.setResult(list);
		return pageBean;
	}
	
	@Override
	public HashMap<String, Integer> countResourceList(PageBean<AirTicketResource> pageBean){
		return resourceMapper.countResourceList(pageBean);
	}
	
	@Override
	public Integer getOrderNum(Integer resourceId,Integer bizId) {
		return resourceMapper.getOrderNum(resourceId,bizId);
	}
	@Override
	public List<String> getFuzzyDepCityList(String prefixDepCity) {
		return resourceMapper.getFuzzyDepCityList(prefixDepCity);
	}
	@Override
	public List<AirTicketLeg> findLegsByResourceId(Integer resourceId) {
		return legMapper.findLegsByResourceId(resourceId);
	}
	
	@Override 
	public Map<Integer, List<AirTicketLeg>> findLegsByResourceIdList(List<Integer> resourceIdList){
		HashMap<Integer, List<AirTicketLeg>> legsMap = new HashMap<Integer, List<AirTicketLeg>>();
		for (Integer id : resourceIdList){
			legsMap.put(id, legMapper.findLegsByResourceId(id));
		}
		return legsMap;
	}
	
	@Override
	public List<String> getLineTemplateNames(Integer bizId, String keyword){
		return resourceMapper.getLineTemplateNames(bizId, keyword);
	}
	@Override
	public List<Map> getLineTemplates(Integer bizId, String lineName){
		return resourceMapper.getLineTemplates(bizId, lineName);
	}
	
	@Override
	public void saveTemplate(Integer bizId, String lineName, String legList) throws ParseException{
		List<AirTicketLeg> legs = new ArrayList<AirTicketLeg>(); // = JSON.parseArray(legList, AirTicketLeg.class);
		JSONArray legArray = JSON.parseArray(legList);
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(int i = 0 ; i < legArray.size() ; i++){
			JSONObject sepObj = legArray.getJSONObject(i);
			AirTicketLeg leg = new AirTicketLeg();
			leg.setAirCode(sepObj.getString("airCode"));
			leg.setDepCity(sepObj.getString("depCity"));
			leg.setDepDate(sepObj.getDate("depDate"));
			leg.setArrCity(sepObj.getString("arrCity"));
			if (!sepObj.getString("depTime").equals("")){
				leg.setDepTime(sdfTime.parse(sepObj.getString("depTime")));
			}
			if (!sepObj.getString("arrTime").equals("")){
				leg.setArrTime(sdfTime.parse(sepObj.getString("arrTime")));
			}
			legs.add(leg);
		}
		this.saveTemplate(bizId, lineName, legs);
	}
	
	@Override
	public void saveTemplate(Integer bizId, String lineName, List<AirTicketLeg> legList){
		List<Map> maps = this.getLineTemplates(bizId, lineName);
		if (isSameTemplate(maps, legList)){
			return ;
		}
		resourceMapper.deleteTemplate(bizId, lineName);
		for(int i=0; i<legList.size(); i++){
			AirTicketLeg leg=legList.get(i);
			Integer dateOffset = 0;
			if (i!=0){dateOffset=getDateOffset(leg.getDepDate(), legList.get(0).getDepDate());}
			resourceMapper.insertTemplate(bizId, lineName, dateOffset, leg.getDepCity(), leg.getArrCity(), leg.getAirCode());
		}
		return ;
	}
	
	@Override
	public void deleteLineTemplate(Integer bizId, String lineName){
		resourceMapper.deleteTemplate(bizId, lineName);
	}
	
	private boolean isSameTemplate(List<Map> maps, List<AirTicketLeg>legs){
		if (maps.size() != legs.size()){
			return false;
		}
		for (int i=0; i<maps.size(); i++){
			Map map=maps.get(i);
			AirTicketLeg leg=legs.get(i);
			if (!leg.getAirCode().equals(map.get("air_code"))){return false;}
			if (!leg.getDepCity().equals(map.get("dep_city"))){return false;}
			if (!leg.getArrCity().equals(map.get("arr_city"))){return false;}
			Integer dateOffset = 0;
			if (i!=0){dateOffset=getDateOffset(leg.getDepDate(), legs.get(0).getDepDate());}
			if (dateOffset!=map.get("date_offset")){return false;}
		}
		return true;
	}
	private Integer getDateOffset(Date day, Date startDay){
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		long time1 = cal.getTimeInMillis();
		cal.setTime(startDay);
		long time2 = cal.getTimeInMillis();
		long between_days=(time1-time2)/(1000*3600*24);
		return Integer.parseInt(String.valueOf(between_days)); 
	}
//	/**
//	 * 获取航线列表
//	 */
//	@Override
//	public List<Map<String, String>> airLineList(Integer bizId, String name) {
//		return resourceMapper.getAirLineList(bizId,name);
//	}
	
}
