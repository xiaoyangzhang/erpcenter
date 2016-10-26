package com.yimayhd.erpcenter.dal.sales.airticket.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketResource;

public interface AirTicketResourceMapper {
	/**
	 * 插入
	 * @param record
	 * @return
	 */
	int insert(AirTicketResource record);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
    int delete(@Param("id") Integer id,@Param("bizId") Integer bizId);
    
    /**
     * 更新
     * @param record
     * @return
     */
    int update(AirTicketResource record);
    int refreshAppliedNumber(@Param("id") Integer id);
    /**
     * 查询
     * @param id
     * @return
     */
    AirTicketResource findResource(@Param("id") Integer id,@Param("bizId") Integer bizId);
    List<AirTicketResource> findResourceByIdList(@Param("bizId") Integer bizId, @Param("idList") String idList);
    /**
     * 返回当前商家当天最大的采购单号
     * @param param
     * @return
     */
    Integer getCurrMaxResourceNumber(Map<String,Object> param);
   
    /**
     * 分页查询
     * @param pageBean
     * @return
     */
    List<AirTicketResource> selectResourceListPage(PageBean<AirTicketResource> pageBean);

    HashMap<String, Integer> countResourceList(PageBean<AirTicketResource> pageBean);
    /**
     * 得到当前资源的订单数量
     * @param resource
     * @return
     */
    Integer getOrderNum(@Param("resourceId") Integer resourceId,@Param("bizId") Integer bizId);
    /**
     * 资源list页面输入城市前缀返回相应列表
     * @param prefixDepCity
     * @return
     */
	List<String> getFuzzyDepCityList(String prefixDepCity);
    
	List<String> getLineTemplateNames(@Param("bizId") Integer bizId, @Param("keyword")String keyword);
	List<Map> getLineTemplates(@Param("bizId") Integer bizId,@Param("lineName") String lineName);
	
	Integer deleteTemplate(@Param("bizId")Integer bizId, @Param("lineName") String lineName);
	Integer insertTemplate(@Param("bizId")Integer bizId, @Param("lineName") String lineName, 
			@Param("dateOffset")Integer dateOffset, @Param("depCity") String depCity, 
			@Param("arrCity") String arrCity, @Param("airCode") String airCode);
	/**
	 * 获取航线列表
	 * @param bizId
	 * @param name
	 * @return
	 */
	List<Map<String, String>> getAirLineList(@Param("bizId") Integer bizId, @Param("name") String name);
	//void insertBtach(@Param("resourceList")List<AirTicketResource> resourceList);
	
	/**
	 * 更新依赖资源的未出票订单价格
	 * @param id
	 * @return
	 */
	int updateUnissuedOrderPrice(@Param("id") Integer id, @Param("price")BigDecimal price); 
}