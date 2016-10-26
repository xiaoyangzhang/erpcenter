package com.yimayhd.erpcenter.dal.sales.client.airticket.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketLeg;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketResource;

public interface AirTicketResourceDal{
	
	/**
	 * 增加机票资源
	 * @param airTicketResource
	 * @return
	 */
	void saveResource(AirTicketResource resource, String legList) throws ParseException;
	void saveResource(AirTicketResource resource, ArrayList<AirTicketLeg> legList) throws ParseException;

	
	/**
	 * 删除资源记录,要求条件：
	 *  1、业务主键 （resourceId）不能为空
	 *  2、商家ID（bizId）不能为空
	 * @param resourceId
	 * @param bizId
	 * @return
	 * @throws Exception 
	 */
	void deleteResource(Integer resourceId,Integer bizId);
	/**
	 * 更新机票资源,要求条件：
	 *  1、资源对象不为空 （resource ： not null）
	 *  2、资源对象业务主键不为空（resource.id : not null）
	 *  3、资源商家ID不为空(resource.biz_id : not null)
	 * @param airTicketResource
	 * @return
	 * @throws Exception 
	 */
	void updateResource(AirTicketResource resource, String legList) throws ParseException;
	void refreshAppliedNumber(Integer id);
	/**
	 * 通过resourceId和BIZ_ID返回资源记录,要求条件：
	 *  1、业务主键 （resourceId）不能为空
	 *  2、商家ID（biz_id）不能为空
	 * @param resourceId
	 * @param bizId
	 * @return
	 * @throws Exception 
	 */
	AirTicketResource findResource(Integer resourceId,Integer bizId);
	
	Map<Integer, AirTicketResource> findResourceByIdList(Integer bizId, List<Integer> resourceIdList);
	/**
	 * 分页查询,要求条件：
	 * 	1、pageBean不为空
	 *  2、pageBean中parameter不为空
	 *  3、parameter中bizId不为空
	 *  4. Parameter: {depDate:"", arrDate:"", airCode:"", resourceNumber:"", depCity:"", arrCity:""}
	 * @param pageBean
	 * @return
	 * @throws Exception 
	 */
	PageBean<AirTicketResource> selectResourceListPage(PageBean<AirTicketResource> pageBean);
	

	HashMap<String, Integer> countResourceList(PageBean<AirTicketResource> pageBean);
	
	/**
	 * 查询当前资源的订单数量 ，要求条件：
	 *  resourceId 不为空
	 *  bizId 不为空
	 * @param param
	 * @return
	 */
	Integer getOrderNum(Integer resourceId,Integer bizId);
	/**
	 * 资源list 出发城市自动补全
	 * @param depCity
	 * @return
	 */
	List<String> getFuzzyDepCityList(String prefixDepCity);
	/**
	 * 根据资源ID查询航段
	 * @param id
	 * @return
	 */
	List<AirTicketLeg> findLegsByResourceId(Integer resourceId);
	Map<Integer, List<AirTicketLeg>> findLegsByResourceIdList(List<Integer> resourceIdList);
	
	/*
	 * return ["北京-昆明", "北京-昆明-大理-北京", ...]
	 */
	List<String> getLineTemplateNames(Integer bizId, String keyword);
	
	/*
	 * return [{"air_code"=>"MU2703", "dep_city"=>"北京", "date_offset"=>"0", "arr_city"=>"昆明"}, {...}]
	 */
	List<Map> getLineTemplates(Integer bizId, String lineName);
	
	void saveTemplate(Integer bizId, String lineName, String legList) throws ParseException;
	void saveTemplate(Integer bizId, String lineName, List<AirTicketLeg> legList) ;
	
	void deleteLineTemplate(Integer bizId, String lineName);
	//void insertBatch(List<Map<String, String>> resources);
//	/**
//	 * 获取航线列表
//	 * @param bizId
//	 * @param name
//	 * @return
//	 */
//	List<Map<String, String>> airLineList(Integer bizId, String name);
}
