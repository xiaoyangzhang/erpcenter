package com.yimayhd.erpcenter.dal.sales.airticket.dao;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketLeg;

public interface AirTicketLegMapper {
	/**
	 * 插入
	 * @param leg
	 * @return
	 */
	int save(AirTicketLeg leg);
	/**
	 * 根据资源ID删除相关航段
	 * @param resourceId
	 * @return
	 */
	int deleteByResourceId(Integer resourceId);
	/**
	 * 查询资源ID相关的航段
	 * @param resourceId
	 * @return
	 */
	List<AirTicketLeg> findLegsByResourceId(Integer resourceId);
	//void insertBtach(@Param("legList")List<AirTicketLeg> legList);
}
