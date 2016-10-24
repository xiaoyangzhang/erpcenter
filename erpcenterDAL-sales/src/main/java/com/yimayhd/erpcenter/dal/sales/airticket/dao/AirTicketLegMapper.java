package com.yihg.airticket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.airticket.po.AirTicketLeg;

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
